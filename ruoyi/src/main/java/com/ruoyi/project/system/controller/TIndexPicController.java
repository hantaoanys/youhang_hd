package com.ruoyi.project.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
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
import com.ruoyi.project.system.domain.TIndexPic;
import com.ruoyi.project.system.service.ITIndexPicService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.common.utils.file.FileUploadUtils.assertAllowed;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@RestController
@RequestMapping("/system/pic")
public class TIndexPicController extends BaseController
{
    @Autowired
    private ITIndexPicService tIndexPicService;
    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/list")
    public TableDataInfo list(TIndexPic tIndexPic)
    {
        startPage();
        List<TIndexPic> list = tIndexPicService.selectTIndexPicList(tIndexPic);
        return getDataTable(list);
    }

    @GetMapping("/export")
    public AjaxResult export(TIndexPic tIndexPic)
    {
        List<TIndexPic> list = tIndexPicService.selectTIndexPicList(tIndexPic);
        ExcelUtil<TIndexPic> util = new ExcelUtil<TIndexPic>(TIndexPic.class);
        return util.exportExcel(list, "pic");
    }

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tIndexPicService.selectTIndexPicById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody TIndexPic tIndexPic)
    {
        return toAjax(tIndexPicService.insertTIndexPic(tIndexPic));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody TIndexPic tIndexPic)
    {
        return toAjax(tIndexPicService.updateTIndexPic(tIndexPic));
    }

	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tIndexPicService.deleteTIndexPicByIds(ids));
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/uploadLunbo")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getlunboProfile();
            // 上传并返回新文件名称
            String fileName = upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
//            ajax.put("url", url);
            ajax.put("url", "http://119.45.144.182/img/lunbo"+fileName);
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
        String pathFileName = "/" + fileName;
        return pathFileName;
    }
}
