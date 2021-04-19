package nsu.fit.basics.service;

import nsu.fit.basics.dao.NodeRepository;
import nsu.fit.basics.model.NodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class NodeService extends CrudService<NodeModel, BigInteger> {
    private final NodeRepository nodeRepository;

    public NodeService(@Autowired NodeRepository nodeRepository) {
        super(nodeRepository);
        this.nodeRepository = nodeRepository;
    }

    /**
     * Получить все Node в заданном радиусе от заданной точки
     *
     * @param latitude  широта точки
     * @param longitude долгота точки
     * @param radius    радиус
     * @return список Node  с тегами
     */
    public List<NodeModel> getNearestNodesInRadius(double latitude,
                                                   double longitude,
                                                   double radius) {
        return nodeRepository.getNearestNodesInRadius(latitude, longitude, radius);
    }
}
