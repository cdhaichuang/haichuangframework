package pro.haichuang.framework.base.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分页接口
 *
 * <p>该接口为业务分页接口, 项目开发中需要用其子类
 * <p>已默认内置 {@link PageDTO}
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see PageDTO
 */
public interface Pageable {

    /**
     * 页码
     *
     * @return 页码
     */
    int getPageNo();

    /**
     * 每页记录数
     *
     * @return 每页记录数
     */
    int getPageSize();

    /**
     * 总记录数
     *
     * @return 总记录数
     */
    long getTotalCount();

    /**
     * 总页数
     *
     * @return 总页数
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    int getTotalPage();

    /**
     * 是否第一页
     *
     * @return 是否第一页
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    boolean isFirstPage();

    /**
     * 是否最后一页
     *
     * @return 是否最后一页
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    boolean isLastPage();

    /**
     * 返回下页的页号
     *
     * @return 返回下页的页号
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    int getNextPage();

    /**
     * 返回上页的页号
     *
     * @return 返回上页的页号
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    int getPrePage();

    /**
     * 获取偏移量
     *
     * @return 偏移量
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    default int offsetPageNo() {
        return getPageNo() > 0 ? (getPageNo() - 1) * getPageSize() : 0;
    }
}
