public class InputParser {

    public static int[] parse(String input) {

        input = input.toUpperCase();

        char letter;
        int number;

        if (Character.isLetter(input.charAt(0))) {
            letter = input.charAt(0);
            number = Integer.parseInt(input.substring(1));
        } else {
            number = Integer.parseInt(input.substring(0, input.length() - 1));
            letter = input.charAt(input.length() - 1);
        }

        int x = number - 1;
        int y = letter - 'A';

        return new int[]{x, y};
    }
}