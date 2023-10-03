package br.com.nekicard.neki.domain;


import java.sql.Types;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "imagem")
public class Imagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "img_cd_id")
	private Long imagemId;
	
	@Lob	
	@JdbcTypeCode(Types.BINARY)
	@Column(name = "img_tx_dados")
	private byte[] dados;
	
	@JsonIgnore
	@Column(name = "img_tx_nome")
	private String nome;
	
	@JsonIgnore
	@Column(name = "img_tx_tipo")
	private String tipo;
	
	@OneToOne(mappedBy="imagem", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private Colaborador colaborador;
	
	public Imagem() {
		
	}
	
	public Imagem(byte[] dados, String nome, String tipo) {
		this.dados = dados;
		this.nome = nome;
		this.tipo = tipo;
	}



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

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
	
}
