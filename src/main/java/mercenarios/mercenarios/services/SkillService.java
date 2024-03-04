package mercenarios.mercenarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mercenarios.mercenarios.models.Skill;
import mercenarios.mercenarios.repositories.SkillRepository;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill getSkillByName(String name) {
        Optional<Skill> res = skillRepository.findByName(name);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
        }
    }

    public Skill updateSkill(Integer id, Skill skill) {
        Optional<Skill> res = skillRepository.findById(id);
        if (res.isPresent()) {
            Skill existingSkill = res.get();
            existingSkill.setName(skill.getName());
            return skillRepository.save(existingSkill);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
        }
    }

    public void deleteSkill(Integer id) {
        Optional<Skill> res = skillRepository.findById(id);
        if (res.isPresent()) {
            skillRepository.delete(res.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
        }
    }

}