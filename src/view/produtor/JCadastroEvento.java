package view.produtor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.producao.CepUtils;
import controller.producao.Eventos;
import controller.producao.LeitorTeclado;
import model.entidades.TabEventos;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JCadastroEvento extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastroEvento frame = new JCadastroEvento();
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
	public JCadastroEvento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelTitulo.setBounds(12, 0, 763, 65);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblCadastreSeuEvento = new JLabel("Cadastre Seu Evento");
		lblCadastreSeuEvento.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCadastreSeuEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastreSeuEvento.setBounds(254, 13, 271, 47);
		panelTitulo.add(lblCadastreSeuEvento);
		
		JLabel lblOlProdutor = new JLabel("Olá, produtor! ");
		lblOlProdutor.setBounds(12, 13, 115, 15);
		panelTitulo.add(lblOlProdutor);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(10, 72, 765, 135);
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
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(12, 219, 763, 92);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { // procedimento para cadastrar novo evento
				
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
//					e.printStackTrace();
				}
				
				
				System.out.print("Hora Evento: ");
				novoTabEvento.setHoraEvento(textFieldHora.getText());
				
				System.out.print("Qtd Ingressos: ");
				novoTabEvento.setIngressos(Integer.parseInt(textFieldQtdIngresso.getText()));
				
				System.out.print("Categoria: ");
				LeitorTeclado.clearInput();
				novoTabEvento.setCategoria(textFieldCategoria.getText());
				
//				---------------- Captura Endereco ---------------
				
				System.out.print("Local: ");
				novoEndereco[0] =  textFieldLocal.getText();
				
				System.out.print("numeroLocal: ");
				novoEndereco[1] = textFieldNumLocal.getText();

				System.out.print("CEP: ");
				novoEndereco[2] = textFieldCep.getText();
				novoEndereco[2] = CepUtils.removeMascaraCep(novoEndereco[2]); // Remove o travessão que divide os três últimos digitos do CEP
				CepUtils.validaCep(novoEndereco[2]); // Verifica se está dentro do formato e com 8 digitos
				
				criaNovoEvento.criarEvento(novoTabEvento, novoEndereco);
				
			}
		});
		btnCadastrar.setBounds(23, 25, 117, 25);
		panelTabela.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(183, 25, 117, 25);
		panelTabela.add(btnAtualizar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(342, 25, 117, 25);
		panelTabela.add(btnRemover);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(612, 25, 117, 25);
		panelTabela.add(btnFinalizar);
	}
}
