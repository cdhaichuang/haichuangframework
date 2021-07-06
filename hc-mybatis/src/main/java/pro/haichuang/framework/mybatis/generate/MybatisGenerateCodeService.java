package pro.haichuang.framework.mybatis.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.stereotype.Service;
import pro.haichuang.framework.mybatis.domain.BaseDO;
import pro.haichuang.framework.mybatis.enums.error.MybatisGenerateErrorEnum;
import pro.haichuang.framework.mybatis.exception.MybatisGenerateErrorApplication;
import pro.haichuang.framework.mybatis.generate.config.CodeBasicConfig;
import pro.haichuang.framework.mybatis.generate.config.CodeDataSourceConfig;
import pro.haichuang.framework.mybatis.generate.config.CodePackageConfig;
import pro.haichuang.framework.mybatis.service.BaseService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * MybatisPlus代码生成器
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Service
@SuppressWarnings("SpellCheckingInspection")
public class MybatisGenerateCodeService {

    public static final String DEFAULT_SUPER_MAPPER_CLASS = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
    public static final String DEFAULT_SUPER_SERVICE_CLASS = "pro.haichuang.framework.mybatis.service.BaseService";
    public static final String DEFAULT_SUPER_SERVICE_IMPL_CLASS = "pro.haichuang.framework.mybatis.service.BaseServiceImpl";

    /**
     * 代码生成
     *
     * @param codeBasicConfig      基本配置
     * @param codeDataSourceConfig 数据源配置
     * @param codePackageConfig    包配置
     * @throws MybatisGenerateErrorApplication 代码生成异常
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
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(this.initGlobalConfig(codeBasicConfig, codePackageConfig));
        ag.setDataSource(this.initDataSourceConfig(codeDataSourceConfig));
        ag.setStrategy(this.initStrategyConfig(codeDataSourceConfig));
        ag.setPackageInfo(this.initPackageConfig(codePackageConfig));

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(2);
                map.put("version", codeBasicConfig.getVersion());
                map.put("dateTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                setMap(map);
            }
        };
        ag.setCfg(cfg);

        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setEntity(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setMapper(null);
        templateConfig.setXml(null);
        templateConfig.setController(null);

        if (codeBasicConfig.getOutputType() == CodeBasicConfig.OutputType.ONLY_DOMAIN) {
            ag.setTemplate(templateConfig.setEntity("/templates/entity.java")).execute();
            return;
        }
        templateConfig.setService("/templates/service.java");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setXml("/templates/mapper.xml");
        templateConfig.setController("/templates/controller.java");
        if (codeBasicConfig.getOutputType() != CodeBasicConfig.OutputType.ALL_EXCLUDE_DOMAIN) {
            templateConfig.setEntity("/templates/entity.java");
        }

        ag.setTemplate(templateConfig).execute();
    }

    /**
     * 初始化全局配置
     *
     * @param codeBasicConfig   基本配置
     * @param codePackageConfig 包配置
     * @return 全局配置
     */
    private GlobalConfig initGlobalConfig(CodeBasicConfig codeBasicConfig, CodePackageConfig codePackageConfig) {
        GlobalConfig gc = new GlobalConfig();

        // 生成文件的输出目录【默认 D 盘根目录】
        gc.setOutputDir(codePackageConfig.getOutputDir());
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 是否打开输出目录
        gc.setOpen(true);
        // 是否在xml中添加二级缓存配置
        gc.setEnableCache(false);
        // 开发人员
        gc.setAuthor(codeBasicConfig.getAuthor());
        // 开启 Kotlin 模式
        gc.setKotlin(false);
        // 开启 swagger2 模式
        gc.setSwagger2(codeBasicConfig.getEnableSwagger());
        // 开启 ActiveRecord 模式
        gc.setActiveRecord(false);
        // 开启 BaseResultMap
        gc.setBaseResultMap(false);
        // 时间类型对应策略
        gc.setDateType(DateType.TIME_PACK);
        // 开启 baseColumnList
        gc.setBaseColumnList(false);
        // 各层文件名称方式, 例如： %sAction 生成 UserAction
        gc.setEntityName("%sDO");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.ASSIGN_ID);

