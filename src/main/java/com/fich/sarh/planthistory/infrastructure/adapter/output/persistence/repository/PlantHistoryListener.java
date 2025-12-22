package com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PlantHistoryListener {

    private static PlantHistoryRepository plantHistoryRepository;

    @Autowired
    public void init(PlantHistoryRepository repo){
        PlantHistoryListener.plantHistoryRepository = repo;
    }

   /* @PrePersist
    public void generateCurrentHistory(PlantHistoryEntity entity){
        if(entity.getHistoryCurrent() == null){
            Long uniqueValue;
            Random random = new Random();
            int attempts = 0; // intentos


            do {

                uniqueValue = 10000L + random.nextLong(900000L);
                attempts++;
                if(attempts > 100) throw new RuntimeException("No se pudo generar un valor unico");

            } while (plantHistoryRepository.existsByHistoryCurrent(uniqueValue));

            entity.setHistoryCurrent(uniqueValue);
        }
    }*/
}
