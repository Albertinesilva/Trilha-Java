package residenciatic18.pratica8.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import residenciatic18.pratica8.entities.Imovel;
import residenciatic18.pratica8.repositories.ImovelRepository;
import residenciatic18.pratica8.views.Views;

public class ImovelService implements ImovelRepository {

  public static List<Imovel> imoveis = new ArrayList<Imovel>();

  public void adcionar(Imovel imovel) {
    imoveis.add(imovel);
  }

  public void cadastrar() {
    Views.limparTela();
    System.out.println("\n\t===== CADASTRO DE IMÓVEL =====");

    System.out.print("\n\tMatrícula do Imóvel: ");
    String matricula = Views.scan.nextLine();

    System.out.print("\n\tEndereço do Imóvel: ");
    String endereco = Views.scan.nextLine();

    int ultimaLeitura = obterInteiroValido("\n\tLeitura atual (em kWh): ");

    imoveis.add(new Imovel(matricula, endereco, ultimaLeitura));

    Views.limparTela();
    System.out.println("\n\tImóvel cadastrado com sucesso!");
    Views.pausar(Views.scan);
  }

  public void listar() {

    Views.limparTela();
    System.out.print("\n\t===== LISTAGEM DE IMÓVEIS =====");

    if (imoveis.size() > 0) {
      for (Imovel imovel : imoveis) {
        System.out.println(imovel.toString()); // Chama explicitamente o método toString
        System.out.print("\t================================");
      }
    } else {
      System.out.println("\n\tNão há imóveis cadastrados!");
    }

    Views.pausar(Views.scan);
  }

  public void editar() {

    Views.limparTela();
    System.out.println("\n\t===== EDIÇÃO DE IMOVEL =====");

    if (imoveis.size() > 0) {
      System.out.print("\n\tDigite a matrícula do imóvel: ");
      String matricula = Views.scan.nextLine();

      for (Imovel imovel : imoveis) {
        if (imovel.getMatricula().equals(matricula)) {
          Views.limparTela();
          System.out.print("\n\t===== DADOS DO IMOVEL =====");
          System.out.println(imovel.toString());
          System.out.println("\t===========================");

          while (true) {
            try {
              System.out.print("\n\tDeseja realmente editar este imóvel? (S/N): ");
              String opcao = Views.scan.nextLine();

              if (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
                throw new InputMismatchException("Opção inválida. Digite 'S' para confirmar ou 'N' para cancelar.");
              }

              if (opcao.equalsIgnoreCase("s")) {
                Views.limparTela();
                System.out.print("\n\tDigite o novo endereço do imóvel: ");
                String endereco = Views.scan.nextLine();

                int penultimaLeitura = obterInteiroValido(
                    "\n\tDigite a nova leitura da penúltima leitura (em kWh): ");
                int ultimaLeitura = obterInteiroValido("\n\tDigite a nova leitura da última leitura (em kWh): ");

                imovel.setEndereco(endereco);
                imovel.setUltimaLeitura(ultimaLeitura);
                imovel.setPenultimaLeitura(penultimaLeitura);

                Views.limparTela();
                System.out.print("\n\t===== IMOVEL EDITADO =====");
                System.out.println(imovel.toString());
                System.out.print("\t==========================");
                System.out.println("\n\tImóvel editado com sucesso!");
                Views.pausar(Views.scan);
                return;
              } else {
                System.out.println("\n\tOperação cancelada!");
                Views.pausar(Views.scan);
                return;
              }
            } catch (InputMismatchException e) {
              System.out.println("\n\t" + e.getMessage());
              // Limpar o buffer do scanner antes de continuar o loop
              Views.scan.nextLine();
            }
          }
        }
      }

      Views.limparTela();
      System.out.println("\n\tImóvel não encontrado!");
    } else {
      Views.limparTela();
      System.out.println("\n\tNão há imóveis cadastrados!");
    }
    Views.pausar(Views.scan);
  }

