package cn.code51.dashboard.modules.system.controller.dept;

import cn.code51.dashboard.common.enums.CommonStatusEnum;
import cn.code51.dashboard.common.pojo.CommonResult;
import cn.code51.dashboard.modules.system.controller.dept.vo.dept.*;
import cn.code51.dashboard.modules.system.convert.dept.SysDeptConvert;
import cn.code51.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import cn.code51.dashboard.modules.system.service.dept.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Api(tags = "学院")
@RestController
@RequestMapping("/system/dept")
@Validated
public class SysDeptController {

    @Resource
    private SysDeptService deptService;

    @PostMapping("create")
    @ApiOperation("创建学院")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public CommonResult<Long> createDept(@Valid @RequestBody SysDeptCreateReqVO reqVO) {
        Long deptId = deptService.createDept(reqVO);
        return CommonResult.success(deptId);
    }

    @PutMapping("update")
    @ApiOperation("更新学院")
    @PreAuthorize("@ss.hasPermission('system:dept:update')")
    public CommonResult<Boolean> updateDept(@Valid @RequestBody SysDeptUpdateReqVO reqVO) {
        deptService.updateDept(reqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("delete")
    @ApiOperation("删除学院")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:dept:delete')")
    public CommonResult<Boolean> deleteDept(@RequestParam("id") Long id) {
        deptService.deleteDept(id);
        return CommonResult.success(true);
    }

    @GetMapping("/list")
    @ApiOperation("获取学院列表")
    public CommonResult<List<SysDeptRespVO>> listDepts(SysDeptListReqVO reqVO) {
        List<SysDeptDO> list = deptService.getSimpleDepts(reqVO);
        list.sort(Comparator.comparing(SysDeptDO::getSort));
        return CommonResult.success(SysDeptConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取学院精简信息列表", notes = "只包含被开启的学院，主要用于前端的下拉选项")
    public CommonResult<List<SysDeptSimpleRespVO>> getSimpleDepts() {
        // 获得学院列表，只要开启状态的
        SysDeptListReqVO reqVO = new SysDeptListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<SysDeptDO> list = deptService.getSimpleDepts(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(SysDeptDO::getSort));
        return CommonResult.success(SysDeptConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/getSubDepts")
    @ApiOperation(value = "获取子学院", notes = "只包含被开启的学院，主要用于前端的下拉选项")
    public CommonResult<List<SysDeptSimpleRespVO>> get() {
        // 获得学院列表，只要开启状态的
        SysDeptListReqVO reqVO = new SysDeptListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        reqVO.setParentId(0L);
        List<SysDeptDO> list = deptService.getSubDepts(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(SysDeptDO::getSort));
        return CommonResult.success(SysDeptConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/get")
    @ApiOperation("获得学院信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:dept:query')")
    public CommonResult<SysDeptRespVO> getDept(@RequestParam("id") Long id) {
        return CommonResult.success(SysDeptConvert.INSTANCE.convert(deptService.getDept(id)));
    }

}
