package com.venturedive.rotikhilao.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.venturedive.rotikhilao.configuration.JwtTokenProvider;
import com.venturedive.rotikhilao.entitiy.Company;
import com.venturedive.rotikhilao.entitiy.Customer;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.DTO.UserTokenResponseDto;
import com.venturedive.rotikhilao.repository.CompanyRepository;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class GoogleService implements IGoogleService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    @Transactional
    public UserTokenResponseDto saveNewUser(GoogleIdToken.Payload payload) {
        Company company = companyRepository.findByEmailDomain(payload.getHostedDomain())
                .orElseThrow(()-> new ApplicationException("Company not found with given domain"));

        Customer customer = Customer.builder().company(company).customerName((String) payload.get("name"))
                .email(payload.getEmail()).imageUrl((String) payload.get("picture"))
                .build();
        customer = customerRepository.save(customer);

        return UserTokenResponseDto.builder().isAuthorized(true)
                .userId(customer.getCustomerId())
                .jwtToken(tokenProvider.generateToken(customer.getCustomerId()))
                .name(customer.getCustomerName())
                .build();

    }

    @Override
    public Boolean checkUserExistence(GoogleIdToken.Payload payload) {

        if (customerRepository.findByEmail(payload.getEmail()).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public UserTokenResponseDto checkUserExistence(Map<String, Object> map) throws Exception {

        if (!companyRepository.findByEmailDomain((String) map.get("hd")).isPresent()){
            new ApplicationException("Company not found with given domain");
        }

        Optional<Customer> customer = customerRepository.findByEmail((String) map.get("email"));
        if (customer.isPresent()){
            return UserTokenResponseDto.builder().isAuthorized(true)
                    .userId(customer.get().getCustomerId())
                    .jwtToken(tokenProvider.generateToken(customer.get().getCustomerId()))
                    .name(customer.get().getCustomerName())
                    .build();
        }
        Customer newCustomer = Customer.builder().customerName((String) map.get("name"))
                .imageUrl((String) map.get("picture"))
                .email((String) map.get("email"))
                .build();
        newCustomer = customerRepository.save(newCustomer);
        return UserTokenResponseDto.builder().isAuthorized(true)
                .userId(newCustomer.getCustomerId())
                .jwtToken(tokenProvider.generateToken(newCustomer.getCustomerId()))
                .name(newCustomer.getCustomerName())
                .build();

    }
}
