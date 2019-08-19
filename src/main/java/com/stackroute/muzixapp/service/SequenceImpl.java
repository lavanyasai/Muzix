package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.exceptions.SequenceException;
import com.stackroute.muzixapp.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceImpl implements Sequence {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public int getNextSequenceId(String key) throws SequenceException {

        Query query = new Query(Criteria.where("_id").is(key));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        DatabaseSequence seqId =
                mongoOperations.findAndModify(query, update, options, DatabaseSequence.class);

        if (seqId == null) {
            throw new SequenceException();
        }

        return seqId.getSeq();

    }

}
