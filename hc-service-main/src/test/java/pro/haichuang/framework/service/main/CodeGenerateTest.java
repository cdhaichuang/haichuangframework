package pro.haichuang.framework.service.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService;
import pro.haichuang.framework.mybatis.generate.config.CodeBasicConfig;
import pro.haichuang.framework.mybatis.generate.config.CodeDataSourceConfig;
import pro.haichuang.framework.mybatis.generate.config.CodePackageConfig;

/**
 * CodeGenerateTest
 *
 * @author JiYinchuan
 * @version 1.0
 */

@SpringBootTest(classes = ServiceApplication.class)
public class CodeGenerateTest {

    @Autowired
    private MybatisGenerateCodeService mybatisGenerateCodeService;

    @Test
    void generate() {
        CodeBasicConfig codeBasicConfig = new CodeBasicConfig();

        CodeDataSourceConfig codeDataSourceConfig = new CodeDataSourceConfig();
        codeDataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/data_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowMutiQueries=true");
        codeDataSourceConfig.setUsername("root");
        codeDataSourceConfig.setPassword("123456");
        codeDataSourceConfig.setTablePrefix(null);
        // codeDataSourceConfig.setInclude();

        CodePackageConfig codePackageConfig = new CodePackageConfig();
        // 设置项目代号
        codePackageConfig.setParentModelName("main");

        mybatisGenerateCodeService.generate(codeBasicConfig, codeDataSourceConfig, codePackageConfig);
    }
}
