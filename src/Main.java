import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Player p = new Player("Bob", 0, 0, 0, 0);
        Game g = new Game(p);
        g.playGame();
    }
}
