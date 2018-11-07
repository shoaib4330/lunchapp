package com.venturedive.rotikhilao.service.company;

import com.venturedive.rotikhilao.DTO.CompanyDto;
import com.venturedive.rotikhilao.DTO.CreateCompanyDto;
import com.venturedive.rotikhilao.entitiy.Company;

import java.util.List;

public interface ICompanyService {
    CompanyDto addCompany(CreateCompanyDto createCompanyDto);
    CompanyDto getCompanyById(long id);
    CompanyDto getCompanyByDomainName(String domainName);
    List<CompanyDto> getAllCompanies();
}
