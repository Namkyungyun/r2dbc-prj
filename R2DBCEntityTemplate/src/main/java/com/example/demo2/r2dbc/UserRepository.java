package com.example.demo2.r2dbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

//import org.springframework.data.relational.core.query.Criteria;

import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.*;


import org.springframework.data.relational.core.query.Query;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    /** 유저 전체 데이터 조회 **/
    public Flux<User> findAll() {
        return this.r2dbcEntityTemplate.select(User.class).all();
    }

    /** name으로 유저 조회 **/
    public Mono<User> findByName(String name) {
        return this.r2dbcEntityTemplate
                .selectOne(Query.query(where("name").is(name)), User.class);
    }
//
    /** 유저 등록 **/
    public Mono<User> save(User p) {
        return this.r2dbcEntityTemplate.insert(User.class)
                .using(p);
    }

    /** 유저 이름 수정 **/
    public Mono<Integer> update(String name, String newName) {
        return this.r2dbcEntityTemplate.update(User.class)
                .matching(Query.query(where("name").is(name)))
                .apply(Update.update("name", newName));
    }

    /** 유저 삭제 **/
    public Mono<Integer> deleteByName(String name) {
        return this.r2dbcEntityTemplate.delete(Query.query(where("name").is(name)), User.class);
    }
}

