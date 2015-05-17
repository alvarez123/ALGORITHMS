package algorithmcomparison.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import algorithmcomparison.algorithms.BubbleSort;
import algorithmcomparison.algorithms.MergeSort;
import algorithmcomparison.generator.RandomArrayGenerator;
import algorithmcomparison.runtimetest.AlgorithmRuntimeTester;
import algorithmcomparison.runtimetest.RuntimeStatistics;

public class Graph extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RandomArrayGenerator arrayGenerator = new RandomArrayGenerator();
	public ArrayList<XYDataset> datasets= new ArrayList<XYDataset>();
	private static final int NUM_OF_ITERATIONS = 10;
	private JButton btnNewButton;
	private JPanel panel1;

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
	
	private ArrayList<XYDataset> createDataset()
	{
		final XYSeries series1 = new XYSeries("BubbleSort");
		final XYSeries series3 = new XYSeries("BubbleSort");
		AlgorithmRuntimeTester test=new AlgorithmRuntimeTester(new BubbleSort());
		
		final XYSeries series2= new XYSeries("MergeSort");
		final XYSeries series4=new XYSeries("MergeSort");
		AlgorithmRuntimeTester test2=new AlgorithmRuntimeTester(new MergeSort());
		
		for (int i = 1; i <= NUM_OF_ITERATIONS; i++) {
			int[] inputArray = arrayGenerator.generate(i*1000);
			RuntimeStatistics st1=test.run(inputArray);
			RuntimeStatistics st2=test2.run(inputArray);
			
			series1.add(i*1000, st1.getAverageKeyComparisons());
			series2.add(i*1000, st2.getAverageKeyComparisons());
			//series3.add(i*1000, st1.getAverageRuntime());
			series4.add(i*1000, st2.getAverageRuntime());
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        final XYSeriesCollection dataset2 = new XYSeriesCollection();
        dataset2.addSeries(series3);
        dataset2.addSeries(series4);
        
        datasets.add(dataset);
        datasets.add(dataset2);
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
		setBounds(100, 100, 870, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("RUNTIME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final XYDataset dataset = createDataset().get(0);
				final JFreeChart chart = createChartKey(dataset);
				
				 ChartPanel chartPanel = new ChartPanel(chart);
				 panel1.removeAll();
				 panel1.add(chartPanel);
				 panel1.validate();
			    /*ChartFrame frame=new ChartFrame("KEYCOMPARISON", chart);
			    frame.setVisible(true);
			    frame.setSize(750,650);*/
			    
			    final XYDataset dataset2 = datasets.get(1);
			    datasets.clear();
				final JFreeChart chart2 = createChartRunTime(dataset2);
				
			     
			    //ChartPanel chartPanel2 = new ChartPanel(chart2);
			    
			    ChartFrame frame2=new ChartFrame("RUNTIME", chart2); 
			    frame2.setVisible(true);
			    frame2.setSize(750,650);
			}
		});
		btnNewButton.setBounds(354, 441, 119, 35);
		contentPane.add(btnNewButton);
		
		panel1 = new JPanel();
		panel1.setBounds(10, 11, 823, 399);
		contentPane.add(panel1);
	}
}
