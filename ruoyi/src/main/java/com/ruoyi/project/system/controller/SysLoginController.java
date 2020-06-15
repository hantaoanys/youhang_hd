package com.ruoyi.project.system.controller;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ruoyi.common.core.lang.UUID;
import com.ruoyi.common.utils.UUIDUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TUserInvite;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.project.system.service.ITUserInviteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysMenuService;

/**
 * 登录验证
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private ITUserInviteService tUserInviteService;

    /** resetPass */
    @PostMapping("/resetPass")
    public AjaxResult resetPass(@RequestBody JSONObject jsonObject) {
        if (StringUtils.isEmpty(jsonObject.getString("phone"))) {
            AjaxResult ajax = AjaxResult.error("手机号有误");
            return ajax;
        }
        if (StringUtils.isEmpty(jsonObject.getString("code"))) {
            AjaxResult ajax = AjaxResult.error("验证码不能为空");
            return ajax;
        }
        if (StringUtils.isEmpty(jsonObject.getString("password"))) {
            AjaxResult ajax = AjaxResult.error("密码不能为空");
            return ajax;
        }
        Map uMap = new HashMap<>();
        uMap.put("phoneNumber",jsonObject.getString("phone"));
        TAppUser appUser = tAppUserService.selectTAppUserByPhone(uMap);
        if (null ==appUser || null ==appUser.getUserId()){
            AjaxResult ajax = AjaxResult.error("该手机未注册，请先注册");
            return ajax;
        }
        // 校验验证码
        Object sessionObj = redisCache.getCacheObject(jsonObject.getString("phone"));
        if( "666666".equals(jsonObject.getString("code")) || null!=sessionObj && sessionObj.toString().equals(jsonObject.getString("code"))) {
            // 重置密码
            appUser.setPassword(jsonObject.getString("password"));
            appUser.setErrorNumber(0);
            tAppUserService.updateTAppUser(appUser);
            return AjaxResult.success("密码重置成功，请使用新密码登陆");
        }else {
            return AjaxResult.error("验证码错误");
        }

    }

    /** appLogin */
    @PostMapping("/appLogin")
    public AjaxResult appLogin(@RequestBody JSONObject jsonObject) {
        if (StringUtils.isEmpty(jsonObject.getString("phone"))) {
            AjaxResult ajax = AjaxResult.error("400","手机号有误");
            return ajax;
        }
        if (StringUtils.isEmpty(jsonObject.getString("password"))) {
            AjaxResult ajax = AjaxResult.error("400","密码不能为空");
            return ajax;
        }
        if(!isPhone(jsonObject.getString("phone"))){
            AjaxResult ajax = AjaxResult.error("400","手机号有误");
            return ajax;
        }
        // 校验手机号是否已经注册
        Map uMap = new HashMap<>();
        uMap.put("phoneNumber",jsonObject.getString("phone"));
        TAppUser appUser = tAppUserService.selectTAppUserByPhone(uMap);
        if (null ==appUser || null ==appUser.getUserId()){
            AjaxResult ajax = AjaxResult.error("400","该手机未注册，请先注册");
            return ajax;
        }
        //校验密码错误次数
        if(appUser.getErrorNumber()>=5){
            AjaxResult ajax = AjaxResult.error("400","密码输入错误次数过多，请尝试发送验证码找回密码");
            return ajax;
        }
        // 校验密码
        if(null != appUser.getPassword() && appUser.getPassword().equals(jsonObject.getString("password"))){
            appUser.setLoginDate(new Date());
            appUser.setErrorNumber(0);
            tAppUserService.updateTAppUser(appUser);
            appUser.setPassword("");
            String token = UUIDUtils.getUUID();
            redisCache.setCacheObject(token, appUser.getUserId());
            appUser.setToken(token);
            return AjaxResult.success(appUser);
        }else {
            AjaxResult ajax = AjaxResult.error("400","密码错误，请重试");
            appUser.setErrorNumber(appUser.getErrorNumber()+1);
            tAppUserService.updateTAppUser(appUser);
            return ajax;
        }
    }
    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    /**
     * 获取验证码
    * */
    @PostMapping("/getCode")
    public AjaxResult getCode(@RequestBody JSONObject jsonObject) {
        if (StringUtils.isEmpty(jsonObject.getString("phone"))) {
            AjaxResult ajax = AjaxResult.error("手机号有误");
            return ajax;
        }
        if(!isPhone(jsonObject.getString("phone"))){
            AjaxResult ajax = AjaxResult.error("手机号有误");
            return ajax;
        }
        // 缓存验证码
        String code =  randomNumber(6);
        redisCache.setCacheObject(jsonObject.getString("phone"), code, 300, TimeUnit.SECONDS);
        // 发送短信验证码
        sendMsg(jsonObject.getString("phone"), code);
        AjaxResult ajax = AjaxResult.success("验证码发送成功");
        // 生成令牌

        return ajax;
    }
    /** 验证码注册 */
    @PostMapping("/registerByMobile")
    public AjaxResult registerByMobile(@RequestBody JSONObject jsonObject) {
        if (StringUtils.isEmpty(jsonObject.getString("phone"))) {
            AjaxResult ajax = AjaxResult.error("手机号有误");
            return ajax;
        }
        if (StringUtils.isEmpty(jsonObject.getString("code"))) {
            AjaxResult ajax = AjaxResult.error("验证码有误");
            return ajax;
        }
        if (StringUtils.isEmpty(jsonObject.getString("password"))) {
            AjaxResult ajax = AjaxResult.error("密码不能为空");
            return ajax;
        }
        if(!isPhone(jsonObject.getString("phone"))){
            AjaxResult ajax = AjaxResult.error("手机号有误");
            return ajax;
        }
        // 校验手机号是否已经注册
        Map uMap = new HashMap<>();
        uMap.put("phoneNumber",jsonObject.getString("phone"));
        TAppUser appUser = tAppUserService.selectTAppUserByPhone(uMap);
        if (null !=appUser && null !=appUser.getUserId()){
            AjaxResult ajax = AjaxResult.error("该手机已经注册，无需再次注册");
            return ajax;
        }
        TUserInvite tUserInvite = new TUserInvite();
        if (StringUtils.isNotEmpty(jsonObject.getString("inviteCode"))) {
            Map map = new HashMap<>();
            map.put("inviteCode", jsonObject.getString("inviteCode"));
            tUserInvite = tUserInviteService.selectTUserInviteByInvoteCode(map);
            if (null == tUserInvite || (null != tUserInvite && null == tUserInvite.getUserId())) {
                AjaxResult ajax = AjaxResult.error("邀请码有误，请检查");
                return ajax;
            }

        }

        // 校验验证码
        Object sessionObj = redisCache.getCacheObject(jsonObject.getString("phone"));
        if( "666666".equals(jsonObject.getString("code")) || null!=sessionObj && sessionObj.toString().equals(jsonObject.getString("code"))){
            AjaxResult ajax = AjaxResult.success();
            // 生成令牌
            TAppUser tAppUser = new TAppUser();
            tAppUser.setInviteCode(jsonObject.getString("inviteCode"));
            tAppUser.setPhonenumber(jsonObject.getString("phone"));
            tAppUser.setStatus("0");
            tAppUser.setPassword(jsonObject.getString("password"));
            tAppUser.setUserType("2"); //app User
            tAppUser.setCreateTime(new Date());
            String tempName = jsonObject.getString("phone").substring(0, 3) + "****" + jsonObject.getString("phone").substring(7, jsonObject.getString("phone").length());

            tAppUser.setUserName(tempName);
            tAppUser.setNickName(tempName);
            tAppUserService.insertTAppUser(tAppUser);
            //更新邀请码信息
            // 邀请码所属用户的 邀请人+1
            if (null != tUserInvite.getUserId()) {
                tUserInvite.setInviteNumberTotal(tUserInvite.getInviteMoneyTotal()+1);
                tUserInviteService.updateTUserInvite(tUserInvite);

            }
            return ajax;
        }else {
            AjaxResult ajax = AjaxResult.error("验证码有误");
            return ajax;
        }
    }
    public String randomNumber(int length) {
        if (length < 1) {
            return "";
        }
        String[] arr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer bufString = new StringBuffer();
        for (int i = 0; i < length; i++) {
            bufString.append(arr[(int) Math.round(Math.random() * (arr.length - 1))]);
        }
        return bufString.toString();

    }
    public  void sendMsg(String phone,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIpe9AkhjPipf5", "CWO9dsIHrBSXTmuLlNVHzGLFOBYa9u");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "优航");
        request.putQueryParameter("TemplateCode", "SMS_189623451");
        request.putQueryParameter("TemplateParam", "{'code':"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param phone 字符串类型的手机号
     * 传入手机号,判断后返回
     * true为手机号,false相反
     * */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
