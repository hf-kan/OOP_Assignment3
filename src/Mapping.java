import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /* create a static LocationMap object
     */
    private static final LocationMap LOC_MAP = new LocationMap();

    /* create a vocabulary HashMap to store all directions a user can go
     */
    private static final HashMap<String, String> VOCABULARY = new HashMap<>();

    /* create a FileLogger object
     */
    private static final FileLogger FILE_LOGGER = new FileLogger();

    /* create a ConsoleLogger object
     */
    private static final ConsoleLogger CONSOLE_LOGGER = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /* complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        VOCABULARY.put("QUIT", "Q");
        VOCABULARY.put("UP", "U");
        VOCABULARY.put("DOWN", "D");
        VOCABULARY.put("NORTH", "N");
        VOCABULARY.put("EAST", "E");
        VOCABULARY.put("SOUTH", "S");
        VOCABULARY.put("WEST", "W");
        VOCABULARY.put("NORTHEAST", "NE");
        VOCABULARY.put("SOUTHEAST", "SE");
        VOCABULARY.put("SOUTHWEST", "SW");
        VOCABULARY.put("NORTHWEST", "NW");
    }

    public void mapping() {

        /* create a Scanner object
         */
        Scanner sc = new Scanner(System.in);
        /* initialise a location variable with the INITIAL_LOCATION
         */
        int currentLocation = INITIAL_LOCATION;

        StringBuilder directionSb = new StringBuilder();
        Location curLocObj;
        String input, nextDirection;
        final String INVALID_DIR_MSG = "You cannot go in that direction";
        String[] multiInput;

        while (true) {

            /* get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            curLocObj = LOC_MAP.get(currentLocation);
            CONSOLE_LOGGER.log(curLocObj.getDescription());
            FILE_LOGGER.log(curLocObj.getDescription());

            /* verify if the location is the exit
             */
            if (curLocObj.getLocationId() == 0)
                System.exit(0);

            /* get a map of the exits for the location
             */
            Map<String, Integer> exits = curLocObj.getExits();

            /* print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            directionSb.setLength(0);
            directionSb.append("Available exits are ");
            exits.forEach((String, Integer) -> directionSb.append(String).append(", "));
            CONSOLE_LOGGER.log(directionSb.toString());
            FILE_LOGGER.log(directionSb.toString());

            /* input a direction
             * ensure that the input is converted to uppercase
             */
            input = String.format("%S", sc.nextLine());

            /* are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            nextDirection = "";
            if (VOCABULARY.containsKey(input))
                nextDirection = VOCABULARY.get(input);
            else if (VOCABULARY.containsValue(input))
                nextDirection = input;
            else {
                multiInput = input.split("[^a-zA-Z0-9]");
                for (String s : multiInput) {
                    if (VOCABULARY.containsKey(s))
                        nextDirection = VOCABULARY.get(s);
                }
            }

            /* if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if (exits.containsKey(nextDirection))
                currentLocation = exits.get(nextDirection);
            else {
                CONSOLE_LOGGER.log(INVALID_DIR_MSG);
                FILE_LOGGER.log(INVALID_DIR_MSG);
            }
        }
    }

    public static void main(String[] args) {
        /* run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping map = new Mapping();
        map.mapping();
    }
}
