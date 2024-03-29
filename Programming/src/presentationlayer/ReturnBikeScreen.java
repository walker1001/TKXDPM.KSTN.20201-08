package presentationlayer;

import businesslogiclayer.controller.RentBikeController;
import businesslogiclayer.controller.ReturnBikeController;
import entities.Card;
import entities.Dock;
import entities.RentBikeTransaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import presentationlayer.box.ConfirmBox;
import presentationlayer.box.NotificationBox;
import presentationlayer.box.NotificationErrorCode;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Quản lý giao diện trả xe của người dùng
 */
public class ReturnBikeScreen implements Initializable {
//    public static String newDockID = null;
    private ArrayList<Dock> docks;
    private String rentalCode;

    @FXML
    private ListView<String> docksView;

    /**
     * Khởi tạo mặc định giao diện trả xe gồm danh sách các bãi xe đông thời lắng nghe xem người dùng có double click
     * vào một dòng trong dánh sách, nếu có double click thì hiển thị thông báo yêu cầu xác nhận và thực hiện giao dịch
     * sau khi người dùng xác nhận, cuối cùng lưu các kết quả vào cơ sở dữ liệu và về màn hình chính
     * @param url: thông số mặc định của hàm initialize
     * @param rb: thông số mặc định của hàm initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ReturnBikeScreen");
    }

    private void handleDoubleClickOnDockList() {
        System.out.println("User double on a dock");
        String dockInfo = docksView.getSelectionModel().getSelectedItem();
        Dock dock = getDockFromString(dockInfo);
        assert dock != null;
        if(!checkDockHasSpareDockingPoint(dock))
            NotificationBox.display("ERROR", "Bãi xe không còn chỗ trống!");
        else{
            boolean confirmReturnBike = ConfirmBox.display("ConfirmBox", "Xác nhận trả xe?");
            System.out.println(confirmReturnBike);
            if(confirmReturnBike){
                Card card = getCardInfoAfterUserConfirm();
                if(card != null){
                    processReturnBike(dock, rentalCode, card);
                }
            }
        }
    }

    private void processReturnBike(Dock d, String rentalCode, Card card){
//        newDockID = );
        Pair<String, RentBikeTransaction> s = ReturnBikeController.processReturnBike(rentalCode, card, d.getDockID());
        String respondCode = s.getKey();
        RentBikeTransaction rentBikeTransaction = s.getValue();

        NotificationErrorCode.displayNotificationErrorCode(respondCode, "refund");

        if (respondCode.equals("00")){
            MainScreen.reset = true;
            showRentBikeTransactionInfo(rentBikeTransaction);
            Stage stage = (Stage)docksView.getScene().getWindow();
            stage.close();
        }
    }

    private boolean checkDockHasSpareDockingPoint(Dock dock){
        return dock.getNumberOfDockingPoints() - dock.getBikes().size() > 0;
    }

    private Card getCardInfoAfterUserConfirm() {
        try {
            Card card = new Card();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CardInfoScreen.fxml"));
            Parent root = loader.load();
            CardInfoScreen cardInfoScreen = loader.getController();
            CardInfoScreen.card = null;
            Stage stageCard = new Stage();
            stageCard.setTitle("CardInfo");
            stageCard.setScene(new Scene(root));
            stageCard.showAndWait();
            card = cardInfoScreen.getCardInfo();
            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void initData(ArrayList<Dock> d, String rentalCode){
        System.out.println("initialize ReturnBikeScreen");
        docks = d;
        this.rentalCode = rentalCode;
        //show list of docks
        for (Dock dock : docks) {
            docksView.getItems().add(dock.getGeneralInfo());
        }

        docksView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleDoubleClickOnDockList();
            }
        });
    }

    /**
     * Lấy đối tượng bãi xe tương ứng trong dánh các bãi xe từ phản hồi double clicks của người dùng từ giao diện
     * vào một dòng trong danh sách
     * @param dockInfo: string phản hồi từ giao diện
     * @return: bãi xe tương ứng hoặc null nếu không tìm được
     */
    private Dock getDockFromString(String dockInfo){

        for(Dock dock: docks){
            String s = dock.getGeneralInfo();
            if(dockInfo.equals(s)){
                return dock;
            }
        }
        return null;
    }

    /**
     * Hiển thị thông tin chi tiết hóa đơn thuê xe
     * @param rentBikeTransaction: hóa đơn thuê xe
     */
    private void showRentBikeTransactionInfo(RentBikeTransaction rentBikeTransaction) {
        System.out.println("showRentBikeTransactionInfo");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RentBikeTransactionScreen.fxml"));
            Parent root = loader.load();

            RentBikeTransactionScreen rentBikeTransactionScreenController = loader.getController();
            rentBikeTransactionScreenController.initData(rentBikeTransaction);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBikeTransactionInfo");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
