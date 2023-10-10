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
import br.com.nekicard.neki.dto.ColaboradorRegisterDTO;
import br.com.nekicard.neki.dto.ColaboradorReturnRegisterDTO;
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
			colaboradorReturnDTO.setFacebook(colaborador.getFacebook());
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
	
	public ColaboradorRegisterDTO mapToRegisterResponseDTO(Colaborador colaborador) {
		ColaboradorRegisterDTO colaboradorDTO = new ColaboradorRegisterDTO();
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
	
	
	public Colaborador mapToEntity(ColaboradorRegisterDTO colaboradorDTO, MultipartFile file	) throws IOException {
		Colaborador colaborador = new Colaborador();
		Imagem imagem = new Imagem();
		imagem.setDados(file.getBytes());
		imagem.setNome(file.getName());
		imagem.setTipo(file.getContentType());
		colaborador.setEmail(colaboradorDTO.getEmail());
		colaborador.setNome(colaboradorDTO.getNome());
		colaborador.setNomeSocial(colaboradorDTO.getNomeSocial());
		colaborador.setDataDeNascimento(colaboradorDTO.getDataDeNascimento());
		colaborador.setImagem(imagem);
		colaborador.setTelefone(colaboradorDTO.getTelefone());
		colaborador.setInstagram(colaboradorDTO.getInstagram());
		colaborador.setGitHub(colaboradorDTO.getGitHub());
		colaborador.setLinkedin(colaboradorDTO.getLinkedin());
		colaborador.setFacebook(colaboradorDTO.getFacebook());
		return colaborador;
	}
	
	public ColaboradorReturnRegisterDTO mapToColaboradorReturnDTO(ColaboradorRegisterDTO colaboradorRegisterDTO) {
		ColaboradorReturnRegisterDTO colaboradorReturnDTO = new ColaboradorReturnRegisterDTO();
		
		colaboradorReturnDTO.setDataDeNascimento(colaboradorRegisterDTO.getDataDeNascimento());
		colaboradorReturnDTO.setEmail(colaboradorRegisterDTO.getEmail());
		colaboradorReturnDTO.setFacebook(colaboradorRegisterDTO.getFacebook());
		colaboradorReturnDTO.setGitHub(colaboradorRegisterDTO.getGitHub());
		colaboradorReturnDTO.setInstagram(colaboradorRegisterDTO.getInstagram());
		colaboradorReturnDTO.setLinkedin(colaboradorRegisterDTO.getLinkedin());
		colaboradorReturnDTO.setNome(colaboradorRegisterDTO.getNome());
		colaboradorReturnDTO.setNomeSocial(colaboradorRegisterDTO.getNomeSocial());
		colaboradorReturnDTO.setTelefone(colaboradorRegisterDTO.getTelefone());
		
		return colaboradorReturnDTO;
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
	
	public ColaboradorReturnRegisterDTO atualizarColaborador(ColaboradorReturnRegisterDTO colaboradorDTO, Long colaboradorId) {
		Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(colaboradorId);
		
		if(colaboradorOptional.isEmpty()  || colaboradorOptional.get() == null) {
			throw new NotFoundException("Não há um colaborador registrado com esse ID: " + colaboradorId);
		}
		
		Colaborador colaboradorAntigo = colaboradorOptional.get();
		ColaboradorReturnRegisterDTO colaboradorReturnDTOs = new ColaboradorReturnRegisterDTO();
		
		
		
		colaboradorAntigo.setDataDeNascimento(colaboradorDTO.getDataDeNascimento());
		colaboradorAntigo.setEmail(colaboradorDTO.getEmail());
		colaboradorAntigo.setFacebook(colaboradorDTO.getFacebook());
		colaboradorAntigo.setGitHub(colaboradorDTO.getGitHub());
		colaboradorAntigo.setInstagram(colaboradorDTO.getInstagram());
		colaboradorAntigo.setLinkedin(colaboradorDTO.getLinkedin());
		colaboradorAntigo.setNome(colaboradorDTO.getNome());
		colaboradorAntigo.setNomeSocial(colaboradorDTO.getNomeSocial());
		colaboradorAntigo.setTelefone(colaboradorDTO.getTelefone());
		
		colaboradorRepository.save(colaboradorAntigo);	
		
		ColaboradorRegisterDTO returnColaboradorDTO = mapToRegisterResponseDTO(colaboradorAntigo);
		colaboradorReturnDTOs = mapToColaboradorReturnDTO(returnColaboradorDTO);
		
		return colaboradorReturnDTOs;
	}
	
	public ColaboradorDTO buscarColaborador(Long colaboradorId) {
		Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(colaboradorId);
		Colaborador colaborador = colaboradorOptional.get();
		ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
		colaboradorDTO.setColaboradorId(colaborador.getColaboradorId());
		colaboradorDTO.setEmail(colaborador.getEmail());
		colaboradorDTO.setFacebook(colaborador.getFacebook());
		colaboradorDTO.setGitHub(colaborador.getGitHub());
		colaboradorDTO.setImagem(colaborador.getImagem());
		colaboradorDTO.setInstagram(colaborador.getInstagram());
		colaboradorDTO.setLinkedin(colaborador.getLinkedin());
		colaboradorDTO.setNome(colaborador.getNome());
		colaboradorDTO.setNomeSocial(colaborador.getNomeSocial());
		colaboradorDTO.setTelefone(colaborador.getTelefone());
		colaboradorDTO.setDataDeNascimento(colaborador.getDataDeNascimento());
		
		return colaboradorDTO;
	}
}	
