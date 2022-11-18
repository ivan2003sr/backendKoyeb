package com.portfolio.ivan200sr.controller;


import com.portfolio.ivan200sr.dto.DtoExperiencia;
import com.portfolio.ivan200sr.entity.Experiencia;
import com.portfolio.ivan200sr.security.Controller.Mensaje;
import com.portfolio.ivan200sr.service.ImpExperienciaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/explab")
@CrossOrigin(origins = "https://frontend-ivan.web.app")
public class ExperienciaController {
    @Autowired
    ImpExperienciaService impExperienciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = impExperienciaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExperiencia){
        if(StringUtils.isBlank(dtoExperiencia.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);

        if(impExperienciaService.existsByNombreE(dtoExperiencia.getNombreE()))
            return new ResponseEntity<>(new Mensaje("Esa experiencia ya existe, no se puede repetir"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = new Experiencia(dtoExperiencia.getNombreE(), dtoExperiencia.getDescripcionE());
        impExperienciaService.save(experiencia);
        return new ResponseEntity<>(new Mensaje("Experiencia agregada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoExperiencia){

        if(!impExperienciaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        if(impExperienciaService.existsByNombreE(dtoExperiencia.getNombreE()) && impExperienciaService.getByNombreE(dtoExperiencia.getNombreE()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoExperiencia.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio, no se puede dejar en blanco"),HttpStatus.BAD_REQUEST);

        Experiencia experiencia = impExperienciaService.getOne(id).get();
        experiencia.setNombreE(dtoExperiencia.getNombreE());
        experiencia.setDescripcionE(dtoExperiencia.getDescripcionE());
        impExperienciaService.save(experiencia);
        return new ResponseEntity<>(new Mensaje("Experiencia actualizada"),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!impExperienciaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID que está intentando borrar, no existe"),HttpStatus.BAD_REQUEST);
        impExperienciaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Experiencia eliminada"),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!impExperienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = impExperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }



}
