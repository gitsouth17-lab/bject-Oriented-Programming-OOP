package projeto;

import java.util.Scanner;

public class Comandos {

	public static void comandoPrograma() {
		Scanner input = new Scanner(System.in);
		String[] MeteComando = input.nextLine().trim().split(" ");

		if (MeteComando[0].equals("RU") && MeteComando.length == 3) {
			Utente.UtenteRegisto(MeteComando[1], MeteComando[2]);
		}
		else if (MeteComando[0].equals("L") && MeteComando.length == 1) {
			Main.lerPrograma();
		}
		else if (MeteComando[0].equals("G") && MeteComando.length == 1) {
			Main.gravarPrograma();
		}
		else if (MeteComando[0].equals("RP") && MeteComando.length == 3) {
			Profissional.profissionalSucesso(MeteComando);
		}
		else if (MeteComando[0].equals("LU") && MeteComando.length == 1) {
			Utente.listaUtente();
		}
		else if (MeteComando[0].equals("RF") && MeteComando.length == 2) {
			Familia.familiaRegisto(MeteComando[1]);
		}
		else if (MeteComando[0].equals("AF") && MeteComando.length == 3) {
			Utente.associarFamiliaUtente(MeteComando[1], MeteComando[2]);
		}
		else if (MeteComando[0].equals("MC") && MeteComando.length == 2) {
			MarcarCuidado.marcarCuidadosAUtentes(MeteComando[1], input);
		}
		else if (MeteComando[0].equals("CC") && MeteComando.length == 2) {
			MarcarCuidado.deassociarCuidadosAUtentes(MeteComando[0]);
		}
		else if (MeteComando[0].equals("DF") && MeteComando.length == 2) {
			Utente.desassociarFamiliaUtente(MeteComando[1]);
		}
		else if (MeteComando[0].equals("LF") && MeteComando.length == 1) {
			Familia.listarFamilia();
		}
		else if (MeteComando[0].equals("LP") && MeteComando.length == 1) {
			Profissional.listarProfissional();
		}
		else if (MeteComando[0].equals("MF") && MeteComando.length == 2) {
			Utente.listaUtentesEmFamilias(MeteComando[1]);
		}
		else if (MeteComando[0].equals("LCU") && MeteComando.length == 2) {
			MarcarCuidado.registarCuidadosMarcadosAoUtente(MeteComando[1]);
		}
		else if (MeteComando[0].equals("LCF") && MeteComando.length == 2) {
			MarcarCuidado.registarMarcacoesParaFamilia(MeteComando[1]);
		}
		else if (MeteComando[0].equals("LSP") && MeteComando.length == 3) {
			MarcarCuidado.registarServicosMarcadosAoProfissional(MeteComando);
		}
		else if (MeteComando[0].equals("LMS") && MeteComando.length == 2) {
			MarcarCuidado.registarMarcacoesParaOServico(MeteComando[1]);
		}
		else {
			System.out.print("Instrução inválida! \n");
			comandoPrograma();
		}
		input.close();
	}
}