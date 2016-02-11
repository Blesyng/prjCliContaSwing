package systemUI;

//import dao.BancoDAO;

public class ProgramTeste {

	/**
	 * @param args
	 */
	
	//private BancoDAO dao = BancoDAO.getInstance();
	private JanelaPrincipal j1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProgramTeste p1 = new ProgramTeste();
		p1.iniciar();
		//new JanelaCadastro();
	}

	private void iniciar() {
		this.j1 = new JanelaPrincipal(this);
		this.j1.carregarDados();
		/*dao.recuperarClientes();
		dao.recuperarContas();*/
	}

	/*public void sair() {
		dao.salvarClientes();
		dao.salvarContas();
		System.exit(0);
		
	}*/

}
