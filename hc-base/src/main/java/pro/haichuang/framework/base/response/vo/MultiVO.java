package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 多条数据VO
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@ApiModel("MultiVO")
public class MultiVO<T> extends BaseVO implements Serializable {
    private static final long serialVersionUID = 8867565885729989450L;

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
