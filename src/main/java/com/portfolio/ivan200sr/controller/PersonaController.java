package com.portfolio.ivan200sr.controller;

import com.portfolio.ivan200sr.dto.DtoPersona;
import com.portfolio.ivan200sr.entity.Persona;
import com.portfolio.ivan200sr.security.Controller.Mensaje;
import com.portfolio.ivan200sr.service.ImpPersonaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"https://frontend-ivan.web.app","http://localhost:4200"})
public class PersonaController {

    @Autowired
    ImpPersonaService impPersonaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = impPersonaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoPersona){
        if(StringUtils.isBlank(dtoPersona.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);

        if(impPersonaService.existsByNombre(dtoPersona.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese usuario ya existe, no se puede repetir"), HttpStatus.BAD_REQUEST);

        Persona persona = new Persona(dtoPersona.getNombre(), dtoPersona.getApellido(), dtoPersona.getDescripcion(), dtoPersona.getImg());
        impPersonaService.save(persona);
        return new ResponseEntity<>(new Mensaje("Educacion agregada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoPersona){

        if(!impPersonaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        if(impPersonaService.existsByNombre(dtoPersona.getNombre()) && impPersonaService.getByNombre(dtoPersona.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esa Educacion ya existe"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPersona.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio, no se puede dejar en blanco"),HttpStatus.BAD_REQUEST);

        Persona persona = impPersonaService.getOne(id).get();
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setDescripcion(dtoPersona.getDescripcion());
        persona.setImg(dtoPersona.getImg());
        impPersonaService.save(persona);
        return new ResponseEntity<>(new Mensaje("Persona actualizada"),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID que está intentando borrar, no existe"),HttpStatus.BAD_REQUEST);
        impPersonaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Educacion eliminada"),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }


}
