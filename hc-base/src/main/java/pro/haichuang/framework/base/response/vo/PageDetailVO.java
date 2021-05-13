package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.constant.PageConstant;

import java.io.Serializable;

/**
 * 分页详情VO
 *
 * @author JiYinchuan
 * @version 1.0
 */
@ApiModel("PageDetailVO")
public class PageDetailVO implements Serializable {
    private static final long serialVersionUID = 4616289028933242958L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNo;

    /**
     * 每页展示数量
     */
    @ApiModelProperty(value = "每页展示数量", example = "10")
    private Integer pageSize;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", example = "0")
    private Long totalCount;

    public PageDetailVO(Integer pageNo, Integer pageSize, Long totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
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
