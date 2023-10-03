package br.com.nekicard.neki.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nekicard.neki.dto.ColaboradorDTO;
import br.com.nekicard.neki.exception.NotFoundException;
import br.com.nekicard.neki.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {
	
	@Autowired
	ColaboradorService colaboradorService;
	

	@GetMapping("/listar")
	@Operation(summary = "Lista todas os colaboradores", description = "Listagem de colaboradores")
	public List<ColaboradorDTO> listar(){
		return colaboradorService.listarTudo();
	}
	
	@PostMapping(path = "/inserir", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Cadastro de colaborador", description = "Cadastro")
	public ResponseEntity<Object> addColaborador(@RequestParam("imagem") MultipartFile file,String email, String nome, @Nullable  String nomeSocial, Date dataDeNascimento, @Nullable String telefone,  @Nullable String instagram,  @Nullable  String gitHub, @Nullable  String linkedin, @Nullable  String facebook) throws IOException {
		 var colaboradorSalvo = colaboradorService.addSkill(email, nome, nomeSocial, dataDeNascimento, file, telefone, instagram, gitHub, linkedin, facebook);
		 
		 if (!email.matches(".+@(neki(-it)?\\.com\\.br)$") ) {
	            throw new NotFoundException("O endereço de e-mail precisa ser com @neki-it.");
	        }
		 return ResponseEntity.ok(colaboradorSalvo);
	}
}
