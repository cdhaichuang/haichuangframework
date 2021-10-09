package pro.haichuang.framework.base.response;

import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.success.SuccessEnum;
import pro.haichuang.framework.base.page.Pageable;
import pro.haichuang.framework.base.response.vo.BaseVO;
import pro.haichuang.framework.base.response.vo.MultiVO;
import pro.haichuang.framework.base.response.vo.PageVO;
import pro.haichuang.framework.base.response.vo.SingleVO;

import java.io.Serializable;
import java.util.Collection;

/**
 * 全局响应VO类
 *
 * <p>该类为全局响应VO, 所有控制器返回必须使用此类进行返回, 返回值类型参考 {@link BaseVO} 下所有实现
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see BaseVO
 * @see SingleVO
 * @see MultiVO
 * @see PageVO
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public final class ResultVO implements Serializable {
    private static final long serialVersionUID = -7437022394115877815L;

    // ------------------------- OK -------------------------

    public static BaseVO ok() {
        return new BaseVO(SuccessEnum.OK);
    }

    public static BaseVO ok(String userTip) {
        return new BaseVO(SuccessEnum.OK, userTip);
    }

    public static BaseVO ok(Boolean operationResult) {
        return new BaseVO(SuccessEnum.OK, String.valueOf(operationResult));
    }

    public static <T> SingleVO<T> okOfSingle(T data) {
        return new SingleVO<>(SuccessEnum.OK, data);
    }

    public static <T> SingleVO<T> okOfSingle(T data, String userTip) {
        return new SingleVO<>(SuccessEnum.OK, data, userTip);
    }

    public static <T> MultiVO<T> okOfMulti(Collection<T> data) {
        return new MultiVO<>(SuccessEnum.OK, data);
    }

    public static <T> MultiVO<T> okOfMulti(Collection<T> data, String userTip) {
        return new MultiVO<>(SuccessEnum.OK, data, userTip);
    }

    public static <T> PageVO<T> okOfPage(Pageable<T> simplePage) {
        return new PageVO<>(SuccessEnum.OK, simplePage.convertToPageDetailVO(), simplePage.getContent());
    }

    public static <T> PageVO<T> okOfPage(Pageable<T> simplePage, String userTip) {
        return new PageVO<>(SuccessEnum.OK, simplePage.convertToPageDetailVO(), simplePage.getContent(), userTip);
    }

    // ------------------------- other -------------------------

    public static BaseVO other(BaseEnum baseEnum) {
        return new BaseVO(baseEnum);
    }

    public static BaseVO other(BaseEnum baseEnum, String userTip) {
        return new BaseVO(baseEnum, userTip);
    }

    public static <T> SingleVO<T> otherOfSingle(BaseEnum baseEnum, T data) {
        return new SingleVO<>(baseEnum, data);
    }

    public static <T> SingleVO<T> otherOfSingle(BaseEnum baseEnum, T data, String userTip) {
        return new SingleVO<>(baseEnum, data, userTip);
    }

    public static <T> MultiVO<T> otherOfMulti(BaseEnum baseEnum, Collection<T> data) {
        return new MultiVO<>(baseEnum, data);
    }

    public static <T> MultiVO<T> otherOfMulti(BaseEnum baseEnum, Collection<T> data, String userTip) {
        return new MultiVO<>(baseEnum, data, userTip);
    }

    public static <T> PageVO<T> otherOfPage(BaseEnum baseEnum, Pageable<T> simplePage) {
        return new PageVO<>(baseEnum, simplePage.convertToPageDetailVO(), simplePage.getContent());
    }

    public static <T> PageVO<T> otherOfPage(BaseEnum baseEnum, Pageable<T> simplePage, String userTip) {
        return new PageVO<>(baseEnum, simplePage.convertToPageDetailVO(), simplePage.getContent(), userTip);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
