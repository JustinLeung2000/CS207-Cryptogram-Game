import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Players {

    public static ArrayList<Player> allPlayers;
    private String playersFile;

    public Players(String playersFile) {
        allPlayers = new ArrayList<>();
        this.playersFile = playersFile;
        loadPlayers();
    }

    public void loadPlayers(){
        String username;
        int cryptogramsPlayed;
        int cryptogramsCompleted;
        int totalGuesses;
        int accuracy;
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(playersFile));
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] attributes = line.split(",");
                username = attributes[0];
                cryptogramsPlayed = Integer.parseInt(attributes[1]);
                cryptogramsCompleted = Integer.parseInt(attributes[2]);
                totalGuesses = Integer.parseInt(attributes[3]);
                accuracy = Integer.parseInt(attributes[4]);

                Player player = new Player(username, cryptogramsPlayed, cryptogramsCompleted, totalGuesses, accuracy);
                allPlayers.add(player);
            }
            bReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Player Data file not found");
        }
        catch (IOException e) {
            System.out.println("Unable to read player data file");
        }

    }

    public void addPlayer(Player p){
        try{
            allPlayers.add(p);
            File csvName = new File(playersFile);
            if (!csvName.exists()) {
                csvName.createNewFile();
            }
            FileWriter csv = new FileWriter(csvName, true);

            csv.append(p.getUsername()).append(",").append(String.valueOf(p.getCryptogramsPlayed())).append(",").append(String.valueOf(p.getCryptogramsCompleted())).append(",").append(String.valueOf(p.getTotalGuesses())).append(",").append(String.valueOf(p.getAccuracy())).append("\n");
            csv.flush();
            csv.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Player Data file not found");
        }
        catch (IOException e) {
            System.out.println("Unable to write to player data file");
        }
    }

    public void savePlayers(String fileName){
        try (FileWriter fWriter = new FileWriter(fileName);
            BufferedWriter bWriter = new BufferedWriter(fWriter)) {
            for (Player p : allPlayers) {
                bWriter.append(p.getUsername()).append(",").append(String.valueOf(p.getCryptogramsPlayed())).append(",").append(String.valueOf(p.getCryptogramsCompleted())).append(",").append(String.valueOf(p.getTotalGuesses())).append(",").append(String.valueOf(p.getAccuracy())).append("\n");
            }
            bWriter.flush();
        }
        catch (IOException e) {
            System.out.println("Unable to save to player data file");
        }

    }

    public Player findPlayer(String currentUser){
        for (Player p : allPlayers) {
            if(p.getUsername().equals(currentUser)) {
                System.out.println("Your player data has been found");
                return p;
            }
        }
        return null;
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
        Players.allPlayers = allPlayers;
    }

    public String getPlayersFile() {
        return playersFile;
    }

    public void setPlayersFile(String playersFile) {
        this.playersFile = playersFile;
    }

}
