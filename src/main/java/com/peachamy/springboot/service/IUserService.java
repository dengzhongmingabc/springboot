package com.peachamy.springboot.service;

import com.peachamy.springboot.entry.User;
import com.peachamy.springboot.utils.service.IBaseService;
import com.peachamy.springboot.utils.service.Page;

import java.util.Map;

public interface IUserService extends IBaseService {


    User queryByUserName(String userName)throws Exception;

    public Page queryUserPageList(Map<String,String> where, int currentPage, int pageSize)  throws Exception;
}