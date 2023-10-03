package br.com.nekicard.neki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nekicard.neki.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
