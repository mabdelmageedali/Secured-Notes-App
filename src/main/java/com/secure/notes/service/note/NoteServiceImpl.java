package com.secure.notes.service.note;

import com.secure.notes.model.Note;
import com.secure.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long id, String content, String username) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found!"));

        if (!note.getOwnerUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to update this note!");
        }

        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long id, String username) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found!"));

        if (!note.getOwnerUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to delete this note!");
        }

        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> getNoteForUser(String username) {
        return noteRepository.findByOwnerUsername(username);
    }
}
