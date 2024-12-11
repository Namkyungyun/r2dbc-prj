package com.example.repositoryservice.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InfoRepository extends ReactiveCrudRepository<Urls, Long> {
    Flux<Urls> findByUrlContainingAndCrud(String select, String crud);
    Mono<Urls> findFirstByUrlContaining(String urlName);
    Mono<Long> findByUrlContaining(String url);

    Mono<? extends Void> deleteById(Urls urls);
}