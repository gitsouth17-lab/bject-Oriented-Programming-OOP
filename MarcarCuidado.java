package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MarcarCuidado implements Serializable {
	private static final long serialVersionUID = 7709428230435542965L;
	String NomeU;
	String NomeP;
	String Servico;
	String Categoria;
	public static ArrayList<MarcarCuidado> ListaMarcacoes = new ArrayList<MarcarCuidado>();
	
	public MarcarCuidado(String NomeU, String NomeP, String Servico, String Categoria) {
		this.NomeU = NomeU;
		this.NomeP = NomeP;
		this.Servico = Servico;
		this.Categoria = Categoria;
	}
	
	public String getNomeUtente() {
		return this.NomeU;
	}
	public String getNomeProfissional() {
		return this.NomeP;
	}
	public String getServico() {
		return this.Servico;
	}
	public String getCategoria() {
		return this.Categoria;
	}
	public static boolean procurarMarcarCuidadoNoUtente(String NomeU) {
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			if (((MarcarCuidado) ListaMarcacoes.get(a)).getNomeUtente().equals(NomeU)) {
				return true;
			}
		}
		return false;
	}
	public static void desmarcarCuidadoNoUtente(String NomeU) {
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			if (((MarcarCuidado) ListaMarcacoes.get(a)).getNomeUtente().equals(NomeU)) {
				ListaMarcacoes.remove(a);
			}
		}
	}
	public static boolean procurarMarcarCuidadoNoProfissional(String NomeP, String Categoria) {
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			if (((MarcarCuidado) ListaMarcacoes.get(a)).getNomeProfissional().equals(NomeP) && ((MarcarCuidado) ListaMarcacoes.get(a)).getCategoria().equals(Categoria)) {
				return true;
			}
		}
		return false;
	}

	public static void marcarCuidadosAUtentes(String NomeU, Scanner a) {
		ArrayList<String> Categorias = new ArrayList<String>();
		Categorias.add("Medicina");
		Categorias.add("Enfermagem");
		Categorias.add("Auxiliar");
		ArrayList<String> Servicos = new ArrayList<String>();
		Servicos.add("Enfermagem");
		Servicos.add("Consulta");
		Servicos.add("PequenCirurgia");
		if (Utente.procurarUtenteExistente(NomeU) == false) {
			System.out.println("Não existe o utente selecionado neste centro de saúde! Por favor, insira um utente existente neste centro de saúde para marcar um serviço. \n");
			Comandos.comandoPrograma();
		}
		String Servico;
		Servico = a.nextLine().trim();
		if (Servico.length() == 0) {
			System.out.println("Não há serviços disponíveis neste momento!\n");
			Comandos.comandoPrograma();
		}
		if (!Servicos.contains(Servico)) {
			System.out.println("O serviço inserido é inválido neste centro de saúde! Por favor, insira um serviço válido(Enfermagem, Consulta ou PequenaCirurgia).\n");
			Comandos.comandoPrograma();
		}
		String[] ProfissionalArray = a.nextLine().trim().split(" ");
		if (!Categorias.contains(ProfissionalArray[0])) {
			System.out.println("A categoria inserida é inválida! Por favor, insira uma categoria válida (Medicina, Enfermagem ou Auxiliar).\n");
			Comandos.comandoPrograma();
		}
		if (Profissional.procurarProfissionalExistente(ProfissionalArray[1], ProfissionalArray[2]) == false) {
			System.out.println("O profissional inserido não existe neste centro de saúde! Por favor, insira um profissional existente neste centro de saúde.\n");
			Comandos.comandoPrograma();
		}
		if (Servico.equals("Enfermagem")) {
			if (ProfissionalArray[0].equals("Medicina")) {
				System.out.println("A categoria inserida é inválida!\n");
				Comandos.comandoPrograma();
			}
		}
		if (Servico.equals("Consulta")) {
			if (ProfissionalArray[0].equals("Auxiliar") || ProfissionalArray[0].equals("Enfermagem")) {
				System.out.println("A categoria inserida é inválida!\n");
				Comandos.comandoPrograma();
			}
		}
	   if (Servico.equals("PequenaCirurgia")) {
		   if(procurarMarcarCuidadoNoUtente(NomeU) == false) {
			   System.out.println("A sequência inserida é inválida!\n");
			   Comandos.comandoPrograma();
		   }
		   
		   
	   }
	   if (!Servico.equals("PequenaCirurgia")) {
		   if (procurarMarcarCuidadoNoUtente(NomeU) == true) { //Verificar se já houve uma pequena cirurgia antes, pois só pode marcar consulta depois 
			   
		   }
		   for(int b = ListaMarcacoes.size() - 1; b > -1; b--) {
			   if (!ListaMarcacoes.get(b).getNomeUtente().equals(NomeU)) 
				   continue;
			   
			   if (!ListaMarcacoes.get(b).getServico().equals("Consulta")) {
				   System.out.println("A sequência inserida é inválida!\n");
				   Comandos.comandoPrograma();
			   }
		   }
	   }
	   MarcarCuidado marcarUtente = new MarcarCuidado(NomeU, Servico.trim(), ProfissionalArray[1].trim(), ProfissionalArray[2].trim());
	   ListaMarcacoes.add(marcarUtente);
	   System.out.println("Os cuidados inseridos foram introduzidos com sucesso!\n");
	   marcarCuidadosAUtentes(NomeU, a);
	   Comandos.comandoPrograma();
	   
	}
	
	public static void deassociarCuidadosAUtentes(String NomeU) {
		if (Utente.procurarUtenteExistente(NomeU) == false) {
			System.out.println("O utente inserido não existe!\n");
			Comandos.comandoPrograma();
		}
		
		if (procurarMarcarCuidadoNoUtente(NomeU) == false) {
			System.out.println("O utente inserido não tem cuidados de saúde marcados.\n");
			Comandos.comandoPrograma();
		}
		desmarcarCuidadoNoUtente(NomeU);
		System.out.println("Os cuidados de saúde foram desmarcados com sucesso.\n");
		Comandos.comandoPrograma();
	  
	}
	public static void registarCuidadosMarcadosAoUtente(String NomeU) {
		if(Utente.procurarUtenteExistente(NomeU) == false) {
			System.out.println("O utente inserido não existe!\n");
			Comandos.comandoPrograma();
		} 
		if (procurarMarcarCuidadoNoUtente(NomeU) == false) {
			System.out.println("O utente inserido não tem cuidados de saúde marcados!\n");
			Comandos.comandoPrograma();
		}
		for(int a = 0; a < cuidadosParaUtente(NomeU).size(); a++) {
			System.out.println(cuidadosParaUtente(NomeU).get(a)+".\n");
		}
		Comandos.comandoPrograma();
	}
	public static void registarServicosMarcadosAoProfissional(String[] MeteComando) {
		String NomeP = MeteComando[1].trim();
		String Categoria = MeteComando[2].trim();
		
		if((Profissional.procurarProfissionalExistente(NomeP, Categoria) == false)) {
			System.out.println("O profissional de saúde inserido é inexistente. \n");
			Comandos.comandoPrograma();
		}
		if (!Profissional.obterACategoriaDoProfissional(NomeP).equals(Categoria)) {
			System.out.println("O profissional de saúde inserido é inexistente. \n");
			Comandos.comandoPrograma();
		}
		if (procurarMarcarCuidadoNoProfissional(NomeP, Categoria) == false) {
			System.out.println("O profissional de saúde não tem marcações feitas. \n");
			Comandos.comandoPrograma();
		}
		
		for (int a = 0; a < listaServicoQueProfissionalFaz(NomeP).size(); a++) {
			System.out.println(listaServicoQueProfissionalFaz(NomeP).get(a));
		}
		Comandos.comandoPrograma();
	}
	public static void registarMarcacoesParaOServico(String Servico) {
		for (int a = 0; a < listaServicoViaMarcacaoPrevia(Servico).size(); a++) {
			System.out.println(listaServicoViaMarcacaoPrevia(Servico).get(a) + ".\n");
		}
		Comandos.comandoPrograma();
	}
	public static void registarMarcacoesParaFamilia(String NomeF) {
		if (Familia.procurarFamiliaExistente(NomeF) == false) {
			System.out.println("A família inserida não existe!\n");
			Comandos.comandoPrograma();
		}
		 for (int a = 0; a < listaServicosParaAFamiliaMarcados(NomeF).size(); a++) {
			 System.out.println(listaServicosParaAFamiliaMarcados(NomeF).get(a) + ".\n");
		 }
		Comandos.comandoPrograma(); 
		
	}
	public static ArrayList <String> cuidadosParaUtente(String NomeU) {
		ArrayList<String> Lista = new ArrayList<String>();
		ArrayList<String> PequenasCirurgias = new ArrayList<String>();
		ArrayList<String> Consultas = new ArrayList<String>();
		ArrayList<String> Enfermagens = new ArrayList<String>();
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			if (((MarcarCuidado) ListaMarcacoes.get(a)).getServico().equals("Consulta")) {
				Consultas.add(((MarcarCuidado)ListaMarcacoes.get(a)).getNomeProfissional());
				break;
			} else if (((MarcarCuidado) ListaMarcacoes.get(a)).getServico().equals("Enfermagem")) {
				Enfermagens.add(((MarcarCuidado) ListaMarcacoes.get(a)).getNomeProfissional());
				break;
			} else if (((MarcarCuidado) ListaMarcacoes.get(a)).getServico().equals("PequenaCirurgia")) {
				PequenasCirurgias.add(((MarcarCuidado) ListaMarcacoes.get(a)).getNomeProfissional());
				break;
			}
		}
		Collections.sort(PequenasCirurgias);
		Collections.sort(Consultas);
		Collections.sort(Enfermagens);
		for (int b = 0; b < PequenasCirurgias.size(); b++) {
			PequenasCirurgias.set(b, "Pequena Cirurgia: " + PequenasCirurgias.get(b));
		}
		for (int b = 0; b < Consultas.size(); b++) {
			Consultas.set(b, "Consulta: " + Consultas.get(b));
		}
		for (int b = 0; b < Enfermagens.size(); b++) {
			Enfermagens.set(b, "Enfermagem:" + Enfermagens.get(b));
		}
		Lista.addAll(PequenasCirurgias);
		Lista.addAll(Consultas);
		Lista.addAll(Enfermagens);
		return Lista;
	
}
	public static ArrayList<String> listaServicoViaMarcacaoPrevia(String Servico) {
		ArrayList<String> servicos = new ArrayList<String>();
		ArrayList<String> lista = new ArrayList<String>();
		servicos.add("Consulta");
		servicos.add("Enfermagem");
		servicos.add("PequenaCirurgia");
		if (!servicos.contains(Servico)) {
			lista.add("Servicos indisponíveis\n");
			return lista;
		}
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			if ((ListaMarcacoes.get(a)).getServico().equals(Servico)) {
				lista.add((ListaMarcacoes.get(a)).getNomeUtente() + " " + (ListaMarcacoes.get(a)).getNomeProfissional() + " " + (ListaMarcacoes.get(a)).getServico());
			}
		}
		ArrayList<String> medico = new ArrayList<String>();
		ArrayList<String> auxiliar = new ArrayList<String>();
		ArrayList<String> enfermeiro = new ArrayList<String>();
		for (int a = 0; a < lista.size(); a++) {
			if(lista.get(a).trim().split(" ")[0].equals("Medicina")) { //ALTERAR
				medico.add(lista.get(a).split(" ")[1] + " " + lista.get(a).split(" ")[2]);
				break;
			} else if(lista.get(a).trim().split(" ")[0].equals("Auxiliar")) {
				auxiliar.add(lista.get(a).split(" ")[1] + " " + lista.get(a).split(" ")[2]);
				break;
			} else if(lista.get(a).trim().split(" ")[0].equals("Enfermagem")) {
				enfermeiro.add(lista.get(a).split(" ")[1] + " " + lista.get(a).split(" ")[2]);
				break;
			}
		}
		lista.clear();
		Collections.sort(auxiliar);
		Collections.sort(enfermeiro);
		Collections.sort(medico);
		for (int a = 0; a < auxiliar.size(); a++) {
			auxiliar.set(a, "Auxiliar:" + auxiliar.get(a));
		}
		for (int a = 0; a < enfermeiro.size(); a++) {
			enfermeiro.set(a, "Enfermagem:" + enfermeiro.get(a));
		}
		for (int a = 0; a < medico.size(); a++) {
			medico.set(a,  "Medicina:" + medico.get(a));
		}
		lista.addAll(auxiliar);
		lista.addAll(enfermeiro);
		lista.addAll(medico);
		if (lista.size() == 0) {
			lista.add("Sem serviços marcados.\n");
		}
		return lista;
	}
	public static ArrayList<String> listaServicoQueProfissionalFaz(String NomeP) {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> pequenaCirurgias = new ArrayList<String>();
		ArrayList<String> consultas = new ArrayList<String>();
		ArrayList<String> enfermeiro = new ArrayList<String>();
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			if ((ListaMarcacoes.get(a)).getServico().equals("PequenaCirurgia")) {
				pequenaCirurgias.add((ListaMarcacoes.get(a)).getNomeUtente());
			} else if((ListaMarcacoes.get(a)).getServico().equals("Consulta")) {
				consultas.add((ListaMarcacoes.get(a)).getNomeUtente());
			} else if((ListaMarcacoes.get(a)).getServico().equals("Enfermagem")) {
				enfermeiro.add((ListaMarcacoes.get(a)).getNomeUtente());
			}
		}
		Collections.sort(pequenaCirurgias);
		Collections.sort(consultas);
		Collections.sort(enfermeiro);
		for (int a = 0; a < pequenaCirurgias.size(); a++) {
			pequenaCirurgias.set(a, "PequenaCirurgia:" + pequenaCirurgias.get(a));
		} 
		for (int a = 0; a < consultas.size(); a++) {
			consultas.set(a, "Consulta:" + consultas.get(a));
		}
		for (int a = 0; a < enfermeiro.size(); a++) {
			enfermeiro.set(a, "Enfermagem:" + enfermeiro.get(a));
		}
		lista.addAll(pequenaCirurgias);
		lista.addAll(consultas);
		lista.addAll(enfermeiro);
		return lista;
	}
	public static ArrayList<String> listaServicosParaAFamiliaMarcados(String NomeF) {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> listaJovem = new ArrayList<String>();
		ArrayList<String> listaAdulto = new ArrayList<String>();
		ArrayList<String> listaIdoso = new ArrayList<String>();
		
		for (int a = 0; a < ListaMarcacoes.size(); a++) {
			String faixaEtaria = Utente.procurarFaixaEtariaDoUtente(ListaMarcacoes.get(a).getNomeUtente());
			if (!Utente.procurarNomeDeFamiliaDoUtente(ListaMarcacoes.get(a).getNomeUtente()).equals(NomeF)) {
				continue;
			}
			
			if(faixaEtaria.equals("Jovem")) { //ALTERAR
				listaJovem.add(ListaMarcacoes.get(a).getNomeUtente() + " " + ListaMarcacoes.get(a).getServico() + " " + ListaMarcacoes.get(a).getCategoria() + " " + ListaMarcacoes.get(a).getNomeProfissional());
			} else if(faixaEtaria.equals("Adulto")) {
				listaAdulto.add(ListaMarcacoes.get(a).getNomeUtente() + " " + ListaMarcacoes.get(a).getServico() + " " + ListaMarcacoes.get(a).getCategoria() + " " + ListaMarcacoes.get(a).getNomeProfissional());
			} else if(faixaEtaria.equals("Idoso")) {
				listaIdoso.add(ListaMarcacoes.get(a).getNomeUtente() + " " + ListaMarcacoes.get(a).getServico() + " " + ListaMarcacoes.get(a).getCategoria() + " " + ListaMarcacoes.get(a).getNomeProfissional());
			}
		}
		Collections.sort(listaJovem);
		Collections.sort(listaAdulto);
		Collections.sort(listaIdoso);
		lista.addAll(listaJovem);
		lista.addAll(listaAdulto);
		lista.addAll(listaIdoso);
		
		if (lista.size() == 0) {
			lista.add("A família selecionada não tem cuidados de saúde marcados neste centro de saúde!");
			return lista;
		}
		
		return lista;
	}
}