package it.travelapp.travelapp.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.Query;

import it.travelapp.travelapp.exception.ResourceNotFoundException;
import it.travelapp.travelapp.model.Review;
import it.travelapp.travelapp.model.Structure;
import it.travelapp.travelapp.repository.StructureRepository;

@RestController
@RequestMapping("/structures")
public class StructureController {

    @Autowired
    StructureRepository structureRepository;

    //Get number of structures
    @GetMapping("/getNum")
    public long getNumberOfStructures() {
        return structureRepository.count();
    }

    // Get All Structures
    @GetMapping("/getAll")
    public List<Structure> getAllStructures() {
        return structureRepository.findAll();
    }

    // Get Structure by Id
    @GetMapping("/id={id}")
    public Structure getStructureById(@PathVariable(value = "id") Long structureId) {
        return structureRepository.findById(structureId)
                .orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));
    }

    // Get Structure by Name
    @GetMapping("/name={name}")
    public Structure getStructureByName(@PathVariable(value = "name") String structureName) {
        return structureRepository.findByName(structureName).orElseThrow(() -> new ResourceNotFoundException("Structure", "name", structureName));
    }

    // Create a new Structure
    @PostMapping("/create")
    public Structure createStructure(@Valid @RequestBody Structure structure) {
        return structureRepository.save(structure);
    }

    // Delete a Note
    @DeleteMapping("/id={id}")
    public ResponseEntity<?> deleteStructure(@PathVariable(value = "id") Long structureId) {
        Structure structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));

        structureRepository.delete(structure);

        return ResponseEntity.ok().build();
    }

    // Update a Structure
    @PutMapping("/id={id}")
    public Structure updateStructure(@PathVariable(value = "id") Long structureId,
                                     @Valid @RequestBody Structure structureDetails) {

        Structure structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));

        structure.setName(structureDetails.getName());
        structure.setPlace(structureDetails.getPlace());
        structure.setCategory(structureDetails.getCategory());
        structure.setPrice(structureDetails.getPrice());
        structure.setWebSite(structureDetails.getWebSite());
        structure.setContacts(structureDetails.getContacts());
        structure.setTag(structureDetails.getTag());
        structure.setDescription(structureDetails.getDescription());

        Structure updatedStructure = structureRepository.save(structure);
        return updatedStructure;
    }

    //---- ENDPOINT FOR FOREIGN KEYS

    // Get Reviews by StructureID
    @GetMapping("/id={id}/getReviews")
    public Set<Review> getReviewsByStructureId(@PathVariable(value = "id") Long structureId) {
        Structure structure = structureRepository.findById(structureId).orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));
        return structure.getReviews();
    }

    // Get Name, Num Rev and Average Points
    @GetMapping("/getInfo")
    public List<Map<String, Object>> getInfo() {
        List<Map<String, Object>> listInfos = new ArrayList<>();

        List<Structure> structureList = structureRepository.findAll();

        for (Structure structure : structureList) {
            Map<String, Object> map = new HashMap<>();
            map.put("structureId", structure.getId());
            map.put("name", structure.getName());
            map.put("numberOfReviews", structure.getReviews().size());

            map.put("averagePoints", structureRepository.getAveragePoints(structure.getId()));

            listInfos.add(map);
        }

        return listInfos;
    }


    // Get Structure by Place
    @GetMapping("/place={place}")
    public List<Structure> getStructureByPalce(@PathVariable(value = "place") String placeName) {
        return structureRepository.findStructureByPlaceContainingIgnoreCase(placeName);
    }

    // Get Structure with price between values
    @GetMapping("/lowerPrice={lowerPrice}&upperPrice={upperPrice}")
    public List<Structure> getStructureByPriceRange(@PathVariable(value = "lowerPrice") Integer lowerPrice, @PathVariable(value = "upperPrice") Integer upperPrice) {
        return structureRepository.findStructureByPriceBetween(lowerPrice, upperPrice);
    }

    @GetMapping("/search/name={name}" +
                        "&place={place}" +
                        "&contacts={contacts}" +
                        "&category={category}" +
                        "&webSite={webSite}" +
                        "&lowerPrice={lowerPrice}" +
                        "&upperPrice={upperPrice}" +
                        "&avgPoints={avgPoints}")
    public List<Structure> getByFilter(@PathVariable(value = "name") String name,
                                       @PathVariable(value = "place") String place,
                                       @PathVariable(value = "contacts") String contacts,
                                       @PathVariable(value = "category") String category,
                                       @PathVariable(value = "webSite") String webSite,
                                       @PathVariable(value = "lowerPrice") Integer lowerPrice,
                                       @PathVariable(value = "upperPrice") Integer upperPrice,
                                       @PathVariable(value = "avgPoints") String avgPoints){
        List<Structure> allStructure = structureRepository.findAll();
        List<Structure> resultStructure = new LinkedList<Structure>();

        for (Structure structure : allStructure) {
            if (!name.equals("null") && !structure.getName().contains(name)) {
                continue;
            }
            if (!place.equals("null") && (structure.getPlace() != null) && !structure.getPlace().contains(place)) {
                continue;
            }
            if (!contacts.equals("null") && (structure.getContacts() != null) && !structure.getContacts().contains(contacts)) {
                continue;
            }
            if (!category.equals("null")  && (structure.getCategory() != null) && !structure.getCategory().equals(category)) {
                continue;
            }
            if (!webSite.equals("null") && (structure.getWebSite() != null) && !structure.getWebSite().contains(webSite)) {
                continue;
            }
            if (lowerPrice != -1 && (structure.getPrice() != null) && !(structure.getPrice() > lowerPrice)) {
                continue;
            }
            if (upperPrice != -1 && (structure.getPrice() != null) && !(structure.getPrice() < upperPrice)) {
                continue;
            }
            if (!avgPoints.equals("null")) {
                System.out.println("CONTROLLO MEDIA RECENSIONI " + avgPoints);
                avgPoints = avgPoints.replace("'", "");
                Double avgPointsStructure = structureRepository.getAveragePoints(structure.getId());
                System.out.println(avgPointsStructure);

                if (avgPointsStructure != null) {
                    if(avgPoints.equals(">=1") && avgPointsStructure < 1) {
                        continue;
                    }
                    if(avgPoints.equals(">=2") && avgPointsStructure < 2) {
                        continue;
                    }
                    if(avgPoints.equals(">=3") && avgPointsStructure < 3) {
                        System.out.println("ESCO");
                        continue;
                    }
                    if(avgPoints.equals(">=4") && avgPointsStructure < 4) {
                        continue;
                    }
                    if(avgPoints.equals("==5") && avgPointsStructure != 5) {
                        continue;
                    }
                } else {
                    continue;
                }
            }

            System.out.println("VA BENE");
            resultStructure.add(structure);
        }

        return resultStructure;
    }
}