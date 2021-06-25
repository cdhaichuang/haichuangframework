package pro.haichuang.framework.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import pro.haichuang.framework.base.enums.abnormal.client.RequestParamAbnormalEnum;
import pro.haichuang.framework.base.exception.client.RequestParamException;
import pro.haichuang.framework.base.request.PageRequest;
import pro.haichuang.framework.mybatis.domain.BaseDO;
import pro.haichuang.framework.base.page.PageDTO;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service基类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public interface BaseService<T extends BaseDO> extends IService<T> {

    // ========================= 忽略 =========================

    /**
     * 忽略ID获取是否存在
     *
     * @param id ID
     * @return 是否存在 [true: 存在, false: 不存在]
     */
    default boolean isExistsByIdAndIgnore(@Nullable Long id) {
        if (id == null) {
            return false;
        }
        return this.countByIdAndValidate(id) != 0;
    }

    /**
     * 批量忽略ID获取是否存在
     *
     * @param ids IDs
     * @return 是否存在 [true: 存在, false: 不存在]
     */
    default boolean isExistsByIdAndIgnore(@Nullable Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return this.countByIdAndValidate(ids) != 0;
    }

    /**
     * 忽略ID获取数量
     *
     * @param id ID
     * @return 数量
     */
    default int countByIdAndIgnore(@Nullable Long id) {
        if (id == null) {
            return 0;
        }
        return this.count(new QueryWrapper<T>().select(BaseDO.ID).eq(BaseDO.ID, id));
    }

    /**
     * 批量忽略ID获取数量
     *
     * @param ids IDs
     * @return 数量
     */
    default int countByIdAndIgnore(@Nullable Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        return this.count(new QueryWrapper<T>().select(BaseDO.ID).in(BaseDO.ID, ids));
    }

    /**
     * 忽略ID并获取DO
     *
     * @param id ID
     * @return DO
     */
    @Nullable
    default T getByIdAndIgnore(@Nullable Long id) {
        if (id == null) {
            return null;
        }
        return this.getById(id);
    }

    /**
     * 忽略IDs并获取DO
     *
     * @param ids IDs
     * @return DOs
     */
    @NonNull
    default List<T> listByIdAndIgnore(@Nullable Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return this.listByIds(ids);
    }

    /**
     * 忽略PageRequest并获取PageDTO
     * 默认根据 {@link BaseDO#MODIFY_TIME} 进行排序
     *
     * @param pageRequest PageRequest
     * @return PageDTO
     */
    @NonNull
    default PageDTO<T> listPageAndIgnore(@Nullable PageRequest pageRequest) {
        if (pageRequest == null) {
            return new PageDTO<>(new PageRequest());
        }
        return new PageDTO<T>(pageRequest).setContent(this.page(new Page<T>(pageRequest.getPageNo(), pageRequest.getPageSize())
                .addOrder(OrderItem.desc(BaseService.toUnderlineCase(BaseDO.MODIFY_TIME)))).getRecords());
    }

    /**
     * 怒略实体类ID并保存
     *
     * @param entity 实体类
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean saveAndIgnore(@Nullable T entity) {
        if (entity == null) {
            return false;
        }
        return this.save(BaseService.clearEntityDefaultParameterAndGet(entity,
                true, true, true));
    }

    /**
     * 批量怒略验证实体类ID并保存
     *
     * @param entities 实体对象集合
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean saveAndIgnore(@Nullable Collection<T> entities) {
        return this.saveAndIgnore(entities, 1000);
    }

    /**
     * 批量怒略验证实体类ID并保存
     *
     * @param entities  实体对象集合
     * @param batchSize 插入批次数量
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean saveAndIgnore(@Nullable Collection<T> entities, int batchSize) {
        if (entities == null || entities.isEmpty()) {
            return false;
        }
        return this.saveBatch(BaseService.clearEntityDefaultParameterAndGet(entities,
                true, true, true), batchSize);
    }

    /**
     * 怒略验证实体类ID并更新
     *
     * @param entity 实体类
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean updateByIdAndIgnore(@Nullable T entity) {
        if (entity == null) {
            return false;
        }
        return this.updateById(clearEntityDefaultParameterAndGet(entity));
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entities 实体对象集合
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean updateByIdAndIgnore(@Nullable Collection<T> entities) {
        return this.updateByIdAndIgnore(entities, 1000);
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entities  实体对象集合
     * @param batchSize 插入批次数量
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean updateByIdAndIgnore(@Nullable Collection<T> entities, int batchSize) {
        if (entities == null || entities.isEmpty()) {
            return false;
        }
        return this.updateBatchById(clearEntityDefaultParameterAndGet(entities), batchSize);
    }

    /**
     * 怒略实体类ID并删除
     *
     * @param id ID
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean removeByIdAndIgnore(@Nullable Long id) {
        if (id == null) {
            return false;
        }
        return this.removeById(id);
    }

    /**
     * 批量怒略实体类ID并删除
     *
     * @param ids ID集合
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean removeByIdAndIgnore(@Nullable Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return this.removeByIds(ids);
    }

    // ========================= 验证 =========================

    /**
     * 验证ID获取是否存在
     *
     * @param id ID
     * @return 是否存在 [true: 存在, false: 不存在]
     * @throws RequestParamException ID为空
     */
    default boolean isExistsByIdAndValidate(@NonNull Long id) throws RequestParamException {
        return this.isExistsByIdAndValidate(id, null);
    }

    /**
     * 验证ID获取是否存在
     *
     * @param id           ID
     * @param errorUserTip 错误提示信息
     * @return 是否存在 [true: 存在, false: 不存在]
     * @throws RequestParamException ID为空
     */
    default boolean isExistsByIdAndValidate(@Nullable Long id, @Nullable String errorUserTip)
            throws RequestParamException {
        if (id == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return this.countByIdAndValidate(id) != 0;
    }

    /**
     * 批量验证ID获取是否存在
     *
     * @param ids IDs
     * @return 是否存在 [true: 存在, false: 不存在]
     * @throws RequestParamException ID为空
     */
    default boolean isExistsByIdAndValidate(@Nullable Collection<Long> ids) throws RequestParamException {
        return this.isExistsByIdAndValidate(ids, null);
    }

    /**
     * 批量验证ID获取是否存在
     *
     * @param ids          IDs
     * @param errorUserTip 错误提示信息
     * @return 是否存在 [true: 存在, false: 不存在]
     * @throws RequestParamException ID为空
     */
    default boolean isExistsByIdAndValidate(@Nullable Collection<Long> ids, @Nullable String errorUserTip)
            throws RequestParamException {
        if (ids == null || ids.isEmpty()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return this.countByIdAndValidate(ids) != 0;
    }

    /**
     * 验证ID获取数量
     *
     * @param id ID
     * @return 数量
     * @throws RequestParamException ID为空
     */
    default int countByIdAndValidate(@Nullable Long id) throws RequestParamException {
        return this.countByIdAndValidate(id, null);
    }

    /**
     * 验证ID获取数量
     *
     * @param id           ID
     * @param errorUserTip 错误提示信息
     * @return 数量
     * @throws RequestParamException ID为空
     */
    default int countByIdAndValidate(@Nullable Long id, @Nullable String errorUserTip) throws RequestParamException {
        if (id == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return this.count(new QueryWrapper<T>().select(BaseDO.ID).eq(BaseDO.ID, id));
    }

    /**
     * 批量验证ID获取数量
     *
     * @param ids IDs
     * @return 数量
     * @throws RequestParamException ID为空
     */
    default int countByIdAndValidate(@Nullable Collection<Long> ids) throws RequestParamException {
        return this.countByIdAndValidate(ids, null);
    }

    /**
     * 批量验证ID获取数量
     *
     * @param ids          IDs
     * @param errorUserTip 错误提示信息
     * @return 数量
     * @throws RequestParamException ID为空
     */
    default int countByIdAndValidate(@Nullable Collection<Long> ids, @Nullable String errorUserTip)
            throws RequestParamException {
        if (ids == null || ids.isEmpty()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return this.count(new QueryWrapper<T>().select(BaseDO.ID).in(BaseDO.ID, ids));
    }

    /**
     * 验证ID并获取DO
     *
     * @param id ID
     * @return DO
     * @throws RequestParamException ID为空|ID错误
     */
    @NonNull
    default T getByIdAndValidate(@Nullable Long id) throws RequestParamException {
        return this.getByIdAndValidate(id, null);
    }

    /**
     * 验证ID并获取DO
     *
     * @param id           ID
     * @param errorUserTip 错误提示信息
     * @return DO
     * @throws RequestParamException ID为空|ID错误
     */
    @NonNull
    default T getByIdAndValidate(@Nullable Long id, @Nullable String errorUserTip) throws RequestParamException {
        if (id == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        T entity = this.getById(id);
        if (entity == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR, errorUserTip);
        }
        return entity;
    }

    /**
     * 验证IDs并获取DOs
     *
     * @param ids IDs
     * @return DOs
     * @throws RequestParamException ID为空|ID错误
     */
    @NonNull
    default List<T> listByIdAndValidate(@Nullable Collection<Long> ids) throws RequestParamException {
        return this.listByIdAndValidate(ids, null);
    }

    /**
     * 验证IDs并获取DOs
     *
     * @param ids          IDs
     * @param errorUserTip 错误提示信息
     * @return DOs
     * @throws RequestParamException ID为空|ID错误
     */
    @NonNull
    default List<T> listByIdAndValidate(@Nullable Collection<Long> ids, @Nullable String errorUserTip)
            throws RequestParamException {
        if (ids == null || ids.isEmpty()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        List<T> entity = this.listByIds(ids);
        if (entity == null || entity.isEmpty()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR, errorUserTip);
        }
        return entity;
    }

    /**
     * 验证PageRequest并获取PageDTO
     * 默认根据 {@link BaseDO#MODIFY_TIME} 进行排序
     *
     * @param pageRequest PageRequest
     * @return PageDTO
     */
    @NonNull
    default PageDTO<T> listPageAndValidate(@Nullable PageRequest pageRequest) throws RequestParamException {
        return this.listPageAndValidate(pageRequest, null);
    }

    /**
     * 验证PageRequest并获取PageDTO
     * 默认根据 {@link BaseDO#MODIFY_TIME} 进行排序
     *
     * @param pageRequest  PageRequest
     * @param errorUserTip 错误提示信息
     * @return PageDTO
     */
    @NonNull
    default PageDTO<T> listPageAndValidate(@Nullable PageRequest pageRequest, @Nullable String errorUserTip)
            throws RequestParamException {
        if (pageRequest == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return new PageDTO<T>(pageRequest).setContent(this.page(new Page<T>(pageRequest.getPageNo(), pageRequest.getPageSize())
                .addOrder(OrderItem.desc(BaseService.toUnderlineCase(BaseDO.MODIFY_TIME)))).getRecords());
    }

    /**
     * 验证实体类ID并保存
     *
     * @param entity 实体类
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean saveAndValidate(@Nullable T entity) throws RequestParamException {
        return this.saveAndValidate(entity, null);
    }

    /**
     * 验证实体类ID并保存
     *
     * @param entity       实体类
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean saveAndValidate(@Nullable T entity, @Nullable String errorUserTip)
            throws RequestParamException {
        if (entity == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return this.save(BaseService.clearEntityDefaultParameterAndGet(entity,
                true, true, true));
    }

    /**
     * 批量验证实体类ID并保存
     *
     * @param entities 实体对象集合
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean saveAndValidate(@Nullable Collection<T> entities) throws RequestParamException {
        return this.saveAndValidate(entities, 1000, null);
    }

    /**
     * 批量验证实体类ID并保存
     *
     * @param entities     实体对象集合
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean saveAndValidate(@Nullable Collection<T> entities, @Nullable String errorUserTip)
            throws RequestParamException {
        return this.saveAndValidate(entities, 1000, errorUserTip);
    }

    /**
     * 批量验证实体类ID并保存
     *
     * @param entities  实体对象集合
     * @param batchSize 插入批次数量
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean saveAndValidate(@Nullable Collection<T> entities, int batchSize) throws RequestParamException {
        return this.saveAndValidate(entities, batchSize, null);
    }

    /**
     * 批量验证实体类ID并保存
     *
     * @param entities     实体对象集合
     * @param batchSize    插入批次数量
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean saveAndValidate(@Nullable Collection<T> entities, int batchSize, @Nullable String errorUserTip)
            throws RequestParamException {
        if (entities == null || entities.isEmpty()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        return this.saveBatch(BaseService.clearEntityDefaultParameterAndGet(entities,
                true, true, true), batchSize);
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entity 实体类
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    @SneakyThrows
    default boolean updateByIdAndValidate(@Nullable T entity) throws RequestParamException {
        if (entity == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY);
        }
        Method getIdMethod = BaseService.validateIdExistsAndGetIdMethod(entity);
        if (this.countByIdAndValidate((Long) getIdMethod.invoke(entity)) == 0) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR);
        }
        return this.updateByIdAndValidate(entity, null);
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entity       实体类
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    @SneakyThrows
    default boolean updateByIdAndValidate(@Nullable T entity, @Nullable String errorUserTip)
            throws RequestParamException {
        if (entity == null) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        Method getIdMethod = BaseService.validateIdExistsAndGetIdMethod(entity);
        if (this.countByIdAndValidate((Long) getIdMethod.invoke(entity)) == 0) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR, errorUserTip);
        }
        return this.updateById(clearEntityDefaultParameterAndGet(entity));
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entities 实体对象集合
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean updateByIdAndValidate(@Nullable Collection<T> entities) throws RequestParamException {
        return this.updateByIdAndValidate(entities, 1000);
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entities     实体对象集合
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    default boolean updateByIdAndValidate(@Nullable Collection<T> entities, @Nullable String errorUserTip)
            throws RequestParamException {
        return this.updateByIdAndValidate(entities, 1000, errorUserTip);
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entities  实体对象集合
     * @param batchSize 插入批次数量
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    @SneakyThrows
    default boolean updateByIdAndValidate(@Nullable Collection<T> entities, int batchSize)
            throws RequestParamException {
        return this.updateByIdAndValidate(entities, batchSize, null);
    }

    /**
     * 批量验证实体类ID并更新
     *
     * @param entities     实体对象集合
     * @param batchSize    插入批次数量
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     * @throws RequestParamException 参数为空|参数错误
     */
    @SneakyThrows
    default boolean updateByIdAndValidate(@Nullable Collection<T> entities, int batchSize, @Nullable String errorUserTip)
            throws RequestParamException {
        if (CollectionUtils.isEmpty(entities)) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_EMPTY, errorUserTip);
        }
        List<Long> ids = new ArrayList<>();
        for (T entity : entities) {
            ids.add((Long) BaseService.validateIdExistsAndGetIdMethod(entity).invoke(entity));
        }
        if (this.countByIdAndValidate(ids) != entities.size()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR, errorUserTip);
        }
        return this.updateBatchById(clearEntityDefaultParameterAndGet(entities), batchSize);
    }

    /**
     * 验证实体类ID并删除
     *
     * @param id ID
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean removeByIdAndValidate(@Nullable Long id) throws RequestParamException {
        return this.removeByIdAndValidate(id, null);
    }

    /**
     * 验证实体类ID并删除
     *
     * @param id           ID
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean removeByIdAndValidate(@Nullable Long id, @Nullable String errorUserTip)
            throws RequestParamException {
        if (this.countByIdAndValidate(id) == 0) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR, errorUserTip);
        }
        return this.removeById(id);
    }

    /**
     * 批量验证实体类ID并删除
     *
     * @param ids ID集合
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean removeByIdAndValidate(@Nullable Collection<Long> ids) throws RequestParamException {
        return this.removeByIdAndValidate(ids, null);
    }

    /**
     * 批量验证实体类ID并删除
     *
     * @param ids          ID集合
     * @param errorUserTip 错误提示信息
     * @return 操作是否成功 {null: 未执行, true: 成功, false: 失败}
     */
    default boolean removeByIdAndValidate(@Nullable Collection<Long> ids, @Nullable String errorUserTip)
            throws RequestParamException {
        if (ids == null || this.countByIdAndValidate(ids) != ids.size()) {
            throw new RequestParamException(RequestParamAbnormalEnum.PARAMETER_ERROR, errorUserTip);
        }
        return this.removeByIds(ids);
    }

    // ========================= 工具 =========================

    /**
     * 清除实体类默认参数值
     *
     * @param entity 实体类
     * @param <T>    实体类类型
     * @return 操作后的实体类
     */
    @NonNull
    static <T extends BaseDO> T clearEntityDefaultParameterAndGet(@NonNull T entity) {
        return BaseService.clearEntityDefaultParameterAndGet(entity,
                false, true, true);
    }

    /**
     * 批量清除实体类默认参数值
     *
     * @param entities 实体对象集合
     * @param <T>      实体类类型
     * @return 操作后的实体类
     */
    @NonNull
    static <T extends BaseDO> List<T> clearEntityDefaultParameterAndGet(@NonNull Collection<T> entities) {
        return entities.stream().map(entity -> BaseService.clearEntityDefaultParameterAndGet(entity,
                false, true, true)).collect(Collectors.toList());
    }

    /**
     * 清除实体类默认参数值
     *
     * @param entity            实体类
     * @param isClearId         是否清除ID
     * @param isClearCreateTime 是否清除创建时间
     * @param isClearModifyTime 是否清除更新时间
     * @param <T>               实体类类型
     * @return 操作后的实体类
     */
    @SneakyThrows
    @NonNull
    static <T extends BaseDO> T clearEntityDefaultParameterAndGet(
            @NonNull T entity, boolean isClearId, boolean isClearCreateTime, boolean isClearModifyTime) {
        if (isClearId) {
            String setIdMethodName = "set".concat(BaseDO.ID.substring(0, 1).toUpperCase().concat(BaseDO.ID.substring(1)));
            Method setIdMethod = BaseService.getDeepDeclareMethod(entity, setIdMethodName, BaseDO.ID_CLASS);
            if (setIdMethod == null) {
                throw new NoSuchMethodException(
                        entity.getClass().getName() + "." + setIdMethodName + "(" + BaseDO.ID_CLASS.getName() + ")"
                );
            }
            setIdMethod.invoke(entity, (Object) null);
        }
        if (isClearCreateTime) {
            String setCreateTimeMethodName = "set".concat(BaseDO.CREATE_TIME.substring(0, 1).toUpperCase()
                    .concat(BaseDO.CREATE_TIME.substring(1)));
            Method setCreateTimeMethod = BaseService.getDeepDeclareMethod(entity,
                    setCreateTimeMethodName, BaseDO.CREATE_TIME_CLASS);
            if (setCreateTimeMethod == null) {
                throw new NoSuchMethodException(entity.getClass().getName()
                        + "." + setCreateTimeMethodName + "(" + BaseDO.ID_CLASS.getName() + ")");
            }
            setCreateTimeMethod.invoke(entity, (Object) null);
        }
        if (isClearModifyTime) {
            String setModifyTimeMethodName = "set".concat(BaseDO.MODIFY_TIME.substring(0, 1).toUpperCase()
                    .concat(BaseDO.MODIFY_TIME.substring(1)));
            Method setModifyTimeMethod = BaseService.getDeepDeclareMethod(entity,
                    setModifyTimeMethodName, BaseDO.MODIFY_TIME_CLASS);
            if (setModifyTimeMethod == null) {
                throw new NoSuchMethodException(entity.getClass().getName()
                        + "." + setModifyTimeMethodName + "(" + BaseDO.ID_CLASS.getName() + ")");
            }
            setModifyTimeMethod.invoke(entity, (Object) null);
        }
        return entity;
    }

    /**
     * 批量清除实体类默认参数值
     *
     * @param entities          实体对象集合
     * @param isClearId         是否清除ID
     * @param isClearCreateTime 是否清除创建时间
     * @param isClearModifyTime 是否清除更新时间
     * @param <T>               实体类类型
     * @return 清除参数后的实体对象集合
     */
    @NonNull
    static <T extends BaseDO> Collection<T> clearEntityDefaultParameterAndGet(
            @NonNull Collection<T> entities, boolean isClearId, boolean isClearCreateTime, boolean isClearModifyTime) {
        return entities.stream().map(entity -> BaseService.clearEntityDefaultParameterAndGet(entity,
                isClearId, isClearCreateTime, isClearModifyTime)).collect(Collectors.toList());
    }

    /**
     * 验证ID是否存在并获取ID方法
     *
     * @param entity 实体类
     * @param <T>    实体类类型
     * @return 获取ID方法
     */
    @SneakyThrows
    @NonNull
    static <T extends BaseDO> Method validateIdExistsAndGetIdMethod(@NonNull T entity) {
        String idMethodFormat = BaseDO.ID.substring(0, 1).toUpperCase().concat(BaseDO.ID.substring(1));
        String setIdMethodName = "set".concat(idMethodFormat);
        Method setIdMethod = BaseService.getDeepDeclareMethod(entity, setIdMethodName, BaseDO.ID_CLASS);
        String getIdMethodName = "get".concat(idMethodFormat);
        Method getIdMethod = BaseService.getDeepDeclareMethod(entity, getIdMethodName);
        if (setIdMethod == null) {
            throw new NoSuchMethodException(entity.getClass().getName()
                    + "." + setIdMethodName + "(" + BaseDO.ID_CLASS.getName() + ")");
        }
        if (getIdMethod == null) {
            throw new NoSuchMethodException(entity.getClass().getName()
                    + "." + getIdMethodName + "(" + BaseDO.ID_CLASS.getName() + ")");
        }
        return getIdMethod;
    }

    /**
     * 获取实体类的方法
     *
     * @param entity         实体类
     * @param methodName     方法名
     * @param parameterTypes 方法参数Class
     * @param <T>            实体类类型
     * @return 方法
     */
    @Nullable
    static <T extends BaseDO> Method getDeepDeclareMethod(
            @NonNull T entity, @NonNull String methodName, @Nullable Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = entity.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException ignored) {
            }
        }
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 简易驼峰转下划线
     *
     * @param value 驼峰字符串
     * @return 下划线字符串
     */
    static String toUnderlineCase(@NonNull String value) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
