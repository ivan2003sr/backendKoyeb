package com.portfolio.ivan200sr.interfaces;

import com.portfolio.ivan200sr.entity.Persona;

import java.util.List;

public interface IPersonaService {
    //Trae lista de personas
    public List<Persona> getPersona();

    //Guardar Personas
    public void savePersona(Persona persona);

    //Eliminar usuario por ID
    public void deletePersona(Long id);

    //Buscar por ID
    public Persona findPersona(Long id);
}
