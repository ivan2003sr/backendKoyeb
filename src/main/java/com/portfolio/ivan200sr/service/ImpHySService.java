package com.portfolio.ivan200sr.service;

import com.portfolio.ivan200sr.entity.HySskills;
import com.portfolio.ivan200sr.repository.IHySSkills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ImpHySService {
    @Autowired
    IHySSkills iHySSkills;

    public List<HySskills> list(){
        return iHySSkills.findAll();
    }

    public Optional<HySskills> getOne(int id){
        return iHySSkills.findById(id);
    }

    public Optional<HySskills> getByNombre(String nombre){
        return iHySSkills.findByNombre(nombre);
    }

    public void save(HySskills skill){
        iHySSkills.save(skill);
    }

    public void delete(int id){
        iHySSkills.deleteById(id);
    }

    public boolean existsById(int id){
        return iHySSkills.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return iHySSkills.existsByNombre(nombre);
    }



}
