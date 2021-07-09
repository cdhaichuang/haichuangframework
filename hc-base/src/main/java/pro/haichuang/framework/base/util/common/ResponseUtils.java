package pro.haichuang.framework.base.util.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP响应工具类
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class ResponseUtils {

    /**
     * 向客户端输入响应信息
     *
     * @param response HttpServletResponse
     * @param data     响应数据
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
     *
     * @param response HttpServletResponse
     * @param data     响应数据
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

    /**
     * 向客户端输入文件响应信息
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param file     文件
     * @throws IOException 获取输出流失败
     */
    public static void fileWrite(HttpServletRequest request, HttpServletResponse response, File file) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        MediaType mediaType;
        String fileBaseName = FilenameUtils.getName(file.getAbsolutePath());

        ServletContext servletContext = request.getServletContext();
        String mineType = servletContext.getMimeType(fileBaseName);
        try {
            mediaType = MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        response.setContentType(mediaType.getType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=".concat(fileBaseName));
        response.setContentLengthLong(file.length());

        FileUtils.copyFile(file, response.getOutputStream());
    }
}
