package it.travelapp.travelapp.repository;

import java.util.Optional;

import it.travelapp.travelapp.model.Structure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

    Optional<Structure> findByName(String name);

    @Query(value = "SELECT AVG(points) FROM review r WHERE structureID = ?1", nativeQuery = true)
    Double getAveragePoints(Long structureID);
}