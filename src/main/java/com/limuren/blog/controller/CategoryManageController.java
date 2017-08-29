package com.limuren.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.limuren.blog.common.Const;
import com.limuren.blog.common.Const.Role;
import com.limuren.blog.common.ResponseCode;
import com.limuren.blog.common.ServerResponse;
import com.limuren.blog.pojo.User;
import com.limuren.blog.service.CategoryService;
import com.limuren.blog.service.UserService;

@RestController
public class CategoryManageController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	
	@PostMapping("addCategory.do")
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否有权限
        if(userService.getUserRole(user.getUserid())>=Role.AUTHOR.getRole()){
            return categoryService.addCategory(categoryName,parentId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
	
	@PostMapping("setCategoryName.do")
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(userService.getUserRole(user.getUserid())>=Role.AUTHOR.getRole()){
            //更新categoryName
            return categoryService.updateCategoryName(categoryId,categoryName);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
	
	@GetMapping("getChildrenParallelCategory.do")
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(userService.getUserRole(user.getUserid())>=Role.AUTHOR.getRole()){
            //查询子节点的category信息,并且不递归,保持平级
            return categoryService.getChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
	@GetMapping("getCategoryAndDeepChildrenCategory.do")
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(userService.getUserRole(user.getUserid())>=Role.AUTHOR.getRole()){
            //查询当前节点的id和递归子节点的id
//            0->10000->100000
            return categoryService.selectCategoryAndChildrenById(categoryId);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

	
}
