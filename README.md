<p align="center">
  <img width="320" src="https://www.haichuang.pro/upload/202010/1602729036.png">
</p>


# 简介

`haichuangframework`是一个简易 [spring-boot](https://github.com/spring-projects/spring-boot) 工具封装。它使用了较新的依赖版本，在 [spring-boot](https://github.com/spring-projects/spring-boot) 的基础上进一步封装了常见的基本配置，开箱即用，统一了开发风格，减少了大部分重复开发的工作，同时也让维护更加容易，如果在使用中遇到了任何问题可以提交 `Issues` ，我们将尽快优化，希望本项目能帮助到您。



# 环境说明

<p align="center">
  <b>Develop</b>
</p>

|  类型   | 版本  | 说明            |
| :-----: | :---: | --------------- |
|  `JDK`  |  1.8  | `JDK`指定版本   |
| `Maven` | 3.6.3 | `Maven`指定版本 |



# 依赖版本

<p align="center">
  <b>spring-boot-starter</b>
</p>


|                          类型                          |   版本   |       说明        |
| :----------------------------------------------------: | :------: | :---------------: |
|               `org.springframework.boot`               |  2.4.5   |     基本依赖      |
|        `com.alibaba:druid-spring-boot-starter`         |  1.2.6   |   `druid`数据源   |
|        `com.baomidou:mybatis-plus-boot-starter`        |  3.4.1   |  `mybatis-plus`   |
| `com.github.pagehelper:pagehelper-spring-boot-starter` |  1.3.0   | `pagehelper`分页  |
|   `com.github.xiaoymin:knife4j-spring-boot-starter`    |  2.0.8   | `knife4j`接口文档 |
|   `org.springframework.boot:spring-boot-starter-aop`   | 默认版本 |       `AOP`       |

<p align="center">
  <b>dependencies</b>
</p>


|                    类型                    |   版本   |           说明           |
| :----------------------------------------: | :------: | :----------------------: |
|        `mysql:mysql-connector-java`        | 默认版本 |  `mysql-connector-java`  |
|       `commons-codec:commons-codec`        | 默认版本 |     `commons-codec`      |
|   `org.apache.httpcomponents:httpclient`   | 默认版本 |       `httpclient`       |
|     `org.apache.commons:commons-lang3`     | 默认版本 |     `commons-lang3`      |
|     `org.apache.commons:commons-pool2`     | 默认版本 |     `commons-pool2`      |
| `org.apache.commons:commons-collections4`  |   4.4    |      `collection4`       |
|          `commons-io:commons-io`           |  2.8.0   |       `common-io`        |
|  `commons-fileupload:commons-fileupload`   |   1.4    |   `commons-fileupload`   |
|   `com.baomidou:mybatis-plus-generator`    |  3.4.1   | `MybatisPlus`代码生成器  |
| `org.apache.velocity:velocity-engine-core` |   2.3    |    `Velocity`模版引擎    |
|           `io.jsonwebtoken:jjwt`           |  0.9.1   |     `Java`封装`JWT`      |
|           `com.alibaba:fastjson`           |  1.2.76  |        `fastjson`        |
|           `cn.hutool:hutool-all`           |  5.6.4   |         `hutool`         |
|       `org.modelmapper:modelmapper`        |  2.4.2   | 实体映射工具（反射实现） |

<p align="center">
  <b>Third</b>
</p>


|                 类型                  | 版本  |         说明          |
| :-----------------------------------: | :---: | :-------------------: |
|   `com.aliyun:aliyun-java-sdk-core`   | 4.5.3 | 阿里云`SDK`基础依赖包 |
| `com.aliyun:aliyun-java-sdk-dysmsapi` | 1.1.0 |    阿里云短信`SDK`    |



# 注意事项

> 项目第一次使用必须在根目录执行初始化脚本：`java Init 项目Code 运行端口`



**项目基础架构禁止修改|项目基础架构禁止修改|项目基础架构禁止修改**

**项目核心依赖版本禁止修改|项目核心依赖版本禁止修改|项目核心依赖版本禁止修改**



**如遇问题或建议可提交`Issues`或发送至邮箱 `jiyinchuan@haichuang.pro`，我们将尽快回复**



**其他注意事项以及开发要求详阅《嗨创软服-研发部开发手册》**



# 名字释义

项目Code：项目代号首字母全小写+数字



# 功能说明

## 一、架构核心

> 默认请求前缀统一为`/api`



### 目录释义&命名规范

```
|pro.haichuang.framework.service.项目代Code-----主包名	
|  config-----------------------------------------|配置包
|  |  properties----------------------------------|--配置文件包（映射yml文件中自定义配置, 文件命名格式：##Properties）
|  |  SwaggerConfig.java--------------------------|--Swagger配置文件（使用SwaggerFactory进行配置）
|  |  [other]-------------------------------------|--其他核心配置文件（文件命名格式：##Config）
|  constant---------------------------------------|常量包（文件命名格式：##Constant或##Const）
|  enum-------------------------------------------|枚举包（文件命名格式：##Enums）
|  controller-------------------------------------|Controller包
|  |  cms-----------------------------------------|--后台管理系统接口包（文件命名格式：Cms##Controller）
|  |  app-----------------------------------------|--APP接口包（文件命名格式：App##Controller）
|  |  mp------------------------------------------|--公众号接口包（文件命名格式：Mp##Controller）
|  |  mina----------------------------------------|--小程序接口包（文件命名格式：Mina##Controller）
|  |  pc------------------------------------------|--电脑端接口包（文件命名格式：Pc##Controller）
|  |  mobile--------------------------------------|--手机端接口包（文件命名格式：Mobile##Controller）
|  service----------------------------------------|Service包（文件命名格式：##Service）
|  |  impl----------------------------------------|--ServiceImpl包（文件命名格式：##ServiceImpl）
|  mapper-----------------------------------------|Mapper包
|  pojo-------------------------------------------|POJO包
|  |  domain--------------------------------------|--实体类包（文件命名格式：##DO）
|  |  dto-----------------------------------------|--DTO包（文件命名格式：##DTO）
|  |  request-------------------------------------|--请求包（文件命名格式：##Request或##Req）
|  |  vo------------------------------------------|--响应包（文件命名格式：##VO）
|  util-------------------------------------------|工具类包（文件命名格式：##Utils）
|  ServiceApplication.java------------------------|SpringBoot启动文件
```



### 核心配置

```yaml
haichuang:
  // 是否启用基础自动配置，默认值为 [true]
  enable: true
```



### 核心注解

> 包路径：`pro.haichuang.framework.base.config.annotation`

- `@EnableControllerAdvice`：启用全局控制器异常拦截
- `@EnableControllerLogAspect`：启用请求`AOP`日志打印
- `@EnableLogSave`：启动请求`AOP`日志持久化，需要在 `Controller` 接口上进行标注`pro.haichuang.framework.base.config.annotation.LogSave`注解，通过自定义继承`pro.haichuang.framework.base.config.interceptor.AbstractLogSave`抽象类实现逻辑
- `@EnableSecurityAspect`：启用`AOP`权限拦截注解，需要自定义继承`pro.haichuang.framework.base.config.interceptor.AbstractSecurityValidate`抽象类实现逻辑，配置完成后可使用`pro.haichuang.framework.base.config.annotation`包下验证注解



### 全局序列化与反序列化定义

#### 时间类型序列化与反序列化

- `java.util.Date`类型序列化与反序列化为`yyyy-MM-dd HH:mm:ss`
- `java.time.LocalTime`类型序列化与反序列化为`HH:mm:ss`
- `java.time.LocalDate`类型序列化与反序列化为`yyyy-MM-dd`
- `java.time.LocalDateTime`类型序列化与反序列化为`yyyy-MM-dd HH:mm:ss`
- 如需传输其他自定义时间格式请使用`java.lang.String`类型进行传输`

#### 枚举类型序列化与反序列化

- 自定义枚举类必须实现`pro.haichuang.framework.base.enums.BaseEnum`接口
- `Jackson`枚举请求参数序列化已经定义，配置参考`pro.haichuang.framework.base.enums.request.ParamEnumConverterFactory`
- `Jackson`枚举请求体序列化需要手动在枚举类中加入`com.fasterxml.jackson.annotation.JsonCreator`注解，配置如下

```java
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
public static MyEnum resolve(String value) {
        return BaseEnum.resolve(value, MyEnum.class);
        }
```

> `Jackson`序列化与反序列化规则已经定义，配置参考`pro.haichuang.framework.base.config.mvc.JacksonConfig`
>
> `FastJson`序列化与反序列化规则已经定义，配置参考`pro.haichuang.framework.base.config.mvc.JacksonConfig`



### 接口文档`Knife4J(Swagger2)`工厂类

- 单分组简单使用如下

  ```java
  @SpringBootConfiguration
  @EnableSwagger2WebMvc
  public class SwaggerConfig {
  
      @Resource
      private OpenApiExtensionResolver openApiExtensionResolver;
  
      @Bean
      public Docket createAppRestApi() {
          return SwaggerFactory.createRestApi(SwaggerInfoDTO.builder()
                  .basePackage("pro.haichuang.framework.service.项目代号.xx")
                  .openApiExtensionResolver(openApiExtensionResolver)
                  .parameters(Collections.singletonList(new ApiKey("[参数说明(Token)]", "[参数名(Authorization)]", "[参数位置(header|query)]")))
                  .build());
      }
  }
  ```

- 多分组简单使用如下

  ```java
  @SpringBootConfiguration
  @EnableSwagger2WebMvc
  public class SwaggerConfig {
  
      @Resource
      private OpenApiExtensionResolver openApiExtensionResolver;
  
      @Bean
      public Docket createAppRestApi() {
          return SwaggerFactory.createRestApi(SwaggerInfoDTO.builder()
                  .groupName("APP")
                  .basePackage("pro.haichuang.framework.service.项目代号.xx")
                  .openApiExtensionResolver(openApiExtensionResolver)
                  .parameters(Collections.singletonList(new ApiKey("[参数说明(Token)]", "[参数名(Authorization)]", "[参数位置(header|query)]")))
                  .build());
      }
  
      @Bean
      public Docket createAppRestApi() {
          return SwaggerFactory.createRestApi(SwaggerInfoDTO.builder()
                  .groupName("CMS")
                  .basePackage("pro.haichuang.framework.service.项目Code.xx")
                  .openApiExtensionResolver(openApiExtensionResolver)
                  .parameters(Collections.singletonList(new ApiKey("[参数说明(Token)]", "[参数名(Authorization)]", "[参数位置(header|query)]")))
                  .build());
      }
  }
  ```

> `SwaggerInfoDTO`详情请参阅`pro.haichuang.framework.base.dto.SwaggerInfoDTO`



### 全局响应错误码枚举

> 包路径：`pro.haichuang.framework.base.enums.abnormal`

- #### 成功枚举：`SuccessEnum`

  ```java
  OK("00000", "OK");
  ```

- #### 请求参数异常枚举：`RequestParamAbnormalEnum`

  ```java
  PARAMETER_ERROR("A0400", "用户请求参数错误"),
  
  CONTAINS_ILLEGAL_LINKS("A0401", "包含非法恶意跳转链接"),
  
  INVALID_INPUT("A0402", "无效的用户输入"),
  
  PARAMETER_EMPTY("A0410", "请求必填参数为空"),
  
  ORDER_NUMBER_EMPTY("A0411", "用户订单号为空"),
  
  ORDER_QUANTITY_EMPTY("A0412", "订购数量为空"),
  
  TIMESTAMP_PARAMETER_EMPTY("A0413", "缺少时间戳参数"),
  
  ILLEGAL_TIMESTAMP_PARAMETER("A0414", "非法的时间戳参数"),
  
  PARAMETER_EXCEEDS_RANGE("A0420", "请求参数值超出允许的范围"),
  
  PARAMETER_FORMAT_NOT_MATCH("A0421", "参数格式不匹配"),
  
  ADDRESS_NOT_IN_SERVICE("A0422", "地址不在服务范围"),
  
  TIME_NOT_IN_SERVICE("A0423", "时间不在服务范围"),
  
  AMOUNT_EXCEEDS_LIMIT("A0424", "金额超出限制"),
  
  COUNT_EXCEEDS_LIMIT("A0425", "数量超出限制"),
  
  BATCH_PROCESSING_EXCEEDS_LIMIT("A0426", "请求批量处理总个数超出限制"),
  
  JSON_PARSING_FAILED("A0427", "请求 JSON 解析失败"),
  
  INPUT_ILLEGAL("A0430", "用户输入内容非法"),
  
  PROHIBITED_SENSITIVE_WORDS("A0431", "包含违禁敏感词"),
  
  PICTURE_PROHIBITED_INFORMATION("A0432", "图片包含违禁信息"),
  
  FILE_INFRINGES_COPYRIGHT("A0433", "文件侵犯版权"),
  
  OPERATION_ABNORMAL("A0440", "用户操作异常"),
  
  PAYMENT_TIMEOUT("A0441", "用户支付超时"),
  
  CONFIRM_ORDER_TIMEOUT("A0442", "确认订单超时"),
  
  ORDER_CLOSED("A0443", "订单已关闭");
  ```

- #### 请求服务异常枚举：`RequestServerAbnormalEnum`

  ```java
  SERVICE_ABNORMAL("A0500", "用户请求服务异常"),
  
  NUMBER_OF_REQUESTS_EXCEEDS_LIMIT("A0501", "请求次数超出限制"),
  
  CONCURRENCY_EXCEEDS_LIMIT("A0502", "请求并发数超出限制"),
  
  OPERATION_PLEASE_WAIT("A0503", "用户操作请等待"),
  
  WEBSOCKET_CONNECTION_ABNORMAL("A0504", "WebSocket 连接异常"),
  
  WEBSOCKET_DISCONNECT("A0505", "WebSocket 连接断开"),
  
  REPEAT_REQUEST("A0506", "用户重复请求");
  ```

- #### 用户登录异常枚举：`LoginAbnormalEnum`

  ```java
  USER_LOGIN_ABNORMAL("A0200", "用户登录异常"),
  
  USER_ACCOUNT_NOT_EXIST("A0201", "用户账户不存在"),
  
  USER_ACCOUNT_FROZEN("A0202", "用户账户被冻结"),
  
  USER_ACCOUNT_INVALID("A0203", "用户账户已作废"),
  
  USER_ACCOUNT_EXPIRE("A0204", "用户账户已过期"),
  
  USER_CREDENTIALS_EXPIRE("A0205", "用户凭证已过期"),
  
  USER_PASSWORD_VERIFY_FAILED("A0210", "用户密码错误"),
  
  USER_INPUT_ERROR_PASSWORD_OVERRUN("A0211", "用户输入密码错误次数超限"),
  
  USER_IDENTITY_VERIFICATION_FAILED("A0220", "用户身份校验失败"),
  
  USER_FINGERPRINT_VERIFICATION_FAILED("A0221", "用户指纹识别失败"),
  
  USER_FACE_VERIFICATION_FAILED("A0222", "用户面容识别失败"),
  
  USER_NOT_AUTHORIZED_BY_THIRTY_PARTY("A0223", "用户未获得第三方登录授权"),
  
  USER_LOGIN_EXPIRED("A0230", "用户登录已过期"),
  
  USER_CODE_VERIFICATION_FAILED("A0240", "用户验证码错误"),
  
  USER_INPUT_ERROR_CODE_OVERRUN("A0241", "用户验证码尝试次数超限");
  ```

- #### 用户注册异常枚举：`RegisterAbnormalEnum`

  ```java
  REGISTER_ERROR("A0100", "用户注册错误"),
  
  NOT_AGREE_PRIVACY_AGREEMENT("A0101", "用户未同意隐私协议"),
  
  RESTRICTED_COUNTRY_OR_REGION_OF_REGISTRATION("A0102", "注册国家或地区受限"),
  
  USERNAME_VERIFY_FAILED("A0110", "用户名校验失败"),
  
  USERNAME_ALREADY_EXISTS("A0111", "用户名已存在"),
  
  USERNAME_CONTAINS_SENSITIVE_WORDS("A0112", "用户名包含敏感词"),
  
  USERNAME_CONTAINS_SPECIAL_CHARACTERS("A0113", "用户名包含特殊字符"),
  
  PASSWORD_VERIFY_FAILED("A0120", "密码校验失败"),
  
  PASSWORD_LENGTH_NOT("A0121", "密码长度不够"),
  
  PASSWORD_NOT_STRONG("A0122", "密码强度不够"),
  
  CODE_INPUT_ERROR("A0130", "校验码输入错误"),
  
  SMS_CODE_INPUT_ERROR("A0131", "短信校验码输入错误"),
  
  EMAIL_CODE_INPUT_ERROR("A0132", "邮件校验码输入错误"),
  
  VOICE_CODE_INPUT_ERROR("A0133", "语音校验码输入错误"),
  
  USER_CERTIFICATE_ABNORMAL("A0140", "用户证件异常"),
  
  USER_CERTIFICATE_TYPE_NOT_SELECTED("A0141", "用户证件类型未选择"),
  
  MAINLAND_ID_CARD_VERIFY_ILLEGAL("A0142", "大陆身份证编号校验非法"),
  
  ILLEGAL_PASSPORT_VERIFY("A0143", "护照编号校验非法"),
  
  MILITARY_OFFICER_ID_VERIFY_ILLEGAL("A0144", "军官证编号校验非法"),
  
  BASIC_INFO_VERIFY_FAILED("A0150", "用户基本信息校验失败"),
  
  PHONE_FORMAT_VERIFY_FAILED("A0151", "手机格式校验失败"),
  
  ADDRESS_FORMAT_VERIFY_FAILED("A0152", "地址格式校验失败"),
  
  EMAIL_FORMAT_VERIFY_FAILED("A0153", "邮箱格式校验失败");
  ```

- #### 访问权限异常枚举：`AuthorityAbnormalEnum`

  ```java
  AUTHORITY_ABNORMAL("A0300", "访问权限异常"),
  
  UNAUTHORIZED("A0301", "访问未授权"),
  
  AUTHORIZING("A0302", "正在授权中"),
  
  AUTHORITY_REJECTED("A0303", "用户授权申请被拒绝"),
  
  BLOCKED_DUE_TO_PRIVACY_SETTINGS("A0310", "因访问对象隐私设置被拦截"),
  
  AUTHORITY_EXPIRED("A0311", "授权已过期"),
  
  NO_PERMISSION_TO_USE_API("A0312", "无权限使用 API"),
  
  ACCESS_BLOCKED("A0320", "用户访问被拦截"),
  
  BLACKLISTED_USER("A0321", "黑名单用户"),
  
  ACCOUNT_IS_FROZEN("A0322", "账号被冻结"),
  
  ILLEGAL_IP_ADDRESS("A0323", "非法 IP 地址"),
  
  GATEWAY_ACCESS_RESTRICTED("A0324", "网关访问受限"),
  
  BLACKLISTED_REGIONAL("A0325", "地域黑名单"),
  
  SERVICE_ARREARS("A0330", "服务已欠费"),
  
  USER_SIGNATURE_ABNORMAL("A0340", "用户签名异常"),
  
  RSA_SIGNATURE_ERROR("A0341", "RSA 签名错误");
  ```

> 其他异常枚举请参照 [阿里巴巴Java开发手册嵩山版](https://github.com/alibaba/p3c) 中枚举常量，如需自定义异常枚举包名与类名请参照上述信息



### 全局异常定义

> 包路径：`pro.haichuang.framework.base.exception`

- 全局自定义异常基类：`pro.haichuang.framework.base.exception.ApplicationException`
- 请求参数自定义异常：`pro.haichuang.framework.base.exception.client.RequestParamException`
- 请求服务自定义异常：`pro.haichuang.framework.base.exception.client.RequestServerException`
- 用户登录自定义异常：`pro.haichuang.framework.base.exception.client.LoginException`
- 用户注册自定义异常：`pro.haichuang.framework.base.exception.client.RegisterException`
- 访问权限自定义异常：`pro.haichuang.framework.base.exception.client.AuthorityException`
- 枚举非法论证异常：`pro.haichuang.framework.base.exception.EnumIllegalArgumentException`
- 第三方自定义异常：`pro.haichuang.framework.base.exception.ThirdPartyException`
- 堆栈自定义异常：`pro.haichuang.framework.base.exception.StackTraceException`



### 全局响应体

> 统一响应类：`pro.haichuang.framework.base.response.ResultVO`

- `VO`基类：`pro.haichuang.framework.base.response.vo.BaseVO`

  ```json
  {
      // 错误码
      "errorCode": "00000",
      // 错误信息
      "errorMessage": "OK",
      // 用户提示信息
      "userTip": "操作成功"
  }
  ```

- 单条数据`VO`：`pro.haichuang.framework.base.response.vo.SingleVO`

  ```json
  {
      // 错误码
      "errorCode": "00000",
      // 错误信息
      "errorMessage": "OK",
      // 用户提示信息
      "userTip": "操作成功",
      // 数据
      "data": java.lang.Object
  }
  ```



- 多条数据`VO`：`pro.haichuang.framework.base.response.vo.MultiVO`

  ```json
  {
      // 错误码
      "errorCode": "00000",
      // 错误信息
      "errorMessage": "OK",
      // 用户提示信息
      "userTip": "操作成功",
      // 数据
      "data": java.util.Collection(Default is [])
  }
  ```

- 分页数据`VO`：`pro.haichuang.framework.base.response.vo.PageVO`

  ```json
  {
      // 错误码
      "errorCode": "00000",
      // 错误信息
      "errorMessage": "OK",
      // 用户提示信息
      "userTip": "操作成功",
      // 分页详情
      "detail": pro.haichuang.framework.base.response.vo.PageDetailVO,
      // 数据
      "data": java.util.Collection(Default is [])
  }
  ```

- 分页详情数据`VO`：`pro.haichuang.framework.base.response.vo.PageDetailVO`

  ```json
  {
      // 页码
      "pageNo": 1,
      // 每页展示数量
      "pageSize": 10,
      // 总数
      "totalCount": 0,
  }
  ```



### 业务分页

> 分页常量：`pro.haichuang.framework.base.constant.PageConstant`
>
> 分页接口：`pro.haichuang.framework.base.page.Pageable`
>
> 分页`DTO`：`pro.haichuang.framework.base.page.PageDTO`
>
> 分页查询基类：`pro.haichuang.framework.base.query.PageQuery`

```java
/**
 * 返回数据库查询起始索引位置
 *
 * @return 数据库查询起始索引位置
 */
@ApiModelProperty(hidden = true)
@JsonIgnore
@JSONField(serialize = false, deserialize = false)
public int getDbPageNo() {
    return getPageNo() > 0 ? (getPageNo() - 1) * getPageSize() : 0;
}
```



### 工具类

> 包路径：`pro.haichuang.framework.base.util`

- 公共工具类（`pro.haichuang.framework.base.util.common`）

  - `IpUtils`（`IP`工具类）：获取客户端真实`IP`地址，由`Nginx`代理后需要使用此类进行获取客户端真实`IP`
  - `UUIDUtils`（`UUID`工具类）：用于生成不带符号的（固定长度）`UUID`字符串与当前线程唯一`UUID`（主要用于统一请求唯一标识）
  - `NullUtils`（空指针工具类）：封装于`java.util.Optional`，用于设置代码中空指针默认值，避免调用时出现空指针异常，常见于`Mybatis`数据查询结果、各类业务数据传输时使用
  - `ValidateUtils`（验证工具类）：主要用于业务参数校验并抛出异常，减少`if-else`代码沉余
  - `ProjectUtils`（项目工具类）：存在于`RunTime`生命周期时，封装各类常用操作
  - `ResponseUtils`（响应工具类）：主要用于自定义`HttpServletResponse`输出时使用（使用流进行输出，防止二次加载流异常）
- `JWT`工具类（`pro.haichuang.framework.base.util.jwt`）
  - `JwtPayload`（`JWT`载荷）：在`JWT`默认载荷的基础上拓展了业务字段
  - `JwtUtils`（`JWT`工具类）：提供了生成与解析`JWT`方法
  - `SecurityUtils`（授权信息工具类）：提供了获取当前登录用户`JWT`载荷方法

- `ModelMapper`工具类（`pro.haichuang.framework.base.util.modelmapper`）
  - `ModelMapperUtils`（实体映射工具类）：提供了获取默认与严格匹配模式`ModelMapper`实例方法



## 二、`ORM`使用说明

> 项目统一采用`MybatisPlus`进行开发



### 核心配置

```yaml
haichuang:
  mybatis:
    // 是否启用Mybatis自动配置，默认值为 [true]
    enable: true
```



### `DO`基类

> 所有实体类必须继承`pro.haichuang.framework.mybatis.domain.BaseDO`



### `Service`基类

> 所有实体映射`Service`层必须继承`pro.haichuang.framework.mybatis.service.BaseService`



### `ServiceImpl`基类

> 所有实体映射`ServiceImpl`层必须继承`pro.haichuang.framework.mybatis.service.BaseServiceImpl`



### `MapperXml`文件

> `MapperXml`文件存放文件夹路径`pro/haichuang/framework/项目Code/mapper`
>
> 同一实体相关`DO`、`DTO`等写在当前`MapperXml`文件中，禁止跨`MapperXml`编写`resultMap`



### 代码生成器

> 核心实现：`pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService`

#### 代码生成器相关配置

> 基本配置：`pro.haichuang.framework.mybatis.generate.config.CodeBasicConfig`
>
> 数据源配置：`pro.haichuang.framework.mybatis.generate.config.CodeDataSourceConfig`
>
> 包配置：`pro.haichuang.framework.mybatis.generate.config.CodePackageConfig`



### 代码示例

```java
/**
 * CodeGenerateTest
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SpringBootTest(classes = ServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodeGenerateTest {

    @Autowired
    private MybatisGenerateCodeService mybatisGenerateCodeService;

    /**
     * 简单配置生成
     */
    @Test
    void simpleSettingGenerate() {
        CodeBasicConfig codeBasicConfig = new CodeBasicConfig();
        // 作者
        // 默认为 [JiYinchuan]
        codeBasicConfig.setAuthor("JiYinchuan");

        CodeDataSourceConfig codeDataSourceConfig = new CodeDataSourceConfig();
        // 驱动连接的URL, 须指定
        // 例如 [jdbc:mysql://127.0.0.1:3306/data_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowMutiQueries=true]
        codeDataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/data_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowMutiQueries=true");
        // 数据库连接用户名, 须指定
        // 例如 [root]
        codeDataSourceConfig.setUsername("root");
        // 数据库连接密码, 须指定
        // 例如 [123456]
        codeDataSourceConfig.setPassword("123456");
        // 统一表前缀, 可为空
        // 例如 [hc_]
        codeDataSourceConfig.setTablePrefix(null);
        // 输出包含表, 可为空, 为空时则输出所有表
        codeDataSourceConfig.setInclude();

        CodePackageConfig codePackageConfig = new CodePackageConfig();
        // 父包模块名, 须指定
        // 此处填写项目Code(项目代号首字母全小写+数字)
        codePackageConfig.setParentModelName("xmdh01");

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
        // 默认为 [JiYinchuan]
        codeBasicConfig.setAuthor("JiYinchuan");
        // 版本号
        // 默认为 [1.0]
        codeBasicConfig.setVersion("1.0");
        // 输出包类型
        // 默认为 [CodeBasicConfig.OutputType.ALL]
        codeBasicConfig.setOutputType(CodeBasicConfig.OutputType.ALL);
        // 是否开启Swagger注解支持
        // 默认为 [true]
        codeBasicConfig.setEnableSwagger(true);

        CodeDataSourceConfig codeDataSourceConfig = new CodeDataSourceConfig();
        // 驱动名
        // 默认为 [com.mysql.cj.jdbc.Driver]
        codeDataSourceConfig.setDriver("com.mysql.cj.jdbc.Driver");
        // 驱动连接的URL, 须指定
        // 例如 [jdbc:mysql://127.0.0.1:3306/data_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowMutiQueries=true]
        codeDataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/data_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowMutiQueries=true");
        // 数据库连接用户名, 须指定
        // 例如 [root]
        codeDataSourceConfig.setUsername("root");
        // 数据库连接密码, 须指定
        // 例如 [123456]
        codeDataSourceConfig.setPassword("123456");
        // 统一表前缀, 可为空
        // 例如 [hc_]
        codeDataSourceConfig.setTablePrefix("hc_");
        // 输出包含表, 可为空, 为空时则输出所有表
        codeDataSourceConfig.setInclude();

        CodePackageConfig codePackageConfig = new CodePackageConfig();
        // 输出包名
        // 默认为 [pro.haichuang.framework.service]
        codePackageConfig.setOutputPackage("pro.haichuang.framework.service");
        // 父包模块名, 须指定
        // 此处填写项目代号缩写(项目代号首字母全小写+数字)
        codePackageConfig.setParentModelName("xmdh01");
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
```



## 三、`Redis`使用说明

### 核心配置

```yaml
haichuang:
  redis:
    // 是否启用Redis自动配置，默认值为 [true]
  	enable: true
```



### 核心注解

> 包路径：`pro.haichuang.framework.redis.config.annotation`

- `@EnableRequestRepeatValidate`：启动重复请求拦截器，需要在 `Controller` 接口上进行标注`pro.haichuang.framework.redis.config.annotation.RepeatRequestValid`注解



### `RedisService`接口

> 默认实现：`pro.haichuang.framework.redis.service.DefaultRedisServiceImpl`



## 四、`SDK`使用说明

### 阿里云`OSS`

> 工具类：`pro.haichuang.framework.sdk.aliyunoss.util.AliYunOssUtils`



### 阿里云`SMS`

> 工具类：`pro.haichuang.framework.sdk.aliyunsms.util.AliYunSmsUtils`



### 微信公众号

> `DTO`包：`pro.haichuang.framework.sdk.wxmp.dto`
>
> 工具类：`pro.haichuang.framework.sdk.wxmp.util.MpUtils`