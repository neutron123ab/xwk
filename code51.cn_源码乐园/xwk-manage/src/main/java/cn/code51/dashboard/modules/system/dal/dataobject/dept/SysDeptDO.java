package cn.code51.dashboard.modules.system.dal.dataobject.dept;

import cn.code51.dashboard.common.enums.CommonStatusEnum;
import cn.code51.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学院表
 *
 *
 */
@TableName("sys_dept")
@Data
@EqualsAndHashCode(callSuper = true)

public class SysDeptDO extends BaseDO {

    /**
     * 学院ID
     */
    @TableId
    private Long id;
    /**
     * 学院名称
     */
    private String name;
    /**
     * 父学院ID
     *
     * 关联 {@link #id}
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 学院状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
