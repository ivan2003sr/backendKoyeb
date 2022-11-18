package com.portfolio.ivan200sr.security.repository;

import com.portfolio.ivan200sr.security.entity.Rol;
import com.portfolio.ivan200sr.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre (RolNombre rolNombre);

}
