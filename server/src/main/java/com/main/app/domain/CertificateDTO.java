package com.main.app.domain;

import com.main.app.domain.model.Certificate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CertificateDTO {

    private Long id;
    private String issuerName;
    private String name;
    private String serialNumber;
    private String startDate;
    private String endDate;
    private String cert;

    public CertificateDTO(Certificate certificate) {
        this.issuerName = certificate.getIssuerName();
        this.id = certificate.getId();
        this.name = certificate.getName();
        this.serialNumber = certificate.getSerialNumber();
        this.startDate = certificate.getStartDate();
        this.endDate = certificate.getEndDate();
        this.cert = certificate.getCert();
    }
}
