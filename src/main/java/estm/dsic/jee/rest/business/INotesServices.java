package estm.dsic.jee.rest.business;

import java.util.List;

import estm.dsic.jee.rest.models.Note;

public interface INotesServices {
        List<Note> getAllNotesByUserId(int i);

    
}
