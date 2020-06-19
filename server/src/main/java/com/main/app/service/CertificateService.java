package com.main.app.service;

import com.main.app.domain.model.IssuerData;
import com.main.app.domain.model.SubjectData;
import com.main.app.domain.model.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;

@Service
public interface CertificateService {

    X509Certificate generate(String issuerName, String name, String serialNumber, String startDate, String endDate);
    void delete(Long id);
    Page<Certificate> getCerts(Pageable page);
}
