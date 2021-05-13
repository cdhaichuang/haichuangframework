package pro.haichuang.framework.base.config.interceptor;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pro.haichuang.framework.base.config.annotation.LogSave;

/**
 * 保存日志
 *
 * @author JiYinchuan
 * @version 1.0
 */
public abstract class AbstractLogSave {

    /**
     * 保存日志逻辑
     *
     * @param logSave        {@link pro.haichuang.framework.base.config.annotation.LogSave}
     * @param api            {@link Api}
     * @param apiOperation   {@link ApiOperation}
     * @param clientIp       客户端IP真实地址
     * @param fullMethodName 完整方法名
     * @param userId         用户ID
     * @param executionTime  执行时间
     */
    public abstract void saveLog(LogSave logSave, Api api, ApiOperation apiOperation, String clientIp, String fullMethodName, Long userId, long executionTime);

}
