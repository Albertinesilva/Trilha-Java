package residenciatic18.pratica8.repositories;

import residenciatic18.pratica8.entities.Imovel;

public interface ImovelRepository {

  public void adcionar(Imovel imovel);

  void cadastrar();

  void listar();

  void editar();

  void excluir();

  void pesquisar();

  Imovel buscaImovel();
}
