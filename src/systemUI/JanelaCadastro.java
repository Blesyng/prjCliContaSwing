package systemUI;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import service.ClienteService;
import service.ContaService;
import dto.ClienteDTO;
import dto.ContaDTO;
import entity.EstadoCivil;
import entity.Sexo;
import entity.TipoConta;
import exception.DaoException;
import exception.ServiceException;

/**
 * @author Danyllo, Felipe e Hugo
 * @version 1.0
 */
public class JanelaCadastro extends JFrame implements ActionListener {

	private JLabel lblFundo;
	private JLabel rotuloNome;
	private JLabel rotuloSobrenome;
	private JLabel rotuloEstCivil;
	private JLabel rotuloSexo;
	private JLabel rotuloCEP;
	private JLabel rotuloEndereco;
	private JLabel rotuloCpf;
	private JLabel rotuloCPF;
	private JTextField campoNome;
	private JTextField campoSobrenome;
	private JTextField campoCEP;
	private JTextField campoEndereco;
	private JTextField campoCpf;
	private JComboBox comboEstCivil;
	private JComboBox comboSexo;
	private static final String[] EST_CIV = { " ", "Solteiro", "Casado",
			"Divorciado", "Outros" };
	private static final String[] SEXO = { " ", "Masculino", "Feminino" };
	private JButton btnCor;
	private JButton btnPoup;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnVoltar;
	private ImageIcon fundo;
	private String cpf = null;
	private JanelaPrincipal j;
	private int contContas = 0;
	private static ContaService contaService = new ContaService();
	private static ClienteService clienteService = new ClienteService(
			contaService);
	private JanelaAviso javiso = new JanelaAviso();
	
