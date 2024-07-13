package com.example.simple_board.reply.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.db.ReplyRepository;
import com.example.simple_board.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create(ReplyRequest replyRequest) {

        PostEntity postEntity = postRepository.findById(replyRequest.getPostId()).orElseThrow(() -> {
            throw new RuntimeException("게시물이 존재하지 않습니다." + replyRequest.getPostId());
        });

        ReplyEntity entity = ReplyEntity.builder()
                .post(postEntity)
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
    }
}
