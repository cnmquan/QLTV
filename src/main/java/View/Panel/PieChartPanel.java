/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Panel;

import constant.TitleStringConstant;
import java.awt.Color;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * PieChartPanel dùng để tạo ra PieChart với giá trị truyền vào là
 * @param totalBook số lượng sách còn lại
 * @param borrowBook số lượng sách mượn
 * @author Admin
 */
public class PieChartPanel {

    public PieChartPanel() {
    }

    // Khởi tạo các giá trị trong Pie Chart
    private static PieDataset createDataset(int totalBook, int borrowBook) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue(TitleStringConstant.PIE_CHART_BORROWED, borrowBook);
        dataset.setValue(TitleStringConstant.PIE_CHART_REST, totalBook);
        return dataset;
    }
    
    // Khởi tạo PieChart
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                TitleStringConstant.PIE_CHART_TITLE, // chart title 
                dataset, // data    
                true, // include legend   
                true,
                false);
        
        PiePlot piePlot = (PiePlot) chart.getPlot();
        
        // Thay đổi màu của Pie Chart Plot
        piePlot.setSectionPaint(TitleStringConstant.PIE_CHART_BORROWED,new Color(255,51,51));
        piePlot.setSectionPaint(TitleStringConstant.PIE_CHART_REST,new Color(102,102,255));
        piePlot.setBackgroundPaint(Color.white);

        return chart;
    }

    // Hiển thị PieChart
    public static JPanel showChart(int totalBook, int borrowBook) {
        JFreeChart chart = createChart(createDataset(totalBook, borrowBook));
        return new ChartPanel(chart);
    }

}
