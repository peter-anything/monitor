package com.gsir.monitor.kafka.producer;

import java.util.Map;

public class KafkaProducerTest {
    public static void main(String[] args) {
        
        KafkaProducerServer kafkaProducer = new KafkaProducerServer();
        String topic = "orderTopic";
        String value = "test";
        String ifPartition = "0";
        Integer partitionNum = 3;
        String role = "test";//��������key
        Map<String,Object> res = kafkaProducer.sendMessageForTemplate
                (topic, value, ifPartition, partitionNum, role);
        
        System.out.println("���Խ�����£�===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");
        
        System.out.println("code:"+code);
        System.out.println("message:"+message);
    }
}
