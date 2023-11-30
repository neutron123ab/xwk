package cn.code51.dashboard.modules.infra.convert.redis;

import cn.code51.dashboard.framework.redis.core.RedisKeyDefine;
import cn.code51.dashboard.modules.infra.controller.redis.vo.InfRedisKeyRespVO;
import cn.code51.dashboard.modules.infra.controller.redis.vo.InfRedisKeyRespVO.InfRedisKeyRespVOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T10:37:35+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class RedisConvertImpl implements RedisConvert {

    @Override
    public List<InfRedisKeyRespVO> convertList(List<RedisKeyDefine> list) {
        if ( list == null ) {
            return null;
        }

        List<InfRedisKeyRespVO> list1 = new ArrayList<InfRedisKeyRespVO>( list.size() );
        for ( RedisKeyDefine redisKeyDefine : list ) {
            list1.add( redisKeyDefineToInfRedisKeyRespVO( redisKeyDefine ) );
        }

        return list1;
    }

    protected InfRedisKeyRespVO redisKeyDefineToInfRedisKeyRespVO(RedisKeyDefine redisKeyDefine) {
        if ( redisKeyDefine == null ) {
            return null;
        }

        InfRedisKeyRespVOBuilder infRedisKeyRespVO = InfRedisKeyRespVO.builder();

        infRedisKeyRespVO.keyTemplate( redisKeyDefine.getKeyTemplate() );
        infRedisKeyRespVO.keyType( redisKeyDefine.getKeyType() );
        infRedisKeyRespVO.valueType( redisKeyDefine.getValueType() );
        infRedisKeyRespVO.timeoutType( redisKeyDefine.getTimeoutType() );
        infRedisKeyRespVO.timeout( redisKeyDefine.getTimeout() );
        infRedisKeyRespVO.memo( redisKeyDefine.getMemo() );

        return infRedisKeyRespVO.build();
    }
}
