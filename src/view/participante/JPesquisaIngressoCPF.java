package view.participante;

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

public class JPesquisaIngressoCPF extends JFrame {

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
					JPesquisaIngressoCPF frame = new JPesquisaIngressoCPF();
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
	public JPesquisaIngressoCPF() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1072, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 12, 1038, 75);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblListandoParticipantePor = new JLabel("Pesquise Seu Ingresso");
		lblListandoParticipantePor.setBounds(396, 28, 334, 22);
		lblListandoParticipantePor.setFont(new Font("Dialog", Font.BOLD, 18));
		panelTitulo.add(lblListandoParticipantePor);
		
		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setLayout(null);
		panelPesquisa.setBounds(12, 99, 1038, 75);
		contentPane.add(panelPesquisa);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Ingressos ingressosComprado = new Ingressos();
				 
				 DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel();
				 
				 boolean valorBoleano = ingressosComprado.buscaParticipantePorEvento(txtIdEvento.getText(), dtmEventos);
				 
				 if (valorBoleano == false) {
					 JOptionPane.showMessageDialog(btnPesquisar, "Nenhum ingresso encontrado para este CPF!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				 }

			}
		});
		btnPesquisar.setBounds(25, 24, 117, 25);
		panelPesquisa.add(btnPesquisar);
		
		JLabel lblNumEvento = new JLabel("Nº CPF");
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
		panelTabela.setBounds(12, 186, 1038, 284);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 1014, 260);
		panelTabela.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00BA Ingresso", "Participante", "CPF", "E-mail", "Evento", "Rua", "N\u00BA", "Bairro", "Cidade/UF"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(51);
		table.getColumnModel().getColumn(1).setPreferredWidth(185);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(134);
		table.getColumnModel().getColumn(4).setPreferredWidth(171);
		table.getColumnModel().getColumn(5).setPreferredWidth(128);
		table.getColumnModel().getColumn(6).setPreferredWidth(58);
		table.getColumnModel().getColumn(7).setPreferredWidth(97);
		scrollPane.setViewportView(table);
	}
}
