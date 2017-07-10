package org.hrd.spring.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.hrd.spring.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
	
	@Insert("<script>"
			+ " INSERT INTO user_roles("
			+ " user_id,"
			+ " role_id"
			+ " )VALUES"
			+ " <foreach collection='role' item='r' saparator=','> "
			+ " ("
			+ " #{u},"
			+ " #{r.id})"
			+ " </foreach>"
			+ " </script>")
	public boolean addRole(@Param("role")List<Role> role, @Param("u") int userId);
}
