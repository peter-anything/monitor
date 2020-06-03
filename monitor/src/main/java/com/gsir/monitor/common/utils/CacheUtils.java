package com.gsir.monitor.common.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CacheUtils {
    private StringRedisTemplate stringRedisTemplate;

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 
     * @Description (����key��ȷƥ��ɾ��)
     * @author peter.wang
     * @Date 2020��5��25��
     * @version 1.0.0
     * @param key
     */
    public void deleteByKey(String key) {
        if (key != null) {
            redisTemplate.delete(key);
        }
    }

    /**
     * ����ɾ��<br>
     * ���ò�����ִ��ģ����ѯ���뾡����Ҫʹ�ã�����Ӱ�����ܻ���ɾ��
     * 
     * @param pattern
     */
    public void batchDel(String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    /**
     * ����ɾ��<br>
     * ���ò�����ִ��ģ����ѯ���뾡����Ҫʹ�ã�����Ӱ�����ܻ���ɾ��
     * 
     * @param pattern
     */
    public Integer getInt(String key) {
        String val = stringRedisTemplate.boundValueOps(key).get();
        if (!StringUtils.isEmpty(val)) {
            return Integer.valueOf(val);
        }

        return null;
    }

    /**
     * ȡ�û��棨�ַ������ͣ�
     * 
     * @param key
     * @return
     */
    public String getStr(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * ȡ�û��棨�ַ������ͣ�
     * 
     * @param key
     * @return
     */
    public String getStr(String key, boolean retain) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return value;
    }

    /**
     * ��ȡ����<br>
     * ע��������������(Character����)����ֱ��ʹ��get(String key, Class<T> clazz)ȡֵ
     * 
     * @param key
     * @return
     */
    public Object getObj(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * ��ȡ����<br>
     * ע��java 8�ֻ������͵�������ֱ��ʹ��get(String key, Class<T> clazz)ȡֵ
     * 
     * @param key
     * @param retain �Ƿ���
     * @return
     */
    public Object getObj(String key, boolean retain) {
        Object obj = redisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return obj;
    }

    /**
     * ��ȡ����<br>
     * ע���÷����ݲ�֧��Character��������
     * 
     * @param key   key
     * @param clazz ����
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * ��ȡ����json����<br>
     * 
     * @param key   key
     * @param clazz ����
     * @return
     * @return
     * @throws Exception
     */
    public <T> T getJson(String key, Class<T> clz) throws Exception {
        String jsonStr = null;
        jsonStr = this.getStr(key);
        if (jsonStr == null) {
            return null;
        } else {
            return (T) JsonUtils.jsonToBean(jsonStr, clz);
        }
    }

    /**
     * ��value����д�뻺��
     * 
     * @param key
     * @param value
     * @param time  ʧЧʱ��(��)
     */
    public void set(String key, Object value, Long time) {

        if (value.getClass().equals(String.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Integer.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Double.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Float.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Short.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Long.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Boolean.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    
        if (time != null && time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * ��value������JSON��ʽд�뻺��
     * 
     * @param key
     * @param value
     * @param time
     *            ʧЧʱ��(��)
     */
    public  void setJson(String key, Object value, Long time) {
        try {
            stringRedisTemplate.opsForValue().set(key, JsonUtils.beanToJson(value));
            if (time != null && time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (IOException e) {
        }
    }


    /**
     * ����key����field��ֵ
     * 
     * @param key
     *            ����key
     * @param field
     *            �������field
     * @param value
     *            �������fieldֵ
     */
    public  void setJsonField(String key, String field, String value) {
        JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
        obj.put(field, value);
        stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
    }


    /**
     * �ݼ�����
     * 
     * @param key
     * @param by
     * @return
     */
    public  double decr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }
 
    /**
     * ��������
     * 
     * @param key
     * @param by
     * @return
     */
    public  double incr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * ��ȡdouble����ֵ
     * 
     * @param key
     * @return
     */
    public  double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (!StringUtils.isEmpty(value)) {
            return Double.valueOf(value);
        }
        return 0d;
    }
 
    /**
     * ����double����ֵ
     * 
     * @param key
     * @param value
     * @param time
     *            ʧЧʱ��(��)
     */
    public  void setDouble(String key, double value, Long time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (time!=null&&time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * ����double����ֵ
     * 
     * @param key
     * @param value
     * @param time
     *            ʧЧʱ��(��)
     */
    public  void setInt(String key, int value, Long time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (time!=null&&time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }
 
    /**
     * ��mapд�뻺��
     * 
     * @param key
     * @param map
     * @param time
     *            ʧЧʱ��(��)
     */
    public  <T> void setMap(String key, Map<String, T> map, Long time) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * ��key��Ӧ��map����ӻ������
     * 
     * @param key
     * @param map
     */
    public  <T> void addMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
 
    /**
     * ��key��Ӧ��map����ӻ������
     * 
     * @param key
     *            cache����key
     * @param field
     *            map��Ӧ��key
     * @param value
     *            ֵ
     */
    public  void addMap(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }
 
    /**
     * ��key��Ӧ��map����ӻ������
     * 
     * @param key
     *            cache����key
     * @param field
     *            map��Ӧ��key
     * @param obj
     *            ����
     */
    public  <T> void addMap(String key, String field, T obj) {
        redisTemplate.opsForHash().put(key, field, obj);
    }
 
    /**
     * ��ȡmap����
     * 
     * @param key
     * @param clazz
     * @return
     */
    public  <T> Map<String, T> mget(String key, Class<T> clazz) {
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }
 
    /**
     * ��ȡmap�����е�ĳ������
     * 
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public  <T> T getMapField(String key, String field, Class<T> clazz) {
        return (T) redisTemplate.boundHashOps(key).get(field);
    }
 
        /**
         * ��ȡmap����
         * 
         * @param key
         * @param clazz
         * @return
         */
//        public  <T> T getMap(String key, Class<T> clazz) {
//            BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
//            Map<String, String> map = boundHashOperations.entries();
//            return JsonUtils.jsonToBean(map, clazz);
//        }
    
        /**
     * ɾ��map�е�ĳ������
     * 
     * @author lh
     * @date 2016��8��10��
     * @param key
     *            map��Ӧ��key
     * @param field
     *            map�иö����key
     */
    public void delMapField(String key, String... field) {
        BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }
 
    /**
     * ָ�������ʧЧʱ��
     * 
     * @author FangJun
     * @date 2016��8��14��
     * @param key
     *            ����KEY
     * @param time
     *            ʧЧʱ��(��)
     */
    public  void expire(String key, Long time) {
        if (time!=null&&time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }
 
    /**
     * ���set
     * 
     * @param key
     * @param value
     */
    public  void sadd(String key, String... value) {
        redisTemplate.boundSetOps(key).add(value);
    }
 
    /**
     * ɾ��set�����еĶ���
     * 
     * @param key
     * @param value
     */
    public  void srem(String key, String... value) {
        redisTemplate.boundSetOps(key).remove(value);
    }
 
    /**
     * set������
     * 
     * @param oldkey
     * @param newkey
     */
    public  void srename(String oldkey, String newkey) {
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }
 
    /**
     * ���Ż���
     * 
     * @author fxl
     * @date 2016��9��11��
     * @param key
     * @param value
     * @param time
     * @throws IOException 
     */
    public  void setIntForPhone(String key, Object value, int time) throws IOException {
        stringRedisTemplate.opsForValue().set(key, JsonUtils.beanToJson(value));
        if (time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }
 
    /**
     * ģ����ѯkeys
     * 
     * @param pattern
     * @return
     */
    public  Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    
    /**
     * 
     * @Description (���key�Ƿ���ڣ�����booleanֵ )
     * @author feizhou
     * @Date 2018��5��29������5:37:40  
     * @version 1.0.0
     * @param key
     * @return
     */
    public  Boolean   ishasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
}
