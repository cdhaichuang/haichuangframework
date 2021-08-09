package pro.haichuang.framework.mybatis.generate.config;

import org.springframework.stereotype.Component;

/**
 * MyBatisPlus包配置
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService
 * @since 1.0.0
 */
@Component
public class CodePackageConfig {

    /**
     * 输出包名
     */
    private String outputPackage = "pro.haichuang.framework.service";

    /**
     * 父包模块名
     */
    private String parentModelName;

    /**
     * 实体类包名
     */
    private String entityPackageName = "pojo.domain";

    /**
     * 数据访问层包名
     */
    private String mapperPackageName = "mapper";

    /**
     * 数据访问层XML包名
     */
    private String mapperXmlPackageName = "mapper.xml";

    /**
     * 业务逻辑层包名
     */
    private String servicePackageName = "service";

    /**
     * 业务逻辑实现层包名
     */
    private String serviceImplPackageName = "service.impl";

    /**
     * 界面层包名
     */
    private String controllerPackageName = "controller";

    /**
     * 输出根目录绝对路径, 默认当前项目
     */
    private String outputDir = System.getProperty("user.dir").concat("/src/main/java/");

    public String getOutputPackage() {
        return outputPackage;
    }

    public void setOutputPackage(String outputPackage) {
        this.outputPackage = outputPackage;
    }

    public String getParentModelName() {
        return parentModelName;
    }

    public void setParentModelName(String parentModelName) {
        this.parentModelName = parentModelName;
    }

    public String getEntityPackageName() {
        return entityPackageName;
    }

    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public String getMapperXmlPackageName() {
        return mapperXmlPackageName;
    }

    public void setMapperXmlPackageName(String mapperXmlPackageName) {
        this.mapperXmlPackageName = mapperXmlPackageName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getServiceImplPackageName() {
        return serviceImplPackageName;
    }

    public void setServiceImplPackageName(String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
}
