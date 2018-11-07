package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.CompanyDto;
import com.venturedive.rotikhilao.DTO.CreateCompanyDto;
import com.venturedive.rotikhilao.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public List<CompanyDto> getAllCompanies(){
        return companyService.getAllCompanies();
    }


    @GetMapping("/{companyId}")
    public CompanyDto getCompanyById(@PathVariable Long companyId){
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/get-by-domain/{domain}")
    public CompanyDto getCompanyByDomain(@PathVariable String domain){
        return companyService.getCompanyByDomainName(domain);
    }

    @PostMapping("/add-company")
    public CompanyDto addCompany(@RequestBody CreateCompanyDto createCompanyDto){
        return companyService.addCompany(createCompanyDto);
    }


}
