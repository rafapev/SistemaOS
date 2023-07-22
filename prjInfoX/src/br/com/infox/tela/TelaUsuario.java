package br.com.infox.tela;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.infox.dal.ModuloConexao;

public class TelaUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textUsuarioId;
	private JTextField textUsuarioNome;
	private JTextField textUsuarioFone;
	private JTextField textUsuarioSenha;
	private JTextField textUsuarioLogin;
	//Usando váriável de conexão DAL
	Connection conexao = null;
	//Criando váriáveis especiais para conexao com o banco de dados
	//PreparedStatement e ResultSet são framework do pacote java.sql
	//servem para preparar executar as instruções SQL
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private JComboBox<String> comboBoxPerfil;
	
	/**
	 * Create the frame.
	 */
	public TelaUsuario() {
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setTitle("Usuário");
		setBounds(0, 0, 790, 513);
		
		//chamando o modulo de conexao com o banco de dados 
		conexao = ModuloConexao.conector();
		
		JLabel lblNewLabel = new JLabel("*ID");
		
		textUsuarioId = new JTextField();
		textUsuarioId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("*Nome");
		
		textUsuarioNome = new JTextField();
		textUsuarioNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("*Login");
		
		textUsuarioFone = new JTextField();
		textUsuarioFone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("*Senha");
		
		textUsuarioSenha = new JTextField();
		textUsuarioSenha.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Telefone");
		
		textUsuarioLogin = new JTextField();
		textUsuarioLogin.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("*Perfil");
		
		comboBoxPerfil = new JComboBox<String>();
		comboBoxPerfil.setModel(new DefaultComboBoxModel<String>(new String[] {"admin", "user"}));
		
		JButton btnUsuarioCreate = new JButton("");
		btnUsuarioCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando o método adicionar
				adicionar(); 
			}
		});
		btnUsuarioCreate.setSize(new Dimension(50, 50));
		btnUsuarioCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarioCreate.setToolTipText("Adicionar");
		btnUsuarioCreate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icone/add.png")));
		
		
		JButton btnUsuarioRead = new JButton("");
		btnUsuarioRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando o método consultar
				consultar();
			}
		});
		btnUsuarioRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarioRead.setToolTipText("Consultar");
		btnUsuarioRead.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icone/consultar.png")));
		btnUsuarioRead.setPreferredSize(new Dimension(50, 50));
		
		JButton btnUsuarioUpdate = new JButton("");
		btnUsuarioUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando o método alterar
				alterar();
			}
		});
		btnUsuarioUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarioUpdate.setToolTipText("Editar");
		btnUsuarioUpdate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icone/editar.png")));
		btnUsuarioUpdate.setPreferredSize(new Dimension(50, 50));
		
		JButton btnUsuarioDelete = new JButton("");
		btnUsuarioDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Chamando o metodo remover
				remover();
			}
		});
		btnUsuarioDelete.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icone/delete.png")));
		btnUsuarioDelete.setToolTipText("Apagar");
		btnUsuarioDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarioDelete.setPreferredSize(new Dimension(50, 50));
		
		JLabel lblNewLabel_6 = new JLabel("* Campos Obrigatórios");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textUsuarioId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(101)
							.addComponent(lblNewLabel_6))
						.addComponent(textUsuarioNome, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
						.addComponent(textUsuarioFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textUsuarioSenha, Alignment.LEADING)
										.addComponent(textUsuarioLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
									.addGap(36)
									.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnUsuarioCreate, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(btnUsuarioRead, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(34)
									.addComponent(btnUsuarioUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUsuarioDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(97))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textUsuarioId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textUsuarioNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textUsuarioFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textUsuarioLogin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textUsuarioSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(comboBoxPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnUsuarioDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUsuarioRead, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUsuarioCreate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUsuarioUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(199, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		

	}
	
	private void consultar() {
		String sql = "select * from tbusuarios where iduser=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, textUsuarioId.getText());
			rs = pst.executeQuery();
			
			if(rs.next()) {
				//busca o nome do usuário
				textUsuarioNome.setText(rs.getString(2));
				textUsuarioFone.setText(rs.getString(3));
				textUsuarioLogin.setText(rs.getString(4));
				textUsuarioSenha.setText(rs.getString(5));
				//a linha abaixo se refere ao combo box
				comboBoxPerfil.setSelectedItem(rs.getString(6));
				
			}else {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado");
				//as linhas abaixo limpam os campos
				textUsuarioNome.setText(null);
				textUsuarioFone.setText(null);
				textUsuarioLogin.setText(null);
				textUsuarioSenha.setText(null);
				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void adicionar() {
		String sql = "insert into tbusuarios(iduser, usuario, fone, login, senha, perfil) values (?, ?, ?, ?, ?, ?)";
		try {
			pst = conexao.prepareStatement(sql);
			//as linhas abaixo adicionam os campos do Java no Banco de Dados
			pst.setString(1, textUsuarioId.getText());
			pst.setString(2, textUsuarioNome.getText());
			pst.setString(3, textUsuarioFone.getText());
			pst.setString(4, textUsuarioLogin.getText());
			pst.setString(5, textUsuarioSenha.getText());
			pst.setString(6, comboBoxPerfil.getSelectedItem().toString());
			
			//validação dos campos obrigatórios
			if (textUsuarioId.getText().isEmpty() || textUsuarioNome.getText().isEmpty()
					|| textUsuarioLogin.getText().isEmpty() || textUsuarioSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

			
			// a linha abaixo adiciona e atualiza a tabela usuario com os dados do formulário
			int adicionado = pst.executeUpdate();
			if(adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
				//as linhas abaixo limpam os campos
				limpar();
				
			}
		}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	private void alterar() {
		String sql = "update tbusuarios set usuario = ?, fone = ?, login = ?, senha = ?, perfil = ? where iduser = ?";
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, textUsuarioNome.getText());
			pst.setString(2, textUsuarioFone.getText());
			pst.setString(3, textUsuarioLogin.getText());
			pst.setString(4, textUsuarioSenha.getText());
			pst.setString(5, comboBoxPerfil.getSelectedItem().toString());
			pst.setString(6, textUsuarioId.getText());
			
			//validação dos campos obrigatórios após as alterações dos dados do usuário
			if (textUsuarioId.getText().isEmpty() || textUsuarioNome.getText().isEmpty()
					|| textUsuarioLogin.getText().isEmpty() || textUsuarioSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

			
			// a linha abaixo adiciona e atualiza a tabela usuario com os dados do formulário
			int adicionado = pst.executeUpdate();
			if(adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
				//as linhas abaixo limpam os campos
				limpar();
				
			}
		}
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	private void remover() {
		//a estrutura abaixo confirma a remoção do usuário
		int confirma = JOptionPane.showConfirmDialog(null, "Remover usuário ? ", "Atenção", JOptionPane.YES_NO_OPTION);
		if(confirma ==JOptionPane.YES_OPTION) {
			String sql = "delete from tbusuarios where iduser = ?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, textUsuarioId.getText());
				int apagado = pst.executeUpdate();
				if(apagado > 0) {
					JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
					limpar();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	//método para limpar os campos do formulário e da tabela
	private void limpar() {
		textUsuarioId.setText(null);
		textUsuarioNome.setText(null);
		textUsuarioFone.setText(null);
		textUsuarioLogin.setText(null);
		textUsuarioSenha.setText(null);
	}
	
	public JTextField getTextUsuarioId() {
		return textUsuarioId;
	}
	public JTextField getTextUsuarioNome() {
		return textUsuarioNome;
	}
	public JComboBox<String> getComboBoxPerfil() {
		return comboBoxPerfil;
	}
}
