package com.senyk.gemProject.dao;

import com.senyk.gemProject.entity.Necklace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NecklaceRepository
        extends JpaRepository<Necklace,Long> {
    @Query("SELECT s FROM Necklace s WHERE s.id=?1")
    Optional<Necklace> findById(Long id);
}
