package com.app.service.service;

import com.app.service.dto.LoginDto;
import com.app.service.dto.SignUpDto;
import com.app.service.dto.UserDto;
import com.app.service.exception.BadRequestException;
import com.app.service.exception.NotFoundEntityException;
import com.app.service.responce.ApiResponse;
import com.app.service.responce.JwtAuthenticationResponse;
import com.app.audit.entities.User;
import com.app.security.jwt.service.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.audit.repository.UsersRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
public class AuthenticateServiceImpl implements AuthenticateService{

    private UsersRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    private JWTService jwtService;

   @Autowired
    public AuthenticateServiceImpl(UsersRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JWTService jwtService){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder =passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(LoginDto loginDTO) throws UnsupportedEncodingException {

        User user = getUser( loginDTO.getUsernameOrEmail());

        if (user.getRoles().contains("ROLE_ADMIN") && passwordEncoder.matches( loginDTO.getPassword(), user.getPassword())){
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwtService.generateToken(user)));
        }
        if (passwordEncoder.matches( loginDTO.getPassword(), user.getPassword()) ){
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwtService.generateToken(user)));
        }

        throw new BadRequestException("password wrong");
    }

    @Override
    public ResponseEntity<ApiResponse> registerUser(SignUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new BadRequestException( "Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new BadRequestException("Email Address already in use!");
        }

        User user = new User(signUpDto.getName(), signUpDto.getUsername(),
                signUpDto.getEmail(), signUpDto.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        user.setRoles(userRepository.findAll().size() > 0 ? Collections.singleton(RoleName.ROLE_USER.name()) : Collections.singleton(RoleName.ROLE_ADMIN.name()));
        userRepository.save(user);

        return new ResponseEntity(new ApiResponse(HttpStatus.CREATED, "User registered successfully" ), HttpStatus.CREATED);
    }

    private User getUser(String email){

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundEntityException("User not found!", "User not found with email " + email, HttpStatus.NOT_FOUND));
        return user;
    }

}
