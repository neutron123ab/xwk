package cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("竞赛 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompInfoRespVO extends CompInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
