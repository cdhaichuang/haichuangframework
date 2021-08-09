import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * InitProject
 * 运行命令: java Init (项目Code) (运行端口)
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class Init {

    /**
     * 原始项目包名
     */
    private static final String PROJECT_ORIGIN_PACKAGE_NAME = "pro.haichuang.framework.service.main";

    /**
     * 项目服务模块名称
     */
    private static final String PROJECT_SERVICE_MODEL_NAME = "hc-service-main";

    /**
     * 系统换行符
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("参数配置错误, 正确运行为 [java Init 项目Code 运行端口]");
        }
        try {
            // 项目原始Code
            String originCodeName = args[0];
            // 运行端口
            String port = args[1];
            if (originCodeName == null || originCodeName.isEmpty()) {
                throw new RuntimeException("项目Code未配置, 执行命令为 [java Init 项目Code 运行端口]");
            }
            if (port == null || port.isEmpty()) {
                throw new RuntimeException("项目运行端口未配置, 执行命令为 [java Init 项目Code 运行端口]");
            }
            for (int i = 0; i < port.length(); i++) {
                if (!Character.isDigit(port.charAt(i))) {
                    throw new RuntimeException("项目运行端口配置错误");
                }
            }
            // 服务模块对象
            File serviceModelDirFile = new File(PROJECT_SERVICE_MODEL_NAME);
            if (!serviceModelDirFile.exists()) {
                throw new RuntimeException(String.format("[%s] 模块不存在", PROJECT_SERVICE_MODEL_NAME));
            }
            if (!serviceModelDirFile.isDirectory()) {
                throw new RuntimeException(String.format("[%s] 必须为目录", PROJECT_SERVICE_MODEL_NAME));
            }
            try {
                // 服务模块 [src/main] 目录
                File mainDirFile = getChildDir(serviceModelDirFile, "src.main");
                // 服务模块 [src/main/java] 目录
                File javaDirFile = getChildDir(mainDirFile, "java");
                // 服务模块核心包路径
                File projectDirFile = getChildDir(javaDirFile, PROJECT_ORIGIN_PACKAGE_NAME);

                // 服务模块 [src/test] 目录
                File testDirFile = getChildDir(serviceModelDirFile, "src.test");
                // 服务模块 [src/test/java] 目录
                File javaTestDirFile = getChildDir(testDirFile, "java");
                // 服务模块测试包路径
                File projectTestDirFile = getChildDir(javaTestDirFile, PROJECT_ORIGIN_PACKAGE_NAME);

                // 服务模块资源路径
                File resourcesDirFile = getChildDir(mainDirFile, "resources");

                // 服务模块 [pom.xml] 文件
                File pomFile = new File(PROJECT_SERVICE_MODEL_NAME, "pom.xml");
                if (!pomFile.exists()) {
                    throw new RuntimeException("[pom.xml] 不存在");
                }
                if (!pomFile.isFile()) {
                    throw new RuntimeException("[pom.xml] 必须为文件");
                }

                // 重命名项目核心文件信息
                replaceCodeAndFlushWrite(projectDirFile.isDirectory(), projectDirFile, originCodeName, port);
                // 重命名项目测试文件信息
                replaceCodeAndFlushWrite(projectTestDirFile.isDirectory(), projectTestDirFile, originCodeName, port);
                // 重命名项目包名
                String tempProjectDirNamePath = projectDirFile.getAbsolutePath().replaceAll("\\\\", "/");
                String tempProjectOriginPackageDirPath = PROJECT_ORIGIN_PACKAGE_NAME.replaceAll("\\.", "/");
                String tempProjectNewPackageDirPath = getNewPackage(originCodeName).replaceAll("\\.", "/");
                boolean renameProjectDirResult = projectDirFile.renameTo(new File(tempProjectDirNamePath.replace(tempProjectOriginPackageDirPath, tempProjectNewPackageDirPath)));
                if (!renameProjectDirResult) {
                    throw new RuntimeException("重命名项目包名失败");
                }
                // 重命名资源文件信息
                replaceCodeAndFlushWrite(resourcesDirFile.isDirectory() || (resourcesDirFile.isFile() && resourcesDirFile.getName().startsWith("application")),
                        resourcesDirFile, originCodeName, port);
                // 更改 [pom.xml] 文件 [Jar] 名称
                replaceCodeAndFlushWrite(false, pomFile, originCodeName, port);
            } catch (Exception e) {
                System.out.println("运行异常, 请联系管理员");
            }
        } catch (Exception e) {
            System.out.println("运行异常, 请联系管理员");
        }
    }

    /**
     * 获取子目录
     *
     * @param parentFile 父目录File对象
     * @param dirName    子目录名称
     * @return 子目录
     */
    private static File getChildDir(File parentFile, String dirName) {
        File childDirFile = null;
        try {
            String[] dirNames = dirName.contains(".") ? dirName.split("\\.") : new String[]{dirName};
            for (String tempDirName : dirNames) {
                File[] tempDirFiles = parentFile.listFiles((dir, name) -> tempDirName.equals(name));
                if (tempDirFiles != null) {
                    for (File file : tempDirFiles) {
                        if (file.isDirectory()) {
                            childDirFile = file;
                        }
                    }
                }
                parentFile = childDirFile;
            }
        } catch (Exception e) {
            throw new RuntimeException("获取子目录失败, 请联系管理员");
        }
        return childDirFile;
    }

    /**
     * 替换代号并重新写入
     *
     * @param isRecursion    是否需要递归
     * @param file           文件
     * @param originCodeName 项目原始Code
     * @param port           项目端口
     */
    private static void replaceCodeAndFlushWrite(boolean isRecursion, File file, String originCodeName, String port) {
        if (isRecursion && file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            if (childrenFiles != null) {
                for (File childrenFile : childrenFiles) {
                    replaceCodeAndFlushWrite(true, childrenFile, originCodeName, port);
                }
            }
        } else if (file.isFile()) {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
            ) {
                StringBuffer buffer = new StringBuffer();
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    if (line.contains(PROJECT_ORIGIN_PACKAGE_NAME)) {
                        line = line.replaceAll(PROJECT_ORIGIN_PACKAGE_NAME, getNewPackage(originCodeName));
                    }
                    if (line.contains("${hc.server.port}")) {
                        line = line.replaceAll("\\$\\{hc.server.port}", port);
                    }
                    if (line.contains("${hc.origin-code-name}")) {
                        line = line.replaceAll("\\$\\{hc.origin-code-name}", originCodeName);
                    }
                    if (line.contains("${hc.origin-lowercase-code-name}")) {
                        line = line.replaceAll("\\$\\{hc.origin-lowercase-code-name}", originCodeName.toLowerCase());
                    }
                    if (line.contains("${hc.project-code-name}")) {
                        line = line.replaceAll("\\$\\{hc.project-code-name}", getProjectCode(originCodeName));
                    }
                    if (line.contains("defaultJarFileName")) {
                        line = line.replaceAll("defaultJarFileName", originCodeName);
                    }
                    buffer.append(line.concat(LINE_SEPARATOR));
                }
                try (
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), true)
                ) {
                    writer.print(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取项目Code(首字母全小写+数字)
     *
     * @param originCodeName 项目原始Code
     * @return 项目Code
     */
    private static String getProjectCode(String originCodeName) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < originCodeName.length(); i++) {
            char tempChar = originCodeName.charAt(i);
            if (Character.isUpperCase(tempChar) || Character.isDigit(tempChar)) {
                result.append(tempChar);
            }
        }
        return result.toString().toLowerCase();
    }

    /**
     * 获取新报名
     *
     * @param originCodeName 项目原始Code
     * @return 新包名
     */
    private static String getNewPackage(String originCodeName) {
        return String.join(".", Arrays.copyOf(PROJECT_ORIGIN_PACKAGE_NAME.split("\\."), PROJECT_ORIGIN_PACKAGE_NAME.split("\\.").length - 1)).concat(".").concat(getProjectCode(originCodeName));
    }
}
