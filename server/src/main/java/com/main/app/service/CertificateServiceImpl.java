package com.main.app.service;

import com.main.app.domain.model.IssuerData;
import com.main.app.domain.model.SubjectData;
import com.main.app.domain.model.Certificate;
import com.main.app.repository.CertRepository;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private CertRepository certRepository;

    @Autowired
    public CertificateServiceImpl(CertRepository certRepository) {
        Security.addProvider(new BouncyCastleProvider());
        this.certRepository = certRepository;
    }

    public CertificateServiceImpl() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public void delete(Long id) {

        Optional<Certificate> cert = certRepository.findOneById(id);

        if(!cert.isPresent()) {
            return;
        }

        Certificate c = cert.get();
        c.setDeleted(true);

        certRepository.save(c);
    }

    public Page<Certificate> getCerts(Pageable page) {
        return certRepository.findAllByDeleted(false, page);
    }

    public X509Certificate generate(String issuerName, String name, String serialNumber, String startDate, String endDate) {

        KeyPair keyPairIssuer = generateKeyPair();
        IssuerData issuerData = getIssuerData(keyPairIssuer.getPrivate(), issuerName);
        SubjectData subjectData = getSubjectData(name, serialNumber, startDate, endDate);

        X509Certificate cert = null;

        try {

            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
            builder = builder.setProvider("BC");

            ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500name(),
                    subjectData.getPublicKey());

            X509CertificateHolder certHolder = certGen.build(contentSigner);

            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            cert = certConverter.getCertificate(certHolder);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        if(cert == null) {
            return  null;
        }

        certRepository.save(new Certificate(issuerName, name, serialNumber, startDate, endDate, cert.toString()));

        return cert;
    }


    private SubjectData getSubjectData(String name, String serial,
                                       String startDate, String endDate) {
        try {
            KeyPair keyPairSubject = generateKeyPair();

            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date eDate = iso8601Formater.parse(endDate);
            Date sDate = iso8601Formater.parse(startDate);
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, name);

            return new SubjectData(keyPairSubject.getPublic(), builder.build(), serial, sDate, eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private IssuerData getIssuerData(PrivateKey key, String name) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

        builder.addRDN(BCStyle.CN, name);

        return new IssuerData(key, builder.build());
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

            keyGen.initialize(2048, random);

            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
