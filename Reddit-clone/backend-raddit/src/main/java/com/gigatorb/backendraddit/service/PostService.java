package com.gigatorb.backendraddit.service;

import com.gigatorb.backendraddit.dto.PostRequest;
import com.gigatorb.backendraddit.dto.PostResponse;
import com.gigatorb.backendraddit.exception.PostNotFoundException;
import com.gigatorb.backendraddit.exception.SubredditNotFoundException;
import com.gigatorb.backendraddit.mapper.PostMapper;
import com.gigatorb.backendraddit.model.Post;
import com.gigatorb.backendraddit.model.Subreddit;
import com.gigatorb.backendraddit.model.User;
import com.gigatorb.backendraddit.repository.PostRepository;
import com.gigatorb.backendraddit.repository.SubredditRepository;
import com.gigatorb.backendraddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
    public void save(PostRequest postRequest){
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(()->new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest,subreddit,authService.getCurrentUser()));
    }


    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void deletePostById(Long postId){
        Post post = postRepository.findByPostId(postId);
        if(postId == null)
        {
            throw new PostNotFoundException("Post not found");
        }
        postRepository.delete(post);
    }
}
