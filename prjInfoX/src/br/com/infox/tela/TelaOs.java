package br.com.infox.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import br.com.infox.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaOs extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textOs;
	private JTextField textData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textPesquisar;
	private JTextField textId;
	private JTable tableClientes;
	private JTextField textEquipamento;
	private JTextField textDefeito;
	private JTextField textServicos;
	private JTextField textTecnico;
	private JTextField textValorTotal;

	// conexões
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private String tipo;
	private JRadioButton rdbtnOrcamento;
	private JRadioButton rdbtnOs;
	private JComboBox<String> comboBoxSituacao;
	private JButton btnAdicionar;
	private JButton btnPesquisar;
	private JButton btnImprimir;
	private JButton btnAlterar;
	private JButton btnExcluir;

	/**
	 * Create the frame.
	 */
	public TelaOs() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				// Ao abrir o form a opção tipo "Orçamento" selecionará automaticamente
				rdbtnOrcamento.setSelected(true);
				tipo = "Orçamento";

			}
		});
		setTitle("OS");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 790, 513);

		conexao = ModuloConexao.conector();

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblNewLabel_2 = new JLabel("Situação");

		comboBoxSituacao = new JComboBox<String>();
		comboBoxSituacao.setModel(new DefaultComboBoxModel<String>(new String[] { " ", "Entrega Ok", "Orçamento Reprovado",
				"Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Na bancada", "Retornou" }));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblNewLabel_5 = new JLabel("* Equipamento");

		JLabel lblNewLabel_5_1 = new JLabel("* Defeito");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel_5_2 = new JLabel("Servico");
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel_5_3 = new JLabel("Técnico");
		lblNewLabel_5_3.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel_5_4 = new JLabel("Valor Total");
		lblNewLabel_5_4.setHorizontalAlignment(SwingConstants.RIGHT);

		textEquipamento = new JTextField();
		textEquipamento.setColumns(10);

		textDefeito = new JTextField();
		textDefeito.setColumns(10);

		textServicos = new JTextField();
		textServicos.setColumns(10);

		textTecnico = new JTextField();
		textTecnico.setColumns(10);

		textValorTotal = new JTextField();
		textValorTotal.setText("0");
		textValorTotal.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chamar o metodo emitir os
				emitirOs();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infox/icone/Add2.png")));

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chamando o método pesquisarOs
				pesquisarOs();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infox/icone/search2.png")));

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// chamando o metodo alterar os
				alterarOs();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infox/icone/editar.png")));

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Chamando método para excluir OS
				excluirOs();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infox/icone/Delete2.png")));

		btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando método para imprimir
				
				imprimirOs();
			}
		});
		btnImprimir.setEnabled(false);
		btnImprimir.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infox/icone/print.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(
														groupLayout.createSequentialGroup().addComponent(lblNewLabel_2)
																.addGap(18).addComponent(comboBoxSituacao, 0,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_1,
												GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_5_1, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_5_2, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_5_3, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_5_4, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textValorTotal, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textDefeito)
														.addComponent(textEquipamento, GroupLayout.DEFAULT_SIZE, 294,
																Short.MAX_VALUE)
														.addComponent(textServicos).addComponent(textTecnico,
																GroupLayout.PREFERRED_SIZE, 294,
																GroupLayout.PREFERRED_SIZE))))))
						.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(btnAdicionar).addGap(18)
								.addComponent(btnPesquisar).addGap(18).addComponent(btnAlterar).addGap(18)
								.addComponent(btnExcluir).addGap(18).addComponent(btnImprimir)))
				.addContainerGap(22, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
												.addComponent(comboBoxSituacao, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addGap(37)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_5).addComponent(
						textEquipamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textDefeito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5_1))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textServicos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5_2))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textTecnico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5_3))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5_4))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnAdicionar)
						.addComponent(btnPesquisar).addComponent(btnAlterar).addComponent(btnExcluir)
						.addComponent(btnImprimir))
				.addGap(42)));

		textPesquisar = new JTextField();
		textPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// chamando o metodo pesquisar cliente
				pesquisarCliente();
			}
		});
		textPesquisar.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infox/icone/consultar.png")));

		JLabel lblNewLabel_4 = new JLabel("*ID");

		textId = new JTextField();
		textId.setEditable(false);
		textId.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_3).addGap(112)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textId,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(textPesquisar,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4)
										.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE).addGap(15)));

		tableClientes = new JTable();
		tableClientes.setShowVerticalLines(false);
		tableClientes.setShowHorizontalLines(false);
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// chamando o metodo setarCampos

				setarCampos();
			}
		});
		tableClientes.setShowGrid(false);
		tableClientes.setBorder(null);
		tableClientes.setFillsViewportHeight(true);
		tableClientes.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null }, }, new String[] { "Id", "Nome", "Fone" }) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

		});
		tableClientes.getColumnModel().getColumn(0).setPreferredWidth(23);
		tableClientes.getColumnModel().getColumn(1).setPreferredWidth(187);
		tableClientes.getColumnModel().getColumn(2).setPreferredWidth(105);
		scrollPane.setViewportView(tableClientes);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("N° OS");

		JLabel lblNewLabel_1 = new JLabel("Data:");

		textOs = new JTextField();
		textOs.setEditable(false);
		textOs.setColumns(10);

		textData = new JTextField();
		textData.setEditable(false);
		textData.setColumns(10);

		rdbtnOrcamento = new JRadioButton("Orçamento");
		rdbtnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// atribuir um texto a variavel tipo selecionado

				tipo = "Orçamento";
			}
		});
		buttonGroup.add(rdbtnOrcamento);

		rdbtnOs = new JRadioButton("Ordem de Serviços");
		rdbtnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// atribuir um texto a variavel tipo selecionado
				tipo = "OS";
			}
		});
		buttonGroup.add(rdbtnOs);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textOs, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel).addComponent(rdbtnOrcamento))
						.addGap(12)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnOs).addComponent(lblNewLabel_1))
						.addContainerGap(16, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(lblNewLabel_1))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textOs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnOs)
						.addComponent(rdbtnOrcamento))
				.addContainerGap(11, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	private void pesquisarCliente() {
		String sql = "select idcli as Id, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";

		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, textPesquisar.getText() + "%");
			rs = pst.executeQuery();
			tableClientes.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void setarCampos() {
		int setar = tableClientes.getSelectedRow();
		textId.setText(tableClientes.getModel().getValueAt(setar, 0).toString());
	}

	// metodo para cadastrar
	private void emitirOs() {
		String sql = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico, valor, idcli) values(?,?,?,?,?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.setString(2, comboBoxSituacao.getSelectedItem().toString());
			pst.setString(3, textEquipamento.getText());
			pst.setString(4, textDefeito.getText());
			pst.setString(5, textServicos.getText());
			pst.setString(6, textTecnico.getText());
			pst.setString(7, textValorTotal.getText().replace(",", "."));// substituir "," pelo "."
			pst.setString(8, textId.getText());

			// validação dos campos obrigatórios
			if (textId.getText().isEmpty() || textDefeito.getText().isEmpty() || textEquipamento.getText().isEmpty()
					|| comboBoxSituacao.getSelectedItem().equals(" ")) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
			} else {
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
					//recuperar o numero da Os
					recuperarOs();
					btnAdicionar.setEnabled(false);
					btnPesquisar.setEnabled(false);
					btnImprimir.setEnabled(true);

				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void pesquisarOs() {
		String numeroOs = JOptionPane.showInputDialog("Numero da OS");
		String sql = "select os, date_format(data_os,'%d/%m/%y - %H:%i'), "
				+ "tipo, situacao,equipamento,defeito,servico,tecnico,valor,idcli from tbos where os = " + numeroOs;
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				textOs.setText(rs.getString(1));
				textData.setText(rs.getString(2));
				// setando os radio buttom
				String rbtTipo = rs.getString(3);
				if (rbtTipo.equals("OS")) {
					rdbtnOs.setSelected(true);
					tipo = "OS";
				} else {
					rdbtnOrcamento.setSelected(true);
					tipo = "Orcamento";
				}
				comboBoxSituacao.setSelectedItem(rs.getString(4));
				textEquipamento.setText(rs.getString(5));
				textDefeito.setText(rs.getString(6));
				textServicos.setText(rs.getString(7));
				textTecnico.setText(rs.getString(8));
				textValorTotal.setText(rs.getString(9));
				textId.setText(rs.getString(10));

				// desativando os botões na pesquisa
				btnAdicionar.setEnabled(false);
				// btnPesquisar.setEnabled(false);
				textPesquisar.setEnabled(false);
				tableClientes.setVisible(false);
				// ativar demais botões
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnImprimir.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "OS nao cadastrada");
			}
		} catch (SQLSyntaxErrorException e) {
			JOptionPane.showMessageDialog(null, "OS invalida");
			// System.out.println(e);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2);
		}
	}

	private void alterarOs() {
		String sql = "update tbos set tipo = ?, situacao = ?, equipamento = ?, defeito = ?, servico = ?, tecnico = ?, valor = ? where os = ?";

		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.setString(2, comboBoxSituacao.getSelectedItem().toString());
			pst.setString(3, textEquipamento.getText());
			pst.setString(4, textDefeito.getText());
			pst.setString(5, textServicos.getText());
			pst.setString(6, textTecnico.getText());
			pst.setString(7, textValorTotal.getText().replace(",", "."));// substituir "," pelo "."
			pst.setString(8, textOs.getText());

			// validação dos campos obrigatórios
			if (textId.getText().isEmpty() || textDefeito.getText().isEmpty() || textEquipamento.getText().isEmpty()
					|| comboBoxSituacao.getSelectedItem().equals(" ")) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
			} else {
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "OS alterada com sucesso");
					limparCampos();

				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void excluirOs() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa OS ?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String sql = "delete from tbos where os=?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, textOs.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "OS excluida com sucesso");
					limparCampos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	//metodo para imprimir uma os
	private void imprimirOs() {
		conexao = ModuloConexao.conector();
		
		//gerando relatório de os
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desse relatório ?",
				"Atenção", JOptionPane.YES_NO_OPTION);
		
		if (confirma == JOptionPane.YES_OPTION) {
			//imprimindo relatório com o framework JasperReport
			try {
				
				//usando a classe HashMap para criar um filtro
				HashMap<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("os", Integer.parseInt(textOs.getText()));
				
				JasperPrint print = JasperFillManager.fillReport
						("C:\\Users\\Rafael\\JaspersoftWorkspace\\MyReports\\os.jasper", filtro, conexao);
				// a linha abaixo exibe o relatório através da classe JasperViewer
				JasperViewer.viewReport(print, false);
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
			}
		}
	}
	
	//recuperar Os
	
	private void recuperarOs() {
		
		String sql = "select max(os) from tbos";
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				textOs.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void limparCampos() {
		textId.setText(null);
		textEquipamento.setText(null);
		textDefeito.setText(null);
		textServicos.setText(null);
		textTecnico.setText(null);
		textValorTotal.setText(null);
		textOs.setText(null);
		textData.setText(null);
		textPesquisar.setText(null);
		((DefaultTableModel) tableClientes.getModel()).setRowCount(0);
		comboBoxSituacao.setSelectedItem(" ");

		// habilitando os botões
		btnAdicionar.setEnabled(true);
		btnPesquisar.setEnabled(false);
		btnPesquisar.setEnabled(true);
		tableClientes.setVisible(true);

		// desailitando botoes
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnImprimir.setEnabled(false);

	}

	protected JRadioButton getRdbtnOrcamento() {
		return rdbtnOrcamento;
	}

	protected JRadioButton getRdbtnOs() {
		return rdbtnOs;
	}

	protected JComboBox<String> getComboBoxSituacao() {
		return comboBoxSituacao;
	}

	protected JButton getBtnAdicionar() {
		return btnAdicionar;
	}

	protected JButton getBtnPesquisar() {
		return btnPesquisar;
	}

	protected JButton getBtnAlterar() {
		return btnAlterar;
	}

	protected JButton getBtnExcluir() {
		return btnExcluir;
	}

	protected JButton getBtnImprimir() {
		return btnImprimir;
	}
}
