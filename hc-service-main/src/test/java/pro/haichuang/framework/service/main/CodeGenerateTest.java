package pro.haichuang.framework.service.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService;
import pro.haichuang.framework.mybatis.generate.config.CodeBasicConfig;
import pro.haichuang.framework.mybatis.generate.config.CodeDataSourceConfig;
import pro.haichuang.framework.mybatis.generate.config.CodePackageConfig;

/**
 * 执行代码生成器, 提供简单生成与完整生成示例
 * 该类下所有静态常量均可根据实际情况进行修改, 其他配置信息不建议修改
 * Danger: 请谨慎执行, 每次执行会自动覆盖已有代码, 请务必提前做好备份
 * ps: 如只需要生成部分表请修改 {@link CodeGenerateTest#DATASOURCE_TABLE_INCLUDE} 字段
 *
 * @author JiYinchuan
 * @version 1.0.0.211020
 */
@SpringBootTest(classes = ServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodeGenerateTest {

    /**
     * 项目Code
     * 项目代号缩写(项目代号首字母全小写+数字)
     */
    private static final String PROJECT_CODE = "${hc.project-code-name}";

    /**
     * 作者
     */
    private static final String AUTHOR = "JiYinchuan";

    /**
     * 驱动连接的URL
     */
    private static final String DATASOURCE_URL = "jdbc:mysql://127.0.0.1:3306/data_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowMutiQueries=true";

    /**
     * 数据库连接用户名
     */
    private static final String DATASOURCE_USERNAME = "root";

    /**
     * 数据库连接密码
     */
    private static final String DATASOURCE_PASSWORD = "123456";

    /**
     * 统一表前缀
     */
    private static final String DATASOURCE_TABLE_PREFIX = null;

    /**
     * 输出包含表
     */
    private static final String[] DATASOURCE_TABLE_INCLUDE = {};

    @Autowired
    private MybatisGenerateCodeService mybatisGenerateCodeService;

    /**
     * 简单配置生成
     */
    @Test
    void simpleSettingGenerate() {
        CodeBasicConfig codeBasicConfig = new CodeBasicConfig();
        // 作者
        codeBasicConfig.setAuthor(AUTHOR);

        CodeDataSourceConfig codeDataSourceConfig = new CodeDataSourceConfig();
        // 驱动连接的URL, 须指定
        codeDataSourceConfig.setUrl(DATASOURCE_URL);
        // 数据库连接用户名, 须指定
        codeDataSourceConfig.setUsername(DATASOURCE_USERNAME);
        // 数据库连接密码, 须指定
        codeDataSourceConfig.setPassword(DATASOURCE_PASSWORD);
        // 统一表前缀, 可为空
        codeDataSourceConfig.setTablePrefix(DATASOURCE_TABLE_PREFIX);
        // 输出包含表, 可为空, 为空时则输出所有表
        codeDataSourceConfig.setInclude(DATASOURCE_TABLE_INCLUDE);

        CodePackageConfig codePackageConfig = new CodePackageConfig();
        // 父包模块名, 须指定
        codePackageConfig.setParentModelName(PROJECT_CODE);

        // 开始生成
        mybatisGenerateCodeService.generate(codeBasicConfig, codeDataSourceConfig, codePackageConfig);
    }

    /**
     * 完整配置生成
     */
    @Test
    void fullSettingGenerate() {
        CodeBasicConfig codeBasicConfig = new CodeBasicConfig();
        // 作者
        codeBasicConfig.setAuthor(AUTHOR);
        // 版本号
        // 默认为 [1.0]
        codeBasicConfig.setSince("1.0");
        // 输出包类型
        // 默认为 [CodeBasicConfig.OutputType.ALL]
        codeBasicConfig.setOutputType(CodeBasicConfig.OutputType.ALL);
        // 是否开启Swagger注解支持
        // 默认为 [true]
        codeBasicConfig.setEnableSwagger(true);

        CodeDataSourceConfig codeDataSourceConfig = new CodeDataSourceConfig();
        // 驱动连接的URL, 须指定
        codeDataSourceConfig.setUrl(DATASOURCE_URL);
        // 数据库连接用户名, 须指定
        codeDataSourceConfig.setUsername(DATASOURCE_USERNAME);
        // 数据库连接密码, 须指定
        codeDataSourceConfig.setPassword(DATASOURCE_PASSWORD);
        // 统一表前缀, 可为空
        codeDataSourceConfig.setTablePrefix(DATASOURCE_TABLE_PREFIX);
        // 输出包含表, 可为空, 为空时则输出所有表
        codeDataSourceConfig.setInclude(DATASOURCE_TABLE_INCLUDE);

        CodePackageConfig codePackageConfig = new CodePackageConfig();
        // 输出包名
        // 默认为 [pro.haichuang.framework.service]
        codePackageConfig.setOutputPackage("pro.haichuang.framework.service");
        // 父包模块名, 须指定
        codePackageConfig.setParentModelName(PROJECT_CODE);
        // 实体类包名
        // 默认为 [pojo.domain]
        codePackageConfig.setEntityPackageName("pojo.domain");
        // 数据访问层包名
        // 默认为 [mapper]
        codePackageConfig.setMapperPackageName("mapper");
        // 数据访问层XML包名, 生成后请手动移动到 [resource] 对应目录下
        // 默认为 [mapper.xml]
        codePackageConfig.setMapperXmlPackageName("mapper.xml");
        // 业务逻辑层包名
        // 默认为 [service]
        codePackageConfig.setServicePackageName("service");
        // 业务逻辑实现层包名
        // 默认为 [service.impl]
        codePackageConfig.setServiceImplPackageName("service.impl");
        // 界面层包名
        // 默认为 [controller]
        codePackageConfig.setControllerPackageName("controller");
        // 输出根目录绝对路径
        // 默认为 [当前项目目录/src/main/java]
        codePackageConfig.setOutputDir(System.getProperty("user.dir").concat("/src/main/java/"));

        // 开始生成
        mybatisGenerateCodeService.generate(codeBasicConfig, codeDataSourceConfig, codePackageConfig);
    }
}
