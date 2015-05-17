package algorithmcomparison.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYDatasetTableModel;
import org.jfree.data.xy.XYSeries;
import algorithmcomparison.algorithms.BubbleSort;
import algorithmcomparison.algorithms.MergeSort;
import algorithmcomparison.generator.RandomArrayGenerator;
import algorithmcomparison.runtimetest.AlgorithmRuntimeTester;
import algorithmcomparison.runtimetest.RuntimeStatistics;

import javax.swing.JTable;

public class Graph extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RandomArrayGenerator arrayGenerator = new RandomArrayGenerator();
	public ArrayList<DefaultTableXYDataset> datasets= new ArrayList<DefaultTableXYDataset>();
	
	private static final int NUM_OF_ITERATIONS = 10;
	private JButton btnNewButton;
	private JPanel panel;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graph frame = new Graph();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private ArrayList<DefaultTableXYDataset> createDataset()
	{
		 datasets.clear();
		 XYSeries series1 = new XYSeries("BubbleAverageKeyComparison",true,false);
		 XYSeries series2 = new XYSeries("BubbleSTDEV",true,false);
		 XYSeries series3 = new XYSeries("BubbleAverageRunTime",true,false);
		 XYSeries series4 = new XYSeries("BubbleRuntimeSTDEV",true,false);
		AlgorithmRuntimeTester test=new AlgorithmRuntimeTester(new BubbleSort());
		
		 XYSeries series5= new XYSeries("MergeAverageKeyComparison",true,false);
		 XYSeries series6=new XYSeries("MergeSTDEV",true,false);
		 XYSeries series7= new XYSeries("MergeAverageRunTime",true,false);
		 XYSeries series8= new XYSeries("MergeRunTimeSTDEV",true,false);
		AlgorithmRuntimeTester test2=new AlgorithmRuntimeTester(new MergeSort());
		
		for (int i = 1; i <= NUM_OF_ITERATIONS; i++) {
			ArrayList<int[]> arrays = new ArrayList<int[]>();
			for(int j = 0; j < AlgorithmRuntimeTester.NUM_OF_ITERATIONS; j++)
				{arrays.add(arrayGenerator.generate(i * 1000));}
			RuntimeStatistics st1=test.run(arrays);
			RuntimeStatistics st2=test2.run(arrays);
			arrays.clear();
			
			//BUBBLESORT
			series1.add(i*1000, st1.getAverageKeyComparisons());
			series2.add(i*1000, st1.getStDevKeyComparisons());
			series3.add(i*1000, st1.getAverageRuntime());
			series4.add(i*1000, st1.getStDevRuntime());
		    //MERGE SORT
			series5.add(i*1000, st2.getAverageKeyComparisons());
			series6.add(i*1000, st2.getStDevKeyComparisons());
			series7.add(i*1000, st2.getAverageRuntime());
			series8.add(i*1000, st2.getStDevRuntime());
		}
		
		final DefaultTableXYDataset dataset = new DefaultTableXYDataset();  
		dataset.addSeries(series1);
        dataset.addSeries(series5);
        
        final DefaultTableXYDataset dataset2 = new DefaultTableXYDataset();
        dataset2.addSeries(series3);
        dataset2.addSeries(series7);
        
        final DefaultTableXYDataset dataset3 = new DefaultTableXYDataset();//For jtable
        dataset3.addSeries(series1);
        dataset3.addSeries(series2);
        dataset3.addSeries(series3);
        dataset3.addSeries(series4);
        dataset3.addSeries(series5);
        dataset3.addSeries(series6);
        dataset3.addSeries(series7);
        dataset3.addSeries(series8);
        
        
        datasets.add(dataset);
        datasets.add(dataset2);
        datasets.add(dataset3);
        return datasets;
	}
	
	private JFreeChart createChartKey(final XYDataset dataset){
		final JFreeChart chart=ChartFactory.createXYLineChart("AVERAGEKEYCOMPARISON", "INPUTSIZE", "AVGKEYCOMPARISON", dataset);
		
		chart.setBorderPaint(Color.YELLOW);
		
		final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        return chart;
		
	}
	
	
	private JFreeChart createChartRunTime(final XYDataset dataset){
		final JFreeChart chart=ChartFactory.createXYLineChart("AVERAGERUNTIME", "INPUTSIZE", "AVGKEYCOMPARISON", dataset);
		
		chart.setBorderPaint(Color.YELLOW);  
		
		final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        return chart;
		
	}
	

	/**
	 * Create the frame.
	 */
	public Graph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("FILLTABLE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final DefaultTableXYDataset dataset = createDataset().get(2);
				final XYDatasetTableModel tablemodel=new XYDatasetTableModel();
			
				tablemodel.setModel(dataset);
				
				
				JTable dataTable = new JTable(tablemodel);
				JScrollPane scroll = new JScrollPane(dataTable);
				scroll.setPreferredSize(new Dimension(750,300));
				
				panel.removeAll();
				panel.add(scroll);
				btnNewButton_1.setEnabled(true);
		        
			}
		});
		btnNewButton.setBounds(171, 437, 143, 35);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 818, 415);
		contentPane.add(panel);
		
		btnNewButton_1 = new JButton("PLOTGRAPH");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final DefaultTableXYDataset dataset = datasets.get(0);
				final JFreeChart chart = createChartKey(dataset);
				
				
			    ChartFrame frame=new ChartFrame("KEYCOMPARISON", chart);
			    frame.setVisible(true);
			    frame.setSize(750,650);
			    
			    final DefaultTableXYDataset dataset2 = datasets.get(1);
				final JFreeChart chart2 = createChartRunTime(dataset2);
				
			    ChartFrame frame2=new ChartFrame("RUNTIME", chart2); 
			    frame2.setVisible(true);
			    frame2.setSize(750,650);
			    btnNewButton_1.setEnabled(false);
			    datasets.clear();
			}
		});
		btnNewButton_1.setBounds(505, 437, 143, 35);
		contentPane.add(btnNewButton_1);
	}
}
