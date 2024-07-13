package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostEntity create(PostRequest postRequest) {
        BoardEntity boardEntity = boardRepository.findById(postRequest.getBoardId()).get();

        PostEntity entity = PostEntity.builder()
                .board(boardEntity) // 임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .status("REGISTERED")
                .email(postRequest.getEmail())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }

    /**
     * 1. 게시글이 있는가?
     * 2. 비밀번호가 맞는가?
     */

    public PostEntity view(PostViewRequest postViewRequest) {
        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(i -> {
                    // entity 존재
                    if(!i.getPassword().equals(postViewRequest.getPassword())){
                        String format = "패스워드 맞지 않습니다. %s vs %s";
                        throw new RuntimeException(String.format(format, i.getPassword(), postViewRequest.getPassword()));
                    }

                    return i;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다." + postViewRequest.getPostId());
                        }
                );
    }

    public Api<List<PostEntity>> all(Pageable pageable) {
        Page<PostEntity> list = postRepository.findAll(pageable);

        Pagination pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalPage(list.getTotalPages())
                .totalElements(list.getTotalElements())
                .build();

        Api<List<PostEntity>> response = Api.<List<PostEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();

        return response;
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(i -> {
                    // entity 존재
                    if(!i.getPassword().equals(postViewRequest.getPassword())){
                        String format = "패스워드 맞지 않습니다. %s vs %s";
                        throw new RuntimeException(String.format(format, i.getPassword(), postViewRequest.getPassword()));
                    }

                    i.setStatus("UNREGISTERED");
                    postRepository.save(i);
                    return i;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다." + postViewRequest.getPostId());
                        }
                );
    }
}
