package pro.haichuang.framework.mybatis.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.springframework.stereotype.Service;
import pro.haichuang.framework.mybatis.domain.BaseDO;
import pro.haichuang.framework.mybatis.enums.error.MybatisGenerateErrorEnum;
import pro.haichuang.framework.mybatis.exception.MybatisGenerateErrorApplication;
import pro.haichuang.framework.mybatis.generate.config.CodeBasicConfig;
import pro.haichuang.framework.mybatis.generate.config.CodeDataSourceConfig;
import pro.haichuang.framework.mybatis.generate.config.CodePackageConfig;
import pro.haichuang.framework.mybatis.service.BaseService;
import pro.haichuang.framework.mybatis.service.BaseServiceImpl;

import java.util.Collections;
import java.util.function.Consumer;

/**
 * MybatisPlus代码生成器
 *
 * <p>该类为 [MybatisPlus] 代码生成器服务, 自动将数据库表生成为 [Java] 代码
 * <p>Danger: 代码生成器将自动覆盖原有文件, 推荐在生成之前做好代码备份,
 * 如只需自动生成指定表则设置 {@link CodeDataSourceConfig#setInclude(String...)} 即可
 * <hr>
 * <p><a href="https://mp.baomidou.com/guide/generator-new.html">点击查看官方文档</a>
 *
 * @author JiYinchuan
 * @see CodeBasicConfig
 * @see CodeDataSourceConfig
 * @see CodePackageConfig
 * @since 1.1.0.211021
 */
@Service
public class MybatisGenerateCodeService {

    /**
     * 代码生成
     *
     * @param codeBasicConfig      基本配置
     * @param codeDataSourceConfig 数据源配置
     * @param codePackageConfig    包配置
     * @throws MybatisGenerateErrorApplication 代码生成异常
     * @since 1.1.0.211021
     */
    public void generate(CodeBasicConfig codeBasicConfig, CodeDataSourceConfig codeDataSourceConfig,
                         CodePackageConfig codePackageConfig) throws MybatisGenerateErrorApplication {
        if (codeDataSourceConfig.getUrl() == null || codeDataSourceConfig.getUrl().length() == 0) {
            throw new MybatisGenerateErrorApplication(MybatisGenerateErrorEnum.DATA_SOURCE_URL_IS_BLANK);
        }
        if (codeDataSourceConfig.getUsername() == null || codeDataSourceConfig.getUsername().length() == 0) {
            throw new MybatisGenerateErrorApplication(MybatisGenerateErrorEnum.DATA_SOURCE_USERNAME_IS_BLANK);
        }
        if (codeDataSourceConfig.getPassword() == null || codeDataSourceConfig.getPassword().length() == 0) {
            throw new MybatisGenerateErrorApplication(MybatisGenerateErrorEnum.DATA_SOURCE_PASSWORD_IS_BLANK);
        }
        if (codePackageConfig.getParentModelName() == null || codePackageConfig.getParentModelName().length() == 0) {
            throw new MybatisGenerateErrorApplication(MybatisGenerateErrorEnum.PACKAGE_PARENT_MODEL_NAME_IS_BLANK);
        }

        String url = codeDataSourceConfig.getUrl();
        String username = codeDataSourceConfig.getUsername();
        String password = codeDataSourceConfig.getPassword();
        FastAutoGenerator.create(url, username, password)
                .globalConfig(initGlobalConfig(codeBasicConfig, codePackageConfig))
                .packageConfig(initPackageConfig(codePackageConfig))
                .strategyConfig(initStrategyConfig(codeDataSourceConfig))
                .templateConfig(initTemplateConfig(codeBasicConfig))
                .injectionConfig(initInjectionConfig(codeBasicConfig))
                .execute();

    }

    /**
     * 初始化全局配置
     *
     * <p><a href="https://mp.baomidou.com/guide/generator-new.html#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig">点击查看官方文档</a>
     *
     * @param codeBasicConfig   基本配置
     * @param codePackageConfig 包配置
     * @return 全局配置
     * @since 1.1.0.211021
     */
    private Consumer<GlobalConfig.Builder> initGlobalConfig(CodeBasicConfig codeBasicConfig, CodePackageConfig codePackageConfig) {
        return builder -> builder
                // 覆盖已生成文件
                .fileOverride()
                // 指定输出目录
                .outputDir(codePackageConfig.getOutputDir())
                // 作者名
                .author(codeBasicConfig.getAuthor())
                // 开启 swagger 模式
                .enableSwagger()
                // 时间策略
                .dateType(DateType.TIME_PACK);
    }

