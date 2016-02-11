package systemUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import service.ClienteService;
import service.ContaService;
import dto.ContaDTO;
import entity.TipoConta;
import exception.DaoException;
import exception.ServiceException;

public class JanelaTransacao extends JFrame implements ActionListener {

	private JLabel rotuloDeposito;
	private JLabel rotuloValorDeposito;
	private JLabel rotuloDepositoConta;
	private JLabel rotuloTransferencia;
	private JLabel rotuloValorTrans;
	private JLabel rotuloTransContaDeb;
	private JLabel rotuloTransContaCred;
	private JLabel rotuloTransacoes;
	private JLabel lblFundo;
	private JLabel lblDeposito;
	private JLabel lblTransf;
	private JLabel lblNotas;
	private JLabel lblSetas;
	private JTextField valorDeposito;
	private JTextField depositoConta;
	private JTextField transfContaDeb;
	private JTextField valorTransf;
	private JTextField transfContaCred;
	private ImageIcon fundo;
	private ImageIcon deposito;
	private ImageIcon transf;
	private ImageIcon notas;
	private ImageIcon setas;
	private JButton ok1;
	private JButton ok2;
	private JButton cancel1;
	private JButton cancel2;
	private JButton voltar;
	private JanelaPrincipal j;
	private JanelaAviso javiso;
	private static final int ZERO = 0;
	private static ContaService contaService = new ContaService();
	private static ClienteService clienteService = new ClienteService(
			contaService);

