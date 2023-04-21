package com.portfolio.ivan200sr.repository;

import com.portfolio.ivan200sr.entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEducacionRepo extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByNombreEd(String nombreEd);
    public boolean existsByNombreEd(String nombreEd);
}