package br.com.nekicard.neki.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nekicard.neki.domain.Imagem;
import br.com.nekicard.neki.dto.ImagemDTO;
import br.com.nekicard.neki.service.ImagemService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/imagem")
public class ImagemController {
	
	@Autowired
	ImagemService imagemService;
	
	@GetMapping("/lista")
	@Operation(summary = "Lista todas as imagens", description = "Listagem de imagens")
	public List<ImagemDTO> findAll(){
		return imagemService.listarTudo();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista as imagem por ID", description = "Listagem de imagens")
	public ImagemDTO findById(@PathVariable("id") Long id) {
		return imagemService.buscarImagemId(id);
	}
	
	@PostMapping(path = "/inserir", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	@Operation(summary = "Upload de Imagem", description = "Upload imagem")
	public Imagem uploadImagem(@RequestPart("imagem") MultipartFile file) {
		String tipoImagem = file.getContentType();
		byte[]dados = null;
		try {
			dados = file.getBytes();
		}catch (IOException e) {
			e.printStackTrace();
		}
		String nomeImagem = file.getOriginalFilename();
		Imagem imagem = new Imagem(dados, tipoImagem, nomeImagem);
		
		return imagemService.salvarImagem(imagem);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar imagens", description = "Exclusão")
	public void deletarImagem(@PathVariable("id") Long id) {
		imagemService.excluirImagem(id);
	}
}
