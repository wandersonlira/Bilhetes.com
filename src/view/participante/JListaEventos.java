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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.producao.Eventos;

public class JListaEventos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private static Integer linhaIdEvento;
	private static DefaultTableModel dtmEventos;

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListaEventos frame = new JListaEventos();
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
	public JListaEventos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 989, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(12, 10, 955, 83);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblListaDeEventos = new JLabel("Lista de Eventos");
		lblListaDeEventos.setFont(new Font("Dialog", Font.BOLD, 20));
		lblListaDeEventos.setBounds(390, 30, 238, 15);
		panelTitulo.add(lblListaDeEventos);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBounds(12, 105, 955, 282);
		contentPane.add(panelTabela);
		panelTabela.setLayout(null);
		
		JScrollPane scrollPaneTabela = new JScrollPane();
		scrollPaneTabela.setBounds(12, 12, 931, 258);
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
		panel.setBounds(12, 396, 955, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Logica para comprar ingresso
				
				if (table.getSelectedRow() != -1) {
					
					dtmEventos = (DefaultTableModel) table.getModel();
					
					linhaIdEvento = (Integer) dtmEventos.getValueAt(table.getSelectedRow(), 0);			
					
					dispose();
					JCadastroParticipante jCadastroParticipante = new JCadastroParticipante();
					jCadastroParticipante.setLocationRelativeTo(btnProximo);
					jCadastroParticipante.setVisible(true);
					System.out.println("ListaEvento: " + getLinhaIdEvento());
					

				} else {
					JOptionPane.showMessageDialog(btnProximo, "Nenhuma Linha Selecionada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnProximo.setBounds(398, 12, 117, 25);
		panel.add(btnProximo);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(826, 51, 117, 25);
		panel.add(btnSair);
		
//		Inserindo dados na tebela
		DefaultTableModel dtmEventos = (DefaultTableModel) table.getModel();
		Eventos.exibirEventos(dtmEventos);

		
	}

	public static Integer getLinhaIdEvento() {
		return linhaIdEvento;
	}

	public static void setLinhaIdEvento(Integer linhaIdEvento) {
		JListaEventos.linhaIdEvento = linhaIdEvento;
	}

	public static DefaultTableModel getDtmEventos() {
		return dtmEventos;
	}

	public static void setDtmEventos(DefaultTableModel dtmEventos) {
		JListaEventos.dtmEventos = dtmEventos;
	}
	
	
	
}
