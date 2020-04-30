package it.travelapp.travelapp.repository;

import java.util.Optional;
import java.util.List;

import it.travelapp.travelapp.model.Structure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

    Optional<Structure> findByName(String name);

    List<Structure> findStructureByNameContainingIgnoreCase(String name);
    List<Structure> findStructureByPlaceContainingIgnoreCase(String place);
    List<Structure> findStructureByCategory(String category);
    List<Structure> findStructureByWebSiteContainingIgnoreCase(String webSite);
    List<Structure> findStructureByPriceBetween(Integer lowerPrice, Integer upperPrice);

    @Query(value = "SELECT AVG(points) FROM review r WHERE structureID = ?1", nativeQuery = true)
    Double getAveragePoints(Long structureID);

    @Query(value = "SELECT * " +
                   "FROM (SELECT s.id, s.name, (SELECT AVG(r.points)" +
                   "                            FROM review r" +
                   "                            WHERE r.structureID=s.id) as media " +
                   "FROM structure s) as info " +
                   "WHERE :avgPoints", nativeQuery = true)
    List<Structure> findAllStructureAndAvgPoints(String avgPoints);
}