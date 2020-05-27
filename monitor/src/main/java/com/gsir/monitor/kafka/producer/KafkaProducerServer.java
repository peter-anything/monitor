package com.gsir.monitor.kafka.producer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;
import com.gsir.monitor.kafka.constant.KafkaMessageConstant;

/**
 * kafkaProducerģ�� ʹ�ô�ģ�巢����Ϣ
 * 
 * @author peter.wang
 *
 */
@Component
public class KafkaProducerServer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * ����keyֵ��ȡ��������
     * 
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum) {
        if (key == null) {
            Random random = new Random();

            return random.nextInt(partitionNum);
        } else {
            int result = Math.abs(key.hashCode()) % partitionNum;

            return result;
        }
    }

    /**
     * kafka������Ϣģ��
     * 
     * @param topic        ����
     * @param value        messageValue
     * @param ifPartition  �Ƿ�ʹ�÷��� 0��\1����
     * @param partitionNum ������ ����Ƿ�ʹ�÷���Ϊ0,�������������0
     * @param role         ��ɫ:bbc app erp...
     */
    public Map<String, Object> sendMessageForTemplate(String topic, Object value, String ifPartition,
            Integer partitionNum, String role) {
        String key = role + value.hashCode();
        String valueString = JSON.toJSONString(value);
        if (ifPartition.equals("1")) {
            // ��ʾʹ�÷���
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key,
                    valueString);
            Map<String, Object> res = checkProRecord(result);

            return res;
        } else {
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            Map<String, Object> res = checkProRecord(result);

            return res;
        }
    }

    /**
     * ��鷢�ͷ��ؽ��record
     * 
     * @param res
     * @return
     */
    private Map<String, Object> checkProRecord(ListenableFuture<SendResult<String, String>> res) {
        Map<String, Object> m = new HashMap<String, Object>();
        if (res != null) {
            try {
                SendResult<String, String> r = res.get();
                /* ���recordMetadata��offset���ݣ������producerRecord */
                Long offsetIndex = r.getRecordMetadata().offset();
                if (offsetIndex != null && offsetIndex >= 0) {
                    m.put("code", KafkaMessageConstant.SUCCESS_CODE);
                    m.put("message", KafkaMessageConstant.SUCCESS_MES);

                    return m;
                } else {
                    m.put("code", KafkaMessageConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message", KafkaMessageConstant.KAFKA_NO_OFFSET_MES);

                    return m;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                m.put("code", KafkaMessageConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMessageConstant.KAFKA_SEND_ERROR_MES);

                return m;
            } catch (ExecutionException e) {
                e.printStackTrace();
                m.put("code", KafkaMessageConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMessageConstant.KAFKA_SEND_ERROR_MES);

                return m;
            }
        } else {
            m.put("code", KafkaMessageConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMessageConstant.KAFKA_NO_RESULT_MES);

            return m;
        }
    }
}
