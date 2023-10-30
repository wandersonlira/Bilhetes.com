package view.participante;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JOpcaoParticipante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOpcaoParticipante frame = new JOpcaoParticipante();
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
	public JOpcaoParticipante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 12, 416, 51);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblOlParticipante = new JLabel("Olá, Participante!");
		lblOlParticipante.setBounds(12, 12, 125, 15);
		panelTitulo.add(lblOlParticipante);
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 23));
		lblMenu.setBounds(181, 12, 119, 27);
		panelTitulo.add(lblMenu);
		
		JPanel panelOpcao = new JPanel();
		panelOpcao.setBounds(12, 75, 416, 102);
		contentPane.add(panelOpcao);
		panelOpcao.setLayout(null);
		
		JButton btnVerEventos = new JButton("Ver Eventos");
		btnVerEventos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				JListaEventos jListaEventos = new JListaEventos();
				jListaEventos.setLocationRelativeTo(panelOpcao);
				jListaEventos.setVisible(true);
			}
		});
		btnVerEventos.setBounds(131, 0, 178, 34);
		panelOpcao.add(btnVerEventos);
		
		JButton btnConsultarIngresso = new JButton("Consultar Ingresso");
		btnConsultarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JPesquisaIngresso jPesquisaIngresso = new JPesquisaIngresso();
				jPesquisaIngresso.setLocationRelativeTo(btnConsultarIngresso);
				jPesquisaIngresso.setVisible(true);
			}
		});
		btnConsultarIngresso.setBounds(131, 46, 178, 44);
		panelOpcao.add(btnConsultarIngresso);
	}

}
