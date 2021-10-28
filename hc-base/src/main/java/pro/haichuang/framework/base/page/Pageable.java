package pro.haichuang.framework.base.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pro.haichuang.framework.base.response.vo.PageDetailVO;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 分页接口
 *
 * <p>该接口为业务分页接口, 项目开发中需要用其子类
 * <p>已默认内置 {@link PageDTO}
 *
 * @author JiYinchuan
 * @see PageDTO
 * @since 1.1.0.211021
 */
public interface Pageable<T> {

    /**
     * 页码
     *
     * @return 页码
     * @since 1.1.0.211021
     */
    long getPageNo();

    /**
     * 每页记录数
     *
     * @return 每页记录数
     * @since 1.1.0.211021
     */
    long getPageSize();

    /**
     * 总记录数
     *
     * @return 总记录数
     * @since 1.1.0.211021
     */
    long getTotalCount();

    /**
     * 总页数
     *
     * @return 总页数
     * @since 1.1.0.211021
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    long getTotalPage();

    /**
     * 是否第一页
     *
     * @return 是否第一页
     * @since 1.1.0.211021
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    boolean isFirstPage();

    /**
     * 是否最后一页
     *
     * @return 是否最后一页
     * @since 1.1.0.211021
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    boolean isLastPage();

    /**
     * 返回下页的页号
     *
     * @return 返回下页的页号
     * @since 1.1.0.211021
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    long getNextPage();

    /**
     * 返回上页的页号
     *
     * @return 返回上页的页号
     * @since 1.1.0.211021
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    long getPrePage();

    /**
     * 获取偏移量
     *
     * @return 偏移量
     * @since 1.1.0.211021
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    default long offsetPageNo() {
        return getPageNo() > 0 ? (getPageNo() - 1) * getPageSize() : 0;
    }

    /**
     * 获取分页数据
     *
     * @return 分页数据
     * @since 1.1.0.211021
     */
    Collection<T> getContent();

    /**
     * 获取分页详情VO
     *
     * @return 分页详情VO
     * @since 1.1.0.211021
     */
    default PageDetailVO convertToPageDetailVO() {
        return new PageDetailVO(getPageNo(), getPageSize(), getTotalCount());
    }

    /**
     * 计算时间范围同一天结束时间
     * 主要用于数据库日期范围查询
     *
     * @param endDateTime 结束时间
     * @return 返回时间范围同一天结束时间
     * @since 1.1.0.211021
     */
    static LocalDateTime formatEndDate(LocalDateTime endDateTime) {
        return endDateTime.plusDays(1);
    }
}
