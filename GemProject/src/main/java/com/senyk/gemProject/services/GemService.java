package com.senyk.gemProject.services;


import com.senyk.gemProject.dao.GemRepository;
import com.senyk.gemProject.entity.Gem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GemService implements com.senyk.gemProject.services.interfaces.Service<Gem> {
    private GemRepository gemRepository;
    @Autowired
    public GemService(GemRepository gemRepository) {
        this.gemRepository = gemRepository;
    }
    @Override
    public List<Gem> getList() {
        return gemRepository.findAll();
    }

    @Transactional
    @Override
    public void addNew(Gem gem) {
        // logger.info("add gem try");
        Optional<Gem> optionalPlayer= gemRepository.findById(gem.getId());
        if (optionalPlayer.isPresent()){
            // logger.error("failing");
            throw new IllegalStateException("id taken");
        }
        gemRepository.save(gem);
        // logger.info("successful adding");
    }

    @Transactional
    @Override
    public void removeObj(Long gemId) {
        boolean exists= gemRepository.existsById(gemId);
        if (!exists){
            throw new IllegalStateException("gem with id "+gemId+" does not exists");
        }
        if (gemRepository.getReferenceById(gemId).getStatus()=="Used"){
            throw new IllegalStateException("gem with id "+gemId+" is used");
        }
        if (!gemRepository.getReferenceById(gemId).getSketchList().isEmpty()){
            throw new IllegalStateException("gem with id "+gemId+" is used");
        }
        gemRepository.deleteById(gemId);
    }

    @Transactional
    public void updateGem(Long gemId,Double price){
        // logger.info("update gem");
        Gem gem=gemRepository.findById(gemId).orElseThrow(
                ()->{// logger.error("failing");
                    return new IllegalStateException("gem with id "+gemId+" does not exists");});
        if (price<=0){
            // logger.error("failing");
            throw new IllegalStateException("incorrect price");
        }
        gem.setPrice(price);
        // logger.info("successful updating");
    }
}
