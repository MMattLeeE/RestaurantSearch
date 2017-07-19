package Model.RestaurantModel;

import Model.DataStructures.BinarySearchTree;

/**
 * Created by Matt on 7/4/2017.
 */
public class RestaurantDB {
    private static BinarySearchTree<Restaurant> restaurantsDB = new BinarySearchTree<>();

    public static BinarySearchTree<Restaurant> getRestaurantsDB() {
        return restaurantsDB;
    }

    public static void setRestaurantsDB(BinarySearchTree<Restaurant> restaurants) {
        RestaurantDB.restaurantsDB = restaurants;
    }
}
