import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;
import java.io.*;

public class Game {
    private String playersFile;
    private String cryptogramsFile;
    private String phraseFile;
    //    private HashMap<Player, Cryptogram> playerGameMapping;
    private Player currentPlayer;
    private boolean gameFinished;
    private String currentAnswer;
    private Players players;

    public Game(Player currentPlayer, String playersFile, String cryptogramsFile, String phraseFile) {
        //      this.playerGameMapping = playerGameMapping;
        this.playersFile = playersFile;
        this.phraseFile = phraseFile;
        this.cryptogramsFile = cryptogramsFile;
        this.currentPlayer = currentPlayer;
        gameFinished = false;
        players = new Players(playersFile);
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void playGame() throws IOException {
        logIn();
        System.out.print("Please select the type of cryptogram you would like to play:\n" +
                "1 for Letter Cryptogram\n" +
                "2 for Number Cryptogram\n" +
                "3 to Load a Cryptogram\n" +
                "4 to Show Leaderboards\n" +
                "\tEnter Choice >");
        char selection = checkValidInput(reader.readLine(), "1/2/3/4").charAt(0);
        if (selection == '4') {
            players.sort();
            int i = 0;
            while (i < 10 && i < Players.allPlayers.size()) {
                System.out.print("\n" + Players.allPlayers.get(i).getUsername() + "    Cryptograms Completed = " + Players.allPlayers.get(i).getCryptogramsCompleted());
                i++;
            }
            System.out.println();
            gameFinished = true;
        } else {
            Cryptogram cryptogram = generateCryptogram(selection);
            if (cryptogram.getPhrase().equals("ERROR")) {
                System.out.print("No cryptograms saved." +
                        "\nPlease select the type of cryptogram you would like to play:" +
                        "\n1 for Letter Cryptogram" +
                        "\n2 for Number Cryptogram" +
                        "\nEnter Choice >");
                selection = checkValidInput(reader.readLine(), "1/2").charAt(0);
                cryptogram = generateCryptogram(selection);
            }
            currentPlayer.incrementCryptogramsPlayed();
            if (selection != '3') {
                populateCurrentAnswer(cryptogram);
            }
            while (!gameFinished) {
                players.savePlayers(playersFile);
                printGameStatus(cryptogram, currentAnswer);
                System.out.print("Enter one of the following options:" +
                        "\n\tEnter" +
                        "\n\tUndo" +
                        "\n\tCheck" +
                        "\n\tStats" +
                        "\n\tSave" +
                        "\n\tSolve" +
                        "\n\tExit" +
                        "\n>");
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
                        if (checkAnswer(currentAnswer, cryptogram)) {
                            currentPlayer.incrementCryptogramsCompleted();
                            System.out.println("Congratulations! You have completed the cryptogram!\nWould you like to play again? (Y/N)");
                            char response = checkValidInput(reader.readLine(), "Y/N").charAt(0);
                            if (response == 'N') { //if the player does not want to play again the game will end
                                System.out.println("Goodbye!");
                                gameFinished = true;
                            } else { //if the player does want to play again the current cryptogram will end and a new cryptogram will begin
                                System.out.print("Please select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\n3 to load a Cryptogram\nEnter Choice >");
                                selection = checkValidInput(reader.readLine(), "1/2/3/4").charAt(0);
                                cryptogram = generateCryptogram(selection);
                                currentPlayer.incrementCryptogramsPlayed();
                                populateCurrentAnswer(cryptogram);
                            }
                        } else {
                            System.out.println("Incorrect. Please try again.");
                        }
                        players.savePlayers(playersFile);
                        break;
                    case "EXIT":
                        gameFinished = true;
                        players.savePlayers(playersFile);
                        System.out.println("Goodbye!");
                        break;
                    case "STATS":
                        currentPlayer.printStats();
                        break;
                    case "SAVE":
                        System.out.println("Saving cryptogram...");
                        cryptogram.saveCryptogram(currentPlayer.getUsername(), currentAnswer, selection);
                        break;
                    case "SOLVE":
                        System.out.println("The solution to the cryptogram: " + cryptogram.encrypted + "\nis: " + cryptogram.phrase + "\nWould you like to play again? (Y/N)");
                        char response = checkValidInput(reader.readLine(), "Y/N").charAt(0);
                        if (response == 'N') { //if the player does not want to play again the game will end
                            System.out.println("Goodbye!");
                            gameFinished = true;
                        } else { //if the player does want to play again the current cryptogram will end and a new cryptogram will begin
                            System.out.print("Please select the type of cryptogram you would like to play:\n1 for Letter Cryptogram\n2 for Number cryptogram\n3 to load Letter Cryptogram\n4 to load Number Cryptogram\nEnter Choice >");
                            selection = checkValidInput(reader.readLine(), "1/2/3/4").charAt(0);
                            cryptogram = generateCryptogram(selection);
                            currentPlayer.incrementCryptogramsPlayed();
                            populateCurrentAnswer(cryptogram);
                        }
                        break;
                    default:
                        System.out.println(input + " is not a valid input. Please enter a valid input");
                        break;
                }
            }
        }
    }

