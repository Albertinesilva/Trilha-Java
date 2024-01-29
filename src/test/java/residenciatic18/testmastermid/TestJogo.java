package residenciatic18.testmastermid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import residenciatic18.mastermind.Configuracao;
import residenciatic18.mastermind.Jogo;

public class TestJogo {

    @Test
    public void testarSenhaTamanhoDiferenteDoConfigurado() {
        Configuracao configuracao = new Configuracao();
        configuracao.setTamanhoSenha(4);
        configuracao.setAlfabeto("ABCDEF");

        Jogo jogo = new Jogo(configuracao);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jogo.setSenha("ABC");
        }, "Deveria lançar uma exceção para senha com tamanho diferente do tamanho configurado");
    }

    @Test
    public void testarSenhaCaracteresForaDoAlfabetoConfigurado() {
        Configuracao configuracao = new Configuracao();
        configuracao.setTamanhoSenha(4);
        configuracao.setAlfabeto("ABCDEF");

        Jogo jogo = new Jogo(configuracao);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jogo.setSenha("ABGK");
        }, "Deveria lançar uma exceção para senha com caracteres fora do alfabeto configurado");
    }

    @Test
    public void testarSenhaComCaracteresDuplicados() {
        Configuracao configuracao = new Configuracao();
        configuracao.setTamanhoSenha(4);
        configuracao.setAlfabeto("ABCDEF");

        Jogo jogo = new Jogo(configuracao);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jogo.setSenha("ABCC");
        }, "Deveria lançar uma exceção para senha com caracteres duplicados");
    }

    @Test
    public void testarSenhaCaracteresForaDoAlfabetoConfiguradoEDuplicados() {
        Configuracao configuracao = new Configuracao();
        configuracao.setTamanhoSenha(4);
        configuracao.setAlfabeto("ABCDEF");

        Jogo jogo = new Jogo(configuracao);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jogo.setSenha("ABGG");
        }, "Deveria lançar uma exceção para senha com caracteres fora do alfabeto configurado e duplicados");
    }

    @Test
    public void testarSenhaCaracteresForaDoAlfabetoETamanhoDiferenteDoConfigurado() {
        Configuracao configuracao = new Configuracao();
        configuracao.setTamanhoSenha(4);
        configuracao.setAlfabeto("ABCDEF");

        Jogo jogo = new Jogo(configuracao);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jogo.setSenha("ABCGH");
        }, "Deveria lançar uma exceção para senha com caracteres fora do alfabeto configurado e tamanho diferente do tamanho configurado");
    }

}