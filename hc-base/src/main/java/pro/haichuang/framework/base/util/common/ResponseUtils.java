package pro.haichuang.framework.base.util.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP响应工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class ResponseUtils {

    /**
     * 向客户端输入响应信息
     * @param response 响应对象
     * @param data 响应数据
     * @throws IOException 获取输出流失败
     */
    public static void write(HttpServletResponse response, Object data) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSONObject.toJSONBytes(data, SerializerFeature.PrettyFormat));
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 向客户端输入响应信息
     * @param response 响应对象
     * @param data 响应数据
     * @throws IOException 获取输出流失败
     */
    public static void originalWrite(HttpServletResponse response, Object data) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(data.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
