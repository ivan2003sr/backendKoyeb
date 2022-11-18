package com.portfolio.ivan200sr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=1,max=50,message = "No cumple con la longitud mínima (1) o máxima (50)")
    private String nombre;
    @NotNull
    @Size(min=1,max=50,message = "No cumple con la longitud mínima (1) o máxima (50)")
    private String apellido;
    @Size(min=1,max=50,message = "No cumple con la longitud mínima (1) o máxima (50)")
    private String img;
}
