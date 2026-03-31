import java.util.ArrayList;
import java.util.List;

public class Ship {

    private int health = 0;
    private List<int[]> coordinates = new ArrayList<>();

    public void addCoordinate(int x, int y) {
        coordinates.add(new int[]{x, y});
        health++; // 👈 увеличиваем здоровье
    }

    public void hit() {
        health--;
        System.out.println("health = " + health);
    }

    public boolean isSunk() {
        return health == 0;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }
}