package view.participante;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

public class JCadastroParticipante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCPF;
	private JTextField textFieldEmail;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastroParticipante frame = new JCadastroParticipante();
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
	public JCadastroParticipante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTitulo.setBounds(0, 0, 839, 57);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblCadastroParticipante = new JLabel("Cadastro Participante");
		lblCadastroParticipante.setFont(new Font("Dialog", Font.BOLD, 30));
		lblCadastroParticipante.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroParticipante.setBounds(222, 12, 376, 44);
		panelTitulo.add(lblCadastroParticipante);
		
		JLabel lblOlParticipante = new JLabel("Olá, Participante!");
		lblOlParticipante.setBounds(22, 12, 134, 15);
		panelTitulo.add(lblOlParticipante);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(0, 63, 851, 127);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(22, 12, 40, 15);
		panelCadastro.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(22, 30, 289, 19);
		panelCadastro.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(349, 12, 70, 15);
		panelCadastro.add(lblCpf);
		
		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(349, 30, 134, 19);
		panelCadastro.add(textFieldCPF);
		textFieldCPF.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(519, 12, 70, 15);
		panelCadastro.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(519, 30, 295, 19);
		panelCadastro.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(22, 82, 117, 25);
		panelCadastro.add(btnCadastrar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(179, 82, 117, 25);
		panelCadastro.add(btnRemover);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(333, 82, 117, 25);
		panelCadastro.add(btnAtualizar);
		
		JButton btnPrximo = new JButton("Próximo");
		btnPrximo.setBounds(697, 82, 117, 25);
		panelCadastro.add(btnPrximo);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(10, 202, 829, 161);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 805, 137);
		panelTabela.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CPF", "E-mail"
			}
		));
		scrollPane.setViewportView(table);
	}
}
