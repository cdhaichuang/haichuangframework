package pro.haichuang.framework.mybatis.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.validate.Group;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DO基类
 *
 * <p>该类为 [DO] 层基类, 所有 [DO] 层必须继承此类
 * <p>该类中指定了映射到数据库中每张表的 {@code id ID} / {@code createTime 创建时间} / {@code modifyTime 最后修改时间}
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@ApiModel("BaseDO")
public class BaseDO implements Serializable {
    private static final long serialVersionUID = 5832404993634933694L;

    public static final String ID = "id";
    public static final String CREATE_TIME = "createTime";
    public static final String MODIFY_TIME = "modifyTime";
    public static final String LOGIC_DELETE = "logicDelete";
    public static final String VERSION = "version";
    public static final Class<Long> ID_CLASS = Long.class;
    public static final Class<LocalDateTime> CREATE_TIME_CLASS = LocalDateTime.class;
    public static final Class<LocalDateTime> MODIFY_TIME_CLASS = LocalDateTime.class;
    public static final Class<Boolean> LOGIC_DELETE_CLASS = Boolean.class;
    public static final Class<Integer> VERSION_CLASS = Integer.class;

    /**
     * 唯一ID
     *
     * <p>默认使用雪花ID, 默认带上Validate的 {@code Group.Update} 类型验证
     */
    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "ID不能为空", groups = Group.Update.class)
    private Long id;

    /**
     * 创建时间
     *
     * <p>新增时将自动填充当前时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     *
     * <p>新增与修改时自动填充当前时间
     */
    @ApiModelProperty(value = "最后修改时间", hidden = true)
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
