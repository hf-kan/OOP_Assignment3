import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    /* create a static locations HashMap
     */
    private static final HashMap<Integer, Location> LOCATIONS = new HashMap<>();
    static {
        /* create a FileLogger object
         */
        final FileLogger FILE_LOGGER = new FileLogger();

        /* create a ConsoleLogger object
         */
        final ConsoleLogger CONSOLE_LOGGER = new ConsoleLogger();

        /* Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */

        File curFile = new File(LOCATIONS_FILE_NAME);
        String input, locDesc, logMsg, direction;
        final String LOG_SEPARATOR = ": ";
        String startMsg = "Available locations:";
        String[] splitDir;
        int locIndex, desIndex;
        final Map<String, Integer> TEMP_EXITS = new LinkedHashMap<>();

        try (FileReader fr = new FileReader(curFile);
             BufferedReader br = new BufferedReader(fr)) {
            input = br.readLine();
            CONSOLE_LOGGER.log(startMsg);
            FILE_LOGGER.log(startMsg);
            while (input != null) {
                locIndex = Integer.parseInt(input.substring(0, input.indexOf(",")));
                locDesc = input.substring(input.indexOf(",") + 1);
                LOCATIONS.put(locIndex, new Location(locIndex, locDesc, TEMP_EXITS));
                logMsg = locIndex + LOG_SEPARATOR + locDesc;
                CONSOLE_LOGGER.log(logMsg);
                FILE_LOGGER.log(logMsg);
                input = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(System.err);
        }


        /* Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        curFile = new File(DIRECTIONS_FILE_NAME);
        startMsg = "Available directions:";
        try (FileReader fr = new FileReader(curFile);
             BufferedReader br = new BufferedReader(fr)) {
            input = br.readLine();
            CONSOLE_LOGGER.log(startMsg);
            FILE_LOGGER.log(startMsg);
            while (input != null) {
                splitDir = input.split(",");
                locIndex = Integer.parseInt(splitDir[0]);
                direction = splitDir[1];
                desIndex = Integer.parseInt(splitDir[2]);
                LOCATIONS.get(locIndex).addExit(direction, desIndex);
                logMsg = splitDir[0] + LOG_SEPARATOR + direction + LOG_SEPARATOR + desIndex;
                CONSOLE_LOGGER.log(logMsg);
                FILE_LOGGER.log(logMsg);
                input = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(System.err);
        }
    }

    /* implement all methods for Map
     * @return
     */
    @Override
    public int size() {
        //TODO
        return LOCATIONS.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return LOCATIONS.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return LOCATIONS.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return LOCATIONS.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        //TODO
        return LOCATIONS.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return LOCATIONS.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return LOCATIONS.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
        LOCATIONS.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        LOCATIONS.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO
        return LOCATIONS.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return LOCATIONS.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return LOCATIONS.entrySet();
    }
}
