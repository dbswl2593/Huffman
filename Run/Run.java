package Run;

import java.awt.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.multi.MultiLabelUI;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import encryption.encryption;
import Coding.Decoding;
import Coding.Encoding;

@SuppressWarnings({ "serial", "unused" })
public class Run extends JFrame {

	public static JProgressBar progressBar = new JProgressBar();
	public static JLabel lblNewLabel_3 = new JLabel("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Run frame = new Run();
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

	public static String Name = ""; // 파일을 열때 그 이름을 저장시켜놓기 위한 전역변수.
	public static String preEncodingPassword = "";
	public static String EncodingPassword = "";
	public static String DecodingPassword = "";
	public String passwordFileName = "압축자료//password.txt";

	public Run() {
		setResizable(false);
		setForeground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\\uAE40\uD638\uC12D\\KakaoTalk_20141027_004423757.jpg"));
		setBackground(Color.DARK_GRAY);
		getContentPane().setBackground(Color.BLACK);
		setFont(new Font("Copperplate Gothic Light", Font.BOLD, 12));
		setTitle("\uC555\uCD95 \uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 605, 618);
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.WHEN_FOCUSED);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("\uBA54\uB274");
		mnMenu.setForeground(Color.WHITE);
		mnMenu.setBackground(Color.BLACK);
		menuBar.add(mnMenu);

		JMenu menu_2 = new JMenu("\uAE30\uB2A5");
		menu_2.setForeground(Color.WHITE);
		menu_2.setBackground(Color.BLACK);
		menuBar.add(menu_2);

		JMenu menu_1 = new JMenu("\uB370\uC774\uD130");
		menu_1.setForeground(Color.WHITE);
		menu_1.setBackground(Color.BLACK);
		menuBar.add(menu_1);

		JMenuItem menuItem_3 = new JMenuItem("\uD5C8\uD504\uB9CC\uD2B8\uB9AC");
		menuItem_3.setForeground(Color.WHITE);
		menuItem_3.setBackground(Color.BLACK);
		menuItem_3
				.setIcon(new ImageIcon(
						Run.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/TreeLeaf.gif")));
		menu_1.add(menuItem_3);

		JLabel lblNewLabel = new JLabel("\uC555\uCD95\uB960");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("양재소슬체S", Font.PLAIN, 12));
		JLabel lblNewLabel_1 = new JLabel(
				"\uC555\uCD95\uC2E4\uD589 \uC2DC\uAC04");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("양재소슬체S", Font.PLAIN, 12));
		JLabel label = new JLabel("\uC555\uCD95\uD574\uC81C \uC2DC\uAC04");
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setFont(new Font("양재소슬체S", Font.PLAIN, 12));
		JLabel lblNewLabel_2 = new JLabel("\uD3C9\uADE0 \uBE44\uD2B8\uC218");
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("양재소슬체S", Font.PLAIN, 12));

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\uC555\uCD95\uB960");
		mntmNewMenuItem_1.setForeground(Color.WHITE);
		mntmNewMenuItem_1.setBackground(Color.BLACK);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = "Result.txt";
				try {
					@SuppressWarnings("resource")
					FileOutputStream fw = new FileOutputStream(str, true);

					File oFile = new File(Name);
					File eFile = new File("압축자료//압축.moon");
					int res = (int) Encoding.zip;
					if (oFile.exists() && eFile.exists()) {
						JOptionPane.showMessageDialog(null, "원본파일 크기 : "
								+ oFile.length() + "bytes\n" + "압축파일 크기 : "
								+ eFile.length() + "bytes\n" + "압축률 : "
								+ Encoding.zip + "%\n" + "압축 시간 : "
								+ Encoding.totalTime + "초\n" + "평균 비트수 : "
								+ Encoding.averagebits + "Bits", "압축률",
								JOptionPane.INFORMATION_MESSAGE);
						fw.write(res);
						fw.write((int) Encoding.totalTime);
						fw.write((int) Encoding.averagebits);
					} else
						JOptionPane.showMessageDialog(null, "압축할 파일을 선택해주세요.",
								"오류", JOptionPane.WARNING_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mntmNewMenuItem_1
				.setIcon(new ImageIcon(
						Run.class
								.getResource("/com/sun/java/swing/plaf/motif/icons/TreeOpen.gif")));
		menu_1.add(mntmNewMenuItem_1);

		JMenu menu = new JMenu("\uC815\uBCF4");
		menu.setForeground(Color.WHITE);
		menu.setBackground(Color.BLACK);
		menuBar.add(menu);

		JMenuItem menuItem_1 = new JMenuItem("\uB3C4\uC6C0\uB9D0");
		menuItem_1.setForeground(Color.WHITE);
		menuItem_1.setBackground(Color.BLACK);
		menuItem_1.setIcon(new ImageIcon(Run.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/menu.gif")));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "도움말\r\n", "도움말",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu.add(menuItem_1);

		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.setForeground(Color.WHITE);
		mntmAbout.setBackground(Color.BLACK);
		mntmAbout
				.setIcon(new ImageIcon(
						Run.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed.gif")));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"제작자 : 김문섭 \r\n버전 : ver 0.1\r\n\r\n학과 : 컴퓨터공학과\r\n학번 : 20110181",
								"프로그램 정보", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu.add(mntmAbout);

		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 21, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -13, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.NORTH, progressBar, 6, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, progressBar, 0, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar, -40,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -300, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 6, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -134, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.NORTH, label, 501, SpringLayout.NORTH, getContentPane());
		getContentPane().setLayout(springLayout);

		JEditorPane editorPane = new JEditorPane();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, editorPane);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, editorPane);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 6, SpringLayout.SOUTH, editorPane);
		springLayout.putConstraint(SpringLayout.NORTH, editorPane, 0,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, editorPane, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, editorPane, -6, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.EAST, editorPane, 0, SpringLayout.EAST, getContentPane());
		editorPane.setEditable(false);
		editorPane.setDropMode(DropMode.INSERT);
		editorPane.getScrollableTracksViewportHeight();
		editorPane.setFont(new Font("돋움체", Font.BOLD, 10));
		getContentPane().add(editorPane);

		JMenuItem menuItem = new JMenuItem("\uC5F4\uAE30");
		menuItem.setForeground(Color.WHITE);
		menuItem.setBackground(Color.BLACK);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(
						"C://Users//김문섭//Downloads//허프만");
				jfc.showDialog(null, "확인");
				FileReader read = null;
				String str = "";
				int data = 0;
				try {
					if (jfc.getSelectedFile() == null)
						return;
					read = new FileReader(jfc.getSelectedFile().toString());
					while (true) {
						try {
							data = read.read();
							if (data == -1)
								break;
							else {
								char data1 = (char) data;
								str = str + data1;
							}
							if (str.length() == 3000)
								break;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				editorPane.setText(str);
				Name = jfc.getSelectedFile().getName();
			}
		});
		menuItem.setIcon(new ImageIcon(
				Run.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif")));
		mnMenu.add(menuItem);

		JMenuItem menuItem_2 = new JMenuItem("\uC800\uC7A5");
		menuItem_2.setForeground(Color.WHITE);
		menuItem_2.setBackground(Color.BLACK);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resultStr = null;
				resultStr = JOptionPane.showInputDialog(null,
						"저장할 파일의 이름을 지정하세요.\r\n(확장자포함)", "저장",
						JOptionPane.INFORMATION_MESSAGE);
				String res = editorPane.getText();
				try {
					char data = 0;
					if (resultStr == null)
						return;
					@SuppressWarnings("resource")
					DataOutputStream fw = new DataOutputStream(
							new FileOutputStream(resultStr));
					for (int i = 0; i < res.length(); i++) {
						data = res.charAt(i);
						fw.write(data);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuItem_2
				.setIcon(new ImageIcon(
						Run.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mnMenu.add(menuItem_2);

		JMenuItem mntmNewMenuItem = new JMenuItem("\uC885\uB8CC");
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setBackground(Color.BLACK);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(getDefaultCloseOperation());
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(Run.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnMenu.add(mntmNewMenuItem);

		Button button = new Button("\uC5F4\uAE30");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 3, SpringLayout.SOUTH, button);
		springLayout.putConstraint(SpringLayout.WEST, button, 0, SpringLayout.WEST, editorPane);
		springLayout.putConstraint(SpringLayout.SOUTH, button, -40, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, -513, SpringLayout.EAST, getContentPane());
		button.setBackground(Color.BLACK);
		button.setForeground(new Color(204, 255, 204));
		button.setFont(new Font("양재소슬체S", Font.BOLD, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser(
						"C://Users//김호섭//Downloads//허프만");
				jfc.showDialog(null, "확인");
				FileReader read = null;
				String str = "";
				int data = 0;
				try {
					if (jfc.getSelectedFile() == null)
						return;
					read = new FileReader(jfc.getSelectedFile().toString());
					while (true) {
						try {
							data = read.read();
							if (data == -1)
								break;
							else {
								char data1 = (char) data;
								str = str + data1;
							}
							if (str.length() == 3000)
								break;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				editorPane.setText(str);
				Name = jfc.getSelectedFile().getName();
			}
		});

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\uD1B5\uACC4");
		mntmNewMenuItem_2.setForeground(Color.WHITE);
		mntmNewMenuItem_2.setBackground(Color.BLACK);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = "Result.txt";
				try {
					@SuppressWarnings("resource")
					FileReader read = new FileReader(str);
					
					int count = 1;
					float sum = 0;
					float sumtime = 0;
					float sumbit = 0;
					float average;
					float averagetime;
					float averagebit;
					String result = "";
					float res1 = (float) read.read();
					float res2 = 0, res3 = 0;
					while (res1 != -1) {
						sum = sum + res1;
						res2 = (float) read.read();
						sumtime = sumtime + res2;
						res3 = (float) read.read();
						sumbit = sumbit + res3;
						result = result + count + "번째 압축파일 압축률 : " + res1
								+ "%   " + "압축에 걸린 시간 : " + res2 + "초   "
								+ "허프만 평균 비트수 : " + res3 + "Bits\n";
						res1 = read.read();
						count++;
					}
					average = sum / (count - 1);
					averagetime = sumtime / (count - 1);
					averagebit = sumbit / (count - 1);
					result = result + "\n" + "평균 압축률 : " + average + "%   "
							+ "평균 압축에 걸린 시간 : " + averagetime + "초   "
							+ "평균 허프만 비트수 : " + averagebit + "Bits\n";
					editorPane.setText(result);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		mntmNewMenuItem_2.setIcon(new ImageIcon(Run.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		menu_1.add(mntmNewMenuItem_2);
		getContentPane().add(button);

		Button button_1 = new Button("\uC800\uC7A5");
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.WEST, button_1, 98, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, button_1, -40,
				SpringLayout.SOUTH, getContentPane());
		button_1.setBackground(Color.BLACK);
		button_1.setForeground(new Color(204, 255, 204));
		button_1.setFont(new Font("양재소슬체S", Font.BOLD, 18));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String resultStr = null;
				resultStr = JOptionPane.showInputDialog(null,
						"저장할 파일의 이름을 지정하세요.\r\n(확장자포함)", "저장",
						JOptionPane.INFORMATION_MESSAGE);
				String res = editorPane.getText();
				try {
					char data = 0;
					if (resultStr == null)
						return;
					@SuppressWarnings("resource")
					DataOutputStream fw = new DataOutputStream(
							new FileOutputStream(resultStr));
					for (int i = 0; i < res.length(); i++) {
						data = res.charAt(i);
						fw.write(data);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(button_1);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, label, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(label);

		Button button_3 = new Button("\uC555\uCD95 \uD574\uC81C");
		springLayout.putConstraint(SpringLayout.NORTH, button_3, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.WEST, button_3, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, button_3, -40,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button_3, -211, SpringLayout.EAST, getContentPane());
		button_3.setBackground(Color.BLACK);
		button_3.setForeground(new Color(204, 255, 204));
		button_3.setFont(new Font("양재소슬체S", Font.BOLD, 18));

		
		class MyThread1 extends Thread {
			File oFile = new File(Run.Name);
			float filesize = oFile.length();

			public void run() {
				for (int i = 1; i < 100; i++) {
					progressBar.setValue((int) ((Encoding.count / filesize) * 100));
				}
			}
		}
		Button button_2 = new Button("\uC555\uCD95 \uC2E4\uD589");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, button_2);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button_2);
		springLayout.putConstraint(SpringLayout.EAST, button_1, -6, SpringLayout.WEST, button_2);
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 6, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, button_2, 0, SpringLayout.WEST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, button_2, -40, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button_2, -300,
				SpringLayout.EAST, getContentPane());
		button_2.setBackground(Color.BLACK);
		button_2.setForeground(new Color(204, 255, 204));
		button_2.setFont(new Font("양재소슬체S", Font.BOLD, 18));
		button_2.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				try {
					if (Name == "")
						return;
					button_3.enable(true);
					button_2.enable(false);
					editorPane.setText("압축실행중.....");
					MyThread1 mt1 = new MyThread1();
					mt1.start();
					Encoding.main(Name);
					editorPane.setText("압축완료.....");
					lblNewLabel_1.setText("  " + Encoding.totalTime + "초");
					lblNewLabel_2.setText(Encoding.averagebits + "Bits");
					lblNewLabel.setText("  " + Encoding.zip + "%");
					label.setText("압축해제 시간");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(button_2);
		getContentPane().add(lblNewLabel_2);

		JMenuItem menuItem_4 = new JMenuItem("\uC555\uCD95\uC2E4\uD589");
		menuItem_4.setForeground(Color.WHITE);
		menuItem_4.setBackground(Color.BLACK);
		menuItem_4.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Name == "")
						return;
					button_3.enable(true);
					button_2.enable(false);
					editorPane.setText("압축실행중.....");
					MyThread1 mt1 = new MyThread1();
					mt1.start();
					Encoding.main(Name);
					editorPane.setText("압축완료.....");
					lblNewLabel_1.setText("  " + Encoding.totalTime + "초");
					lblNewLabel_2.setText(Encoding.averagebits + "Bits");
					lblNewLabel.setText("  " + Encoding.zip + "%");
					label.setText("압축해제 시간");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuItem_4
				.setIcon(new ImageIcon(
						Run.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		menu_2.add(menuItem_4);

		class MyThread2 extends Thread {
			File oFile = new File(Decoding.inputFileName);
			float filesize = oFile.length();
			
			@SuppressWarnings("deprecation")
			public void run() {
				for (int i = 1; i < 100; i++) {
					try {
						progressBar.setValue((int) ((Decoding.decount / filesize) * 100));
						sleep(50);
					}
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("MyThread2 종료");
				stop();
			}
		}
		button_3.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				try {
					@SuppressWarnings("resource")
					DataInputStream passIn = new DataInputStream(
							new FileInputStream(passwordFileName));
					String pass = passIn.readLine();

					if (pass == null) {
						JOptionPane.showMessageDialog(null,
								"비밀번호가 설정되지 않았습니다. 비밀번호를 설정해쥇요.", "비밀번호 설정",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					DecodingPassword = JOptionPane.showInputDialog(null,
							"비밀번호를 입력하세요(숫자 4글자)", "비밀번호입력",
							JOptionPane.INFORMATION_MESSAGE);
					if (DecodingPassword == null)
						return;
					int key;
					key = Integer.parseInt(DecodingPassword);
					int word = 0;
					int result = 0;
					word = Integer.parseInt(pass);
					if (key != word / encryption.SEOP / encryption.MOON) {
						JOptionPane.showMessageDialog(null,
								"비밀번호가 틀렷습니다. 프로그램을 종료 하겠습니다", "비밀번호 오류",
								JOptionPane.WARNING_MESSAGE);
						System.exit(1);
					}
					progressBar.setValue(0);
					int data = 0;
					String str = "";
					MyThread2 mt2 = new MyThread2();
					mt2.start();
					long startTime = System.currentTimeMillis();
					Decoding.main(null);
					@SuppressWarnings("resource")
					FileReader read = new FileReader("압축해제//Huffman.out.txt");
					while (true) {
						data = read.read();
						if (data == -1)
							break;
						else {
							char data1 = (char) data;
							str = str + data1;
						}
					}
					editorPane.setText(str);
					button_2.enable(true);
					button_3.enable(false);
					long endTime = System.currentTimeMillis();
					float totalTime = ((endTime - startTime) / 1000.0f);
					label.setText(totalTime + "초");
					lblNewLabel_1.setText("평균 비트수");
					lblNewLabel_2.setText("압축률");
					lblNewLabel.setText("압축실행 시간");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(button_3);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\uC555\uCD95\uD574\uC81C");
		mntmNewMenuItem_3.setForeground(Color.WHITE);
		mntmNewMenuItem_3.setBackground(Color.BLACK);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					@SuppressWarnings("resource")
					DataInputStream passIn = new DataInputStream(
							new FileInputStream(passwordFileName));
					String pass = passIn.readLine();

					if (pass == null) {
						JOptionPane.showMessageDialog(null,
								"비밀번호가 설정되지 않았습니다. 비밀번호를 설정해쥇요.", "비밀번호 설정",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					DecodingPassword = JOptionPane.showInputDialog(null,
							"비밀번호를 입력하세요(숫자 4글자)", "비밀번호입력",
							JOptionPane.INFORMATION_MESSAGE);
					if (DecodingPassword == null)
						return;
					int key;
					key = Integer.parseInt(DecodingPassword);
					int word = 0;
					int result = 0;
					word = Integer.parseInt(pass);
					if (key != word / encryption.SEOP / encryption.MOON) {
						JOptionPane.showMessageDialog(null,
								"비밀번호가 틀렷습니다. 프로그램을 종료 하겠습니다", "비밀번호 오류",
								JOptionPane.ERROR_MESSAGE);
						System.exit(1);
					}
					progressBar.setValue(0);
					int data = 0;
					String str = "";
					MyThread2 mt2 = new MyThread2();
					mt2.start();
					long startTime = System.currentTimeMillis();
					Decoding.main(null);
					@SuppressWarnings("resource")
					FileReader read = new FileReader("압축해제//Huffman.out.txt");
					while (true) {
						data = read.read();
						if (data == -1)
							break;
						else {
							char data1 = (char) data;
							str = str + data1;
						}
					}
					editorPane.setText(str);
					button_2.enable(true);
					button_3.enable(false);
					long endTime = System.currentTimeMillis();
					float totalTime = ((endTime - startTime) / 1000.0f);
					label.setText(totalTime + "초");
					lblNewLabel_1.setText("평균 비트수");
					lblNewLabel_2.setText("압축률");
					lblNewLabel.setText("압축실행 시간");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		char[] key = new char[10];

		mntmNewMenuItem_3
				.setIcon(new ImageIcon(
						Run.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		menu_2.add(mntmNewMenuItem_3);
		progressBar.setBackground(Color.BLACK);
		progressBar.setValue(50);
		progressBar.setForeground(Color.WHITE);
		getContentPane().add(progressBar);

		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC124\uC815");
		label_1.setBackground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.EAST, label_1, -414, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 6, SpringLayout.EAST, label_1);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 6, SpringLayout.SOUTH, editorPane);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("양재소슬체S", Font.PLAIN, 12));
		label_1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "resource", "deprecation" })
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DataInputStream passIn;
				try {
					passIn = new DataInputStream(
							new FileInputStream(passwordFileName));
					String pass = "";
					pass = passIn.readLine();
					if(pass != null) //이미 비밀번호가 존재한다면
					{
						preEncodingPassword = pass;
						EncodingPassword = JOptionPane.showInputDialog(null,
								"기존의 비밀번호를 입력하세요(숫자 4글자)", "비밀번호입력",
								JOptionPane.INFORMATION_MESSAGE);
						if(EncodingPassword == null)
							return;
						EncodingPassword = encryption.encryption(EncodingPassword);
						if(preEncodingPassword.equals(EncodingPassword))
						{
							EncodingPassword = JOptionPane.showInputDialog(null,
									"비밀번호를 입력하세요(숫자 4글자)", "비밀번호입력",
									JOptionPane.INFORMATION_MESSAGE);
							if(EncodingPassword == null)
								return;
							EncodingPassword = encryption.encryption(EncodingPassword);
						}
						else
						{
							JOptionPane.showMessageDialog(null,
									"비밀번호가 틀렷습니다. 프로그램을 종료 하겠습니다", "비밀번호 오류",
									JOptionPane.ERROR_MESSAGE);
							System.exit(1);
						}
					}
					else
					{
						EncodingPassword = JOptionPane.showInputDialog(null,
								"비밀번호를 입력하세요(숫자 4글자)", "비밀번호입력",
								JOptionPane.INFORMATION_MESSAGE);
						if(EncodingPassword == null)
							return;
						EncodingPassword = encryption.encryption(EncodingPassword);
					}
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DataOutputStream passOut = null;
				try {
					passOut = new DataOutputStream(new FileOutputStream(
							passwordFileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				for (int i = 0; i < Run.EncodingPassword.length(); i++) {
					key[i] = Run.EncodingPassword.charAt(i);
					try {
						passOut.write(key[i]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\uD2B8\uB9AC\uAD6C\uC870 \uBCF4\uAE30");
		label_2.setBackground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.EAST, label_2, -513, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_1, 6, SpringLayout.EAST, label_2);
		springLayout.putConstraint(SpringLayout.WEST, label_2, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 6, SpringLayout.SOUTH, editorPane);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("양재소슬체S", Font.PLAIN, 12));
		getContentPane().add(label_2);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\\uAE40\uBB38\uC12D\\Pictures\\IMG_0002.JPG"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_3.setBackground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, editorPane);
		lblNewLabel_3.setForeground(Color.WHITE);
		getContentPane().add(lblNewLabel_3);
		
		JLabel label_3 = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 3, SpringLayout.SOUTH, button_3);
		springLayout.putConstraint(SpringLayout.SOUTH, label_3, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_3, -6, SpringLayout.WEST, lblNewLabel);
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setIcon(new ImageIcon("C:\\Users\\\uAE40\uBB38\uC12D\\Pictures\\IMG_0002.JPG"));
		springLayout.putConstraint(SpringLayout.EAST, label_3, 0, SpringLayout.EAST, editorPane);
		getContentPane().add(label_3);

	}
}