package Controller;

import Model.DataStructures.BinarySearchTree;
import Model.DataStructures.PriorityQueueOverflowException;
import Model.DataStructures.PriorityQueueUnderflowException;
import Model.DataStructures.minHeap;
import Model.HaversineDistance;
import Model.RestaurantModel.Restaurant;
import Model.RestaurantModel.RestaurantDB;
import MyDataStructures.Exceptions.ListIndexOutOfBounds;
import MyDataStructures.Exceptions.QueueUnderFlowException;
import MyDataStructures.Implementations.NodeIndexed;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Matt on 6/25/2017.
 */
public class ControllerRestaurantSearchPage implements Initializable {

    public Label Name;
    public Label Address;
    public Label Lat;
    public Label Long;
    public Label Phone;
    public TextField currentLat;
    public TextField currentLong;
    public TextField searchRadius;
    public CheckBox useLocationSearch;
    public Label locationError;

    @FXML private Label restaurantNameOutput;
    @FXML private Label restaurantAddressOutput;
    @FXML private Label restaurantLatitudeOutput;
    @FXML private Label restaurantLongitudeOutput;
    @FXML private Label restaurantPhoneNumberOutput;
    @FXML private ImageView restaurantImageView;

    @FXML private TextField searchTextField;
    @FXML private Button searchBtn;

    @FXML private Button UserInfoBtn;
    @FXML private Button LogOutBtn;

    @FXML private TableView<Restaurant> restaurantTable;
    //@FXML private TableColumn<Restaurant, String> restaurantNameCol;

    private ArrayList<Restaurant> searchArray;
    private ArrayList<Restaurant> tempList;
    private BinarySearchTree<Restaurant> bst;
    /**
    private BinarySearchTree<Restaurant> bstName;
    private BinarySearchTree<Restaurant> bstAddress;
    private BinarySearchTree<Restaurant> bstLongitude;
    private BinarySearchTree<Restaurant> bstPhone;
    **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bst = RestaurantDB.getRestaurantsDB();
    /**
        try {
            bstName = bst.reorderBST(Restaurant.RestaurantNameComparator);
            bstAddress = bst.reorderBST(Restaurant.RestaurantAddressComparator);
            bstLongitude = bst.reorderBST(Restaurant.RestaurantLongitudeComparator);
            bstPhone = bst.reorderBST(Restaurant.RestaurantPhoneComparator);
        } catch (ListIndexOutOfBounds listIndexOutOfBounds) {
            listIndexOutOfBounds.printStackTrace();
        } catch (QueueUnderFlowException e) {
            e.printStackTrace();
        }
    **/
        //Code below that populates the table with all restaurants from bst
        tempList = new ArrayList<>();
        bst.reset(BinarySearchTree.INORDER);
        for (int i=0; i<bst.size(); i++) {
            try {
                tempList.add(bst.getNext(BinarySearchTree.INORDER));
            } catch (QueueUnderFlowException e) {
                e.printStackTrace();
                System.err.println("Binary Search Tree inorder queue is empty...");
            }
        }
        restaurantTable.getItems().setAll(tempList);

