package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 多条数据VO
 *
 * <p>该类为多条数据VO, 用于多条数据返回的情况下使用, 多条数据+分页时请使用 {@link PageVO},
 * 使用时必须使用 {@link pro.haichuang.framework.base.response.ResultVO} 中相关方法进行返回
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see pro.haichuang.framework.base.response.ResultVO
 * @since 1.0.0.211009
 */
@ApiModel("MultiVO")
public final class MultiVO<T> extends BaseVO implements Serializable {
    private static final long serialVersionUID = -9191522053300516216L;

    /**
     * 多条数据
     */
    private Collection<T> data;

    public MultiVO(BaseEnum baseEnum, Collection<T> data) {
        super(baseEnum);
        setData(data);
    }

    public MultiVO(BaseEnum baseEnum, Collection<T> data, String userTip) {
        super(baseEnum, userTip);
        setData(data);
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data == null ? Collections.emptyList() : data;
    }

    @Override
    public String toString() {
        return "MultiVO{" +
                "data=" + data +
                "} " + super.toString();
    }
}
