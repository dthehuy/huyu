/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject5;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dtheh
 */
public class MainFXMLController implements Initializable {

    @FXML
    private TableView<HoiNghi_DAO> tableHoiNghi;
    @FXML
    private TableColumn<HoiNghi_DAO, String> tenColumn;
    @FXML
    private TableColumn<HoiNghi_DAO, String> maColumn;
    @FXML
    private TableColumn<HoiNghi_DAO, Integer> soLuongColumn;
    @FXML
    private TableColumn<HoiNghi_DAO, String> loaiPhongColumn;
    
    private ObservableList<HoiNghi_DAO> hoiNghiList;
    
    private ObservableList<LoaiPhong_DAO> loaiPhongList;
    
    private Connection conn;
    @FXML
    private TextField maHoiNghi;
    @FXML
    private TextField tenHoiNghi;
    @FXML
    private TextField soNguoi;
    @FXML
    private Button add;
    @FXML
    private Button save;
    @FXML
    private Button delete;
    @FXML
    private Button ketThuc;
    @FXML
    private ComboBox<String> comboBoxLoaiPhong;
    
    public Connection connect (){
        conn = null;
        try {
            String url       = "jdbc:mysql://localhost:3306/qlhn";
            String user      = "root";
            String password  = "123456";
            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {   
           System.out.println(e.getMessage());
        } 
        return conn;
    }

    public void filterHoiNghi(String maLoaiPhong){
         hoiNghiList = FXCollections.observableArrayList();
        if (connect() != null) {
            try {
                PreparedStatement stmt = connect().prepareStatement("SELECT * FROM HoiNghi where maLoaiPhong = ?");
                stmt.setString(1, maLoaiPhong);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    hoiNghiList.add( new HoiNghi_DAO(rs.getString("maHoiNghi"), 
                            rs.getString("tenHoiNghi"), 
                            rs.getInt("SoNguoi"), 
                            rs.getString("maLoaiPhong")));
                }
                tableHoiNghi.setItems(hoiNghiList);
            } catch (SQLException ex) {
            } 
        }
    }
    
    public void deleteHoiNghi(String maHoiNghi){
        if (connect() != null) {
            try {
                PreparedStatement stmt = connect().prepareStatement("DELETE FROM HoiNghi where maHoiNghi = ?");
                stmt.setString(1, maHoiNghi);
                stmt.executeUpdate();
            } catch (Exception e) {
            }
        }
    }
    
    public void addHoiNghi(String maHoiNghi, String tenHoiNghi, int soNguoi, String maLoaiPhong){
         if (connect() != null) {
             try {
                 PreparedStatement stmt = connect().prepareStatement("INSERT HoiNghi(maHoiNghi ,  tenHoiNghi , SoNguoi , maLoaiPhong ) VALUES (?, ?, ?, ?)");
                 stmt.setString(1, maHoiNghi);
                 stmt.setString(2, tenHoiNghi);
                 stmt.setInt(3, soNguoi);
                 stmt.setString(4, maLoaiPhong);
                 stmt.execute();
             } catch (Exception e) {
             }
        }
    }
    
    public ObservableList<LoaiPhong_DAO> getLoaiPhong(){
        loaiPhongList = FXCollections.observableArrayList();
        if (connect() != null) {
            try {
                String sql = "SELECT * FROM loaiphong";
                Statement stmt = connect().createStatement();;
                ResultSet rs = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    loaiPhongList.add( new LoaiPhong_DAO(rs.getString("maLoaiPhong"), 
                            rs.getString("tenLoaiPhong")));
                }
            } catch (SQLException ex) {
            }
        }
        return loaiPhongList;
    }
    
    public ObservableList<HoiNghi_DAO> getHoiNghi(){
        hoiNghiList = FXCollections.observableArrayList();
        if (connect() != null) {
            try {
                String sql = "SELECT * FROM hoinghi";
                Statement stmt = connect().createStatement();;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    hoiNghiList.add( new HoiNghi_DAO(rs.getString("maHoiNghi"), 
                            rs.getString("tenHoiNghi"), 
                            rs.getInt("SoNguoi"), 
                            rs.getString("maLoaiPhong")));
                }
            } catch (SQLException ex) {
            }
        }
        return hoiNghiList;
    }
    
    public void table(ObservableList<HoiNghi_DAO> hoiNghi){
        if (!hoiNghi.isEmpty()) {
            tenColumn.setCellValueFactory(new PropertyValueFactory<HoiNghi_DAO, String>("tenHoiNghi"));
            maColumn.setCellValueFactory(new PropertyValueFactory<HoiNghi_DAO, String>("maHoiNghi"));
            soLuongColumn.setCellValueFactory(new PropertyValueFactory<HoiNghi_DAO, Integer>("soNguoi"));
            loaiPhongColumn.setCellValueFactory(new PropertyValueFactory<HoiNghi_DAO, String>("maLoaiPhong"));
            tableHoiNghi.setItems(hoiNghi);       
        }   
    }
    
    public void comboBox(){
        if (!getLoaiPhong().isEmpty()) {
            for (int i = 0; i < getLoaiPhong().size(); i++) {
                comboBoxLoaiPhong.getItems().add(getLoaiPhong().get(i).getMaLoaiPhong());
            }   
        }
        comboBoxLoaiPhong.getSelectionModel().select(0);
    }
    
    public void selectedRow(){
        ObservableList<HoiNghi_DAO> slted = tableHoiNghi.getSelectionModel().getSelectedItems();
        slted.addListener(new ListChangeListener<HoiNghi_DAO>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends HoiNghi_DAO> c) {
                maHoiNghi.setText(slted.get(0).getMaHoiNghi());
                tenHoiNghi.setText(slted.get(0).getTenHoiNghi());
                soNguoi.setText(String.valueOf(slted.get(0).getSoNguoi()));
                comboBoxLoaiPhong.getSelectionModel().select(slted.get(0).getMaLoaiPhong());
            }
        });        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        table(getHoiNghi());
        comboBox();
        selectedRow();
    }       

    @FXML
    private void addClicked(MouseEvent event) {
        maHoiNghi.setText("");
        tenHoiNghi.setText("");
        soNguoi.setText("");
        comboBoxLoaiPhong.getSelectionModel().select(0);
        maHoiNghi.requestFocus();
   }
     

    @FXML
    private void saveCliked(MouseEvent event) {
        String maPhong = comboBoxLoaiPhong.getSelectionModel().getSelectedItem();
        addHoiNghi(maHoiNghi.getText(), 
                tenHoiNghi.getText(), 
                Integer.valueOf(soNguoi.getText()), 
                maPhong);
        table(getHoiNghi());
    }

    @FXML
    private void deleteClicked(MouseEvent event) {
        ObservableList<HoiNghi_DAO> slted = tableHoiNghi.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (!slted.isEmpty()) {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Ban co chac chan muon xoa hoi nghi nay.");
            alert.setHeaderText("Xac nhan");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                deleteHoiNghi(slted.get(0).getMaHoiNghi());
                tableHoiNghi.setItems(getHoiNghi());
            }
        }else{
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Khong co doi tuong de xoa..");
            alert.show();
        }
    }

    @FXML
    private void filerCliked(MouseEvent event) {
        filterHoiNghi(comboBoxLoaiPhong.getValue());
    }

    @FXML
    private void endCliked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Ban co chac chan thoat.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
