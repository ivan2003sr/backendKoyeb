package com.portfolio.ivan200sr.controller;

import com.portfolio.ivan200sr.dto.DtoHyS;
import com.portfolio.ivan200sr.entity.Experiencia;
import com.portfolio.ivan200sr.entity.HySskills;
import com.portfolio.ivan200sr.security.Controller.Mensaje;
import com.portfolio.ivan200sr.service.ImpHySService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@CrossOrigin(origins = "https://frontend-ivan.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class HySController {
    @Autowired
    ImpHySService impHySService;

    @GetMapping("/lista")
    public ResponseEntity<List<HySskills>> list() {
        List<HySskills> list = impHySService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHyS dtoHyS) {
        if (StringUtils.isBlank(dtoHyS.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if (impHySService.existsByNombre(dtoHyS.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese skill ya existe, no se puede repetir"), HttpStatus.BAD_REQUEST);

        HySskills hySskills = new HySskills(dtoHyS.getNombre(), dtoHyS.getPorcentaje());
        impHySService.save(hySskills);
        return new ResponseEntity<>(new Mensaje("Skill agregado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHyS dtoHyS) {

        if (!impHySService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        if (impHySService.existsByNombre(dtoHyS.getNombre()) && impHySService.getByNombre(dtoHyS.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Ese skill ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(dtoHyS.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio, no se puede dejar en blanco"), HttpStatus.BAD_REQUEST);

        HySskills hySskills = impHySService.getOne(id).get();
        hySskills.setNombre(dtoHyS.getNombre());
        hySskills.setPorcentaje(dtoHyS.getPorcentaje());
        impHySService.save(hySskills);
        return new ResponseEntity<>(new Mensaje("Skill actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!impHySService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID que está intentando borrar, no existe"), HttpStatus.BAD_REQUEST);
        impHySService.delete(id);
        return new ResponseEntity<>(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!impHySService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        HySskills hySskills = impHySService.getOne(id).get();
        return new ResponseEntity(hySskills, HttpStatus.OK);
    }


}
