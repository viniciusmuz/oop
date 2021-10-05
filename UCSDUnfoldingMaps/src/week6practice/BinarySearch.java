package week6practice;

public class BinarySearch {

    private Airport[] airports = new Airport[10];

    public BinarySearch() {
        airports[0] = new Airport("Berlin-Schönefeld Airport", "Berlin", "Germany", "EDDB");
        airports[1] = new Airport("Dresden Airport", "Dresden", "Germany", "EDDC");
        airports[2] = new Airport("Edinburgh Airport", "Edinburgh", "Scotland", "EGPH");
        airports[3] = new Airport("Lyon Saint-Exupéry Airport", "Lyon", "France", "LFLL");
        airports[4] = new Airport("Angel S Adami Airport", "Montevideo", "Uruguay", "SUAA");
        airports[5] = new Airport("John F Kennedy International Airport", "New York", "United States", "KJFK");
        airports[6] = new Airport("Aeroporto Brigadeiro Eduardo Gomes", "Nova Mutum", "Brazil", "SDNM");
        airports[7] = new Airport("Incheon International Airport", "Seoul", "South Korea", "RKSI");
        airports[8] = new Airport("Sydney Bankstown Airport", "Sydney", "Australia", "YSBK");
        airports[9] = new Airport("Vancouver International Airport", "Vancouver", "Canada", "CYVR");
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch().search("Lyon"));
    }

    public String search(String argument) {
        int low = 0;
        int high = airports.length - 1;

        int count = 0;

        while (high >= low) {
            count++;
            int mid = low + ((high - low) / 2);
            int comparison = argument.compareTo(airports[mid].getCity());
            if (comparison == 0) {
                String result = airports[mid].getCode() + "\n";
                result += "NÚMERO DE EXECUÇÕES: " + count;
                return result;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return "none\nNUMERO DE EXECUÇÕES: " + count;
    }
}