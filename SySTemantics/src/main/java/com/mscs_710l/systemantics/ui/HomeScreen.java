/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.ui;

import com.mscs_710l.systemantics.db.SystemanticsDb;
import com.sun.media.jfxmedia.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dixita
 */
public class HomeScreen extends Application {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HomeScreen.class);
    private TableView table = new TableView();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home Screen");
        Group root = new Group();
        Scene scene = new Scene(root, 1250, 450, Color.WHITE);

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();
        for (int i = 0; i < 3; i++) {
            Tab tab = new Tab();

            switch (i) {
                case 0:
                    tab.setText("CPU Info");
                    createProcessTable();
                    final VBox vbox = new VBox();
                    vbox.setSpacing(5);
                    vbox.setPadding(new Insets(50, 10, 10, 70));
                    vbox.getChildren().addAll(table);

                    ((Group) scene.getRoot()).getChildren().addAll(vbox);
                    tab.setContent(table);
                    
                    break;
                case 1:
                    tab.setText("Memory Info");
                    break;
                case 2:
                    tab.setText("Network Info");
                     
                    break;
                default:
                    tab.setText("Extra tab");
                    break;
            }

            tabPane.getTabs().add(tab);
        }
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void createProcessTable() {
        try {
            TableColumn pidCol = new TableColumn("Process ID");
            TableColumn ppidCol = new TableColumn("Parent Process ID");
            TableColumn usernameCol = new TableColumn("Username");
            TableColumn priorityCol = new TableColumn("Priority");
            TableColumn niceCol = new TableColumn("Nice Value");
            TableColumn virtualCol = new TableColumn("Virtual");
            TableColumn resCol = new TableColumn("Res");
            TableColumn sharedCol = new TableColumn("Shared");
            TableColumn statusCol = new TableColumn("Status");
            TableColumn prctCpuUsageCol = new TableColumn("% CPU Usage");
            TableColumn prctMemUsageCol = new TableColumn("% Memory Usage");
            TableColumn timeCol = new TableColumn("Time");
            TableColumn commandCol = new TableColumn("Command");

            table.getColumns().addAll(pidCol, ppidCol, usernameCol,
                    priorityCol, niceCol, virtualCol, resCol, sharedCol,
                    statusCol, prctCpuUsageCol, prctMemUsageCol, timeCol, commandCol);

        } catch (Exception ex) {
            LOGGER.error("Exception in createProcessTable() in HomeScreen.java" + ex.getMessage());
        }
    }

}