    private void logIn() throws IOException {
        System.out.print("Welcome to Friday Team 1's Cryptogram game!\n");
        if(currentPlayer.getUsername().equals("")){
            if(players.getAllPlayers().isEmpty()) {
                createNewPlayer();
            }
            else{
                System.out.print("Do you want to:\n1: Log in\n2: Create a new account\n\tEnter Choice> ");
                Character response = checkValidInput(reader.readLine(), "1/2").charAt(0);
                if (response == '1') {
                    boolean playerFound = false;
                    while (!playerFound) {
                        System.out.print("Please enter your username:\n\t>");
                        String username = checkValidInput(reader.readLine(), "General");
                        currentPlayer = players.findPlayer(username);
                        if (currentPlayer != null) {
                            System.out.println("You are now logged in as: " + username);
                            playerFound = true;
                        } else
                            System.out.println("Player " + username + " does not exist.");
                    }
                } else {
                    createNewPlayer();
                }
            }
        }
    }

    private void createNewPlayer() throws IOException {
        System.out.print("Welcome new player!\nPlease enter your desired username:\n\tEnter Username >");
        String username = checkValidInput(reader.readLine(), "General");
        currentPlayer = new Player(username,0,0,0,0);
        players.addPlayer(currentPlayer);
    }

    private void populateCurrentAnswer(Cryptogram cryptogram) {
        currentAnswer = "";
        for (int i = 0; i < cryptogram.getPhrase().length(); i++) { //fills the current answer with dashes to represent unspecified characters
            if (cryptogram.getPhrase().charAt(i) == ' ') {
                currentAnswer = currentAnswer.concat(" ");
            } else {
                currentAnswer = currentAnswer.concat("- ");
            }
        }
    }

    public String enterLetter(Cryptogram cryptogram, String currentAnswer, String selectedLetter, Character changeLetterTo) throws IOException {
        if(currentAnswer.contains(changeLetterTo.toString())) {
            System.out.println("You have already entered this letter. Please select a different letter");
        }
        else {
            String encrypted = cryptogram.getEncrypted();
            int selectedLetterIndex = 0;
            for (int i = 0; i < encrypted.length(); i++) {
                Character currToken = encrypted.charAt(i);
                if (currToken.toString().equals(selectedLetter)) {
                    selectedLetterIndex = encrypted.indexOf(currToken);
                    break;
                }
            }
            if(currentAnswer.charAt(selectedLetterIndex) == '-'){
                for(int j=0; j<currentAnswer.length(); j++){
                    Character currChar = encrypted.charAt(j);
                    if(currChar.toString().equals(selectedLetter)){
                        currentAnswer = currentAnswer.substring(0, j) + changeLetterTo + currentAnswer.substring(j + 1);
                    }
                }
            }
            else{
                System.out.println("You have already entered a letter into this slot, are you sure you want to override it? (Y/N)");
                char response = checkValidInput(reader.readLine(), "Y/N").charAt(0); //...the player will be asked if they want to override their previous answer
                if (response == 'N') {
                    System.out.println("Ok, character not changed");
                } else {
                    for(int j=0; j<currentAnswer.length(); j++){
                        Character currChar = encrypted.charAt(j);
                        if(currChar.equals(selectedLetter.charAt(0))){
                            currentAnswer = currentAnswer.substring(0, j) + changeLetterTo + currentAnswer.substring(j + 1);
                        }
                    }
                }
            }
        }
        return currentAnswer;
    }

