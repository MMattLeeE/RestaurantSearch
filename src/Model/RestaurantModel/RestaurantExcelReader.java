package Model.RestaurantModel;

import Model.DataStructures.BinarySearchTree;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by Matt on 7/2/2017.
 */
public class RestaurantExcelReader {
    public static String addressString = "";

    public static DataFormatter fmt = new DataFormatter();

    public static BinarySearchTree<Restaurant> readExcel(String theExcelFile) throws IOException {
        BinarySearchTree<Restaurant> restaurantBST = new BinarySearchTree<>();
        FileInputStream inputStream = new FileInputStream(new File(theExcelFile));

        Workbook workbook = getWorkbook(inputStream, theExcelFile);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        //consume the first row as the excel sheet has a header row
        Row headerRow = iterator.next();
        //conditional check cuz hasNext keeps reading empty rows...
        boolean lastRestaurant = false;

        while(iterator.hasNext() && !lastRestaurant) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Restaurant aRestaurant = new Restaurant();
            double[] loc = {0, 0};

            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();

                switch (columnIndex) {
                    case 1: //restaurant name
                        aRestaurant.setRestaurantName((String) getCellValue(cell));
                        //System.out.println(aRestaurant.getRestaurantName());
                        break;
                    case 2: //street
                        addressString = (String) getCellValue(cell);
                        //System.out.println(addressString);
                        break;
                    case 3: //city
                        addressString += " " + getCellValue(cell) + ", ";
                        //System.out.println(addressString);
                        break;
                    case 4: //state
                        addressString += getCellValue(cell);
                        //System.out.println(addressString);
                        break;
                    case 5: //zip
                        addressString += " " + getCellValue(cell);
                        aRestaurant.setRestaurantAddress(addressString);
                        //System.out.println(aRestaurant.getRestaurantAddress());
                        break;
                    case 6: //latitude
                        if (getCellValue(cell) == null) {
                            loc[0] = 0;
                            //System.out.println("latitude for " + aRestaurant.getRestaurantName() + " is null");
                        } else {
                            loc[0] = Double.parseDouble((String)getCellValue(cell));
                        }
                        break;
                    case 7: //longitude
                        if (getCellValue(cell) == null) {
                            loc[1] = 0;
                            //System.out.println("longitude for " + aRestaurant.getRestaurantName() + " is null");
                            aRestaurant.setRestaurantLocation(loc);
                        } else {
                            loc[1] = Double.parseDouble((String) getCellValue(cell));
                            aRestaurant.setRestaurantLocation(loc);
                        }
                        //System.out.println(aRestaurant.getRestaurantLocation()[0] + ", " + aRestaurant.getRestaurantLocation()[1]);
                        break;
                    case 8: //phone number
                        aRestaurant.setRestaurantPhoneNumber((String) getCellValue(cell));
                        //System.out.println(aRestaurant.getRestaurantPhoneNumber());
                        break;
                    case 9: //image url
                        aRestaurant.setRestaurantImage((String) getCellValue(cell));
                        //System.out.println(aRestaurant.getRestaurantImage());
                        break;
                }
            }//end cells of row iteration while

           // System.out.println("This is the name of the restaurant before being put in: " + aRestaurant.getRestaurantName());
            if (aRestaurant.getRestaurantName() != null) {
                restaurantBST.add(aRestaurant);
                //System.out.println("The restaurant " + aRestaurant.getRestaurantName() + " was added successfully!");
            } else {
                lastRestaurant = true;
            }

        }//end next row iteration while
        workbook.close();
        inputStream.close();

       // restaurantBST.printTreeStructure();
        return restaurantBST;
    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case NUMERIC:
                return fmt.formatCellValue(cell);
        }
        return null;
    }
    private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }
}
