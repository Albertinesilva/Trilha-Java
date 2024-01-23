package residenciatic18.mastermind;

import java.io.File;
import java.util.ArrayList;

public class ListaConfiguracoes {

  public static final String CAMINHO = "." + File.separator + "arquivos" + File.separator;

  private String nomeArquivo;
  private ArrayList<Configuracao> lista;

  public ListaConfiguracoes() {
    super();
    nomeArquivo = CAMINHO + "configs.json";
    lista = new ArrayList<Configuracao>();
  }

  public String getNomeArquivo() {
    return nomeArquivo;
  }

  public void setNomeArquivo(String nomeArquivo) {
    this.nomeArquivo = nomeArquivo;
  }

  public ArrayList<Configuracao> getLista() {
    return lista;
  }

  public void novaConfiguracao(Configuracao configuracao) throws Exception {

    if (configuracao == null) {
      throw new Exception("Configuração inválida");
    }
    if (configuracao.getNome() == null || configuracao.getNome().trim().length() == 0) {
      throw new Exception("Nome da configuração inválido");
    }
    if (configuracao.getAlfabeto() == null || configuracao.getAlfabeto().trim().length() == 0) {
      throw new Exception("Alfabeto inválido");
    }
    if (configuracao.getTamanhoSenha() < 1) {
      throw new Exception("Tamanho da senha inválido");
    }
    for (Configuracao c : lista) {
      if (c.getNome().equals(configuracao.getNome())) {
        throw new Exception("Já existe uma configuração com este nome");
      }
    }
    lista.add(configuracao);
  }
}
