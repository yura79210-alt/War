import java.util.Random;

public class Board {

    private char[][] grid = new char[10][10];  // отображение на экране
    private Ship[][] ships = new Ship[10][10]; // объекты кораблей

    // Конструктор
    public Board() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '.';
            }
        }
    }
    public void printPrettyBoard(boolean hideShips) {

        System.out.print("  ");
        for (char c = 'A'; c < 'A' + 10; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < 10; j++) {

                char cell = grid[i][j];

                if (hideShips && cell == 'S') {
                    System.out.print(". ");
                } else {
                    System.out.print(cell + " ");
                }
            }

            System.out.println();
        }
    }
    public void printHiddenBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (grid[i][j] == 'S') {
                    System.out.print(". "); // скрываем корабль
                } else {
                    System.out.print(grid[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    // Печать поля
    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Ставим корабль вручную
    public void placeShip(int x, int y, int size) {
        Ship ship = new Ship(size);

        for (int i = 0; i < size; i++) {
            grid[x][y + i] = 'S';
            ships[x][y + i] = ship;
        }
    }

    // Новый метод: случайное размещение корабля
    public void placeShipRandomly(int size) {
        Random rand = new Random();
        boolean placed = false;

        while (!placed) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            boolean horizontal = rand.nextBoolean();

            if (horizontal) {
                if (y + size > 10) continue;
                boolean collision = false;
                for (int i = 0; i < size; i++) {
                    if (ships[x][y + i] != null) {
                        collision = true;
                        break;
                    }
                }
                if (collision) continue;

                Ship ship = new Ship(size);
                for (int i = 0; i < size; i++) {
                    grid[x][y + i] = 'S';
                    ships[x][y + i] = ship;
                }
                placed = true;
            } else {
                if (x + size > 10) continue;
                boolean collision = false;
                for (int i = 0; i < size; i++) {
                    if (ships[x + i][y] != null) {
                        collision = true;
                        break;
                    }
                }
                if (collision) continue;

                Ship ship = new Ship(size);
                for (int i = 0; i < size; i++) {
                    grid[x + i][y] = 'S';
                    ships[x + i][y] = ship;
                }
                placed = true;
            }
        }
    }

    // Выстрел
    public int shoot(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            System.out.println(" Неверные координаты!");
            return -1;
        }

        if (grid[x][y] == 'X' || grid[x][y] == 'O') {
            System.out.println(" Ты уже стрелял сюда!");
            return -1;
        }

        if (ships[x][y] != null) {
            grid[x][y] = 'X';
            Ship ship = ships[x][y];
            ship.hit();
            if (ship.isSunk()) {
                System.out.println(" Корабль уничтожен!");
            }
            return 1;
        } else {
            grid[x][y] = 'O';
            return 0;
        }
    }
    public boolean allShipsSunk() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (ships[i][j] != null && !ships[i][j].isSunk()) {
                    return false;
                }

            }
        }

        return true;
    }
}