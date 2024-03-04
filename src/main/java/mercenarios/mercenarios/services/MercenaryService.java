package mercenarios.mercenarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import mercenarios.mercenarios.models.Equipment;
import mercenarios.mercenarios.models.Mercenary;
import mercenarios.mercenarios.repositories.EquipmentRepository;
import mercenarios.mercenarios.repositories.MercenaryRepository;

@Service
public class MercenaryService {
    @Autowired
    private MercenaryRepository mercenaryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired

    public List<Mercenary> getMercenaries() {
        return mercenaryRepository.findAll();
    }

    public Mercenary createMercenary(Mercenary mercenary) {
        return mercenaryRepository.save(mercenary);
    }

    public Mercenary updateMercenary(Integer id, Mercenary mercenary) {
        Optional<Mercenary> res = mercenaryRepository.findById(id);
        if (res.isPresent()) {
            Mercenary existingMercenary = res.get();
            existingMercenary.setName(mercenary.getName());
            // Update other fields as needed
            return mercenaryRepository.save(existingMercenary);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mercenary not found");
        }
    }

    public void deleteMercenary(Integer id) {
        Optional<Mercenary> res = mercenaryRepository.findById(id);
        if (res.isPresent()) {
            mercenaryRepository.delete(res.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mercenary not found");
        }
    }

    public void assignEquipmentToMercenary(Integer mercenaryId, Integer equipmentId) {
        Optional<Mercenary> mercenaryOptional = mercenaryRepository.findById(mercenaryId);
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);

        if (mercenaryOptional.isPresent() && equipmentOptional.isPresent()) {
            Mercenary mercenary = mercenaryOptional.get();
            Equipment equipment = equipmentOptional.get();

            mercenary.getEquipment().add(equipment);
            mercenaryRepository.save(mercenary);
        } else {
            throw new EntityNotFoundException("Mercenary or Equipment not found");
        }
    }

}