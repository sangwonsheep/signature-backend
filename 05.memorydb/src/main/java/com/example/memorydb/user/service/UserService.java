package com.example.memorydb.user.service;

import com.example.memorydb.user.db.UserRepository;
import com.example.memorydb.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findOne(Long userId) {
        return userRepository.findById(userId);
    }

    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    public List<UserEntity> filterScore(int amount) {
        return userRepository.findAllScoreGraterThan(amount);
    }


}
