package estm.dsic.jee.rest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import estm.dsic.jee.rest.business.INotesServices;
import estm.dsic.jee.rest.models.Note;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class NoteDao implements Repository<Note, String> ,INotesServices{

    @Resource(lookup = "jdbc/g-note")
    private DataSource datasource;
    private Connection conn;
    @Override
    public void create(Note note) {
    String sql = "INSERT INTO notes (dateTime, subject, body, userId) VALUES (?, ?, ?, ?)";

    try (Connection conn = this.datasource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setTimestamp(1, Timestamp.valueOf(note.getDateTime()));
        pstmt.setString(2, note.getSubject());
        pstmt.setString(3, note.getBody());
        pstmt.setInt(4, note.getIdUser());

        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating note failed, no rows affected.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions appropriately
    }
}

    @Override
    public Note auth(Note entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'auth'");
    }

    
    @Override
public List<Note> getAllNotesByUserId(int userId) {
    List<Note> noteList = new ArrayList<>();
    String query = "SELECT * FROM notes WHERE userId = ?"; // Adjusted to include a WHERE clause

    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId); // Setting the userId parameter
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Note note = new Note();
            note.setId(rs.getInt("id"));
            note.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
            note.setSubject(rs.getString("subject"));
            note.setBody(rs.getString("body"));
            note.setIdUser(rs.getInt("userId"));
            noteList.add(note);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return noteList;
}

@Override
public void update(Note entity, String index) {
    // This method should update a note with the given index (ID) with new data from the entity
    String query = "UPDATE notes SET subject=?, body=? WHERE id=?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        // Assuming 'subject' and 'body' are the fields you want to update
        pstmt.setString(1, entity.getSubject());
        pstmt.setString(2, entity.getBody());
        pstmt.setInt(3, Integer.parseInt(index)); // Convert String index to int
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

@Override
public void delete(Note entity, String index) {
    // This method should delete a note with the given index (ID)
    String query = "DELETE FROM notes WHERE id=?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, Integer.parseInt(index)); // Convert String index to int
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public List<Note> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    
    
}
