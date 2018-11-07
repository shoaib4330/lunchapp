package com.venturedive.rotikhilao.service.company;

import com.venturedive.rotikhilao.DTO.CompanyDto;
import com.venturedive.rotikhilao.DTO.CreateCompanyDto;
import com.venturedive.rotikhilao.common.CommonUtils;
import com.venturedive.rotikhilao.entitiy.Company;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.CompanyMapper;
import com.venturedive.rotikhilao.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public CompanyDto addCompany(CreateCompanyDto createCompanyDto) {
        CommonUtils.checkRequiredField(createCompanyDto.getAddress());
        CommonUtils.checkRequiredField(createCompanyDto.getCompanyName());
        CommonUtils.checkRequiredField(createCompanyDto.getEmailDomain());

       if(companyRepository.findByEmailDomainAndCompanyName(createCompanyDto.getEmailDomain(),createCompanyDto.getCompanyName()).isPresent())
       {
           throw new ApplicationException("Company already Exist");
       }

       Company company = Company.builder()
               .companyName(createCompanyDto.getCompanyName())
               .address(createCompanyDto.getAddress())
               .emailDomain(createCompanyDto.getEmailDomain())
               .build();

       company = companyRepository.save(company);

       return companyMapper.mapToDto(company);
    }

    @Override
    public CompanyDto getCompanyById(long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new ApplicationException("Company not found with ID: " + id));

        return companyMapper.mapToDto(company);
    }

    @Override
    public CompanyDto getCompanyByDomainName(String domainName) {
        Company company = companyRepository.findByEmailDomain(domainName)
                .orElseThrow(()-> new ApplicationException("Company not found with domain: " + domainName));

        return companyMapper.mapToDto(company);
    }


    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.mapToListDto(companyRepository.findAll());
    }
}