        return gc;
    }

    /**
     * 初始化数据源配置
     *
     * @param codeDataSourceConfig 数据源配置
     * @return 数据源配置
     */
    private DataSourceConfig initDataSourceConfig(CodeDataSourceConfig codeDataSourceConfig) {
        DataSourceConfig dsc = new DataSourceConfig();

        // 数据库信息查询 （根据DbType自动生成）
        dsc.setDbQuery(new MySqlQuery());
        // 数据库信息查询 （默认为Mysql）
        dsc.setDbType(DbType.MYSQL);
        // PostgreSQL schemaName
        dsc.setSchemaName(null);
        // 类型转换 （根据DbType自动生成）
        dsc.setTypeConvert(new MySqlTypeConvert());
        // 驱动连接的URL
        dsc.setUrl(codeDataSourceConfig.getUrl());
        // 驱动名称
        dsc.setDriverName(codeDataSourceConfig.getDriver());
        // 数据库连接用户名
        dsc.setUsername(codeDataSourceConfig.getUsername());
        // 数据库连接密码
        dsc.setPassword(codeDataSourceConfig.getPassword());

        return dsc;
    }

    /**
     * 初始化策略配置
     *
     * @param codeDataSourceConfig 数据源配置
     * @return 策略配置
     */
    private StrategyConfig initStrategyConfig(CodeDataSourceConfig codeDataSourceConfig) {
        StrategyConfig sc = new StrategyConfig();

        // 是否大写命名
        sc.setCapitalMode(false);
        // 是否跳过视图
        sc.setSkipView(false);
        // 名称转换
        sc.setNameConvert(null);
        // 数据库表映射到实体的命名策略
        sc.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略 （未指定按照 naming 执行）
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 表前缀
        if (codeDataSourceConfig.getTablePrefix() != null && codeDataSourceConfig.getTablePrefix().length() != 0) {
            sc.setTablePrefix(codeDataSourceConfig.getTablePrefix());
        }
        // 字段前缀
        sc.setFieldPrefix("");
        // 自定义继承的Entity类全称, 带包名
        sc.setSuperEntityClass(BaseDO.class);
        // 自定义基础的Entity类, 公共字段
        sc.setSuperEntityColumns(BaseDO.ID, BaseService.toUnderlineCase(BaseDO.CREATE_TIME), BaseService.toUnderlineCase(BaseDO.MODIFY_TIME));
        // 自定义继承的Mapper类全称, 带包名 （默认）
        sc.setSuperMapperClass(DEFAULT_SUPER_MAPPER_CLASS);
        // 自定义继承的Service类全称, 带包名 （默认=com.baomidou.mybatisplus.extension.service.IService）
        sc.setSuperServiceClass(DEFAULT_SUPER_SERVICE_CLASS);
        // 自定义继承的ServiceImpl类全称, 带包名 （默认=com.baomidou.mybatisplus.extension.service.impl.ServiceImpl）
        sc.setSuperServiceImplClass(DEFAULT_SUPER_SERVICE_IMPL_CLASS);
        // 自定义继承的Controller类全称, 带包名（默认=null）[sc.setSuperControllerClass(null);]
        // 需要包含的表名（与exclude二选一配置, likeTable|notLikeTable可以模糊匹配）
        if (codeDataSourceConfig.getInclude() != null && codeDataSourceConfig.getInclude().length != 0) {
            sc.setInclude(codeDataSourceConfig.getInclude());
        }
        // 实体是否生成 serialVersionUID
        sc.setEntitySerialVersionUID(true);
        // 【实体】是否生成字段常量（默认 false）
        sc.setEntityColumnConstant(false);
        // 【实体】是否为构建者模型（默认 false）
        sc.setChainModel(true);
        // 【实体】是否为lombok模型（默认 false）
        sc.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀（默认 false）
        sc.setEntityBooleanColumnRemoveIsPrefix(true);
        // 生成 @RestController 控制器
        sc.setRestControllerStyle(true);
        // 驼峰转连字符（@RequestMapping）
        sc.setControllerMappingHyphenStyle(false);
        // 是否生成实体时, 生成字段注解
        sc.setEntityTableFieldAnnotationEnable(true);
        // 乐观锁属性名称
        sc.setVersionFieldName(null);
        // 逻辑删除属性名称
        sc.setLogicDeleteFieldName(BaseDO.LOGIC_DELETE);
        // 表填充字段
        sc.setTableFillList(null);
        // 启用sql过滤
        // 语法不能支持使用sql过滤表的话, 可以考虑关闭此开关.
        // 目前所知微软系需要关闭, 其他数据库等待反馈, sql可能要改动一下才能支持, 没数据库环境搞, 请手动关闭使用内存过滤的方式。
        sc.setEnableSqlFilter(true);

        return sc;
    }

    /**
     * 初始化包配置
     *
     * @param codePackageConfig 包配置
     * @return 包配置
     */
    private PackageConfig initPackageConfig(CodePackageConfig codePackageConfig) {
        PackageConfig pc = new PackageConfig();

        // 父包名。如果为空, 将下面子包名必须写全部,  否则就只需写子包名
        pc.setParent(codePackageConfig.getOutputPackage());
        // 父包模块名
        pc.setModuleName(codePackageConfig.getParentModelName());
        // Entity包名
        pc.setEntity(codePackageConfig.getEntityPackageName());
        // Service包名
        pc.setService(codePackageConfig.getServicePackageName());
        // Service Impl包名
        pc.setServiceImpl(codePackageConfig.getServiceImplPackageName());
        // Mapper包名
        pc.setMapper(codePackageConfig.getMapperPackageName());
        // Mapper XML包名
        pc.setXml(codePackageConfig.getMapperXmlPackageName());
        // Controller包名
        pc.setController(codePackageConfig.getControllerPackageName());
        // 路径配置信息
        pc.setPathInfo(null);

        return pc;
    }
}
