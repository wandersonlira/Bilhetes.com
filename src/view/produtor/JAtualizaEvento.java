package view.produtor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.apache.http.client.ClientProtocolException;

import controller.producao.CepUtils;
import controller.service.ViacepService;
import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.EventosDao;
import model.entidades.TabEndereco;
import model.entidades.TabEventos;

public class JAtualizaEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeEvento;
	private JTextField textFieldData;
	private JTextField textFieldHora;
	private JTextField textFieldCep;
	private JTextField textFieldQtdIngresso;
	private JTextField textFieldCategoria;
	private JTextField textFieldLocal;
	private JTextField textFieldNumLocal;
	private JTextField textFieldProcurar;
	private JTable table;
	
	private TabEndereco enderecoEvento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAtualizaEvento frame = new JAtualizaEvento();
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
	public JAtualizaEvento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelTitulo.setBounds(50, 12, 763, 65);
		contentPane.add(panelTitulo);
		
		JLabel lblAtualizaEvento = new JLabel("Atualize Seu Evento");
		lblAtualizaEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizaEvento.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAtualizaEvento.setBounds(254, 13, 271, 47);
		panelTitulo.add(lblAtualizaEvento);
		
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
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(48, 388, 765, 135);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNomeDoEvento = new JLabel("Nome do Evento");
		lblNomeDoEvento.setBounds(23, 12, 128, 15);
		panelCadastro.add(lblNomeDoEvento);
		
		textFieldNomeEvento = new JTextField();
		textFieldNomeEvento.setBounds(23, 31, 286, 19);
		panelCadastro.add(textFieldNomeEvento);
		textFieldNomeEvento.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(343, 12, 70, 15);
		panelCadastro.add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setBounds(343, 31, 114, 19);
		panelCadastro.add(textFieldData);
		textFieldData.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(487, 12, 70, 15);
		panelCadastro.add(lblHora);
		
		textFieldHora = new JTextField();
		textFieldHora.setBounds(487, 31, 114, 19);
		panelCadastro.add(textFieldHora);
		textFieldHora.setColumns(10);
		
		JLabel lblQtdIngressos = new JLabel("QTD Ingressos");
		lblQtdIngressos.setBounds(619, 12, 114, 15);
		panelCadastro.add(lblQtdIngressos);
		
		textFieldQtdIngresso = new JTextField();
		textFieldQtdIngresso.setBounds(619, 31, 114, 19);
		panelCadastro.add(textFieldQtdIngresso);
		textFieldQtdIngresso.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(23, 75, 70, 15);
		panelCadastro.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(23, 93, 142, 19);
		panelCadastro.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(209, 75, 70, 15);
		panelCadastro.add(lblLocal);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setBounds(207, 93, 250, 19);
		panelCadastro.add(textFieldLocal);
		textFieldLocal.setColumns(10);
		
		JLabel lblNmeroLocal = new JLabel("Número Local");
		lblNmeroLocal.setBounds(487, 75, 109, 15);
		panelCadastro.add(lblNmeroLocal);
		
		textFieldNumLocal = new JTextField();
		textFieldNumLocal.setBounds(487, 93, 114, 19);
		panelCadastro.add(textFieldNumLocal);
		textFieldNumLocal.setColumns(10);
		
		JLabel lblCep = new JLabel("Cep");
		lblCep.setBounds(640, 75, 70, 15);
		panelCadastro.add(lblCep);
		
		textFieldCep = new JTextField();
		textFieldCep.setBounds(640, 93, 93, 19);
		panelCadastro.add(textFieldCep);
		textFieldCep.setColumns(10);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(50, 535, 763, 92);
		contentPane.add(panelBotoes);
		panelBotoes.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { // procedimento para cadastrar novo evento
				
				
				if (table.getSelectedRow() != -1) {
					
					if (!textFieldNomeEvento.getText().isEmpty() || !textFieldNomeEvento.getText().isBlank()
							|| !textFieldData.getText().isEmpty() || !textFieldData.getText().isBlank()
							|| !textFieldHora.getText().isEmpty() || !textFieldHora.getText().isBlank()
							|| !textFieldQtdIngresso.getText().isEmpty() || !textFieldQtdIngresso.getText().isBlank()
							|| !textFieldCategoria.getText().isEmpty() || !textFieldCategoria.getText().isBlank()
							|| !textFieldLocal.getText().isEmpty() || !textFieldLocal.getText().isBlank()
							|| !textFieldNumLocal.getText().isEmpty() || !textFieldNumLocal.getText().isBlank()
							|| !textFieldCep.getText().isEmpty() || !textFieldCep.getText().isBlank()) {
						
						TabEventos novoTabEvento = new TabEventos();
						Date data = new Date();
						SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
						String[] novoEndereco = new String[3];
						
//						Nome Evento
						novoTabEvento.setNomeEvento(textFieldNomeEvento.getText());
						
						try {
//							Data Evento
							data = formatoData.parse(textFieldData.getText());
							novoTabEvento.setDataEvento(data);
						} catch (ParseException el) {
							try {
								data = formatoData.parse(textFieldData.getText().replace("-", "/"));
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							novoTabEvento.setDataEvento(data);
						}
						
						
//						Hora Evento
						novoTabEvento.setHoraEvento(textFieldHora.getText());
						
//						Qtd Ingressos
						novoTabEvento.setIngressos(Integer.parseInt(textFieldQtdIngresso.getText()));
						
//						Categoria
						novoTabEvento.setCategoria(textFieldCategoria.getText());
						
//						---------------- Captura Endereco ---------------
						
//						Local
						novoEndereco[0] =  textFieldLocal.getText();
						
//						Numero Local
						novoEndereco[1] = textFieldNumLocal.getText();

//						CEP
						novoEndereco[2] = textFieldCep.getText();
						novoEndereco[2] = CepUtils.removeMascaraCep(novoEndereco[2]); // Remove o travessão que divide os três últimos digitos do CEP
						CepUtils.validaCep(novoEndereco[2]); // Verifica se está dentro do formato e com 8 digitos
						confereEndereco(novoEndereco); // testa se o endereco existe no banco e cadastra se necessário
						
						novoTabEvento.setIdEvento((Integer) table.getModel().getValueAt(table.getSelectedRow(), 0));
						novoTabEvento.setCodigoEndereco(enderecoEvento);
						
						boolean statusAtualizacao = updateAtualizaEvento(novoTabEvento);
						
						if (statusAtualizacao == true) {
							JOptionPane.showMessageDialog(btnCadastrar, "EVENTO ATUALIZADO!", "ATUALIZADO!", JOptionPane.WARNING_MESSAGE);
							dispose();
							
						} else {
							JOptionPane.showMessageDialog(btnCadastrar, "Houve um erro inesperado... nenhuma linha Atualizada!!", "ATUALIZADO!", JOptionPane.WARNING_MESSAGE);
						}
					
					} else {
						JOptionPane.showMessageDialog(btnCadastrar, "OS CAMPOS NÃO PODEM ESTAR VAZIO", "Aviso!", JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(btnCadastrar, "Nenhuma Linha Selecionada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
					

				
			}
		});
		btnCadastrar.setBounds(23, 25, 117, 25);
		panelBotoes.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(612, 25, 117, 25);
		panelBotoes.add(btnVoltar);
	}
	
	
	private void confereEndereco(String[] novoEndereco) {
		
		EnderecoDao novoEnderecoDao = DaoFactory.createEndereco();
		TabEndereco findEndereco = novoEnderecoDao.findByCep(novoEndereco[2]); // 'novoEndereco[2]' contém a String com o Cep
		
		
		if (findEndereco != null) {
			System.out.println("Diferente de NULL");
			this.enderecoEvento = findEndereco;
			System.out.println("Existe Endereco: " + this.enderecoEvento);
			
		} else {
			System.out.println("Igual a NULL");
			
			try {
				ViacepService findCep = new ViacepService();
	
				this.enderecoEvento = findCep.getEndereco(novoEndereco[2]);
				this.enderecoEvento.setNomeLocal(novoEndereco[0]);
				this.enderecoEvento.setNumLocal(novoEndereco[1]);
	
				novoEnderecoDao.insert(this.enderecoEvento);
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			
			} catch (IOException e) {
				e.printStackTrace();
			
			}
		}
			
	}
	
	
	private boolean updateAtualizaEvento(TabEventos tabEvento) {
		
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos updateTabEvento = new TabEventos();
		
		updateTabEvento.setNomeEvento(tabEvento.getNomeEvento());
		updateTabEvento.setDataEvento(tabEvento.getDataEvento());
		updateTabEvento.setHoraEvento(tabEvento.getHoraEvento());
		updateTabEvento.setIngressos(tabEvento.getIngressos());
		updateTabEvento.setCategoria(tabEvento.getCategoria());
		updateTabEvento.setCodigoEndereco(tabEvento.getCodigoEndereco());
		updateTabEvento.setIdEvento(tabEvento.getIdEvento());
		
		return eventoDao.update(updateTabEvento);
	}
	
	
	
}
