package view.participante;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.JTelaInicial;
import java.awt.Font;

public class JMenuParticipante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuParticipante frame = new JMenuParticipante();
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
	public JMenuParticipante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 466);
		setLocationRelativeTo(contentPane);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(12, 12, 890, 32);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 914, 21);
		panelMenu.add(menuBar);
		
		JMenu mnEvento = new JMenu("Evento");
		menuBar.add(mnEvento);
		
		JMenu mnNovo = new JMenu("Novo");
		mnEvento.add(mnNovo);
		
		JMenuItem mntmComprarIngresso = new JMenuItem("Comprar Ingresso");
		mntmComprarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JCompraIngresso jCompraIngresso = new JCompraIngresso();
				jCompraIngresso.setLocationRelativeTo(mntmComprarIngresso);
				jCompraIngresso.setVisible(true);
				
			}
		});
		mnNovo.add(mntmComprarIngresso);
		
		JMenuItem mntmVerEventos = new JMenuItem("Ver Eventos");
		mntmVerEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JListaEventos jListaEventos = new JListaEventos();
				jListaEventos.setLocationRelativeTo(mntmVerEventos);
				jListaEventos.setVisible(true);
				
			}
		});
		mnEvento.add(mntmVerEventos);
		
		JMenu mnParticipante = new JMenu("Participante");
		menuBar.add(mnParticipante);
		
		JMenu mnMeuIngresso = new JMenu("Meu Ingresso");
		mnParticipante.add(mnMeuIngresso);
		
		JMenuItem mntmVerIngresso = new JMenuItem("Ver Ingresso");
		mnMeuIngresso.add(mntmVerIngresso);
		
		JMenuItem mntmRevomerIngresso = new JMenuItem("Revomer Ingresso");
		mntmRevomerIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JRemoveIngresso jPesquisaIngressoID = new JRemoveIngresso();
				jPesquisaIngressoID.setLocationRelativeTo(mntmRevomerIngresso);
				jPesquisaIngressoID.setVisible(true);
				
			}
		});
		mnMeuIngresso.add(mntmRevomerIngresso);
		
		mntmVerIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPesquisaIngressoCPF jPesquisaIngresso = new JPesquisaIngressoCPF();
				jPesquisaIngresso.setLocationRelativeTo(mntmVerIngresso);
				jPesquisaIngresso.setVisible(true);
			}
		});
		
		JMenuItem mntmAtualizarCadastro = new JMenuItem("Atualizar Cadastro");
		mntmAtualizarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JAtualizaCadastro jAtualizaCadastro = new JAtualizaCadastro();
				jAtualizaCadastro.setLocationRelativeTo(mntmAtualizarCadastro);
				jAtualizaCadastro.setVisible(true);
			}
		});
		mnParticipante.add(mntmAtualizarCadastro);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSaibaMais = new JMenuItem("Saiba Mais");
		mnSobre.add(mntmSaibaMais);
		
		
		
		JPanel panelBtnMenuPrincipal = new JPanel();
		panelBtnMenuPrincipal.setBounds(212, 56, 460, 245);
		contentPane.add(panelBtnMenuPrincipal);
		panelBtnMenuPrincipal.setLayout(null);
		
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
		btnMenuInicial.setBounds(156, 92, 167, 41);
		panelBtnMenuPrincipal.add(btnMenuInicial);
		
		
	}
}
