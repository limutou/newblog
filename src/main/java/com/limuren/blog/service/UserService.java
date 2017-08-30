package com.limuren.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limuren.blog.common.ResponseCode;
import com.limuren.blog.common.ServerResponse;
import com.limuren.blog.mapper.UserMapper;
import com.limuren.blog.pojo.User;
import com.limuren.blog.util.MD5Util;
import com.limuren.blog.util.MyStringUtils;

@Service
public class UserService {
	
    private Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserMapper userMapper;
	
    public ServerResponse<String> register(User user){
    	if(MyStringUtils.isNotBlank(user.getUsername())
    			&&MyStringUtils.isNotBlank(user.getPassword())
    			&&MyStringUtils.isNotBlank(user.getEmail())
    			&&MyStringUtils.isNotBlank(user.getNickname())
    			) {
    		
        	int count;
        	//统计相同用户名
        	count = userMapper.countUserByUsername(user.getUsername());
        	//用户名已存在
        	if(count>0) return ServerResponse.createByErrorMessage("用户名已存在");
        	//统计相同邮箱
        	count = userMapper.countEmailByEmail(user.getEmail());
        	//邮箱已存在
        	if(count>0) return ServerResponse.createByErrorMessage("邮箱已存在");
        	
        	String MD5Password = MD5Util.MD5EncodeUtf8(user.getPassword());
        	user.setPassword(MD5Password);
        	
        	count = userMapper.insertSelective(user);
        	
            if(count == 0){
                return ServerResponse.createByErrorMessage("注册失败");
            }
            return ServerResponse.createBySuccessMessage("注册成功");
        	
    	}
    	return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
    }
    
    public ServerResponse<User> login(String username, String password){
    	if(MyStringUtils.isNotBlank(username)
    			&&MyStringUtils.isNotBlank(password)) {
    		
        	String MD5Password = MD5Util.MD5EncodeUtf8(password);

    		int count;
    		count = userMapper.countUserByUsernameAndPassword(username, MD5Password);
    		
    		if(count==1) {
                return ServerResponse.createBySuccess("登录成功",userMapper.selectUserByUsernameAndPassword(username, MD5Password));
    		}else {
                return ServerResponse.createByErrorMessage("登录失败");
    		}
    		
    	}
    	return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
    }
    public ServerResponse<String> resetPassword(Integer userid,String passwordOld,String passwordNew){
    	if(MyStringUtils.isNotBlank(passwordOld)
    			&&MyStringUtils.isNotBlank(passwordNew)
    			&&userid!=null) {
    		passwordNew = MD5Util.MD5EncodeUtf8(passwordNew);
    		passwordOld = MD5Util.MD5EncodeUtf8(passwordOld);
    		
    		int count = 0;
    		count = userMapper.updatePasswordByUsernameAndPassword(userid, passwordOld, passwordNew);
    		if(count==1) {
    			return ServerResponse.createBySuccessMessage("修改密码成功");
    		}else if(count==0) {
    			return ServerResponse.createBySuccessMessage("修改密码失败");
    		}
    		logger.error("修改用户密码时出错，用户ID为：{}，修改前密码为：{}，修改后密码为：{}",userid,passwordOld,passwordNew);
			return ServerResponse.createBySuccessMessage("系统发生异常");
    	}
    	return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
    }
    public ServerResponse<User> getInformation(Integer userId){
    	User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        return ServerResponse.createBySuccess(user);
    }
    public ServerResponse<User> updateInformation(User user){
    	
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
    	
    	if(user!=null&&user.getUserid()!=null) {
    		int count;
    		if(user.getEmail()!=null){
    			count = userMapper.countOtherUserEmail(user.getUserid(), user.getEmail());
    			if(count>0) return ServerResponse.createByErrorMessage("该邮箱已被注册，请修改邮箱");
    		}
    		count = userMapper.updateUserInfoByUserid(user);
    		if(count==0) return ServerResponse.createByErrorMessage("用户信息更新失败");
    		if(count==1) return ServerResponse.createBySuccessMessage("用户信息更新成功");
    		logger.error("更新用户信息异常{}",user);
    		return ServerResponse.createByErrorMessage("用户信息更新异常");
    		
    	}
    	return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "传参错误");
    }
    public Integer getUserRole(Integer userid) {
    	return userMapper.selectRoleByUserid(userid);
    }
    public Integer setRoleANDStatusByUserid(Integer role,Integer status,Integer userid) {
    	return userMapper.setRoleANDStatusByUserid(role, status, userid);
    }
}
