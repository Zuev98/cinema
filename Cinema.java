package cinema;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final int CAPACITY = 60, PRICE = 10, LOWER_PRICE = 8;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int rows, columns, total;
    private static char[][] seats;
    private static String numColumns = " ";
    private static int ticket = 0, income = 0;
    private static double percentage;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = SCANNER.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = SCANNER.nextInt();

        if (rows * columns <= CAPACITY)
            total = rows * columns * 10;
        else
            total = rows / 2 * columns * PRICE + (rows - rows / 2) * columns * LOWER_PRICE;

        seats = new char[rows][columns];
        for (char[] seat: seats)
            Arrays.fill(seat, 'S');

        for (int i = 1; i <= columns; i++)
            numColumns += " " + i;

        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = SCANNER.nextInt();
            switch (choice) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    getStatistics();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void showSeats() {
        System.out.println("Cinema:");
        System.out.println(numColumns);
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < columns; j++)
                System.out.print(seats[i][j] + " ");
            System.out.println();
        }
    }

    private static void buyTicket() {
        int seatRow, seatColumn;
        do {
            System.out.println("\nEnter a row number:");
            seatRow = SCANNER.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatColumn = SCANNER.nextInt();
            if (seatRow < 0 || seatRow > rows || seatColumn < 0 || seatColumn > columns)
                System.out.println("Wrong input!");
            else
                if (seats[seatRow - 1][seatColumn - 1] == 'B')
                    System.out.println("That ticket has already been purchased!");
                else
                    break;
        } while (true);

        if (rows * columns <= CAPACITY) {
            System.out.println("Ticket price: $" + PRICE);
            income += PRICE;
        } else
            if (rows / 2 >= seatRow) {
                System.out.println("Ticket price: $" + PRICE);
                income += PRICE;
            } else {
                System.out.println("Ticket price: $" + LOWER_PRICE);
                income += LOWER_PRICE;
            }

        seats[seatRow - 1][seatColumn - 1] = 'B';
        ticket++;
        percentage = (double)ticket * 100 / (rows * columns);
    }

    private static void getStatistics() {
        System.out.printf("\nNumber of purchased tickets: %d\n", ticket);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%d\n", income);
        System.out.printf("Total income: $%d\n", total);
    }
}