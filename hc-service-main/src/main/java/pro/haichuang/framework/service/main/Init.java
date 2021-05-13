package pro.haichuang.framework.service.main;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 初始化项目配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class Init {

    public static void main(String[] args) {
        try {
            assert args.length > 0;
            String codeName = args[0];
            if (codeName != null && !codeName.isEmpty()) {
                File serviceModelDirFile = new File("hc-service-main");
                if (serviceModelDirFile.exists()) {
                    if (serviceModelDirFile.isDirectory()) {
                        try {
                            File mainDirFile = getChildDir(serviceModelDirFile, "src.main");
                            File javaDirFile = getChildDir(mainDirFile, "java");
                            File resourcesDirFile = getChildDir(mainDirFile, "resources");

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
        }catch (AssertionError e) {
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
        String[] dirNames = dirName.contains(".") ? dirName.split("\\.") : new String[] {dirName};
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

    private static void renameFilePackage(File file) {
        File[] childrenFiles = file.listFiles();
        assert childrenFiles != null;
        for (File childrenFile : childrenFiles) {
            if (childrenFile.isDirectory()) {

            }else if (childrenFile.isFile()) {

            }
        }
    }
}
