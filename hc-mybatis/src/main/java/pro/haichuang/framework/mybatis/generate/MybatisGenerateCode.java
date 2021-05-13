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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.haichuang.framework.mybatis.base.BaseService;
import pro.haichuang.framework.mybatis.config.properties.MybatisProperties;
import pro.haichuang.framework.mybatis.domain.BaseDO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * MybatisPlus代码生成器
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Component
public class MybatisGenerateCode {

    @Autowired
    private MybatisProperties mybatisProperties;

    public void generate() {
        if (mybatisProperties.getGenerate().getDatasourceConfig().getUrl() == null || mybatisProperties.getGenerate().getDatasourceConfig().getUrl().length() == 0) {
            throw new RuntimeException("[代码生成器] 数据库URL未配置");
        }
        if (mybatisProperties.getGenerate().getDatasourceConfig().getUsername() == null || mybatisProperties.getGenerate().getDatasourceConfig().getUsername().length() == 0) {
            throw new RuntimeException("[代码生成器] 数据库帐号未配置");
        }
        if (mybatisProperties.getGenerate().getDatasourceConfig().getPassword() == null || mybatisProperties.getGenerate().getDatasourceConfig().getPassword().length() == 0) {
            throw new RuntimeException("[代码生成器] 数据库密码未配置");
        }
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(this.initGlobalConfig());
        ag.setDataSource(this.initDataSourceConfig());
        ag.setStrategy(this.initStrategyConfig());
        ag.setPackageInfo(this.initPackageConfig());

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(2);
                map.put("version", mybatisProperties.getGenerate().getBasicConfig().getVersion());
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

        if (mybatisProperties.getGenerate().getBasicConfig().getOutputType() == -1) {
            ag.setTemplate(templateConfig.setEntity("/templates/entity.java")).execute();
            return;
        }
        templateConfig.setService("/templates/service.java");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setXml("/templates/mapper.xml");
        templateConfig.setController("/templates/controller.java");
        if (mybatisProperties.getGenerate().getBasicConfig().getOutputType() == 0) {
            templateConfig.setEntity("/templates/entity.java");
        }

        ag.setTemplate(templateConfig).execute();
    }

    private GlobalConfig initGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();

        // 生成文件的输出目录【默认 D 盘根目录】
        gc.setOutputDir(mybatisProperties.getGenerate().getPackageConfig().getOutputDir());
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 是否打开输出目录
        gc.setOpen(true);
        // 是否在xml中添加二级缓存配置
        gc.setEnableCache(false);
        // 开发人员
        gc.setAuthor(mybatisProperties.getGenerate().getBasicConfig().getAuthor());
        // 开启 Kotlin 模式
        gc.setKotlin(false);
        // 开启 swagger2 模式
        gc.setSwagger2(mybatisProperties.getGenerate().getBasicConfig().getEnableSwagger());
        // 开启 ActiveRecord 模式
        gc.setActiveRecord(false);
        // 开启 BaseResultMap
        gc.setBaseResultMap(true);
        // 时间类型对应策略
        gc.setDateType(DateType.TIME_PACK);
        // 开启 baseColumnList
        gc.setBaseColumnList(false);
        // 各层文件名称方式，例如： %sAction 生成 UserAction
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

    private DataSourceConfig initDataSourceConfig() {
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
        dsc.setUrl(mybatisProperties.getGenerate().getDatasourceConfig().getUrl());
        // 驱动名称
        dsc.setDriverName(mybatisProperties.getGenerate().getDatasourceConfig().getDriver());
        // 数据库连接用户名
        dsc.setUsername(mybatisProperties.getGenerate().getDatasourceConfig().getUsername());
        // 数据库连接密码
        dsc.setPassword(mybatisProperties.getGenerate().getDatasourceConfig().getPassword());

        return dsc;
    }

    private StrategyConfig initStrategyConfig() {
        StrategyConfig sc = new StrategyConfig();
        String[] includeTables = StringUtils.isNotBlank(mybatisProperties.getGenerate().getDatasourceConfig().getInclude()) ? Arrays.stream(mybatisProperties.getGenerate().getDatasourceConfig().getInclude().split(",")).map(String::trim).toArray(String[]::new) : null;

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
        if (mybatisProperties.getGenerate().getDatasourceConfig().getTablePrefix() != null && mybatisProperties.getGenerate().getDatasourceConfig().getTablePrefix().length() != 0) {
            sc.setTablePrefix(mybatisProperties.getGenerate().getDatasourceConfig().getTablePrefix());
        }
        // 字段前缀
        sc.setFieldPrefix("");
        // 自定义继承的Entity类全称，带包名
        sc.setSuperEntityClass(BaseDO.class);
        // 自定义基础的Entity类，公共字段
        sc.setSuperEntityColumns(BaseDO.ID, BaseService.toUnderlineCase(BaseDO.CREATE_TIME), BaseService.toUnderlineCase(BaseDO.MODIFY_TIME));
        // 自定义继承的Mapper类全称，带包名 （默认）
        sc.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 自定义继承的Service类全称，带包名 （默认）
        sc.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        // 自定义继承的ServiceImpl类全称，带包名 （默认）
        sc.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        // 自定义继承的Controller类全称，带包名 （默认）
        // sc.setSuperControllerClass(null);
        // 需要包含的表名（与exclude二选一配置，likeTable|notLikeTable可以模糊匹配）
        if (includeTables != null && includeTables.length != 0) {
            sc.setInclude(includeTables);
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
        // 是否生成实体时，生成字段注解
        sc.setEntityTableFieldAnnotationEnable(true);
        // 乐观锁属性名称
        sc.setVersionFieldName(null);
        // 逻辑删除属性名称
        sc.setLogicDeleteFieldName(BaseDO.LOGIC_DELETE);
        // 表填充字段
        sc.setTableFillList(null);
        // 启用sql过滤
        // 语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
        // 目前所知微软系需要关闭，其他数据库等待反馈，sql可能要改动一下才能支持，没数据库环境搞，请手动关闭使用内存过滤的方式。
        sc.setEnableSqlFilter(true);

        return sc;
    }

    private PackageConfig initPackageConfig() {
        PackageConfig pc = new PackageConfig();

        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(mybatisProperties.getGenerate().getPackageConfig().getOutputPackage());
        // 父包模块名
        pc.setModuleName(mybatisProperties.getGenerate().getPackageConfig().getParentModelName());
        // Entity包名
        pc.setEntity(mybatisProperties.getGenerate().getPackageConfig().getEntityPackageName());
        // Service包名
        pc.setService(mybatisProperties.getGenerate().getPackageConfig().getServicePackageName());
        // Service Impl包名
        pc.setServiceImpl(mybatisProperties.getGenerate().getPackageConfig().getServiceImplPackageName());
        // Mapper包名
        pc.setMapper(mybatisProperties.getGenerate().getPackageConfig().getMapperPackageName());
        // Mapper XML包名
        pc.setXml(mybatisProperties.getGenerate().getPackageConfig().getMapperXmlPackageName());
        // Controller包名
        pc.setController(mybatisProperties.getGenerate().getPackageConfig().getControllerPackageName());
        // 路径配置信息
        pc.setPathInfo(null);

        return pc;
    }
}
