package com.ecjtu.osbs.web.controller;

import com.alipay.api.AlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 支付控制层
 *
 * @author CaoLongHui
 * @since 2024/4/25 23:56
 */
@RestController
@RequestMapping("alipay")
public class AliPayController {

    @Autowired
    private AlipayClient alipayClient;

    @GetMapping("/pay")
    public void pay(HttpServletResponse httpResponse) throws Exception {
    }

}
