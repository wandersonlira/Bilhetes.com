package view.produtor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JOpcaoProdutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOpcaoProdutor frame = new JOpcaoProdutor();
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
	public JOpcaoProdutor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 12, 416, 54);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 23));
		lblMenu.setBounds(162, 12, 110, 30);
		panelTitulo.add(lblMenu);
		
		JLabel lblOlProdutor = new JLabel("Olá, produtor!");
		lblOlProdutor.setBounds(12, 13, 110, 15);
		panelTitulo.add(lblOlProdutor);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 78, 416, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCadastrarEvento = new JButton("Cadastrar Evento");
		btnCadastrarEvento.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				JCadastroEvento jCadastroEvento = new JCadastroEvento();
				jCadastroEvento.setLocationRelativeTo(btnCadastrarEvento);
				jCadastroEvento.setVisible(true);
			}
		});
		btnCadastrarEvento.setBounds(120, 15, 181, 25);
		panel.add(btnCadastrarEvento);
		
		JButton btnListarEvento = new JButton("Listar Evento");
		btnListarEvento.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				JListaParticipantePorEvento jListaParticipantePorEvento = new JListaParticipantePorEvento();
				jListaParticipantePorEvento.setLocationRelativeTo(btnCadastrarEvento);
				jListaParticipantePorEvento.setVisible(true);
			}
		});
		btnListarEvento.setBounds(120, 52, 181, 25);
		panel.add(btnListarEvento);
	}

}
