package view.participante;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.producao.Eventos;
import model.dao.DaoFactory;
import model.dao.EventosDao;
import model.entidades.TabEventos;
import model.entidades.TabParticipantes;

public class JCadastroParticipante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCPF;
	private JTextField textFieldEmail;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panelCadastro.setBounds(12, 98, 827, 127);
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
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!textFieldNome.getText().isEmpty() || !textFieldNome.getText().isBlank()
						|| !textFieldCPF.getText().isEmpty() || !textFieldCPF.getText().isBlank()
						|| !textFieldEmail.getText().isEmpty() || !textFieldEmail.getText().isBlank()) {
					
					TabParticipantes novoParticipante = new TabParticipantes();
					novoParticipante.setNomeParticipante(textFieldNome.getText());
					novoParticipante.setCpf(textFieldCPF.getText());
					novoParticipante.setEmail(textFieldEmail.getText());
					
					Integer idEventoEscolhido = JListaEventos.getLinhaIdEvento(); // Usando método Get statico da classe "JListaEventos"
					
					Eventos evento = new Eventos();
					boolean statusCompra = evento.comprarIngresso(idEventoEscolhido, novoParticipante);
					
					if (statusCompra == true) {
						
						EventosDao eventoDao = DaoFactory.createEventos();
						TabEventos tabEventos = new TabEventos();
						
						tabEventos = eventoDao.findById(idEventoEscolhido);
						
						JOptionPane.showMessageDialog(btnComprar, 
									"\nEvento: " + tabEventos.getNomeEvento()
								+	"\nRua: " + tabEventos.getCodigoEndereco().getLogradouro()
								+	", " + tabEventos.getCodigoEndereco().getNumLocal()
								+	"\nBairro: " + tabEventos.getCodigoEndereco().getBairro()
								+	"\nCidade: " + tabEventos.getCodigoEndereco().getLocalidade()
								+	"/" + tabEventos.getCodigoEndereco().getUf()
								+	"\nParticipante: " + novoParticipante.getNomeParticipante(), 
								"COMPRADO!", JOptionPane.WARNING_MESSAGE);
								dispose();
						
					} else {
						JOptionPane.showMessageDialog(btnComprar, "--- INGRESSO ESGOTADO! ---", "Aviso!", JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(btnComprar, "--- DADOS NÃO PODEM ESTAR VAZIO! ---", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnComprar.setBounds(22, 82, 117, 25);
		panelCadastro.add(btnComprar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(697, 82, 117, 25);
		panelCadastro.add(btnCancelar);
	}
}
