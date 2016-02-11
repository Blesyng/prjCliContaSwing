package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dao.BancoDAO;
import dto.ClienteDTO;
import entity.Cliente;
import entity.Conta;
import exception.DaoException;
import exception.ServiceException;
import exception.ServiceExceptionEnum;

public class ClienteService {
	private BancoDAO dao;
	private Map<Long, ClienteDTO> clientesDTO;
	private static ContaService contaService;
	//private static final int CONTA_NULA = 0;
	
	public ClienteService(ContaService contaService) {
		this.dao = BancoDAO.getInstance();
		this.contaService = contaService;
	}

	public ClienteDTO buscarCliente(long cpf) throws DaoException {
		try {
			ClienteDTO clienteDTO = new ClienteDTO(
					dao.getCliente(cpf).getCpf(), dao.getCliente(cpf).getNome(), dao.getCliente(cpf).getSobrenome(),
					dao.getCliente(cpf).getSexo(),dao.getCliente(cpf).getEstadoCivil(), dao.getCliente(cpf).getCep(),
					dao.getCliente(cpf).getEndereco());
			for(Conta conta : dao.getCliente(cpf).getListaContas())
				clienteDTO.adicionarContaDTO(conta.getCodigo());
			return clienteDTO;
		} catch (DaoException e) {
			throw new DaoException("Não há cliente com o código informado");
		}
	}

	public void cadastrarCliente(ClienteDTO ClienteDTO)
			throws ServiceException, DaoException {
		if ((ClienteDTO.getCpf() < 1)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_CPF_INVALIDO);
		}
		if ((ClienteDTO.getNome().length() < 1)
				|| (ClienteDTO.getNome().length() > 20)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_NOME_INVALIDO);
		}
		if ((ClienteDTO.getSobrenome().length() < 1)
				|| (ClienteDTO.getSobrenome().length() > 25)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_SOBRENOME_INVALIDO);
		}
		if (ClienteDTO.getEstadoCivil() == null) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_ESTADOCIVIL_INVALIDO);
		}
		if (ClienteDTO.getSexo() == null) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_SEXO_INVALIDO);
		}
		if (ClienteDTO.getCep().length() < 1) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_CEP_INVALIDO);
		}
		if ((ClienteDTO.getEndereco().length() < 1)
				|| (ClienteDTO.getEndereco().length() > 30)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_ENDERECO_INVALIDO);
		}

		Cliente cliente = new Cliente(ClienteDTO.getCpf(), ClienteDTO.getNome(),
				ClienteDTO.getSobrenome(), ClienteDTO.getSexo(), 
				ClienteDTO.getEstadoCivil(), ClienteDTO.getCep(), ClienteDTO.getEndereco());

		try {
			dao.addCliente(cliente);
		} catch (DaoException e) {
			throw new DaoException("Erro no banco de dados");
		}
	}

	public void alterarCliente(ClienteDTO ClienteDTO) throws ServiceException,
			DaoException {
		if ((ClienteDTO.getCpf() < 1)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_CPF_INVALIDO);
		}
		if ((ClienteDTO.getNome().length() < 1)
				|| (ClienteDTO.getNome().length() > 20)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_NOME_INVALIDO);
		}
		if ((ClienteDTO.getSobrenome().length() < 1)
				|| (ClienteDTO.getSobrenome().length() > 25)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_SOBRENOME_INVALIDO);
		}
		if (ClienteDTO.getEstadoCivil() == null) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_ESTADOCIVIL_INVALIDO);
		}
		if (ClienteDTO.getSexo() == null) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_SEXO_INVALIDO);
		}
		if ((ClienteDTO.getCep().length() < 1)
				|| (ClienteDTO.getCep().length() > 8)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_CEP_INVALIDO);
		}
		if ((ClienteDTO.getEndereco().length() < 1)
				|| (ClienteDTO.getEndereco().length() > 30)) {
			throw new ServiceException(
					ServiceExceptionEnum.CLIENTE_ENDERECO_INVALIDO);
		}


		Cliente cliente = new Cliente( ClienteDTO.getCpf(), ClienteDTO.getNome(),
				ClienteDTO.getSobrenome(), ClienteDTO.getSexo(), 
				ClienteDTO.getEstadoCivil(), ClienteDTO.getCep(), ClienteDTO.getEndereco());

		try {
			dao.updateCliente(cliente);
		} catch (DaoException e) {
			throw new DaoException("Erro no banco de dados");
		}
	}

	public void removerCliente(long cpf) throws DaoException {
		try {
			
			dao.removeCliente(cpf);
					
		} catch (DaoException e) {
			throw new DaoException("Não há cliente com o código informado");
		}
	}
	
	public Collection<Cliente> buscarClientes() throws DaoException {
		return dao.getClientes();
	}
	
	public Collection<ClienteDTO> buscarclientesDTO() throws DaoException{
		clientesDTO = new HashMap<Long, ClienteDTO>();
		for (Cliente c : dao.getClientes()) {
			ClienteDTO clienteDTO = new ClienteDTO( c.getCpf(), c.getNome(), c.getSobrenome(),
													c.getSexo(), c.getEstadoCivil(), c.getCep(), c.getEndereco());
			for(Conta conta : dao.getCliente(c.getCpf()).getListaContas())
				clienteDTO.adicionarContaDTO(conta.getCodigo());
			clientesDTO.put(clienteDTO.getCpf(), clienteDTO);
		}
		return clientesDTO.values();
	}
	
	public boolean adicionarContaCliente(long cpf) {
		Collection<Conta> contas = null;
		try {
			contas = (Collection<Conta>) contaService.buscarContas();			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		for (Conta conta : contas) {
			// dando problema
			try{
				if (dao.getCliente(cpf).getCpf() == conta.getCliente().getCpf())
					dao.getCliente(cpf).adicionarConta(conta);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			
		}
		return true;
	}
	
	public int getQuantidadeContas (long cpf) throws DaoException {
		int qtdContas = dao.getCliente(cpf).getListaContas().size();
		return qtdContas;
	}
	
	public void resgatarClientes() {
		dao.recuperarClientes();
	}
}
