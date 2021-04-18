package nsu.fit.basics.controller;

import nsu.fit.basics.model.RelationModel;
import nsu.fit.basics.service.RelationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/realtion")
public class RelationController extends CrudController<RelationModel, BigInteger> {

    public RelationController(RelationService relationService) {
        super(relationService);
    }
}
