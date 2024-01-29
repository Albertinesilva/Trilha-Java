package residenciatic18.pratica8.services;

import java.util.ArrayList;
import java.util.List;

import residenciatic18.pratica8.entities.Fatura;
import residenciatic18.pratica8.entities.Imovel;
import residenciatic18.pratica8.entities.Pagamento;
import residenciatic18.pratica8.repositories.ImovelRepository;
import residenciatic18.pratica8.views.Views;

public class FaturaService {

	private static List<Fatura> listaFatura = new ArrayList<>();
	private ImovelRepository imovelRepository = newImovelRepository();

	public void registrarConsumo() {

		Imovel imovel = imovelRepository.buscaImovel();

		if (imovel == null) {
			Views.cxMsg("Imóvel não encontrado!");
			return;
		}

		while (true) {
			int valorLido;
			try {

				Views.limparTela();
				System.out.print("Informe a leitura realizada: ");
				valorLido = Views.scan.nextInt();

				if (imovel.getUltimaLeitura() > valorLido) {
					Views.cxMsg("A leitura atual não pode ser menor que a leitura antiga!");
					continue;
				}
				imovel.setUltimaLeitura(valorLido);
				novaFatura(imovel);
				Views.cxMsg("O consumo foi registrado e a fatura foi gerada!");

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

	}

	public static void novaFatura(Imovel imovel) {
		Fatura nova = new Fatura(imovel.getMatricula(), imovel.getUltimaLeitura(), imovel.getPenultimaLeitura());
		listaFatura.add(nova);
	}

	public void todasAsFaturas() {
		Views.limparTela();
		System.out.println("=============== TODAS AS FATURAS ===============");
		System.out.println("");

		for (Fatura f : listaFatura) {
			System.out.println(f.toString());
		}

		Views.pausar(Views.scan);
	}

	public void faturasEmAberto() {
		Views.limparTela();
		System.out.println("=============== FATURAS EM ABERTO ===============");
		System.out.println("");

		for (Fatura f : listaFatura) {
			if (!f.isQuitado())
				System.out.println(f.toString());
		}
		Views.pausar(Views.scan);
	}

	public Fatura obterFaturaPorMesEmissao() {

		Imovel imovel = imovelRepository.buscaImovel();

		if (imovel == null) {
			Views.cxMsg("Imóvel não encontrado!");
			return null;
		}

		int valorLido = 0;
		int k = 0;
		while (valorLido > 12 || valorLido < 1) {
			try {

				Views.limparTela();
				System.out.print("Informe o mês referente à fatura: ");
				valorLido = Views.scan.nextInt();

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			if (valorLido > 12 || valorLido < 1) {
				Views.cxMsg("O mês informado é inválido!");
				k++;
			}
			if (k == 3) {
				Views.cxMsg("Limite de tentativas excedidas! Tente novamente!");
			}
		}

		for (Fatura fatura : listaFatura) {
			if (fatura.getMatriculaImovel().equalsIgnoreCase(imovel.getMatricula())
					&& fatura.getDataEmissao().getMonthValue() == valorLido) {
				return fatura;
			}
		}
		return null;
	}

	public static void todosOsPagamentos() {

		Views.limparTela();
		System.out.println("=============== TODOS OS PAGAMENTOS ===============");
		System.out.println("");
		for (Fatura f : listaFatura) {
			System.out.println("===== IMÓVEL DE MATRÍCULA: " + f.getMatriculaImovel() + " =====");
			System.out.println("");
			for (Pagamento p : f.getPagamentos()) {
				System.out.println(p.toString());
			}
			System.out.println("");
		}

		Views.pausar(Views.scan);
	}

	public void pagamentosPorFatura() {

		Fatura encontrada = obterFaturaPorMesEmissao();

		if (encontrada == null) {
			Views.cxMsg("Não foi encontrado nenhuma fatura com as descrições informadas!");
			return;
		}

		Views.limparTela();
		System.out.println("=============== PAGAMENTOS RELACIONADOS À FATURA ===============");
		System.out.println("");
		for (Pagamento p : encontrada.getPagamentos()) {
			System.out.println(p.toString());
		}

		Views.pausar(Views.scan);
	}

	public void todosOsReembolsos() {

		Views.limparTela();
		System.out.println("=============== TODOS OS REEMBOLSOS ===============");
		System.out.println("");
		for (Fatura f : listaFatura) {
			System.out.println("===== IMÓVEL DE MATRÍCULA: " + f.getMatriculaImovel() + " =====");
			System.out.println("");
			System.out.println(f.getReembolso().toString());
			System.out.println("");
		}

		Views.pausar(Views.scan);
	}

	public void reembolsosPorFatura() {

		Fatura encontrada = obterFaturaPorMesEmissao();

		if (encontrada == null) {
			Views.cxMsg("Não foi encontrado nenhuma fatura com as descrições informadas!");
			return;
		}

		Views.limparTela();
		System.out.println("=============== REEMBOLSOS RELACIONADOS À FATURA ===============");
		System.out.println("");
		if (encontrada.getReembolso() != null)
			System.out.println(encontrada.getReembolso().toString());
		else
			System.out.println("Não há reembolsos para essa fatura!");

		Views.pausar(Views.scan);
	}

	public ImovelService newImovelRepository() {
		return new ImovelService();
	}

}