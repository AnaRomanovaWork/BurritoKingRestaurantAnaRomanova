//class represent functionality to export orders to csv file

package model;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderExporter {
	//export order to csv file method
    public static void exportOrdersToCSV(List<AllOrdersInfo> orders, String filePath, boolean exportOrderStatus, boolean exportOrderNumber, boolean exportTotalPrice,  boolean exportFoodItems) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.append("Order Number");
            if (exportOrderStatus) {
                writer.append(",Order Status");
            }
            
            if (exportFoodItems) {
                writer.append(",Food Items");
            }
            
            if (exportTotalPrice) {
                writer.append(",Total Price");
            }
            
            writer.append("\n");

 
            for (AllOrdersInfo order : orders) {
                writer.append(String.valueOf(order.getOrderNumber()));
                if (exportOrderStatus) {
                    writer.append(",").append(order.getOrderStatus());
                }
                if (exportTotalPrice) {
                    writer.append(",").append(String.valueOf(order.getTotalPrice()));
                }
                if (exportFoodItems) {
                    writer.append(",").append(order.getFoodItems());
                }

                writer.append("\n");
            }
        }
    }
}
