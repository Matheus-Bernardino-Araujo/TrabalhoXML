import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


public class Usuario {
	private String matricula;
	private String nome;
	private double debito;
	private String tipo;
	
	public Usuario() {
	}
	
	public void inserir(String matricula, String nome, double debito, String tipo){
		List<Usuario> usuarios = lerArquivo();
		Element root = new Element("ListaUsuarios");
		Document myDoc = new Document(root);
		
		Usuario usr = new Usuario();
		usr.setMatricula(matricula);
		usr.setNome(nome);
		usr.setDebito(debito);
		usr.setTipo(tipo);
		
		
		for(Usuario u : usuarios){
			if(u.getMatricula().equals(matricula))
				return;
		}
		
		usuarios.add(usr);
		
		for(Usuario u : usuarios){
			Element usuario = new Element("Usuario");
			
			Element matriculaE = new Element("matricula");
			matriculaE.setText(u.getMatricula());
			
			Element nomeE = new Element("nome");
			nomeE.setText(u.getNome());
			
			Element debitoE = new Element("debito");
			debitoE.setText(Double.toString((u.getDebito())));
			
			Element tipoE = new Element("tipo");
			tipoE.setText(u.getTipo());
			
			usuario.addContent(matriculaE);
			usuario.addContent(nomeE);
			usuario.addContent(debitoE);
			usuario.addContent(tipoE);
			
			root.addContent(usuario);
		}
		
		try{
		FileWriter fxml = new FileWriter("arquivo.xml");
		XMLOutputter xout = new XMLOutputter();
			xout.output(myDoc, fxml);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void remover(String matricula){
		List<Usuario> usuarios = lerArquivo();
		Element root = new Element("ListaUsuarios");
		Document myDoc = new Document(root);
		
		for(Usuario u : usuarios){
			if(u.getMatricula().equals(matricula)){
				usuarios.remove(u);
				break;
			}
		}
		
		for(Usuario u : usuarios){
			Element usuario = new Element("Usuario");
			
			Element matriculaE = new Element("matricula");
			matriculaE.setText(u.getMatricula());
			
			Element nomeE = new Element("nome");
			nomeE.setText(u.getNome());
			
			Element debitoE = new Element("debito");
			debitoE.setText(Double.toString((u.getDebito())));
			
			Element tipoE = new Element("tipo");
			tipoE.setText(u.getTipo());
			
			usuario.addContent(matriculaE);
			usuario.addContent(nomeE);
			usuario.addContent(debitoE);
			usuario.addContent(tipoE);
			
			root.addContent(usuario);
		}
		
		try{
		FileWriter fxml = new FileWriter("arquivo.xml");
		XMLOutputter xout = new XMLOutputter();
			xout.output(myDoc, fxml);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void alterar(String matricula, String nome){
		List<Usuario> usuarios = lerArquivo();
		Element root = new Element("ListaUsuarios");
		Document myDoc = new Document(root);
		
		for(Usuario u : usuarios){
			if(u.getMatricula().equals(matricula)){
				u.setNome(nome);
				break;
			}
		}
		
		for(Usuario u : usuarios){
			Element usuario = new Element("Usuario");
			
			Element matriculaE = new Element("matricula");
			matriculaE.setText(u.getMatricula());
			
			Element nomeE = new Element("nome");
			nomeE.setText(u.getNome());
			
			Element debitoE = new Element("debito");
			debitoE.setText(Double.toString((u.getDebito())));
			
			Element tipoE = new Element("tipo");
			tipoE.setText(u.getTipo());
			
			usuario.addContent(matriculaE);
			usuario.addContent(nomeE);
			usuario.addContent(debitoE);
			usuario.addContent(tipoE);
			
			root.addContent(usuario);
		}
		
		try{
		FileWriter fxml = new FileWriter("arquivo.xml");
		XMLOutputter xout = new XMLOutputter();
			xout.output(myDoc, fxml);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void pesquisar(String matricula){
		File file = new File("arquivo.xml");
		if(file.exists()){
			Document doc = null;
			SAXBuilder builder = new SAXBuilder();
			
			try{
				doc = builder.build("arquivo.xml");
			} catch(Exception e){
				e.printStackTrace();
			}
			
			Element usuario_ = doc.getRootElement();
			List<Element> lista = usuario_.getChildren();
			
			for(Element e : lista){
				if(e.getChildText("matricula").equals(matricula)){
					System.out.println("Nome: " + e.getChildText("nome"));
					System.out.println("Tipo de usuário: " + e.getChildText("tipo"));
					System.out.println("Débito: " + e.getChildText("debito") + "\n");
					return;
				}
			}
			System.out.println("Usuário não encontrado\n");
		}
	}
	
	public void listarUsuarios(){
		File file = new File("arquivo.xml");
		if(file.exists()){
			Document doc = null;
			SAXBuilder builder = new SAXBuilder();
			
			try{
				doc = builder.build("arquivo.xml");
			} catch(Exception e){
				e.printStackTrace();
			}
			
			Element usuario_ = doc.getRootElement();
			List<Element> lista = usuario_.getChildren();
			
			for(Element e : lista){
				System.out.println("Matricula: " + e.getChildText("matricula"));
				System.out.println("Nome: " + e.getChildText("nome"));
				System.out.println("Tipo de usuário: " + e.getChildText("tipo"));
				System.out.println("Débito: " + e.getChildText("debito") + "\n");
			}
			
		}		
	}
		
	public List<Usuario> lerArquivo(){
		File file = new File("arquivo.xml");
		if(file.exists()){
			List<Usuario> usuarios = new ArrayList<Usuario>();
			Document doc = null;
			SAXBuilder builder = new SAXBuilder();
			
			try{
				doc = builder.build("arquivo.xml");
			} catch(Exception e){
				e.printStackTrace();
			}
			
			Element usuario_ = doc.getRootElement();
			List<Element> lista = usuario_.getChildren();
			
			for(Element e : lista){
				Usuario usr = new Usuario();
				usuarios.add(usr);
				usr.setMatricula(e.getChildText("matricula"));
				usr.setNome(e.getChildText("nome"));
				usr.setDebito(Double.parseDouble(e.getChildText("debito")));
				usr.setTipo(e.getChildText("tipo"));
			}
			return usuarios;
		}
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		return usuarios;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getDebito() {
		return debito;
	}
	public void setDebito(double debito) {
		this.debito = debito;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
}
