package com.portfolio.ivan200sr.service;

import com.portfolio.ivan200sr.entity.Experiencia;
import com.portfolio.ivan200sr.repository.IExperienciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImpExperienciaService {
    @Autowired
    IExperienciaRepo iExperienciaRepo;

    public List<Experiencia> list(){
        return iExperienciaRepo.findAll();
    }

    public Optional<Experiencia> getOne(int id){
        return iExperienciaRepo.findById(id);
    }

    public Optional<Experiencia> getByNombreE(String nombreE){
        return iExperienciaRepo.findByNombreE(nombreE);
    }

    public void save(Experiencia experiencia){
        iExperienciaRepo.save(experiencia);
    }

    public void delete(int id){
        iExperienciaRepo.deleteById(id);
    }

    public boolean existsById(int id){
        return iExperienciaRepo.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
        return iExperienciaRepo.existsByNombreE(nombreE);
    }

}
