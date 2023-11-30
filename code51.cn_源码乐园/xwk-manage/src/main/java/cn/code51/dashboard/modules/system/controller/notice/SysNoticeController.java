package cn.code51.dashboard.modules.system.controller.notice;

import cn.code51.dashboard.common.pojo.CommonResult;
import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticeCreateReqVO;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticePageReqVO;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticeRespVO;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticeUpdateReqVO;
import cn.code51.dashboard.modules.system.convert.notice.SysNoticeConvert;
import cn.code51.dashboard.modules.system.dal.dataobject.notice.SysNoticeDO;
import cn.code51.dashboard.modules.system.service.notice.SysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.code51.dashboard.common.pojo.CommonResult.success;

@Api(tags = "通知公告")
@RestController
@RequestMapping("/system/notice")
@Validated
public class SysNoticeController {

    @Resource
    private SysNoticeService noticeService;

    @PostMapping("/create")
    @ApiOperation("创建通知公告")
    @PreAuthorize("@ss.hasPermission('system:notice:create')")
    public CommonResult<Long> createNotice(@Valid @RequestBody SysNoticeCreateReqVO reqVO) {
        reqVO.setReaded("");
        Long noticeId = noticeService.createNotice(reqVO);
        return success(noticeId);
    }

    @PutMapping("/update")
    @ApiOperation("修改通知公告")
    @PreAuthorize("@ss.hasPermission('system:notice:update')")
    public CommonResult<Boolean> updateNotice(@Valid @RequestBody SysNoticeUpdateReqVO reqVO) {
        noticeService.updateNotice(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除通知公告")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:notice:delete')")
    public CommonResult<Boolean> deleteNotice(@RequestParam("id") Long id) {
        noticeService.deleteNotice(id);
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获取通知公告列表")
    public CommonResult<PageResult<SysNoticeRespVO>> pageNotices(@Validated SysNoticePageReqVO reqVO) {
        return success(SysNoticeConvert.INSTANCE.convertPage(noticeService.pageNotices(reqVO)));
    }

    @GetMapping("/get")
    @ApiOperation("获得通知公告")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<SysNoticeRespVO> getNotice(@RequestParam("id") Long id) {
        return success(SysNoticeConvert.INSTANCE.convert(noticeService.getNotice(id)));
    }




    @RequestMapping("/handleRead")
    public CommonResult handleRead(@Param("id")String id){

        SysNoticeDO  getnotice= noticeService.getNotice(Long.valueOf(id));
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();

        if(getnotice!=null){
            String readed = getnotice.getReaded();
            if(StringUtils.isNotEmpty(readed)){
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(",");
                stringBuffer.append(loginUserId);
                String concat = readed.concat(stringBuffer.toString());
                SysNoticeDO  vo=new SysNoticeDO();
                vo.setId(getnotice.getId());
                vo.setReaded(concat);
                noticeService.update(vo);
            }else{
                getnotice.setReaded(String.valueOf(loginUserId));
                SysNoticeDO  vo=new SysNoticeDO();
                vo.setId(getnotice.getId());
                vo.setReaded(getnotice.getReaded());
                noticeService.update(vo);noticeService.update(vo);
            }
        }

        return  success(true);
    }

}
