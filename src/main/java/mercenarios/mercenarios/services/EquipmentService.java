package mercenarios.mercenarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mercenarios.mercenarios.models.Equipment;
import mercenarios.mercenarios.repositories.EquipmentRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Integer id, Equipment equipment) {
        Optional<Equipment> res = equipmentRepository.findById(id);
        if (res.isPresent()) {
            Equipment existingEquipment = res.get();
            existingEquipment.setName(equipment.getName());
            return equipmentRepository.save(existingEquipment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found");
        }
    }

    public void deleteEquipment(Integer id) {
        Optional<Equipment> res = equipmentRepository.findById(id);
        if (res.isPresent()) {
            equipmentRepository.delete(res.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found");

        }
    }

    public Equipment getEquipmentByName(String name) {
        return equipmentRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));
    }

}
