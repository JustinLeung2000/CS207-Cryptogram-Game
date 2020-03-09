import java.util.ArrayList;

public class Players {

    private ArrayList<Player> allPlayers;
    private String playersFile;

    public Players(ArrayList<Player> allPlayers, String playersFile) {
        this.allPlayers = allPlayers;
        this.playersFile = playersFile;
    }

    /*Placeholder*/
    public void addPlayer(Player p){

    }

    /*Placeholder*/
    public void savePlayers(){

    }

    /*Placeholder*/
    public void findPlayer(Player p){

    }

    /*Placeholder*/
    public void getAllPlayersAccuracies(){

    }

    /*Placeholder*/
    public void getAllPlayersCryptogramsPlayed(){

    }

    /*Placeholder*/
    public void getAllPlayersCompletedCryptos(){

    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public String getPlayersFile() {
        return playersFile;
    }

    public void setPlayersFile(String playersFile) {
        this.playersFile = playersFile;
    }
}
