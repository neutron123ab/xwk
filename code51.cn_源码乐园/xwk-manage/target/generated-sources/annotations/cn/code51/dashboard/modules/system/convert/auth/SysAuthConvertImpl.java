package cn.code51.dashboard.modules.system.convert.auth;

import cn.code51.dashboard.framework.security.core.LoginUser;
import cn.code51.dashboard.modules.system.controller.auth.vo.auth.SysAuthMenuRespVO;
import cn.code51.dashboard.modules.system.controller.auth.vo.auth.SysAuthMenuRespVO.SysAuthMenuRespVOBuilder;
import cn.code51.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import cn.code51.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import cn.code51.dashboard.modules.system.dal.dataobject.permission.SysMenuDO;
import cn.code51.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T10:37:35+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class SysAuthConvertImpl implements SysAuthConvert {

    @Override
    public LoginUser convert(SysUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        loginUser.setId( bean.getId() );
        loginUser.setDeptId( bean.getDeptId() );
        loginUser.setUsername( bean.getUsername() );
        loginUser.setPassword( bean.getPassword() );
        loginUser.setStatus( bean.getStatus() );

        return loginUser;
    }

    @Override
    public SysAuthMenuRespVO convertTreeNode(SysMenuDO menu) {
        if ( menu == null ) {
            return null;
        }

        SysAuthMenuRespVOBuilder sysAuthMenuRespVO = SysAuthMenuRespVO.builder();

        sysAuthMenuRespVO.id( menu.getId() );
        sysAuthMenuRespVO.parentId( menu.getParentId() );
        sysAuthMenuRespVO.name( menu.getName() );
        sysAuthMenuRespVO.path( menu.getPath() );
        sysAuthMenuRespVO.component( menu.getComponent() );
        sysAuthMenuRespVO.icon( menu.getIcon() );

        return sysAuthMenuRespVO.build();
    }

    @Override
    public LoginUser convert(SysUserProfileUpdateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        return loginUser;
    }

    @Override
    public LoginUser convert(SysUserProfileUpdatePasswordReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        return loginUser;
    }
}
