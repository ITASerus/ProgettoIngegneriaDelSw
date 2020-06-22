package it.travelapp.travelapp.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Structure createStructure(@Valid @RequestBody Structure structure) {
        return structureRepository.save(structure);
    }

    // Update a Structure
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/id={id}")
    public Structure updateStructure(@PathVariable(value = "id") Long structureId,
                                     @Valid @RequestBody Structure structureDetails) {

        Structure structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));

        if(structureDetails.getName() != null)
            structure.setName(structureDetails.getName());

        if(structureDetails.getPlace() != null)
            structure.setPlace(structureDetails.getPlace());

        if(structureDetails.getCategory() != null)
            structure.setCategory(structureDetails.getCategory());

        if(structureDetails.getPlace() != null)
            structure.setPrice(structureDetails.getPrice());

        if(structureDetails.getWebSite() != null)
            structure.setWebSite(structureDetails.getWebSite());

        if(structureDetails.getContacts() != null)
            structure.setContacts(structureDetails.getContacts());

        if(structureDetails.getTag() != null)
            structure.setTag(structureDetails.getTag());

        if(structureDetails.getDescription() != null)
            structure.setDescription(structureDetails.getDescription());

        if(structureDetails.getImage() != null)
            structure.setImage(structureDetails.getImage());

        Structure updatedStructure = structureRepository.save(structure);
        return updatedStructure;
    }

    // Delete a Note
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/id={id}")
    public ResponseEntity<?> deleteStructure(@PathVariable(value = "id") Long structureId) {
        Structure structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));

        structureRepository.delete(structure);

        return ResponseEntity.ok().build();
    }

    //---- ENDPOINT WITH FOREIGN KEYS

    // Get Reviews by StructureID
    @GetMapping("/id={id}/getReviews")
    public Set<Review> getReviewsByStructureId(@PathVariable(value = "id") Long structureId) {
        Structure structure = structureRepository.findById(structureId).orElseThrow(() -> new ResourceNotFoundException("Structure", "id", structureId));
        return structure.getReviews();
    }

    // Get Structures with Average Points
    @GetMapping("/getAllWithAvgPoints")
    public List<Map<String, Object>> getAllWithAvgPoints() {
        List<Map<String, Object>> list = new ArrayList<>();

        List<Structure> structureList = structureRepository.findAll();

        for (Structure structure : structureList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", structure.getId());
            map.put("name", structure.getName());
            map.put("place", structure.getPlace());
            map.put("category", structure.getCategory());
            map.put("price", structure.getPrice());
            map.put("webSite", structure.getWebSite());
            map.put("contacts", structure.getContacts());
            map.put("tag", structure.getTag());
            map.put("description", structure.getDescription());
            map.put("image", structure.getImage());
            map.put("nReviews", structure.getReviews().size());
            map.put("avgPoints", structureRepository.getAveragePoints(structure.getId()));

            list.add(map);
        }

        return list;
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
                        "&category={category}" +
                        "&contacts={contacts}" +
                        "&webSite={webSite}" +
                        "&lowerPrice={lowerPrice}" +
                        "&upperPrice={upperPrice}" +
                        "&avgPoints={avgPoints}")
    public List<Structure> getByFilter(@PathVariable(value = "name") String name,
                                       @PathVariable(value = "place") String place,
                                       @PathVariable(value = "category") String category,
                                       @PathVariable(value = "contacts") String contacts,
                                       @PathVariable(value = "webSite") String webSite,
                                       @PathVariable(value = "lowerPrice") Integer lowerPrice,
                                       @PathVariable(value = "upperPrice") Integer upperPrice,
                                       @PathVariable(value = "avgPoints") String avgPoints){
        List<Structure> allStructure = structureRepository.findAll();
        List<Structure> resultStructure = new LinkedList<Structure>();

        for (Structure structure : allStructure) {

            if (!name.equals("null") && !containsIgnoreCase(structure.getName(), name)) {
                continue;
            }

            if (!place.equals("null") && (structure.getPlace() == null || (structure.getPlace() != null && !containsIgnoreCase(structure.getPlace(), place)))) {//!structure.getPlace().contains(place)) {
                continue;
            }

            if (!category.equals("null") && (structure.getCategory() == null || (structure.getCategory() != null && !structure.getCategory().equals(category)))) {
                continue;
            }

            if (!contacts.equals("null") && (structure.getContacts() == null || (structure.getContacts() != null && !containsIgnoreCase(structure.getContacts(), contacts)))) {
                continue;
            }

            if (!webSite.equals("null") && (structure.getWebSite() == null || (structure.getWebSite() != null && !containsIgnoreCase(structure.getWebSite(), webSite)))) {
                continue;
            }

            if (lowerPrice != -1 && (structure.getPrice() == null || (structure.getPrice() != null && structure.getPrice() < lowerPrice))) {
                continue;
            }

            if (upperPrice != -1 && (structure.getPrice() == null || (structure.getPrice() != null && structure.getPrice() > upperPrice))) {
                continue;
            }

            if (!avgPoints.equals("null")) {
                Double avgPointsStructure = structureRepository.getAveragePoints(structure.getId());

                if (avgPointsStructure != null) {
                    if(avgPoints.equals("0 e oltre") && avgPointsStructure < 0) {
                        continue;
                    }
                    if(avgPoints.equals("1 e oltre") && avgPointsStructure < 1) {
                        continue;
                    }
                    if(avgPoints.equals("2 e oltre") && avgPointsStructure < 2) {
                        continue;
                    }
                    if(avgPoints.equals("3 e oltre") && avgPointsStructure < 3) {
                        continue;
                    }
                    if(avgPoints.equals("4 e oltre") && avgPointsStructure < 4) {
                        continue;
                    }
                    if(avgPoints.equals("5") && avgPointsStructure != 5) {
                        continue;
                    }
                } else {
                    continue;
                }
            }

            resultStructure.add(structure);
        }

        return resultStructure;
    }

    private static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }
}