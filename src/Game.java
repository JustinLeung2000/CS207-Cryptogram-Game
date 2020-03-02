import java.util.Map;

public class Game {
    private Map playerGameMapping;
    private Player currentPlayer;

    public Game(Map playerGameMapping, Player currentPlayer) {
        this.playerGameMapping = playerGameMapping;
        this.currentPlayer = currentPlayer;
    }

    /*Placeholder*/
    public String getHint(){
        return "";
    }

    /*Placeholder*/
    public Player loadPlayer(){
        Player p = new Player("",0,0,0,0);
        return p;
    }

    /*Placeholder*/
    public void playGame(){

    }

    /*Placeholder*/
    public void generateCryptogram(){

    }

    /*Placeholder*/
    public void enterLetter(){

    }

    /*Placeholder*/
    public void undoLetter(){

    }

    /*Placeholder*/
    public void viewFrequencies(){

    }

    /*Placeholder*/
    public void saveGame(){

    }

    /*Placeholder*/
    public void loadGame(){

    }

    /*Placeholder*/
    public void showSolution(){

    }

    public Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Map getPlayerGameMapping() {
        return playerGameMapping;
    }

    public void setPlayerGameMapping(Map playerGameMapping) {
        this.playerGameMapping = playerGameMapping;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
