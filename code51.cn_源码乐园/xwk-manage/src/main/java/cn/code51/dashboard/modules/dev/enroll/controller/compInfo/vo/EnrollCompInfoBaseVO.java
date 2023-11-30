package cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 竞赛报名 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EnrollCompInfoBaseVO {

    @ApiModelProperty(value = "竞赛名称")
    private String compName;

    @ApiModelProperty(value = "参赛人编号")
    private String enrollerId;

    @ApiModelProperty(value = "参赛人姓名")
    private String enrollerName;

    @ApiModelProperty(value = "状态")
    private String statusInfo;

}
