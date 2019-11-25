package br.com.fatec.drawingController.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "AUT_AUTORIZACAO")
public class Autorizacao implements GrantedAuthority {

    private static final long serialVersionUID = 1071136239920166082L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUT_ID")
    private long id;

    @Column(name = "AUT_NOME", unique = true, length = 20, nullable = false)
    private String nomeAutorizacao;






    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAutorizacao() {
        return this.nomeAutorizacao;
    }

    public void setNomeAutorizacao(String nomeAutorizacao) {
        this.nomeAutorizacao = nomeAutorizacao;
    }

    @Override
    public String getAuthority() {
        return this.nomeAutorizacao;
    }

    public void setAuthority(String authority) {
        this.nomeAutorizacao = authority;
    }

}
