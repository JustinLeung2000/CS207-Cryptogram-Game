import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Game {
    //    private HashMap<Player, Cryptogram> playerGameMapping;
    private Player currentPlayer;
    private boolean gameFinished;

    public Game(Player currentPlayer) {
        //      this.playerGameMapping = playerGameMapping;
        this.currentPlayer = currentPlayer;
        gameFinished = false;
    }

    public Game() {
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void playGame() throws IOException {
        System.out.print("Welcome to Friday Team 1's Cryptogram game!\nNote that this cryptogram game is case sensitive\nPlease select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\nEnter Choice >");
        char selection = checkValidInput(reader.readLine(), "1/2").charAt(0);
        Cryptogram cryptogram = generateCryptogram(selection);
        currentPlayer.incrementCryptogramsPlayed();
        Stack<String> stack = new Stack();
        String currentAnswer = "";
        for (int i = 0; i < cryptogram.getPhrase().length(); i++) { //fills the current answer with dashes to represent unspecified characters
            currentAnswer = currentAnswer + "-";
        }
        stack.push(currentAnswer);
        while(gameFinished == false){
            printGameStatus(cryptogram, currentAnswer);
            System.out.print("Enter one of the following options:\n   Enter\n   Undo\n   Check\n   Stats\n   Exit\n>");
            String input = checkValidInput(reader.readLine(), "General");
            switch(input){
                case "enter":
                    System.out.println("Type the character you wish to select followed by your answer");
                    System.out.print("Character > ");
                    Character selectedChar = checkValidInput(reader.readLine(), "General").charAt(0); //selectedChar holds which character from the encrypted phrase the player has selected
                    if (cryptogram.getEncrypted().contains(selectedChar.toString())) {
                        System.out.print("Change to > ");
                        char changeCharTo = checkValidInput(reader.readLine(), "General").charAt(0); //changeCharTo holds the character which the player wants to insert into their answer
                        currentAnswer = enterLetter(cryptogram, currentAnswer, selectedChar, changeCharTo); //updates the current answer
                        stack.push(currentAnswer);
                    }
                    else {
                        System.out.println("The encrypted phrase does not contain this character, please enter another character");
                    }
                    break;
                case "undo":
                    if(!stack.empty()) {
                        stack.pop();
                        currentAnswer = stack.peek();
                    }
                    break;
                case "check":
                    if (!currentAnswer.contains("-")) { //iff all empty spaces in the answer have been filled in by the player...
                        currentPlayer.incrementTotalGuesses();
                        if (cryptogram.getPhrase().equals(currentAnswer)) {
                            currentPlayer.incrementCryptogramsCompleted();
                            System.out.println("Congratulations! You have completed the cryptogram!\nWould you like to play again? (Y/N)");
                            char response = checkValidInput(reader.readLine(), "Y/N").charAt(0);
                            if (response == 'N') { //if the player does not want to play again the game will end
                                System.out.println("Goodbye!");
                                gameFinished = true;
                            } else { //if the layer does want to play again the current cryptogram will end and a new cryptogram will begin
                                System.out.print("Please select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\nEnter Choice >");
                                selection = checkValidInput(reader.readLine(), "1/2").charAt(0);
                                cryptogram = generateCryptogram(selection);
                                currentPlayer.incrementCryptogramsPlayed();
                                currentAnswer = "";
                                for (int i = 0; i < cryptogram.getPhrase().length(); i++) { //fills the current answer with dashes to represent unspecified characters
                                    currentAnswer = currentAnswer + "-";
                                }
                            }
                        } else {
                            System.out.println("Incorrect. Please try again.");
                        }
                    }
                    else{
                        System.out.println("There are still empty spaces in your answer. PLease enter more letters into your answer");
                    }
                    break;
                case "exit":
                    gameFinished = true;
                    System.out.println("Goodbye!");
                    break;
                case "stats":
                    currentPlayer.printStats();
                    break;
                default:
                    System.out.println(input + " is not a valid input. Please enter a valid input");
                    break;
            }
        }
    }

    public String enterLetter(Cryptogram cryptogram, String currentAnswer, Character selectedChar, Character changeCharTo) throws IOException {
        int i = cryptogram.getEncrypted().indexOf(selectedChar);
        if ('-' != currentAnswer.charAt(i)) { //if the player has already entered a plain letter...
            System.out.println("You have already entered a letter into this slot, are you sure you want to override it? (Y/N)");
            char response = checkValidInput(reader.readLine(), "Y/N").charAt(0); //...the player will be asked if they want to override their previous answer
            if (response == 'N') {
                System.out.println("Ok, character not changed");
            } else {
                for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                    if (cryptogram.getEncrypted().charAt(j) == selectedChar) {
                        currentAnswer = currentAnswer.substring(0, j) + changeCharTo + currentAnswer.substring(j + 1);
                    }
                }
            }
        } else {
            for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                if (cryptogram.getEncrypted().charAt(j) == selectedChar) {
                    currentAnswer = currentAnswer.substring(0, j) + changeCharTo + currentAnswer.substring(j + 1);
                }
            }
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

//    public HashMap getPlayerGameMapping() {
//        return playerGameMapping;
//    }
//
//    public void setPlayerGameMapping(HashMap<Player, Cryptogram> playerGameMapping) {
//        this.playerGameMapping = playerGameMapping;
//    }

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
    private Cryptogram generateCryptogram(char selection) throws IOException {
        if (selection=='1') {
            System.out.println("Letter Cryptogram Selected");
            String phrase = null;
            System.out.println("Please enter a phrase");
            phrase = reader.readLine();
            while (phrase.isEmpty()){
                System.out.println("Phrase is invalid");
                System.out.println("Please enter a phrase");
                phrase = reader.readLine();
            }

            return new LetterCryptogram(phrase.toUpperCase().trim());
        } else {
            System.out.println("Number Cryptogram Selected");
            String phrase = null;
            Boolean numeric = true;
            System.out.println("Please enter a phrase");
            phrase = reader.readLine();
            try {
                Double num = Double.parseDouble(phrase);
            } catch (NumberFormatException e) {
                numeric = false;
            }
            while (phrase.isEmpty() || !numeric){
                System.out.println("Phrase is invalid");
                System.out.println("Please enter a phrase");
                phrase = reader.readLine();
                try {
                    Double num = Double.parseDouble(phrase);
                } catch (NumberFormatException e) {
                    numeric = false;
                }
            }
            return new NumberCryptogram(phrase.trim());
        }
    }
    /*Checks whether an input is valid based on the expected input*/
    private String checkValidInput(String input, String constraint) throws IOException {
        input = input.trim().toUpperCase();
        if (input.toLowerCase().equals("exit")) {
            setGameFinished(true);
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
                    input = checkValidInput(input, constraint);
                }
                break;
            case "1/2":
                if (formatedInput != '1' && formatedInput != '2') {
                    System.out.print(input + " is not a valid input. Please enter a valid input (1 or 2)\nEnter Choice > ");
                    input = reader.readLine();
                    input = checkValidInput(input, constraint);
                }
                break;
            case "General":
                if(input.equals("")){
                    System.out.println("Please enter a valid input");
                    input = reader.readLine();
                    input = checkValidInput(input, constraint);
                }
                break;
            default:
                System.out.println("Error: invalid constraint passed into Game checkValidInput()");
        }
        return input;
    }

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
