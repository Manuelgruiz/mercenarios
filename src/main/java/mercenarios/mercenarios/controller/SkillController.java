package mercenarios.mercenarios.controller;

import java.util.List;

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
import mercenarios.mercenarios.models.Skill;
import mercenarios.mercenarios.services.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Skill>> getEquipments() {
        return new ResponseEntity<List<Skill>>(skillService.getSkills(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Skill> getSkillByName(@PathVariable("name") String name) {
        return new ResponseEntity<Skill>(skillService.getSkillByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        return new ResponseEntity<Skill>(skillService.createSkill(skill), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable("id") Integer id, @RequestBody Skill skill) {
        return new ResponseEntity<Skill>(skillService.updateSkill(id, skill), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable("id") Integer id) {
        skillService.deleteSkill(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}