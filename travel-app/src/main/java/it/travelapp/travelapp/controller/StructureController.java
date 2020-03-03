package it.travelapp.travelapp.controller;

import it.travelapp.travelapp.exception.ResourceNotFoundException;
import it.travelapp.travelapp.model.Structure;
import it.travelapp.travelapp.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/structures")
public class StructureController {

    @Autowired
    StructureRepository structureRepository;

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
}