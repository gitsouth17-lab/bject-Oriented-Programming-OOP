package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Familia implements Serializable {
	private static final long serialVersionUID = -1092659727812515497L;

	public String NomeF;
	public static ArrayList<Familia> ListaFamilia = new ArrayList<Familia>();
	public ArrayList<Utente> UtenteFamilia = new ArrayList<Utente>();

	public Familia (String NomeF) {			// Construtor Familia
		this.NomeF = NomeF;
	}

	public String getNomeF() {		// Get nome de familia
		return NomeF;
	}

	public static boolean procurarFamiliaExistente(String NomeF) {		// Ver se familia existe
		for (int a = 0; a < ListaFamilia.size(); a++) {
			if (ListaFamilia.get(a).getNomeF().equals(NomeF)) {
				return true;
			}
		}
		return false;
	}

	public static void familiaRegisto(String NomeF) {		// Registar familia
		if (procurarFamiliaExistente(NomeF) == true) {
			System.out.println("A família já existe na base de dados deste centro de saúde.\n");
			Comandos.comandoPrograma();
		}
		else {
			Familia familia = new Familia(NomeF);
			ListaFamilia.add(familia);
			System.out.println("A família inserida foi registada com sucesso neste centro de saúde.\n");
			Comandos.comandoPrograma();
		}
	}

	public static void listarFamilia() {		// Listar Familia
		if (ListaFamilia.size() > 0) {
			ListaFamilia.sort(Comparator.comparing(Familia::getNomeF));
			for (int a = 0; a < ListaFamilia.size(); a++) {
				System.out.println(ListaFamilia.get(a).getNomeF());
			}
		}
		else {
			System.out.println("Sem famílias registadas neste centro de saúde.\n");
		}
		Comandos.comandoPrograma();
	}
}
