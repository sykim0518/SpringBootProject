package com.springbootProject.service.posts;


import com.springbootProject.domain.posts.Posts;
import com.springbootProject.domain.posts.PostsRepository;
import com.springbootProject.web.dto.PostsResponseDto;
import com.springbootProject.web.dto.PostsSaveRequestDto;
import com.springbootProject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Post. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Post. id = "+id));
        return new PostsResponseDto(entity);
    }
}