import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private HashMap<Player, Cryptogram> playerGameMapping;
    private Player currentPlayer;

    public Game(HashMap<Player, Cryptogram> playerGameMapping, Player currentPlayer) {
        this.playerGameMapping = playerGameMapping;
        this.currentPlayer = currentPlayer;
    }

    public Game() {
    }

    boolean finished = false;
    public void playGame(){
        while(finished != true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Please select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram");
            int selection = scan.nextInt();
            //Throw error if not 1 or 2
            Cryptogram crypt = generateCryptogram(selection);
            for(int i=0; i<crypt.getOriginal().length(); i++) {
                System.out.print(crypt.getEncrypted().charAt(i) + " ");
            }
        }
    }

    /*Placeholder*/
    public String getHint(){
        return "";
    }

    /*Placeholder*/
    public void loadPlayer(){

    }



    /*****/
    /*Placeholder*/
    public Cryptogram generateCryptogram(int selection){
        Cryptogram returnCrypt;
        if (selection==1){
            System.out.println("Letter Cryptogram Selected");
            returnCrypt = new LetterCryptogram("Hello");
        }
        else
            System.out.println("Letter Cryptogram Selected");
            returnCrypt = new NumberCryptogram();
        return returnCrypt;
    }

    /*****/
    /*Placeholder*/
    public void enterLetter(char plainLetter, char inputLetter){

    }

    /*****/
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

    /*****/
    /*Placeholder*/
    public void showSolution(){

    }

    public HashMap getPlayerGameMapping() {
        return playerGameMapping;
    }

    public void setPlayerGameMapping(HashMap<Player, Cryptogram> playerGameMapping) {
        this.playerGameMapping = playerGameMapping;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
