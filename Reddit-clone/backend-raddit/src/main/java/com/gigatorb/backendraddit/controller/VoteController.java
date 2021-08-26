package com.gigatorb.backendraddit.controller;

import com.gigatorb.backendraddit.dto.VoteDto;
import com.gigatorb.backendraddit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {


    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto){
        voteService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
