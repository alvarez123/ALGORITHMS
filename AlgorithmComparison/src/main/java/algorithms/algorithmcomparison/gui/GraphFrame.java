package algorithms.algorithmcomparison.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYDatasetTableModel;
import org.jfree.data.xy.XYSeries;

import algorithms.algorithmcomparison.algorithms.BubbleSort;
import algorithms.algorithmcomparison.algorithms.MergeSort;
import algorithms.algorithmcomparison.generator.RandomArrayGenerator;
import algorithms.algorithmcomparison.runtimetest.AlgorithmRuntimeTester;
import algorithms.algorithmcomparison.runtimetest.RuntimeStatistics;

public class GraphFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RandomArrayGenerator arrayGenerator = new RandomArrayGenerator();
	public ArrayList<DefaultTableXYDataset> datasets = new ArrayList<DefaultTableXYDataset>();

	private static final int NUM_OF_ITERATIONS = 10;
	private JButton buttonFillTable;
	private JPanel panel;
	private JButton buttonPlotGraph;
	private JTable table;
	private JCheckBox checkbox;
	private boolean enable25000 = false;

	private ArrayList<DefaultTableXYDataset> createDataset() {
		datasets.clear();
		XYSeries series1 = new XYSeries("BubbleAverageKeyComparison", true,
				false);
		XYSeries series2 = new XYSeries("BubbleSTDEV", true, false);
		XYSeries series3 = new XYSeries("BubbleAverageRunTime", true, false);
		XYSeries series4 = new XYSeries("BubbleRuntimeSTDEV", true, false);
		AlgorithmRuntimeTester test = new AlgorithmRuntimeTester(
				new BubbleSort());

		XYSeries series5 = new XYSeries("MergeAverageKeyComparison", true,
				false);
		XYSeries series6 = new XYSeries("MergeSTDEV", true, false);
		XYSeries series7 = new XYSeries("MergeAverageRunTime", true, false);
		XYSeries series8 = new XYSeries("MergeRunTimeSTDEV", true, false);
		AlgorithmRuntimeTester test2 = new AlgorithmRuntimeTester(
				new MergeSort());

		ArrayList<int[]> arrays = new ArrayList<int[]>();
		int limit = (enable25000) ? NUM_OF_ITERATIONS + 1 : NUM_OF_ITERATIONS;
		for (int i = 1; i <= limit; i++) {
			int size = (i == NUM_OF_ITERATIONS + 1) ? 25000 : i * 1000;
			for (int j = 0; j < limit; j++) {
				arrays.add(arrayGenerator.generate(size));
			}
			RuntimeStatistics st1 = test.run(arrays);
			RuntimeStatistics st2 = test2.run(arrays);
			arrays.clear();

			// BUBBLESORT
			series1.add(size, st1.getAverageKeyComparisons());
			series2.add(size, st1.getStDevKeyComparisons());
			series3.add(size, st1.getAverageRuntime());
			series4.add(size, st1.getStDevRuntime());
			// MERGE SORT
			series5.add(size, st2.getAverageKeyComparisons());
			series6.add(size, st2.getStDevKeyComparisons());
			series7.add(size, st2.getAverageRuntime());
			series8.add(size, st2.getStDevRuntime());
		}

		final DefaultTableXYDataset dataset = new DefaultTableXYDataset();
		dataset.addSeries(series1);
		dataset.addSeries(series5);

		final DefaultTableXYDataset dataset2 = new DefaultTableXYDataset();
		dataset2.addSeries(series3);
		dataset2.addSeries(series7);

		final DefaultTableXYDataset dataset3 = new DefaultTableXYDataset();// For
																			// jtable
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

	private JFreeChart createChartKey(final XYDataset dataset) {
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"AVERAGE KEY COMPARISON", "INPUT SIZE", "AVG KEY COMPARISON",
				dataset);

		chart.setBorderPaint(Color.YELLOW);

		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		return chart;

	}

	private JFreeChart createChartRunTime(final XYDataset dataset) {
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"AVERAGE RUNTIME", "INPUTSIZE", "AVG RUNTIME", dataset);

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
	public GraphFrame() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		buttonFillTable = new JButton("FILL TABLE");

		buttonFillTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						GraphFrame.this
								.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						buttonFillTable.setEnabled(false);
						final DefaultTableXYDataset dataset = createDataset()
								.get(2);
						final XYDatasetTableModel tableModel = new XYDatasetTableModel();

						tableModel.setModel(dataset);

						table.setModel(tableModel);
						table.setVisible(true);

						buttonPlotGraph.setEnabled(true);
						buttonFillTable.setEnabled(true);
						GraphFrame.this.setCursor(new Cursor(
								Cursor.DEFAULT_CURSOR));
					}
				}).start();

			}
		});

		buttonFillTable.setBounds(100, 437, 143, 35);
		contentPane.add(buttonFillTable);

		panel = new JPanel();
		panel.setBounds(10, 11, 818, 415);
		table = new JTable();
		table.setPreferredSize(new Dimension(800, 300));
		table.setVisible(false);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(false);

		panel.add(new JScrollPane(table));
		contentPane.add(panel);

		buttonPlotGraph = new JButton("PLOT GRAPH");
		buttonPlotGraph.setEnabled(false);
		buttonPlotGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final DefaultTableXYDataset dataset = datasets.get(0);
				final JFreeChart chart = createChartKey(dataset);

				ChartFrame frame = new ChartFrame("KEY COMPARISON", chart);
				frame.setLocation(new Point(10, 10));
				frame.setVisible(true);
				frame.setSize(750, 650);

				final DefaultTableXYDataset dataset2 = datasets.get(1);
				final JFreeChart chart2 = createChartRunTime(dataset2);

				ChartFrame frame2 = new ChartFrame("RUNTIME", chart2);
				frame2.setLocation(new Point(110, 110));
				frame2.setVisible(true);
				frame2.setSize(750, 650);
				buttonPlotGraph.setEnabled(false);
				datasets.clear();
			}
		});
		buttonPlotGraph.setBounds(600, 437, 143, 35);
		checkbox = new JCheckBox("Enable 25000 Input");
		checkbox.setBounds(300, 437, 143, 35);
		checkbox.setVisible(true);
		checkbox.setMaximumSize(new Dimension(250, 50));
		checkbox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				enable25000 = checkbox.isSelected();
			}
		});
		contentPane.add(checkbox);
		contentPane.add(buttonPlotGraph);
	}
}
