package residenciatic18.pratica8.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import residenciatic18.pratica8.entities.Cliente;
import residenciatic18.pratica8.repositories.ClienteRepository;
import residenciatic18.pratica8.views.Views;


public class ClienteService implements ClienteRepository {

  public static List<Cliente> clientes = new ArrayList<>();

  @Override
  public void adcionar(Cliente cliente) 
  {
    clientes.add(cliente);
  }

  public void cadastrar() 
  {
    Views.limparTela();
    System.out.println("\n\t===== CADASTRO DE CLIENTE =====");

    System.out.print("\n\tNome do Cliente: ");
    String nome = Views.scan.nextLine();

    System.out.print("\n\tDigite o CPF: ");
    String cpf = Views.scan.nextLine();

    clientes.add(new Cliente(nome, cpf));

    Views.limparTela();
    System.out.println("\n\tCliente cadastrado com sucesso!");
    Views.pausar(Views.scan);
  }

  public void listar() 
  {
    Views.limparTela();
    System.out.print("\n\t===== LISTAGEM DE CLIENTES =====");

    if (clientes.size() > 0) {
      for (Cliente cliente : clientes) {
        System.out.println(cliente.toString()); // Chama explicitamente o método toString
        System.out.print("\t================================");
      }
    } else {
      System.out.println("\n\tNão há clientes cadastrados!");
    }

    Views.pausar(Views.scan);
  }

  public void editar() 
  {

    Views.limparTela();
    System.out.println("\n\t===== EDIÇÃO DE CLIENTE =====");

    System.out.print("\n\tDigite o CPF do cliente: ");
    String cpf = Views.scan.nextLine();

    for (Cliente cliente : clientes) {
      if (cliente.getCpf().equals(cpf)) {

        Views.limparTela();
        System.out.print("\n\t===== DADOS DO CLIENTE =====");
        System.out.println(cliente.toString());

        while (true) {
          try {
            System.out.print("\n\tDeseja realmente editar este cliente? (S/N): ");
            String opcao = Views.scan.nextLine();

            if (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
              throw new InputMismatchException("Opção inválida. Digite 'S' para confirmar ou 'N' para cancelar.");
            }

            if (opcao.equalsIgnoreCase("s")) {
              Views.limparTela();
              System.out.print("\n\tDigite o novo nome do cliente: ");
              String nome = Views.scan.nextLine();

              System.out.println("\n\tDigite o novo CPF: ");
              cpf = Views.scan.nextLine();

              cliente.setNome(nome);
              cliente.setCpf(cpf);

              Views.limparTela();
              System.out.print("\n\t===== CLIENTE EDITADO =====");
              System.out.println(cliente.toString());
              System.out.println("\n\tCliente editado com sucesso!");
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
    System.out.println("\n\tOps, Cliente não encontrado!..");
    Views.pausar(Views.scan);
    return;
  }

  public void excluir() 
  {

    Views.limparTela();
    System.out.println("\n\t===== EXCLUSÃO DE CLIENTE =====");

    System.out.print("\n\tDigite o CPF do cliente: ");
    String cpf = Views.scan.nextLine();

    for (Cliente cliente : clientes) {
      if (cliente.getCpf().equals(cpf)) {

        Views.limparTela();
        System.out.print("\n\t===== DADOS DO CLIENTE =====");
        System.out.println(cliente.toString());

        while (true) {
          try {
            System.out.print("\n\tDeseja realmente excluir este cliente? (S/N): ");
            String opcao = Views.scan.nextLine();

            if (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
              throw new InputMismatchException("Opção inválida. Digite 'S' para confirmar ou 'N' para cancelar.");
            }

            if (opcao.equalsIgnoreCase("s")) {
              Views.limparTela();
              clientes.remove(cliente);
              System.out.println("\n\tCliente removido com sucesso!");
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
      } else {
        System.out.println("\n\tOps, Cliente não encontrado!..");
        Views.pausar(Views.scan);
        return;

      }
    }
  }

  public void pesquisar() 
  {
    Views.limparTela();
    System.out.println("\n\t===== PESQUISA DE CLIENTE =====");

    try {
      System.out.print("\n\tDigite o CPF do cliente: ");
      String cpf = Views.scan.nextLine();

      Cliente clienteEncontrado = null;

      for (Cliente cliente : clientes) {
        if (cliente.getCpf().equals(cpf)) {
          clienteEncontrado = cliente;
          break;
        }
      }

      if (clienteEncontrado != null) {
        Views.limparTela();
        System.out.print("\n\t===== DADOS DO CLIENTE =====");
        System.out.println(clienteEncontrado.toString());
        Views.pausar(Views.scan);
      } else {
        throw new NoSuchElementException("\n\tOps, Cliente não encontrado!..");
      }
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      Views.pausar(Views.scan);
    } catch (Exception e) {
      System.out.println("\n\tOps, ocorreu um erro inesperado: " + e.getMessage());
      Views.pausar(Views.scan);
    }
  }

}