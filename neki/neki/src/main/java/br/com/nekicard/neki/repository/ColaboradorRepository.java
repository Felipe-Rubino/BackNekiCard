package br.com.nekicard.neki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nekicard.neki.domain.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
	
	Boolean existsByEmail(String email);

}
