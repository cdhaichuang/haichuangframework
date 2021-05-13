package pro.haichuang.framework.base.util.common;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * 项目工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class ProjectUtils {

    public static String getResourcePath() throws FileNotFoundException {
        return getResourcePath("");
    }

    public static String getResourcePath(String filePath) throws FileNotFoundException {
        return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + filePath).getPath();
    }
}
