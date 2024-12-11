package com.example.yamlgeneratorservice.controller;

import com.example.yamlgeneratorservice.generator.Binding_yaml;
import com.example.yamlgeneratorservice.vo.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class YamlController {
    private Environment env;
    private Binding_yaml binding_yaml;


    @Autowired
    public YamlController(Environment env, Binding_yaml binding_yaml){
        this.env =env;
        this.binding_yaml=binding_yaml;
    }

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's Working in User Service"
                +", port(local.server.port)="+env.getProperty("local.server.port")
                +", port(server.port)="+env.getProperty("server.port")
                +", token secret="+env.getProperty("token.secret")
                +", token expiration time="+env.getProperty("token.expiration_time"));
    }
    @PostMapping("/insertYaml")
    public void insertYaml(@RequestBody String uri){
        binding_yaml = new Binding_yaml(uri);

    }
    @PostMapping("/insertFilter")
    public void insertFilter(@RequestBody RequestFilter request){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RequestFilter requestFilter = mapper.map(request, RequestFilter.class);

        binding_yaml.insert_filter(requestFilter.getName(),
                requestFilter.getArgs(),
                requestFilter.getBaseMessage(),
                requestFilter.getPostLogger(),
                requestFilter.getPostLogger());

    }

    @PostMapping("/insertFilter_pre")
    public void insertFilter_pre(@RequestBody RequestFilter_pre request_pre){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RequestFilter_pre requestFilter_pre = mapper.map(request_pre,RequestFilter_pre.class);

        binding_yaml.insert_filter(requestFilter_pre.getPrefilter_name(),
                requestFilter_pre.getName(),
                requestFilter_pre.getArgs(),
                requestFilter_pre.getBaseMessage(),
                requestFilter_pre.getPostLogger(),
                requestFilter_pre.getPostLogger());
    }
    @PostMapping("/deleteFilter")
    public void delete_filter(){
        binding_yaml.delete_filter();
    }
    @PostMapping("/deleteFilter_target")
    public void delete_filter(@RequestBody String target){
        binding_yaml.delete_filter(target);
    }

    @PostMapping("/updateFilter")
    public void update_filter(@RequestBody RequestUpdate update){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RequestUpdate requestUpdate = mapper.map(update,RequestUpdate.class);
        binding_yaml.update_filter(requestUpdate.getPrefilter_name(),
                requestUpdate.getName(),
                requestUpdate.getArgs(),
                requestUpdate.getBaseMessage(),
                requestUpdate.getPreLogger(),
                requestUpdate.getPostLogger());
    }

    //라우터
    @PostMapping("/insertRouter")
    public void insertRouter(@RequestBody RequestRouter request){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RequestRouter requestRouter = mapper.map(request,RequestRouter.class);

        binding_yaml.insert_router(requestRouter.getId(),
                requestRouter.getUri(),
                requestRouter.getPredicates());

    }
    @PostMapping("/insertRouter_pre")
    public void insertRouter(@RequestBody RequestRouter_pre request){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RequestRouter_pre requestRouter_pre = mapper.map(request,RequestRouter_pre.class);

        binding_yaml.insert_router(requestRouter_pre.getPreNode_id(),
                requestRouter_pre.getId(),
                requestRouter_pre.getUri(),
                requestRouter_pre.getPredicates());

    }

    @PostMapping("/deleteRouter")
    public void delete_router(){
        binding_yaml.delete_router();
    }
    @PostMapping("/deleteRouter_target")
    public void delete_router(@RequestBody String target){
        binding_yaml.delete_filter(target);
    }

    @PostMapping("/updateRouter")
    public void update_router(@RequestBody RequestUpdateRouter update){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RequestUpdateRouter requestUpdateRouter = mapper.map(update,RequestUpdateRouter.class);
        binding_yaml.update_router(requestUpdateRouter.getTarget_id(),
                requestUpdateRouter.getUpdate_id(),
                requestUpdateRouter.getUpdate_uri());
    }

    
    @PostMapping("/createYaml")
    public void createYaml(@RequestBody String uri){
        binding_yaml.createYaml(uri);
    }



}
