package systemUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JanelaAviso extends JFrame {

	private JLabel rotulo;
	private String mensagem;
	private ImageIcon fundo;
	private JLabel lblFundo;

	public JanelaAviso() {

		this.setLayout(null);
		this.setSize(558, 191);
		this.setLocation(200, 200);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		this.setTitle("Atenção!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/Icone PILARES.png"));
		
		fundo = new ImageIcon("./src/imagens/atencao.jpg");
		this.lblFundo = new JLabel(fundo);

		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblFundo.setBounds(0, 0, 558, 191);
		getContentPane().add(lblFundo);
		this.lblFundo.setBackground(Color.WHITE);

		this.rotulo = new JLabel();
		this.rotulo.setBounds(50, 75, 500, 100);
		this.rotulo.setFont(new Font("Arial", Font.BOLD, 16));
		this.lblFundo.add(rotulo);
		this.setVisible(false);
	}

	public void exibirMensagem(String mensagem) {
		this.mensagem = mensagem;
		this.rotulo.setText(mensagem);
		this.setVisible(true);
	}
	
}
