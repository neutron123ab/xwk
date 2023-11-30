package cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.code51.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 竞赛 DO
 *
 * @author coder
 */
@TableName("comp_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 报名条件
     */
    private String conditionInfo;
    /**
     * 学科类别
     */
    private String xkType;
    /**
     * 所属学院
     */
    private String deptId;
    /**
     * 审核状态
     */
    private String checkStatus;
    /**
     * 竞赛描述信息
     */
    private String descInfo;
    /**
     * 竞赛名称
     */
    private String name;

}
