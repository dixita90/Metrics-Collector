/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author uppalapati
 */
public class LineChartProgram extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = 
                new LineChart<>(xAxis,yAxis);
       
        lineChart.setTitle("Stock Monitoring, 2010");
                          
        XYChart.Series CPU_Stats = new XYChart.Series();
        CPU_Stats.setName("CPU Statistics");
        
        CPU_Stats.getData().add(new XYChart.Data("Jan", 23));
        CPU_Stats.getData().add(new XYChart.Data("Feb", 14));
        CPU_Stats.getData().add(new XYChart.Data("Mar", 15));
        CPU_Stats.getData().add(new XYChart.Data("Apr", 24));
        CPU_Stats.getData().add(new XYChart.Data("May", 34));
        CPU_Stats.getData().add(new XYChart.Data("Jun", 36));
        CPU_Stats.getData().add(new XYChart.Data("Jul", 22));
        CPU_Stats.getData().add(new XYChart.Data("Aug", 45));
        CPU_Stats.getData().add(new XYChart.Data("Sep", 43));
        CPU_Stats.getData().add(new XYChart.Data("Oct", 17));
        CPU_Stats.getData().add(new XYChart.Data("Nov", 29));
        CPU_Stats.getData().add(new XYChart.Data("Dec", 25));
        
        XYChart.Series Memory_Stats = new XYChart.Series();
        Memory_Stats.setName("Memory Statistics");
        Memory_Stats.getData().add(new XYChart.Data("Jan", 33));
        Memory_Stats.getData().add(new XYChart.Data("Feb", 34));
        Memory_Stats.getData().add(new XYChart.Data("Mar", 25));
        Memory_Stats.getData().add(new XYChart.Data("Apr", 44));
        Memory_Stats.getData().add(new XYChart.Data("May", 39));
        Memory_Stats.getData().add(new XYChart.Data("Jun", 16));
        Memory_Stats.getData().add(new XYChart.Data("Jul", 55));
        Memory_Stats.getData().add(new XYChart.Data("Aug", 54));
        Memory_Stats.getData().add(new XYChart.Data("Sep", 48));
        Memory_Stats.getData().add(new XYChart.Data("Oct", 27));
        Memory_Stats.getData().add(new XYChart.Data("Nov", 37));
        Memory_Stats.getData().add(new XYChart.Data("Dec", 29));
        
        XYChart.Series Network_Stats = new XYChart.Series();
        Network_Stats.setName("Network Statistics");
        Network_Stats.getData().add(new XYChart.Data("Jan", 44));
        Network_Stats.getData().add(new XYChart.Data("Feb", 35));
        Network_Stats.getData().add(new XYChart.Data("Mar", 36));
        Network_Stats.getData().add(new XYChart.Data("Apr", 33));
        Network_Stats.getData().add(new XYChart.Data("May", 31));
        Network_Stats.getData().add(new XYChart.Data("Jun", 26));
        Network_Stats.getData().add(new XYChart.Data("Jul", 22));
        Network_Stats.getData().add(new XYChart.Data("Aug", 25));
        Network_Stats.getData().add(new XYChart.Data("Sep", 43));
        Network_Stats.getData().add(new XYChart.Data("Oct", 44));
        Network_Stats.getData().add(new XYChart.Data("Nov", 45));
        Network_Stats.getData().add(new XYChart.Data("Dec", 44));
        
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().addAll(CPU_Stats, Memory_Stats, Network_Stats);
       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
