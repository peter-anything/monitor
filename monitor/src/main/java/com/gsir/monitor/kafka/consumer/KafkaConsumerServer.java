package com.gsir.monitor.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class KafkaConsumerServer implements MessageListener<String, String> {
    protected final Logger logger = LoggerFactory.getLogger("kafkaConsumer");

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        logger.info("=============kafkaConsumer开始消费=============");
        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        long offset = record.offset();
        int partition = record.partition();

        logger.info("-------------topic:" + topic);
        logger.info("-------------value:" + value);
        logger.info("-------------key:" + key);
        logger.info("-------------offset:" + offset);
        logger.info("-------------partition:" + partition);
        logger.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }

}
