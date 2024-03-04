package mercenarios.mercenarios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mercenarios.mercenarios.models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    public Optional<Skill> findByName(String name);

}
