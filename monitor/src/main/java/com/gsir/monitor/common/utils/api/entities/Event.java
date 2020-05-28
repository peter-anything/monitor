package com.gsir.monitor.common.utils.api.entities;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Event {
    @JSONField(name = "project_id")
    private Integer projectId;
    @JSONField(name = "push_data")
    private PushData pushData;
}