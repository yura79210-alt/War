public class Ship {

    private int size;
    private int health;

    public Ship(int size) {
        this.size = size;
        this.health = size;
    }

    public void hit() {
        health--;
    }

    public boolean isSunk() {
        return health <= 0;
    }
}
