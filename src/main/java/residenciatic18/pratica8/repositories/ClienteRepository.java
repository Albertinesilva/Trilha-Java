package residenciatic18.pratica8.repositories;

import residenciatic18.pratica8.entities.Cliente;

public interface ClienteRepository {

  void adcionar(Cliente cliente);

  void cadastrar();

  void listar();

  void editar();

  void excluir();

  void pesquisar();
}