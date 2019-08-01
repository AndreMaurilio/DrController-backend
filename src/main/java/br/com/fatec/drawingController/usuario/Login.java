package br.com.fatec.drawingController.usuario;

import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;


//@Entity
//@Table(name="LOGIN")
public class Login  {

 
    
    private String email;

    private String senha;


}