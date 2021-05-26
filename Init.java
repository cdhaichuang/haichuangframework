package pro.haichuang.framework.service.main;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Arrays;

/**
 * 初始化项目配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class Init {

    private static final String PROJECT_DIR_NAME = "pro.haichuang.framework.service.main";
    private static final String REPLACE_PROJECT_DIR_NAME = String.join(".", Arrays.copyOf(PROJECT_DIR_NAME.split("\\."), PROJECT_DIR_NAME.split("\\.").length - 1));
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
        try {
            assert args.length > 0;
            String codeName = args[0];
            String port = args[1];
            if (codeName != null && !codeName.isEmpty()) {
                File serviceModelDirFile = new File("hc-service-main");
                if (serviceModelDirFile.exists()) {
                    if (serviceModelDirFile.isDirectory()) {
                        try {
                            File mainDirFile = getChildDir(serviceModelDirFile, "src.main");
                            File javaDirFile = getChildDir(mainDirFile, "java");
                            File projectDirFile = getChildDir(javaDirFile, PROJECT_DIR_NAME);
                            File resourcesDirFile = getChildDir(mainDirFile, "resources");

                            renameFilePackage(projectDirFile, codeName);
                            renameFileResourceInfo(resourcesDirFile, codeName, port);
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("[hc-service-main] 必须为模块名");
                    }
                } else {
                    System.out.println("[hc-service-main] 模块不存在");
                }
            } else {
                System.out.println("项目代号未配置, 执行命令为 [java Init 项目代号]");
            }
        } catch (AssertionError e) {
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
        assert dirName != null && !dirName.isEmpty();
        String[] dirNames = dirName.contains(".") ? dirName.split("\\.") : new String[]{dirName};
        File childDirFile = null;
        for (String tempDirName : dirNames) {
            assert parentFile != null;
            File[] tempDirFiles = parentFile.listFiles((dir, name) -> tempDirName.equals(name));
            assert tempDirFiles != null;
            for (File file : tempDirFiles) {
                if (file.isDirectory()) {
                    childDirFile = file;
                }
            }
            parentFile = childDirFile;
        }
        assert childDirFile != null;
        return childDirFile;
    }

    /**
     * 递归更改包名
     *
     * @param file     File文件
     * @param codeName 项目代号
     */
    private static void renameFilePackage(File file, String codeName) {
        if (file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            assert childrenFiles != null;
            for (File childrenFile : childrenFiles) {
                renameFilePackage(childrenFile, codeName);
            }
        } else if (file.isFile()) {
            try (
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    PrintWriter writer = new PrintWriter(new FileWriter(file), true);
            ) {
                StringBuffer buffer = new StringBuffer();
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    if (line.contains(PROJECT_DIR_NAME)) {
                        line = line.replaceAll(PROJECT_DIR_NAME, REPLACE_PROJECT_DIR_NAME.concat(codeName));
                    }
                    buffer.append(line.contains(LINE_SEPARATOR));
                }
                writer.println(buffer);
            } catch (IOException e) {
                System.out.println("运行异常, 请联系管理员");
            }
        }
    }

    /**
     * 递归更改配置文件
     *
     * @param file     File文件
     * @param codeName 项目代号
     * @param port     项目端口
     */
    private static void renameFileResourceInfo(File file, String codeName, int port) {
        if (file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            assert childrenFiles != null;
            for (File childrenFile : childrenFiles) {
                renameFileResourceInfo(childrenFile, codeName, port);
            }
        } else if (file.isFile() && file.getName().contains("application")) {
            try (
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    PrintWriter writer = new PrintWriter(new FileWriter(file), true);
            ) {
                StringBuffer buffer = new StringBuffer();
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    if (line.contains("${hc.server.port}")) {
                        line = line.replaceAll("\\$\\{hc.server.port}", String.valueOf(port));
                    } else if (line.contains("${hc.code-name}")) {
                        line = line.replaceAll("\\$\\{hc.code-name}", codeName);
                    }
                    buffer.append(line.contains(LINE_SEPARATOR));
                }
                writer.println(buffer);
            } catch (IOException e) {
                System.out.println("运行异常, 请联系管理员");
            }
        }
    }
}
