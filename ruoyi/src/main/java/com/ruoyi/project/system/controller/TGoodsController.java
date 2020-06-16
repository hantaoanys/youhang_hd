package com.ruoyi.project.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TGoodcollect;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.project.system.service.ITGoodcollectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.TGoods;
import com.ruoyi.project.system.service.ITGoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.ruoyi.common.utils.file.FileUploadUtils.assertAllowed;

/**
 * 商品Controller
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@RestController
@RequestMapping("/system/goods")
public class TGoodsController extends BaseController
{
    @Autowired
    private ITGoodsService tGoodsService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ITGoodcollectService tGoodcollectService;

    @GetMapping("/list")
    public TableDataInfo list(TGoods tGoods)
    {
        startPage();
        List<TGoods> list = tGoodsService.selectTGoodsList(tGoods);
        return getDataTable(list);
    }


    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TGoods tGoods)
    {
        List<TGoods> list = tGoodsService.selectTGoodsList(tGoods);
        ExcelUtil<TGoods> util = new ExcelUtil<TGoods>(TGoods.class);
        return util.exportExcel(list, "goods");
    }

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tGoodsService.selectTGoodsById(id));
    }

    @GetMapping(value = "/app/{id}")
    public AjaxResult getAppInfo(@PathVariable("id") Long id, HttpServletRequest request)
    {

        TGoods tGoods =  tGoodsService.selectTGoodsById(id);
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token){
            uflag =false;
            tGoods.setCollect(false);
//            tNews.setLike(false);
        }else {
            Long userId = redisCache.getCacheObject(request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()) {
                uflag = false;
                tGoods.setCollect(false);
//                tNews.setLike(false);
            }
            if (uflag) {
                // 带出用户是否收藏
                TGoodcollect tGoodcollect = new TGoodcollect();
                tGoodcollect.setGoodId(id.toString());
                tGoodcollect.setUserId(userId.toString());
                List<TGoodcollect> c = tGoodcollectService.selectTGoodcollectList(tGoodcollect);
                if (null != c && c.size() > 0) {
                    tGoods.setCollect(true);
                } else {
                    tGoods.setCollect(false);
                }
            }
        }
        return AjaxResult.success(tGoods);
    }

    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGoods tGoods)
    {
        return toAjax(tGoodsService.insertTGoods(tGoods));
    }


    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGoods tGoods)
    {
        return toAjax(tGoodsService.updateTGoods(tGoods));
    }

    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tGoodsService.deleteTGoodsByIds(ids));
    }


    /**
     * 通用上传请求
     */
    @PostMapping("/uploadGoods")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getGoodProfile();
            // 上传并返回新文件名称
            String fileName = upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
//            ajax.put("url", url);
            ajax.put("url", "http://119.45.144.182/img/goods"+fileName);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = file.getOriginalFilename();

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException
    {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    private static final String getPathFileName(String uploadDir, String fileName) throws IOException
    {
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
//        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = "/"   + fileName;
        return pathFileName;
    }

}
