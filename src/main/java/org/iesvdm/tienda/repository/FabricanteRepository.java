package org.iesvdm.tienda.repository;

import org.iesvdm.tienda.modelo.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {
    
}
