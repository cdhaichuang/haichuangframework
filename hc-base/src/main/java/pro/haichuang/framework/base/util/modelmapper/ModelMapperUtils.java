package pro.haichuang.framework.base.util.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * ModelMapper映射工具类
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class ModelMapperUtils {

    /**
     * 获取默认ModelMapper实例
     * 默认匹配规则为 {@link MatchingStrategies#STANDARD}
     *
     * @return ModelMapper实例
     */
    public static ModelMapper get() {
        return new ModelMapper();
    }

    /**
     * 获取严格匹配模式的ModelMapper实例
     *
     * @return ModelMapper实例
     */
    public static ModelMapper getStrictModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
