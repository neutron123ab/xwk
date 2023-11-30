package cn.code51.dashboard.modules.tool.dal.dataobject.test;

import cn.code51.dashboard.common.enums.CommonStatusEnum;
import cn.code51.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import cn.code51.dashboard.framework.mybatis.core.dataobject.BaseDO;
import cn.code51.dashboard.framework.redis.core.RedisKeyDefine;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
* 字典类型 DO
*
* @author 教研源码
*/
@TableName("tool_test_demo")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolTestDemoDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 类型
     *
     * 枚举 {@link OperateTypeEnum}
     */
    private Integer type;
    /**
     * 分类
     *
     * 枚举 {@link RedisKeyDefine.TimeoutTypeEnum}
     */
    private Integer category;
    /**
     * 备注
     */
    private String remark;

}
