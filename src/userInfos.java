/**
 * Created by guillaume on 28/09/2014.
 */
public class userInfos {

    /**
     *
     */
    private station[] _topDockers;

    /**
     * station's latitude
     */
    private double latitude;

    /**
     * Station longitude
     */
    private double longitude;


    private int offset = -1;

    public userInfos() {}



    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void initializeDockers(station[] _topDockers)
    {
        this.setTopDockers(_topDockers);
        offset = 0;
        for (int offset = 0; offset < _topDockers.length; offset++)
        {
            System.out.println("Dock street : " + _topDockers[offset].getName());
            System.out.println("Dock available : " + _topDockers[offset].getAvailable());
        }
    }


    public station[] getTopDockers() {
        return this._topDockers;
    }

    public void setTopDockers(station[] _topDockers) {
        this._topDockers = _topDockers;
    }

    public station getCurrentStation() {
        return this._topDockers[offset];
    }

    public int updateCurrentStation(station current)
    {
        if (current.getAvailable() == 0)
            this.offset++;
        else
           this. _topDockers[offset] = current;
        return offset;
    }

    public void setCurrentPos(double latitude, double longitude)
    {
        this.setLongitude(longitude);
        this.setLatitude(latitude);
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
