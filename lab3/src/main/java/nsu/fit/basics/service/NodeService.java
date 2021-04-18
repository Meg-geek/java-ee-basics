package nsu.fit.basics.service;

import nsu.fit.basics.dao.NodeRepository;
import nsu.fit.basics.model.NodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class NodeService extends CrudService<NodeModel, BigInteger> {
    private final NodeRepository nodeRepository;

    public NodeService(@Autowired NodeRepository nodeRepository) {
        super(nodeRepository);
        this.nodeRepository = nodeRepository;
    }
}
