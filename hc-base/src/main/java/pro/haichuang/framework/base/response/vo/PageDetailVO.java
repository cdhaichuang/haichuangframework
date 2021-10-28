package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.constant.PageConstant;

import java.io.Serializable;

/**
 * 分页详情VO
 *
 * <p>该类为 {@link PageVO} 中分页详情数据抽离, 禁止直接使用此类用于返回,
 * 需要配合 {@link PageVO}, 并通过 {@link pro.haichuang.framework.base.response.ResultVO} 进行返回
 *
 * @author JiYinchuan
 * @see PageVO
 * @since 1.1.0.211021
 */
@ApiModel("PageDetailVO")
public final class PageDetailVO implements Serializable {
    private static final long serialVersionUID = -3679338095395261866L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = PageConstant.DEFAULT_PAGE_NO_STRING)
    private Long pageNo;

    /**
     * 每页展示数量
     */
    @ApiModelProperty(value = "每页展示数量", example = PageConstant.DEFAULT_PAGE_SIZE_STRING)
    private Long pageSize;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", example = PageConstant.DEFAULT_TOTAL_COUNT_STRING)
    private Long totalCount;

    public PageDetailVO(long pageNo, long pageSize, long totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageDetailVO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                '}';
    }
}
