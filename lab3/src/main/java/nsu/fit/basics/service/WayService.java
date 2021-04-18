package nsu.fit.basics.service;

import nsu.fit.basics.dao.WayRepository;
import nsu.fit.basics.model.WayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class WayService extends CrudService<WayModel, BigInteger> {

    public WayService(@Autowired WayRepository repository) {
        super(repository);
    }
}
