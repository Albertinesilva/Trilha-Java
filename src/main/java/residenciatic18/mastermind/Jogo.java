package residenciatic18.mastermind;

public class Jogo {

    private Configuracao configuracao;
    private String senha;
    private int tentativas;
    private int tentativasRestantes;
    private boolean venceu;
    private boolean perdeu;

    public Jogo(Configuracao configuracao) {
        this.configuracao = configuracao;
        this.senha = configuracao.gerarSenha();
        this.tentativas = 0;
        this.tentativasRestantes = configuracao.getTentativas();
        this.venceu = false;
        this.perdeu = false;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public String getSenha() {
        return senha;
    }

    public int getTentativas() {
        return tentativas;
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public boolean isVenceu() {
        return venceu;
    }

    public boolean isPerdeu() {
        return perdeu;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public void setTentativasRestantes(int tentativasRestantes) {
        this.tentativasRestantes = tentativasRestantes;
    }

    public void setVenceu(boolean venceu) {
        this.venceu = venceu;
    }

    public void setPerdeu(boolean perdeu) {
        this.perdeu = perdeu;
    }

    public void decrementarTentativasRestantes() {
        this.tentativasRestantes--;
    }

    // Método que verifica se o jogador venceu ou perdeu o jogo e retorna um inteiro que representa o resultado
    // 0 - Jogo não acabou ainda (não venceu e não perdeu) 
    public int fimDeJogo() {

        if (this.venceu) {
            return 1;
        } else if (this.perdeu) {
            return 2;
        } else {
            return 0;
        }
    }

}