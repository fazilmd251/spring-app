package com.fazil.my_first_app.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>>getCompanies(){
        List<Company> companies=this.companyService.getCompanies();
        if(!companies.isEmpty()){
            return ResponseEntity.ok(companies);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        Company createdCompany=this.companyService.createCompany(company);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        Company company=this.companyService.getCompany(id);
        if(company!=null){
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company,@PathVariable Long id){
        Company updatedCompany=this.companyService.updateCompany(company,id);
        if(updatedCompany==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        if (!this.companyService.deleteCompany(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete: Company not found");
        }
        return ResponseEntity.ok("Company Deleted  Succesfully");
    }

}
