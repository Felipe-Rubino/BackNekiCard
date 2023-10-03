package br.com.nekicard.neki.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.nekicard.neki.domain.Colaborador;
import br.com.nekicard.neki.domain.Imagem;
import br.com.nekicard.neki.dto.ColaboradorDTO;
import br.com.nekicard.neki.exception.NotFoundException;
import br.com.nekicard.neki.repository.ColaboradorRepository;
import br.com.nekicard.neki.security.repository.UserRepository;


@Service
public class ColaboradorService {
	
	@Autowired
	ColaboradorRepository colaboradorRepository;
	

	@Autowired
	UserRepository userRepository;
		
		
	
	@Transactional
	public List<ColaboradorDTO> listarTudo(){
		List<Colaborador> colaboradores = colaboradorRepository.findAll();
		List<ColaboradorDTO> colaboradorDTO = new ArrayList<>();
		
		if (colaboradores.isEmpty() || colaboradores == null) {
			throw new NotFoundException("Não há cargos cadastrados");
		}
		for(Colaborador colaborador : colaboradores) {
			ColaboradorDTO colaboradorReturnDTO = new ColaboradorDTO();
			colaboradorReturnDTO.setColaboradorId(colaborador.getColaboradorId());
			colaboradorReturnDTO.setDataDeNascimento(colaborador.getDataDeNascimento());
			colaboradorReturnDTO.setEmail(colaborador.getEmail());
			colaboradorReturnDTO.setFacebook(colaborador.getEmail());
			colaboradorReturnDTO.setGitHub(colaborador.getGitHub());
			colaboradorReturnDTO.setImagem(colaborador.getImagem());
			colaboradorReturnDTO.setInstagram(colaborador.getInstagram());
			colaboradorReturnDTO.setLinkedin(colaborador.getLinkedin());
			colaboradorReturnDTO.setNome(colaborador.getNome());
			colaboradorReturnDTO.setNomeSocial(colaborador.getNomeSocial());
			colaboradorReturnDTO.setTelefone(colaborador.getTelefone());
			colaboradorDTO.add(colaboradorReturnDTO);
		}
		return colaboradorDTO;
	}
	
	public Colaborador mapToEntity(String email, String nome, String nomeSocial, Date dataDeNascimento, MultipartFile file, String telefone, String instagram, String gitHub, String linkedin, String facebook ) throws IOException {
		Colaborador colaborador = new Colaborador();
		Imagem imagem = new Imagem();
		imagem.setDados(file.getBytes());
		imagem.setNome(file.getName());
		imagem.setTipo(file.getContentType());
		colaborador.setEmail(email);
		colaborador.setNome(nome);
		colaborador.setNomeSocial(nomeSocial);
		colaborador.setDataDeNascimento(dataDeNascimento);
		colaborador.setImagem(imagem);
		colaborador.setTelefone(telefone);
		colaborador.setInstagram(instagram);
		colaborador.setGitHub(gitHub);
		colaborador.setLinkedin(linkedin);
		colaborador.setFacebook(facebook);
		return colaborador;
	}
	
	public ColaboradorDTO mapToResponseDTO(Colaborador colaborador) {
		ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
		colaboradorDTO.setColaboradorId(colaborador.getColaboradorId());
		colaboradorDTO.setDataDeNascimento(colaborador.getDataDeNascimento());
		colaboradorDTO.setEmail(colaborador.getEmail());
		colaboradorDTO.setFacebook(colaborador.getFacebook());
		colaboradorDTO.setGitHub(colaborador.getGitHub());
		colaboradorDTO.setImagem(colaborador.getImagem());
		colaboradorDTO.setInstagram(colaborador.getInstagram());
		colaboradorDTO.setLinkedin(colaborador.getLinkedin());
		colaboradorDTO.setNome(colaborador.getNome());
		colaboradorDTO.setNomeSocial(colaborador.getNomeSocial());
		colaboradorDTO.setTelefone(colaborador.getTelefone());
		return colaboradorDTO;
	}
	
	@Transactional
	public ColaboradorDTO addColaborador( String email, String nome, String nomeSocial, Date dataDeNascimento, MultipartFile file, String telefone, String instagram, String gitHub, String linkedin, String facebook)  throws IOException {
		Colaborador colaborador = mapToEntity(email, nome, nomeSocial, dataDeNascimento, file, telefone, instagram, gitHub, linkedin, facebook);
		
		if(colaborador.getNomeSocial().length() > 20) {
			throw new NotFoundException("Nome social deve ter menos que 20 letras");
		}
		if (colaboradorRepository.existsByEmail(colaborador.getEmail())) {
		    throw new NotFoundException("O endereço de e-mail já existe!");
		}
		
		Colaborador colaboradorSalvo = userRepository.save(colaborador);
		ColaboradorDTO returnColaboradorDTO = mapToResponseDTO(colaboradorSalvo);
		return returnColaboradorDTO;
	}
	
	public void excluirColaborador(Long colaboradorId) {
		Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(colaboradorId);
	    if (!colaboradorOptional.isPresent()) {
	        throw new NotFoundException("Não há colaborador com este ID: " + colaboradorId);
	    }
		colaboradorRepository.deleteById(colaboradorId);
	

	}
}	
