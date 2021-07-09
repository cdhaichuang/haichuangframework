package pro.haichuang.framework.mybatis.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pro.haichuang.framework.mybatis.domain.BaseDO;

/**
 * 封装MybatisServiceImpl
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseDO> extends ServiceImpl<M, T> implements BaseService<T> {

}
