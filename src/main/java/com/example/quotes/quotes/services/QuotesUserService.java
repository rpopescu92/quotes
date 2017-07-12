package com.example.quotes.quotes.services;

import com.example.quotes.quotes.domain.QuotesUser;
import com.example.quotes.quotes.domain.QuotesUserRepository;
import com.example.quotes.quotes.exception.ApplicationException;
import com.example.quotes.quotes.exception.UserAlreadyExistsException;
import com.example.quotes.quotes.jwt.TokenService;
import com.example.quotes.quotes.model.ErrorCode;
import com.example.quotes.quotes.model.QuotesLoginAccess;
import com.example.quotes.quotes.model.QuotesUserLogin;
import com.example.quotes.quotes.model.QuotesUserRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class QuotesUserService {

    private final QuotesUserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public QuotesUserService(QuotesUserRepository userRepository,
                             TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public void register(QuotesUserRegister register) {
        Optional<QuotesUser> user = userRepository.findByUserName(register.getUserName());
        if (user.isPresent()) {
            log.error("Username already exists");
            throw new UserAlreadyExistsException().withErrorCode(ErrorCode.USER_ALREADY_EXISTS);
        } else {
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            QuotesUser newUser = new QuotesUser();
            newUser.setAddress(register.getAddress());
            newUser.setEmail(register.getEmail());
            newUser.setFirstName(register.getFirstName());
            newUser.setLastName(register.getLastName());
            newUser.setPassword(bCrypt.encode(register.getPassword()));
            newUser.setPhoneNumber(register.getPhoneNumber());
            newUser.setUserName(register.getUserName());

            userRepository.save(newUser);
        }
    }

    public QuotesLoginAccess login(QuotesUserLogin login) {
        Optional<QuotesUser> user = userRepository.findByUserName(login.getUserName());
        if (!user.isPresent()) {
            log.error("Username does not exist");
            throw new UserAlreadyExistsException().withErrorCode(ErrorCode.USER_DOES_NOT_EXIST);
        }

        return QuotesLoginAccess.builder().token(tokenService.createToken(login.getUserName())).build();
    }

}
