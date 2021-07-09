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
 * @author JiYinchuan
 * @version 1.0.0
 */
@ApiModel("分页查询基类")
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 9011320097226993676L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private int pageNo;

    /**
     * 每页展示数量
     */
    @ApiModelProperty(value = "每页展示数量", example = "10")
    private int pageSize;

    /**
     * 构造器
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
     */
    public PageRequest(int pageNo, int pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    /**
     * 设置页码
     *
     * @param pageNo 页码
     */
    public void setPageNo(int pageNo) {
        this.pageNo = Math.max(pageNo, 1);
    }

    /**
     * 设置每页展示数量
     *
     * @param pageSize 每页展示数量
     */
    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(pageSize, 0);
    }

    /**
     * 返回数据库查询起始索引位置
     *
     * @return 数据库查询起始索引位置
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    public int getDbPageNo() {
        return getPageNo() > 0 ? (getPageNo() - 1) * getPageSize() : 0;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
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
