package com.senyk.gemProject.services;

import com.senyk.gemProject.dao.GemRepository;
import com.senyk.gemProject.dao.SketchRepository;
import com.senyk.gemProject.entity.Gem;
import com.senyk.gemProject.entity.Sketch;
import com.senyk.gemProject.services.interfaces.ServiceOfNecklace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SketchService implements ServiceOfNecklace<Sketch,Gem> {

    private final SketchRepository sketchRepository;
    private final GemRepository gemRepository;
//    private static final Logger logger= LoggerFactory.getLogger(SketchService.class);
    @Autowired
    public SketchService(SketchRepository sketchRepository, GemRepository gemRepository) {
        this.sketchRepository = sketchRepository;
        this.gemRepository = gemRepository;
    }

    @Override
    public List<Sketch> getList() {
        return sketchRepository.findAll();
    }

    @Transactional
    @Override
    public void addNew(Sketch sketch) {
//        logger.info("create sketch try");
        Optional<Sketch>optionalPlayer= sketchRepository.findSketchByName(sketch.getName());
        if (optionalPlayer.isPresent()){
//            logger.error("failing");
            throw new IllegalStateException("name taken");
        }
        sketchRepository.save(sketch);
//        logger.info("successful creating");
    }

    @Transactional
    @Override
    public void removeObj(Long sketchId) {
//        logger.info("remove sketch try");
        boolean exists= sketchRepository.existsById(sketchId);
        if (!exists){
//            logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        sketchRepository.getReferenceById(sketchId).getGems().clear();
        sketchRepository.deleteById(sketchId);
//        logger.info("successful removing");
    }

    @Override
    public Double getPrice(Long sketchId) {
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        double sum=0.0;
        Sketch sketch =exists.get();
        List<Gem> gems = sketch.getGems();
        for (Gem gem : gems) {
            sum += gem.getPrice();
        }
        return sum;
    }

    @Override
    public Double getCarat(Long sketchId) {
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        double sum=0.0;
        Sketch sketch =exists.get();
        List<Gem> gems = sketch.getGems();
        for (Gem gem : gems) {
            sum += gem.getCarat();
        }
        return sum;
    }

    @Override
    public List<Gem> getGems(Long sketchId) {
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("necklace with id "+sketchId+" does not exists");
        }
        return exists.get().getGems();
    }

    @Transactional
    public void addGemToSketch(Long sketchId, Long gemId){
        // logger.info("add gem to sketch try");
        boolean exists= sketchRepository.existsById(sketchId);
        boolean exists1= gemRepository.existsById(gemId);
        if (!exists){
            // logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        if (!exists1){
            // logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" does not exists");
        }
        if (gemRepository.getReferenceById(gemId).getStatus()=="Used"){
            // logger.error("failing");
            throw new IllegalStateException("gem "+gemId+" is already used in necklace");
        }
        for (Gem gem :sketchRepository.getReferenceById(sketchId).getGems()) {
            if(gem.getId() == gemId){
                // logger.error("failing");
                throw new IllegalStateException("gem "+gemId+" is already used in this sketch");
            }
        }
        sketchRepository.getReferenceById(sketchId).getGems()
                .add(gemRepository.getReferenceById(gemId));
        // logger.info("successful adding");
    }
    @Transactional
    public void removeGemFromSketch(Long sketchId, Long gemId){
//        logger.info("remove gem from sketch try");
        boolean exists= sketchRepository.existsById(sketchId);
        boolean exists1= gemRepository.existsById(gemId);
        if (!exists){
//            logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        if (!exists1){
            // logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" does not exists");
        }
        boolean q=false;
        for (Gem gem :sketchRepository.getReferenceById(sketchId).getGems()) {
            if(gem.getId() == gemId){
                // logger.error("failing");
                q=true;
                break;
            }
        }
        if (!q){
            // logger.error("failing");
            throw new IllegalStateException("gem "+gemId+" is no used in this sketch");
        }
        List<Gem> gems=sketchRepository.getReferenceById(sketchId).getGems();
        gems.remove(gemRepository.getReferenceById(gemId));
        sketchRepository.getReferenceById(sketchId).setGems(gems);
        // logger.info("successful removing");
    }

    @Transactional
    public Info info(Long sketchId){
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("necklace with id "+sketchId+" does not exists");
        }
        Double p=getPrice(sketchId);
        Double c=getCarat(sketchId);
        List<Gem> gems=getGems(sketchId);
        return new Info(p,c,gems);
    }

    class Info{
        private Double price;
        private Double sum;
        private List<Gem> list;

        public Info(Double price, Double sum, List<Gem> list) {
            this.price = price;
            this.sum = sum;
            this.list = list;
        }
    }
}
