import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        // Случайная расстановка кораблей
        board.placeShipRandomly(4); // 1 корабль 4 клетки
        board.placeShipRandomly(3); // 2 корабля 3 клетки
        board.placeShipRandomly(3);
        board.placeShipRandomly(2); // 3 корабля 2 клетки
        board.placeShipRandomly(2);
        board.placeShipRandomly(2);
        board.placeShipRandomly(1); // 4 корабля 1 клетка
        board.placeShipRandomly(1);
        board.placeShipRandomly(1);
        board.placeShipRandomly(1);

        System.out.println("Игра началась! Введи координаты выстрела X и Y (0-9)");
        board.printBoard();
        while (true) {
            System.out.print("Введи X: ");
            int x = scanner.nextInt();

            System.out.print("Введи Y: ");
            int y = scanner.nextInt();

            int result = board.shoot(x, y);

            if (result == -1) continue; // неверный ход или уже стрелял сюда

            Move move = new Move(x, y, result == 1);
            MoveLogger.logMove(move);

            System.out.println(result == 1 ? "ПОПАЛ!" : "МИМО");

            board.printBoard();
            if (board.allShipsSunk()) {
                System.out.println(" ВСЕ КОРАБЛИ УНИЧТОЖЕНЫ! ТЫ ВЫИГРАЛ!");
                break;
            }
        }
    }
}