package pro.haichuang.framework.base.page;

import org.springframework.lang.NonNull;
import pro.haichuang.framework.base.constant.PageConstant;
import pro.haichuang.framework.base.request.PageRequest;
import pro.haichuang.framework.base.response.vo.PageDetailVO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * 分页DTO
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class PageDTO<T> implements Pageable, Serializable {
    private static final long serialVersionUID = -110663046876892030L;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 总数
     */
    private Long totalCount;

    /**
     * 数据
     */
    private Collection<T> content;

    /**
     * 构造器
     */
    public PageDTO() {
        this.pageNo = PageConstant.DEFAULT_PAGE_NO;
        this.pageSize = PageConstant.DEFAULT_PAGE_SIZE;
        this.totalCount = PageConstant.DEFAULT_TOTAL_COUNT;
    }

    /**
     * 构造器
     *
     * @param pageNo   页码
     * @param pageSize 每页数量
     */
    public PageDTO(int pageNo, int pageSize) {
        this();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        adjustPageNo();
    }

    /**
     * 构造器
     *
     * @param pageNo     页码
     * @param pageSize   每页数量
     * @param totalCount 总数
     * @param content    数据
     */
    public PageDTO(int pageNo, int pageSize, long totalCount, @NonNull Collection<T> content) {
        this(pageNo, pageSize);
        this.totalCount = totalCount;
        this.content = content;
        adjustPageNo();
    }

    /**
     * 构造器
     *
     * @param pageRequest 分页参数
     */
    public PageDTO(@NonNull PageRequest pageRequest) {
        this();
        this.pageNo = pageRequest.getPageNo();
        this.pageSize = pageRequest.getPageSize();
    }

    /**
     * 构造器
     *
     * @param pageRequest  分页参数
     * @param content    数据
     * @param totalCount 总数
     */
    public PageDTO(@NonNull PageRequest pageRequest, long totalCount, @NonNull Collection<T> content) {
        this(pageRequest);
        this.totalCount = totalCount;
        this.content = content;
    }

    /**
     * 构造器
     *
     * @param pageDetailVO 分页详情
     * @param content      数据
     */
    public PageDTO(@NonNull PageDetailVO pageDetailVO, @NonNull Collection<T> content) {
        this(pageDetailVO.getPageNo(), pageDetailVO.getPageSize(), pageDetailVO.getTotalCount(), content);
        adjustPageNo();
    }

    /**
     * 获取空分页DTO
     *
     * @param <T> 分页数据类型
     * @return 空分页DTO
     */
    public static <T> PageDTO<T> empty() {
        return new PageDTO<>();
    }

    @Override
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNo() {
        return pageNo;
    }

    @Override
    public int getTotalPage() {
        int totalPage = (int) (totalCount / pageSize);
        if (totalPage == 0 || totalCount % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }

    @Override
    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    @Override
    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    @Override
    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    @Override
    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    public PageDTO<T> setTotalCount(long totalCount) {
        this.totalCount = Math.max(totalCount, 0);
        return this;
    }

    public PageDTO<T> setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = PageConstant.DEFAULT_MAX_PAGE_SIZE;
        } else {
            this.pageSize = Math.min(pageSize, PageConstant.DEFAULT_MAX_PAGE_SIZE);
        }
        return this;
    }

    public PageDTO<T> setPageNo(int pageNo) {
        this.pageNo = Math.max(pageNo, 1);
        adjustPageNo();
        return this;
    }

    public Collection<T> getContent() {
        return content;
    }

    public PageDTO<T> setContent(Collection<T> content) {
        this.content = content == null ? Collections.emptyList() : content;
        return this;
    }

    /**
     * 调整页码，使不超过最大页数
     */
    public void adjustPageNo() {
        if (pageNo == 1) {
            return;
        }
        int totalPage = getTotalPage();
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }
    }

    /**
     * 获取分页详情VO
     *
     * @return 分页详情VO
     */
    public PageDetailVO convertToPageDetailVO() {
        return new PageDetailVO(getPageNo(), getPageSize(), getTotalCount());
    }

    /**
     * 计算时间范围同一天结束时间
     * 主要用于数据库日期范围查询
     *
     * @param beginDateTime 开始时间
     * @param endDateTime   结束时间
     * @return 返回时间范围同一天结束时间
     */
    @NonNull
    public static LocalDateTime formatEndDate(@NonNull LocalDateTime beginDateTime, @NonNull LocalDateTime endDateTime) {
        return endDateTime.plusDays(1);
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", content=" + content +
                '}';
    }
}
