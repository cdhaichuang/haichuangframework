package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.success.SuccessEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 分页VO
 *
 * <p>该类为分页VO, 用于分页数据返回的情况下使用, 分页详情数据请参考 {@link PageDetailVO} 说明,
 * 使用时必须使用 {@link pro.haichuang.framework.base.response.ResultVO} 中相关方法进行返回
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see PageDetailVO
 * @see pro.haichuang.framework.base.response.ResultVO
 */
@ApiModel("PageVO")
public class PageVO<T> extends BaseVO implements Serializable {
    private static final long serialVersionUID = 2207315013077490485L;

    /**
     * 分页详情数据
     */
    @ApiModelProperty("PageDetail")
    private PageDetailVO detail;

    /**
     * 分页数据
     */
    private Collection<T> data;

    public PageVO() {
        super(SuccessEnum.OK);
        data = Collections.emptyList();
    }

    public PageVO(BaseEnum baseEnum, PageDetailVO detail, Collection<T> data) {
        super(baseEnum);
        setDetail(detail);
        setData(data);
    }

    public PageVO(BaseEnum baseEnum, PageDetailVO detail, Collection<T> data, String userTip) {
        super(baseEnum, userTip);
        setDetail(detail);
        setData(data);
    }

    public PageDetailVO getDetail() {
        return detail;
    }

    public void setDetail(PageDetailVO detail) {
        this.detail = detail;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data == null ? Collections.emptyList() : data;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "detail=" + detail +
                ", data=" + data +
                "} " + super.toString();
    }
}
