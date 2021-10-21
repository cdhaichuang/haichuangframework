package pro.haichuang.framework.base.validate;

/**
 * 验证分组
 *
 * <p>该类用于 {@link org.springframework.validation.annotation.Validated @Validated} 注解验证时使用
 * <p>在 {@link javax.validation.constraints} 包下的注解中加入分组,
 * 同时在 {@link org.springframework.validation.annotation.Validated @Validated} 中加入同一分组类,
 * 则可以只验证指定分组(当指定了分组后不会验证默认没有分组的数据)
 *
 * @author JiYinchuan
 * @see org.springframework.validation.annotation.Validated
 * @see javax.validation.constraints
 * @since 1.1.0.211021
 */
public class Group {

    /**
     * 分组-查询
     *
     * @since 1.1.0.211021
     */
    public interface Query {

    }

    /**
     * 分组-新增
     *
     * @since 1.1.0.211021
     */
    public interface Insert {

    }

    /**
     * 分组-更新
     *
     * @since 1.1.0.211021
     */
    public interface Update {

    }

    /**
     * 分组-删除
     *
     * @since 1.1.0.211021
     */
    public interface Delete {

    }
}
