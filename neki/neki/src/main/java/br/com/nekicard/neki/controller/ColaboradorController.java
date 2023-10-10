package br.com.nekicard.neki.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nekicard.neki.dto.ColaboradorDTO;
import br.com.nekicard.neki.dto.ColaboradorReturnRegisterDTO;
import br.com.nekicard.neki.exception.NotFoundException;
import br.com.nekicard.neki.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaborador")
@SecurityRequirement(name = "Bearer Auth")
public class ColaboradorController {
	
	@Autowired
	ColaboradorService colaboradorService;
	

	@GetMapping("/listar")
	@Operation(summary = "Lista todas os colaboradores", description = "Listagem de colaboradores")
	public List<ColaboradorDTO> listar(){
		return colaboradorService.listarTudo();
	}
	
	@PostMapping(path = "/inserir")
	@Operation(summary = "Cadastro de colaborador", description = "Cadastro")
	public ResponseEntity<Object> addColaborador(@Valid @RequestParam("imagem") MultipartFile file,String email, String nome, @Nullable  String nomeSocial, Date dataDeNascimento, @Nullable String telefone,  @Nullable String instagram,  @Nullable  String gitHub, @Nullable  String linkedin, @Nullable  String facebook) throws IOException {
		 var colaboradorSalvo = colaboradorService.addColaborador(email, nome, nomeSocial, dataDeNascimento, file, telefone, instagram, gitHub, linkedin, facebook);
		 
		 if (!email.matches(".+@(neki(-it)?\\.com\\.br)$") ) {
	            throw new NotFoundException("O endereço de e-mail precisa ser com @neki-it.");
	        }
		
		 return ResponseEntity.ok(colaboradorSalvo);
	}
	
	@GetMapping("colaboradorId/{id}")
	@Operation(summary = "Achar colaborador por id", description = "Listar")
	public ColaboradorDTO findById(@PathVariable("id") Long colaboradorId) {
		return colaboradorService.buscarColaborador(colaboradorId);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclusão de colaborador", description = "Exclusão")
	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADM')")
	public ResponseEntity<?> excluirColaborador(@PathVariable("id") Long id) {
		colaboradorService.excluirColaborador(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("atualizar/{id}")
	@Operation(summary = "Atualização de colaborador", description = "Atualizar ")
	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADM')")
	public ResponseEntity<Object> atualizarColaborador(@RequestBody ColaboradorReturnRegisterDTO colaboradorDTO, @PathVariable("id") Long colaboradorID) {
		return ResponseEntity.ok(colaboradorService.atualizarColaborador(colaboradorDTO, colaboradorID));
	}
	
}
