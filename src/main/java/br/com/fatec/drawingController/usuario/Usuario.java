package br.com.fatec.drawingController.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.fatec.drawingController.desenho.Desenho;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "USUARIO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@SequenceGenerator(name = "hibernate_seq", sequenceName = "hibernate_seq", initialValue = 5, allocationSize = 1)

public class Usuario implements UserDetails {

    private static final long serialVersionUID = 9102228635788384787L;

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
    @Column(name = "usu_id")
    private long id;

    @Column(name = "usu_idcad")
    private String idCad;

    @Column(name = "usu_nome")
    private String nome;

    @Column(name = "usu_disciplina")
    private String disciplina;

    @Column(name = "usu_email")
    private String email;

    @Column(name = "usu_senha")
    private String senha;

    @Column(name = "usu_funcao")
    private String perfil;

    // @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    // @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    // @JsonManagedReference
    private List<Desenho> desenhos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UAU_USUARIO_AUTORIZACAO", joinColumns = { @JoinColumn(name = "USU_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "AUT_ID") })
    @XmlElement(name = "AUTORIZACAO")
    private List<Autorizacao> autorizacoes;

    public Usuario() {

        this.desenhos = new ArrayList<>();

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdCad() {
        return this.idCad;
    }

    public void setIdCad(String idCad) {
        this.idCad = idCad;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<Desenho> getDesenhos() {
        return this.desenhos;
    }

    public void setDesenhos(List<Desenho> desenhos) {
        this.desenhos = desenhos;
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}