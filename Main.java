package projeto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Main {
	public static void main(String[] agrs) {
		Comandos.comandoPrograma();		
	}
	
	@SuppressWarnings("unchecked")
	public static void lerPrograma() {
		Utente.ListaUtente.clear();
		Familia.ListaFamilia.clear();
		Profissional.ListaProfissional.clear();
		try {
			FileInputStream ficheiro = new FileInputStream("LerPrograma.sav");
			ObjectInputStream fich = new ObjectInputStream(ficheiro);
			try {
				Profissional.ListaProfissional = (ArrayList<Profissional>) fich.readObject();
				Utente.ListaUtente = (ArrayList<Utente>) fich.readObject();
				MarcarCuidado.ListaMarcacoes = (ArrayList<MarcarCuidado>) fich.readObject();
				Familia.ListaFamilia = (ArrayList<Familia>) fich.readObject();
				System.out.println("Unidade do centro de saúde carregada com sucesso.\n ");
			} catch (ClassNotFoundException c) {
				System.out.println("Ocorreu um erro de carregamento da unidade do centro de saúde.\n");
			}
			fich.close();
		} catch(IOException io) {
			System.out.println("Ocorreu um erro de carregamento da unidade do centro de saúde. \n");
		}
	}
	public static void gravarPrograma() {
	 try {
		 FileOutputStream guardar = new FileOutputStream("GuardarPrograma.sav");
		 ObjectOutputStream guarda = new ObjectOutputStream(guardar);
		 guarda.writeObject(Profissional.ListaProfissional);
		 guarda.writeObject(Utente.ListaUtente);
		 guarda.writeObject(MarcarCuidado.ListaMarcacoes);
		 guarda.writeObject(Familia.ListaFamilia);
		 guarda.close();
		 guardar.close();
		 System.out.println("Unidade do centro de saúde gravadada corretamente. \n");
	 } catch (IOException io) {
		 System.out.println("Ocorreu erros ao gravar a unidade do centro de saúde. \n");
	 }
	}
	

}