    public String undoLetter(String currentAnswer) throws IOException {
        System.out.print("Please select the letter you wish to undo\n>");
        char response = checkValidInput(reader.readLine(), "General").charAt(0);
        String s = "" + response;
        if(currentAnswer.contains(s) && response != '-'){
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

    public boolean checkAnswer(String currentAnswer, Cryptogram cryptogram) {
        boolean correct = false;
        if (!currentAnswer.contains("-")) { //iff all empty spaces in the answer have been filled in by the player...
            currentPlayer.incrementTotalGuesses();
            String phrase = cryptogram.getPhrase();
            String spacedPhrase = "";
            for(int i=0; i<phrase.length(); i++){
                if(phrase.charAt(i) == ' '){
                   spacedPhrase = spacedPhrase.concat(" ");
                }
                else{
                    spacedPhrase = spacedPhrase.concat(phrase.charAt(i) + " ");
                }
            }
            if(currentAnswer.equals(spacedPhrase)){
                correct = true;
            }
        } else {
            System.out.println("There are still empty spaces in your answer. PLease enter more letters into your answer");
        }
        return correct;
    }


    /*Placeholder*/
    public String getHint() {
        return "";
    }


    /*Placeholder*/
    public void viewFrequencies() {

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
    public Cryptogram generateCryptogram(char selection) throws IOException {
        if (selection == '1') {
            System.out.println("Letter Cryptogram Selected");
            String phrase;
            FileReader fr = new FileReader(phraseFile);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> phrases = new ArrayList<>();
            try {
                String currPhrase;
                while ((currPhrase = br.readLine()) != null) {
                    phrases.add(currPhrase);
                }
            }
            catch(IOException e){
                System.out.println("Could not read from phrase bank");
            }
            Random random = new Random();
            phrase = phrases.get(random.nextInt(phrases.size()));
            return new LetterCryptogram(phrase.toUpperCase().trim());

        } else if (selection == '2') {
            System.out.println("Number Cryptogram Selected");
            String phrase;
            FileReader fr = new FileReader(phraseFile);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> phrases = new ArrayList<>();
            try {
                String currPhrase;
                while ((currPhrase = br.readLine()) != null) {
                    phrases.add(currPhrase);
                }
            }
            catch(IOException e){
                System.out.println("Could not read from phrase bank");
            }
            Random random = new Random();
            phrase = phrases.get(random.nextInt(phrases.size()));
            return new NumberCryptogram(phrase.toUpperCase().trim());
        }

        else {
            System.out.println("Loading Cryptogram");
            return loadCryptogram(currentPlayer.getUsername());
        }
    }

    /*Checks whether an input is valid based on the expected input*/
    private String checkValidInput(String input, String constraint) throws IOException {
        input = input.trim().toUpperCase();
        if (input.toUpperCase().equals("EXIT")) {
            setGameFinished(true);
        }

        if (input.equals("")) {
            input = "This";
        }
        char formattedInput = input.trim().toUpperCase().charAt(0);
        switch (constraint) {
            case "Y/N":
                if (formattedInput != 'Y' && formattedInput != 'N') {
                    System.out.print(input + " is not a valid input. Please enter a valid input (Y/N)\nEnter Choice > ");
                    input = reader.readLine();
                    input = checkValidInput(input, constraint);
                }
                break;
            case "1/2/3/4":
                if (formattedInput != '1' && formattedInput != '2' && formattedInput != '3' && formattedInput != '4') {
                    System.out.print(input + " is not a valid input. Please enter a valid input (1,2,3,4)\nEnter Choice > ");
                    input = reader.readLine();
                    input = checkValidInput(input, constraint);
                }
                break;
            case "1/2":
                if (formattedInput != '1' && formattedInput != '2') {
                    System.out.print(input + " is not a valid input. Please enter a valid input (1,2)\nEnter Choice > ");
                    input = reader.readLine();
                    input = checkValidInput(input, constraint);
                }
                break;
            case "General":
                if (input.isEmpty()) {
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

    private void printGameStatus(Cryptogram cryptogram, String currentAnswer) {
        String encrypted = cryptogram.getEncrypted();
        for(int i=0; i<encrypted.length(); i++){
            try{
                System.out.print(encrypted.charAt(i));
            }
            catch(NullPointerException e){
                System.out.print(" ");
            }
        }
        System.out.println();
        StringTokenizer defaultTokenizer = new StringTokenizer(cryptogram.getEncrypted());
        for(int i=0; i<currentAnswer.length(); i++){
            if (currentAnswer.charAt(i) == ' ') {
                System.out.print(currentAnswer.charAt(i));
            }
            else{
                System.out.print(currentAnswer.charAt(i));
                String currToken = defaultTokenizer.nextToken();
                for(int j=1; j<currToken.length(); j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    private Cryptogram loadCryptogram(String playerName) throws IOException {
        String line;
        File csv = new File(cryptogramsFile);
        if (csv.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(csv));
            while ((line = csvReader.readLine()) != null) {
                String[] cryptoData = line.split(",");
                if (cryptoData[0].compareTo(playerName) == 0) {
                    csvReader.close();
                    if (cryptoData[1].equals("Letter")) {
                        currentAnswer = cryptoData[4];
                        return new LetterCryptogram(cryptoData[2], cryptoData[3]);
                    } else {
                        currentAnswer = cryptoData[4];
                        return new NumberCryptogram(cryptoData[2], cryptoData[3]);
                    }
                }
            }
            csvReader.close();
            return new LetterCryptogram();
        }
        return new LetterCryptogram();
    }
}