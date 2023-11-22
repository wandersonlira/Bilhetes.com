package view.produtor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.JTelaInicial;

import javax.swing.JButton;
import java.awt.Font;

public class JMenuProdutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuProdutor frame = new JMenuProdutor();
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
	public JMenuProdutor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 466);
		setLocationRelativeTo(contentPane);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(12, 12, 776, 32);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 764, 21);
		panelMenu.add(menuBar);
		
		JMenu mnEvento = new JMenu("Evento");
		menuBar.add(mnEvento);
		
		JMenu mnNovo = new JMenu("Novo");
		mnEvento.add(mnNovo);
		
		JMenuItem menuItemCadastrarEventos = new JMenuItem("Cadastrar eventos");
		menuItemCadastrarEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JCadastroEvento jCadastroEvento = new JCadastroEvento();
				jCadastroEvento.setLocationRelativeTo(menuItemCadastrarEventos);
				jCadastroEvento.setVisible(true);
				
			}
		});
		mnNovo.add(menuItemCadastrarEventos);
		
		JMenuItem mntmListarParticipantes = new JMenuItem("Listar Participantes");
		mntmListarParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				dispose();
				JListaParticipantePorEvento jListaParticipantePorEvento = new JListaParticipantePorEvento();
				jListaParticipantePorEvento.setLocationRelativeTo(mntmListarParticipantes);
				jListaParticipantePorEvento.setVisible(true);
				
			}
		});
		mnEvento.add(mntmListarParticipantes);
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenuItem mntmAtualizarEvento = new JMenuItem("Atualizar Evento");
		mntmAtualizarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JAtualizaEvento jAtualizaEvento = new JAtualizaEvento();
				jAtualizaEvento.setLocationRelativeTo(mntmListarParticipantes);
				jAtualizaEvento.setVisible(true);
			}
		});
		mnEditar.add(mntmAtualizarEvento);
		
		JMenuItem mntmRemoverEvento = new JMenuItem("Remover Evento");
		mntmRemoverEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JRemoveEvento jRemoveEvento = new JRemoveEvento();
				jRemoveEvento.setLocationRelativeTo(mntmListarParticipantes);
				jRemoveEvento.setVisible(true);
				
			}
		});
		mnEditar.add(mntmRemoverEvento);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSaibaMais = new JMenuItem("Saiba Mais");
		mnSobre.add(mntmSaibaMais);
		
		JPanel panelBotao = new JPanel();
		panelBotao.setBounds(153, 117, 441, 118);
		contentPane.add(panelBotao);
		panelBotao.setLayout(null);
		
		JButton btnMenuInicial = new JButton("Menu Inicial");
		btnMenuInicial.setFont(new Font("Dialog", Font.BOLD, 18));
		btnMenuInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTelaInicial jTelaInicial = new JTelaInicial();
				jTelaInicial.setLocationRelativeTo(btnMenuInicial);
				jTelaInicial.setVisible(true);
				dispose();
			}
		});
		btnMenuInicial.setBounds(136, 39, 171, 41);
		panelBotao.add(btnMenuInicial);
		
		
	}	
}
