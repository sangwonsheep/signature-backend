package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

    public List<UserEntity> findAllScoreGraterThan(int score) {
        return this.findAll().stream()
                .filter(
                        i -> {
                            return i.getScore() >= score;
                        }
                ).collect(Collectors.toList());
    }
}
