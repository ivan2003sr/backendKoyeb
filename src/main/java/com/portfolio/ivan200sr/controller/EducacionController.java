package com.portfolio.ivan200sr.controller;


import com.portfolio.ivan200sr.dto.DtoEducacion;
import com.portfolio.ivan200sr.dto.DtoEducacion;
import com.portfolio.ivan200sr.entity.Educacion;
import com.portfolio.ivan200sr.entity.Educacion;
import com.portfolio.ivan200sr.security.Controller.Mensaje;
import com.portfolio.ivan200sr.service.ImpEducacionService;
import com.portfolio.ivan200sr.service.ImpEducacionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://frontend-ivan.web.app")
public class EducacionController {
    @Autowired
    ImpEducacionService impEducacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = impEducacionService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion){
        if(StringUtils.isBlank(dtoEducacion.getNombreEd()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);

        if(impEducacionService.existsByNombreEd(dtoEducacion.getNombreEd()))
            return new ResponseEntity<>(new Mensaje("Esa educacion ya existe, no se puede repetir"), HttpStatus.BAD_REQUEST);

        Educacion educacion = new Educacion(dtoEducacion.getNombreEd(), dtoEducacion.getDescripcionEd());
        impEducacionService.save(educacion);
        return new ResponseEntity<>(new Mensaje("Educacion agregada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEducacion){

        if(!impEducacionService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        if(impEducacionService.existsByNombreEd(dtoEducacion.getNombreEd()) && impEducacionService.getByNombreEd(dtoEducacion.getNombreEd()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esa Educacion ya existe"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoEducacion.getNombreEd()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio, no se puede dejar en blanco"),HttpStatus.BAD_REQUEST);

        Educacion educacion = impEducacionService.getOne(id).get();
        educacion.setNombreEd(dtoEducacion.getNombreEd());
        educacion.setDescripcionEd(dtoEducacion.getDescripcionEd());
        impEducacionService.save(educacion);
        return new ResponseEntity<>(new Mensaje("Educacion actualizada"),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!impEducacionService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID que está intentando borrar, no existe"),HttpStatus.BAD_REQUEST);
        impEducacionService.delete(id);
        return new ResponseEntity<>(new Mensaje("Educacion eliminada"),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!impEducacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = impEducacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

}
