package view.participante;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.ParticipantesDao;
import model.entidades.TabParticipantes;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JAtualizaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTable table;
	private JTextField textFieldPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAtualizaCadastro frame = new JAtualizaCadastro();
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
	public JAtualizaCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1002, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setBounds(12, 12, 968, 117);
		contentPane.add(panelPesquisa);
		panelPesquisa.setLayout(null);
		
		JButton btnPesquisa = new JButton("Pesquisar");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ParticipantesDao participanteDao = DaoFactory.createParticipantes();
				TabParticipantes findParticipante = participanteDao.findByCPF(textFieldPesquisar.getText());
				
				if (findParticipante != null) {
					DefaultTableModel dtmParticipante = (DefaultTableModel) table.getModel();
					
					Object[] dados = {findParticipante.getIdParticipante(), findParticipante.getNomeParticipante(),
							findParticipante.getCpf(), findParticipante.getEmail()
							};
					dtmParticipante.addRow(dados);
					
				} else {
					JOptionPane.showMessageDialog(btnPesquisa, "CPF não encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		
		JLabel lblAtualizandoCadastro = new JLabel("Atualizando Cadastro");
		lblAtualizandoCadastro.setBounds(448, 12, 181, 15);
		panelPesquisa.add(lblAtualizandoCadastro);
		btnPesquisa.setBounds(0, 69, 117, 25);
		panelPesquisa.add(btnPesquisa);
		
		JLabel lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setForeground(new Color(154, 153, 150));
		lblCpf_1.setBounds(136, 74, 70, 15);
		panelPesquisa.add(lblCpf_1);
		
		textFieldPesquisar = new JTextField();
		textFieldPesquisar.setBounds(129, 72, 97, 19);
		panelPesquisa.add(textFieldPesquisar);
		textFieldPesquisar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 135, 968, 175);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CPF", "E-mail"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(37);
		scrollPane.setViewportView(table);
		
		JPanel panelAtualizar = new JPanel();
		panelAtualizar.setBounds(12, 322, 968, 136);
		contentPane.add(panelAtualizar);
		panelAtualizar.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 29, 70, 15);
		panelAtualizar.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(12, 49, 237, 19);
		panelAtualizar.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(295, 29, 70, 15);
		panelAtualizar.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(295, 49, 230, 19);
		panelAtualizar.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() != -1) {
					
					DefaultTableModel dtmParticipante = (DefaultTableModel) table.getModel();
					String nomeParticipanteTabela = (String) dtmParticipante.getValueAt(table.getSelectedRow(), 1);
					String emailParticipanteTabela = (String) dtmParticipante.getValueAt(table.getSelectedRow(), 3);
					
					if (textFieldNome.getText().equals(nomeParticipanteTabela) == false
							|| textFieldEmail.getText().equals(emailParticipanteTabela) == false) {
						
						ParticipantesDao participanteDao = DaoFactory.createParticipantes();
						TabParticipantes participanteAtualizado = new TabParticipantes();
						
						
						participanteAtualizado.setNomeParticipante(textFieldNome.getText());
						participanteAtualizado.setCpf(textFieldPesquisar.getText());
						participanteAtualizado.setEmail(textFieldEmail.getText());
						Integer idParticipanteTabela = (Integer) dtmParticipante.getValueAt(table.getSelectedRow(), 0);
						participanteAtualizado.setIdParticipante(idParticipanteTabela);
						
						boolean statusUpdate = participanteDao.update(participanteAtualizado);
						
						if (statusUpdate == true) {
							
							JOptionPane.showMessageDialog(btnAtualizar, "ANTES: " 
									+ "\nNome:" + nomeParticipanteTabela 
									+ "\nE-mail: " + emailParticipanteTabela
									+ "\n\nDEPOIS: "
									+ "\nNome: " + textFieldNome.getText() 
									+ "\nE-mail: " + textFieldEmail.getText(), "Atualizado!", JOptionPane.WARNING_MESSAGE);
							dispose();
						}
						
					} else {
						JOptionPane.showMessageDialog(btnAtualizar, "Nomes Iguais", "Aviso!", JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(btnAtualizar, "Nenhuma Linha Selecionada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnAtualizar.setBounds(386, 99, 117, 25);
		panelAtualizar.add(btnAtualizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(823, 99, 117, 25);
		panelAtualizar.add(btnCancelar);
	}
}
