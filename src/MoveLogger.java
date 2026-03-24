import java.io.FileWriter;
import java.io.IOException;

public class MoveLogger {

    public static void logMove(Move move) {
        try (FileWriter writer = new FileWriter("moves.txt", true)) {
            writer.write(move.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}