package systemUI;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import service.ClienteService;
import service.ContaService;
import dto.ClienteDTO;
import dto.ContaDTO;
import entity.EstadoCivil;
import entity.Sexo;
import exception.DaoException;
//import javax.swing.JOptionPane;

/**
 * @author Danyllo, Felipe e Hugo
 * @version 1.0
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
	private JButton btnOk;
	private JButton btnSair;
	private JButton btnListarCadastro;
	private JButton btnTransacoes;
	private JButton btnExcluir;
	private JLabel lblFundo;
	private JLabel rotulo1;
	private JLabel rotulo2;
	private JTextField campo;
	private ImageIcon fundo;
	private String cpf = null;
	private JTable table;
	private JScrollPane scrollPane;
	private static ContaService contaService = new ContaService();
	private static ClienteService clienteService = new ClienteService(contaService);
	//private int contaBotao = 0;
	private JanelaAviso javiso;
	//private static final int UM = 1;
	private static final int COLUNA_CPF = 3;
	private static final int COLUNA_CONTA = 6;
	private static final int QTD_COLUNAS = 8;
	private static final long CPF_NULO = 0;
	private ProgramTeste pt;
	

	/**
	 * @param btnOk
	 * @param lblFundo
	 * @param fundo
	 * @throws HeadlessException
	 */

	public JanelaPrincipal(ProgramTeste pt) {
		super();
		this.pt = pt;
		// ADICIONANDO ELEMENTOS AO FORMULÁRIO
		this.setLayout(null);
		this.setSize(1100, 715);
		this.setLocation(5, 5);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		this.setTitle("Sistema Banco Pilares - Tela Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/Icone PILARES.png"));
		setDefaultCloseOperation(0);
		// COLOCA E INSTANCIA A IMAGEM DE FUNDO
		fundo = new ImageIcon("./src/imagens/bkg.jpg");
		this.lblFundo = new JLabel(fundo);

		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblFundo.setBounds(0, 0, 1100, 715);
		this.add(lblFundo);
		this.lblFundo.setBackground(Color.WHITE);

		// POSICIONANDO BOTÕES E RÓTULOS
		this.btnOk = new JButton("OK");
		this.btnOk.setBounds(632, 300, 55, 29);
		this.btnOk.setBackground(Color.GRAY);
		this.btnOk.setForeground(Color.WHITE);
		this.lblFundo.add(btnOk);
		btnOk.addActionListener(this);
		
		this.btnSair = new JButton("Sair do Sistema");
		this.btnSair.setBounds(810, 70, 200, 29);
		this.btnSair.setBackground(Color.red.darker());
		this.btnSair.setForeground(Color.WHITE);
		this.lblFundo.add(btnSair);
		btnSair.addActionListener(this);

		this.btnTransacoes = new JButton("Transações");
		this.btnTransacoes.setBounds(475, 620, 155, 29);
		this.btnTransacoes.setBackground(Color.GRAY);
		this.btnTransacoes.setForeground(Color.WHITE);
		this.lblFundo.add(btnTransacoes);
		btnTransacoes.addActionListener(this);
		btnTransacoes.setEnabled(false);

		this.btnExcluir = new JButton("Excluir Linha");
		this.btnExcluir.setBounds(80, 620, 155, 29);
		this.btnExcluir.setBackground(Color.GRAY);
		this.btnExcluir.setForeground(Color.WHITE);
		this.lblFundo.add(btnExcluir);
		btnExcluir.addActionListener(this);
		btnExcluir.setEnabled(false);
		
		this.btnListarCadastro = new JButton("Listar Cadastros");
		this.btnListarCadastro.setBounds(475, 360, 155, 29);
		this.btnListarCadastro.setBackground(Color.GRAY);
		this.btnListarCadastro.setForeground(Color.WHITE);
		this.lblFundo.add(btnListarCadastro);
		btnListarCadastro.addActionListener(this);

		try {
			MaskFormatter formatar = new MaskFormatter("###.###.###-##");
			this.campo = new JFormattedTextField(formatar);
			this.campo.setBounds(475, 300, 155, 30);
			this.campo.setToolTipText("Insira o CPF do cliente");
			this.lblFundo.add(campo);
		} catch (Exception e) {
		}

		this.rotulo1 = new JLabel("Insira o CPF do cliente");
		this.rotulo1.setBounds(475, 260, 200, 50);
		this.lblFundo.add(rotulo1);

		this.rotulo2 = new JLabel("ou liste todos os cadastros");
		this.rotulo2.setBounds(475, 320, 200, 50);
		this.lblFundo.add(rotulo2);

		// POSICIONANDO A GRID
		scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 400, 940, 212);
		scrollPane.setVisible(false);
		this.lblFundo.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Nome", "Sobrenome", "Sexo", "CPF", "Estado Civil", "Endereço",
				"Conta", "Saldo" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(300);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		//this.table.setEnabled(false);
		
		this.setVisible(true);
		this.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent dispara) {
		Collection<ClienteDTO> clientes = null;
		Collection<Integer> codContas = null;

		if (dispara.getSource() == this.btnOk) {
			cpf = campo.getText();
			cpf = cpf.replace(".", "").trim();
			cpf = cpf.replace("-", "").trim();

			if ((cpf.isEmpty()) || (cpf.length() < 11)) {
				cpf = ("Sem cadastro");
				new JanelaCadastro(this, cpf);
				JanelaAviso javiso = new JanelaAviso();
				javiso.exibirMensagem("O CPF digitado é inválido!");
			}
			if (cpf.length() == 11) {
				this.campo.setText(cpf);
				this.cpf = campo.getText();
				new JanelaCadastro(this, cpf);
			}
			this.setVisible(false);
			this.btnListarCadastro.setEnabled(true);
		}

		if (dispara.getSource() == this.btnListarCadastro) {
			JanelaAviso javiso = new JanelaAviso();
			DefaultTableModel model = (DefaultTableModel) this.table.getModel();

			if (table.getRowCount() > 0) {
				while (table.getRowCount() > 0)
					model.removeRow(0);
			}

			this.btnListarCadastro.setEnabled(false);
			scrollPane.setVisible(true);
			btnTransacoes.setEnabled(true);
			btnExcluir.setEnabled(true);
			ContaDTO contaDTO = new ContaDTO();

			try {
				clientes = (Collection<ClienteDTO>) clienteService.buscarclientesDTO();
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
			}

			for (ClienteDTO clienteDTO : clientes) {
				String sexo = null;
				String estadoCivil = null;
				if (clienteDTO.getSexo() == Sexo.MASCULINO) {
					sexo = "MASCULINO";
					if (clienteDTO.getEstadoCivil() == EstadoCivil.SOLTEIRO) {
						estadoCivil = "SOLTEIRO";
					} else {
						if (clienteDTO.getEstadoCivil() == EstadoCivil.CASADO) {
							estadoCivil = "CASADO";
						} else {
							if (clienteDTO.getEstadoCivil() == EstadoCivil.DIVORCIADO) {
								estadoCivil = "DIVORCIADO";
							} else {
								estadoCivil = "OUTROS";
							}
						}
					}
				} else {
					if (clienteDTO.getSexo() == Sexo.FEMININO) {
						sexo = "FEMININO";
						if (clienteDTO.getEstadoCivil() == EstadoCivil.SOLTEIRO) {
							estadoCivil = "SOLTEIRA";
						} else {
							if (clienteDTO.getEstadoCivil() == EstadoCivil.CASADO) {
								estadoCivil = "CASADA";
							} else {
								if (clienteDTO.getEstadoCivil() == EstadoCivil.DIVORCIADO) {
									estadoCivil = "DIVORCIADA";
								} else {
									estadoCivil = "OUTROS";
								}
							}
						}
					}
				}
				codContas = (Collection<Integer>) clienteDTO.getCodContas();

				for (int i : codContas) {
					try {
						contaDTO = contaService.buscarConta(i);
						String[] linha = new String[] {
								clienteDTO.getNome(),
								clienteDTO.getSobrenome(),
								sexo,
								String.valueOf(clienteDTO.getCpf()),
								estadoCivil,
								clienteDTO.getEndereco() + " - "
										+ clienteDTO.getCep(),
								String.valueOf(String.format("%05d", contaDTO.getCodigo())),
								String.valueOf(contaDTO.getSaldo()) };
						model.addRow(linha);
					} catch (DaoException e) {
						javiso.exibirMensagem(e.getMessage());
					}
				}

				if (codContas.isEmpty()) {
					String[] linha = new String[] {
							clienteDTO.getNome(),
							clienteDTO.getSobrenome(),
							sexo,
							String.valueOf(clienteDTO.getCpf()),
							estadoCivil,
							clienteDTO.getEndereco() + " - " + clienteDTO.getCep() };
					model.addRow(linha);
				}
			}
		}

		if (dispara.getSource() == this.btnTransacoes) {
			this.btnListarCadastro.setEnabled(true);
			new JanelaTransacao(this);
			this.dispose();
		}
		
		if (dispara.getSource() == this.btnExcluir) {
			JanelaAviso javiso = new JanelaAviso();
			DefaultTableModel model = (DefaultTableModel) this.table.getModel();
			
			System.out.println(this.table.getSelectedRow() + " e " + this.table.getColumnCount());
			//instancio o array que pegará os valores das colunas da linha selecionada
			String[] linhaSelecionada = new String[QTD_COLUNAS];
			
			for (int i = 0; i < QTD_COLUNAS; i++) {
				System.out.println("i = "+i);
				//guardo o valor de cada coluna no array
				linhaSelecionada[i] = (String) model.getValueAt(this.table.getSelectedRow(), i);
			}
			System.out.println(linhaSelecionada[COLUNA_CPF]);
			//Procedimento para exclusão
			try {
				//se o cliente tiver mais de uma conta, removo apenas as contas
				if (clienteService.getQuantidadeContas(Long.parseLong(linhaSelecionada[COLUNA_CPF])) > 1) { 
					//apago a "referência" que o ClienteDTO tem da ContaDTO
					clienteService.buscarCliente(Long.parseLong(linhaSelecionada[COLUNA_CPF])).removerContaDTO(Integer.parseInt(linhaSelecionada[COLUNA_CONTA]));
					//apago a "referência" que a ContaDTO tem do ClienteDTO					
					contaService.buscarConta(Integer.parseInt(linhaSelecionada[COLUNA_CONTA])).setClienteCpf(CPF_NULO);
					//com esse método desfaço o relacionamento entre Cliente e Conta do DO e removo a conta do DAO
					contaService.removerConta(Integer.parseInt(linhaSelecionada[COLUNA_CONTA]));
				}
				//se o cliente tiver apenas uma conta, removo-o e sua conta
				else {
					if (clienteService.getQuantidadeContas(Long.parseLong(linhaSelecionada[COLUNA_CPF])) == 1) {
						//apago a "referência" que o ClienteDTO tem da ContaDTO
						clienteService.buscarCliente(Long.parseLong(linhaSelecionada[COLUNA_CPF])).removerContaDTO(Integer.parseInt(linhaSelecionada[COLUNA_CONTA]));
						//apago a "referência" que a ContaDTO tem do ClienteDTO					
						contaService.buscarConta(Integer.parseInt(linhaSelecionada[COLUNA_CONTA])).setClienteCpf(CPF_NULO);
						//com esse método desfaço o relacionamento entre Cliente e Conta do DO e removo a conta do DAO
						contaService.removerConta(Integer.parseInt(linhaSelecionada[COLUNA_CONTA]));
						//removo o Cliente do DAO						
						clienteService.removerCliente(Long.parseLong(linhaSelecionada[COLUNA_CPF]));
					}
					else //caso contrário, removo apenas o cliente
						clienteService.removerCliente(Long.parseLong(linhaSelecionada[COLUNA_CPF]));
					
				}
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
			} 
						
			model.removeRow(this.table.getSelectedRow());
			
		}
		
		if (dispara.getSource() == this.btnSair) {
			System.exit(0);
			//pt.sair();
		}
		
	}

	public void carregarDados() {
		clienteService.resgatarClientes();
		contaService.resgatarContas();
	}
	
	/**
	 * @return the contaService
	 */
	public static ContaService getContaService() {
		return contaService;
	}

	/**
	 * @param contaService
	 *            the contaService to set
	 */
	public static void setContaService(ContaService contaService) {
		JanelaPrincipal.contaService = contaService;
	}

	/**
	 * @return the clienteService
	 */
	public static ClienteService getClienteService() {
		return clienteService;
	}

	/**
	 * @param clienteService
	 *            the clienteService to set
	 */
	public static void setClienteService(ClienteService clienteService) {
		JanelaPrincipal.clienteService = clienteService;
	}

}
