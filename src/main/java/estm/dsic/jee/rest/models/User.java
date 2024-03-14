package estm.dsic.jee.rest.models;

public class User {
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + "]";
    }

    private int id;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isActive;

    
    

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
