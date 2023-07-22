package br.com.infox.tela;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import br.com.infox.dal.ModuloConexao;
//A linha abaixo importa os recursos do rsx2ml.jar 
import net.proteanit.sql.DbUtils;

public class TelaCliente extends JInternalFrame {
	/**
	 * @
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textClienteNome;
	private JTextField textClienteFone;
	private JTextField textClienteEndereco;
	private JTextField textClienteEmail;
	private JTextField textPesquisar;
	private JTable table;
	private JTable tableClientes_1;

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField textClienteId;
	private JButton btnClienteAdd;


	/**
	 * Create the frame.
	 */
	public TelaCliente() {
		setClosable(true);
		setBounds(0, 0, 790, 513);

		// chamando o modulo de conexao com o banco de dados
		conexao = ModuloConexao.conector();

		JLabel lblNewLabel_1 = new JLabel("*Nome");

		textClienteNome = new JTextField();
		textClienteNome.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("*Telefone");

		textClienteFone = new JTextField();
		textClienteFone.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Endereço");

		textClienteEndereco = new JTextField();
		textClienteEndereco.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Email");

		textClienteEmail = new JTextField();
		textClienteEmail.setColumns(10);

		btnClienteAdd = new JButton("");
		btnClienteAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// metodo para adicionar clientes
				adicionar();
			}
		});
		btnClienteAdd.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icone/add.png")));
		btnClienteAdd.setToolTipText("Adicionar");
		btnClienteAdd.setSize(new Dimension(50, 50));

		JButton btnClienteEditar = new JButton("");
		btnClienteEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chamando o método para alterar clientes

				alterar();
			}
		});
		btnClienteEditar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icone/editar.png")));
		btnClienteEditar.setToolTipText("Editar");
		btnClienteEditar.setSize(new Dimension(50, 50));

		JButton btnClienteRemover = new JButton("");
		btnClienteRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// o método abaixo remove o cliente desejado
				remover();
			}
		});
		btnClienteRemover.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icone/delete.png")));
		btnClienteRemover.setToolTipText("Remover");
		btnClienteRemover.setSize(new Dimension(50, 50));

		JLabel lblNewLabel_6 = new JLabel("* Campos Obrigatórios");

		textPesquisar = new JTextField();
		textPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// o evento pesquisa enquanto for digitando no teclado
				pesquisarCliente();
			}
		});
		textPesquisar.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("*Nome");
		lblNewLabel_1_2.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icone/consultar.png")));

		table = new JTable();

		new javax.swing.JTable();

		JLabel lblNewLabel_1_3 = new JLabel("ID");

		textClienteId = new JTextField();
		textClienteId.setEnabled(false);
		textClienteId.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(60)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
										.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 33,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 57,
														Short.MAX_VALUE)
												.addComponent(lblNewLabel_1))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textClienteFone, GroupLayout.PREFERRED_SIZE, 154,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textClienteEmail, GroupLayout.PREFERRED_SIZE, 292,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textClienteNome, GroupLayout.PREFERRED_SIZE, 292,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textClienteEndereco, GroupLayout.PREFERRED_SIZE, 292,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnClienteAdd, GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE)
														.addGap(26)
														.addComponent(btnClienteEditar, GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE)
														.addGap(28).addComponent(btnClienteRemover,
																GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE))))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 33,
														GroupLayout.PREFERRED_SIZE)
												.addGap(42).addComponent(textClienteId, 0, 0, Short.MAX_VALUE))
										.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 129,
												GroupLayout.PREFERRED_SIZE))
								.addGap(455)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(
										groupLayout
										.createSequentialGroup().addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(20)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(table,
								GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel_6)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(24)
												.addComponent(lblNewLabel_1_3))
										.addGroup(groupLayout
												.createSequentialGroup().addGap(18).addComponent(textClienteId,
														GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textClienteNome, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(
												textClienteEndereco, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout
												.createSequentialGroup().addGap(24).addComponent(lblNewLabel_1_1)))
								.addGap(21)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
										.addComponent(textClienteFone, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(24)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1_1_1)
										.addComponent(textClienteEmail, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnClienteAdd, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnClienteEditar, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnClienteRemover, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE))))));
		tableClientes_1 = new javax.swing.JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tableClientes_1.setShowGrid(false);
		scrollPane.setViewportView(tableClientes_1);

		tableClientes_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
					{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "ID", "Nome", "Endereco", "Fone", "Email" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableClientes_1.getColumnModel().getColumn(0).setPreferredWidth(84);

		tableClientes_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Evento que será usado para setar os campos da tabela clicando com o mouse

				setarCampos();
			}
		});
		getContentPane().setLayout(groupLayout);

	}

	private void adicionar() {

		String sql = "insert into tbclientes(nomecli, endcli,fonecli,emailcli) values (?, ?, ?, ?)";
		try {
			pst = conexao.prepareStatement(sql);
			// as linhas abaixo adicionam os campos do Java no Banco de Dados
			pst.setString(1, textClienteNome.getText());
			pst.setString(2, textClienteEndereco.getText());
			pst.setString(3, textClienteFone.getText());
			pst.setString(4, textClienteEmail.getText());

			// validação dos campos obrigatórios
			if (textClienteNome.getText().isEmpty() || textClienteFone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

				// a linha abaixo adiciona e atualiza a tabela usuario com os dados do
				// formulário
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
					// as linhas abaixo limpam os campos
					limpar();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// método para pesquisar clientes pelo nome, utilizando o filtro
	private void pesquisarCliente() {

		String sql = "select idcli as id, nomecli as nome, endcli as endereco, fonecli as fone, "
				+ "emailcli as email from tbclientes where nomecli like ?";

		try {
			pst = conexao.prepareStatement(sql);
			// passando o conteudo da caixa de pequisa para o ?
			// atenção ao % que é a continuação da string SQL
			pst.setString(1, textPesquisar.getText() + "%");
			rs = pst.executeQuery();
			// a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
			tableClientes_1.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// método para selecionar e setar os conteúdos da pesquisa com o conteúdo da
	// tabela de cadastro
	public void setarCampos() {
		int setar = tableClientes_1.getSelectedRow();
		textClienteId.setText(tableClientes_1.getModel().getValueAt(setar, 0).toString());
		textClienteNome.setText(tableClientes_1.getModel().getValueAt(setar, 1).toString());
		textClienteEndereco.setText(tableClientes_1.getModel().getValueAt(setar, 2).toString());
		textClienteFone.setText(tableClientes_1.getModel().getValueAt(setar, 3).toString());
		textClienteEmail.setText(tableClientes_1.getModel().getValueAt(setar, 4).toString());

		// A linha abaixo desabilita o botão adicionar
		btnClienteAdd.setEnabled(false);
	}

	private void alterar() {

		String sql = "update tbclientes set nomecli = ?, endcli = ?, fonecli = ?, emailcli = ? where idcli = ?";

		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, textClienteNome.getText());
			pst.setString(2, textClienteEndereco.getText());
			pst.setString(3, textClienteFone.getText());
			pst.setString(4, textClienteEmail.getText());
			pst.setString(5, textClienteId.getText());

			// validação dos campos obrigatórios após as alterações dos dados do cliente
			if (textClienteNome.getText().isEmpty() || textClienteFone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {
				// a linha abaixo adiciona e atualiza a tabela cliente com os dados do
				// formulário
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
					// as linhas abaixo limpam os campos
					limpar();
					btnClienteAdd.setEnabled(true);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void remover() {
		// a estrutura abaixo confirma a remoção de clientes
		int confirma = JOptionPane.showConfirmDialog(null, "Remover cliente ? ", "Atenção", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String sql = "delete from tbclientes where idcli = ?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, textClienteId.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
					limpar();
					btnClienteAdd.setEnabled(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	// método para limpar os campos da tabela
	
	private void limpar() {
		textPesquisar.setText(null);
		textClienteId.setText(null);
		textClienteNome.setText(null);
		textClienteEndereco.setText(null);
		textClienteFone.setText(null);
		textClienteEmail.setText(null);
		((DefaultTableModel) tableClientes_1.getModel()).setRowCount(0);
	}

	protected JButton getBtnClienteAdd() {
		return btnClienteAdd;
	}
}
