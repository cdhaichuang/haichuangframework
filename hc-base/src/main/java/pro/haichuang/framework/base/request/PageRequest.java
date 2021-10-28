package pro.haichuang.framework.base.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.constant.PageConstant;

import java.io.Serializable;

/**
 * 分页查询基类
 *
 * <p>该类为分页查询基类, 所有带分页参数的请求实体均必须继承此类, 此类也可以单独进行使用
 * <p>已默认提供一个基础分页查询实体 {@link SimplePageRequest}
 *
 * @author JiYinchuan
 * @see PageConstant
 * @since 1.1.0.211021
 */
@ApiModel("分页查询基类")
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 5161339755291232348L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private long pageNo;

    /**
     * 每页展示数量
     */
    @ApiModelProperty(value = "每页展示数量", example = "10")
    private long pageSize;

    /**
     * 构造器
     *
     * @since 1.1.0.211021
     */
    public PageRequest() {
        this.setPageNo(PageConstant.DEFAULT_PAGE_NO);
        this.setPageSize(PageConstant.DEFAULT_PAGE_SIZE);
    }

    /**
     * 构造器
     *
     * @param pageNo   页码
     * @param pageSize 每页展示数量
     * @since 1.1.0.211021
     */
    public PageRequest(long pageNo, long pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    /**
     * 设置页码
     *
     * @param pageNo 页码
     * @since 1.1.0.211021
     */
    public void setPageNo(long pageNo) {
        this.pageNo = Math.max(pageNo, 1);
    }

    /**
     * 设置每页展示数量
     *
     * @param pageSize 每页展示数量
     * @since 1.1.0.211021
     */
    public void setPageSize(long pageSize) {
        this.pageSize = Math.max(pageSize, 0);
    }

    /**
     * 返回数据库查询起始索引位置
     *
     * @return 数据库查询起始索引位置
     * @since 1.1.0.211021
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    public long getDbPageNo() {
        return getPageNo() > 0 ? (getPageNo() - 1) * getPageSize() : 0;
    }

    public long getPageNo() {
        return pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
