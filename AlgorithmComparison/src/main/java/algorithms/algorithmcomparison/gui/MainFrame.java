package algorithms.algorithmcomparison.gui;

import java.awt.Button;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algorithms.algorithmcomparison.runtimetest.CorrectnessTest;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					new MainFrame().setVisible(true);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		
		super("Algorithms Project 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1));
		
		Button button1 = new Button("Runtime and Key Comparison Statistics");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GraphFrame().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		final Button button2 = new Button("Correctness Test");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						MainFrame.this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						button2.setEnabled(false);
						new CorrectnessTest().test();
						button2.setEnabled(true);
						MainFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						JOptionPane.showMessageDialog(MainFrame.this, "Algorithms are tested and results are written into output file.");
					}
				}).start();
			}
		});
		
		contentPane.add(button1);
		contentPane.add(button2);
	}

}
