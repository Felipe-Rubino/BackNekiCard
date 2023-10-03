package br.com.nekicard.neki.dto;

import java.util.Date;

import br.com.nekicard.neki.domain.Imagem;
import br.com.nekicard.neki.security.dto.SignupRequestDTO;

public class ColaboradorRegisterDTO {
	
	
	private Long colaboradorId;
	private String email;
	private String nome;
	private String nomeSocial;
	private Date dataDeNascimento;
	private Imagem imagem;
	private String telefone;
	private String instagram;
	private String gitHub;
	private String linkedin;
	private String facebook;
	
	private SignupRequestDTO signupRequestDTO;
	
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
	public SignupRequestDTO getSignupRequestDTO() {
		return signupRequestDTO;
	}
	public void setSignupRequestDTO(SignupRequestDTO signupRequestDTO) {
		this.signupRequestDTO = signupRequestDTO;
	}
	
	
}
