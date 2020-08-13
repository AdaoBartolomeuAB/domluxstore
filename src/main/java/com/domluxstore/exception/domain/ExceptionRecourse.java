package com.domluxstore.exception.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Setter
@Builder
public class ExceptionRecourse {

    private String timestamp;
    private int status;
    private String message;
    private Object detail;


}
