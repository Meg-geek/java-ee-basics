package nsu.fit.basics.controller;

import nsu.fit.basics.model.NodeModel;
import nsu.fit.basics.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/node")
public class NodeController extends CrudController<NodeModel, BigInteger> {
    private final NodeService nodeService;

    public NodeController(@Autowired NodeService nodeService) {
        super(nodeService);
        this.nodeService = nodeService;
    }

    @GetMapping("/nearest-nodes")
    public List<NodeModel> getNearestNodesInRadius(@RequestParam double latitude,
                                                   @RequestParam double longitude,
                                                   @RequestParam double radius) {
        return nodeService.getNearestNodesInRadius(latitude, longitude, radius);
    }
}
