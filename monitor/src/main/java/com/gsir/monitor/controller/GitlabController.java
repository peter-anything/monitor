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
    private ProjectService projectService;

    public void quickSort(Integer[] arr, int low, int high) {
        if (low <= high) {
            return ;
        }
        int pivot = low;
        int pivotValue = arr[pivot];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[j] >= pivotValue) {
                --j;
            }
            int tmp = arr[i];
            arr[j] = tmp;
            arr[i] = tmp;
            while (arr[i] <= pivotValue) {
                 ++i;
            }
        }
    }

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

        Integer[] arr = {7, 1, 2, 4, 3, 10, 0, 8};
        quickSort(arr, 0, arr.length - 1);

        return ResponseData.success(projects);
    }
}
