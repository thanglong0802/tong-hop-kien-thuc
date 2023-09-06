package com.dagoras.edu.api.repository;

import com.dagoras.edu.api.entity.VoucherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherDetailsRepository extends JpaRepository<VoucherDetails, Long> {
}
