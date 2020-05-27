package com.gsir.monitor.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * kafkaProducer����������producer�����ļ��п���
 * 
 * @author wangb
 *
 */
@SuppressWarnings("rawtypes")
public class KafkaProducerListener implements ProducerListener {
    protected final Logger LOG = LoggerFactory.getLogger("kafkaProducer");

    /**
     * ������Ϣ�ɹ������
     */
    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        LOG.info("==========kafka�������ݳɹ�����־��ʼ��==========");
        LOG.info("----------topic:" + topic);
        LOG.info("----------partition:" + partition);
        LOG.info("----------key:" + key);
        LOG.info("----------value:" + value);
        LOG.info("----------RecordMetadata:" + recordMetadata);
        LOG.info("~~~~~~~~~~kafka�������ݳɹ�����־������~~~~~~~~~~");
    }

    /**
     * ������Ϣ��������
     */
    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        LOG.info("==========kafka�������ݴ�����־��ʼ��==========");
        LOG.info("----------topic:" + topic);
        LOG.info("----------partition:" + partition);
        LOG.info("----------key:" + key);
        LOG.info("----------value:" + value);
        LOG.info("----------Exception:" + exception);
        LOG.info("~~~~~~~~~~kafka�������ݴ�����־������~~~~~~~~~~");
        exception.printStackTrace();
    }

    /**
     * ��������ֵ�����Ƿ�����kafkaProducer������
     */
    @Override
    public boolean isInterestedInSuccess() {
        LOG.info("///kafkaProducer����������///");
        return true;
    }

}