    /**
     * 初始化包配置
     *
     * <p><a href="https://mp.baomidou.com/guide/generator-new.html#%E5%8C%85%E9%85%8D%E7%BD%AE-packageconfig">点击查看官方文档</a>
     *
     * @param codePackageConfig 包配置
     * @return 包配置
     * @since 1.1.0.211021
     */
    private Consumer<PackageConfig.Builder> initPackageConfig(CodePackageConfig codePackageConfig) {
        return builder -> builder
                // 父包名
                .parent(codePackageConfig.getOutputPackage())
                // 父包模块名
                .moduleName(codePackageConfig.getParentModelName())
                // Entity 包名
                .entity(codePackageConfig.getEntityPackageName())
                // Service 包名
                .service(codePackageConfig.getServicePackageName())
                // ServiceImpl 包名
                .serviceImpl(codePackageConfig.getServiceImplPackageName())
                // Mapper 包名
                .mapper(codePackageConfig.getMapperPackageName())
                // MapperXML 包名
                .xml(codePackageConfig.getMapperXmlPackageName())
                // Controller 包名
                .controller(codePackageConfig.getControllerPackageName())
                // 路径配置信息
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, codePackageConfig.getOutputResourcesDir()));
    }

    /**
     * 初始化模版配置
     *
     * <p><a href="https://mp.baomidou.com/guide/generator-new.html#%E6%A8%A1%E6%9D%BF%E9%85%8D%E7%BD%AE-templateconfig">点击查看官方文档</a>
     *
     * @param codeBasicConfig 基本配置
     * @return 包配置
     * @since 1.1.0.211021
     */
    private Consumer<TemplateConfig.Builder> initTemplateConfig(CodeBasicConfig codeBasicConfig) {
        return builder -> {
            if (codeBasicConfig.getOutputType() == CodeBasicConfig.OutputType.ONLY_DOMAIN) {
                // 禁用模板
                builder.disable(TemplateType.ENTITY);
            }
            if (codeBasicConfig.getOutputType() != CodeBasicConfig.OutputType.ALL_EXCLUDE_DOMAIN) {
                // 设置实体模板路径
                builder.entity("/templates/entity.java");
            }
            builder
                    // 设置 service 模板路径
                    .service("/templates/service.java")
                    // 设置 serviceImpl 模板路径
                    .serviceImpl("/templates/serviceImpl.java")
                    // 设置 mapper 模板路径
                    .mapper("/templates/mapper.java")
                    // 设置 mapperXml 模板路径
                    .mapperXml("/templates/mapper.xml")
                    // 设置 controller 模板路径
                    .controller("/templates/controller.xml");
        };
    }

    /**
     * 初始化注入配置
     *
     * <p><a href="https://mp.baomidou.com/guide/generator-new.html#%E6%B3%A8%E5%85%A5%E9%85%8D%E7%BD%AE-injectionconfig">点击查看官方文档</a>
     *
     * @param codeBasicConfig 基本配置
     * @return 包配置
     * @since 1.1.0.211021
     */
    private Consumer<InjectionConfig.Builder> initInjectionConfig(CodeBasicConfig codeBasicConfig) {
        return builder -> builder
                // 自定义配置 Map 对象
                .customMap(Collections.singletonMap("since", codeBasicConfig.getSince()));
    }

    /**
     * 初始化策略配置
     *
     * <p><a href="https://mp.baomidou.com/guide/generator-new.html#%E7%AD%96%E7%95%A5%E9%85%8D%E7%BD%AE-strategyconfig">点击查看官方文档</a>
     *
     * @param codeDataSourceConfig 数据源配置
     * @return 策略配置
     * @since 1.1.0.211021
     */
    private Consumer<StrategyConfig.Builder> initStrategyConfig(CodeDataSourceConfig codeDataSourceConfig) {
        return builder -> {
            if (codeDataSourceConfig.getTablePrefix() != null && !codeDataSourceConfig.getTablePrefix().isEmpty()) {
                builder.addTablePrefix(codeDataSourceConfig.getTablePrefix());
            }
            if (codeDataSourceConfig.getInclude().length > 0) {
                // 增加表匹配 (内存过滤)
                builder.addInclude(codeDataSourceConfig.getInclude());
            }
            // 实体策略配置
            builder.entityBuilder()
                    // 设置父类
                    .superClass(BaseDO.class)
                    // 开启链式模型
                    .enableChainModel()
                    // 开启 lombok 模型
                    .enableLombok()
                    // 开启 Boolean 类型字段移除 is 前缀
                    .enableRemoveIsPrefix()
                    // 开启生成实体时生成字段注解
                    .enableTableFieldAnnotation()
                    // 乐观锁字段名 (数据库)
                    .versionColumnName(BaseDO.VERSION)
                    // 乐观锁属性名 (实体)
                    .versionPropertyName(BaseDO.VERSION)
                    // 逻辑删除字段名 (数据库)
                    .logicDeleteColumnName(BaseService.toUnderlineCase(BaseDO.LOGIC_DELETE))
                    // 逻辑删除属性名 (实体)
                    .logicDeletePropertyName(BaseDO.LOGIC_DELETE)
                    // 添加父类公共字段
                    .addSuperEntityColumns(BaseDO.ID, BaseService.toUnderlineCase(BaseDO.CREATE_TIME), BaseService.toUnderlineCase(BaseDO.MODIFY_TIME))
                    // 格式化文件名称
                    .formatFileName("%sDO")
                    .build();

            // Controller策略配置
            builder.controllerBuilder()
                    // 开启生成 @RestController 控制器
                    .enableRestStyle()
                    .build();

            // Service策略配置
            builder.serviceBuilder()
                    // 设置 service 接口父类
                    .superServiceClass(BaseService.class)
                    // 设置 service 实现类父类
                    .superServiceImplClass(BaseServiceImpl.class)
                    // 格式化 service 接口文件名称
                    .formatServiceFileName("%sService")
                    // 格式化 service 实现类文件名称
                    .formatServiceImplFileName("%sServiceImpl")
                    .build();

            // Mapper策略配置
            builder.mapperBuilder()
                    // 开启 @Mapper 注解
                    .enableMapperAnnotation()
                    // 格式化 mapper 文件名称
                    .formatMapperFileName("%sMapper")
                    // 格式化 xml 实现类文件名称
                    .formatXmlFileName("%sXml")
                    .build();
        };
    }
}
