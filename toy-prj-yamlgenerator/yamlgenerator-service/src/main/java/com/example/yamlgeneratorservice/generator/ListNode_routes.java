package com.example.yamlgeneratorservice.generator;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// List를 구성하는 Node 클래스
@Data
@Component
class ListNode_routes{
    private String id;
    private String uri;
    private ArrayList<String> predicates = new ArrayList<String>();
    public ListNode_routes link;    // 다른 노드를 참조할 링크 노드

    public ListNode_routes() {
        this.id = null;
        this.uri = null;
        this.predicates = null;
        this.link = null;
    }

    public ListNode_routes(String id,String uri,ArrayList<String> predicates) {
        this.id = id;
        this.uri = uri;
        this.predicates = predicates;
        this.link = null;
    }

    public ListNode_routes(String id,String uri,ArrayList<String> predicates,ListNode_routes link) {
        this.id = id;
        this.uri = uri;
        this.predicates = predicates;
        this.link = link;
    }

}
