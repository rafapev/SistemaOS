package br.com.infox.tela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import br.com.infox.dal.ModuloConexao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblData;
	public JMenuItem menuCadUsu;
	public JMenuItem menuRelServ;
	JMenuItem menuRelClientes;
	JMenu menuRel;
	JMenu menuCad;
	private JLabel lblUsuario;
	private JDesktopPane desktop;
	
	Connection conexao = null;
	

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//as linhas abaixo substituem a label data pela data atual do sistema
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
				lblData.setText(formatador.format(data));
			}
		});
		
		setSize(new Dimension(1000, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		menuCad = new JMenu("Cadastro");
		menu.add(menuCad);
		
		JMenuItem menuCadCli = new JMenuItem("Cliente");
		menuCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando a tela cliente
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				desktop.add(cliente);
			}
		});
		menuCadCli.setHorizontalAlignment(SwingConstants.LEFT);
		menuCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		menuCad.add(menuCadCli);
		
		JMenuItem menuCadOs = new JMenuItem("OS");
		menuCadOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando Tela OS
				TelaOs os = new TelaOs();
				os.setVisible(true);
				desktop.add(os);
			}
		});
		menuCadOs.setHorizontalAlignment(SwingConstants.LEFT);
		menuCadOs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
		menuCad.add(menuCadOs);
		
		menuCadUsu = new JMenuItem("Usuários");
		menuCadUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//o código abaixo abre o form tela usuário dentro do desktop pane
				TelaUsuario usuario = new TelaUsuario();
				usuario.setVisible(true);
				desktop.add(usuario);
			}
		});
		menuCadUsu.setEnabled(false);
		menuCadUsu.setHorizontalAlignment(SwingConstants.LEFT);
		menuCadUsu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		menuCad.add(menuCadUsu);
		
		menuRel = new JMenu("Relatórios");
		menu.add(menuRel);
		
		menuRelClientes = new JMenuItem("Clientes");
		menuRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conexao = ModuloConexao.conector();
								
				//gerando relatório de clientes
				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desse relatório ?",
						"Atenção", JOptionPane.YES_NO_OPTION);
				
				if (confirma == JOptionPane.YES_OPTION) {
					//imprimindo relatório com o framework JasperReport
					try {
						
						JasperPrint print = JasperFillManager.fillReport
								("C:\\Users\\Rafael\\JaspersoftWorkspace\\MyReports\\clientes.jasper", new HashMap<>(), conexao);
						// a linha abaixo exibe o relatório através da classe JasperViewer
						JasperViewer.viewReport(print, false);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
				}
			}
		});
		menuRelClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
		menuRel.add(menuRelClientes);
		
		menuRelServ = new JMenuItem("Serviços");
		menuRelServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conexao = ModuloConexao.conector();
				
				//gerando relatório de os
				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desse relatório ?",
						"Atenção", JOptionPane.YES_NO_OPTION);
				
				if (confirma == JOptionPane.YES_OPTION) {
					//imprimindo relatório com o framework JasperReport
					try {
						
						JasperPrint print = JasperFillManager.fillReport
								("C:\\Users\\Rafael\\JaspersoftWorkspace\\MyReports\\servicos.jasper", new HashMap<>(), conexao);
						// a linha abaixo exibe o relatório através da classe JasperViewer
						JasperViewer.viewReport(print, false);
						
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, e3);
					}
				}
				
				
			}
		});
		menuRelServ.setEnabled(false);
		menuRelServ.setHorizontalAlignment(SwingConstants.LEFT);
		menuRelServ.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		menuRel.add(menuRelServ);
		
		JMenu menuAju = new JMenu("Ajuda");
		menu.add(menuAju);
		
		JMenuItem menuAjuSobr = new JMenuItem("Sobre");
		menuAjuSobr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando a tela sobre
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
			}
		});
		menuAjuSobr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		menuAjuSobr.setHorizontalAlignment(SwingConstants.LEFT);
		menuAju.add(menuAjuSobr);
		
		JMenu menuOpc = new JMenu("Opções");
		menu.add(menuOpc);
		
		JMenuItem menuOpcSair = new JMenuItem("Sair");
		menuOpcSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//exibe uma caixa de dialogo
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair ", "Atenção", JOptionPane.YES_NO_OPTION);
				if(sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		menuOpcSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		menuOpcSair.setHorizontalAlignment(SwingConstants.LEFT);
		menuOpc.add(menuOpcSair);
		
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(64, 128, 128));
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktop, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblData)
						.addComponent(lblUsuario)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblUsuario)
					.addGap(38)
					.addComponent(lblData)
					.addGap(442))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	public JLabel getLblData() {
		return lblData;
	}
	public JMenuItem getMenuCadUsu() {
		return menuCadUsu;
	}
	public JMenuItem getMenuRelServ() {
		return menuRelServ;
	}
	public JMenu getMenuRel() {
		return menuRel;
	}
	public JMenu getMenuCad() {
		return menuCad;
	}
	public JLabel getLblUsuario() {
		return lblUsuario;
	}
	public JDesktopPane getDesktop() {
		return desktop;
	}
	public JMenuItem getMenuRelClientes() {
		return menuRelClientes;
	}
}
