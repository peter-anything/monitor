package com.gsir.monitor.common.utils.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.gsir.monitor.common.utils.Requests;
import com.gsir.monitor.common.utils.api.entities.Event;

public class GitlablabAPI {

    public static List<Event> getEventsByProjectId(int projectId) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("private_token", "mzRPeeiMrTSmRUUukNNT");
        String result = Requests.get(String.format("https://gitlab.g-sir.com.cn/api/v4/projects/%d/events", projectId), headers, null);
        List<Event> events = JSON.parseArray(result, Event.class);

        return events;
    }
}
