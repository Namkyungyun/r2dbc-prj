package com.example.yamlgeneratorservice.vo;

import lombok.Data;


@Data
public class RequestFilter {
    private String name;
    private String args;
    private String baseMessage;
    private String preLogger;
    private String postLogger;
}