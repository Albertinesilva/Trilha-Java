package residenciatic18.mastermind;

import java.util.Arrays;

public class Configuracao {

  private String nome;
  private String alfabeto;
  private int tamanhoSenha;
  private int maxTentativas;

  public Configuracao() {
  }

  public Configuracao(String nome, String alfabeto, int tamanhoSenha, int maxTentativas) {
    this.nome = nome;
    this.alfabeto = alfabeto;
    this.tamanhoSenha = tamanhoSenha;
    this.maxTentativas = maxTentativas;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    if (nome == null || nome.trim().isEmpty()) {
      throw new IllegalArgumentException("Nome da configuração inválido");
    }
    this.nome = nome;
  }

  public String getAlfabeto() {
    return alfabeto;
  }

  public void setAlfabeto(String alfabeto) {
    if (alfabeto == null || alfabeto.trim().isEmpty()) {
      throw new IllegalArgumentException("Alfabeto inválido");
    }

    // Verificar se há caracteres duplicados no alfabeto
    if (temCaracteresDuplicados(alfabeto)) {
      throw new IllegalArgumentException("O alfabeto não pode conter caracteres duplicados");
    }

    this.alfabeto = alfabeto;
  }

  private boolean temCaracteresDuplicados(String str) {

    // Convertendo a string para um array de caracteres
    char[] caracteres = str.toCharArray();

    // Ordenando o array para facilitar a verificação de duplicatas
    Arrays.sort(caracteres);

    // Verificando se há caracteres duplicados no array ordenado
    for (int i = 0; i < caracteres.length - 1; i++) {
      if (caracteres[i] == caracteres[i + 1]) {
        return true; 
      }
    }

    return false; 
  }

  public int getTamanhoSenha() {
    return tamanhoSenha;
  }

  public void setTamanhoSenha(int tamanhoSenha) {

    try {
      if (tamanhoSenha < 1) {
        throw new IllegalArgumentException("Senha deve ter ao menos 1 caracter");
      }

      if (alfabeto != null && tamanhoSenha > alfabeto.length()) {
        throw new IllegalArgumentException("Tamanho da senha não pode ser maior que o comprimento do alfabeto");
      }

      this.tamanhoSenha = tamanhoSenha;
    } catch (IllegalArgumentException e) {

      System.out.println("Exceção capturada: " + e.getMessage());
      throw e; // Re-lança a exceção após o tratamento, se necessário.
    }
  }

  public int getMaxTentativas() {
    return maxTentativas;
  }

  public void setMaxTentativas(int maxTentativas) {
    this.maxTentativas = maxTentativas;
  }

}