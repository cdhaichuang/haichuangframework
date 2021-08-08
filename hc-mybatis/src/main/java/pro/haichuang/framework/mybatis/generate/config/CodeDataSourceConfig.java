package pro.haichuang.framework.mybatis.generate.config;

import org.springframework.stereotype.Component;

/**
 * MyBatisPlus数据源配置文件
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService
 */
@Component
public class CodeDataSourceConfig {

    /**
     * 驱动名
     */
    private String driver = "com.mysql.cj.jdbc.Driver";

    /**
     * 驱动连接的URL
     */
    private String url;

    /**
     * 数据库连接用户名
     */
    private String username;

    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * 统一表前缀
     */
    private String tablePrefix;

    /**
     * 输出包含表
     */
    private String[] include;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String... include) {
        this.include = include;
    }
}
