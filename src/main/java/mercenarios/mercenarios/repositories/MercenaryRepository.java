package mercenarios.mercenarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import mercenarios.mercenarios.models.Mercenary;

@Repository
public interface MercenaryRepository extends JpaRepository<Mercenary, Integer> {

}
