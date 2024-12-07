package com.exploraperu.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Usuario {
	
	private int idUsuario;
    private String nombre;
    private String correo;
    private String clave;
    private String telefono;
    private int idRol;
    private Date fechaCreacion;
    private Date fechaCumple;
    private String img;

}
