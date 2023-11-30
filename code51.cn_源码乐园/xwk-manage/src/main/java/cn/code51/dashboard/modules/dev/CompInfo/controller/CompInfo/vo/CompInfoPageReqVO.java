package cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.code51.dashboard.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.code51.dashboard.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("竞赛分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompInfoPageReqVO extends PageParam {

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "报名条件")
    private String conditionInfo;

    @ApiModelProperty(value = "学科类别")
    private String xkType;

    @ApiModelProperty(value = "所属学院")
    private String deptId;

    @ApiModelProperty(value = "审核状态")
    private String checkStatus;

    @ApiModelProperty(value = "竞赛描述信息")
    private String descInfo;

    @ApiModelProperty(value = "竞赛名称")
    private String name;

}
