package com.springredditclone.controller;

import com.springredditclone.dto.SubredditDto;
import com.springredditclone.exceptions.SpringRedditException;
import com.springredditclone.service.SubredditService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id) {
    	SubredditDto subredditDto= null;
    	try {
    
    	 subredditDto = subredditService.getSubreddit(id);
    	  return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditDto);
    	}
    	catch(SpringRedditException ex){
    		
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subredditDto);
                    
    	}
    }
}
