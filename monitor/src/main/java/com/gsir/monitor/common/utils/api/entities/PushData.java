package com.gsir.monitor.common.utils.api.entities;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PushData {
    private String ref;
    @JSONField(name = "ref_type")
    private String refType;
}