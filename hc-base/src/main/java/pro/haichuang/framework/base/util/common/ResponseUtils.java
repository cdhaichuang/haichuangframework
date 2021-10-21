package pro.haichuang.framework.base.util.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;

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
 * <p>该类主要用于直接对响应流的操作
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class ResponseUtils {

    /**
     * 向客户端输出Json响应信息
     *
     * @param response HttpServletResponse
     * @param data     响应数据
     * @throws IOException 获取输出流失败
     * @since 1.1.0.211021
     */
    public static void writeOfJson(@NonNull HttpServletResponse response, @NonNull Object data) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSONObject.toJSONBytes(data));
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 向客户端输出原始响应信息
     *
     * @param response HttpServletResponse
     * @param data     响应数据
     * @throws IOException 获取输出流失败
     * @since 1.1.0.211021
     */
    public static void writeOfOriginal(@NonNull HttpServletResponse response, @NonNull Object data) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(data.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 向客户端输出文件响应信息
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param file     文件
     * @throws IOException 获取输出流失败
     * @since 1.1.0.211021
     */
    public static void writeOfFile(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull File file) throws IOException {
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
