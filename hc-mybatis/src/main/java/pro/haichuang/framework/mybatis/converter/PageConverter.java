package pro.haichuang.framework.mybatis.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pro.haichuang.framework.base.page.PageDTO;
import pro.haichuang.framework.base.util.modelmapper.ModelMapperUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页转换器
 *
 * <p>该类主要用于将 [Mybatis] 中 {@link IPage} 转换为 {@link PageDTO}
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
public class PageConverter {

    /**
     * 将Mybatis中的Page转换为PageDTO
     *
     * <p>该方法转换结果数据对象类型与 {@link IPage} 中一致
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
     * <p>该方法转换结果数据对象类型为 {@code toClass} 类型(根据反射进行严格转换)
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
     * 将Mybatis中的Page转换为PageDTO
     *
     * <p>该方法可以自定义转换 {@link IPage} 中的数据(原始数据与目标数据字段名称或类型不一致时)
     * <hr>
     * Example:
     * <pre>
     *     PageConverter.converter(iPage, originData -&gt; {
     *         // Do Something And Return
     *     });
     * </pre>
     *
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
