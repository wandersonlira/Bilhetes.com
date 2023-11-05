package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.participante.JMenuParticipante;
import view.produtor.JMenuProdutor;

public class JTelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTelaInicial frame = new JTelaInicial();
					frame.setLocationRelativeTo(frame); // Centralizar
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
	public JTelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 12, 416, 72);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblBemvindoBilhetecom = new JLabel("Bem-vindo ao Bilhete.com");
		lblBemvindoBilhetecom.setFont(new Font("Dialog", Font.BOLD, 19));
		lblBemvindoBilhetecom.setBounds(60, 22, 305, 15);
		panelTitulo.add(lblBemvindoBilhetecom);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 96, 416, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnProdutor = new JButton("Sou Produtor");
		btnProdutor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				JMenuProdutor  jMenuProdutor = new JMenuProdutor();
				jMenuProdutor.setLocationRelativeTo(btnProdutor);
				jMenuProdutor.setVisible(true);
				
			}
		});
		btnProdutor.setBounds(139, 0, 156, 25);
		panel.add(btnProdutor);
		
		JButton btnParticipante = new JButton("Sou Participante");
		btnParticipante.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				JMenuParticipante jMenuParticipante = new JMenuParticipante();
				jMenuParticipante.setLocationRelativeTo(btnParticipante);
				jMenuParticipante.setVisible(true);
				
			}
		});
		btnParticipante.setBounds(139, 37, 156, 25);
		panel.add(btnParticipante);
	}
}
