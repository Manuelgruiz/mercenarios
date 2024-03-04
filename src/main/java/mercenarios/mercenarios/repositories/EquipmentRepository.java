package mercenarios.mercenarios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import mercenarios.mercenarios.models.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    public Optional<Equipment> findByName(String name);
}
