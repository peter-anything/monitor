package com.gsir.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.gsir.monitor.common.ResponseData;
import com.gsir.monitor.common.utils.CacheUtils;
import com.gsir.monitor.common.utils.Requests;
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
    public ResponseData<String> list() {
        User user = userService.getUserById(2);
        cacheUtils.setJson(user.getUserName(), user, 60L);
        try {
            user = cacheUtils.getJson(user.getUserName(), User.class);
        } catch (Exception e) {
            return ResponseData.success();
        } finally {
            
        }
        
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("private_token", "mzRPeeiMrTSmRUUukNNT");
        JSONArray result = (JSONArray) Requests.getJson("https://gitlab.g-sir.com.cn/api/v4/projects", headers, null);
        return ResponseData.success(Requests.get("https://gitlab.g-sir.com.cn/api/v4/projects?private_token=mzRPeeiMrTSmRUUukNNT", null, headers));
    }
}
