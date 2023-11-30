package cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.code51.dashboard.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.code51.dashboard.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "竞赛报名 Excel 导出 Request VO", description = "参数和 EnrollCompInfoPageReqVO 是一致的")
@Data
public class EnrollCompInfoExportReqVO {

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "竞赛名称")
    private String compName;

    @ApiModelProperty(value = "参赛人编号")
    private String enrollerId;

    @ApiModelProperty(value = "参赛人姓名")
    private String enrollerName;

    @ApiModelProperty(value = "状态")
    private String statusInfo;

}
