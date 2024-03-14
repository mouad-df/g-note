package estm.dsic.jee.rest.models;

import java.time.LocalDateTime;

public class Note {
    private int id;
    private LocalDateTime  dateTime;
    private String subject;
    private String body;
    private int idUser;
    
    // public Note(int id, LocalDateTime dateTime, String subject, String body, int idUser) {
    //     this.id = id;
    //     this.dateTime = dateTime;
    //     this.subject = subject;
    //     this.body = body;
    //     this.idUser = idUser;
    // }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    


    
}
