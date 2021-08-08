package pro.haichuang.framework.mybatis.exception;

import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.mybatis.enums.error.MybatisGenerateErrorEnum;

/**
 * Mybatis代码生成自定义异常
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see MybatisGenerateErrorEnum
 */
public class MybatisGenerateErrorApplication extends ApplicationException {
    private static final long serialVersionUID = 8463634516603437883L;

    public MybatisGenerateErrorApplication(MybatisGenerateErrorEnum mybatisServiceErrorEnum) {
        super(mybatisServiceErrorEnum);
    }

    public MybatisGenerateErrorApplication(MybatisGenerateErrorEnum mybatisServiceErrorEnum, @Nullable String userTip) {
        super(mybatisServiceErrorEnum, userTip);
    }
}
