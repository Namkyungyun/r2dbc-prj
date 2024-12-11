package com.example.yamlgeneratorservice.generator;

import org.springframework.stereotype.Component;

@Component
public class LinkedList_default_filters {
    private ListNode_default_filters head;    // ListNode 타입의 head 노드 인스턴스 변수

    // LinkedList 생성자
    public LinkedList_default_filters() {
        head = null;    // head 노드 초기화
    }

    // Node 삽입 (중간에 삽입)
    public void insertNode(String preNode_name,
                           String name,
                           String args,
                           String baseMessage,
                           String preLogger,
                           String postLogger) {
        ListNode_default_filters preNode = searchNode(preNode_name);
        ListNode_default_filters newNode = new ListNode_default_filters(
                name,
                args,
                baseMessage,
                preLogger,
                postLogger);    // 새로운 노드 생성


        // 이전노드의 link = 새로운 노드의 link
        newNode.link = preNode.link;

        // 이전노드의 link가 새로운 노드를 참조하도록 함.
        preNode.link = newNode;
    }

    // Node 삽입 (마지막에 삽입)
    public void insertNode(String name,
                           String args,
                           String baseMessage,
                           String preLogger,
                           String postLogger) {
        ListNode_default_filters newNode = new ListNode_default_filters(
                        name,
                        args,
                        baseMessage,
                        preLogger,
                        postLogger);    // 새로운 노드 생성

        if(head == null) {
            // head 노드가 없을때 새로운 노드 = 헤드노드
            this.head = newNode;
        } else { //head 노드가 존재할때 제일 마지막 노드를 찾아야함

            // tempNode 임시노드(마지막 노드를 찾기위해서)
            ListNode_default_filters tempNode = head;


            while(tempNode.link != null) {
                tempNode = tempNode.link;    // 다음 노드를 참조
            }// 마지막노드는 temp 노드가 된다.

            // tempNode(마지막 노드)의 link가 다음 노드를 참조하도록 함.
            tempNode.link = newNode;
        }
    }

    // Node 삭제(중간 노드 삭제)
    public void deleteNode(String name) {
        // preNode는 head가 가리키는 노드를 할당
        ListNode_default_filters preNode = head;
        // tempNode는 head가 가리키는 노드의 다음 노드. 즉, preNode의 다음 노드를 할당
        ListNode_default_filters tempNode = head.link;

        // 주어진 데이터가 preNode의 데이터와 일치하는 경우
        // 즉, 첫번째 노드의 데이터와 일치하는 경우
        if(name.equals( preNode.getName() )) {
            // head는 preNode의 다음 노드를 참조하도록 함.
            head = preNode.link;
            // preNode의 link는 null을 할당하여 연결을 끊음.
            preNode.link = null;
        } else {
            // tempNode가 null일 때까지 반복하여 탐색
            while(tempNode != null) {
                // 주어진 데이터와 temp 노드의 데이터가 일치할 경우.
                if(name.equals( tempNode.getName() )) {
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

    // Node 삭제(마지막 노드 삭제)
    public void deleteNode() {
        ListNode_default_filters preNode;
        ListNode_default_filters tempNode;

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
    public ListNode_default_filters searchNode(String name) {
        ListNode_default_filters tempNode = this.head;    // temp 노드에 head가 가리키는 첫 번째 할당.

        // temp 노드가 null이 아닐 때까지 반복하여 탐색
        while(tempNode != null) {
            // 주어진 데이터와 temp 노드의 데이터가 일치할 경우 해당 temp 노드를 return
            if(name.equals(tempNode.getName())) {
                return tempNode;
            } else {
                // 데이터가 일치하지 않을 경우 temp 노드에 다음 노드 할당.
                tempNode = tempNode.link;
            }
        }

        return tempNode;
    }
//  노드 수정
    public void updateNode(ListNode_default_filters target,String update_name,String update_args,String update_baseMessage,String update_preLogger,String update_postLogger) {
        if(update_name !=null){target.setName(update_name);}
        if(update_args !=null){target.setArgs(update_args);}
        if(update_baseMessage !=null){target.setBaseMessage(update_baseMessage);}
        if(update_preLogger !=null){target.setPreLogger(update_preLogger);}
        if(update_postLogger !=null){target.setPostLogger(update_postLogger);}

    }


    // 연결 리스트에 저장된 모든 데이터를 출력
//    public void printList() {
//        ListNode_default_filters tempNode = this.head;    // tempNode에 head가 가리키는 첫번째 노드를 할당
//
//        //앞부분 출력
//        System.out.print("spring:\n" +
//                "  cloud:\n" +
//                "    gateway:\n" +
//                "      default-filters:\n"
//        );
//        // tempNode가 null이 아닐 때까지 반복하여 출력
//        while(tempNode != null) {
//            System.out.print(
//                    "        - name: " + tempNode.getName()+"\n"+
//                    "          args: " + tempNode.getArgs()+"\n"+
//                    "          baseMessage: " + tempNode.getBaseMessage()+"\n"+
//                    "          preLogger: " + tempNode.getPreLogger()+"\n"+
//                    "          postLogger: " + tempNode.getPostLogger()+"\n"
//            );
//            tempNode = tempNode.link;    // temp 노드에 다음 노드(tempNode.link) 할당.
//        }
//        System.out.println();
//    }
    //텍스트 결과 리턴
    public String return_str() {
        ListNode_default_filters tempNode = this.head;    // tempNode에 head가 가리키는 첫번째 노드를 할당
        String result="";


        // tempNode가 null이 아닐 때까지 반복하여 출력
        while(tempNode != null) {
            result +=
                    "        - name: " + tempNode.getName()+"\n"+
                            "          args: " + tempNode.getArgs()+"\n"+
                            "          baseMessage: " + tempNode.getBaseMessage()+"\n"+
                            "          preLogger: " + tempNode.getPreLogger()+"\n"+
                            "          postLogger: " + tempNode.getPostLogger()+"\n"
            ;
            tempNode = tempNode.link;    // temp 노드에 다음 노드(tempNode.link) 할당.
        }
        return result;
    }
}
