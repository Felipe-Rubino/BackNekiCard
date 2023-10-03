	package br.com.nekicard.neki.security.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nekicard.neki.domain.Colaborador;
import br.com.nekicard.neki.domain.Imagem;
import br.com.nekicard.neki.domain.Usuario;
import br.com.nekicard.neki.dto.ColaboradorRegisterDTO;
import br.com.nekicard.neki.dto.ColaboradorReturnDTO;
import br.com.nekicard.neki.repository.ColaboradorRepository;
import br.com.nekicard.neki.repository.UsuarioRepository;
import br.com.nekicard.neki.security.domain.Role;
import br.com.nekicard.neki.security.domain.User;
import br.com.nekicard.neki.security.dto.JwtResponseDTO;
import br.com.nekicard.neki.security.dto.LoginRequestDTO;
import br.com.nekicard.neki.security.enums.RoleEnum;
import br.com.nekicard.neki.security.jwt.JwtUtils;
import br.com.nekicard.neki.security.repository.RoleRepository;
import br.com.nekicard.neki.security.repository.UserRepository;
import br.com.nekicard.neki.security.service.UserDetailsImpl;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ColaboradorRepository colaboradorRepository;
	
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
	
	public ColaboradorRegisterDTO mapToResponseDTO(Colaborador colaborador) {
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
	
	public ColaboradorReturnDTO mapToColaboradorReturnDTO(ColaboradorRegisterDTO colaboradorRegisterDTO) {
		ColaboradorReturnDTO colaboradorReturnDTO = new ColaboradorReturnDTO();
		
		colaboradorReturnDTO.setColaboradorId(colaboradorRegisterDTO.getColaboradorId());
		colaboradorReturnDTO.setDataDeNascimento(colaboradorRegisterDTO.getDataDeNascimento());
		colaboradorReturnDTO.setEmail(colaboradorRegisterDTO.getEmail());
		colaboradorReturnDTO.setFacebook(colaboradorRegisterDTO.getFacebook());
		colaboradorReturnDTO.setGitHub(colaboradorRegisterDTO.getGitHub());
		colaboradorReturnDTO.setImagem(colaboradorRegisterDTO.getImagem());
		colaboradorReturnDTO.setInstagram(colaboradorRegisterDTO.getInstagram());
		colaboradorReturnDTO.setLinkedin(colaboradorRegisterDTO.getLinkedin());
		colaboradorReturnDTO.setNome(colaboradorRegisterDTO.getNome());
		colaboradorReturnDTO.setNomeSocial(colaboradorRegisterDTO.getNomeSocial());
		colaboradorReturnDTO.setTelefone(colaboradorRegisterDTO.getTelefone());
		
		return colaboradorReturnDTO;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.toList();

		return ResponseEntity.ok(new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getEmail(), roles, userDetails.getLoggedUserId()));

	}
	
	@PostMapping("/signup")
	public ColaboradorReturnDTO registerUser(@Valid @RequestBody ColaboradorRegisterDTO colaboradorDTO, MultipartFile file) throws Exception{
	    if (userRepository.existsByEmail(colaboradorDTO.getEmail())) {
	    	throw new Exception("Erro: Email já utilizado!");
	    }
	    Colaborador colaborador = mapToEntity(colaboradorDTO, file);

	    ColaboradorReturnDTO colaboradorReturnDTOs = new ColaboradorReturnDTO();
	    
	    Usuario usuario = new Usuario();
	    usuario.setNome(colaboradorDTO.getSignupRequestDTO().getUsername());
	    usuario = usuarioRepository.save(usuario);

	
	    User user = new User(colaboradorDTO.getEmail(),
	            encoder.encode(colaboradorDTO.getSignupRequestDTO().getPassword()),
	            usuario
	            );

	    Set<String> strRoles = colaboradorDTO.getSignupRequestDTO().getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
	        roles.add(userRole);
	    } else {
	        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
	        roles.add(userRole);
	    }

	    user.setRoles(roles);
	    userRepository.save(user);
	    colaborador.setUsuario(user.getUsuario());
	    colaborador.setEmail(user.getEmail());
	    Colaborador colaboradorSalvo = colaboradorRepository.save(colaborador);
	    usuario.setUsuario(user);
	    usuario = usuarioRepository.save(usuario);
	    
	    ColaboradorRegisterDTO returnColaboradorDTO = mapToResponseDTO(colaboradorSalvo);
	    colaboradorReturnDTOs = mapToColaboradorReturnDTO(returnColaboradorDTO);

	    return colaboradorReturnDTOs;
	}
}
