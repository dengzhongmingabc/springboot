package com.peachamy.springboot.controller;

import com.peachamy.springboot.entry.User;
import com.peachamy.springboot.sercurity.MyUserDtls;
import com.peachamy.springboot.service.IUserService;
import com.peachamy.springboot.utils.AppConst;
import com.peachamy.springboot.utils.JSONHelper;
import com.peachamy.springboot.utils.service.Page;
import com.peachamy.springboot.utils.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserService userServiceImpl;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            MyUserDtls currentPrincipalName = (MyUserDtls) authentication.getPrincipal();
            return "xxxx";
        } catch (Exception e) {
            e.printStackTrace();
            return "xxxx";
        }
    }

    @ResponseBody
    @RequestMapping("/userLogin")
    public void userLogin(String username,String password){
        try {
            if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            User user = userServiceImpl.queryByUserName(username);
            if(null==user){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            if(!password.equals(user.getPassword())){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            this.setSession(session_key,user);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(user));
        } catch (Exception e) {
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ResponseBody
    @RequestMapping("/regist")
    public void regist(String username,String password){
        try {
            if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_ERR_CODE,AppConst.ARGS_ERR_MSG));
                return;
            }
            User user = userServiceImpl.queryByUserName(username);
            if(null!=user){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("用户已存在"));
                return;
            }
            User user_ = new User();
            user_.setPassword(password);
            user_.setUserName(username);
            userServiceImpl.save(user_);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(user_));
        } catch (Exception e) {
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ResponseBody
    @RequestMapping("/userPageList")
    public void userPageList(String username,int currentPage, int pageSize){
        try {
            Map args = new HashMap();
            args.put("username",username);
            Page page = userServiceImpl.queryUserPageList(args,currentPage,pageSize);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(page));
        } catch (Exception e) {
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }
}
