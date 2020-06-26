package com.ruoyi.project.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.redis.RedisCache;
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
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.ruoyi.common.utils.file.FileUploadUtils.assertAllowed;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@RestController
@RequestMapping("/app/user")
public class TAppUserController extends BaseController
{
    @Autowired
    private ITAppUserService tAppUserService;
    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询用户信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TAppUser tAppUser)
    {
        startPage();
        List<TAppUser> list = tAppUserService.selectTAppUserList(tAppUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TAppUser tAppUser)
    {
        List<TAppUser> list = tAppUserService.selectTAppUserList(tAppUser);
        ExcelUtil<TAppUser> util = new ExcelUtil<TAppUser>(TAppUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(tAppUserService.selectTAppUserById(userId));
    }

    /**
     * 新增用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAppUser tAppUser)
    {
        return toAjax(tAppUserService.insertTAppUser(tAppUser));
    }

    /**
     * 修改用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAppUser tAppUser)
    {
        return toAjax(tAppUserService.updateTAppUser(tAppUser));
    }


    /**
     * 修改用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/app/updateUser")
    public AjaxResult updateUser(@RequestBody TAppUser tAppUser)
    {
        return toAjax(tAppUserService.updateTAppUser(tAppUser));
    }

    /**
     * 删除用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(tAppUserService.deleteTAppUserByIds(userIds));
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    public Object uploadFile(MultipartFile file, HttpServletRequest request) throws Exception
    {
        TAppUser appUser = new TAppUser();
        try
        {
            //根据token取userID
            JSONObject ret = new JSONObject();
            String token = request.getHeader("token");
            Boolean uflag = true;
            if(null == token || "".equals(token)) {
                uflag = false;
                ret.put("msg","请先登录");
                return ret;
            }else {
                Long userId = redisCache.getCacheObject( request.getHeader("token"));
                //校验用户id是否存在
                appUser = tAppUserService.selectTAppUserById(userId);
                if (null == appUser || null == appUser.getUserId()){
                    ret.put("msg","请先登录");
                    return ret;
                }
            }
            // 上传文件路径
            String filePath = RuoYiConfig.getUserPictureProfile();
            // 上传并返回新文件名称
            String fileName = upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
//            ajax.put("url", url);
            ajax.put("url", "http://119.45.144.182/img/touxiang"+fileName);
            appUser.setAvatar("http://119.45.144.182/img/touxiang"+fileName);
            tAppUserService.updateTAppUser(appUser);
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
        String pathFileName = "/" +  fileName;
        return pathFileName;
    }
}
