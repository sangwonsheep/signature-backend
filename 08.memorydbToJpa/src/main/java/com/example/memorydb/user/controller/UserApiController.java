package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping
    public UserEntity create(@RequestBody UserEntity entity) {
        UserEntity save = userService.save(entity);
        log.info("entity : {}", save);
        return save;
    }

    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @GetMapping("/id/{userId}")
    public UserEntity findOne(@PathVariable Long userId) {
        return userService.findOne(userId).get();
    }

    @GetMapping("/score")
    public List<UserEntity> filterScore(@RequestParam int score) {
        return userService.filterScore(score);
    }

    @GetMapping("/min-max")
    public List<UserEntity> filterScore(@RequestParam int min,
                                        @RequestParam int max) {
        return userService.filterScore(min, max);
    }


    @DeleteMapping("/id/{userId}")
    public void delete(@PathVariable UserEntity userId) {
        userService.delete(userId);
    }
}
