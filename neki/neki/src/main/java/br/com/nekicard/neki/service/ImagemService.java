package br.com.nekicard.neki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nekicard.neki.domain.Imagem;
import br.com.nekicard.neki.dto.ImagemDTO;
import br.com.nekicard.neki.exception.NotFoundException;
import br.com.nekicard.neki.repository.ImagemRepository;

@Service
public class ImagemService {
	
	@Autowired
	ImagemRepository imagemRepository;
	
	public List<ImagemDTO> listarTudo(){
		List<Imagem> imagens = imagemRepository.findAll();
		List<ImagemDTO> imagemDTO = new ArrayList<>();
		

		if (imagens.isEmpty() || imagens == null) {
			throw new NotFoundException("Não há imagens cadastradas");
		}
		
		for(Imagem imagem : imagens) {
			ImagemDTO imagemReturnDTO = new ImagemDTO();
			imagemReturnDTO.setDados(imagem.getDados());
			imagemReturnDTO.setImagemId(imagem.getImagemId());
			imagemReturnDTO.setNome(imagem.getNome());
			imagemReturnDTO.setTipo(imagem.getTipo());
			imagemDTO.add(imagemReturnDTO);
		}
		return imagemDTO;
	}
	
	public ImagemDTO buscarImagemId(Long id) {
		Optional<Imagem> encontrarImagem = imagemRepository.findById(id);
		Imagem imagem = encontrarImagem.get();
		ImagemDTO imagemReturnDTO = new ImagemDTO();
		
		imagemReturnDTO.setDados(imagem.getDados());
		imagemReturnDTO.setImagemId(imagem.getImagemId());
		imagemReturnDTO.setNome(imagem.getNome());
		imagemReturnDTO.setTipo(imagem.getTipo());
			
		return imagemReturnDTO;
	}
	
	public Imagem salvarImagem(Imagem imagem) {
		return imagemRepository.save(imagem);
	}
	
	public void excluirImagem(Long id) {
		imagemRepository.deleteById(id);
	}
}
