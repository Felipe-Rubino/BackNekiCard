package br.com.nekicard.neki.dto;

public class ImagemDTO {
	
	private Long imagemId;
	private byte[] dados;
	private String nome;
	private String tipo;
	
	public Long getImagemId() {
		return imagemId;
	}
	public void setImagemId(Long imagemId) {
		this.imagemId = imagemId;
	}
	public byte[] getDados() {
		return dados;
	}
	public void setDados(byte[] dados) {
		this.dados = dados;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
