package com.gigatorb.backendraddit.controller;

import com.gigatorb.backendraddit.dto.SubredditDto;
import com.gigatorb.backendraddit.model.Subreddit;
import com.gigatorb.backendraddit.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins="*")

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {
    private final SubredditService subredditService;

    @GetMapping
    public List<SubredditDto> getAllSubreddits(){
        return subredditService.getAll();
    }

    @GetMapping("/{id}")
    public SubredditDto getSubreddit(@PathVariable Long id){
        return subredditService.getSubreddit(id);
    }

    @PostMapping
    public SubredditDto create(@RequestBody @Valid SubredditDto subredditDto){
        return subredditService.save(subredditDto);
    }
 }
