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

import mercenarios.mercenarios.models.Equipment;
import mercenarios.mercenarios.services.EquipmentService;

@RestController
@RequestMapping("/equipments")
public class EquipamentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getEquipments() {
        return new ResponseEntity<List<Equipment>>(equipmentService.getEquipments(), HttpStatus.OK);

    }

    @GetMapping("/{name}")
    public ResponseEntity<Equipment> getEquipmentByName(@PathVariable("name") String name) {
        return new ResponseEntity<Equipment>(equipmentService.getEquipmentByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipments(@RequestBody Equipment equipment) {
        return new ResponseEntity<Equipment>(equipmentService.createEquipment(equipment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipments(@PathVariable("id") Integer id,
            @RequestBody Equipment equipment) {
        return new ResponseEntity<Equipment>(equipmentService.updateEquipment(id, equipment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipments(@PathVariable("id") Integer id) {
        equipmentService.deleteEquipment(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
