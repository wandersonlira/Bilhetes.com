package view.participante;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.producao.Eventos;
import model.entidades.TabEventos;
import model.entidades.TabParticipantes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JCompraIngresso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private static Integer linhaIdEvento;
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
					JCompraIngresso frame = new JCompraIngresso();
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
	public JCompraIngresso() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 991, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 10, 955, 45);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTelaCompra = new JLabel("Comprando Ingresso");
		lblTelaCompra.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTelaCompra.setBounds(377, 12, 238, 21);
		panelTitulo.add(lblTelaCompra);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(12, 67, 965, 262);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JScrollPane scrollPaneTabela = new JScrollPane();
		scrollPaneTabela.setBounds(12, 12, 931, 238);
		panelTabela.add(scrollPaneTabela);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00BA Evento", "Nome", "Data", "Hora", "Local/UF"
			}
		));
		scrollPaneTabela.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 341, 965, 162);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(25, 37, 40, 15);
		panel.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(28, 58, 356, 19);
		panel.add(textFieldNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(446, 37, 70, 15);
		panel.add(lblCpf);
		
		textFieldCPF = new JTextField();
		textFieldCPF.setColumns(10);
		textFieldCPF.setBounds(446, 58, 134, 19);
		panel.add(textFieldCPF);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(650, 37, 70, 15);
		panel.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(650, 58, 295, 19);
		panel.add(textFieldEmail);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Logica para comprar ingresso
				
				if (table.getSelectedRow() != -1) {
					
//					TabEventos tabEvento = new TabEventos();
					DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel();
					
					linhaIdEvento = (Integer) dtmEventos.getValueAt(table.getSelectedRow(), 0);
//					tabEvento.setIdEvento(linhaIdEvento);
					
					setLinhaIdEvento(linhaIdEvento); // guarda o id selecionado pelo usuário
					
					TabParticipantes novoParticipante = new TabParticipantes();
					novoParticipante.setNomeParticipante(textFieldNome.getText());
					novoParticipante.setCpf(textFieldCPF.getText());
					novoParticipante.setEmail(textFieldEmail.getText());
					
					Eventos evento = new Eventos();
					evento.comprarIngresso(linhaIdEvento, novoParticipante);
					

				} else {
					JOptionPane.showMessageDialog(btnComprar, "Nenhuma Linha Selecionada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnComprar.setBounds(352, 122, 117, 25);
		panel.add(btnComprar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JOpcaoParticipante jOpcaoParticipante = new JOpcaoParticipante();
				jOpcaoParticipante.setLocationRelativeTo(btnSair);
				jOpcaoParticipante.setVisible(true);
			}
		});
		btnSair.setBounds(828, 122, 117, 25);
		panel.add(btnSair);
		
//		Inserindo dados na tebela
		DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel();
		Eventos.exibirEventos(dtmEventos);

		
	}

	public Integer getLinhaIdEvento() {
		return linhaIdEvento;
	}

	public void setLinhaIdEvento(Integer linhaIdEvento) {
		JCompraIngresso.linhaIdEvento = linhaIdEvento;
	}
	
	
	
}
