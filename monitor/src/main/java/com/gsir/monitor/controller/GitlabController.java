package com.gsir.monitor.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsir.monitor.common.ResponseData;
import com.gsir.monitor.pojo.Project;
import com.gsir.monitor.service.ProjectService;

@Controller
@RequestMapping("/gitlab")
public class GitlabController {
    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/handler", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<Object> handler() {
        List<Project> projects = projectService.getAllProject();
        return ResponseData.success(projects);
    }
}
