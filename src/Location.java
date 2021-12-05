import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /* declare private final locationId, description, exits
     */
    private final int LOCATION_ID;
    private final String DESCRIPTION;
    private final LinkedHashMap<String, Integer> EXITS = new LinkedHashMap<>();

    public Location(int locationId, String description, Map<String, Integer> exits) {
        /* set the locationId and the description
         */
        LOCATION_ID = locationId;
        DESCRIPTION = description;

        /* if exits are not null, set the exit
         * otherwise, set the exit HashMap to (Q,0)
         */
        if (!exits.isEmpty())
            exits.forEach(this::addExit);
        else
            addExit("Q", 0);
    }

    protected void addExit(String direction, int location) {
        /* put the direction and the location in the exits HashMap
         */
        EXITS.put(direction, location);
    }

    public int getLocationId() {
        /* complete getter to return the location id
         */
        return LOCATION_ID;
    }

    public String getDescription() {
        /* complete getter to return the description
         */
        return DESCRIPTION;
    }

    public Map<String, Integer> getExits() {
        /* complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        return EXITS;
    }
}
