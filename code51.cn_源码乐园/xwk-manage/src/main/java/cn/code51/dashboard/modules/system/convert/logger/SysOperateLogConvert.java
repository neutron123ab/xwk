package cn.code51.dashboard.modules.system.convert.logger;

import cn.code51.dashboard.common.exception.enums.GlobalErrorCodeConstants;
import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogCreateReqVO;
import cn.code51.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogExcelVO;
import cn.code51.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogRespVO;
import cn.code51.dashboard.modules.system.dal.dataobject.logger.SysOperateLogDO;
import cn.code51.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import cn.code51.dashboard.util.collection.MapUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface SysOperateLogConvert {

    SysOperateLogConvert INSTANCE = Mappers.getMapper(SysOperateLogConvert.class);

    SysOperateLogDO convert(SysOperateLogCreateReqVO bean);

    PageResult<SysOperateLogRespVO> convertPage(PageResult<SysOperateLogDO> page);

    SysOperateLogRespVO convert(SysOperateLogDO bean);

    default List<SysOperateLogExcelVO> convertList(List<SysOperateLogDO> list, Map<Long, SysUserDO> userMap) {
        return list.stream().map(operateLog -> {
            SysOperateLogExcelVO excelVO = convert02(operateLog);
            MapUtils.findAndThen(userMap, operateLog.getId(), user -> excelVO.setUserNickname(user.getNickname()));
            excelVO.setSuccessStr(GlobalErrorCodeConstants.SUCCESS.getCode().equals(operateLog.getResultCode()) ? "成功" : "失败");
            return excelVO;
        }).collect(Collectors.toList());
    }

    SysOperateLogExcelVO convert02(SysOperateLogDO bean);

}
