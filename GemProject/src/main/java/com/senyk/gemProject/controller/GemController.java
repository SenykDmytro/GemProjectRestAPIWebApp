package com.senyk.gemProject.controller;

import com.senyk.gemProject.entity.Gem;
import com.senyk.gemProject.entity.GemFactory;
import com.senyk.gemProject.services.GemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/gemProject/gem")
public  class GemController {
    private final GemService gemService;
    @Autowired
    public GemController(GemService gemService) {
        this.gemService = gemService;
    }

    @GetMapping
    public GemFactory getGems(){
        GemFactory gemFactory= new GemFactory(gemService.getList());
        return gemFactory;
    }

    @PostMapping
    public void addNewGem(@RequestBody Gem gem){
        gemService.addNew(gem);
    }

    @DeleteMapping(path = "/{gemID}")
    public void deleteGem(@PathVariable("gemID")Long gemID){
        gemService.removeObj(gemID);
    }

    @PutMapping(path = "/{gemID}")
    public void updateGem(
        @PathVariable("gemID") Long gemID,
        @RequestParam() Double price
    ){
        gemService.updateGem(gemID,price);
    }
}
