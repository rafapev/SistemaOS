package br.com.infox.tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.infox.dal.ModuloConexao;



public class TelaLogin {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JFrame frmXSystem;
	private JTextField textUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frmXSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
		conexao = ModuloConexao.conector();
		//a linha abaixo serve de apoio ao status da conex�o
		if(conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icone/online.png")));
			
		}else
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icone/offline.png")));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXSystem = new JFrame();
		frmXSystem.setTitle("X System - Login");
		frmXSystem.setResizable(false);
		frmXSystem.setBounds(100, 100, 360, 197);
		frmXSystem.setLocationRelativeTo(null);
		frmXSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Login");

		JLabel lblNewLabel_1 = new JLabel("Senha");

		textUsuario = new JTextField();
		textUsuario.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando o método logar
				logar();
			}
		});

		txtSenha = new JPasswordField();

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/infox/icone/online.png")));
		GroupLayout groupLayout = new GroupLayout(frmXSystem.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, Alignment.LEADING)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(82, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(147)
										.addComponent(btnLogin)
										.addGap(26))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLogin)
								.addComponent(lblStatus))
						.addGap(0))
				);
		frmXSystem.getContentPane().setLayout(groupLayout);
	}
	
	public void logar() {
		String sql = "select * from tbusuarios where login=? and senha=?";
		try {
			//as linhas abaixo preparam a consulta ao banco de dados
			pst = conexao.prepareStatement(sql);
			pst.setString(1, textUsuario.getText());
			String captura = new String(txtSenha.getPassword());
			pst.setString(2, captura);
			//a linha abaixo executa a query
			rs = pst.executeQuery();
			//se existir usuário e senha
			if (rs.next()) {
				// a linha abaixo obtem o conteudo do campo perfil da tabela tbusuarios
				String perfil=rs.getString(6);
				//System.out.println(perfil);
				//a estrutura abaixo faz o tratamento de perfil de usuário
				if(perfil.equals("admin")) {
					//a linha abaixo exibe o conteudo do campo da tabela
					TelaPrincipal principal = new TelaPrincipal();
					principal.setVisible(true);
					principal.menuCadUsu.setEnabled(true);
					principal.menuRelServ.setEnabled(true);
					principal.menuRelClientes.setEnabled(true);
					principal.getLblUsuario().setText(rs.getString(2));
					principal.getLblUsuario().setForeground(Color.blue);
					this.frmXSystem.dispose();
					conexao.close();
				} else {
					TelaPrincipal principal = new TelaPrincipal();
					principal.setVisible(true);
					principal.getLblUsuario().setText(rs.getString(2));
					principal.getLblUsuario().setForeground(Color.blue);
					this.frmXSystem.dispose();
				}

			}else {
				JOptionPane.showMessageDialog(null,"usuário e/ou senha invalido");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
	}
}
