package view.produtor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import controller.producao.CepUtils;
import controller.producao.Eventos;
import model.dao.DaoFactory;
import model.dao.EventosDao;
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
										tabEvento.getCodigoEndereco().getLocalidade() + "/" + tabEvento.getCodigoEndereco().getUf() };
								
								dtmEvento.addRow(dados);
							}
						 
						} catch (NumberFormatException meuTratamento) {
							
								if (tabEvento.getNomeEvento().equals(textFieldProcurar.getText()) == true) {
									
									DefaultTableModel dtmEvento = (DefaultTableModel) table.getModel();
									
									Object[] dados = {tabEvento.getIdEvento(), tabEvento.getNomeEvento(), tabEvento.getDataEvento(),
											tabEvento.getHoraEvento(), tabEvento.getCodigoEndereco().getLogradouro() + ", " + tabEvento.getCodigoEndereco().getNumLocal(),
											tabEvento.getCodigoEndereco().getLocalidade() + "/" + tabEvento.getCodigoEndereco().getUf() };
									
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
				"N\u00BA Evento", "Nome", "Data", "Hora", "Localidade", "Local/UF"
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
					Eventos criaNovoEvento = new Eventos();
					
					System.out.print("Nome Evento: ");
					novoTabEvento.setNomeEvento(textFieldNomeEvento.getText());
					
					try {
						System.out.print("Data Evento: ");
						data = formatoData.parse(textFieldData.getText());
						novoTabEvento.setDataEvento(data);
					} catch (ParseException el) {
						try {
							data = formatoData.parse(textFieldData.getText().replace("-", "/"));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						novoTabEvento.setDataEvento(data);
//						e.printStackTrace();
					}
					
					
					System.out.print("Hora Evento: ");
					novoTabEvento.setHoraEvento(textFieldHora.getText());
					
					System.out.print("Qtd Ingressos: ");
					novoTabEvento.setIngressos(Integer.parseInt(textFieldQtdIngresso.getText()));
					
					System.out.print("Categoria: ");
					novoTabEvento.setCategoria(textFieldCategoria.getText());
					
//					---------------- Captura Endereco ---------------
					
					System.out.print("Local: ");
					novoEndereco[0] =  textFieldLocal.getText();
					
					System.out.print("numeroLocal: ");
					novoEndereco[1] = textFieldNumLocal.getText();

					System.out.print("CEP: ");
					novoEndereco[2] = textFieldCep.getText();
					novoEndereco[2] = CepUtils.removeMascaraCep(novoEndereco[2]); // Remove o travessão que divide os três últimos digitos do CEP
					CepUtils.validaCep(novoEndereco[2]); // Verifica se está dentro do formato e com 8 digitos
					
					criaNovoEvento.criarEvento(novoTabEvento, novoEndereco);
					
					JOptionPane.showMessageDialog(btnCadastrar, "EVENTO CADASTRADO!", "Aviso!", JOptionPane.WARNING_MESSAGE);
					dispose();
					
					
				} else {
					JOptionPane.showMessageDialog(btnCadastrar, "OS CAMPOS NÃO PODEM ESTAR VAZIO", "Aviso!", JOptionPane.WARNING_MESSAGE);
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
}
