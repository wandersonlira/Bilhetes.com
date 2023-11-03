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
import model.dao.DaoFactory;
import model.dao.ParticipanteEventoDao;

public class JPesquisaIngressoID extends JFrame {

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
					JPesquisaIngressoID frame = new JPesquisaIngressoID();
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
	public JPesquisaIngressoID() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1072, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 12, 1038, 45);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblPesqIngressoID = new JLabel("Remover Ingresso");
		lblPesqIngressoID.setBounds(396, 12, 200, 22);
		lblPesqIngressoID.setFont(new Font("Dialog", Font.BOLD, 18));
		panelTitulo.add(lblPesqIngressoID);
		
		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setLayout(null);
		panelPesquisa.setBounds(12, 61, 1038, 59);
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
		
		JLabel lblIdPartEvento = new JLabel("Nº Ingresso");
		lblIdPartEvento.setForeground(new Color(154, 153, 150));
		lblIdPartEvento.setBounds(160, 26, 77, 20);
		panelPesquisa.add(lblIdPartEvento);
		
		txtIdEvento = new JTextField();
		txtIdEvento.setBounds(154, 26, 99, 23);
		panelPesquisa.add(txtIdEvento);
		txtIdEvento.setColumns(10);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(12, 132, 1038, 284);
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
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() != -1) {
					
					DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel(); // Instanciado para referenciar a linha e coluna que será clicada
					
					Integer linhaIdEvento = (Integer) dtmEventos.getValueAt(table.getSelectedRow(), 0); // pega coluna 0 na linha clicada e transforma e Integer
						
						ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
						participanteEventoDao.deleteById(linhaIdEvento);
						
						JOptionPane.showMessageDialog(btnRemover, 
						"\nNº Ingresso: " + dtmEventos.getValueAt(table.getSelectedRow(), 0)
						+ "\nEvento: " + dtmEventos.getValueAt(table.getSelectedRow(), 4)
						+ "\nRua: " + dtmEventos.getValueAt(table.getSelectedRow(), 5) + ", " + dtmEventos.getValueAt(table.getSelectedRow(), 6)
						+ "\nBairro: " + dtmEventos.getValueAt(table.getSelectedRow(), 7)
						+ "\nCIdade: " + dtmEventos.getValueAt(table.getSelectedRow(), 8), 
						"EXCLUÍDO!", JOptionPane.WARNING_MESSAGE);
						dispose();
						
				} else {
					JOptionPane.showMessageDialog(btnRemover, "Nenhuma Linha Selecionada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRemover.setBounds(465, 445, 117, 25);
		contentPane.add(btnRemover);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(933, 445, 117, 25);
		contentPane.add(btnCancelar);
	}
}
