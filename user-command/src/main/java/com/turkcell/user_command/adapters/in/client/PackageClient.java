package com.turkcell.user_command.adapters.in.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface PackageClient
{

    @GetExchange("package/ispackageexist/{id}")
    public ResponseEntity<Boolean> isPackageExist(@PathVariable("id") Long packageId);


}
