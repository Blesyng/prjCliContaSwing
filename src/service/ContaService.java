package service;

import java.util.Collection;

import dao.BancoDAO;
import dto.ContaDTO;
import entity.Conta;
import exception.DaoException;
import exception.ServiceException;
import exception.ServiceExceptionEnum;

public class ContaService {
	private BancoDAO dao;

	public ContaService() {
		this.dao = BancoDAO.getInstance();
	}

	public ContaDTO buscarConta(int codigo) throws DaoException {
		try{
			ContaDTO contaDTO = new ContaDTO(dao.getConta(codigo).getCodigo(), dao.getConta(codigo).getAgencia(),
											dao.getConta(codigo).getSaldo(), dao.getConta(codigo).getTipo(),
											dao.getConta(codigo).getCliente().getCpf());
			
			contaDTO.setClienteCpf(dao.getConta(codigo).getCliente().getCpf());
			return contaDTO;
		}
		catch(DaoException e)
		{
			throw new DaoException("Não há conta com o código informado");
		}
	}

	public void cadastrarConta(ContaDTO contaDTO) throws ServiceException,
			DaoException {
		if (contaDTO.getCodigo() < 1) {
			throw new ServiceException(
					ServiceExceptionEnum.CONTA_CODIGO_INVALIDO);
		}
		if ((contaDTO.getAgencia().length() < 1)
				|| (contaDTO.getAgencia().length() > 20)) {
			throw new ServiceException(ServiceExceptionEnum.CONTA_AGENCIA_INVALIDA);
		}
		if (contaDTO.getSaldo() < 0) {
			throw new ServiceException(
					ServiceExceptionEnum.CONTA_SALDO_INVALIDO);
		}
		if (contaDTO.getTipoConta() == null) {
			throw new ServiceException(
					ServiceExceptionEnum.CONTA_TIPO_INVALIDA);
		}
		

		Conta conta = new Conta(contaDTO.getCodigo(), contaDTO.getAgencia(),contaDTO.getSaldo(), contaDTO.getTipoConta(), dao.getCliente(contaDTO.getClienteCpf()));
		
		try {
			dao.addConta(conta);
		} catch (DaoException e) {
			throw new DaoException("Erro no banco de dados");
		}
	}

	public void alterarConta(ContaDTO ContaDTO) throws ServiceException,
			DaoException {
		if (ContaDTO.getCodigo() < 1) {
			throw new ServiceException(
					ServiceExceptionEnum.CONTA_CODIGO_INVALIDO);
		}
		if ((ContaDTO.getAgencia().length() < 1)
				|| (ContaDTO.getAgencia().length() > 20)) {
			throw new ServiceException(ServiceExceptionEnum.CONTA_AGENCIA_INVALIDA);
		}

		Conta conta = new Conta(ContaDTO.getCodigo(), ContaDTO.getAgencia(), ContaDTO.getSaldo(), ContaDTO.getTipoConta(), dao.getCliente(ContaDTO.getClienteCpf()));

		try {
			dao.updateConta(conta);
		} catch (DaoException e) {
			throw new DaoException("Erro no banco de dados");
		}
	}

	public void removerConta(int codigo) throws DaoException {
		try {
			
			dao.getConta(codigo).setCliente(null);
			dao.removeConta(codigo);
		}
		catch(DaoException e)
		{
			throw new DaoException("Não há conta com o código informado");
		}
	}
	
	public Collection<Conta> buscarContas() throws DaoException {
		return dao.getContas();
	}
	
	public void resgatarContas() {
		dao.recuperarContas();
	}
}
