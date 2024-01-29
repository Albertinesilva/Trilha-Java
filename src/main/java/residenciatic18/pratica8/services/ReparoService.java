package residenciatic18.pratica8.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import residenciatic18.pratica8.entities.Falha;
import residenciatic18.pratica8.entities.Reparo;
import residenciatic18.pratica8.repositories.ReparoRepository;
import residenciatic18.pratica8.utils.Utils;

public class ReparoService implements ReparoRepository {

    private static ArrayList<Reparo> listaReparos = new ArrayList<>();

    @Override
    public void listarRaparosAbertos() {

        Utils.limparTela();
        List<Reparo> reparosAbertos = new ArrayList<Reparo>();
        reparosAbertos = listaReparos.stream().filter(reparo -> !reparo.getConcluido()).collect(Collectors.toList());
        int i = 0;
        System.out.println("\n\t=====REPAROS NÃO CONCLUIDOS=====");
        for (Reparo reparo : reparosAbertos) {
            i++;
            System.out.println(i + " - " + reparo.toString());
        }
        Utils.pausar(Utils.scan);
    }

    @Override
    public void listarReparos() {

        int i = 0;
        Utils.limparTela();
        System.out.println("\n\t=====TODOS OS  REPAROS=====");
        for (Reparo reparo : listaReparos) {
            i++;
            System.out.println(i + " - " + reparo.toString());
        }
        Utils.pausar(Utils.scan);
    }

    @Override
    public void encerraReparo() {

        Utils.limparTela();
        System.out.println("\n\t======ENCERRANDO REPARO=======");
        List<Reparo> reparosAbertos = new ArrayList<Reparo>();
        reparosAbertos = listaReparos.stream().filter(reparo -> !reparo.getConcluido()).collect(Collectors.toList());

        if (reparosAbertos.isEmpty()) {
            System.out.println("\n\tNão há reparos não concluídos.");
            return;
        }

        int i = 0;
        System.out.println("\n\t=====REPAROS NÃO CONCLUIDOS=====");
        for (Reparo reparo : reparosAbertos) {
            i++;
            System.out.println(i + " - " + reparo.toString());
        }
        System.out.println("\n\tDigite o indice do reparo que deseja finalizar");
        int indice = Utils.scan.nextInt();
        Reparo reparo = reparosAbertos.get(indice - 1);
        reparo.setConcluido(true);
        reparo.setDataFim(LocalDate.now());

        System.out.println("\n\tO reparo realizado resolveu a falha? (sim - 1/ nao - 0)");
        int resposta = Utils.scan.nextInt();
        if (resposta == 1)
            return;
        else {
            Utils.limparTela();
            System.out.println("\n\tCRIANDO REPARO AUXILIAR");
            System.out.println("Digite a descrição do reparo auxiliar");
            Utils.scan.nextLine();
            String descricao = Utils.scan.nextLine();
            System.out.print("\n\tDigite a previsão de conclusão: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate previsaoConclusao = LocalDate.parse(Utils.scan.nextLine(), formatter);
            reparo.setReparoAuxiliar(new Reparo(descricao, previsaoConclusao, LocalDate.now(), reparo.getFalha()));
            listaReparos.add(reparo.getReparoAuxiliar());
        }
        Utils.pausar(Utils.scan);
    }

    @Override
    public void cadastrarReparo(Falha falha) {

        Utils.limparTela();
        System.out.println("\n\t===== CADASTRO DE REPARO=====");

        System.out.print("\n\tDigite a descrição do reparo: ");
        String descricao = Utils.scan.nextLine();

        System.out.print("\n\tDigite a previsão de conclusão: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate previsaoConclusao = LocalDate.parse(Utils.scan.nextLine(), formatter);

        Reparo novoReparo = new Reparo(descricao, previsaoConclusao, LocalDate.now(), falha);
        listaReparos.add(novoReparo);
        Utils.pausar(Utils.scan);
    }
}