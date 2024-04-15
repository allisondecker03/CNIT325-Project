/**
 * A class that represents a user that is connected to the server playing games
 */
public class Player {
    private String username;
    
    public Player(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
}
