package com.main.app.domain.model;

import com.main.app.domain.CertificateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Certificate extends AbstractEntity {

    private String issuerName;

    private String name;
    private String startDate;

    private String endDate;

    private String cert;

    private String serialNumber;



    public Certificate(String issuerName, String name,
                       String serialNumber, String startDate, String endDate, String cert) {
        this.issuerName = issuerName;
        this.name = name;
        this.serialNumber = serialNumber;
        this.endDate = endDate;
        this.cert = cert;
        this.startDate = startDate;

    }

    public Certificate(CertificateDTO cert) {
        this.issuerName = cert.getIssuerName();
        this.id = cert.getId();
        this.name = cert.getName();
        this.startDate = cert.getStartDate();
        this.endDate = cert.getEndDate();
        this.serialNumber = cert.getSerialNumber();
        this.cert = cert.getCert();
    }
}
