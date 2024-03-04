package mercenarios.mercenarios.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import mercenarios.mercenarios.models.Equipment;
import mercenarios.mercenarios.models.Mercenary;
import mercenarios.mercenarios.models.Skill;
import mercenarios.mercenarios.repositories.EquipmentRepository;
import mercenarios.mercenarios.repositories.MercenaryRepository;
import mercenarios.mercenarios.repositories.SkillRepository;
import mercenarios.mercenarios.services.MercenaryService;

@RestController
@RequestMapping("/mercenaries")
public class MercenaryController {

    @Autowired
    private MercenaryService mercenaryService;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private MercenaryRepository mercenaryRepository;
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public ResponseEntity<List<Mercenary>> getMercenaries() {
        return new ResponseEntity<List<Mercenary>>(mercenaryService.getMercenaries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mercenary> createMercenary(@RequestBody Mercenary mercenary) {
        return new ResponseEntity<Mercenary>(mercenaryService.createMercenary(mercenary), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mercenary> updateMercenary(@PathVariable("id") Integer id,
            @RequestBody Mercenary mercenary) {
        return new ResponseEntity<>(mercenaryService.updateMercenary(id, mercenary), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMercenary(@PathVariable("id") Integer id) {
        mercenaryService.deleteMercenary(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{mercenaryId}/equipment/{equipmentId}")
    public ResponseEntity<Mercenary> assignEquipmentToMercenary(@PathVariable("mercenaryId") Integer mercenaryId,
            @PathVariable("equipmentId") Integer equipmentId) {
        Optional<Mercenary> mercenaryOptional = mercenaryRepository.findById(mercenaryId);
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);

        if (mercenaryOptional.isPresent() && equipmentOptional.isPresent()) {
            Mercenary mercenary = mercenaryOptional.get();
            Equipment equipment = equipmentOptional.get();

            mercenary.getEquipment().add(equipment);
            return new ResponseEntity<>(mercenaryRepository.save(mercenary), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Mercenary or Equipment not found");
        }
    }

    @PostMapping("/{mercenaryId}/skills/{skillId}")
    public ResponseEntity<Mercenary> assignSkillToMercenary(@PathVariable("mercenaryId") Integer mercenaryId,
            @PathVariable("skillId") Integer skillId) {
        Optional<Mercenary> mercenaryOptional = mercenaryRepository.findById(mercenaryId);
        Optional<Skill> skillOptional = skillRepository.findById(skillId);

        if (mercenaryOptional.isPresent() && skillOptional.isPresent()) {
            Mercenary mercenary = mercenaryOptional.get();
            Skill skill = skillOptional.get();

            mercenary.getSkills().add(skill);
            skill.getMercenaries().add(mercenary);

            skillRepository.save(skill);
            return new ResponseEntity<>(mercenaryRepository.save(mercenary), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Mercenary or Skill not found");
        }
    }

    @PostMapping("/{mercenaryId}/superior/{superiorId}")
    public ResponseEntity<Mercenary> assignSuperiorToMercenary(@PathVariable("mercenaryId") Integer mercenaryId,
            @PathVariable("superiorId") Integer superiorId) {
        Optional<Mercenary> mercenaryOptional = mercenaryRepository.findById(mercenaryId);
        Optional<Mercenary> superiorOptional = mercenaryRepository.findById(superiorId);

        if (mercenaryOptional.isPresent() && superiorOptional.isPresent()) {
            Mercenary mercenary = mercenaryOptional.get();
            Mercenary superior = superiorOptional.get();

            mercenary.setParent(superior);
            return new ResponseEntity<>(mercenaryRepository.save(mercenary), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Mercenary or Superior not found");
        }
    }
}