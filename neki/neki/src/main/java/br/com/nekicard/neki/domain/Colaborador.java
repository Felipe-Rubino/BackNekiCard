package br.com.nekicard.neki.domain;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="colaborador")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
property = "colaboradorId", scope = Colaborador.class )
public class Colaborador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="colaborador_cd_id")
	private Long colaboradorId;
	
	@Column(name="col_tx_email", unique=true )
	@NotBlank
	private String email;
	
	@Column(name="col_tx_nome")
	@NotBlank
	private String nome;
	
	@OneToOne
	@JoinColumn(name="usuario_cd_id")
	private Usuario usuario;
	
	@Column(name="col_tx_nomeSocial")
	private String nomeSocial;
	
	@Column(name="col_dt_nascimento")
	private Date dataDeNascimento;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "fk_imagem_id")
	private Imagem imagem;
	
	@Column(name = "col_tx_telefone")
	private String telefone;
	
	@Column(name = "col_tx_inst")
	private String instagram;
	
	@Column(name = "col_tx_github")
	private String gitHub;
	
	@Column(name = "col_tx_link")
	private String linkedin;
	
	@Column(name = "col_tx_face")
	private String facebook;
	
	public Colaborador() {
		
	}

	public Colaborador(Long colaboradorId, String email, String nome, String nomeSocial, Date dataDeNascimento,
			Imagem imagem, String telefone, String instagram, String gitHub, String linkedin, String facebook,Usuario usuario) {
		
		this.colaboradorId = colaboradorId;
		this.email = email;
		this.nome = nome;
		this.nomeSocial = nomeSocial;
		this.dataDeNascimento = dataDeNascimento;
		this.imagem = imagem;
		this.telefone = telefone;
		this.instagram = instagram;
		this.gitHub = gitHub;
		this.linkedin = linkedin;
		this.facebook = facebook;
		this.usuario = usuario;
	}

	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getColaboradorId() {
		return colaboradorId;
	}

	public void setColaboradorId(Long colaboradorId) {
		this.colaboradorId = colaboradorId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getGitHub() {
		return gitHub;
	}

	public void setGitHub(String gitHub) {
		this.gitHub = gitHub;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
	
	
	
	
	
	
}
