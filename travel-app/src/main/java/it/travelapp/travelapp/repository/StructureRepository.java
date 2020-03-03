package it.travelapp.travelapp.repository;

import it.travelapp.travelapp.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

}