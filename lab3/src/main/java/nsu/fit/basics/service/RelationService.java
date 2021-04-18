package nsu.fit.basics.service;

import nsu.fit.basics.dao.RelationRepository;
import nsu.fit.basics.model.RelationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RelationService extends CrudService<RelationModel, BigInteger> {
    public RelationService(@Autowired RelationRepository relationRepository) {
        super(relationRepository);
    }
}
