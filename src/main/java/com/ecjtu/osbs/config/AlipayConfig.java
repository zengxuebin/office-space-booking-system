package com.ecjtu.osbs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取yml中的alipay配置信息
 *
 * @author CaoLongHui
 * @since 2024/3/25 22:43
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    /**
     * Alipay 分配给开发者的应用ID
     */
    private String appId;

    /**
     * 支付宝公钥（支付宝生成，用于验签）
     */
    private String alipayPublicKey;

    /**
     * 应用私钥（开发者生成，用于签名）
     */
    private String appPrivateKey;

    /**
     * 签名方式
     */
    private String signType = "RSA2";

    /**
     * 字符编码格式
     */
    private String charset = "UTF-8";

    /**
     * 返回格式
     */
    private String format = "JSON";

    /**
     * 支付宝网关
     */
    private String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

}
