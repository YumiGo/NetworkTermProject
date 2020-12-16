package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.StyleConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ChattingWindow extends JFrame implements MouseListener, ActionListener {

	JFrame frame;
	JTextArea ta_all;// ��ü textarea
	JTextArea ta_mytextarea;// ���� textarea
	Color info_bg = new Color(194, 163, 133);
	Color all_bg = new Color(255, 242, 204);
	Color send_bg = new Color(194, 163, 133);
	Color my_bg = Color.WHITE;
	static String me;
	static String opponent;
	JButton btn_send;

	/**
	 * Create the application.
	 */
	public ChattingWindow(String me, String oppnent) {
		this.me = me;
		this.opponent = oppnent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// optionpane �۲� ����
		UIManager.put("OptionPane.messageFont", new Font("���ʷҵ���", Font.PLAIN, 12));
		UIManager.put("OptionPane.buttonFont", new Font("���ʷҵ���", Font.PLAIN, 12));

		frame = new JFrame();
		frame.setTitle(opponent + " �԰��� 1:1 ��ȭâ");
		frame.setBounds(100, 100, 400, 600);
		frame.setResizable(false);//â ������ ���� ��� false
		frame.setBackground(all_bg);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setBackground(all_bg);
		frame.getContentPane().setLayout(null);

		JPanel panel_info = new JPanel();
		panel_info.setBounds(0, 0, 384, 50);
		panel_info.setBackground(info_bg);
		frame.getContentPane().add(panel_info);
		panel_info.setLayout(null);

		JLabel lbl_usericon = new JLabel();
		lbl_usericon.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_usericon.setIcon(new ImageIcon(ChattingWindow.class.getResource("/project/friend(small_dark).png")));
		lbl_usericon.setBounds(12, 5, 44, 45);
		panel_info.add(lbl_usericon);

		JLabel lbl_yourname = new JLabel(opponent);
		lbl_yourname.setFont(new Font("���ʷҵ���", Font.BOLD, 16));
		lbl_yourname.setBounds(68, 5, 154, 35);
		panel_info.add(lbl_yourname);

		//��ü��ȭâ�� ����(���)
		JScrollPane scrollPane_all_opp = new JScrollPane();
		scrollPane_all_opp.setBorder(null);
		scrollPane_all_opp.setBounds(10, 57, 362, 395);
		scrollPane_all_opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // �ڵ���ũ��
		frame.getContentPane().add(scrollPane_all_opp);

		ta_all = new JTextArea();
		ta_all.setBorder(null);
		ta_all.setBackground(all_bg);
		ta_all.setEditable(false);
		scrollPane_all_opp.setViewportView(ta_all);// scrollPane�� add
		ta_all.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT); //��������
		ta_all.setLineWrap(true);// �ڵ��ٹٲ�
		// ta_all.setPreferredSize(new Dimension(380, 200));//textarea ������ ����

		
		//��ü��ȭâ�� ������(��)
		JScrollPane scrollPane_all_me = new JScrollPane();
		scrollPane_all_me.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_all_me.setBorder(null);
		scrollPane_all_me.setBounds(191, 57, 181, 395);
		frame.getContentPane().add(scrollPane_all_me);
		
		
		//������ ���� �г�
		JPanel panel_msg = new JPanel();
		panel_msg.setBounds(0, 460, 384, 100);
		panel_msg.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_msg);
		panel_msg.setLayout(null);

		btn_send = new JButton("����");
		btn_send.setBackground(send_bg);
		btn_send.setFont(new Font("���ʷҵ���", Font.PLAIN, 12));
		btn_send.setBounds(304, 10, 69, 56);
		panel_msg.add(btn_send);

		JScrollPane scrollPane_my = new JScrollPane();
		scrollPane_my.setBorder(null);
		scrollPane_my.setBounds(12, 10, 280, 80);
		scrollPane_my.setViewportBorder(null);// �׵θ�����
		scrollPane_my.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // �ڵ���ũ��
		panel_msg.add(scrollPane_my);

		ta_mytextarea = new JTextArea();
		scrollPane_my.setViewportView(ta_mytextarea);
		ta_mytextarea.setBackground(my_bg);
		ta_mytextarea.setBorder(null);// textare �׵θ� ����. https://hsp1116.tistory.com/1
		ta_mytextarea.setMinimumSize(new Dimension(10, 24));
		ta_mytextarea.setAlignmentX(Component.LEFT_ALIGNMENT);

		ta_mytextarea.setLineWrap(true);// �ڵ��ٹٲ�
		

	}

	void addLog(String log) //add to my log

	{

		
		// StyleConstants.setAlignment(log, StyleConstants.ALIGN_RIGHT);
		//String.format("%100s", log) 
		log = me + " : " + log;
		//ta_all_me.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //�ؽ�Ʈ �����ʺ��;���
		ta_all.append(log + "\n"); // �α� ������ JTextArea ���� �ٿ��ְ�	
		//ta_all.append("\n"); //�ݴ��� ä�õ� ���� �����.
		ta_all.setCaretPosition(ta_all.getDocument().getLength()); // �ǾƷ��� ��ũ���Ѵ�.

	}

	//��밡 ��������
	void addOpponentLog(String log) {
		ta_all.append(log + "\n"); // �α� ������ JTextArea ���� �ٿ���		
		ta_all.setCaretPosition(ta_all.getDocument().getLength()); // �ǾƷ��� ��ũ���Ѵ�.
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
