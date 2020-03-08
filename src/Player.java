import java.sql.SQLOutput;

public class Player {
    private String username;
    private int cryptogramsCompleted;
    private int cryptogramsPlayed;
    private int accuracy;
    private int totalGuesses;

    public Player(String username, int cryptogramsCompleted, int cryptogramsPlayed, int accuracy, int totalGuesses) {
        this.username = username;
        this.cryptogramsCompleted = cryptogramsCompleted;
        this.cryptogramsPlayed = cryptogramsPlayed;
        this.accuracy = accuracy;
        this.totalGuesses = totalGuesses;
    }

    /*Placeholder*/
    public void incrementCryptogramsCompleted(){
        cryptogramsCompleted++;
        updateAccuracy();
    }

    /*Placeholder*/
    public void incrementCryptogramsPlayed(){
        cryptogramsPlayed++;
    }

    public void incrementTotalGuesses(){
        totalGuesses++;
        updateAccuracy();
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

    public void printStats(){
        System.out.println(username + " Stats:\n  Cryptograms Played: " + cryptogramsPlayed +
                "\n  Cryptograms Completed: " + cryptogramsCompleted + "\n   Total Guesses: " + totalGuesses +
                "\n   Accuracy: " + accuracy + "%");
    }

    private void updateAccuracy(){
        accuracy = (cryptogramsCompleted*100)/(totalGuesses);
    }
}
