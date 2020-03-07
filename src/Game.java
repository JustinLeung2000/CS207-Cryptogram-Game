import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        while (gameFinished != true) { //controlls whether the game is being played, multiple cryptograms can be played in a single game
            System.out.print("Please select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\nEnter Choice >");
            char selection = checkValidInput(reader.readLine(), "1/2");
            // add method to return error message if not 1 or 2
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
                Character selectedChar = reader.readLine().toUpperCase().trim().charAt(0); //selectedChar holds which character from the encrypted phrase the player has selected
                if (cryptogram.getEncrypted().contains(selectedChar.toString())) {
                    System.out.print("Change to > ");
                    char changeCharTo = reader.readLine().toUpperCase().trim().charAt(0); //changeCharTo holds the character which the player wants to insert into their answer
                    currentAnswer = enterLetter(cryptogram, currentAnswer, selectedChar, changeCharTo); //updates the current answer
                } else {
                    System.out.println("The encrypted phrase does not contain this character, please enter another character");
                    /*Player attempts++*/
                }
                if (!currentAnswer.contains("-")) { //iff all empty spaces in the answer have been filled in by the player...
                    printGameStatus(cryptogram, currentAnswer);
                    System.out.println("Would you like to enter your answer? (Y/N)");
                    char response = checkValidInput(reader.readLine(), "Y/N"); //...the player will be prompted to say whether they would like to enter their answer
                    if (response == 'Y') {
                        //Player attempts++
                        if (cryptogram.getPhrase().equals(currentAnswer)) {
                            //Player cryptogramsCompleted++
                            System.out.println("Congratulations! You have completed the cryptogram!\nWould you like to play again? (Y/N)");
                            response = checkValidInput(reader.readLine(), "Y/N");
                            if (response == 'N') { //if the player does not want to play again the game will end
                                System.out.println("Goodbye!");
                                gameFinished = true;
                                cryptoFinished = true;
                            } else { //if the layer does want to play again the current cryptogram will end and a new cryptogram will begin
                                cryptoFinished = true;
                            }
                        } else {
                            //Player incorrect attempts++
                            System.out.println("Incorrect. Please try again.");
                        }
                    }
                }
            }
        }
    }

    public String enterLetter(Cryptogram cryptogram, String currentAnswer, Character selectedChar, Character changeCharTo) throws IOException {
        int i = cryptogram.getEncrypted().indexOf(selectedChar);
        if ('-' != currentAnswer.charAt(i)) { //if the player has already entered a plain letter...
            System.out.println("You have already entered a letter into this slot, are you sure you want to override it? (Y/N)");
            char response = checkValidInput(reader.readLine(), "Y/N"); //...the player will be asked if they want to override their previous answer
            if (response == 'N') {
                System.out.println("Ok, character not changed");
            } else {
                for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                    if (cryptogram.getEncrypted().charAt(j) == selectedChar) {
                        currentAnswer = currentAnswer.substring(0, j) + changeCharTo + currentAnswer.substring(j + 1);
                    }
                }
                //player attempts ++
            }
        } else {
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
    public String getHint() {
        return "";
    }

    /*Placeholder*/
    public void loadPlayer() {

    }
    /*****/
    /*Placeholder*/
    public void undoLetter() {

    }

    /*Placeholder*/
    public void viewFrequencies() {

    }

    /*Placeholder*/
    public void saveGame() {

    }

    /*Placeholder*/
    public void loadGame() {

    }

    /*****/
    /*Placeholder*/
    public void showSolution() {

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

    /*Creates an appropriate cryptogram given the user input
     * letter cryptogram if 1
     * number cryptogram if 2*/
    private Cryptogram generateCryptogram(char selection) {
        if (selection=='1') {
            System.out.println("Letter Cryptogram Selected");
            return new LetterCryptogram("ABC CBA".toUpperCase().trim());
        } else {
            System.out.println("Number Cryptogram Selected");
            return new NumberCryptogram("123321".trim());
        }
    }
    /*Checks whether an input is valid based on the expected input*/
    private char checkValidInput(String input, String constraint) throws IOException {
        input = input.trim();
        if (input.equals("exit")) {
            setGameFinished(false);
        }

        if(input.equals("")){
            input = "This";
        }
        char formatedInput = input.trim().toUpperCase().charAt(0);
        switch (constraint){
            case "Y/N":
                if (formatedInput != 'Y' && formatedInput != 'N') {
                    System.out.print(input + " is not a valid input. Please enter a valid input (Y/N)\nEnter Choice > ");
                    input = reader.readLine();
                    checkValidInput(input, constraint);
                }
                break;
            case "1/2":
                if (formatedInput != '1' && formatedInput != '2') {
                    System.out.print(input + " is not a valid input. Please enter a valid input (1 or 2)\nEnter Choice > ");
                    input = reader.readLine();
                    checkValidInput(input, constraint);
                }
                break;

            default:
                System.out.println("Error: invalid constraint passed into Game checkValidInput()");
        }
        return formatedInput;
    }

//    /*Checks whether an input is valid based on the expected input*/
//    private char checkValidInput(String input, String constraint) throws IOException {
//        input = input.trim();
//        if (input.equals("exit")) {
//            setGameFinished(false);
//        }
//
//        if(input.equals("")){
//            input = "This";
//        }
//        char formatedInput = input.trim().toUpperCase().charAt(0);
//        switch (constraint){
//            case "Y/N":
//                if (formatedInput != 'Y' && formatedInput != 'N') {
//                    System.out.println(input + " is not a valid input. Please enter a valid input");
//                    boolean validInput = false;
//                    while (!validInput) {
//                        input = reader.readLine();
//                        System.out.println(input + " is not a valid input. Please enter a valid input");
//                        formatedInput = input.trim().toUpperCase().charAt(0);
//                        if (formatedInput == 'Y' || formatedInput == 'N') {
//                            validInput = true;
//                        }
//                    }
//                }
//                break;
//            case "1/2":
//                if (formatedInput != '1' && formatedInput != '2') {
//                    System.out.println(input + " is not a valid input. Please enter a valid input (1 or 2)");
//                    boolean validInput = false;
//                    while (!validInput) {
//                        input = reader.readLine();
//                        System.out.println(input + " is not a valid input. Please enter a valid input (1 or 2)");
//                        formatedInput = input.trim().toUpperCase().charAt(0);
//                        if (formatedInput == '1' || formatedInput == '2') {
//                            validInput = true;
//                        }
//                    }
//                }
//                break;
//
//            default:
//                System.out.println("Error: invalid constraint passed into Game checkValidInput()");
//        }
//        return formatedInput;
//    }

    private void printGameStatus(Cryptogram crypt, String currentAnswer) {
        for (int i = 0; i < crypt.getEncrypted().length(); i++) {
            System.out.print(crypt.getEncrypted().charAt(i) + " ");
        }
        System.out.println("");
        for (int i = 0; i < crypt.getEncrypted().length() * 2; i++) {
            System.out.print("-");
        }
        System.out.println("");
        for (int i = 0; i < crypt.getPhrase().length(); i++) {
            System.out.print(currentAnswer.charAt(i) + " ");
        }
        System.out.println("");
    }
}
