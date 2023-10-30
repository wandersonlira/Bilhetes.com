package view.participante;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.producao.Eventos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JListaEventos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListaEventos frame = new JListaEventos();
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
	public JListaEventos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 10, 955, 83);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblListaDeEventos = new JLabel("Lista de Eventos");
		lblListaDeEventos.setFont(new Font("Dialog", Font.BOLD, 20));
		lblListaDeEventos.setBounds(390, 30, 238, 15);
		panelTitulo.add(lblListaDeEventos);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(12, 105, 955, 282);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JScrollPane scrollPaneTabela = new JScrollPane();
		scrollPaneTabela.setBounds(12, 12, 931, 258);
		panelTabela.add(scrollPaneTabela);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00BA Evento", "Nome", "Data", "Hora", "Local/UF"
			}
		));
		scrollPaneTabela.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 396, 955, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setBounds(398, 12, 117, 25);
		panel.add(btnComprar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JOpcaoParticipante jOpcaoParticipante = new JOpcaoParticipante();
				jOpcaoParticipante.setLocationRelativeTo(btnSair);
				jOpcaoParticipante.setVisible(true);
			}
		});
		btnSair.setBounds(826, 51, 117, 25);
		panel.add(btnSair);
		
//		Inserindo dados na tebela
		DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel();
		 
		Eventos.exibirEventos(dtmEventos);

		
	}
}
