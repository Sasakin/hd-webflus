package com.charot.dhwebflux.service;

import reactor.core.publisher.Mono;

public interface SequenceGeneratorService {
    Mono<Long> generateSequence(String seqName);
}
