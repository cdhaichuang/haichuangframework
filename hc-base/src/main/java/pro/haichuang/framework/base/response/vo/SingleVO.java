package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.io.Serializable;

/**
 * 单条数据VO
 *
 * <p>该类为单条数据VO, 用于单条数据返回的情况下使用,
 * 使用时必须使用 {@link pro.haichuang.framework.base.response.ResultVO} 中相关方法进行返回
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see pro.haichuang.framework.base.response.ResultVO
 * @since 1.0.0.211009
 */
@ApiModel("SingleVO")
public final class SingleVO<T> extends BaseVO implements Serializable {
    private static final long serialVersionUID = -3162181461276163016L;

    /**
     * 数据
     */
    private T data;

    public SingleVO(BaseEnum baseEnum, T data) {
        super(baseEnum);
        this.data = data;
    }

    public SingleVO(BaseEnum baseEnum, T data, String userTip) {
        super(baseEnum, userTip);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SingleVO{" +
                "data=" + data +
                "} " + super.toString();
    }
}
