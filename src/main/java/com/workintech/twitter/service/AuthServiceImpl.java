package com.workintech.twitter.service;

import com.workintech.twitter.config.JwtService;
import com.workintech.twitter.dto.LoginRequestDto;
import com.workintech.twitter.dto.LoginResponseDto;
import com.workintech.twitter.dto.RegisterRequestDto;
import com.workintech.twitter.dto.RegisterResponseDto;
import com.workintech.twitter.entity.Role;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.UserAlreadyRegisteredException;
import com.workintech.twitter.exceptions.WrongPasswordException;
import com.workintech.twitter.repository.RoleRepository;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;


    @Override
    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findByEmail(requestDto.email());
        if(userOptional.isPresent()) {
            throw new UserAlreadyRegisteredException("Email already registered!");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.password());

        Role role = roleRepository.getRoleByAuthority("USER");

        User user = new User();
        user.setPassword(encodedPassword);
        user.setEmail(requestDto.email());
        user.setFullName(requestDto.fullName());
        user.setNickName(requestDto.nickName());
        user.setCreatedAt(LocalDate.now());
        user.setRoles(Set.of(role));
        user = userRepository.save(user);

        return new RegisterResponseDto(user.getEmail(), "Registered successfully!");
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email()).orElseThrow(() -> new UsernameNotFoundException("Username nor found' Username: " + loginRequestDto.email()));

        if(!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new WrongPasswordException("Password is not correct!");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponseDto(token, user.getFullName(), user.getNickName());
    }
}