	public JanelaTransacao(JanelaPrincipal janelaPrincipal)
			throws HeadlessException {
		super();
		this.j = janelaPrincipal;
		this.javiso = new JanelaAviso();
		// ADICIONANDO ELEMENTOS AO FORMULÁRIO
		getContentPane().setLayout(null);
		this.setSize(1100, 715);
		this.setLocation(5, 5);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/Icone PILARES.png"));
		this.setTitle("Sistema Banco Pilares - Tela de Transações");

		setDefaultCloseOperation(0);

		// COLOCA E INSTANCIA A IMAGEM DE FUNDO
		fundo = new ImageIcon("./src/imagens/bkg2.jpg");
		this.lblFundo = new JLabel(fundo);

		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblFundo.setBounds(0, 0, 1100, 715);
		getContentPane().add(lblFundo);
		this.lblFundo.setBackground(Color.WHITE);

		deposito = new ImageIcon("./src/imagens/fundo Deposito.png");
		this.lblDeposito = new JLabel(deposito);
		this.lblDeposito.setBounds(200, 250, 700, 180);
		this.lblFundo.add(lblDeposito);

		transf = new ImageIcon("./src/imagens/fundo Transferencia.png");
		this.lblTransf = new JLabel(transf);
		this.lblTransf.setBounds(150, 370, 800, 300);
		this.lblFundo.add(lblTransf);

		this.rotuloDeposito = new JLabel("Depósito");
		this.rotuloDeposito.setBounds(290, 30, 200, 30);
		this.rotuloDeposito.setFont(new Font("Arial", Font.BOLD, 30));
		this.lblDeposito.add(rotuloDeposito);

		this.rotuloDepositoConta = new JLabel("Conta a creditar:");
		this.rotuloDepositoConta.setBounds(325, 80, 200, 20);
		this.rotuloDepositoConta.setFont(new Font("Arial", Font.BOLD, 15));
		this.lblDeposito.add(rotuloDepositoConta);

		this.rotuloValorDeposito = new JLabel("Valor: R$");
		this.rotuloValorDeposito.setBounds(100, 80, 100, 20);
		this.rotuloValorDeposito.setFont(new Font("Arial", Font.BOLD, 15));
		this.lblDeposito.add(rotuloValorDeposito);

		this.valorDeposito = new JTextField();
		this.valorDeposito.setBounds(165, 75, 70, 30);
		this.valorDeposito.setHorizontalAlignment(JTextField.RIGHT);
		this.valorDeposito.setToolTipText("Insira o valor");
		this.lblDeposito.add(valorDeposito);

		this.depositoConta = new JTextField();
		this.depositoConta.setBounds(450, 75, 150, 30);
		this.depositoConta.setToolTipText("Insira a conta");
		this.lblDeposito.add(depositoConta);

		this.rotuloTransferencia = new JLabel("Transferência");
		this.rotuloTransferencia.setBounds(300, 80, 200, 30);
		this.rotuloTransferencia.setFont(new Font("Arial", Font.BOLD, 30));
		this.lblTransf.add(rotuloTransferencia);

		this.rotuloValorTrans = new JLabel("Valor: R$");
		this.rotuloValorTrans.setBounds(123, 160, 150, 30);
		this.rotuloValorTrans.setFont(new Font("Arial", Font.BOLD, 15));
		this.lblTransf.add(rotuloValorTrans);

		this.rotuloTransContaDeb = new JLabel("Conta a debitar:");
		this.rotuloTransContaDeb.setBounds(50, 120, 150, 30);
		this.rotuloTransContaDeb.setFont(new Font("Arial", Font.BOLD, 15));
		this.lblTransf.add(rotuloTransContaDeb);

		this.rotuloTransContaCred = new JLabel("Conta a creditar:");
		this.rotuloTransContaCred.setBounds(475, 120, 150, 30);
		this.rotuloTransContaCred.setFont(new Font("Arial", Font.BOLD, 15));
		this.lblTransf.add(rotuloTransContaCred);

		this.valorTransf = new JTextField();
		this.valorTransf.setBounds(190, 160, 70, 30);
		this.valorTransf.setHorizontalAlignment(JTextField.RIGHT);
		this.valorTransf.setToolTipText("Insira o valor");
		this.lblTransf.add(valorTransf);

		this.transfContaDeb = new JTextField();
		this.transfContaDeb.setBounds(170, 120, 150, 30);
		this.transfContaDeb.setToolTipText("Insira a conta a debitar");
		this.lblTransf.add(transfContaDeb);

		this.transfContaCred = new JTextField();
		this.transfContaCred.setBounds(600, 120, 150, 30);
		this.transfContaCred.setToolTipText("Insira a conta a creditar");
		this.lblTransf.add(transfContaCred);

		notas = new ImageIcon("./src/imagens/Notas.png");
		lblNotas = new JLabel(notas);
		lblNotas.setBounds(30, 0, 120, 78);
		this.lblDeposito.add(lblNotas);

		setas = new ImageIcon("./src/imagens/Setas.png");
		lblSetas = new JLabel(setas);
		lblSetas.setBounds(0, 10, 115, 116);
		this.lblTransf.add(lblSetas);

		this.ok1 = new JButton("OK");
		this.ok1.setBounds(275, 110, 70, 29);
		this.ok1.setBackground(Color.GRAY);
		this.ok1.setForeground(Color.WHITE);
		this.lblDeposito.add(ok1);
		ok1.addActionListener(this);

		this.cancel1 = new JButton("Limpar");
		this.cancel1.setBounds(355, 110, 90, 29);
		this.cancel1.setBackground(Color.GRAY);
		this.cancel1.setForeground(Color.WHITE);
		this.lblDeposito.add(cancel1);
		cancel1.addActionListener(this);

		this.ok2 = new JButton("OK");
		this.ok2.setBounds(320, 178, 70, 29);
		this.ok2.setBackground(Color.GRAY);
		this.ok2.setForeground(Color.WHITE);
		this.lblTransf.add(ok2);
		ok2.addActionListener(this);

		this.cancel2 = new JButton("Limpar");
		this.cancel2.setBounds(400, 178, 90, 29);
		this.cancel2.setBackground(Color.GRAY);
		this.cancel2.setForeground(Color.WHITE);
		this.lblTransf.add(cancel2);
		cancel2.addActionListener(this);

		this.voltar = new JButton("Voltar");
		this.voltar.setBounds(515, 640, 70, 29);
		this.voltar.setBackground(Color.red.darker());
		this.voltar.setForeground(Color.WHITE);
		this.lblFundo.add(voltar);
		voltar.addActionListener(this);

		this.setVisible(true);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent dispara) {
		if (dispara.getSource() == this.ok1) {
			if ((depositoConta.getText().length() == 0) || (valorDeposito.getText().length() == 0)){
				javiso.exibirMensagem("Os valores digitados são inválidos, por favor, tente novamente");
			}
			else {
			ContaDTO contaDTO = new ContaDTO();
			try{
				contaDTO = contaService.buscarConta(Integer.parseInt(this.depositoConta.getText()));
				javiso.exibirMensagem("Depósito efetuado com sucesso!");
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
			}
			catch (NumberFormatException n){
				javiso.exibirMensagem("O valor digitado para a conta é inválido!");
				return;
			}
			try
			{
			contaDTO.setSaldo(contaDTO.getSaldo()+ Float.parseFloat(this.valorDeposito.getText()));
			alterarConta(contaDTO.getCodigo(), contaDTO.getAgencia(),contaDTO.getSaldo(), contaDTO.getTipoConta(),contaDTO.getClienteCpf());
			}
			catch (NumberFormatException n){
				javiso.exibirMensagem("Valor digitado é inválido");
				return;
				}
			}
		}

		if (dispara.getSource() == this.cancel1) {
			this.depositoConta.setText(null);
			this.valorDeposito.setText(null);
		}
		
		if (dispara.getSource() == this.ok2) {
			if ((transfContaDeb.getText().length() == 0) || (transfContaCred.getText().length() == 0) || (valorTransf.getText().length() == 0)){
				javiso.exibirMensagem("Os valores digitados são inválidos, por favor, tente novamente");
			}
			else {
			ContaDTO contaDTO1 = new ContaDTO();
			try {
				contaDTO1 = contaService.buscarConta(Integer.parseInt(transfContaDeb.getText()));
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
				return;
			}
			catch (NumberFormatException n) {
				javiso.exibirMensagem("O valor digitado para a conta a debitar é inválido!");
				return;
			}
			
			ContaDTO contaDTO2 = new ContaDTO();
			try {
				contaDTO2 = contaService.buscarConta(Integer.parseInt(transfContaCred.getText()));
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
				return;
			}
			catch (NumberFormatException n) {
				javiso.exibirMensagem("O valor digitado para a conta a creditar é inválido!");
				return;
			}
			

			try{
				if ((contaDTO1.getSaldo() >= Float.parseFloat(valorTransf.getText())) && (Float.parseFloat(valorTransf.getText()) > ZERO)) {
					contaDTO1.setSaldo(contaDTO1.getSaldo()- Float.parseFloat(valorTransf.getText()));
					contaDTO2.setSaldo(contaDTO2.getSaldo()+ Float.parseFloat(valorTransf.getText()));
					alterarConta(contaDTO1.getCodigo(), contaDTO1.getAgencia(),contaDTO1.getSaldo(), contaDTO1.getTipoConta(),contaDTO1.getClienteCpf());
					alterarConta(contaDTO2.getCodigo(), contaDTO2.getAgencia(),contaDTO2.getSaldo(), contaDTO2.getTipoConta(),contaDTO2.getClienteCpf());
					javiso.exibirMensagem("Transferência efetuada com sucesso!");
				}
			else {
					javiso.exibirMensagem("O valor digitado é superior ao disponível para transferência");
					return;
				}
			}
			catch (NumberFormatException n){
					javiso.exibirMensagem("Valor digitado para a transferência é inválido");
					return;
			}
			}
		}
		
		if (dispara.getSource() == this.cancel2) {
			this.transfContaCred.setText(null);
			this.transfContaDeb.setText(null);
			this.valorTransf.setText(null);
		}
		
		if (dispara.getSource() == this.voltar) {
			this.j.setVisible(true);
			this.dispose();
		}
	}

	public static void alterarConta(int codigo, String agencia, float saldo, TipoConta tipo, long cpf) {
		ContaDTO contaDTO = new ContaDTO(codigo, agencia, saldo, tipo, cpf);
		try {
			contaService.alterarConta(contaDTO);
		} catch (ServiceException e) {
			//System.out.println(e.getTipo());
		} catch (DaoException e) {
			//System.out.println(e.getMessage());
		}
	}
}
