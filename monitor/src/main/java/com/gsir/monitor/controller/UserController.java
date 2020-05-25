package com.gsir.monitor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsir.monitor.common.ResponseData;
import com.gsir.monitor.common.utils.CacheUtils;
import com.gsir.monitor.pojo.User;
import com.gsir.monitor.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private CacheUtils cacheUtils;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<User> list() {
        User user = userService.getUserById(2);
        cacheUtils.setJson(user.getUserName(), user, 60L);
        try {
            user = cacheUtils.getJson(user.getUserName(), User.class);
        } catch (Exception e) {
            return ResponseData.success();
        } finally {
            
        }

        return ResponseData.success(user);
    }
}
