package com.gsir.monitor.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsir.monitor.common.ResponseData;
import com.gsir.monitor.common.utils.api.GitlablabAPI;
import com.gsir.monitor.common.utils.api.entities.Event;
import com.gsir.monitor.common.utils.api.entities.PushData;
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
        for (Project project : projects) {
            List<Event> events = GitlablabAPI.getEventsByProjectId(project.getId());
            for (Event event : events) {
                PushData pushData = event.getPushData();
                if ("master".equals(pushData.getRef()) || "tag".equals(pushData.getRefType())) {
                    
                }
            }
        }

        return ResponseData.success(projects);
    }
}
