package com.example.handy_home.core.search.domain;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Map<String, Object>> getKeywordComplexes(String keyword);
    Map<String, Object> getAreaListFromComplexNo(String complexNo);
}
