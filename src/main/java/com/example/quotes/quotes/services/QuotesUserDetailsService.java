package com.example.quotes.quotes.services;

import com.example.quotes.quotes.domain.QuotesUser;
import com.example.quotes.quotes.domain.QuotesUserRepository;
import com.example.quotes.quotes.exception.ApplicationException;
import com.example.quotes.quotes.model.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class QuotesUserDetailsService implements UserDetailsService {

    @Autowired
    private QuotesUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<QuotesUser> user = userRepository.findByUserName(username);

        if (!user.isPresent()) {
            throw new ApplicationException().withErrorCode(ErrorCode.USER_DOES_NOT_EXIST);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new User(user.get().getUserName(), user.get().getPassword(), authorities);
    }
}
