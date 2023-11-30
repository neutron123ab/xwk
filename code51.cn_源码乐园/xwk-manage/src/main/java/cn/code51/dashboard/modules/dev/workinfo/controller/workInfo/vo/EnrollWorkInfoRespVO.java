package cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("参赛作品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EnrollWorkInfoRespVO extends EnrollWorkInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
