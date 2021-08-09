package pro.haichuang.framework.base.config.interceptor;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pro.haichuang.framework.base.annotation.LogSave;

/**
 * 保存日志抽象类
 *
 * <p>该类为实现请求参数持久化的抽象类, 需要自定义类实现该类, 进行指定具体持久化逻辑
 * <p>注意: 该类中 {@link #saveLog(LogSave, Api, ApiOperation, String, String, Long, long)} 方法只有在标注了
 * {@link pro.haichuang.framework.base.annotation.EnableLogSave @EnableLogSave} 注解和在对应的方法上标注
 * {@link LogSave @LogSave} 注解才会生效
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.base.annotation.EnableLogSave
 * @see LogSave
 * @since 1.0.0
 */
public abstract class AbstractLogSave {

    /**
     * 保存日志逻辑
     *
     * @param logSave        {@link pro.haichuang.framework.base.annotation.LogSave @LogSave} 注解
     * @param api            {@link Api @Api} 注解
     * @param apiOperation   {@link ApiOperation @ApiOperation} 注解
     * @param clientIp       客户端IP真实地址
     * @param fullMethodName 完整方法名
     * @param userId         用户ID
     * @param executionTime  执行时间
     */
    public abstract void saveLog(LogSave logSave, Api api, ApiOperation apiOperation, String clientIp, String fullMethodName, Long userId, long executionTime);

}
