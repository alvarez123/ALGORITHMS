package algorithmcomparison.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import algorithmcomparison.algorithms.BubbleSort;
import algorithmcomparison.algorithms.MergeSort;
import algorithmcomparison.generator.RandomArrayGenerator;
import algorithmcomparison.runtimetest.AlgorithmRuntimeTester;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graph extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbtnNewRadioButton;
	private RandomArrayGenerator arrayGenerator = new RandomArrayGenerator();
	private static final int NUM_OF_ITERATIONS = 10;

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
	
	private XYDataset createDataset()
	{
		final XYSeries series1 = new XYSeries("BubbleSort");
		AlgorithmRuntimeTester test=new AlgorithmRuntimeTester(new BubbleSort());
		
		final XYSeries series2= new XYSeries("MergeSort");
		AlgorithmRuntimeTester test2=new AlgorithmRuntimeTester(new MergeSort());
		
		for (int i = 1; i <= NUM_OF_ITERATIONS; i++) {
			int[] inputArray = arrayGenerator.generate(i*1000);
			series1.add(i*1000, test.run(inputArray).getAverageKeyComparisons());
			series2.add(i*1000, test2.run(inputArray).getAverageKeyComparisons());
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
	}
	
	private JFreeChart createChart(final XYDataset dataset){
		final JFreeChart chart=ChartFactory.createXYLineChart("AVERAGEKEYCOMPARISON", "INPUTSIZE", "AVGKEYCOMPARISON", dataset);
		
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
		setBounds(100, 100, 659, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnNewRadioButton = new JRadioButton("KEYCOMPARISON");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final XYDataset dataset = createDataset();
				final JFreeChart chart = createChart(dataset);
			    ChartFrame frame=new ChartFrame("KEYCOMPARISON", chart);
			    frame.setVisible(true);
			    frame.setSize(750,650);
				
			}
		});
		rdbtnNewRadioButton.setBounds(246, 284, 129, 23);
		contentPane.add(rdbtnNewRadioButton);
	}
}
