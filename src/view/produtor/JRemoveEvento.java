package view.produtor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.exception.DbException;
import model.dao.DaoFactory;
import model.dao.EventosDao;
import model.entidades.TabEventos;

public class JRemoveEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProcurar;
	private JTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRemoveEvento frame = new JRemoveEvento();
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
	public JRemoveEvento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelTitulo.setBounds(50, 12, 763, 65);
		contentPane.add(panelTitulo);
		
		JLabel lblRemoveEvento = new JLabel("Remove Evento");
		lblRemoveEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveEvento.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRemoveEvento.setBounds(254, 13, 271, 47);
		panelTitulo.add(lblRemoveEvento);
		
		JLabel lblOlProdutor = new JLabel("Olá, produtor! ");
		lblOlProdutor.setBounds(12, 13, 115, 15);
		panelTitulo.add(lblOlProdutor);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(12, 79, 846, 304);
		contentPane.add(panelTable);
		panelTable.setLayout(null);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!textFieldProcurar.getText().isEmpty() || !textFieldProcurar.getText().isBlank()) {
					
					EventosDao eventoDao = DaoFactory.createEventos();
					List<TabEventos> listTabEvento = eventoDao.findAll();
					
					for (TabEventos tabEvento : listTabEvento) {
						
						try {
							
						 if (tabEvento.getIdEvento() == Integer.parseInt(textFieldProcurar.getText())) {
								
								DefaultTableModel dtmEvento = (DefaultTableModel) table.getModel();
								
								Object[] dados = {tabEvento.getIdEvento(), tabEvento.getNomeEvento(), tabEvento.getDataEvento(),
										tabEvento.getHoraEvento(), tabEvento.getCodigoEndereco().getLogradouro() + ", " + tabEvento.getCodigoEndereco().getNumLocal(),
										tabEvento.getCodigoEndereco().getBairro(), tabEvento.getCodigoEndereco().getLocalidade() + "/" + tabEvento.getCodigoEndereco().getUf() };
								
								dtmEvento.addRow(dados);
							}
						 
						} catch (NumberFormatException meuTratamento) {
							
								if (tabEvento.getNomeEvento().equals(textFieldProcurar.getText()) == true) {
									
									DefaultTableModel dtmEvento = (DefaultTableModel) table.getModel();
									
									Object[] dados = {tabEvento.getIdEvento(), tabEvento.getNomeEvento(), tabEvento.getDataEvento(),
											tabEvento.getHoraEvento(), tabEvento.getCodigoEndereco().getLogradouro() + ", " + tabEvento.getCodigoEndereco().getNumLocal(),
											tabEvento.getCodigoEndereco().getBairro(), tabEvento.getCodigoEndereco().getLocalidade() + "/" + tabEvento.getCodigoEndereco().getUf() };
									
									dtmEvento.addRow(dados);
								}
								
						}
						
					}
					
				} else {
					System.out.println("Campo Vazio!");
				}
				
			}
		});
		btnProcurar.setBounds(12, 22, 117, 25);
		panelTable.add(btnProcurar);
		
		JLabel lblNEventoOu = new JLabel("Nº evento ou Nome");
		lblNEventoOu.setForeground(new Color(154, 153, 150));
		lblNEventoOu.setBounds(159, 27, 150, 15);
		panelTable.add(lblNEventoOu);
		
		textFieldProcurar = new JTextField();
		textFieldProcurar.setBounds(153, 22, 228, 25);
		panelTable.add(textFieldProcurar);
		textFieldProcurar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 822, 222);
		panelTable.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00BA Evento", "Nome", "Data", "Hora", "Localidade", "Bairro", "Local/UF"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(205);
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(4).setPreferredWidth(240);
		table.getColumnModel().getColumn(5).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(50, 395, 763, 92);
		contentPane.add(panelBotoes);
		panelBotoes.setLayout(null);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { // procedimento para cadastrar novo evento
				
				
				if (table.getSelectedRow() != -1) {
					
					if (JOptionPane.showConfirmDialog(btnRemover, "Deseja Remover Evento? ", "Remover", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						EventosDao eventoDao = DaoFactory.createEventos();
						DefaultTableModel dtmRemoveEvento = (DefaultTableModel) table.getModel();
						Integer linhaRemoveId = (Integer) dtmRemoveEvento.getValueAt(table.getSelectedRow(), 0);
						
						try{
							eventoDao.deleteById(linhaRemoveId);
							
							JOptionPane.showMessageDialog(btnRemover, 
									"\nNº Evento: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 0)
									+ "\nEvento: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 1)
									+ "\nData: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 2)
									+ "\nHora: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 3)
									+ "\nRua: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 4)
									+ "\nBairro: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 5)
									+ "\nCidade: " + dtmRemoveEvento.getValueAt(table.getSelectedRow(), 6), 
									"REMOVIDO!", JOptionPane.WARNING_MESSAGE);
									dispose();
							
						} catch (DbException exceptionErroRemove) {
							JOptionPane.showMessageDialog(btnRemover, "NÂO foi possível REMOVER o Evento! \n\nMOTIVO: possui participantes cadastrados.", 
									"ATENÇÂO", JOptionPane.WARNING_MESSAGE);
									dispose();
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(btnRemover, "Nenhuma Linha Selecionada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnRemover.setBounds(23, 25, 117, 25);
		panelBotoes.add(btnRemover);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(612, 25, 117, 25);
		panelBotoes.add(btnVoltar);
	}	
	
	
}
