package com.ruoyi.project.system.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ruoyi.common.utils.UUIDUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户商品Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@RestController
@RequestMapping("/system/order")
public class TUserOrderController extends BaseController
{
    @Autowired
    private ITUserOrderService tUserOrderService;
    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ITGoodsService tGoodsService;
    @Autowired
    private ITUserInviteService tUserInviteService;
    @Autowired
    private ITUserInvitegoodsService tUserInvitegoodsService;



    /**APP用户获取我的订单，历史订单*/
    @GetMapping("/app/list")
    public Object AppList(TUserOrder tUserOrder, HttpServletRequest request){
        JSONObject ret = new JSONObject();
        String token = request.getHeader("token");
        if(null == token || "".equals(token)) {
            ret.put("msg","请先登录");
            return ret;
        }else {
            Long userId = redisCache.getCacheObject( request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()){
                ret.put("msg","请先登录");
                return ret;
            }else {
                tUserOrder.setUserId(userId);
            }
        }
        startPage();
        List<TUserOrder> list = tUserOrderService.selectTUserOrderList(tUserOrder);
        return getDataTable(list);
    }


    /**
     * 查询用户商品列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TUserOrder tUserOrder)
    {
        startPage();
        List<TUserOrder> list = tUserOrderService.selectTUserOrderList(tUserOrder);
        return getDataTable(list);
    }

    /**
     * 导出用户商品列表
     */
    @Log(title = "用户商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUserOrder tUserOrder)
    {
        List<TUserOrder> list = tUserOrderService.selectTUserOrderList(tUserOrder);
        ExcelUtil<TUserOrder> util = new ExcelUtil<TUserOrder>(TUserOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取用户商品详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tUserOrderService.selectTUserOrderById(id));
    }

    /**
     * 新增用户商品
     */
    @Log(title = "用户商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserOrder tUserOrder)
    {
        return toAjax(tUserOrderService.insertTUserOrder(tUserOrder));
    }

    /**
     * 修改用户商品
     */
    @Log(title = "用户商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserOrder tUserOrder)
    {
        return toAjax(tUserOrderService.updateTUserOrder(tUserOrder));
    }

    /**
     * 删除用户商品
     */
    @Log(title = "用户商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tUserOrderService.deleteTUserOrderByIds(ids));
    }

    /**
     *      支付接口
     * */
    @PostMapping("/appPay")
    public Object appPay(@RequestBody JSONObject jsonObject, HttpServletRequest req) throws AlipayApiException {
        JSONObject ret = new JSONObject();
        String goodId = jsonObject.getString("goodId");
        String token = req.getHeader("token");
        if(null == token || "".equals(token)) {
            ret.put("msg","请先登录");
//            throw new Exception ("请先登录");
            return ret;
        }
        Long userId = redisCache.getCacheObject(req.getHeader("token"));
        //校验用户id是否存在
        TAppUser appUser = tAppUserService.selectTAppUserById(userId);
        if (null == appUser || null == appUser.getUserId()) {
            ret.put("msg", "请先登录");
            return ret;
        }

        //校验good
        TGoods tGoods = tGoodsService.selectTGoodsById(Long.valueOf(goodId));
        if (null==tGoods || null ==tGoods.getId()){
            ret.put("msg", "商品不存在");
            return ret;
        }
        //先创建预付费订单
        TUserOrder tUserOrder = new TUserOrder();
        tUserOrder.setGoodId(Long.valueOf(goodId));

        tUserOrder.setGoodName(tGoods.getName());
        tUserOrder.setGoodDesc(tGoods.getSketch());
        tUserOrder.setGoodPath(tGoods.getPicture());
//        tUserOrder.setGoodType(tGoods.getKeywords());
        tUserOrder.setPayMoney(tGoods.getPrice());
        tUserOrder.setServiceProcess("未付款"); //服务进度
        tUserOrder.setCreateTime(new Date());
        tUserOrder.setDelFlag("0"); //暂未付款
        tUserOrder.setUserId(userId);
        int count = tUserOrderService.insertTUserOrder(tUserOrder);
        System.out.println("主键是：");
        System.out.println(tUserOrder.getId());

        tUserOrder.setOrderId("DD"+getOrderIdByTime()+tUserOrder.getId());
        tUserOrderService.updateTUserOrder(tUserOrder);


        String appId = "2021001167649092";
        String priKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCigcuXKhKn8VeTSG0lRWeNDKHCWPFcNQmlRqpLKjbb8GL01am6QqJHfN9gzYZZ2xjTWVV3ode8LS/J/2toeQtY65Z3sgTzlZXwTxZGUAs6ge+BxkzinsAmybU1lKvHC04Y2m/u93ft+wEIjZr91BFnV8mtvQVSO3GHeAoe8Qj/1qapZvo20icYrrPcuYCY2Y2lAnlRedabMfVDq4tX/++9nAkQ2Ye2n7pj9bNPhEB+EyzN0474I0IQLJVCVEFXWPuJ0rWK+488zmAotGtrC9xQdp9K8UcYt6XUr2mdQcT8w9IfiPOlQx1yHZxlHGahCBr6CpOjp1zb10WPeuhoxAWrAgMBAAECggEAbsWtAqjCOMpxWMsY4zwRHvuKVBEOzTy2C3xE3qGy7W9J9PykCnwbZEGBftn6B8wqev69HKQsi2/90GKUU42XJ5DHa2XkrCm+ICW42Er9rG8f1MVYaW2vK4F5TUG/ahmRra1QeYMktArSJINqMDXbeydDKXPcabZtZ9tygq7o5ILVKo4BB94Wiv1kWKV4MhdUvJMauphbTR9YKDN1VTWMZ+vbKXG+GjcQe4FX4r00TdY6wwMa4/B6RzInkEdDR69kqenzGMfmLL4UO7bfNOIMwsMjViKmfY6ghyf/2ehz2Rcbr4J1/p6BrWc5PIUF/zFnIdWX7UfnrZ9hOhP+WYTqGQKBgQDa68L3klSHeH4rdHrSrUuV5Ckh+ytkdaFlRH/sUBWiWlGelPld/zWNBRPjMiP/qo6yKUgfvqgVJHg4fv7Q3mPpdTigRPLyZX2FtaXtbm4pK6R/vDVWyJ5lxCmtoAQJnUTzOrqR5WViDLp0VylnCe0D/N03xPizhsgTrOdFl/lJtQKBgQC+B/hsZP1yR5Qpc7xRAgD2nQ0gUHX21xTVP0HS7ypvU7yM6ru5oKV6fpzvejuldzCahTMNlO696Sbn1D1kDgKh/sm4j5RM4pGzPJHzcmZeKUBGZEy0nePRe9XEUmsCn5MbQXHyCzVSTHLZRcqv18ulRV3Coty6u5zTZzbcqwgt3wKBgQC4ggnRZKgPgVM9Rq+ZzmqtCx5LwIgC5E1Br2jtf1WsftjZgg9l1ImKsCPh0Umb2mDn8XkTIDpDj3gvjYEwAq9MfPpbwaDNARK41a7iHLeFxX2gFr0RxoYRmTHTXtDoVX9eTDogaql26Olg0nFXoXr8aGr97On7TPZl9sz4z6xQHQKBgQCgcBRCV8VPTbV+hdMxRZLQo2nopWXjAFX7cIu/beMS2Myw4KGbqFDtqaYP3dAr9ARaASlIRzyFpCoPwrYOGnIImRdwNfCMNSG0BOhodGaPCx7UD2xfdYpZW8bwGHF2ZomJkmiEZQggsqCoZe8pJPUvVPBDGsNm+G0AYv0Ds3aVWwKBgQC1celU0Ul91vY+EwtligFib9GApQazp5Peaaq0+QLLyrXfqiUjN9/28XlKchpmgbl1vDRvGRcp7J7RwpZ2+dcYqQBT25EORhro2aZnttTRFSEI/xC9zvFna0w+rE/FM5CdTmxRqILtRE9meDP95BXra/6xari7cviNDGx86CdBCg==";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmuP/qeBuFDalwtndUceHEAsM9rSbl54+DL8wPazehRduRmSHGSnqbpniPYKoPVTdyEi6JlnpiXyOBv5kA0FFHarVpw4g7qOr2TA7QDI+9vOnyquEA0/sljg5OZSkNI3Av4au91St/ZKw+YiiaNnXAGplfuwm7mFa2JRIU+AjXF2vI4vvwbu4IgoBv0S8GX2yKdOeNI2oF1O6ZWHIiPqESbULUDAwdqaHZLS0sCNCbF2QdPc5eeK6/T6MhMuIdOu1ZD8P6UVjAiDOk341c+s4CpvqmIkoY0TzbhobnTtvamxdqKfFylMvIzFpSOnj+rUzN/p8yJjRsvTTpS5m5yCvgQIDAQAB";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, priKey, "json", "utf-8", pubKey, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(tGoods.getSketch());
        model.setSubject(tGoods.getName());
        model.setOutTradeNo(String.valueOf(tUserOrder.getId()));
        model.setTimeoutExpress("90m");
        model.setTotalAmount(String.valueOf(tGoods.getPrice()));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("119.45.144.182/appapi/system/order/return");
        request.setReturnUrl("/pages/user/money/success");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return  response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws AlipayApiException {

        //获取订单编码
        System.out.println(getOrderIdByTime());
    }
    public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }



    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }
    /**
     *
     * */
    @PostMapping("/return")
    public Object appPay(HttpServletRequest request) throws AlipayApiException {
        //拿到账单id,更新账单支付状态，
        //2 找到userID,找到邀请人。 更新邀请人商品流水，邀请人总金额，带提现金额。
        logger.info("this is qiaoweisheng");

        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        logger.info("支付宝回调，{}", paramsJson);


        Map<String,String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();
        for(String key : keys) {
            String[] value = map.get(key);
            System.out.println(key + " = " + value[0]);
        }
        logger.info("###########################");
        logger.info(params.get("trade_status").toString());
        logger.info(params.get("out_trade_no").toString());
        logger.info("###########################");
        if (params.get("trade_status").toString().equals("TRADE_SUCCESS")){
            logger.info("支付成功");
            logger.info("账单id是"+params.get("out_trade_no").toString());

            //查找账单 更新账单
           TUserOrder tUserOrder =  tUserOrderService.selectTUserOrderById(Long.valueOf(params.get("out_trade_no").toString()));
           tUserOrderService.updateOrderStatus(tUserOrder);


            // 找到userID,找到邀请人。 更新邀请人商品流水，邀请人总金额，带提现金额。
            long userId = tUserOrder.getUserId();
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()) {
                return "success";
            }
            if (null == appUser.getInviteCode() || "".equals(appUser.getInviteCode())){
                return "success";
            }
            // 查找商品的邀请金额
            TGoods good = tGoodsService.selectTGoodsById(tUserOrder.getGoodId());
            if (null == good || null ==good.getId()){
                return "success";
            }
            //更新商品库存等
            good.setStock(good.getStock()-1);
            good.setSales(good.getSales()+1);
            tGoodsService.updateTGoods(good);
            String inviteCode = appUser.getInviteCode();
            // 根据inviteCode 更新两个表 t_user_invitegoods 商品流水 t_user_invite邀请人总金额，代提现金额
//            TUserInvite userInvite = new TUserInvite();
//            userInvite.setInviteCode(inviteCode);
            Map inviteMap  = new HashMap();
            inviteMap.put("inviteCode",inviteCode);
            TUserInvite userInvite = tUserInviteService.selectTUserInviteByInvoteCode(inviteMap);
            userInvite.setInviteMoneyNot(userInvite.getInviteMoneyNot()+good.getInvitemoney());
            userInvite.setInviteMoneyTotal(userInvite.getInviteMoneyTotal()+good.getInvitemoney());
            tUserInviteService.updateTUserInvite(userInvite);

            TUserInvitegoods tUserInvitegoods = new TUserInvitegoods();
            tUserInvitegoods.setUserId(userId);
            tUserInvitegoods.setPhone(appUser.getPhonenumber());
            tUserInvitegoods.setGoodsId(tUserOrder.getGoodId());
            tUserInvitegoods.setGoodName(good.getName());
            tUserInvitegoods.setOrderId(tUserOrder.getId());
            tUserInvitegoods.setCreateTime(new Date());
            tUserInvitegoods.setMoney(good.getInvitemoney());//佣金
            tUserInvitegoodsService.insertTUserInvitegoods(tUserInvitegoods);

        }
    return "success";
    }





}
