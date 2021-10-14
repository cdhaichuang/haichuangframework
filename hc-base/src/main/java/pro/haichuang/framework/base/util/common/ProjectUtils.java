package pro.haichuang.framework.base.util.common;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * 项目工具类
 *
 * <p>该类封装了项目中常用的方法
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public class ProjectUtils {

    /**
     * 运行时获取项目中Resource目录
     *
     * @return 项目中Resource目录
     * @throws FileNotFoundException 文件未找到
     * @since 1.0.0.211009
     */
    public static String getResourcePath() throws FileNotFoundException {
        return getResourcePath("");
    }

    /**
     * 运行时获取项目中Resource目录
     *
     * @param filePath 相对于 {@code resource} 目录下的路径
     * @return 项目中Resource目录
     * @throws FileNotFoundException 文件未找到
     * @since 1.0.0.211009
     */
    public static String getResourcePath(String filePath) throws FileNotFoundException {
        return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + filePath).getPath();
    }
}
