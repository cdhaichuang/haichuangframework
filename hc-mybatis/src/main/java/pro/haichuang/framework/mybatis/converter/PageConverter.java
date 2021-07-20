package pro.haichuang.framework.mybatis.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pro.haichuang.framework.base.page.PageDTO;
import pro.haichuang.framework.base.util.modelmapper.ModelMapperUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页转换器
 *
 * <p>该类主要用于将Mybatis中 {@link IPage} 转换为 {@link PageDTO}</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class PageConverter {

    /**
     * 将Mybatis中的Page转换为PageDTO
     *
     * @param iPage Mybatis{@link IPage}
     * @param <T>   对象类型
     * @return PageDTO
     */
    public static <T> PageDTO<T> converter(IPage<T> iPage) {
        return new PageDTO<>((int) iPage.getCurrent(), (int) iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 将Mybatis中的Page转换为PageDTO
     *
     * @param iPage   Mybatis中的 {@link IPage}
     * @param toClass 转换对象Class
     * @param <T>     原始对象类型
     * @param <R>     转换对象类型
     * @return PageDTO
     */
    public static <T, R> PageDTO<R> converter(IPage<T> iPage, Class<R> toClass) {
        return new PageDTO<>((int) iPage.getCurrent(), (int) iPage.getSize(), iPage.getTotal(),
                iPage.getRecords().stream().map(record ->
                        ModelMapperUtils.getStrictModelMapper().map(record, toClass)).collect(Collectors.toList()));
    }

    /**
     * @param iPage    Mybatis中的 {@link IPage}
     * @param function 转换对象的Function
     * @param <T>      原始对象类型
     * @param <R>      转换对象类型
     * @return PageDTO
     */
    public static <T, R> PageDTO<R> converter(IPage<T> iPage, Function<T, R> function) {
        return new PageDTO<>((int) iPage.getCurrent(), (int) iPage.getSize(), iPage.getTotal(),
                iPage.getRecords().stream().map(function).collect(Collectors.toList()));
    }
}
