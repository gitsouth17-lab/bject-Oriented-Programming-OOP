package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Profissional implements Serializable {
	private static final long serialVersionUID = 3380949963170007965L;
	public String NomeP;
	public String Categoria;
	public static ArrayList<Profissional> ListaProfissional = new ArrayList<Profissional>();
	public Profissional(String NomeP, String Categoria) {
		this.NomeP = NomeP;
		this.Categoria = Categoria;
	}
	public String getNomeProfissional() {
		return this.NomeP;
	}
	public String getCategoria() {
		return this.Categoria;
	}
	public static boolean procurarProfissionalExistente(String NomeP, String Categoria) {
		for (int a = 0; a < ListaProfissional.size(); a++) {
			if (((Profissional) ListaProfissional.get(a)).getNomeProfissional().equals(NomeP) && ((Profissional) ListaProfissional.get(a)).getCategoria().equals(Categoria)) {
				return true;
			}
		}
		return false;
	}
	public static String obterACategoriaDoProfissional(String NomeP) {
		for (int a = 0; a < ListaProfissional.size(); a++) {
			if (((Profissional) ListaProfissional.get(a)).getNomeProfissional().equals(NomeP)) {
				return ((((Profissional) ListaProfissional.get(a)).getCategoria()));
			}
		}
		return " ";
}
	public static void profissionalSucesso(String[] MeteComando) {
		if (procurarProfissionalExistente(MeteComando[1], MeteComando[2]) == true) {
			System.out.println("O profissioanl inserido já existe neste centro de saúde!\n");
			Comandos.comandoPrograma();
		}
		ArrayList<String> Categorias = new ArrayList<String>();
		Categorias.add("Medicina");
		Categorias.add("Enfermagem");
		Categorias.add("Auxiliar");
		if (!Categorias.contains(MeteComando[1])) {
			System.out.println("A categoria inserida é inválida! Por favor, insira uma categoria válida (Medicina, Enfermagem ou Auxiliar)\n");
			Comandos.comandoPrograma();
		}
		Profissional profissional = new Profissional(MeteComando[1], MeteComando[2]);
		ListaProfissional.add(profissional);
		System.out.println("O profissional foi registado com sucesso neste centro de saúde\n");
		System.out.println(ListaProfissional);
		Comandos.comandoPrograma();
		
	}
	public static void listarProfissional() {
		for (int a = 0; a < registarProfissional().size(); a++) {
			System.out.println(registarProfissional().get(a));
		}
		Comandos.comandoPrograma();
	}
	public static ArrayList<String> registarProfissional() {
		ArrayList<String> Lista = new ArrayList<String>();
		ArrayList<String> Auxiliar = new ArrayList<String>();
		ArrayList<String> Médico = new ArrayList<String>();
		ArrayList<String> Enfermeiro = new ArrayList<String>();
		for (int a = 0; a < ListaProfissional.size(); a++) {
			System.out.println(ListaProfissional);
			if (((Profissional) ListaProfissional.get(a)).getCategoria().equals("Medicina")) {
				Médico.add(((Profissional) ListaProfissional.get(a)).getNomeProfissional());
				System.out.println(Médico);
			} else if (((Profissional) ListaProfissional.get(a)).getCategoria().equals("Auxiliar")) {
				Auxiliar.add(((Profissional) ListaProfissional.get(a)).getNomeProfissional());
			} else if (((Profissional) ListaProfissional.get(a)).getCategoria().equals("Enfermagem")) {
				Enfermeiro.add(((Profissional) ListaProfissional.get(a)).getNomeProfissional());
			}
		}
		Collections.sort(Auxiliar);
		Collections.sort(Médico);
		Collections.sort(Enfermeiro);
		for (int a = 0; a < Auxiliar.size(); a++) {
			Auxiliar.set(a, "Auxiliar:" + Auxiliar.get(a));
		}
		for (int a = 0; a < Médico.size(); a++) {
			Médico.set(a, "Médico:" + Médico.get(a));
			
		}
		for (int a = 0; a < Enfermeiro.size(); a++) {
			Enfermeiro.set(a, "Enfermagem:" + Enfermeiro.get(a));
			
		}
		System.out.println(Lista);
		Lista.addAll(Auxiliar);
		Lista.addAll(Médico);
		Lista.addAll(Enfermeiro);
		if (Lista.size() == 0) {
			Lista.add("Não há profissionais registados para desempenhar alguma função neste centro de saúde.\n");
		}
		return Lista;
		
	}
	
}
