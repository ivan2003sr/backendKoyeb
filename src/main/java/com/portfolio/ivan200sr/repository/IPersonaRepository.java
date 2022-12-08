package com.portfolio.ivan200sr.repository;

import com.portfolio.ivan200sr.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Integer> {
public Optional<Persona> findByNombre(String nombre);
public boolean existsByNombre(String nombre);
}
