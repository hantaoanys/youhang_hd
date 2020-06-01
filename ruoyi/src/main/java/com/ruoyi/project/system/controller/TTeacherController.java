package com.ruoyi.project.system.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.ruoyi.common.constant.Constants;
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
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.TTeacher;
import com.ruoyi.project.system.service.ITTeacherService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.ruoyi.common.utils.file.FileUploadUtils.assertAllowed;
import static com.ruoyi.common.utils.file.FileUploadUtils.extractFilename;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@RestController
@RequestMapping("/system/teacher")
public class TTeacherController extends BaseController
{
    @Autowired
    private ITTeacherService tTeacherService;
    @Autowired
    private ServerConfig serverConfig;
    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TTeacher tTeacher)
    {
        startPage();
        List<TTeacher> list = tTeacherService.selectTTeacherList(tTeacher);
        return getDataTable(list);
    }

    /**
     *
     * @Title:图片上传返回路径
     * @Description
     * @param MultipartFile，request
     * @return  服务器保存的图片地址url
     * @throws
     */
    @PostMapping("upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile files, HttpServletRequest request) {
        String dicName="UpFile";//服务器上传文件夹名称
        String newFileName=files.getOriginalFilename();
        newFileName=newFileName.substring(0,newFileName.lastIndexOf("."));
        String picurl = saveFile(files,newFileName,dicName, request);

        return AjaxResult.success("上传成功");
    }
    public static String saveFile(MultipartFile filedata,String newFileName,String dicName, HttpServletRequest request) {
        // TODO Auto-generated method stub
        String pathval = request.getSession().getServletContext().getRealPath("/");
        // 根据配置文件获取服务器图片存放路径
        //String newFileName = String.valueOf(System.currentTimeMillis());
        Calendar cale = null;
        cale = Calendar.getInstance();
        String year = String.valueOf(cale.get(Calendar.YEAR));
        String month = String.valueOf(cale.get(Calendar.MONTH) + 1);
        String saveFilePath = "UpFile/"+dicName+"/"+year+"/"+month+"/";
        /* 构建文件目录 */
        File fileDir = new File(pathval + saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        // 上传的文件名
        String filename = filedata.getOriginalFilename();
        // 文件的扩张名
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            String imgPath = saveFilePath + newFileName + "." + extensionName;
            // System.out.println(pathval + imgPath);打印图片位置
            FileOutputStream out = new FileOutputStream(pathval + imgPath);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 通用上传请求
     */
    @PostMapping("/uploadTeacher")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getTeacherforProfile();
            // 上传并返回新文件名称
            String fileName = upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
//            ajax.put("url", url);
            ajax.put("url", "http://119.45.144.182/img/teacher"+fileName);
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

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TTeacher tTeacher)
    {
        List<TTeacher> list = tTeacherService.selectTTeacherList(tTeacher);
        ExcelUtil<TTeacher> util = new ExcelUtil<TTeacher>(TTeacher.class);
        return util.exportExcel(list, "teacher");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tTeacherService.selectTTeacherById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTeacher tTeacher)
    {
        return toAjax(tTeacherService.insertTTeacher(tTeacher));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTeacher tTeacher)
    {
        return toAjax(tTeacherService.updateTTeacher(tTeacher));
    }

    /**
     * 删除【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tTeacherService.deleteTTeacherByIds(ids));
    }
}
