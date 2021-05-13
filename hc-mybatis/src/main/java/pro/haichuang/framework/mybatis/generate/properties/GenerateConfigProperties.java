package pro.haichuang.framework.mybatis.generate.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;
import pro.haichuang.framework.mybatis.generate.properties.info.BasicConfigProperties;
import pro.haichuang.framework.mybatis.generate.properties.info.DataSourceConfigProperties;
import pro.haichuang.framework.mybatis.generate.properties.info.PackageConfigProperties;

/**
 * Mybatis代码生成器配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "haichuang.mybatis.generate")
public class GenerateConfigProperties {

    /**
     * 是否启用代码生成器配置
     */
    private Boolean enable;

    /**
     * MyBatisPlus基本配置文件
     */
    @NestedConfigurationProperty
    private BasicConfigProperties basicConfig = new BasicConfigProperties();

    /**
     * MyBatisPlus数据源配置文件
     */
    @NestedConfigurationProperty
    private DataSourceConfigProperties datasourceConfig = new DataSourceConfigProperties();

    /**
     * MyBatisPlus包配置文件
     */
    @NestedConfigurationProperty
    private PackageConfigProperties packageConfig = new PackageConfigProperties();

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public BasicConfigProperties getBasicConfig() {
        return basicConfig;
    }

    public void setBasicConfig(BasicConfigProperties basicConfig) {
        this.basicConfig = basicConfig;
    }

    public DataSourceConfigProperties getDatasourceConfig() {
        return datasourceConfig;
    }

    public void setDatasourceConfig(DataSourceConfigProperties datasourceConfig) {
        this.datasourceConfig = datasourceConfig;
    }

    public PackageConfigProperties getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PackageConfigProperties packageConfig) {
        this.packageConfig = packageConfig;
    }
}
