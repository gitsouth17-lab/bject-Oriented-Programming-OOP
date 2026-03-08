package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Utente implements Serializable {
	private static final long serialVersionUID = 8914791031402577891L;

	public String NomeU;
	public String FaixaEtaria;
	public String NomeF;
	public static ArrayList<Utente> ListaUtente = new ArrayList<Utente>();

	public Utente(String NomeU, String FaixaEtaria, String NomeF) {		
		this.NomeU = NomeU;
		this.FaixaEtaria = FaixaEtaria;
		this.NomeF = NomeF;
	}

	public String getNomeU() {		
		return NomeU;
	}

	public String getFaixaEtaria() {		
		return FaixaEtaria;
	}

	public void setNomeF(String NomeF) {		
		this.NomeF = NomeF;
	}

	public String getNomeF() {		
		return NomeF;
	}

	public static boolean procurarUtenteExistente(String NomeU) {		
		for (int a = 0; a < ListaUtente.size(); a++) {
			if (ListaUtente.get(a).getNomeU().equals(NomeU)) {
				return true;
			}
		}
		return false;
	}

	public static void UtenteRegisto(String NomeU, String FaixaEtaria) {		// Registar utente
		if (procurarUtenteExistente(NomeU) == true) {
			System.out.println("O utente já existe na base de dados deste centro de saúde.\n");
			Comandos.comandoPrograma();
		}
		ArrayList<String> UtenteFaixaEtaria = new ArrayList<String>();
		UtenteFaixaEtaria.add("Idoso");
		UtenteFaixaEtaria.add("Adulto");
		UtenteFaixaEtaria.add("Jovem");
		if (!UtenteFaixaEtaria.contains(FaixaEtaria)) {
			System.out.println("A faixa etária inserida não existe! Por favor, insere uma faixa etária válida (Jovem, Adulto ou Idoso)!\n");
			UtenteFaixaEtaria.clear();
			Comandos.comandoPrograma();
		}
		Utente utente = new Utente(NomeU, FaixaEtaria, null);
		ListaUtente.add(utente);
		System.out.println("O utente inserido foi registado com sucesso neste centro de saúde.\n");
		UtenteFaixaEtaria.clear();
		Comandos.comandoPrograma();
	}

	public static boolean procurarFamiliaAssociadaAUtente(String NomeU) {		// Ver se utente tem familia associada
		for (int a = 0; a < ListaUtente.size(); a++) {
			if (ListaUtente.get(a).getNomeU().equals(NomeU) && ListaUtente.get(a).getNomeF() != null) {
				return true;
			}
		}
		return false;
	}

	public static void associarFamiliaUtente(String NomeU, String NomeF) {		// Associar utente a familia
		if (procurarUtenteExistente(NomeU) == false) {
			System.out.println("O utente não existe na base de dados deste centro de saúde.\n");
		}
		else if (Familia.procurarFamiliaExistente(NomeF) == false) {
			System.out.println("A família não existe na base de dados deste centro de saúde.\n");
		}
		else if (procurarFamiliaAssociadaAUtente(NomeU) == true) {
			System.out.println("O utente selecionado já pertence a uma família!.\n");
		}
		else {
			for (int a = 0; a < ListaUtente.size(); a++) {
				if (ListaUtente.get(a).getNomeU().equals(NomeU)) {
					ListaUtente.get(a).setNomeF(NomeF);
					System.out.println("O utente foi associado à família com sucesso.\n");
				}
			}
		}
		Comandos.comandoPrograma();
	}

	public static void desassociarFamiliaUtente(String NomeU) {		// Desassociar utente da familia
		if (procurarUtenteExistente(NomeU) == false) {
			System.out.println("O utente não existe na base de dados deste centro de saúde.\n");
		}
		else if (procurarFamiliaAssociadaAUtente(NomeU) == false) {
			System.out.println("O utente selecionado não pertence a nenhuma família.\n");
		}
		else {
			for (int a = 0; a < ListaUtente.size(); a++) {
				if (ListaUtente.get(a).getNomeU().equals(NomeU)) {
					ListaUtente.get(a).setNomeF(null);
					System.out.println("O utente foi desassociado da família\n");
				}
			}
		}
		Comandos.comandoPrograma();
	}

	public static void listaUtente() {		// Listar utentes registados
		ArrayList<Utente> ListaOrdem = new ArrayList<Utente>();
		ArrayList<Utente> ListaJovem = new ArrayList<Utente>();
		ArrayList<Utente> ListaAdulto = new ArrayList<Utente>();
		ArrayList<Utente> ListaIdoso = new ArrayList<Utente>();

		if (ListaUtente.size() > 0) {
			for (int a = 0; a < ListaUtente.size(); a++) {
				if (ListaUtente.get(a).getNomeF() != null) {
					ListaOrdem.add(ListaUtente.get(a));
				}
			}

			for (int a = 0; a < ListaOrdem.size(); a++) {
				if (ListaOrdem.get(a).getFaixaEtaria().equals("Jovem")) {
					ListaJovem.add(ListaOrdem.get(a));
				}
				if (ListaOrdem.get(a).getFaixaEtaria().equals("Adulto")) {
					ListaAdulto.add(ListaOrdem.get(a));
				}
				if (ListaOrdem.get(a).getFaixaEtaria().equals("Idoso")) {
					ListaIdoso.add(ListaOrdem.get(a));
				}
			}
			ListaOrdem.clear();

			ListaJovem.sort(Comparator.comparing(Utente::getNomeU));
			ListaAdulto.sort(Comparator.comparing(Utente::getNomeU));
			ListaIdoso.sort(Comparator.comparing(Utente::getNomeU));
			ArrayList<Familia> ListaFamilia = (ArrayList<Familia>) Familia.ListaFamilia.stream().sorted(Comparator.comparing(Familia::getNomeF)).collect(Collectors.toList());

			for (int a = 0; a < ListaFamilia.size(); a++) {
				for (int b = 0; b < ListaJovem.size(); b++) {
					if (ListaFamilia.get(a).getNomeF().equals(ListaJovem.get(b).getNomeF())) {
						System.out.println(ListaJovem.get(b).getNomeF() + " " + ListaJovem.get(b).getFaixaEtaria() + " " + ListaJovem.get(b).getNomeU());
					}
				}
			}

			for (int a = 0; a < ListaFamilia.size(); a++) {
				for (int b = 0; b < ListaAdulto.size(); b++) {
					if (ListaFamilia.get(a).getNomeF().equals(ListaAdulto.get(b).getNomeF())) {
						System.out.println(ListaAdulto.get(b).getNomeF() + " " + ListaAdulto.get(b).getFaixaEtaria() + " " + ListaAdulto.get(b).getNomeU());
					}
				}
			}

			for (int a = 0; a < ListaFamilia.size(); a++) {
				for (int b = 0; b < ListaIdoso.size(); b++) {
					if (ListaFamilia.get(a).getNomeF().equals(ListaIdoso.get(b).getNomeF())) {
						System.out.println(ListaIdoso.get(b).getNomeF() + " " + ListaIdoso.get(b).getFaixaEtaria() + " " + ListaIdoso.get(b).getNomeU());
					}
				}
			}
			ListaJovem.clear();
			ListaAdulto.clear();
			ListaIdoso.clear();
			ListaFamilia.clear();

			for (int a = 0; a < ListaUtente.size(); a++) {
				if (ListaUtente.get(a).getNomeF() == null) {
					ListaOrdem.add(ListaUtente.get(a));
				}
			}

			for (int a = 0; a < ListaOrdem.size(); a++) {
				if (ListaOrdem.get(a).getFaixaEtaria().equals("Jovem")) {
					ListaJovem.add(ListaOrdem.get(a));
				}
				else if (ListaOrdem.get(a).getFaixaEtaria().equals("Adulto")) {
					ListaAdulto.add(ListaOrdem.get(a));
				}
				else if (ListaOrdem.get(a).getFaixaEtaria().equals("Idoso")) {
					ListaIdoso.add(ListaOrdem.get(a));
				}
			}
			ListaOrdem.clear();

			ListaJovem.sort(Comparator.comparing(Utente::getNomeU));
			ListaAdulto.sort(Comparator.comparing(Utente::getNomeU));
			ListaIdoso.sort(Comparator.comparing(Utente::getNomeU));

			for (int a = 0; a < ListaJovem.size(); a++) {
				System.out.println(ListaJovem.get(a).getFaixaEtaria() + " " + ListaJovem.get(a).getNomeU());
			}
			for (int a = 0; a < ListaAdulto.size(); a++) {
				System.out.println(ListaAdulto.get(a).getFaixaEtaria() + " " + ListaAdulto.get(a).getNomeU());
			}
			for (int a = 0; a < ListaIdoso.size(); a++) {
				System.out.println(ListaIdoso.get(a).getFaixaEtaria() + " " + ListaIdoso.get(a).getNomeU());
			}
			ListaJovem.clear();
			ListaAdulto.clear();
			ListaIdoso.clear();

		}
		else {
			System.out.println("Não existem utentes na base de dados deste centro de saúde.\n");
		}

		Comandos.comandoPrograma();
	}

	public static void listaUtentesEmFamilias(String NomeF) {
		ArrayList<Utente> ListaJovem = new ArrayList<Utente>();
		ArrayList<Utente> ListaAdulto = new ArrayList<Utente>();
		ArrayList<Utente> ListaIdoso = new ArrayList<Utente>();

		if (Familia.procurarFamiliaExistente(NomeF) == false) {
			System.out.println("A família não existe na base de dados deste centro de saúde.\n");
		}
		else {
			for (int a = 0; a < ListaUtente.size(); a++) {
				if (ListaUtente.get(a).getNomeF() != null) {
					if (ListaUtente.get(a).getFaixaEtaria().equals("Jovem")) {
						if (ListaUtente.get(a).getNomeF().equals(NomeF)) {
							ListaJovem.add(ListaUtente.get(a));
						}
					}
					else if (ListaUtente.get(a).getFaixaEtaria().equals("Adulto")) {
						if (ListaUtente.get(a).getNomeF().equals(NomeF)) {
							ListaAdulto.add(ListaUtente.get(a));
						}
					}
					else if (ListaUtente.get(a).getFaixaEtaria().equals("Idoso")) {
						if (ListaUtente.get(a).getNomeF().equals(NomeF)) {
							ListaIdoso.add(ListaUtente.get(a));
						}
					}
				}
			}

			ListaJovem.sort(Comparator.comparing(Utente::getNomeU));
			ListaAdulto.sort(Comparator.comparing(Utente::getNomeU));
			ListaIdoso.sort(Comparator.comparing(Utente::getNomeU));

			for (int a = 0; a < ListaJovem.size(); a++) {
				System.out.println(ListaJovem.get(a).getFaixaEtaria() + " " + ListaJovem.get(a).getNomeU());
			}
			for (int a = 0; a < ListaAdulto.size(); a++) {
				System.out.println(ListaAdulto.get(a).getFaixaEtaria() + " " + ListaAdulto.get(a).getNomeU());
			}
			for (int a = 0; a < ListaIdoso.size(); a++) {
				System.out.println(ListaIdoso.get(a).getFaixaEtaria() + " " + ListaIdoso.get(a).getNomeU());
			}

			ListaJovem.clear();
			ListaAdulto.clear();
			ListaIdoso.clear();
		}
		Comandos.comandoPrograma();
	}

	public static String procurarNomeDeFamiliaDoUtente(String NomeU) {
		for (int a = 0; a < ListaUtente.size(); a++) {
			if (ListaUtente.get(a).getNomeF() != null) {
				if (ListaUtente.get(a).getNomeU().equals(NomeU)) {
					return ListaUtente.get(a).getNomeF();
				}
			}
		}
		return "";
	}

	public static String procurarFaixaEtariaDoUtente(String NomeU) {
		for (int a = 0; a < ListaUtente.size(); a++) {
			if (ListaUtente.get(a).getNomeU().equals(NomeU)) {
				return ListaUtente.get(a).getFaixaEtaria();
			}
		}
		return "";
	}
}
