
public class Principal {

	public static void main(String[] args) {
		Usuario u = new Usuario();
		
		u.inserir("123", "Maria", 0, "Aluno");
		u.inserir("124", "Matheus", 10, "Aluno");
		u.inserir("100", "Evilasio", 0, "Professor");
		u.inserir("100", "Evilasio", 0, "Professor");
		u.remover("10");
		u.alterar("123", "Joao");
		u.pesquisar("100");
		u.remover("100");
		u.pesquisar("100");
		u.listarUsuarios();
	}

}
