package com.hublotcloud.utils;

import java.util.Map;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil
 *
 */
public class JacksonUtil {

    /**
     * 对象转json
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String returnJson = "";
        try {
            returnJson = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnJson;
    }

    /**
     * 将 map 转换成 json 字符串
     */
    public static String mapToJson(Map<String, Object> map) throws Exception {
        if (ObjectUtils.isEmpty(map)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    /**
     * 从返回的 json 中获取数据
     */
    public static String getFromJson(String json, String key) throws Exception {
        if (null  == json || "".equals(json) || 0 == json.length()) {
            return "";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.get(key).asText();
    }

}
