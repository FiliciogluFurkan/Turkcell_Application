package com.turkcell.user_command.adapters.in.client;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface ShakeWinClient {

    @GetExchange("/shakewin/isshakewinexist/{id}")
    public ResponseEntity<Boolean> isShakeWinExist(@PathVariable Long id);

    @GetExchange("/shakewin/getshakewinrandomly")
    public ResponseEntity<Long> getShakeWinRandomly();


}
