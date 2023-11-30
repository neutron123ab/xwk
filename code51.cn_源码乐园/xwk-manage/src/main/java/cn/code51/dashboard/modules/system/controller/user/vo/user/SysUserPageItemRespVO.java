package cn.code51.dashboard.modules.system.controller.user.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel(value = "用户分页时的信息 Response VO", description = "相比用户基本信息来说，会多学院信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysUserPageItemRespVO extends SysUserRespVO {

    /**
     * 所在学院
     */
    private Dept dept;

    @ApiModel("学院")
    @Data
    public static class Dept {

        @ApiModelProperty(value = "学院编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "学院名称", required = true, example = "研发部")
        private String name;

    }

}
