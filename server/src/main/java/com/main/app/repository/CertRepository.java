package com.main.app.repository;

import com.main.app.domain.model.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CertRepository extends JpaRepository<Certificate, Long> {

    Page<Certificate> findAllByDeleted(boolean deleted, Pageable pageable);
    Optional<Certificate> findOneById(Long id);

}
