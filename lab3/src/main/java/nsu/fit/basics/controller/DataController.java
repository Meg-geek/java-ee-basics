package nsu.fit.basics.controller;

import lombok.RequiredArgsConstructor;
import nsu.fit.basics.service.OsmReaderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
public class DataController {
    private final OsmReaderService osmReaderService;

    @PostMapping("/parse")
    public void parseOsm(@RequestParam String filePath) {
        osmReaderService.readFileAndSaveEntities(filePath);
    }
}
