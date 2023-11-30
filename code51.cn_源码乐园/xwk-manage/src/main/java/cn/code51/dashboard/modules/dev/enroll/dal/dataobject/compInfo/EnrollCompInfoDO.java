package cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.code51.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 竞赛报名 DO
 *
 * @author 教研源码
 */
@TableName("enroll_comp_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollCompInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 竞赛名称
     */
    private String compName;
    /**
     * 参赛人编号
     */
    private String enrollerId;
    /**
     * 参赛人姓名
     */
    private String enrollerName;
    /**
     * 状态
     */
    private String statusInfo;

}
