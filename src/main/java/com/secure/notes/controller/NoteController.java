package com.secure.notes.controller;

import com.secure.notes.dto.ApiResponse;
import com.secure.notes.model.Note;
import com.secure.notes.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;


    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Note>> createNote(
            @RequestBody String content,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: " + username);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Note created successfully",
                        noteService.createNoteForUser(username, content)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Note>>> getUserNotes(
            @AuthenticationPrincipal UserDetails userDetails){

        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: " + username);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Notes found for user",
                        noteService.getNoteForUser(username)));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<ApiResponse<Note>> updateNote(
            @PathVariable Long noteId,
            @RequestBody String content,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        String username = userDetails.getUsername();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Note updated successfully",
                        noteService.updateNoteForUser(noteId, content, username)));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<ApiResponse<Note>> deleteNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal UserDetails userDetails
    ){

        String username = userDetails.getUsername();
        noteService.deleteNoteForUser(noteId, username);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Note updated successfully", null));
    }
}






