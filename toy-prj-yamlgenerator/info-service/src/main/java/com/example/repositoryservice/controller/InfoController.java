package com.example.repositoryservice.controller;

import com.example.repositoryservice.r2dbc.InfoRepository;
import com.example.repositoryservice.r2dbc.Urls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/info")
@RestController
public class InfoController {

    private InfoRepository infoRepository;

    @Autowired
    public InfoController(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's Working in Info Service\n"
                + "You can check the yaml_generator guide!\n"
                + "Try check using GetMapping and url(/url)");
    }

    @GetMapping
    public Flux<Urls> getUrls() {
        return infoRepository.findAll();
    }
    /** {ex) /info/filter/create, /info/router/create} **/
    @GetMapping("/{select}/{crud}")
    public Flux<Urls> getUser(@PathVariable("select") String select, @PathVariable("crud") String crud) {
        return infoRepository.findByUrlContainingAndCrud(select,crud);
    }

    @PostMapping
    public Mono<Urls> save(@RequestBody Urls urls) {
        return infoRepository.save(urls);
    }


    @DeleteMapping("/delete/{urlName}")
    public Mono<Void> removeMember(@PathVariable String urlName) {
        return Mono.just(urlName)
                .flatMap(infoRepository::findFirstByUrlContaining)
                .flatMap(infoRepository::deleteById);
    }


}
