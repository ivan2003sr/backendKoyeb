package com.portfolio.ivan200sr.repository;

import com.portfolio.ivan200sr.entity.HySskills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IHySSkills extends JpaRepository<HySskills, Integer> {

    Optional<HySskills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
