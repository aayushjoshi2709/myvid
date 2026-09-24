package com.github.aayushjoshi2709.commentservice.controller;

import com.github.aayushjoshi2709.commentservice.dto.CreateCommentDto;
import com.github.aayushjoshi2709.commentservice.dto.GetCommentDto;
import com.github.aayushjoshi2709.commentservice.dto.UpdateCommentDto;
import com.github.aayushjoshi2709.commentservice.service.CommentService;
import com.github.aayushjoshi2709.commentservice.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<GetCommentDto> getComment(@PathVariable UUID id){
        return ResponseEntity.ok(this.commentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GetCommentDto>> getAllComments(@RequestParam(required = false) UUID id){
        return ResponseEntity.ok(this.commentService.findAll());
    }

    @PostMapping
    public ResponseEntity<GetCommentDto> create(@RequestBody CreateCommentDto createCommentDto){
        return ResponseEntity.ok(this.commentService.create(createCommentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id){
        this.commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetCommentDto> update(@PathVariable UUID id, @RequestBody UpdateCommentDto updateCommentDto){
        return ResponseEntity.ok(this.commentService.update(id,  updateCommentDto));
    }

}
