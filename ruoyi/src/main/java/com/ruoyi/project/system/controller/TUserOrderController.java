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
        String priKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCigcuXKhKn8VeTSG0lRWeNDKHCWPFcNQmlRqpLKjbb8GL01am6QqJHfN9gzYZZ2xjTWVV3ode8LS/J/2toeQtY65Z3sgTzlZXwTxZGUAs6ge+BxkzinsAmybU1lKvHC04Y2m/u93ft+wEIjZr91BFnV8mtvQVSO3GHeAoe8Qj/1qapZvo20icYrrPcuYCY2Y2lAnlRedabMfVDq4tX/++9nAkQ2Ye2n7pj9bNPhEB+EyzN0474I0IQLJVCVEFXWPuJ0rWK+488zmAotGtrC9xQdp9K8UcYt6XUr2mdQcT8w9IfiPOlQx1yHZxlHGahCBr6CpOjp1zb10WPeuhoxAWrAgMBAAECggEAbsWtAqjCOMpxWMsY4zwRHvuKVBEOzTy2C3xE3qGy7W9J9PykCnwbZEGBftn6B8wqev69HKQsi2/90GKUU42XJ5DHa2XkrCm+ICW42Er9rG8f1MVYaW2vK4F5TUG/ahmRra1QeYMktArSJINqMDXbeydDKXPcabZtZ9tygq7o5ILVKo4BB94Wiv1kWKV4MhdUvJMauphbTR9YKDN1VTWMZ+vbKXG+GjcQe4FX4r00TdY6wwMa4/B6RzInkEdDR69kqenzGMfmLL4UO7bfNOIMwsMjViKmfY6ghyf/2ehz2Rcbr4J1/p6BrWc5PIUF/zFnIdWX7UfnrZ9hOhP+WYTqGQKBgQDa68L3klSHeH4rdHrSrUuV5Ckh+ytkdaFlRH/sUBWiWlGelPld/zWNBRPjMiP/qo6yKUgfvqgVJHg4fv7Q3mPpdTigRPLyZX2FtaXtbm4pK6R/vDVWyJ5lxCmtoAQJnUTzOrqR5WViDLp0VylnCe0D/N03xPizhsgTrOdFl/lJtQKBgQC+B/hsZP1yR5Qpc7xRAgD2nQ0gUHX21xTVP0HS7ypvU7yM6ru5oKV6fpzvejuldzCahTMNlO696Sbn1D1kDgKh/sm4j5RM4pGzPJHzcmZeKUBGZEy0nePRe9XEUmsCn5MbQXHyCzVSTHLZRcqv18ulRV3Coty6u5zTZzbcqwgt3wKBgQC4ggnRZKgPgVM9Rq+ZzmqtCx5LwIgC5E1Br2jtf1WsftjZgg9l1ImKsCPh0Umb2mDn8XkTIDpDj3gvjYEwAq9MfPpbwaDNARK41a7iHLeFxX2gFr0RxoYRmTHTXtDoVX9eTDogaql26Olg0nFXoXr8aGr97On7TPZl9sz4z6xQHQKBgQCgcBRCV8VPTbV+hdMxRZLQo2nopWXjAFX7cIu/beMS2Myw4KGbqFDtqaYP3dAr9ARaASlIRzyFpCoPwrYOGnIImRdwNfCMNSG0BOhodGaPCx7UD2xfdYpZW8bwGHF2ZomJkmiEZQggsqCoZe8pJPUvVPBDGsNm+G0AYv0Ds3aVWwKBgQC1celU0Ul91vY+EwtligFib9GApQazp5Peaaq0+QLLyrXfqiUjN9/28XlKchpmgbl1vDRvGRcp7J7RwpZ2+dcYqQBT25EORhro2aZnttTRFSEI/xC9zvFna0w+rE/FM5CdTmxRqILtRE9meDP95BXra/6xari7cviNDGx86CdBCg==";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAooHLlyoSp/FXk0htJUVnjQyhwljxXDUJpUaqSyo22/Bi9NWpukKiR3zfYM2GWdsY01lVd6HXvC0vyf9raHkLWOuWd7IE85WV8E8WRlALOoHvgcZM4p7AJsm1NZSrxwtOGNpv7vd37fsBCI2a/dQRZ1fJrb0FUjtxh3gKHvEI/9amqWb6NtInGK6z3LmAmNmNpQJ5UXnWmzH1Q6uLV//vvZwJENmHtp+6Y/WzT4RAfhMszdOO+CNCECyVQlRBV1j7idK1ivuPPM5gKLRrawvcUHafSvFHGLel1K9pnUHE/MPSH4jzpUMdch2cZRxmoQga+gqTo6dc29dFj3roaMQFqwIDAQAB";
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
            return  response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws AlipayApiException {
        String appId = "2021001165672086";
        String priKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCigcuXKhKn8VeTSG0lRWeNDKHCWPFcNQmlRqpLKjbb8GL01am6QqJHfN9gzYZZ2xjTWVV3ode8LS/J/2toeQtY65Z3sgTzlZXwTxZGUAs6ge+BxkzinsAmybU1lKvHC04Y2m/u93ft+wEIjZr91BFnV8mtvQVSO3GHeAoe8Qj/1qapZvo20icYrrPcuYCY2Y2lAnlRedabMfVDq4tX/++9nAkQ2Ye2n7pj9bNPhEB+EyzN0474I0IQLJVCVEFXWPuJ0rWK+488zmAotGtrC9xQdp9K8UcYt6XUr2mdQcT8w9IfiPOlQx1yHZxlHGahCBr6CpOjp1zb10WPeuhoxAWrAgMBAAECggEAbsWtAqjCOMpxWMsY4zwRHvuKVBEOzTy2C3xE3qGy7W9J9PykCnwbZEGBftn6B8wqev69HKQsi2/90GKUU42XJ5DHa2XkrCm+ICW42Er9rG8f1MVYaW2vK4F5TUG/ahmRra1QeYMktArSJINqMDXbeydDKXPcabZtZ9tygq7o5ILVKo4BB94Wiv1kWKV4MhdUvJMauphbTR9YKDN1VTWMZ+vbKXG+GjcQe4FX4r00TdY6wwMa4/B6RzInkEdDR69kqenzGMfmLL4UO7bfNOIMwsMjViKmfY6ghyf/2ehz2Rcbr4J1/p6BrWc5PIUF/zFnIdWX7UfnrZ9hOhP+WYTqGQKBgQDa68L3klSHeH4rdHrSrUuV5Ckh+ytkdaFlRH/sUBWiWlGelPld/zWNBRPjMiP/qo6yKUgfvqgVJHg4fv7Q3mPpdTigRPLyZX2FtaXtbm4pK6R/vDVWyJ5lxCmtoAQJnUTzOrqR5WViDLp0VylnCe0D/N03xPizhsgTrOdFl/lJtQKBgQC+B/hsZP1yR5Qpc7xRAgD2nQ0gUHX21xTVP0HS7ypvU7yM6ru5oKV6fpzvejuldzCahTMNlO696Sbn1D1kDgKh/sm4j5RM4pGzPJHzcmZeKUBGZEy0nePRe9XEUmsCn5MbQXHyCzVSTHLZRcqv18ulRV3Coty6u5zTZzbcqwgt3wKBgQC4ggnRZKgPgVM9Rq+ZzmqtCx5LwIgC5E1Br2jtf1WsftjZgg9l1ImKsCPh0Umb2mDn8XkTIDpDj3gvjYEwAq9MfPpbwaDNARK41a7iHLeFxX2gFr0RxoYRmTHTXtDoVX9eTDogaql26Olg0nFXoXr8aGr97On7TPZl9sz4z6xQHQKBgQCgcBRCV8VPTbV+hdMxRZLQo2nopWXjAFX7cIu/beMS2Myw4KGbqFDtqaYP3dAr9ARaASlIRzyFpCoPwrYOGnIImRdwNfCMNSG0BOhodGaPCx7UD2xfdYpZW8bwGHF2ZomJkmiEZQggsqCoZe8pJPUvVPBDGsNm+G0AYv0Ds3aVWwKBgQC1celU0Ul91vY+EwtligFib9GApQazp5Peaaq0+QLLyrXfqiUjN9/28XlKchpmgbl1vDRvGRcp7J7RwpZ2+dcYqQBT25EORhro2aZnttTRFSEI/xC9zvFna0w+rE/FM5CdTmxRqILtRE9meDP95BXra/6xari7cviNDGx86CdBCg==";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAooHLlyoSp/FXk0htJUVnjQyhwljxXDUJpUaqSyo22/Bi9NWpukKiR3zfYM2GWdsY01lVd6HXvC0vyf9raHkLWOuWd7IE85WV8E8WRlALOoHvgcZM4p7AJsm1NZSrxwtOGNpv7vd37fsBCI2a/dQRZ1fJrb0FUjtxh3gKHvEI/9amqWb6NtInGK6z3LmAmNmNpQJ5UXnWmzH1Q6uLV//vvZwJENmHtp+6Y/WzT4RAfhMszdOO+CNCECyVQlRBV1j7idK1ivuPPM5gKLRrawvcUHafSvFHGLel1K9pnUHE/MPSH4jzpUMdch2cZRxmoQga+gqTo6dc29dFj3roaMQFqwIDAQAB";
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
