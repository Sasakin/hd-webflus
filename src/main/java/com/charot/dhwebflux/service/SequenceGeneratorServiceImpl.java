package com.charot.dhwebflux.service;

import com.charot.dhwebflux.domain.entity.DatabaseSequence;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    private ReactiveMongoOperations mongoOperations;

    public SequenceGeneratorServiceImpl(ReactiveMongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Mono<Long> generateSequence(String seqName) {

        Mono<DatabaseSequence> counter = mongoOperations.findAndModify(Query.query(new Criteria("_id").is(seqName)),
                new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return counter.map(dbSeq -> !Objects.isNull(dbSeq) ? dbSeq.getSeq() : 1);

    }
}
