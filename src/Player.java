public class Player {
    private String playerName;
    private int solved;
    private int attempted;
    private int correctGuesses;
    private int totalGuesses;

    public Player(String playerName, int solved, int attempted, int correctGuesses, int totalGuesses) {
        this.playerName = playerName;
        this.solved = solved;
        this.attempted = attempted;
        this.correctGuesses = correctGuesses;
        this.totalGuesses = totalGuesses;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }
}
