package view.produtor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.producao.Ingressos;

public class JListaParticipantePorEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdEvento;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListaParticipantePorEvento frame = new JListaParticipantePorEvento();
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
	public JListaParticipantePorEvento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 12, 915, 75);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblListandoParticipantePor = new JLabel("Listando Participante por Evento");
		lblListandoParticipantePor.setBounds(286, 27, 334, 22);
		lblListandoParticipantePor.setFont(new Font("Dialog", Font.BOLD, 18));
		panelTitulo.add(lblListandoParticipantePor);
		
		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setLayout(null);
		panelPesquisa.setBounds(12, 99, 915, 75);
		contentPane.add(panelPesquisa);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Ingressos ingressosComprado = new Ingressos();
				
				 int numeroConvertido = Integer.parseInt(txtIdEvento.getText());
				 
				 DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel();
				 
				 boolean valorBoleano = ingressosComprado.buscaEventoPorParticipante(numeroConvertido, dtmEventos);
				 
				 if (valorBoleano == false) {
					 JOptionPane.showMessageDialog(btnPesquisar, "Nenhum participante encontrado para este Evento!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				 }

			}
		});
		btnPesquisar.setBounds(25, 24, 117, 25);
		panelPesquisa.add(btnPesquisar);
		
		JLabel lblNumEvento = new JLabel("Nº Evento");
		lblNumEvento.setForeground(new Color(154, 153, 150));
		lblNumEvento.setBounds(160, 26, 77, 20);
		panelPesquisa.add(lblNumEvento);
		
		txtIdEvento = new JTextField();
		txtIdEvento.setBounds(154, 26, 99, 23);
		panelPesquisa.add(txtIdEvento);
		txtIdEvento.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(786, 24, 117, 25);
		panelPesquisa.add(btnVoltar);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(12, 186, 915, 284);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 891, 260);
		panelTabela.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CPF", "E-mail", "N\u00BA Evento", "Nome Evento"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(131);
		table.getColumnModel().getColumn(2).setPreferredWidth(191);
		table.getColumnModel().getColumn(4).setPreferredWidth(271);
		scrollPane.setViewportView(table);
	}
}
