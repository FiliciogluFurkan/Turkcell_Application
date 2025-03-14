package com.turkcell.user_command.adapters.in.client;

import com.turkcell.user_command.application.dto.PackageExistAndPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ExtraPackageClient {

    @GetExchange("extrapackage/isextrapackageexist/{extrapackageid}")
    public ResponseEntity<PackageExistAndPrice> isExtraPackageExist(@PathVariable("extrapackageid") Long id);
}
