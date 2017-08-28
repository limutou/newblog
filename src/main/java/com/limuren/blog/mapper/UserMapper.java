package com.limuren.blog.mapper;

import org.apache.ibatis.annotations.Param;

import com.limuren.blog.pojo.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	int countUserByUsername(@Param("username") String username);

	int countEmailByEmail(@Param("email") String email);

	int countUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	int updatePasswordByUsernameAndPassword(@Param("userid") Integer userid, 
			@Param("passwordOld") String passwordOld,
			@Param("passwordNew") String passwordNew);
	
	int updateUserInfoByUserid(User user);
	
	int countOtherUserEmail(@Param("userid") Integer userid,@Param("email") String email);
	
	int selectRoleByUserid(@Param("userid") Integer userid);
	
	int setRoleANDStatusByUserid(@Param("role") Integer role,@Param("status") Integer status,@Param("userid") Integer userid);
	
}