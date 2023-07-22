package net.java.springboot.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.java.springboot.config.JwtService;
import net.java.springboot.repository.UserRepository;
import net.java.springboot.repository.TokenRepository;
import net.java.springboot.token.Token;
import net.java.springboot.token.TokenType;
import net.java.springboot.user.Role;
import net.java.springboot.user.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	  public AuthenticationResponse register(RegisterRequest request) {
			// Verificar si el correo electrónico ya está en uso
			if (repository.findByEmail(request.getEmail()).isPresent()) {
				throw new EmailAlreadyInUseException("El correo electrónico ya está registrado.");
			}
			
		    var user = User.builder()
		        .firstname(request.getFirstname())
		        .lastname(request.getLastname())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(Role.USER)
		        .build();
		    var savedUser = repository.save(user);
		    var jwtToken = jwtService.generateToken(user);
		    saveUserToken(savedUser, jwtToken);
		    return AuthenticationResponse.builder()
		        .token(jwtToken)
		        .build();
	  }
	  
	  public AuthenticationResponse authenticate(AuthenticationRequest request) {
		    authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			            request.getEmail(),
			            request.getPassword()
			        )
			    );
			    var user = repository.findByEmail(request.getEmail())
			        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
			    var jwtToken = jwtService.generateToken(user);
			    revokeAllUserTokens(user);
			    saveUserToken(user, jwtToken);
			    return AuthenticationResponse.builder()
			            .token(jwtToken)
			            .build();
	  }

	  private void saveUserToken(User user, String jwtToken) {
		    var token = Token.builder()
			        .user(user)
			        .token(jwtToken)
			        .tokenType(TokenType.BEARER)
			        .expired(false)
			        .revoked(false)
			        .build();
			    tokenRepository.save(token);
	  }
	  
	  private void revokeAllUserTokens(User user) {
		    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		    if (validUserTokens.isEmpty())
		      return;
		    validUserTokens.forEach(token -> {
		      token.setExpired(true);
		      token.setRevoked(true);
		    });
		    tokenRepository.saveAll(validUserTokens);
	  }

	  public class EmailAlreadyInUseException extends RuntimeException {
			public EmailAlreadyInUseException(String message) {
				super(message);
			}
	  }
	  
}