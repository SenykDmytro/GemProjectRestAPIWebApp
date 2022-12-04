package com.senyk.gemProject.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.senyk.gemProject.entity.Gem;
import com.senyk.gemProject.entity.Sketch;
import com.senyk.gemProject.services.GemService;
import com.senyk.gemProject.services.SketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/gemProject/sketches")
public class SketchController {
    private final SketchService sketchService;

    @Autowired
    public SketchController(SketchService sketchService) {
        this.sketchService = sketchService;
    }

    @GetMapping
    public List<Sketch> getSketch(){
        return sketchService.getList();
    }

    @PostMapping
    public void addNewSketch(@RequestBody Sketch sketch){
        sketchService.addNew(sketch);
    }

    @DeleteMapping(path = "{sketchID}")
    public void deleteSketch(@PathVariable("sketchID")Long sketchID){
        sketchService.removeObj(sketchID);
    }

    @GetMapping("sketch/{sketchID}")
    public Map<String, Object> info(@PathVariable("sketchID")Long sketchID){
        Map<String, Object> map = new HashMap<>();
        double price=sketchService.getPrice(sketchID);
        double carat=sketchService.getCarat(sketchID);
        List<Gem> gems=sketchService.getGems(sketchID);
        map.put("price", price);
        map.put("carat", carat);
        map.put("gems", gems);
        return map;
    }

    @PostMapping(path = "sketch/{sketchID}")
    public void addToSketch(
            @PathVariable("sketchID")Long sketchID,
            @RequestParam() Long gemID
    ){
        sketchService.addGemToSketch(sketchID,gemID);
    }

    @DeleteMapping(path = "sketch/{sketchID}")
    public void deleteFromSketch(
            @PathVariable("sketchID")Long sketchID,
            @RequestParam() Long gemID
    ){
        sketchService.removeGemFromSketch(sketchID,gemID);
    }
}

