import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by guillaume on 28/09/2014.
 */
public class dockersManagement {


    private ArrayList<station> _dockers =  new ArrayList<station>();

    public dockersManagement() {}

    public int updateDockers(userInfos user)
    {
        if (user.getOffset() == -1) // no top 5
        user.initializeDockers(this.getTop5nearestDockers(user));
        station nearestDock =  this.findNearestDocker(user);
        int current = user.updateCurrentStation(nearestDock);
        return current;
    }

    private station findNearestDocker(userInfos user)
    {
        for (int offset = 0; offset < _dockers.size(); offset++)
        {
            if (user.getCurrentStation().getTerminalName() == _dockers.get(offset).getTerminalName()  )
            return _dockers.get(offset);
        }
        return null;
    }

    private station[] getTop5nearestDockers(userInfos user)
    {
        station[] top5 = new station[5];
        TreeMap<Double, station> results = new TreeMap<Double, station>();

        for (int offset = 0; offset < _dockers.size(); offset++)
        {
            station current = _dockers.get(offset);
            float latitude = current.getLatitude();
            float longitude = current.getLongitude();
            double distance = this.distance((double)latitude, user.getLatitude(), (double)longitude, user.getLongitude());
            if (current.getAvailable() > 0) // save only dockers with available bikes
                results.put(distance, current);
        }

        for (int offset = 0; offset < 5; offset++)
        {
            top5[offset] = results.firstEntry().getValue();
            results.remove(results.firstEntry().getKey());
        }
        return top5;
    }

    public ArrayList<station> getDockers() {
        return _dockers;
    }

    public void setDockers(ArrayList<station> _dockers) {
        this._dockers = _dockers;
    }

    private double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = deg2rad(lat2 - lat1);
        Double lonDistance = deg2rad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

}
