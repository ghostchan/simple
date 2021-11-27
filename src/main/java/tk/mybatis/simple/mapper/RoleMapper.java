package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {
    @Select({"select id,role_name roleName,enabled,create_by createBy,create_time createTime from sys_role where id = #{id}"})
    SysRole selectById(Long id);
}
