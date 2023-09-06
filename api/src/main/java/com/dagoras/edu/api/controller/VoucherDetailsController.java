package com.dagoras.edu.api.controller;

import com.dagoras.edu.api.service.impl.VoucherDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/voucher-details")
@RestController
public class VoucherDetailsController {
    private final VoucherDetailsService voucherDetailsService;

    public VoucherDetailsController(VoucherDetailsService voucherDetailsService) {
        this.voucherDetailsService = voucherDetailsService;
    }

    @GetMapping
    public void importExcel() {
        try {
            voucherDetailsService.voucherDetailsImportFileExcel("E:\\BookNew.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
