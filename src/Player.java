public class Player {
    private String username;
    private int cryptogramsCompleted;
    private int cryptogramsPlayed;
    private int accuracy;
    private int totalGuesses;

    public Player(String playerName, int solved, int attempted, int correctGuesses, int totalGuesses) {
        this.username = playerName;
        this.cryptogramsCompleted = solved;
        this.cryptogramsPlayed = attempted;
        this.accuracy = correctGuesses;
        this.totalGuesses = totalGuesses;
    }

    /*Placeholder*/
    public void incrementCryptogramsCompleted(){

    }

    /*Placeholder*/
    public void incrementCryptogramsPlayed(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCryptogramsCompleted() {
        return cryptogramsCompleted;
    }

    public void setCryptogramsCompleted(int cryptogramsCompleted) {
        this.cryptogramsCompleted = cryptogramsCompleted;
    }

    public int getCryptogramsPlayed() {
        return cryptogramsPlayed;
    }

    public void setCryptogramsPlayed(int cryptogramsPlayed) {
        this.cryptogramsPlayed = cryptogramsPlayed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }
}
