package residenciatic18.pratica8.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import residenciatic18.pratica8.entities.FalhaDistribuicao;
import residenciatic18.pratica8.entities.FalhaGeracao;
import residenciatic18.pratica8.entities.Imovel;
import residenciatic18.pratica8.repositories.FalhaRepository;
import residenciatic18.pratica8.repositories.ImovelRepository;
import residenciatic18.pratica8.repositories.ReparoRepository;
import residenciatic18.pratica8.utils.Utils;

public class FalhaService implements FalhaRepository {

    public static List<FalhaDistribuicao> falhasDist = new ArrayList<>();
    public static List<FalhaGeracao> falhasGer = new ArrayList<>();
    ReparoRepository reparoRepository = new ReparoService();

    public void cadastrarFalhaDistribuicao() {

        Utils.limparTela();
        System.out.println("\n\t===== CADASTRO DE FALHA DE DISTRIBUIÇÃO=====");

        System.out.println("\n\tA falha foi reportada por algum cliente? (S/N)");
        String resposta = Utils.scan.nextLine();
        String matriculaImovel = null;
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("\n\tDigite a matrícula do imóvel: ");
            matriculaImovel = Utils.scan.nextLine();

            // Imovel imovel = ImovelService.buscaImovel();

            // if (imovel == null) {
            // Utils.cxMsg("Imóvel não encontrado!");
            // return;
            // }
            // matriculaImovel = imovel.getMatricula();
        }

        System.out.print("\n\tDigite a descrição da falha: ");
        String descricao = Utils.scan.nextLine();

        System.out.print("\n\tDigite a previsão de conclusão: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate previsaoConclusao = LocalDate.parse(Utils.scan.nextLine(), formatter);

        System.out.print("\n\tDigite a data de início: ");
        LocalDate dataInicio = LocalDate.parse(Utils.scan.nextLine(), formatter);

        FalhaDistribuicao falhaDist = new FalhaDistribuicao(matriculaImovel, descricao, previsaoConclusao, dataInicio,
                null);

        reparoRepository.cadastrarReparo(falhaDist);

        falhasDist.add(falhaDist);
    }

    public void cadastrarFalhaGeracao() {

        ImovelRepository imovelRepository = new ImovelService();

        Utils.limparTela();
        System.out.println("\n\t===== CADASTRO DE FALHA DE GERAÇÃO =====");

        System.out.println("\n\tA falha foi reportada por algum cliente? (S/N)");
        String resposta = Utils.scan.nextLine();
        String matriculaImovel = null;
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("\n\tDigite a matrícula do imóvel: ");
            Imovel imovel = imovelRepository.buscaImovel();

            if (imovel == null) {
                Utils.cxMsg("Imóvel não encontrado!");
                return;
            }

            matriculaImovel = imovel.getMatricula();
        }

        System.out.print("\n\tDigite a descrição da falha: ");
        String descricao = Utils.scan.nextLine();

        System.out.print("\n\tDigite a previsão de conclusão: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate previsaoConclusao = LocalDate.parse(Utils.scan.nextLine(), formatter);

        System.out.print("\n\tDigite a data de início: ");
        LocalDate dataInicio = LocalDate.parse(Utils.scan.nextLine(), formatter);

        FalhaGeracao falhaGer = new FalhaGeracao(matriculaImovel, descricao, previsaoConclusao, dataInicio, null);

        falhasGer.add(falhaGer);
    }

    public void listar() {

        Utils.limparTela();
        System.out.println("\n\t===== LISTA DE FALHAS =====");
        System.out.print("\n\t===== FALHAS DE DISTRIBUIÇÃO =====");
        for (FalhaDistribuicao falhaDist : falhasDist) {
            System.out.println(falhaDist.toString());
        }
        System.out.print("\n\t===== FALHAS DE GERAÇÃO =====");
        for (FalhaGeracao falhaGer : falhasGer) {
            System.out.println(falhaGer.toString());
        }
        System.out.println("\t===== FIM DA LISTA =====");
        Utils.pausar(Utils.scan);
    }

    public void editar() {

        Utils.limparTela();
        System.out.println("\n\t===== EDITAR FALHA =====");
        System.out.print("\n\t===== FALHAS DE DISTRIBUIÇÃO =====");
        for (FalhaDistribuicao falhaDist : falhasDist) {
            System.out.println(falhaDist.toString());
        }
        System.out.print("\n\t===== FALHAS DE GERAÇÃO =====");
        for (FalhaGeracao falhaGer : falhasGer) {
            System.out.println(falhaGer.toString());
        }
        System.out.println("\n\t===== FIM DA LISTA =====");
        System.out.print("\n\tDigite o ID da falha que deseja editar: ");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();
        for (FalhaDistribuicao falhaDist : falhasDist) {
            if (falhaDist.getId() == id) {
                System.out.print("\n\tDigite a nova data de fim: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataFim = LocalDate.parse(Utils.scan.nextLine(), formatter);
                falhaDist.setDataFim(dataFim);
                System.out.println("\n\t===== FALHA EDITADA =====");
                System.out.println(falhaDist.toString());
                Utils.pausar(Utils.scan);
                return;
            }
        }
        for (FalhaGeracao falhaGer : falhasGer) {
            if (falhaGer.getId() == id) {
                System.out.print("\n\tDigite a nova data de fim: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataFim = LocalDate.parse(Utils.scan.nextLine(), formatter);
                falhaGer.setDataFim(dataFim);
                System.out.println("\n\t===== FALHA EDITADA =====");
                System.out.println(falhaGer.toString());
                Utils.pausar(Utils.scan);
                return;
            }
        }
        System.out.println("\n\t===== FALHA NÃO ENCONTRADA =====");
        Utils.pausar(Utils.scan);

    }

    public FalhaDistribuicao buscarFalhaDistribuicao() {

        Utils.limparTela();
        System.out.println("\n\t===== BUSCAR FALHA DE DISTRIBUIÇÃO =====");
        System.out.print("\n\tDigite o ID da falha que deseja buscar: ");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();
        for (FalhaDistribuicao falhaDist : falhasDist) {
            if (falhaDist.getId() == id) {
                System.out.println("\n\t===== FALHA ENCONTRADA =====");
                System.out.println(falhaDist.toString());
                Utils.pausar(Utils.scan);
                return falhaDist;
            }
        }
        System.out.println("\n\t===== FALHA NÃO ENCONTRADA =====");
        Utils.pausar(Utils.scan);
        return null;
    }

}