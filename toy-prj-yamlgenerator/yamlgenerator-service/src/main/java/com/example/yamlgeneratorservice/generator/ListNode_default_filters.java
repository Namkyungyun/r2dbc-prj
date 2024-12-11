package com.example.yamlgeneratorservice.generator;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ListNode_default_filters {
    private String name;
    private String args;
    private String baseMessage;
    private String preLogger;
    private String postLogger;
    public ListNode_default_filters link;    // 다른 노드를 참조할 링크 노드

    public ListNode_default_filters() {
        String name = null;
        String args = null;
        String baseMessage = null;
        String preLogger = null;
        String postLogger = null;
    }

    public ListNode_default_filters(String name, String args, String baseMessage, String preLogger, String postLogger) {
        this.name = name;
        this.args = args;
        this.baseMessage = baseMessage;
        this.preLogger = preLogger;
        this.postLogger = postLogger;
        this.link = null;
    }

    public ListNode_default_filters(String name, String args, String baseMessage, String preLogger, String postLogger, ListNode_default_filters link) {
        this.name = name;
        this.args = args;
        this.baseMessage = baseMessage;
        this.preLogger = preLogger;
        this.postLogger = postLogger;
        this.link = link;
    }

}
