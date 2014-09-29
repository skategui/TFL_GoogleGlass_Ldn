package containers;

/**
 * Created by guillaume on 28/09/2014.
 */
public class station {

    /**
     * Station's id
     */
    private int id;

    /**
     * Station's name
     */
    private String name;

    /**
     * Terminal 's name
     */
    private int terminalName;

    /**
     * station's latitude
     */
    private float latitude;

    /**
     * Station longitude
     */
    private float longitude;

    /**
     * station still available ?
     */
    private boolean installed;

    /**
     * number of bike available
     */
    private int available;

    /**
     * number of empty docks
     */
    private int emptyDock;


    /**
     * Total docks at this station
     */
    private int total;



    /**
     *  constructor
     */
    public station()
    {
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTerminalName(int terminalName) {
        this.terminalName = terminalName;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setEmptyDock(int emptyDock) {
        this.emptyDock = emptyDock;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTerminalName() {
        return terminalName;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public boolean isInstalled() {
        return installed;
    }

    public int getAvailable() {
        return available;
    }

    public int getEmptyDock() {
        return emptyDock;
    }




}
