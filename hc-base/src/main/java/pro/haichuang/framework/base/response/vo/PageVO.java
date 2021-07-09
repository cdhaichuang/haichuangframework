package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.error.client.SuccessEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 分页VO
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@ApiModel("PageVO")
public class PageVO<T> extends BaseVO implements Serializable {
    private static final long serialVersionUID = 5452051337130717431L;

    /**
     * 分页详情返回
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
