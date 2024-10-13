package org.iesvdm.tienda.repository;

import org.iesvdm.tienda.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    
}
