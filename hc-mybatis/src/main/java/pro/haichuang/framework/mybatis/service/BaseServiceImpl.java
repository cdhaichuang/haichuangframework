package pro.haichuang.framework.mybatis.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pro.haichuang.framework.mybatis.domain.BaseDO;

/**
 * 封装MybatisServiceImpl
 *
 * <p>该类为 {@link BaseService} 实现类, 与 {@link BaseService} 联合使用,
 * 所有 [DO] 层 [Service] 实现必须继承该类, 以替代 [Mybatis] 中的 {@link ServiceImpl}
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see BaseService
 * @since 1.0.0.211014
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseDO> extends ServiceImpl<M, T> implements BaseService<T> {

}
