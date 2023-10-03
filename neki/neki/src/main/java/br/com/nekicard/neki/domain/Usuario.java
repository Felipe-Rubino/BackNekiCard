package br.com.nekicard.neki.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.nekicard.neki.security.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
property = "usuarioId", scope = Usuario.class )
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_cd_id")
	private Long usuarioId;
	
	@OneToOne(mappedBy="usuario")
	private User usuario;
	
	@Column(name="usu_tx_nome")
	private String nome;
	
	@OneToOne(mappedBy = "usuario")
	private Colaborador colaborador;
	
	public Usuario() {
		
	}

	public Usuario(Long usuarioId, User usuario, String nome, Colaborador colaborador) {
		this.usuarioId = usuarioId;
		this.usuario = usuario;
		this.nome = nome;
		this.colaborador = colaborador;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
	
}
