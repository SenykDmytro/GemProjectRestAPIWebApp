package com.senyk.gemProject.configuration;


import com.senyk.gemProject.dao.GemRepository;
import com.senyk.gemProject.entity.Gem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class gemConfig {
    @Bean
    CommandLineRunner commandLineRunner(GemRepository repository){
        return args -> {
            Gem gem1 =new Gem("Diamond",12.3,1200.2,"shape1");
            Gem gem2 =new Gem("Amber",1.3,5000.0,"ef");
            Gem gem3 =new Gem("Ruby",2.3,500.0,"shape1");
            Gem gem4 =new Gem("Emerald",0.3,100.0,"ef");
            Gem gem5 =new Gem("Sapphire",5.3,100000.0,"shape1");
            Gem gem6 =new Gem("Topaz",7.3,9000.0,"ef");
            repository.saveAll(
                    List.of(gem1,gem2,gem3,gem4,gem5,gem6)
            );
        };
    }
}
