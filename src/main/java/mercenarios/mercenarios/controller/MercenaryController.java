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

import mercenarios.mercenarios.models.Mercenary;

import mercenarios.mercenarios.services.MercenaryService;

@RestController
@RequestMapping("/mercenaries")
public class MercenaryController {

    @Autowired
    private MercenaryService mercenaryService;

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
}