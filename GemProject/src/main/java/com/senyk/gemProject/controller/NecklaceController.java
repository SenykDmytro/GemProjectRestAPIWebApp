package com.senyk.gemProject.controller;

import com.senyk.gemProject.entity.Gem;
import com.senyk.gemProject.entity.Necklace;
import com.senyk.gemProject.entity.Sketch;
import com.senyk.gemProject.services.NecklaceService;
import com.senyk.gemProject.services.SketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/gemProject/necklaces")
public class NecklaceController {
    private final NecklaceService necklaceService;

    @Autowired
    public NecklaceController(NecklaceService necklaceService) {
        this.necklaceService = necklaceService;
    }

    @GetMapping
    public List<Necklace> getSketch(){
        return necklaceService.getList();
    }

    @PostMapping(path = "{sketchID}")
    public void addNewNecklace(@PathVariable("sketchID") Long sketchID){
        necklaceService.implementSketch(sketchID);
    }

    @DeleteMapping(path = "{necklaceID}")
    public void deleteNecklace(@PathVariable("necklaceID")Long necklaceID){
        necklaceService.removeObj(necklaceID);
    }

    @GetMapping("necklace/{necklaceID}")
    public Map<String, Object> info(@PathVariable("necklaceID")Long necklaceID){
        Map<String, Object> map = new HashMap<>();
        double price= necklaceService.getPrice(necklaceID);
        double carat= necklaceService.getCarat(necklaceID);
        List<Gem> gems= necklaceService.getGems(necklaceID);
        map.put("price", price);
        map.put("carat", carat);
        map.put("gems", gems);
        return map;
    }
}

