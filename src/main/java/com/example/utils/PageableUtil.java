package com.example.utils;

import com.example.dto.AbstractDTO;

import java.util.Map;

public class PageableUtil {

    public static AbstractDTO pageable(AbstractDTO result, Map<String, String> params, long countItem) {
        if (params.get("page") == null) params.put("page", "1");
        if (params.get("limit") == null) params.put("limit", "10");
        result.setTotalItem(countItem);
        result.setLimit(params.get("limit").equals("all") ? (int) countItem: Integer.parseInt(params.get("limit")));
        int totalPage = (int) Math.ceil(1.0 * countItem  / result.getLimit());
        result.setTotalPage(totalPage);
        result.setCurrentPage(Integer.parseInt(params.get("page")));
        return result;
    }

}
