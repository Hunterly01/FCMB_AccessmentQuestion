package org.example.services;


import org.example.data.models.AuditLog;
import org.example.data.models.User;
import org.example.data.repositories.AuditRepository;
import org.example.data.repositories.UserRepository;
import org.example.dtos.requests.LoginRequest;
import org.example.dtos.requests.RegisterRequest;
import org.example.dtos.responses.LoginResponse;
import org.example.dtos.responses.RegisterResponse;
import org.example.exceptions.EmailAlreadyExistException;
import org.example.exceptions.EmailDoesnotExistException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  AuditRepository auditRepository;


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new EmailAlreadyExistException("Email already exist");

        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt(12)));
        user.setUserType(registerRequest.getUserType());
        user.setCreatedAt(LocalDate.now().minusYears(registerRequest.getYearsAsCustomer()).atStartOfDay());
        User savedUser = userRepository.save(user);

        AuditLog log = new AuditLog();
        log.setEmail(savedUser.getEmail());
        log.setAction("USER_REGISTRATION");
        log.setTimeStamp(LocalDateTime.now());
        auditRepository.save(log);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setMessage("Registered successfully");
        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new EmailDoesnotExistException("invalid Email and password"));
        if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            throw new EmailDoesnotExistException("invalid Email and password");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Logged in successfully");
        return loginResponse;
    }
}
