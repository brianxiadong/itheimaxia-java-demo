package com.brianxia.alipaydemo.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.brianxia.alipaydemo.config.AlipayConfig.*;

@Configuration
@Data
public class PayUrlConfig {

    /**
     * 支付成功页面跳转
     */
    @Value("${alipay.success_return_url}")
    private String alipaySuccessReturnUrl;


    /**
     * 支付成功，回调通知
     */
    @Value("${alipay.callback_url}")
    private String alipayCallbackUrl;


    @Bean
    public AlipayClient defaultAlipayClient(){
        return  new DefaultAlipayClient(PAY_GATEWAY,APPID,APP_PRI_KEY,FORMAT,CHARSET,ALIPAY_PUB_KEY,SIGN_TYPE);

    }
}
