package com.ruoyi.project.system.controller;

import java.util.List;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
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
import com.ruoyi.project.system.domain.TUserOrder;
import com.ruoyi.project.system.service.ITUserOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
    public Object appPay() throws AlipayApiException {
        String appId = "2021001165672086";
        String priKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDuulzJs60B3Hlk8bgTTctTVz1TE9jPxFystfXvHiAkEeJVvXTNvUANsFhUBcraRrcs6BlKY7aO1MVmHjgh79qTDnIobL5LXgzAe9jO1fjudYp3zeW5Okcufg5hGX12m2/9jrd0Ztt53DSbvyvE+oSb84ajFtdFmfkM4Pz1StlMrD1qhyhIcP1oXlRVtlK6mWlxIZ+Y7yEOZjOSloJLGMMQrcqgeYxNtprxtDKvilQLxS6fL/4+aJweEI0cJJlProuUwD6IpEEsj8+m88GbZZFeZ+05SIWt3DlpUVahF3hoea9EhJI1Yp2cFPzQU+RSwgGoVAACZtGbyq2Em0L6zoTlAgMBAAECggEAHAxhYoFtzk4tJSMRUZNOVFhnqB8o1iBoExK99n4/oH1JbnF4C8Q0hmc1uYmEr+inFOugLRFSHa36E2lxhus7bxO/beh44zoFnYfvjT2upbXBsxnJ591lB51f6yCnkQvqOIxQGakMGGE6/SGU7uvQMNKH+xlv6YmRrWWx0tAcf9lytYBiabZVJPBP3/Bh+bbLZI+T+llw2UDiw6a6qvlsDyV36zEWdpxjAZaYvyl68oTsDn/jA7aE/3MLI+WtgT9ytxKkUAGvhu8Y1+7od9f1IequR4HbHIkIJLerBIHZbn0drcqRBTSmVbmNzg1ITmhNnF/osi0o07nea23Adj9e8QKBgQD5+AO36x3f9Mn6x/jQ96sP+1dkzOkNKyRwW9g5jewnKXPN0EutjdffosUDeSFKl7EFyl0StGKjI54sMHwe6EGpDdAcIP5oEtZbgg2lkCfocdd8ZpDszk0X2obm5qzD+qlZ/+oEEkglLdXllPr4PWpUgraGQR+uF1G60K434uyrrwKBgQD0fOqjwsTbYZPsfvhbQiyM8pDk9M23xWj+PgIHyN/GTqO+SZAXrY5aj6VMi1ChUxow9ni0XOPFcmZS2/A+mHdCU7FijK9OPc9FXW02BJPNkPE/rZv7Vk/OhAZsIbgJ7amg10e+a0KDyeDRj0rhUlz+x0bdjxSA+lehRYqA63hZqwKBgF7leQPpjn3nQIakRCGQ5LA8iwEJibAmIL9HHt86sdxsMDqCSTiqELol4QVghvL6g4nfOVZQhOHKM9Iy1ewl4Ed9pvQG0o5QGHDE/slL4Nqt3VCs33gHGd1kaLJgAFamYoR4Uy8Ygasbq4hs9ponNqa+uOxe7Nf6olx5vKoazB6hAoGAGJDInsW+M/DdBSFI4RqIEW16A5dZaEKspwch+xhC69ERANixYr+skwrl6AgDhQItmofZXv4KYNd60o6I2DFHomF4bFeIccsiHW0iwQ+6rigHJYfTKXDlufFoF8tXi5/WsHORIN/0IfvR3BySwIsfDvaE91QeEkuVLH4pSYTfZdsCgYAjN2kdJEIfIy5bM1ObB6Hk9CN7e5pACDMDYP6SUmL+c4Z7zQdpJD5BHC9ukrehUegG66eDNp9v46UbhVw32E3uulbJIfYVHKYSRlHaagD6pCFDJCOfs7qSmZ0lcTAuHBTthwNbYrFs8rhkHxi5vPHaOPYImr/Df5WVnJwZcXVLAg==";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7rpcybOtAdx5ZPG4E03LU1c9UxPYz8RcrLX17x4gJBHiVb10zb1ADbBYVAXK2ka3LOgZSmO2jtTFZh44Ie/akw5yKGy+S14MwHvYztX47nWKd83luTpHLn4OYRl9dptv/Y63dGbbedw0m78rxPqEm/OGoxbXRZn5DOD89UrZTKw9aocoSHD9aF5UVbZSuplpcSGfmO8hDmYzkpaCSxjDEK3KoHmMTbaa8bQyr4pUC8Uuny/+PmicHhCNHCSZT66LlMA+iKRBLI/PpvPBm2WRXmftOUiFrdw5aVFWoRd4aHmvRISSNWKdnBT80FPkUsIBqFQAAmbRm8qthJtC+s6E5QIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,priKey,"json","GBK",pubKey,"RSA2");
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizContent("\"{\"total_amount\":\"0.01\",\"subject\":\"艺考服务视频剪辑\",\"out_trade_no\":\"1591697088230\",\"timeout_express\":\"\",\"seller_id\":\"\",\"product_code\":\"\",\"body\":\"\",\"time_expire\":\"\",\"goods_type\":\"\",\"promo_params\":\"\",\"passback_params\":\"\",\"royalty_info\":\"\",\"extend_params\":\"\",\"sub_merchant\":\"\",\"merchant_order_no\":\"\",\"enable_pay_channels\":\"\",\"store_id\":\"\",\"specified_channel\":\"\",\"disable_pay_channels\":\"\",\"goods_detail\":\"\",\"settle_info\":\"\",\"invoice_info\":\"\",\"ext_user_info\":\"\",\"business_params\":\"\",\"agreement_sign_params\":\"\"}\"");
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return "";
    }

    public static void main(String[] args) throws AlipayApiException {
        String appId = "2021001165672086";
        String priKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDuulzJs60B3Hlk8bgTTctTVz1TE9jPxFystfXvHiAkEeJVvXTNvUANsFhUBcraRrcs6BlKY7aO1MVmHjgh79qTDnIobL5LXgzAe9jO1fjudYp3zeW5Okcufg5hGX12m2/9jrd0Ztt53DSbvyvE+oSb84ajFtdFmfkM4Pz1StlMrD1qhyhIcP1oXlRVtlK6mWlxIZ+Y7yEOZjOSloJLGMMQrcqgeYxNtprxtDKvilQLxS6fL/4+aJweEI0cJJlProuUwD6IpEEsj8+m88GbZZFeZ+05SIWt3DlpUVahF3hoea9EhJI1Yp2cFPzQU+RSwgGoVAACZtGbyq2Em0L6zoTlAgMBAAECggEAHAxhYoFtzk4tJSMRUZNOVFhnqB8o1iBoExK99n4/oH1JbnF4C8Q0hmc1uYmEr+inFOugLRFSHa36E2lxhus7bxO/beh44zoFnYfvjT2upbXBsxnJ591lB51f6yCnkQvqOIxQGakMGGE6/SGU7uvQMNKH+xlv6YmRrWWx0tAcf9lytYBiabZVJPBP3/Bh+bbLZI+T+llw2UDiw6a6qvlsDyV36zEWdpxjAZaYvyl68oTsDn/jA7aE/3MLI+WtgT9ytxKkUAGvhu8Y1+7od9f1IequR4HbHIkIJLerBIHZbn0drcqRBTSmVbmNzg1ITmhNnF/osi0o07nea23Adj9e8QKBgQD5+AO36x3f9Mn6x/jQ96sP+1dkzOkNKyRwW9g5jewnKXPN0EutjdffosUDeSFKl7EFyl0StGKjI54sMHwe6EGpDdAcIP5oEtZbgg2lkCfocdd8ZpDszk0X2obm5qzD+qlZ/+oEEkglLdXllPr4PWpUgraGQR+uF1G60K434uyrrwKBgQD0fOqjwsTbYZPsfvhbQiyM8pDk9M23xWj+PgIHyN/GTqO+SZAXrY5aj6VMi1ChUxow9ni0XOPFcmZS2/A+mHdCU7FijK9OPc9FXW02BJPNkPE/rZv7Vk/OhAZsIbgJ7amg10e+a0KDyeDRj0rhUlz+x0bdjxSA+lehRYqA63hZqwKBgF7leQPpjn3nQIakRCGQ5LA8iwEJibAmIL9HHt86sdxsMDqCSTiqELol4QVghvL6g4nfOVZQhOHKM9Iy1ewl4Ed9pvQG0o5QGHDE/slL4Nqt3VCs33gHGd1kaLJgAFamYoR4Uy8Ygasbq4hs9ponNqa+uOxe7Nf6olx5vKoazB6hAoGAGJDInsW+M/DdBSFI4RqIEW16A5dZaEKspwch+xhC69ERANixYr+skwrl6AgDhQItmofZXv4KYNd60o6I2DFHomF4bFeIccsiHW0iwQ+6rigHJYfTKXDlufFoF8tXi5/WsHORIN/0IfvR3BySwIsfDvaE91QeEkuVLH4pSYTfZdsCgYAjN2kdJEIfIy5bM1ObB6Hk9CN7e5pACDMDYP6SUmL+c4Z7zQdpJD5BHC9ukrehUegG66eDNp9v46UbhVw32E3uulbJIfYVHKYSRlHaagD6pCFDJCOfs7qSmZ0lcTAuHBTthwNbYrFs8rhkHxi5vPHaOPYImr/Df5WVnJwZcXVLAg==";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7rpcybOtAdx5ZPG4E03LU1c9UxPYz8RcrLX17x4gJBHiVb10zb1ADbBYVAXK2ka3LOgZSmO2jtTFZh44Ie/akw5yKGy+S14MwHvYztX47nWKd83luTpHLn4OYRl9dptv/Y63dGbbedw0m78rxPqEm/OGoxbXRZn5DOD89UrZTKw9aocoSHD9aF5UVbZSuplpcSGfmO8hDmYzkpaCSxjDEK3KoHmMTbaa8bQyr4pUC8Uuny/+PmicHhCNHCSZT66LlMA+iKRBLI/PpvPBm2WRXmftOUiFrdw5aVFWoRd4aHmvRISSNWKdnBT80FPkUsIBqFQAAmbRm8qthJtC+s6E5QIDAQAB";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, priKey, "json", "utf-8", pubKey, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("asdasdasd");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
