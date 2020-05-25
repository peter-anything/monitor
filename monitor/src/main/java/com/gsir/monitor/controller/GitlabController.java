package com.gsir.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gitlab")
public class GitlabController {
    @RequestMapping(value = "/handler", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> handler() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("messges", "success");
    
        return map;
    }
}
