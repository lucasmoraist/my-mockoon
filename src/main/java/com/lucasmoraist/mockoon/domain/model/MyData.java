package com.lucasmoraist.mockoon.domain.model;

import java.util.Map;

public record MyData(
        Object data,
        Map<String, Object> metadata
) {

}
