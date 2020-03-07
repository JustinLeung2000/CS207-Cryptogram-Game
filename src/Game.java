import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private HashMap<Player, Cryptogram> playerGameMapping;
    private Player currentPlayer;
    private boolean gameFinished;

    public Game(HashMap<Player, Cryptogram> playerGameMapping, Player currentPlayer) {
        this.playerGameMapping = playerGameMapping;
        this.currentPlayer = currentPlayer;
        gameFinished = false;
    }

    public Game() {
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public void playGame() throws IOException {
        gameFinished = false;
        while (gameFinished != true) {

            System.out.print("Please select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\nEnter Choice >");
            String selection = reader.readLine();
            //Throw error if not 1 or 2
            Cryptogram cryptogram = generateCryptogram(selection); //creates the type of cryptogram specified by the player
            String currentAnswer = "";
            for (int i = 0; i < cryptogram.getPhrase().length(); i++) { //fills the current answer with dashes to represent unspecified characters
                currentAnswer = currentAnswer + "-";
            }
            boolean cryptoFinished = false; //controls whether the current cryptogram is finished
            while (cryptoFinished != true) {
                printGameStatus(cryptogram, currentAnswer);
                System.out.println("Type the character you wish to select followed by your answer");
                System.out.print("Character > ");
                Character selectedChar = reader.readLine().toUpperCase().trim().charAt(0);
                if (cryptogram.getEncrypted().contains(selectedChar.toString())) {
                    System.out.print("Change to > ");
                    char changeCharTo = reader.readLine().toUpperCase().trim().charAt(0);
                    currentAnswer = enterLetter(cryptogram, currentAnswer, selectedChar, changeCharTo);
                } else {
                    System.out.println("The encrypted phrase does not contain this character, please enter another character");
                    /*Player attempts++*/
                }
                if(!currentAnswer.contains("-")){
                    printGameStatus(cryptogram, currentAnswer);
                    System.out.println("Would you like to enter your answer? (Y/N)");
                    char response = checkInput(reader.readLine());
                    if(response == 'Y'){
                        //Player attempts++
                        if(cryptogram.getPhrase().equals(currentAnswer)){
                            System.out.println("Congratulations! You have completed the cryptogram!\nWould you like to play again? (Y/N)");
                            response = checkInput(reader.readLine());
                            if (response == 'N') {
                                gameFinished = true;
                                System.out.println("Goodbye!");
                                gameFinished = true;
                            } else {
                                cryptoFinished = true;
                            }
                        }
                        else{
                            System.out.println("Incorrect. Please try again.");
                        }
                    }
                }
            }
        }
    }

    public String enterLetter(Cryptogram cryptogram, String currentAnswer, Character selectedChar, Character changeCharTo) throws IOException {
        int i = cryptogram.getEncrypted().indexOf(selectedChar);
        if('-' != currentAnswer.charAt(i)) {
            System.out.println("You have already entered a letter into this slot, are you sure you want to override it? (Y/N)");
            char response = reader.readLine().trim().toUpperCase().charAt(0);
            if(response == 'N') {
                System.out.println("Ok, character not changed");
            }
            else{
                for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                    if (cryptogram.getEncrypted().charAt(j) == selectedChar) {
                        currentAnswer = currentAnswer.substring(0, j) + changeCharTo + currentAnswer.substring(j + 1);
                    }
                }
                //player attempts ++
            }
        }
        else{
            for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                if (cryptogram.getEncrypted().charAt(j) == selectedChar) {
                    currentAnswer = currentAnswer.substring(0, j) + changeCharTo + currentAnswer.substring(j + 1);
                }
            }
            //player attempts ++
        }
        return currentAnswer;
    }
    /*Placeholder*/
    public String getHint(){
        return "";
    }

    /*Placeholder*/
    public void loadPlayer(){

    }

    public Cryptogram generateCryptogram(String selection){
        if (selection.equals("1")){
            System.out.println("Letter Cryptogram Selected");
            return new LetterCryptogram("ABCCBA".toUpperCase().trim());
        }
        else {
            System.out.println("Number Cryptogram Selected");
            return new NumberCryptogram("123321".trim());
        }

    }

    private char checkInput(String input) throws IOException {
        if(input.equals("exit")){
            setGameFinished(false);
        }
        char formatedInput = input.trim().toUpperCase().charAt(0);
        if(formatedInput != 'Y' || formatedInput != 'N'){
            System.out.println(input + " is not a valid input. Please enter a valid input");
            boolean validInput = false;
            while(!validInput){
                input = reader.readLine();
                System.out.println(input + " is not a valid input. Please enter a valid input");
                formatedInput = input.trim().toUpperCase().charAt(0);
                if(formatedInput == 'Y' || formatedInput == 'N'){
                    validInput = true;
                }
            }
        }
        return formatedInput;
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

    private void printGameStatus(Cryptogram crypt, String currentAnswer){
        for(int i=0; i<crypt.getEncrypted().length(); i++){
            System.out.print(crypt.getEncrypted().charAt(i) + " ");
        }
        System.out.println("");
        for(int i=0; i<crypt.getEncrypted().length()*2; i++){
            System.out.print("-");
        }
        System.out.println("");
        for(int i=0; i<crypt.getPhrase().length(); i++){
            System.out.print(currentAnswer.charAt(i) + " ");
        }
        System.out.println("");
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

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
