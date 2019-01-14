package com.alejandrolaban.websocketpoc.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
public class WsResponseDto {
    String echo;
    Date date;
}
