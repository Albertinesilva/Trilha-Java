package residenciatic18.pratica8.repositories;

import residenciatic18.pratica8.entities.FalhaDistribuicao;

public interface FalhaRepository {

  void cadastrarFalhaDistribuicao();

  void cadastrarFalhaGeracao();

  void listar();

  void editar();

  FalhaDistribuicao buscarFalhaDistribuicao();
}