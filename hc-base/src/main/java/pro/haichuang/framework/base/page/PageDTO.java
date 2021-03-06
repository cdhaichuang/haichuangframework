package pro.haichuang.framework.base.page;

import pro.haichuang.framework.base.constant.PageConstant;
import pro.haichuang.framework.base.request.PageRequest;
import pro.haichuang.framework.base.response.vo.PageDetailVO;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页DTO
 *
 * <p>该类为分页DTO, {@link Pageable} 的默认实现, 用于项目中所有分页相关功能数据传输, 原则上所有分页相关功能均使用此类
 *
 * @author JiYinchuan
 * @see Pageable
 * @since 1.1.0.211021
 */
public class PageDTO<T> implements Pageable<T>, Serializable {
    private static final long serialVersionUID = -6895490553746305660L;

    /**
     * 页码
     */
    private Long pageNo;

    /**
     * 每页数量
     */
    private Long pageSize;

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
     *
     * @since 1.1.0.211021
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
     * @since 1.1.0.211021
     */
    public PageDTO(long pageNo, long pageSize) {
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
     * @since 1.1.0.211021
     */
    public PageDTO(long pageNo, long pageSize, long totalCount, Collection<T> content) {
        this(pageNo, pageSize);
        this.totalCount = totalCount;
        this.content = content;
        adjustPageNo();
    }

    /**
     * 构造器
     *
     * @param pageRequest 分页参数
     * @since 1.1.0.211021
     */
    public PageDTO(PageRequest pageRequest) {
        this();
        this.pageNo = pageRequest.getPageNo();
        this.pageSize = pageRequest.getPageSize();
    }

    /**
     * 构造器
     *
     * @param pageRequest 分页参数
     * @param content     数据
     * @param totalCount  总数
     * @since 1.1.0.211021
     */
    public PageDTO(PageRequest pageRequest, long totalCount, Collection<T> content) {
        this(pageRequest);
        this.totalCount = totalCount;
        this.content = content;
    }

    /**
     * 构造器
     *
     * @param pageDetailVO 分页详情
     * @param content      数据
     * @since 1.1.0.211021
     */
    public PageDTO(PageDetailVO pageDetailVO, Collection<T> content) {
        this(pageDetailVO.getPageNo(), pageDetailVO.getPageSize(), pageDetailVO.getTotalCount(), content);
        adjustPageNo();
    }

    /**
     * 获取空分页DTO
     *
     * @param <T> 分页数据类型
     * @return 空分页DTO
     * @since 1.1.0.211021
     */
    public static <T> PageDTO<T> empty() {
        return new PageDTO<>();
    }

    @Override
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public long getPageSize() {
        return pageSize;
    }

    @Override
    public long getPageNo() {
        return pageNo;
    }

    @Override
    public long getTotalPage() {
        long totalPage = (totalCount / pageSize);
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
    public long getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    @Override
    public long getPrePage() {
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

    public PageDTO<T> setPageSize(long pageSize) {
        if (pageSize < 1) {
            this.pageSize = PageConstant.DEFAULT_MAX_PAGE_SIZE;
        } else {
            this.pageSize = Math.min(pageSize, PageConstant.DEFAULT_MAX_PAGE_SIZE);
        }
        return this;
    }

    public PageDTO<T> setPageNo(long pageNo) {
        this.pageNo = Math.max(pageNo, 1);
        adjustPageNo();
        return this;
    }

    @Override
    public Collection<T> getContent() {
        return content;
    }

    public PageDTO<T> setContent(Collection<T> content) {
        this.content = content;
        return this;
    }

    /**
     * 调整页码，使不超过最大页数
     *
     * @since 1.1.0.211021
     */
    public void adjustPageNo() {
        if (pageNo == 1) {
            return;
        }
        long totalPage = getTotalPage();
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }
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
