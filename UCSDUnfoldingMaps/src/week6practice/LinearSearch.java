package week6practice;

public class LinearSearch {
    private static Airport[] airports = new Airport[10];

    public static void main(String[] args) {
        for (int i = 0; i < airports.length; i++) {
            if (airports[i].getCity().equals(args[0])) {
                System.out.println(airports[i].getCode());
            }
        }
    }
}
