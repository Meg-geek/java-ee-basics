package nsu.fit.basics.controller;

import nsu.fit.basics.model.WayModel;
import nsu.fit.basics.service.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/way")
public class WayController extends CrudController<WayModel, BigInteger> {

    public WayController(@Autowired WayService wayService) {
        super(wayService);
    }
}
