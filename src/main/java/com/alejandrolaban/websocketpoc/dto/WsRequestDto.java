package com.alejandrolaban.websocketpoc.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WsRequestDto {
    String name;
}
