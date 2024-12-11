package com.example.yamlgeneratorservice.generator;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LinkedList_routes {

    private ListNode_routes head;    // ListNode 타입의 head 노드 인스턴스 변수

    // LinkedList 생성자
    public LinkedList_routes() {
        head = null;    // head 노드 초기화
    }

    // Node 삽입 (중간에 삽입)
    public void insertNode(ListNode_routes preNode, String id, String uri, ArrayList<String> predicates) {
        ListNode_routes newNode = new ListNode_routes(id,uri,predicates);    // 새로운 노드 생성


        // 이전노드의 link = 새로운 노드의 link
        newNode.link = preNode.link;

        // 이전노드의 link가 새로운 노드를 참조하도록 함.
        preNode.link = newNode;
    }

    // Node 삽입 (마지막에 삽입)
    public void insertNode(String id,String uri,ArrayList<String> predicates) {
        ListNode_routes newNode = new ListNode_routes(id,uri,predicates);    // 새로운 노드 생성
        if(head == null) {
            // head 노드가 없을때 새로운 노드 = 헤드노드
            this.head = newNode;
        } else { //head 노드가 존재할때 제일 마지막 노드를 찾아야함

            // tempNode 임시노드(마지막 노드를 찾기위해서)
            ListNode_routes tempNode = head;


            while(tempNode.link != null) {
                tempNode = tempNode.link;    // 다음 노드를 참조
            }// 마지막노드는 temp 노드가 된다.

            // tempNode(마지막 노드)의 link가 다음 노드를 참조하도록 함.
            tempNode.link = newNode;
        }
    }

    // Node 삭제(중간 노드 삭제)
    public void deleteNode(String id) {
        // preNode는 head가 가리키는 노드를 할당
        ListNode_routes preNode = head;
        // tempNode는 head가 가리키는 노드의 다음 노드. 즉, preNode의 다음 노드를 할당
        ListNode_routes tempNode = head.link;

        // 주어진 데이터가 preNode의 데이터와 일치하는 경우
        // 즉, 첫번째 노드의 데이터와 일치하는 경우
        if(id.equals( preNode.getId() )) {
            // head는 preNode의 다음 노드를 참조하도록 함.
            head = preNode.link;
            // preNode의 link는 null을 할당하여 연결을 끊음.
            preNode.link = null;
        } else {
            // tempNode가 null일 때까지 반복하여 탐색
            while(tempNode != null) {
                // 주어진 데이터와 temp 노드의 데이터가 일치할 경우.
                if(id.equals( tempNode.getId() )) {
                    // tempNode가 마지막 노드인 경우
                    if(tempNode.link == null) {
                        preNode.link = null;
                    } else {
                        // tempNode가 마지막 노드가 아닌 경우
                        // preNode의 link는 tempNode의 다음 노드를 참조.
                        // tempNode의 link는 null을 할당하여 다음 노드로의 연결을 끊음.
                        preNode.link = tempNode.link;
                        tempNode.link = null;
                    }
                    break;
                } else {
                    // 데이터가 일치하지 않을 경우
                    // preNode에 tempNode를 할당하고, tempNode에 다음 노드 할당.
                    preNode = tempNode;
                    tempNode = tempNode.link;
                }
            }
        }
    }
    // 마지막 노드 삭제
    // Node 삭제(마지막 노드 삭제)
    public void deleteNode() {
        ListNode_routes preNode;
        ListNode_routes tempNode;

        // head 노드가 null인 경우 모든 노드가 삭제되었으므로 return
        if(head == null) {
            return;
        }

        // head 노드의 link가 null인 경우
        // 노드가 1개 남았을 경우
        if(head.link == null) {
            // head에 null을 할당하여 남은 노드와의 연결을 끊음.
            head = null;
        } else {
            // preNode는 head가 가리키는 노드를 할당
            preNode = head;
            // tempNode는 head가 가리키는 노드의 다음 노드. 즉, preNode의 다음 노드를 할당
            tempNode = head.link;

            // tempNode의 link가 null이 아닐 때까지 한 노드씩 다음 노드로 이동.
            // preNode는 tempNode를 할당하고
            // tempNode는 tempNode의 다음 노드를 할당.
            // 이렇게 하면 preNode는 마지막 노드의 이전 노드가 되고, tempNode는 마지막 노드가 됨.
            while(tempNode.link != null) {
                preNode = tempNode;
                tempNode = tempNode.link;
            }

            // preNode의 link를 null로 만들어서 가장 마지막 노드를 삭제
            // 즉, preNode의 다음 노드인 tempNode로의 연결을 끊음.
            preNode.link = null;
        }
    }

    // Node 탐색
    public ListNode_routes searchNode(String id) {
        ListNode_routes tempNode = this.head;    // temp 노드에 head가 가리키는 첫 번째 할당.

        // temp 노드가 null이 아닐 때까지 반복하여 탐색
        while(tempNode != null) {
            // 주어진 데이터와 temp 노드의 데이터가 일치할 경우 해당 temp 노드를 return
            if(id.equals(tempNode.getId())) {
                return tempNode;
            } else {
                // 데이터가 일치하지 않을 경우 temp 노드에 다음 노드 할당.
                tempNode = tempNode.link;
            }
        }
        return tempNode;
    }

    // route 수정
    public void updateNode(ListNode_routes target ,String update_id,String update_uri){

        if(update_id !=null){target.setId(update_id);}
        if(update_uri !=null){target.setUri(update_uri);}
    }

    // predicate 삽입
    public void insertPredicate(String id ,int predicate_num,String insert){
        //
        ListNode_routes target = searchNode(id);

        ArrayList<String> temp = target.getPredicates();
        temp.add(predicate_num,insert);
        target.setPredicates(temp);
    }

    // predicate 수정
    public void updatePredicate(ListNode_routes target ,int predicate_num,String update){
        ArrayList<String> temp = target.getPredicates();
        temp.set(predicate_num,update);
        target.setPredicates(temp);
    }

    // predicate 삭제
    public void deletePredicate(String id ,int predicate_num){
        //
        ListNode_routes target = searchNode(id);

        ArrayList<String> temp = target.getPredicates();
        temp.remove(predicate_num);
        target.setPredicates(temp);
    }


    public String return_str() {
        ListNode_routes tempNode = this.head;    // tempNode에 head가 가리키는 첫번째 노드를 할당
        String result = "";
        result +="      routes:\n";
        // tempNode가 null이 아닐 때까지 반복하여 출력
        while(tempNode != null) {
            result +=
                    "        - id: " +tempNode.getId()+"\n"+
                            "          uri: " +tempNode.getUri()+"\n"+
                            "          predicates:\n";


            for (String predicate : tempNode.getPredicates()) {
                result += "            - " + predicate + "\n";
            }
            tempNode = tempNode.link;    // temp 노드에 다음 노드(tempNode.link) 할당.
        }
        return result;
    }
}