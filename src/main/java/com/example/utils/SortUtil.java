package com.example.utils;

import com.example.dto.AbstractDTO;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class SortUtil {

    public static Sort sortUtil(Map<String, String> params, AbstractDTO dto) {
        Sort sort;
        if (params.get("sort") == null) {
            sort = Sort.by(Sort.Direction.ASC, "name");
        } else {
            dto.setColumnName(params.get("column"));
            dto.setSortType(params.get("sort"));
            if (_sortUtil(params.get("sort")).equals("ASC"))
                sort = Sort.by(Sort.Direction.ASC, params.get("column"));
            else
                sort = Sort.by(Sort.Direction.DESC, params.get("column"));
        }
        return sort;
    }

    public static String _sortUtil(String sort) {
        if (sort.equals("asc")){
            return "ASC";
        }
        return "DESC";
    }

}
