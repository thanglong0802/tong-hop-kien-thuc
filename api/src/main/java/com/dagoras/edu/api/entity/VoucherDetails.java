package com.dagoras.edu.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "tbl_voucher_details")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherDetails extends BaseEntity {
    private String code;
    private String voucherType;
    private LocalDate expiredDate;
    private Boolean status;
}
