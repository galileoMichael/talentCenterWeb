package com.tujuhsembilan.talentcenter.service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.talentcenter.dto.request.LoginRequest;
import com.tujuhsembilan.talentcenter.dto.request.RegisterRequest;
import com.tujuhsembilan.talentcenter.model.UserDetail;
import com.tujuhsembilan.talentcenter.model.User;
import com.tujuhsembilan.talentcenter.repository.UserRepository;
import com.tujuhsembilan.talentcenter.response.LoginResponse;
import com.tujuhsembilan.talentcenter.response.MessageResponse;
import com.tujuhsembilan.talentcenter.util.jwt.JwtUtil;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
     
    public ResponseEntity<MessageResponse> registerUser(RegisterRequest request){

        try {
            Set<ConstraintViolation<RegisterRequest>> constraintViolations = validator.validate(request);
            if (!constraintViolations.isEmpty()){
                ConstraintViolation<RegisterRequest> firsViolation = constraintViolations.iterator().next();
                String message = firsViolation.getMessage();
                return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(message, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
            }

            if (userRepository.existsByEmail(request.getEmail())){
                String message = messageSource.getMessage("email.exist", null, Locale.getDefault());

                return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(message, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
            }

            User userModel = User.builder()
                .email(request.getEmail())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .build();

            userRepository.save(userModel);

            String message = messageSource.getMessage("register.successful", null, Locale.getDefault());
            String formatMessage = MessageFormat.format(message, request.getEmail());

            return ResponseEntity
                .ok()
                .body(new MessageResponse(formatMessage, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            log.error(null, e);
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());

            return ResponseEntity
                .internalServerError()
                .body(new MessageResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<LoginResponse> loginUser(LoginRequest request){
        try {
            Set<ConstraintViolation<LoginRequest>> constraintViolations = validator.validate(request);
            if (!constraintViolations.isEmpty()){
                ConstraintViolation<LoginRequest> firsViolation = constraintViolations.iterator().next();
                String message = firsViolation.getMessage();
                return ResponseEntity
                    .badRequest()
                    .body(new LoginResponse(message, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null));
            }

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()) 
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtil.generateJwtToken(authentication);

            UserDetail userDetail = (UserDetail) authentication.getPrincipal();

            String message = messageSource.getMessage("login.successful", null, Locale.getDefault());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Token: ", jwt);

            return ResponseEntity
                .ok()
                .body(LoginResponse.builder()
                    .data(new LoginResponse.UserData(userDetail.getUsername(), userDetail.getUserId(), jwt))
                    .message(message)
                    .statusCode((int) HttpStatus.OK.value())
                    .status(HttpStatus.OK.getReasonPhrase())
                    .build() 
                );

        } catch (AuthenticationException e) {

            String message = messageSource.getMessage("login.error", null, Locale.getDefault());

            return ResponseEntity
                    .badRequest()
                    .body(new LoginResponse(message, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null));

        } catch (Exception e) {
            log.error(null, e);
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());

            return ResponseEntity
                .internalServerError()
                .body(new LoginResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null) );
        }
    }    
}

