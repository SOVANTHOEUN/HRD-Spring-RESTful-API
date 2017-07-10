package org.hrd.spring.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.hrd.spring.entities.Role;
import org.hrd.spring.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	
	@Insert("INSERT INTO users ("
			+ "username,"
			+ " email,"
			+ " password,"
			+ " gender,"
			+ " device,"
			+ " uuid"
			+ ") VALUES ("
			+ " #{user.username},"
			+ " #{user.email},"
			+ " #{user.password},"
			+ " #{user.gender},"
			+ " #{user.device},"
			+ " #{user.uuid})")
	@SelectKey(
			statement = "SELECT last_value FROM users_id_seq",
			keyProperty = "user.id",
			keyColumn = "last_value",
			before = false,
			resultType = int.class
			)
	public boolean addUser(@Param("user")User user);
	
	@Select("SELECT"
			+ " U.username,"
			+ " U.email,"
			+ " U.password,"
			+ " U.dob,"
			+ " U.gender,"
			+ " U.socail_id,"
			+ " U.socail_type,"
			+ " U.device,"
			+ " U.id,"
			+ " U.created_date,"
			+ " U.remark,"
			+ " U.status,"
			+ " U.index,"
			+ " U.uuid"
			+ " FROM"
			+ " users U"
			+ " WHERE status='1';")
	@Results(value={
			@Result(property="createdDate", column="created_date"),
			@Result(property="roles", column="id", 
					many = @Many(select = "findRoleByUserID"))
	})
	public List<User> findAll();
	
	@Select("SELECT"
			+ " R.name,"
			+ " UR.user_id"
			+ " FROM"
			+ " roles R INNER JOIN user_roles UR"
			+ " ON R.id = UR.role_id"
			+ " WHERE UR.user_id = #{u_id}")
	public List<Role> findRoleByUserID(@Param("u_id") int u_id);

	@Select("SELECT"
			+ " U.username,"
			+ " U.email,"
			+ " U.password,"
			+ " U.dob,"
			+ " U.gender,"
			+ " U.socail_id,"
			+ " U.socail_type,"
			+ " U.device,"
			+ " U.id,"
			+ " U.created_date,"
			+ " U.remark,"
			+ " U.status,"
			+ " U.index,"
			+ " U.uuid"
			+ " FROM"
			+ " users U"
			+ " WHERE uuid = #{uuid}")
	@Results(value={
			@Result(property="createdDate", column="created_date"),
			@Result(property="roles", column="id", 
					many = @Many(select = "findRoleByUserID"))
	})
	public User findUserByUUID(String uuid);
	
	@Delete("DELETE"
			+ " FROM "
			+ " users"
			+ " WHERE uuid = #{uuid};")
	@Results(value={
			@Result(property="createdDate", column="created_date"),
			@Result(property="roles", column="id", 
					many = @Many(select = "findRoleByUserID"))
	})
	public boolean deleteAllByUUID(@Param("uuid") String uuid);
	

}
