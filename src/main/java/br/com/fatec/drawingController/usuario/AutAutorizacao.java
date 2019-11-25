package br.com.fatec.drawingController.usuario;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.security.core.GrantedAuthority;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@SequenceGenerator(name = "hibernate_seq", sequenceName = "hibernate_seq", initialValue = 1, allocationSize = 1)
@Table(name = "uau_usuario_autorizacao")
public class AutAutorizacao  {

    private static final long serialVersionUID = 1071136239920166082L;

    @Id  
    @Column(name = "usu_id")
    private long usuario;

    @Column(name = "aut_id")
    private long autorizacao;




    public AutAutorizacao() {
    }

    public AutAutorizacao(long usuario, long autorizacao) {
        this.usuario = usuario;
        this.autorizacao = autorizacao;
    }

    public long getUsuario() {
        return this.usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public long getAutorizacao() {
        return this.autorizacao;
    }

    public void setAutorizacao(long autorizacao) {
        this.autorizacao = autorizacao;
    }

    public AutAutorizacao usuario(long usuario) {
        this.usuario = usuario;
        return this;
    }

    public AutAutorizacao autorizacao(long autorizacao) {
        this.autorizacao = autorizacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AutAutorizacao)) {
            return false;
        }
        AutAutorizacao autAutorizacao = (AutAutorizacao) o;
        return usuario == autAutorizacao.usuario && autorizacao == autAutorizacao.autorizacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, autorizacao);
    }

    @Override
    public String toString() {
        return "{" +
            " usuario='" + getUsuario() + "'" +
            ", autorizacao='" + getAutorizacao() + "'" +
            "}";
    }
    


}