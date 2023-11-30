package cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 竞赛 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CompInfoBaseVO {

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