  public void excluir() {

    Views.limparTela();
    System.out.println("\n\t===== EXCLUSÃO DE IMOVEL =====");

    if (imoveis.size() > 0) {
      System.out.print("\n\tDigite a matrícula do imóvel: ");
      String matricula = Views.scan.nextLine();

      // boolean imovelEncontrado = false;

      for (Imovel imovel : imoveis) {
        if (imovel.getMatricula().equals(matricula)) {
          Views.limparTela();
          System.out.print("\n\t===== DADOS DO IMOVEL =====");
          System.out.println(imovel.toString());
          System.out.print("\t===========================");

          while (true) {
            try {
              System.out.print("\n\tDeseja realmente excluir este imóvel? (S/N): ");
              String opcao = Views.scan.nextLine();

              if (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
                throw new InputMismatchException("Opção inválida. Digite 'S' para confirmar ou 'N' para cancelar.");
              }

              if (opcao.equalsIgnoreCase("s")) {
                Views.limparTela();
                imoveis.remove(imovel);
                System.out.println("\n\tImóvel excluído com sucesso!");
                Views.pausar(Views.scan);
                return;

              } else {
                System.out.println("\n\tOperação cancelada!");
                Views.pausar(Views.scan);
                return;
              }
            } catch (InputMismatchException e) {
              System.out.println("\n\t" + e.getMessage());
              // Limpar o buffer do scanner antes de continuar o loop
              Views.scan.nextLine();
            }
          }
        }
      }

      Views.limparTela();
      System.out.println("\n\tImóvel não encontrado!");
    } else {
      Views.limparTela();
      System.out.println("\n\tNão há imóveis cadastrados!");
    }
    Views.pausar(Views.scan);
  }

  public void pesquisar() {

    Views.limparTela();
    System.out.print("\n\t===== PESQUISA DE IMÓVEL =====\n");

    if (imoveis.size() > 0) {
      System.out.print("\n\tDigite a matrícula do imóvel: ");
      String matricula = Views.scan.nextLine();

      boolean imovelEncontrado = false;

      for (Imovel imovel : imoveis) {
        if (imovel.getMatricula().equals(matricula)) {
          Views.limparTela();
          System.out.print("\n\t===== DADOS DO IMOVEL =====");
          System.out.println(imovel.toString());
          System.out.println("\t===========================");
          imovelEncontrado = true;
          break;
        }
      }

      if (!imovelEncontrado) {
        Views.limparTela();
        System.out.println("\n\tImóvel não encontrado!");
      }

    } else {
      Views.limparTela();
      System.out.println("\n\tNão há imóveis cadastrados!");
    }
    Views.pausar(Views.scan);
  }

  private static int obterInteiroValido(String prompt) {

    int valor = 0;
    boolean valorValido = false;

    do {
      try {
        System.out.print(prompt);
        valor = Integer.parseInt(Views.scan.nextLine());
        valorValido = true; // Se chegou aqui, o valor é válido

      } catch (NumberFormatException e) {
        Views.limparTela();
        System.out.println("\n\tPor favor, digite um valor inteiro válido.");
        Views.pausar(Views.scan);
      }
    } while (!valorValido);

    return valor;
  }

  public Imovel buscaImovel() {

    Views.limparTela();
    System.out.print("\n\t===== PESQUISA DE IMÓVEL POR MATRÍCULA =====");

    if (imoveis.size() > 0) {

      while (true) {

        System.out.print("\n\tDigite a matrícula do imóvel: ");
        String matricula = Views.scan.nextLine();
        Views.limparTela();

        boolean imovelEncontrado = false;

        for (Imovel imovel : imoveis) {

          if (imovel.getMatricula().equals(matricula)) {
            Views.limparTela();
            System.out.print("\n\t===== DADOS DO IMOVEL =====");
            System.out.println(imovel.toString());
            System.out.println("\t===========================");

            // Pergunta se é o Imovel correto
            while (true) {
              System.out.print("\n\tEsse é o Imovel correto? (S/N): ");
              String resposta = Views.scan.nextLine();

              if (resposta.equalsIgnoreCase("S")) {
                Views.pausar(Views.scan);
                return imovel;
              } else

              if (resposta.equalsIgnoreCase("N")) {
                imovelEncontrado = true;
                break; // Retorna ao loop anterior para pedir uma nova matrícula

              } else {
                Views.limparTela();
                System.out.println("\n\tOpção inválida. Digite 'S' para confirmar ou 'N' para cancelar.");
              }
            }
          }
        }
        if (!imovelEncontrado) {
          Views.limparTela();
          System.out.println("\n\tImóvel não encontrado!");
        }
      }
    } else {
      Views.limparTela();
      System.out.println("\n\tNão há imóveis cadastrados!");
    }

    Views.pausar(Views.scan);
    return null; // Se não encontrou, retorna null
  }

}