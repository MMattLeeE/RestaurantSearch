package Model;

/**
 * Created by Matt on 7/18/2017.
 */
public class HaversineDistance {

    public static double distance(double lat1, double long1, double lat2, double long2) {
        Double R = 3958.756; //radius of earth in miles
        Double latDistance = Math.toRadians(lat2-lat1);

        Double longDistance = Math.toRadians(long2-long1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Double distance = R * c;

        return distance;
    }
}
