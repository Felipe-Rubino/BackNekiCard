package br.com.nekicard.neki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nekicard.neki.domain.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
	
	
}
