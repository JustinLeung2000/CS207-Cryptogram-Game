import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        System.out.print("Welcome to Friday Team 1's Cryptogram game!\nPlease select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\nEnter Choice >");
        char selection = checkValidInput(reader.readLine(), "1/2").charAt(0);
        Cryptogram cryptogram = generateCryptogram(selection);
        currentPlayer.incrementCryptogramsPlayed();
        String currentAnswer = "";
        for (int i = 0; i < cryptogram.getPhrase().length(); i++) { //fills the current answer with dashes to represent unspecified characters
            if (cryptogram.getPhrase().charAt(i) == ' ') {
                currentAnswer = currentAnswer + " ";
            } else {
                currentAnswer = currentAnswer + "-";
            }
        }
        while (!gameFinished) {
            printGameStatus(cryptogram, currentAnswer);
            System.out.print("Enter one of the following options:\n   Enter\n   Undo\n   Check\n   Stats\n   Exit\n>");
            String input = checkValidInput(reader.readLine(), "General");
            switch (input) {
                case "ENTER":
                    System.out.println("Type the letter you wish to select followed by your answer");
                    System.out.print("Letter > ");
                    String selectedLetter = checkValidInput(reader.readLine(), "General"); //selectedLetter holds which character from the encrypted phrase the player has selected
                    if (cryptogram.getEncrypted().contains(selectedLetter)) {
                        System.out.print("Change to > ");
                        char changeLetterTo = checkValidInput(reader.readLine(), "General").charAt(0); //changeLetterTo holds the character which the player wants to insert into their answer
                        currentAnswer = enterLetter(cryptogram, currentAnswer, selectedLetter, changeLetterTo); //updates the current answer
                    } else {
                        System.out.println("The encrypted phrase does not contain this character, please enter another character");
                    }
                    break;
                case "UNDO":
                    currentAnswer = undoLetter(currentAnswer);
                    break;
                case "CHECK":
                    if(checkAnswer(currentAnswer, cryptogram)) {
                        System.out.println("Congratulations! You have completed the cryptogram!\nWould you like to play again? (Y/N)");
                        Character response = checkValidInput(reader.readLine(), "Y/N").charAt(0);
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
                    }
                    else{
                        System.out.println("Incorrect. Please try again.");
                    }
                    break;
                case "EXIT":
                    gameFinished = true;
                    System.out.println("Goodbye!");
                    break;
                case "STATS":
                    currentPlayer.printStats();
                    break;
                default:
                    System.out.println(input + " is not a valid input. Please enter a valid input");
                    break;
            }
        }
    }

    public String enterLetter(Cryptogram cryptogram, String currentAnswer, String selectedLetter, Character changeLetterTo) throws IOException {
        if(currentAnswer.contains(changeLetterTo.toString())) {
            System.out.println("You have already entered this letter. Please select a different letter");
        }
        else{
            StringTokenizer encryptedTokenizer = new StringTokenizer(cryptogram.getEncrypted());
            int selectedLetterIndex = 0;
            int i=0;
            boolean breakOut = false;
            while(encryptedTokenizer.hasMoreTokens() && breakOut==false) {
                if(encryptedTokenizer.nextToken().equals(selectedLetter)) {
                    selectedLetterIndex = i;
                    breakOut=true;
                }
                else
                    i++;
            }
            if (currentAnswer.charAt(selectedLetterIndex) != '-') { //if the player has already entered a plain letter...
                System.out.println("You have already entered a letter into this slot, are you sure you want to override it? (Y/N)");
                char response = checkValidInput(reader.readLine(), "Y/N").charAt(0); //...the player will be asked if they want to override their previous answer
                if (response == 'N') {
                    System.out.println("Ok, character not changed");
                } else {
                    encryptedTokenizer = new StringTokenizer(cryptogram.getEncrypted());
                    for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                        if(encryptedTokenizer.nextToken().equals(selectedLetter)){
                            currentAnswer = currentAnswer.substring(0, j) + changeLetterTo + currentAnswer.substring(j + 1);
                        }
                    }
                }
            } else {
                encryptedTokenizer = new StringTokenizer(cryptogram.getEncrypted());
                for (int j = 0; j < currentAnswer.length(); j++) { // for all characters of current answer
                    if(encryptedTokenizer.nextToken().equals(selectedLetter)){
                        currentAnswer = currentAnswer.substring(0, j) + changeLetterTo + currentAnswer.substring(j + 1);
                    }
                }
            }
        }
        return currentAnswer;
    }

    public String undoLetter(String currentAnswer) throws IOException {
        System.out.print("Please select the letter you wish to undo\n>");
        Character response = checkValidInput(reader.readLine(), "General").charAt(0);
        if(currentAnswer.contains(response.toString()) && response != '-'){
            for(int i=0; i<currentAnswer.length(); i++){
                if(currentAnswer.charAt(i) == response){
                    currentAnswer = currentAnswer.replace(response, '-');
                }
            }
        }
        else {
            System.out.println("You have not mapped this letter. Please select a letter that you have mapped");
        }
        return currentAnswer;
    }

    public boolean checkAnswer(String currentAnswer, Cryptogram cryptogram) throws IOException {
        if (!currentAnswer.contains("-")) { //iff all empty spaces in the answer have been filled in by the player...
            currentPlayer.incrementTotalGuesses();
            if (cryptogram.getPhrase().equals(currentAnswer)) {
                currentPlayer.incrementCryptogramsCompleted();
                return true;
            }
            else {
                return false;
            }
        } else {
            System.out.println("There are still empty spaces in your answer. PLease enter more letters into your answer");
            return false;
        }
    }


    /*Placeholder*/
    public String getHint() {
        return "";
    }

    /*Placeholder*/
    public void loadPlayer() {

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
        if (selection == '1') {
            System.out.println("Letter Cryptogram Selected");
            String phrase;
            System.out.println("Please enter a phrase");
            phrase = reader.readLine();
            while (phrase.isEmpty()) {
                System.out.println("Phrase is invalid");
                System.out.println("Please enter a phrase");
                phrase = reader.readLine();
            }
            return new LetterCryptogram(phrase.toUpperCase().trim());
        } else {
            System.out.println("Number Cryptogram Selected");
            String phrase;
            System.out.println("Please enter a phrase");
            phrase = checkValidInput(reader.readLine(), "General");
            while (phrase.isEmpty()) {
                System.out.println("Phrase is invalid");
                System.out.println("Please enter a phrase");
                phrase = reader.readLine();
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

        if (input.equals("")) {
            input = "This";
        }
        char formatedInput = input.trim().toUpperCase().charAt(0);
        switch (constraint) {
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
                if (input.equals("")) {
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
        StringTokenizer defaultTokenizer = new StringTokenizer(crypt.getEncrypted());
        while (defaultTokenizer.hasMoreTokens()) {
            System.out.print(defaultTokenizer.nextToken() + " ");
        }
        System.out.println();
        for (int i = 0; i < crypt.getEncrypted().length(); i++) {
            System.out.print("-");
        }
        System.out.println();
        defaultTokenizer = new StringTokenizer(crypt.getEncrypted());
        for (int i = 0; i < crypt.getPhrase().length(); i++) {
            System.out.print(currentAnswer.charAt(i));
            int tokenLength = defaultTokenizer.nextToken().length();
            for(int j=0;j<tokenLength; j++){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}