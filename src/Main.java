import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board1 = new Board();
        Board board2 = new Board();
        Scanner scanner = new Scanner(System.in);
        // Случайная расстановка кораблей первого игрока
        board1.placeShipRandomly(4); // 1 корабль 4 клетки
        board1.placeShipRandomly(3); // 2 корабля 3 клетки
        board1.placeShipRandomly(3);
        board1.placeShipRandomly(2); // 3 корабля 2 клетки
        board1.placeShipRandomly(2);
        board1.placeShipRandomly(2);
        board1.placeShipRandomly(1); // 4 корабля 1 клетка
        board1.placeShipRandomly(1);
        board1.placeShipRandomly(1);
        board1.placeShipRandomly(1);

        // Случайная расстановка кораблей второго игрока
        board2.placeShipRandomly(4); // 1 корабль 4 клетки
        board2.placeShipRandomly(3); // 2 корабля 3 клетки
        board2.placeShipRandomly(3);
        board2.placeShipRandomly(2); // 3 корабля 2 клетки
        board2.placeShipRandomly(2);
        board2.placeShipRandomly(2);
        board2.placeShipRandomly(1); // 4 корабля 1 клетка
        board2.placeShipRandomly(1);
        board2.placeShipRandomly(1);
        board2.placeShipRandomly(1);
        boolean player1Turn = true;
        System.out.println("Игра началась! Введи координаты выстрела X и Y (0-9)");
        String userInput;
        int[] coords;
        int x, y;
        while (true) {
            Board currentBoard;
            String playerName;

            if (player1Turn) {
                currentBoard = board2; // игрок 1 стреляет по игроку 2
                playerName = "Игрок 1";
            } else {
                currentBoard = board1;
                playerName = "Игрок 2";
            }

            System.out.println("\n" + playerName + ", твой ход!");
            try {
                System.out.print("Введи координаты (например A1): ");

                userInput = scanner.next();

                coords = InputParser.parse(userInput);
                x = coords[0];
                y = coords[1];

                int result = currentBoard.shoot(x, y);

                if (result == -1) continue;

                Move move = new Move(x, y, result == 1);
                MoveLogger.logMove(move);

                System.out.println(result == 1 ? "ПОПАЛ!" : "МИМО");

                currentBoard.printPrettyBoard(true);

                if (currentBoard.allShipsSunk()) {
                    System.out.println("🏆 Победил " + playerName);
                    break;
                }

                if (result == 0) {
                    player1Turn = !player1Turn;
                }

            } catch (Exception e) {
                System.out.println("❌ Неверный ввод! Пример: A1 или 1A");
            }
        }
    }
}