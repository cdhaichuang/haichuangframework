package pro.haichuang.framework.base.response;

import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.abnormal.SuccessEnum;
import pro.haichuang.framework.base.page.PageDTO;
import pro.haichuang.framework.base.response.vo.BaseVO;
import pro.haichuang.framework.base.response.vo.MultiVO;
import pro.haichuang.framework.base.response.vo.PageVO;
import pro.haichuang.framework.base.response.vo.SingleVO;

import java.io.Serializable;
import java.util.Collection;

/**
 * 全局响应工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class ResultVO implements Serializable {
    private static final long serialVersionUID = 2830894283845180061L;

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

    public static <T> PageVO<T> okOfPage(PageDTO<T> simplePage) {
        return new PageVO<>(SuccessEnum.OK, simplePage.convertToPageDetailVO(), simplePage.getContent());
    }

    public static <T> PageVO<T> okOfPage(PageDTO<T> simplePage, String userTip) {
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

    public static <T> PageVO<T> otherOfPage(BaseEnum baseEnum, PageDTO<T> simplePage) {
        return new PageVO<>(baseEnum, simplePage.convertToPageDetailVO(), simplePage.getContent());
    }

    public static <T> PageVO<T> otherOfPage(BaseEnum baseEnum, PageDTO<T> simplePage, String userTip) {
        return new PageVO<>(baseEnum, simplePage.convertToPageDetailVO(), simplePage.getContent(), userTip);
    }
}
