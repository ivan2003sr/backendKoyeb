package com.portfolio.ivan200sr.service;

import com.portfolio.ivan200sr.entity.Educacion;
import com.portfolio.ivan200sr.repository.IEducacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImpEducacionService {
    @Autowired
    IEducacionRepo iEducacionRepo;

    public List<Educacion> list() {
        return iEducacionRepo.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return iEducacionRepo.findById(id);
    }

    public Optional<Educacion> getByNombreEd(String nombreEd) {
        return iEducacionRepo.findByNombreEd(nombreEd);
    }

    public void save(Educacion educacion) {
        iEducacionRepo.save(educacion);
    }

    public void delete(int id) {
        iEducacionRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return iEducacionRepo.existsById(id);
    }

    public boolean existsByNombreEd(String nombreEd) {
        return iEducacionRepo.existsByNombreEd(nombreEd);
    }

}
