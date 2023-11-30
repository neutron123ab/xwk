package cn.code51.dashboard.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@ApiModel("学院更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptUpdateReqVO extends SysDeptBaseVO {

    @ApiModelProperty(value = "学院编号", required = true, example = "1024")
    @NotNull(message = "学院编号不能为空")
    private Long id;

}
