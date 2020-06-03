package com.gsir.monitor.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsir.monitor.common.ResponseData;
import com.gsir.monitor.kafka.producer.KafkaProducerServer;

@Controller
@RequestMapping("/kafka")
public class KafkaController {
    private KafkaProducerServer kafkaProducer;

    @RequestMapping(value = "/produce/single-value/{value}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<Object> produce(@PathVariable(value="value") String value) {
        String topic = "orderTopic";
        String ifPartition = "1";
        Integer partitionNum = 3;
        String role = "test";// 用来生成key
        Map<String, Object> res = kafkaProducer.sendMessageForTemplate(topic, value, ifPartition, partitionNum, role);

        System.out.println("测试结果如下：===============");
        String message = (String) res.get("message");
        String code = (String) res.get("code");

        System.out.println("code:" + code);
        System.out.println("message:" + message);

        return ResponseData.success();
    }
}
