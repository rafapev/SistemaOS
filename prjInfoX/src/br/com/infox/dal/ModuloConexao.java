package br.com.infox.dal;


import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

	/**
	 * Método responsável pela conexão com o banco de dados
	 * @return conexao
	 */
	public static Connection conector() {
		java.sql.Connection conexao = null;
		//a linha abaixo chama o drive importado para a biblioteca
		String driver = "com.mysql.cj.jdbc.Driver";
		//armazenando informa��es referente ao banco
		String url = "jdbc:mysql://127.0.0.1:3306/dbinfox?characterEncoding=utf-8";
		String user = "dba";
		String password = "123@Rafael";
		//estabelecendo conex�o com o banco de dados
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		}catch(Exception e) {
			return null;
		}
	}
}
