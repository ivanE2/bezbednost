package com.main.app.controller;

import com.main.app.domain.CertificateDTO;
import com.main.app.domain.Entities;
import com.main.app.domain.model.Certificate;
import com.main.app.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.cert.X509Certificate;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private CertificateService certService;

    @Autowired
    public CertificateController(CertificateService certService) {
        this.certService = certService;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        certService.delete(id);
    }

    @PostMapping
    ResponseEntity<String> createCert(@RequestBody CertificateDTO dto) {

        X509Certificate c = certService.generate(dto.getIssuerName(), dto.getName(),
                dto.getSerialNumber(), dto.getStartDate(), dto.getEndDate());

        return new ResponseEntity<>(c.toString(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Entities> getCerts(Pageable page) {
        Entities result = new Entities();

        Page<Certificate> c = certService.getCerts(page);

        result.setEntities(c.getContent());
        result.setTotal(c.getTotalElements());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
