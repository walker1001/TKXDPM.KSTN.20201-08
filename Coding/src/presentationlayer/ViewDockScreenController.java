package presentationlayer;

import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDockScreenController implements Initializable {
    private Dock dock;

    @FXML
    private ListView<String> bikesView;

    @FXML
    private TextField text1, text2, text3, text4, text5, text6;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ViewDockScreen");

        //listen when user double click on a bike in listview => show ViewBikeScreen
        bikesView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                System.out.println("User double on a dock");
                String dockInfo = bikesView.getSelectionModel().getSelectedItem();
                Bike bike = getBikeFromString(dockInfo);

                showViewBikeScreen(bike);
            }
        });
    }

    public void initData(Dock dock){
        System.out.println("initialize dockInfo and bikesInfo ");
        this.dock = dock;
        text1.setText(dock.getDockID());
        text2.setText(dock.getName());
        text3.setText(dock.getAddress());
        text4.setText(dock.getArea());
        text5.setText(Integer.toString(dock.getNumberOfDockingPoints()));
        text6.setText(Integer.toString(dock.getNumberOfBikes()));
        ArrayList<Bike> bikes = dock.getBikes();
        for(Bike bike : bikes)
            if(!bike.isInUse())
                bikesView.getItems().add(bike.getGeneralInfo());
    }

    public void showViewBikeScreen(Bike bike){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBikeScreen.fxml"));
            Parent root = loader.load();

            ViewBikeScreenController viewBikeController = loader.getController();

            viewBikeController.init(bike);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("ViewBikeScreen");
            stage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Bike getBikeFromString(String bikeInfo){

        for(Bike bike: dock.getBikes()){
            String s = bike.getGeneralInfo();
            if(bikeInfo.equals(s)){
                return bike;
            }
        }
        return null;
    }
}
