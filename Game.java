package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JFrame implements Runnable{

	private JPanel contentPane;
	private JTextField textField;
	private Thread thread = new Thread(this);
	private boolean state = false;
	private long totalscore = 0;//number of total score
	private long correctscore = 0;//number of correct
	private long wrongscore = 0;//number of wrong
	private String[] keys={"long","private","blooean","watermelon","sweetmelon","float","extends","implements","double","short","int","switch",
			            "package","import","while",};
	private Vector<String> keyV = new Vector();
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel lblCorrect;
	private JLabel label_1;
	private JLabel lblWrong;
	private JLabel label_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setResizable(false);
		setTitle("Winston Gao TypingGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(38, 19, 375, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(-17, 303, 418, 10);
		panel.add(panel_2);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10){
					String key = (textField.getText());
					JLabel jl = appeared.get(0);
					
					if (jl.getText().equals(key)){
						jl.setForeground(Color.RED);
						totalscore += (298-jl.getY()+10);
						correctscore +=1;
						
					}
					
					else{
						jl.setFont(new Font("",Font.BOLD,30));
						jl.setForeground(Color.RED);
						totalscore -= 200;
                        wrongscore +=1;
					}
					
					System.out.println(jl.getText());
					textField.setText("");
					textField.requestFocus();
					
				}
				
			}
		});
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		textField.setBounds(45, 343, 297, 82);
		panel.add(textField);
		textField.setColumns(10);
		thread.start();
		
	    JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(450, 19, 147, 462);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblNewLabel = new JLabel("Total Score");
		lblNewLabel.setBounds(24, 24, 91, 28);
		panel_1.add(lblNewLabel);
		
		label = new JLabel("0");
		label.setForeground(Color.RED);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(24, 66, 117, 67);
		panel_1.add(label);
		
		lblCorrect = new JLabel("Correct");
		lblCorrect.setBounds(24, 154, 61, 16);
		panel_1.add(lblCorrect);
		
		label_1 = new JLabel("0\n");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label_1.setBounds(24, 197, 61, 67);
		panel_1.add(label_1);
		
		lblWrong = new JLabel("Wrong");
		lblWrong.setBounds(24, 290, 61, 16);
		panel_1.add(lblWrong);
		
	    label_2 = new JLabel("0\n");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label_2.setBounds(24, 318, 61, 55);
		panel_1.add(label_2);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				keyV.addAll(Arrays.asList(keys));
				Collections.shuffle(keyV);//shuttle the key works to have ramdom looking
				
				
				totalscore = 0;
				correctscore = 0;
				wrongscore = 0;
				state = true;
				
				
			}
		});
		btnStart.setBounds(-2, 385, 117, 29);
		panel_1.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = false;
			}
		});
		btnStop.setBounds(-2, 426, 117, 29);
		panel_1.add(btnStop);
	}
	
	
	Vector <JLabel> appeared = new Vector<JLabel>();//record the keywrods that have already appeared;
    JPanel panel = null;
	public void run() {
		/*JLabel jl = new JLabel("I am Java key words");
		jl.setFont(new Font("",Font.BOLD,20));
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setBounds(100,0,200,30);
		panel.add(jl);
		panel.repaint();*/
		
		
		Random r = new Random();
		int y = 0;
		int out = 0; //produce keyword
		int index = 0;
		int num = 0;
		
		while (true){
			
			if (state){
				label.setText(totalscore+"");
				label_1.setText(correctscore+"");
				label_2.setText(wrongscore+"");
				out++;
				if (out == 60+num){
					JLabel jl = new JLabel(keyV.get(index));
					index++;
					if(index>=keyV.size()){
						index = 0;
					}
					jl.setFont(new Font("",Font.BOLD,20));
					jl.setHorizontalAlignment(SwingConstants.CENTER);
					jl.setBounds(r.nextInt(231),0,150,30);
					panel.add(jl);
					panel.repaint();
					appeared.add(jl);
					out = 0;
					num = r.nextInt(40);
					}
				
				
				for (JLabel jLabel : (Vector<JLabel>)appeared.clone()){
					jLabel.setLocation(jLabel.getX(),jLabel.getY()+1);
					
					if(jLabel.getY()+jLabel.getHeight()>=303){
						jLabel.setVisible(false);
						appeared.remove(jLabel);
						panel.remove(jLabel);
						if(!jLabel.getForeground().equals(Color.RED)){
							totalscore -=100;
							wrongscore +=1;
						}
					}
					
					if(jLabel.getForeground().equals(Color.RED)){
						jLabel.setVisible(false);
						appeared.remove(jLabel);
						panel.remove(jLabel);
					}
					
				}
				
				
				
			}
			else {
				if(appeared.size()!=0){
					for(JLabel jLabel : appeared){
						panel.remove(jLabel);
						panel.repaint();
					}
				}
				y = 0;
				out = 0;
				index = 0;
			}
			try{
				Thread.sleep(20-(totalscore/1000));
			}catch (Exception e){
				
			}
			
		}
		
	}
}






