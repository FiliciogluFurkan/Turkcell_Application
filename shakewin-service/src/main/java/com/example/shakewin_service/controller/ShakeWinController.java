package com.example.shakewin_service.controller;

import com.example.shakewin_service.dto.CreateShakeWin;
import com.example.shakewin_service.entity.ShakeWinEntity;
import com.example.shakewin_service.service.ShakeWinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shakewin")
public class ShakeWinController {

    private final ShakeWinService shakeWinService;

    @PostMapping
    public void addShakeWin(@Valid @RequestBody CreateShakeWin createShakeWin){
        shakeWinService.addShakeWin(createShakeWin);
    }

    @GetMapping
    public List<ShakeWinEntity> getAll(){
        return shakeWinService.getAll();
    }

    @GetMapping("/{id}")
    public ShakeWinEntity getById(@PathVariable Long id){
    return shakeWinService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteShakeWin(@PathVariable Long id){
        shakeWinService.deleteShakeWin(id);
    }

    @GetMapping("/isshakewinexist/{id}")
    public ResponseEntity<Boolean> isShakeWinExist(@PathVariable Long id){
        return ResponseEntity.ok(shakeWinService.isShakeWinExist(id));
    }

    @GetMapping("/getshakewinrandomly")
    public ResponseEntity<Long> getShakeWinRandomly(){
        return ResponseEntity.ok(shakeWinService.getShakeWinIdByRandomly());
    }


}
