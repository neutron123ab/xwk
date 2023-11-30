package cn.code51.dashboard.modules.tool.dal.mysql.codegen;

import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.modules.tool.dal.dataobject.codegen.ToolSchemaTableDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ToolSchemaTableMapper extends BaseMapperX<ToolSchemaTableDO> {

    default List<ToolSchemaTableDO> selectList(Collection<String> tableSchemas, String tableName, String tableComment) {
        return selectList(new QueryWrapperX<ToolSchemaTableDO>().in("table_schema", tableSchemas)
                .likeIfPresent("table_name", tableName)
                .likeIfPresent("table_comment", tableComment));
    }

    default List<ToolSchemaTableDO> selectListByTableSchema(String tableSchema) {
        return selectList(new QueryWrapper<ToolSchemaTableDO>().eq("table_schema", tableSchema));
    }

    default ToolSchemaTableDO selectByTableName(String tableName) {
        return selectOne(new QueryWrapper<ToolSchemaTableDO>().eq("table_name", tableName));
    }

}
