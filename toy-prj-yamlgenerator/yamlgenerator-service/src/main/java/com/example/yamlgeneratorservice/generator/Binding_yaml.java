package com.example.yamlgeneratorservice.generator;

import com.example.generatorYaml.generator.obj.Filters;
import com.example.generatorYaml.generator.obj.Routes;
import com.example.generatorYaml.generator.obj.Yaml_st;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class Binding_yaml {
    private LinkedList_default_filters linkedList_default_filters;
    private LinkedList_routes linkedList;


    public Binding_yaml(String uri){
        Yaml yaml = new Yaml(new Constructor(Yaml_st.class));
        InputStream inputStream = yaml.getClass()
                .getClassLoader()
                .getResourceAsStream(uri);

        Yaml_st yaml_st = yaml.load(inputStream);

        //필터 정보 -> 배열에 저장(filters)
        ArrayList<Filters> filters = yaml_st.getSpring().getCloud().getGateway().getFilters();
        //라우터 정보 -> 배열에 저장((routes)
        ArrayList<Routes> routes = yaml_st.getSpring().getCloud().getGateway().getRoutes();

        //필터/라우터정보가 저장될 Linked_List 선언
        linkedList_default_filters = new LinkedList_default_filters();
        linkedList = new LinkedList_routes();

        // linked_list에 resource정보(필터) 입력
        for(Filters input : filters){
            linkedList_default_filters.insertNode(input.getName(),input.getArgs(),input.getBaseMessage(),input.getPostLogger(), input.getPreLogger());
        }

        // linked_list에 resource정보(라우터) 입력
        for(Routes input : routes){
            linkedList.insertNode(input.getId(),input.getUri(),input.getPredicates());
        }
    }

//-------------------------------------------------------필터-----------------------------------------------------------------

    // 중간에 필터 추가
    public void insert_filter(String prefilter_name,String name,String args,String baseMessage,String preLogger, String postLogger){
        linkedList_default_filters.insertNode(prefilter_name,name,args,baseMessage,preLogger,postLogger);
    }

    // 맨뒤에 필터 추가
    public void insert_filter(String name,String args,String baseMessage,String preLogger, String postLogger){
        linkedList_default_filters.insertNode(name,args,baseMessage,preLogger,postLogger);
    }

    // 특정필터 삭제
    public void delete_filter(String name){
        linkedList_default_filters.deleteNode(name);
    }

    // 맨뒤에 필터 삭제
    public void delete_filter(){
        linkedList_default_filters.deleteNode();
    }

    // 필터 업데이트(내용수정)
    public void update_filter(String target_name, String name,String args,String baseMessage,String preLogger,String postLogger){
        ListNode_default_filters target = linkedList_default_filters.searchNode(target_name);
        linkedList_default_filters.updateNode(target,name, args, baseMessage, preLogger, postLogger);
    }

//-------------------------------------------------------라우터-----------------------------------------------------------------

    //중간에 삽입
    public void insert_router(String preNode_id, String id, String uri, ArrayList<String> predicates){
        ListNode_routes preNode = linkedList.searchNode(preNode_id);
        linkedList.insertNode(preNode,id,uri,predicates);
    }

    //마지막에 삽입
    public void insert_router(String id,String uri,ArrayList<String> predicates){
        linkedList.insertNode(id,uri,predicates);
    }

    //특정 라우터 삭제
    public void delete_router(String id){
        linkedList.deleteNode(id);
    }

    //마지막 라우터 삭제
    public void delete_router(){
        linkedList.deleteNode();
    }

    //라우터 업데이트
    public void update_router(String target_id,String update_id,String update_uri){
        ListNode_routes target = linkedList.searchNode(target_id);
        linkedList.updateNode(target,update_id,update_uri);
    }
//    //predicate 삽입
//
//    //predicate 삭제
//    public void delete_predicate(){
//
//    }
//
//    //predicate 업데이트
//    public void update_predicate(String target_id,int predicate_num,String update){
//        ListNode_routes target = linkedList.searchNode(target_id);
//        linkedList.updatePredicate(target,predicate_num,update);
//    }

    public void createYaml(String fileuri){
        File file = new File(fileuri);
        FileWriter writer = null;

        try {
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정
            writer = new FileWriter(file, false);
            writer.write(
                    "spring:\n" +
                            "  cloud:\n" +
                            "    gateway:\n" +
                            "      default-filters:\n");
            writer.write(linkedList_default_filters.return_str()); //default_filter 입력
            writer.write(linkedList.return_str());                 //router 입력
            writer.flush();

            System.out.println("DONE");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