	/**
	 * @throws HeadlessException
	 */
	public JanelaCadastro(JanelaPrincipal j, String cpf) {
		super();
		this.j = j;
		this.cpf = cpf;

		// ADICIONANDO ELEMENTOS AO FORMULÁRIO
		this.setLayout(null);
		this.setSize(1100, 715);
		this.setLocation(5, 5);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		this.setTitle("Sistema Banco Pilares - Tela de Cadastro");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/Icone PILARES.png"));
		setDefaultCloseOperation(0);

		// COLOCA E INSTANCIA A IMAGEM DE FUNDO
		fundo = new ImageIcon("./src/imagens/bkg2.jpg");
		this.lblFundo = new JLabel(fundo);

		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblFundo.setBounds(0, 0, 1100, 715);
		this.add(lblFundo);
		this.lblFundo.setBackground(Color.WHITE);

		this.rotuloCpf = new JLabel("CPF:");
		this.rotuloCpf.setBounds(300, 190, 100, 50);
		this.lblFundo.add(rotuloCpf);

		this.rotuloNome = new JLabel("Nome:");
		this.rotuloNome.setBounds(300, 235, 100, 50);
		this.lblFundo.add(rotuloNome);

		this.rotuloSobrenome = new JLabel("Sobrenome:");
		this.rotuloSobrenome.setBounds(300, 280, 100, 50);
		this.lblFundo.add(rotuloSobrenome);

		this.rotuloEstCivil = new JLabel("Estado Civil:");
		this.rotuloEstCivil.setBounds(300, 325, 100, 50);
		this.lblFundo.add(rotuloEstCivil);

		this.rotuloSexo = new JLabel("Sexo:");
		this.rotuloSexo.setBounds(300, 370, 100, 50);
		this.lblFundo.add(rotuloSexo);

		this.rotuloCEP = new JLabel("CEP:");
		this.rotuloCEP.setBounds(300, 415, 100, 50);
		this.lblFundo.add(rotuloCEP);

		this.rotuloEndereco = new JLabel("Endereço:");
		this.rotuloEndereco.setBounds(300, 460, 100, 50);
		this.lblFundo.add(rotuloEndereco);

		this.rotuloCPF = new JLabel("CPF: XXX.XXX.XXX-XX");
		this.rotuloCPF.setBounds(810, 70, 200, 50);
		this.lblFundo.add(rotuloCPF);

		/*this.rotuloConta = new JLabel("tipo de conta");
		this.rotuloConta.setBounds(810, 90, 200, 50);
		this.rotuloConta.setForeground(Color.RED);
		this.lblFundo.add(rotuloConta);*/

		try {
			MaskFormatter formatar = new MaskFormatter("###.###.###-##");
			this.campoCpf = new JFormattedTextField(formatar);
			this.campoCpf.setBounds(350, 195, 155, 30);
			this.campoCpf.setToolTipText("Insira o CPF do cliente");
			this.lblFundo.add(campoCpf);
		} catch (ParseException e1) {
		}

		this.campoNome = new JTextField();
		this.campoNome.setBounds(382, 240, 100, 30);
		this.campoNome.setToolTipText("Insira o nome do cliente");
		this.lblFundo.add(campoNome);

		this.campoSobrenome = new JTextField();
		this.campoSobrenome.setBounds(380, 285, 155, 30);
		this.campoSobrenome.setToolTipText("Insira o sobrenome do cliente");
		this.lblFundo.add(campoSobrenome);

		this.comboEstCivil = new JComboBox(EST_CIV);
		this.comboEstCivil.setMaximumRowCount(5);
		this.comboEstCivil.setBounds(380, 330, 100, 30);
		this.comboEstCivil.setBackground(Color.WHITE);
		this.lblFundo.add(comboEstCivil);

		this.comboSexo = new JComboBox(SEXO);
		this.comboSexo.setMaximumRowCount(3);
		this.comboSexo.setBounds(338, 375, 100, 30);
		this.comboSexo.setBackground(Color.WHITE);
		this.lblFundo.add(comboSexo);

		try {
			MaskFormatter formatar = new MaskFormatter("#####-###");
			this.campoCEP = new JFormattedTextField(formatar);
			this.campoCEP.setBounds(338, 420, 155, 30);
			this.campoCEP.setToolTipText("Insira o CEP do cliente");
			this.lblFundo.add(campoCEP);
		} catch (ParseException e1) {
		}

		this.campoEndereco = new JTextField();
		this.campoEndereco.setBounds(370, 465, 155, 30);
		this.campoEndereco
				.setToolTipText("Insira o endereço completo do cliente");
		this.lblFundo.add(campoEndereco);

		// POSICIONANDO BOTÕES
		this.btnEditar = new JButton("Editar");
		this.btnEditar.setBounds(450, 640, 70, 29);
		this.btnEditar.setBackground(Color.GRAY);
		this.btnEditar.setForeground(Color.WHITE);
		this.lblFundo.add(btnEditar);
		this.btnEditar.addActionListener(this);

		this.btnSalvar = new JButton("Salvar");
		this.btnSalvar.setBounds(530, 640, 70, 29);
		this.btnSalvar.setBackground(Color.GRAY);
		this.btnSalvar.setForeground(Color.WHITE);
		this.lblFundo.add(btnSalvar);
		this.btnSalvar.addActionListener(this);

		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setBounds(610, 640, 75, 29);
		this.btnVoltar.setBackground(Color.red.darker());
		this.btnVoltar.setForeground(Color.WHITE);
		this.lblFundo.add(btnVoltar);
		this.btnVoltar.addActionListener(this);

		this.btnCor = new JButton("Vincular Conta Corrente");
		this.btnCor.setBounds(632, 350, 180, 40);
		this.btnCor.setBackground(Color.lightGray);
		this.btnCor.setForeground(Color.WHITE);
		this.lblFundo.add(btnCor);
		this.btnCor.addActionListener(this);
		this.btnCor.setEnabled(false);

		this.btnPoup = new JButton("Vincular Conta Poupança");
		this.btnPoup.setBounds(632, 400, 180, 40);
		this.btnPoup.setBackground(Color.lightGray);
		this.btnPoup.setForeground(Color.WHITE);
		this.lblFundo.add(btnPoup);
		this.btnPoup.addActionListener(this);
		this.btnPoup.setEnabled(false);

		this.setVisible(true);
		this.repaint();
		this.buscarCliente();
		
		try {
			contContas = contaService.buscarContas().size();
		} catch (DaoException e) {
			javiso.exibirMensagem(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent dispara) {
		if (dispara.getSource() == this.btnEditar) {
			editarHabilitarComponentes();
		}
		if (dispara.getSource() == this.btnSalvar) {
			ClienteDTO clienteDTO = null;
			cpf = campoCpf.getText();
			cpf = cpf.replace(".", "").trim();
			cpf = cpf.replace("-", "").trim();
			
			if (cpf.length() == 11) {
				try{
					long cpfl = Long.parseLong(cpf);
					clienteDTO = clienteService.buscarCliente(cpfl);
					if (clienteDTO != null)
						javiso.exibirMensagem("CPF já cadastrado");
					this.buscarCliente();
					
				} catch (DaoException e) {
					if (clienteDTO == null){
						cpf = campoCpf.getText();
						this.rotuloCPF.setText(cpf);
						cpf = cpf.replace(".", "").trim();
						cpf = cpf.replace("-", "").trim();
			
						Sexo sexo = null;
						if (comboSexo.getSelectedItem() == "Masculino") {
							sexo = Sexo.MASCULINO;
						} else {
							sexo = Sexo.FEMININO;
						}

						EstadoCivil estadoCivil = null;
						if (comboEstCivil.getSelectedItem() == "Solteiro") {
							estadoCivil = EstadoCivil.SOLTEIRO;
						} else {
							if (comboEstCivil.getSelectedItem() == "Casado") {
								estadoCivil = EstadoCivil.CASADO;
							} else {
								if (comboEstCivil.getSelectedItem() == "Divorciado") {
									estadoCivil = EstadoCivil.DIVORCIADO;
								} else {
									estadoCivil = EstadoCivil.OUTROS;
								}
							}
						}
						
						String cep = campoCEP.getText();
						cep = cep.replace(" ", "").trim();
						cep = cep.replace("-", "").trim();
						
						if (cep.length() > 1){
						criarCliente(Long.parseLong(cpf), campoNome.getText(),campoSobrenome.getText(), sexo, estadoCivil,
								campoCEP.getText(), campoEndereco.getText());
						}
						
						if ((cpf != null) && (campoNome.getText().length() > 1) && (campoNome.getText().length() < 20) &&
								(campoSobrenome.getText().length() > 1) && (campoSobrenome.getText().length() < 25) && (estadoCivil != null) &&
								(sexo != null) && (cep.length() > 1) && (campoEndereco.getText().length() > 1) && (campoEndereco.getText().length() < 30)){
							javiso.exibirMensagem("Novo cliente cadastrado com sucesso");						
							salvarDesabilitarComponentes();
						}
					}
				}
			}
			else{
				javiso.exibirMensagem("O CPF digitado é inválido!");
			}
		}
		
		if (dispara.getSource() == this.btnVoltar) {
			cpf = null;
			this.setVisible(false);
			j.setVisible(true);
		}
		
		if (dispara.getSource() == this.btnCor) {
			this.btnCor.setEnabled(false);
			/*try {
				contContas = contaService.buscarContas().size();
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
			}*/

			cpf = campoCpf.getText();
			cpf = cpf.replace(".", "").trim();
			cpf = cpf.replace("-", "").trim();

			String agencia = new String("AgPilares");
			int randomico = new Random().nextInt(10);
			int codigo = ++contContas + randomico;//código da conta é composto da qtd de contas existentes mais um número aleatório
			criarConta(codigo, agencia, 0L, TipoConta.CORRENTE,Long.parseLong(cpf));
			javiso.exibirMensagem("Conta corrente criada com sucesso!");
		}

		if (dispara.getSource() == this.btnPoup) {
			this.btnPoup.setEnabled(false);
			/*try {
				contContas = contaService.buscarContas().size();
			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
			}*/

			cpf = campoCpf.getText();
			cpf = cpf.replace(".", "").trim();
			cpf = cpf.replace("-", "").trim();

			String agencia = new String("AgPilares");
			int randomico = new Random().nextInt(10);
			int codigo = ++contContas + randomico;//código da conta é composto da qtd de contas existentes mais um número aleatório
			criarConta(codigo, agencia, 0L, TipoConta.POUPANCA,Long.parseLong(cpf));
			javiso.exibirMensagem("Conta poupança criada com sucesso!");
			
		}
	}

	private void editarHabilitarComponentes() {
		this.campoNome.setEnabled(true);
		this.campoSobrenome.setEnabled(true);
		this.comboSexo.setEnabled(true);
		this.comboEstCivil.setEnabled(true);
		this.campoEndereco.setEnabled(true);
		this.campoCEP.setEnabled(true);
		this.btnSalvar.setEnabled(true);
		this.btnCor.setEnabled(true);
		this.btnPoup.setEnabled(true);
		
		this.btnEditar.setEnabled(false);
	}

	private void salvarDesabilitarComponentes() {
		this.campoCpf.setEnabled(false);
		this.campoNome.setEnabled(false);
		this.campoSobrenome.setEnabled(false);
		this.comboSexo.setEnabled(false);
		this.comboEstCivil.setEnabled(false);
		this.campoEndereco.setEnabled(false);
		this.campoCEP.setEnabled(false);
		this.btnSalvar.setEnabled(false);
		
		this.btnEditar.setEnabled(true);
		this.btnCor.setEnabled(true);
		this.btnPoup.setEnabled(true);
	}

	private void buscarCliente() {
		if (cpf.equals("Sem cadastro")) {
			rotuloCPF.setText("CPF: XXX.XXX.XXX-XX");
			btnEditar.setEnabled(false);
			btnSalvar.setEnabled(true);
		} else {
			rotuloCPF.setText(cpf);
			cpf = cpf.replace(".", "").trim();
			cpf = cpf.replace("-", "").trim();
			long cpfl = Long.parseLong(cpf);

			try {
				ClienteDTO cliente = clienteService.buscarCliente(cpfl);
				this.campoCpf.setText(cpf);
				this.campoCpf.setEnabled(false);
				this.campoSobrenome.setEnabled(false);
				this.campoNome.setText(cliente.getNome());
				this.campoNome.setEnabled(false);
				this.campoSobrenome.setText(cliente.getSobrenome());
				this.campoSobrenome.setEnabled(false);

				if (cliente.getSexo() == Sexo.MASCULINO) {
					this.comboSexo.setSelectedItem("Masculino");
				} else {
					this.comboSexo.setSelectedItem("Feminino");
				}
				this.comboSexo.setEnabled(false);

				if (cliente.getEstadoCivil() == EstadoCivil.SOLTEIRO) {
					this.comboEstCivil.setSelectedItem("Solteiro");
				} else {
					if (cliente.getEstadoCivil() == EstadoCivil.CASADO) {
						this.comboEstCivil.setSelectedItem("Casado");
					} else {
						if (cliente.getEstadoCivil() == EstadoCivil.DIVORCIADO) {
							this.comboEstCivil.setSelectedItem("Divorciado");
						} else {
							this.comboEstCivil.setSelectedItem("Outros");
						}

					}
				}
				this.comboEstCivil.setEnabled(false);
				this.campoEndereco.setText(cliente.getEndereco());
				this.campoEndereco.setEnabled(false);
				this.campoCEP.setText(cliente.getCep());
				this.campoCEP.setEnabled(false);
				this.btnSalvar.setEnabled(false);
				this.btnEditar.setEnabled(true);

			} catch (DaoException e) {
				javiso.exibirMensagem(e.getMessage());
				this.campoCpf.setText(cpf);
				this.btnEditar.setEnabled(false);
				this.btnSalvar.setEnabled(true);
			}
		}
		
	}
	
	private static void criarCliente(long cpf, String nome, String sobrenome,
			Sexo sexo, EstadoCivil estadoCivil, String cep, String endereco) {
		ClienteDTO clienteDTO = new ClienteDTO(cpf, nome, sobrenome, sexo, estadoCivil, cep, endereco);
		try {
			clienteService.cadastrarCliente(clienteDTO);
		} catch (ServiceException e) {
			//System.out.println(e.getTipo());
		} catch (DaoException e) {
			//System.out.println(e.getMessage());
		}

	}

	private static void criarConta(int codigo, String agencia, float saldo,
			TipoConta tipo, long cpf) {
		ContaDTO contaDTO = new ContaDTO(codigo, agencia, saldo, tipo, cpf);
		try {
			contaService.cadastrarConta(contaDTO);
		} catch (ServiceException e) {
			//System.out.println(e.getTipo());
		} catch (DaoException e) {
			//System.out.println(e.getMessage());
		}

		Collection<ClienteDTO> clientesCadastrados = null;
		try {
			clientesCadastrados = (Collection<ClienteDTO>) clienteService.buscarclientesDTO();
		} catch (DaoException e) {
			//e.printStackTrace();
		}
		for (ClienteDTO clienteDTO : clientesCadastrados) {
			if (contaDTO.getClienteCpf() == clienteDTO.getCpf())
				clienteDTO.adicionarContaDTO(codigo);
		}
		clienteService.adicionarContaCliente(cpf);
	}

}
