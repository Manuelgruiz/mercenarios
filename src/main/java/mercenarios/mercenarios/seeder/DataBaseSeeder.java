package mercenarios.mercenarios.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import mercenarios.mercenarios.models.Equipment;
import mercenarios.mercenarios.models.Mercenary;
import mercenarios.mercenarios.models.Skill;
import mercenarios.mercenarios.repositories.EquipmentRepository;
import mercenarios.mercenarios.repositories.MercenaryRepository;
import mercenarios.mercenarios.repositories.SkillRepository;
import java.util.ArrayList;

@Component
public class DataBaseSeeder {

    private final EquipmentRepository equipmentRepository;
    private final MercenaryRepository mercenaryRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public DataBaseSeeder(EquipmentRepository equipmentRepository, MercenaryRepository mercenaryRepository,
            SkillRepository skillRepository) {
        this.equipmentRepository = equipmentRepository;
        this.mercenaryRepository = mercenaryRepository;
        this.skillRepository = skillRepository;

    }

    @PostConstruct
    @Transactional
    public void seedDatabaseEquipment() {
        Equipment equipment1 = new Equipment();
        equipment1.setName("Excalibur");
        this.equipmentRepository.save(equipment1);

        Equipment equipment2 = new Equipment();
        equipment2.setName("Dragonplate");
        this.equipmentRepository.save(equipment2);

        Equipment equipment3 = new Equipment();
        equipment3.setName("Master Sword");
        this.equipmentRepository.save(equipment3);

        Equipment equipment4 = new Equipment();
        equipment4.setName("Ebony Mail");
        this.equipmentRepository.save(equipment4);

        Equipment equipment5 = new Equipment();
        equipment5.setName("Thunderfury, Blessed Blade of the Windseeker");
        this.equipmentRepository.save(equipment5);

        Equipment equipment6 = new Equipment();
        equipment6.setName("Daedric Helmet");
        this.equipmentRepository.save(equipment6);

        Equipment equipment7 = new Equipment();
        equipment7.setName("Mjölnir");
        this.equipmentRepository.save(equipment7);

        Equipment equipment8 = new Equipment();
        equipment8.setName("T-51b Power Armor");
        this.equipmentRepository.save(equipment8);

        Equipment equipment9 = new Equipment();
        equipment9.setName("Keyblade");
        this.equipmentRepository.save(equipment9);

        Equipment equipment10 = new Equipment();
        equipment10.setName("Spartan Armor");
        this.equipmentRepository.save(equipment10);
    }

    @PostConstruct
    public void seedDatabaseSkills() {
        Skill skill1 = new Skill();
        skill1.setName("Archery");
        skill1.setMercenaries(new ArrayList<>());
        this.skillRepository.save(skill1);

        Skill skill2 = new Skill();
        skill2.setName("Swordsmanship");
        skill2.setMercenaries(new ArrayList<>());
        this.skillRepository.save(skill2);

        Skill skill3 = new Skill();
        skill3.setName("Magic");
        skill3.setMercenaries(new ArrayList<>());
        this.skillRepository.save(skill3);
    }

    @PostConstruct
    public void setMercenaries() {
        Mercenary mercenary1 = new Mercenary();
        mercenary1.setName("Geralt of Rivia");
        mercenary1.setEquipment(new ArrayList<>());
        mercenary1.setSkills(new ArrayList<>());

        this.mercenaryRepository.save(mercenary1);

        Mercenary mercenary2 = new Mercenary();
        mercenary2.setName("Link");
        mercenary2.setEquipment(new ArrayList<>());
        mercenary2.setSkills(new ArrayList<>());
        mercenary2.setParent(mercenary1);
        this.mercenaryRepository.save(mercenary2);

    }
}