        //check when checkbox is selected
        useLocationSearch.setOnAction((event) -> {
            locationError.setVisible(false);
            boolean selected = useLocationSearch.isSelected();
            if (selected) {
                currentLat.setDisable(false);
                currentLong.setDisable(false);
                searchRadius.setDisable(false);
            } else if (!selected) {
                currentLat.setDisable(true);
                currentLong.setDisable(true);
                searchRadius.setDisable(true);
            }
        });
        //change listener for when a restaurant is selected on list...
        restaurantTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (restaurantTable.getSelectionModel().getSelectedItem() != null) {
                locationError.setVisible(false);
                displayRestaurantData(newSelection);
            }
        });
        //When user clicks search button
        searchBtn.setOnAction(e -> {
            searchRestaurants();
        });
        //When user info button is pressed
        UserInfoBtn.setOnAction(e -> {
            try {
                LoadPage.loadUserPage(e);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        });
        //When log out button is pressed
        LogOutBtn.setOnAction(e -> {
            try {
                LoadPage.loadLoginPage(e);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void displayRestaurantData(Restaurant currentSelected) {
        restaurantNameOutput.setText(currentSelected.getRestaurantName());
        restaurantAddressOutput.setText(currentSelected.getRestaurantAddress());

        restaurantLatitudeOutput.setText(Double.toString(currentSelected.getRestaurantLocation()[0]));
        restaurantLongitudeOutput.setText(Double.toString(currentSelected.getRestaurantLocation()[1]));

        restaurantPhoneNumberOutput.setText(currentSelected.getRestaurantPhoneNumber());

        Image image = new Image(currentSelected.getRestaurantImage(),583,322,true,false);
        restaurantImageView.setImage(image);

        //make all the restaurant information visible
        setRestaurantInfoVisibility(true);
    }

    private void searchRestaurants() {
        locationError.setVisible(false);
        //get the search query from the search textfield
        String searchQuery = searchTextField.getText().toLowerCase();
        //the field that holds what is going to be displayed in the table
        searchArray = new ArrayList<>();

        if (useLocationSearch.isSelected() ) { //if user location search is enabled:
            if (isLocationInputsEmpty()) {
                locationError.setText("Current lat/long or search radius is empty");
                locationError.setTextFill(Color.RED);
                locationError.setVisible(true);
            } else if (!isValidLocationInputs()){
                locationError.setText("Invalid location inputs");
                locationError.setTextFill(Color.RED);
                locationError.setVisible(true);
            } else {
                //if nothing is inputted in search bar return restaurants that are in search radius
                locationSearch(searchQuery);

                //if there is nothing in searchArray, it means nothing is within search radius or nothing in search radius meets search query.
                if (searchArray.size()==0) {
                    restaurantTable.setPlaceholder(new Label("No matches to search query." + "\n" + "Press SEARCH to display original list."));
                    restaurantTable.getItems().clear();
                } else {
                    restaurantTable.getItems().setAll(searchArray);
                }
            }
        } else { //all other cases use regular search:
            recursiveSearch(bst.getRoot(),searchQuery);

            //if search array is empty, the table view resets to show entire list, else the search array is displayed in the table
            if (searchArray.size()==0 && searchTextField.getText().trim().equals("")) { //if nothing is inputted and search is clicked, display entire list:
                restaurantTable.getItems().setAll(tempList);
            } else if (searchArray.size()==0){ //if no matches to search query are found:
                restaurantTable.setPlaceholder(new Label("No matches to search query." + "\n" + "Press SEARCH to display original list."));
                restaurantTable.getItems().clear();
            }else {
                restaurantTable.getItems().setAll(searchArray);
            }
        }

        //clears the search textfield after searching
        searchTextField.clear();
    }

    public void recursiveSearch(NodeIndexed<Restaurant> node, String query) {
        query = query.toLowerCase();
        if (node == null || query.equals("")) {
            return;
        } else {
            String currentName = node.getInfo().getRestaurantName().toLowerCase();
            String currentAddress = node.getInfo().getRestaurantAddress().toLowerCase();
            String currentLat = Double.toString(node.getInfo().getRestaurantLocation()[0]);
            String currentLong = Double.toString(node.getInfo().getRestaurantLocation()[1]);
            String currentPhone = node.getInfo().getRestaurantPhoneNumber();

            if (currentName.contains(query) || currentAddress.contains(query) || orderContainString(query, currentLat) || orderContainString(query, currentLong) || currentPhone.contains(query)) { //match is found
                searchArray.add(node.getInfo());
            }

            recursiveSearch(node.getRight(),query);
            recursiveSearch(node.getLeft(),query);

        }
    }

    public boolean orderContainString(String query, String checked) {//check the strings in order from the beginning.
        boolean output = false;
        if(query.length() > checked.length()) { //if the substring that is being looked for is larger than the string it is being looked for in it
            output = false;
        } else {
            boolean end = false;
            int index = 0;

            while (!end) {//ends if there is a mismatch for a character or if the last query character has been compared.
                if (checked.charAt(index)==query.charAt(index)){//if the character is a match
                    index++;

                    if (index == query.length()) {//if the last character of the query is checked and matches
                        output = true;
                        end = true;
                    }

                } else {
                    end = true;
                    output = false;
                }
            }
        }
        return output;
    }

    public void setRestaurantInfoVisibility(boolean isVisible) {
        restaurantNameOutput.setVisible(isVisible);
        restaurantAddressOutput.setVisible(isVisible);
        restaurantLatitudeOutput.setVisible(isVisible);
        restaurantLongitudeOutput.setVisible(isVisible);
        restaurantPhoneNumberOutput.setVisible(isVisible);
        restaurantImageView.setVisible(isVisible);

        Name.setVisible(isVisible);
        Address.setVisible(isVisible);
        Lat.setVisible(isVisible);
        Long.setVisible(isVisible);
        Phone.setVisible(isVisible);
    }

    public boolean isLocationInputsEmpty() {
        if (currentLong.getText().isEmpty() || currentLat.getText().isEmpty() || searchRadius.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidLocationInputs() {
        double clat;
        double clong;
        double cradius;

        try {
            clat = Double.parseDouble(currentLat.getText());
            clong = Double.parseDouble(currentLong.getText());
            cradius = Double.parseDouble(searchRadius.getText());
        } catch (Exception e) {
            return false;
        }

        if (-90>clat || clat>90) {
            return false;
        }

        if (-180>clong || clong>180) {
            return false;
        }

        if (cradius<0 || cradius>12450.5) {
            return false;
        }

        return true;
    }

    public void locationSearch(String searchQuery) {
        double cLatitude = Double.parseDouble(currentLat.getText());
        double cLongitude = Double.parseDouble(currentLong.getText());
        double cRadius = Double.parseDouble(searchRadius.getText());

        minHeap<Restaurant> locationHeap = new minHeap(bst.size());

        bst.reset(BinarySearchTree.INORDER);

        for (int i=0; i<bst.size(); i++) {
            try {
                Restaurant cRestaurant = bst.getNext(BinarySearchTree.INORDER);
                cRestaurant.setDistanceFromCurrentLocation(HaversineDistance.distance(cLatitude, cLongitude, cRestaurant.getRestaurantLocation()[0], cRestaurant.getRestaurantLocation()[1]));
                locationHeap.enqueue(cRestaurant);
            } catch (QueueUnderFlowException e) {
                e.printStackTrace();
                System.err.println("Binary Search Tree inorder queue is empty...");
            } catch (PriorityQueueOverflowException e) {
                System.err.println("Location search priority queue is full!");
            }
        }

        boolean isFound = false;
        int counter = 0;

        while(!isFound || counter!=bst.size()) {
            Restaurant output;

            try {
                output = locationHeap.dequeue();
                if (output.getDistanceFromCurrentLocation()<=cRadius){
                    searchArray.add(output);
                } else if (output.getDistanceFromCurrentLocation()>cRadius){
                    isFound=true;
                }
            } catch (PriorityQueueUnderflowException e) {
                e.printStackTrace();
            }

            counter++;
        }

        for (int i=0; i<searchArray.size(); i++) {
            Boolean hello;
        }
    }

}