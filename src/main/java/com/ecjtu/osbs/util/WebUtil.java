package com.ecjtu.osbs.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web工具类
 *
 * @author CaoLongHui
 * @since 2024/3/10 01:03
 */
public class WebUtil {

    public static void renderString(HttpServletResponse response, String data) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(data);
    }
}
