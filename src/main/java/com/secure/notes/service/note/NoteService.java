package com.secure.notes.service.note;

import com.secure.notes.model.Note;
import java.util.List;

public interface NoteService {
    Note createNoteForUser (String username, String content);
    Note updateNoteForUser (Long id, String content, String username);
    void deleteNoteForUser (Long id, String username);
    List<Note> getNoteForUser (String username);
}
