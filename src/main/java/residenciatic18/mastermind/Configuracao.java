package residenciatic18.mastermind;

public class Configuracao {

  private String nome;
  private String alfabeto;
  private int tamanhoSenha;
  private int MaxTentativas;

  public Configuracao() {
  }

  public Configuracao(String nome, String alfabeto, int tamanhoSenha) {
    super();
    this.nome = nome;
    this.alfabeto = alfabeto;
    this.tamanhoSenha = tamanhoSenha;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {

    if (nome == null || nome.trim().length() == 0) {
      throw new IllegalArgumentException("Nome da configuração inválido");
    }
    this.nome = nome;
  }

  public String definirAlfabeto(String alfabeto) {

    if (alfabeto == null || alfabeto.trim().length() == 0) {
      throw new IllegalArgumentException("Alfabeto inválido");
    }
    return alfabeto;
  }

  public String getAlfabeto() {
    return alfabeto;
  }

  public void setAlfabeto(String alfabeto) {
    this.alfabeto = alfabeto;
  }

  public int getTamanhoSenha() {
    return tamanhoSenha;
  }

  public void setTamanhoSenha(int tamanhoSenha) {
    if (tamanhoSenha < 1) {
      throw new IllegalArgumentException("O tamanho da senha deve ser pelo menos 1 caracter");
    }
    this.tamanhoSenha = tamanhoSenha;
  }

  public int getMaxTentativas() {
    return MaxTentativas;
  }

  public void setMaxTentativas(int maxTentativas) {
    MaxTentativas = maxTentativas;
  }
}
