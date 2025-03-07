package com.turkcell.user_query.controller;

import com.turkcell.user_query.dto.ApiResponse;
import com.turkcell.user_query.dto.UserResponseDto;
import com.turkcell.user_query.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserQuery {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/search/{name}/{age}/{status}")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> search(@PathVariable("name") String name, @PathVariable("age") Integer age, @PathVariable("status") Boolean status) {
        return ResponseEntity.ok(userService.search(name,age,status));
    }

}
