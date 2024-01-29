package residenciatic18.pratica8.entities;

import java.time.LocalDate;
import java.util.ArrayList;

import residenciatic18.pratica8.views.Views;

import java.text.DecimalFormat;

public class Fatura {

    private String matriculaImovel;
    private int ultimaLeitura;
    private int penultimaLeitura;
    private double valorTotal;
    private LocalDate dataEmissao;
    private boolean quitado;
    private ArrayList<Pagamento> pagamentos;
    private Reembolso reembolso;

    public Fatura(String matriculaImovel, int ultimaLeitura, int penultimaLeitura) {
        this.matriculaImovel = matriculaImovel;
        this.ultimaLeitura = ultimaLeitura;
        this.penultimaLeitura = penultimaLeitura;
        this.dataEmissao = LocalDate.now();
        this.quitado = false;
        this.pagamentos = new ArrayList<>();
        calcularValorFatura();
    }

    public String getMatriculaImovel() {
        return matriculaImovel;
    }

    public int getUltimaLeitura() {
        return ultimaLeitura;
    }

    public int getPenultimaLeitura() {
        return penultimaLeitura;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public ArrayList<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public Reembolso getReembolso() {
        return reembolso;
    }

    public void novoPagamento() {

        if (quitado) {
            Views.cxMsg("A fatura já está quitada!");
            return;
        }

        float totalPago = 0;
        Pagamento novo = Pagamento.obterDadosPagamento();
        if (novo == null) {
            Views.cxMsg("Pagamento não realizado");
            return;
        }
        this.pagamentos.add(novo);

        for (Pagamento p : pagamentos)
            totalPago += p.valor;

        if (totalPago < this.valorTotal) {
            DecimalFormat df = new DecimalFormat("#.##");
            String msg = String.format("A fatura foi parcialmente paga, restando R$%s a pagar!",
                    df.format(this.valorTotal - totalPago));
            Views.cxMsg(msg);
            return;
        }

        this.quitado = true;
        Views.cxMsg("A fatura foi paga!");
        if (totalPago > this.valorTotal) {
            this.reembolso = new Reembolso(totalPago - this.valorTotal);
            Views.cxMsg(this.reembolso.toString());
        }
    }

    public boolean isQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    public void calcularValorFatura() {
        double custoPorKWh = 10.0;
        this.valorTotal = (ultimaLeitura - penultimaLeitura) * custoPorKWh;
    }

    public void registraLeitura(int novaLeitura) {

        try {

            System.out.println("Entrou no método registraLeitura()");

            if (novaLeitura <= this.ultimaLeitura) {
                System.out.println("Lançando exceção: A nova leitura deve ser maior que a última leitura.");
                throw new IllegalArgumentException("A nova leitura deve ser maior que a última leitura.");
            }
            
            // Atualiza a penúltima leitura com o valor atual da última leitura
            this.penultimaLeitura = this.ultimaLeitura;

            // Atualiza a última leitura com a nova leitura
            this.ultimaLeitura = novaLeitura;

            // Recalcula o valor total da fatura
            calcularValorFatura();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao registrar leitura: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("Nº de Matrícula: %s, Consumo: %s, Valor Total: R$%s, Data de Emissão: %s, Quitado: %s]",
                matriculaImovel, ultimaLeitura - penultimaLeitura, df.format(valorTotal), dataEmissao, quitado);
    }
}
