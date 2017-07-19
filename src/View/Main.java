package View;

import Model.RestaurantModel.RestaurantDB;
import Model.RestaurantModel.RestaurantExcelReader;
import MyDataStructures.Exceptions.ListElementDuplicate;
import MyDataStructures.Exceptions.ListIndexOutOfBounds;
import MyDataStructures.Exceptions.QueueUnderFlowException;
import MyDataStructures.Implementations.List.ListOrdered;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Matt on 5/27/2017.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("loading user DB...");
        loadUserDB();
        loadRestaurantsDB(); //NOTE: in later versions, read from excel file inside application and not at start or on a separate thread....
        System.out.println("loading login page...");
        Parent root = FXMLLoader.load(getClass().getResource("/View/loginPage.fxml"));

        Scene scene = new Scene(root, 600, 530);

        primaryStage.setTitle("Matt Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void loadUserDB() {
        try{
            Model.UserModel.UserDB.setUsersArrayList((ListOrdered<Model.UserModel.User>) Model.UserModel.UserIO.readUsers());
            //Model.UserModel.UserDB.printOrderedList();
            //System.out.println();
        } catch(IOException e) {
            System.err.print("Can't read/open users.dat file");
        } catch(ClassNotFoundException e) {
            System.err.print("Class not found...");
            e.printStackTrace();
        }
    }

    private void loadRestaurantsDB() {
        String filePath = "C:\\Users\\Matt\\Desktop\\restaurantList.xlsx";

        try {
            System.out.println("reading restaurants from excel file...");
            RestaurantDB.setRestaurantsDB(RestaurantExcelReader.readExcel(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cant load Excel file....");
        }

        try {
            System.out.println("balancing restaurant bst...");
            RestaurantDB.getRestaurantsDB().balanceTree();
        } catch (QueueUnderFlowException e) {
            e.printStackTrace();
        } catch (ListElementDuplicate listElementDuplicate) {
            listElementDuplicate.printStackTrace();
        } catch (ListIndexOutOfBounds listIndexOutOfBounds) {
            listIndexOutOfBounds.printStackTrace();
        }
        //RestaurantDB.getRestaurantsDB().printTreeStructure();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
