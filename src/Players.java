import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Players {

    public static ArrayList<Player> allPlayers;
    private String playersFile;

    public static void loadPlayers(String fileName){
        String username;
        int cryptogramsPlayed;
        int cryptogramsCompleted;
        int totalGuesses;
        int accuracy;
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bReader.readLine()) != null) {
                Scanner s = new Scanner(line);
                username = s.next();
                cryptogramsPlayed = s.nextInt();
                cryptogramsCompleted = s.nextInt();
                totalGuesses = s.nextInt();
                accuracy = s.nextInt();

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
        try (FileWriter fWriter = new FileWriter("src/PlayerData.txt", true);
             BufferedWriter bWriter = new BufferedWriter(fWriter)) {
            bWriter.write(p.toString());
            bWriter.newLine();
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
                bWriter.write(p.toString());
                bWriter.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Unable to save to player data file");
        }

    }

    public static Player findPlayer(String currentUser){
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
        this.allPlayers = allPlayers;
    }

    public String getPlayersFile() {
        return playersFile;
    }

    public void setPlayersFile(String playersFile) {
        this.playersFile = playersFile;
    }
}
