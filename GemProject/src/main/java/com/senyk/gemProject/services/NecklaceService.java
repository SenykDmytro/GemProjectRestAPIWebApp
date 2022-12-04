package com.senyk.gemProject.services;

import com.senyk.gemProject.dao.NecklaceRepository;
import com.senyk.gemProject.dao.SketchRepository;
import com.senyk.gemProject.entity.Gem;
import com.senyk.gemProject.entity.Necklace;
import com.senyk.gemProject.entity.Sketch;
import com.senyk.gemProject.services.interfaces.ServiceOfNecklace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class NecklaceService implements ServiceOfNecklace<Necklace,Gem> {

    private final NecklaceRepository necklaceRepository;
    private final SketchRepository sketchRepository;

  //  private static final Logger logger= LoggerFactory.getLogger(NecklaceService.class);

    @Autowired
    public NecklaceService(NecklaceRepository necklaceRepository, SketchRepository sketchRepository) {
        this.necklaceRepository = necklaceRepository;
        this.sketchRepository = sketchRepository;
    }

    @Override
    public List<Necklace> getList() {
        return necklaceRepository.findAll();
    }

    @Transactional
    @Override
    public void addNew(Necklace sketchId) {
    }

    @Transactional
    public void implementSketch(Long sketchId){
        // logger.info("implement necklace try");
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            // logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        Sketch sketch =exists.get();
        List<Gem> gems = new LinkedList<>(sketch.getGems());
        for (Gem g :sketchRepository.getReferenceById(sketchId).getGems()) {
            if (g.getStatus()=="Used")
                throw new IllegalStateException("gem "+g.getId()+" is already used");
        }
        for (Gem g :sketchRepository.getReferenceById(sketchId).getGems()) {
            g.setStatus("Used");
        }
        String name=sketchRepository.getReferenceById(sketchId).getName();
        Necklace necklace= new Necklace(name,gems);
        necklaceRepository.save(necklace);
        // logger.info("successful implementing");
    }

    @Transactional
    @Override
    public void removeObj(Long necklaceId) {
        // logger.info("reconstruct necklace try");
        boolean exists= necklaceRepository.existsById(necklaceId);
        if (!exists){
            // logger.error("failing");
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        for (Gem gem:necklaceRepository.getReferenceById(necklaceId).getGems()) {
            gem.setStatus("No used");
        }
        necklaceRepository.deleteById(necklaceId);
        // logger.info("successful reconstructing");
    }

    @Override
    public Double getPrice(Long necklaceId) {
        Optional<Necklace> exists= necklaceRepository.findById(necklaceId);
        if (exists.isEmpty()){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        double sum=0.0;
        Necklace necklace =exists.get();
        List<Gem> gems = necklace.getGems();
        for (Gem gem : gems) {
            sum += gem.getPrice();
        }
        return sum;
    }

    @Override
    public Double getCarat(Long necklaceId) {
        boolean exists= necklaceRepository.existsById(necklaceId);
        if (!exists){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        return necklaceRepository.getReferenceById(necklaceId).getGems().stream()
                .reduce(0.0,(sketchCarat,gem)->sketchCarat+gem.getCarat(),Double::sum);    }

    @Override
    public List<Gem> getGems(Long necklaceId) {
        Optional<Necklace> exists= necklaceRepository.findById(necklaceId);
        if (exists.isEmpty()){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        Necklace necklace =exists.get();
        return necklace.getGems();
    }
}