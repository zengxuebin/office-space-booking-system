package com.ecjtu.osbs.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝沙箱配置类
 *
 * @author CaoLongHui
 * @since 2024/3/25 22:46
 */
@Configuration
public class AlipayClientConfig {

    /**
     * 创建支付宝客户端
     *
     * @param alipayConfig 支付宝配置
     * @return 支付宝客户端
     */
    @Bean
    public AlipayClient alipayClient(AlipayConfig alipayConfig) {
        return new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()
        );
    }
}
