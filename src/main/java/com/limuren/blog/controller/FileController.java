package com.limuren.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.limuren.blog.common.Const;
import com.limuren.blog.common.ResponseCode;
import com.limuren.blog.common.ServerResponse;
import com.limuren.blog.pojo.User;
import com.limuren.blog.service.FileService;
import com.limuren.blog.service.UserService;
import com.limuren.blog.util.PropertiesUtil;

@RestController
public class FileController {
	
	
	@Autowired
	FileService fileService;
	@Autowired
	UserService userService;
	@PostMapping("upload.do")
    public ServerResponse upload(HttpSession session,@RequestParam(value = "upload_file",required = false) MultipartFile file,HttpServletRequest request){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请先登录");
        }
        if(userService.getUserRole(user.getUserid())>=Const.Role.AUTHOR.getRole()){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(file,path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            Map fileMap = new HashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerResponse.createBySuccess(fileMap);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

	
	
}
