package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.io.Serializable;

/**
 * 单条数据VO
 *
 * @author JiYinchuan
 * @version 1.0
 */
@ApiModel("SingleVO")
public class SingleVO<T> extends BaseVO implements Serializable {
    private static final long serialVersionUID = -791674624589468027L;

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
