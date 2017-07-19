package Model.RestaurantModel;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Matt on 6/25/2017.
 */
public class Restaurant implements Serializable, Comparable<Restaurant> {
    private String restaurantName;
    private String restaurantAddress;
    private double[] restaurantLocation;
    private String restaurantPhoneNumber;
    private String restaurantImage;
    private double distanceFromCurrentLocation;

    public Restaurant() {

    }
    public Restaurant(String name, String address, double[] location, String number, String image) {
        setRestaurantName(name);
        setRestaurantAddress(address);
        setRestaurantLocation(location);
        setRestaurantPhoneNumber(number);
        setRestaurantImage(image);
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantAddress() {
        return restaurantAddress;
    }
    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }
    public double[] getRestaurantLocation() {
        return restaurantLocation;
    }
    public void setRestaurantLocation(double[] restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }
    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }
    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }
    public String getRestaurantImage() {
        return restaurantImage;
    }
    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }
    public String toString() {
        return " " + restaurantName + " " + restaurantAddress + " " + restaurantLocation[0] + ", " + restaurantLocation[1] + " " + restaurantPhoneNumber + " " + restaurantImage;
    }

    //determine how restaurants are sorted on bst. Currently by Latitude.
    @Override
    public int compareTo(Restaurant o) {
        int tempCompare;
        if ((this.restaurantLocation[0] - o.restaurantLocation[0])==0) { //if the restaurants have the same location Latitude
            tempCompare = 0;
        } else if (this.restaurantLocation[0] < o.restaurantLocation[0]){
            tempCompare = (int) ((this.restaurantLocation[0]-o.restaurantLocation[0])*110.574);
        } else {
            tempCompare = (int) ((this.restaurantLocation[0]-o.restaurantLocation[0])*110.574);
        }
        return tempCompare;
    }

    //Comparators used to reorganize collections with restaurants by a specific field.
    public static Comparator<Restaurant> RestaurantNameComparator = new Comparator<Restaurant>() {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            String restaurantName1 = o1.getRestaurantName().toLowerCase();
            String restaurantName2 = o2.getRestaurantName().toLowerCase();

            return restaurantName1.compareTo(restaurantName2);
        }
    };
    public static Comparator<Restaurant> RestaurantAddressComparator = new Comparator<Restaurant>() {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            String restaurantAddress1 = o1.getRestaurantAddress().toLowerCase();
            String restaurantAddress2 = o2.getRestaurantAddress().toLowerCase();

            return restaurantAddress1.compareTo(restaurantAddress2);
        }
    };
    public static Comparator<Restaurant> RestaurantLatitudeComparator = new Comparator<Restaurant>() {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            if (o1.getRestaurantLocation()[0] < o2.getRestaurantLocation()[0]) return -1;
            if (o1.getRestaurantLocation()[0] > o2.getRestaurantLocation()[0]) return 1;
            return 0;
        }
    };
    public static Comparator<Restaurant> RestaurantLongitudeComparator = new Comparator<Restaurant>() {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            if (o1.getRestaurantLocation()[1] < o2.getRestaurantLocation()[1]) return -1;
            if (o1.getRestaurantLocation()[1] > o2.getRestaurantLocation()[1]) return 1;
            return 0;
        }
    };
    public static Comparator<Restaurant> RestaurantPhoneComparator = new Comparator<Restaurant>() {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            String restaurantPhone1 = o1.getRestaurantPhoneNumber();
            String restaurantPhone2 = o2.getRestaurantPhoneNumber();

            return restaurantPhone1.compareTo(restaurantPhone2);
        }
    };

    public double getDistanceFromCurrentLocation() {
        return distanceFromCurrentLocation;
    }

    public void setDistanceFromCurrentLocation(double distanceFromCurrentLocation) {
        this.distanceFromCurrentLocation = distanceFromCurrentLocation;
    }
}

