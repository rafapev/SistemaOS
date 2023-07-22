package br.com.infox.tela;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**
 * @author Rafael
 */

public class TelaSobre extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	
	public TelaSobre() {
		setResizable(false);
		setTitle("Tela Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[111px]", "[14px][14px]"));
		
		JLabel lblNewLabel = new JLabel("Sistema para controle de Ordem de Serviços");
		contentPane.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		
		JLabel lblNewLabel_1 = new JLabel("Estudos por Rafael Pereira Vanderlei");
		contentPane.add(lblNewLabel_1, "cell 0 1,growx,aligny top");
	}

}
