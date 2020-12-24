package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {

    public static <T> String listToJson(List<T> tlist) {
        ObjectMapper mapper = new ObjectMapper();
        String data = "";
        try {
            data = mapper.writeValueAsString(tlist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }

}
