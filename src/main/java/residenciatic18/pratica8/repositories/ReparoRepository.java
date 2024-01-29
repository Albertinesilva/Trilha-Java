package residenciatic18.pratica8.repositories;

import residenciatic18.pratica8.entities.Falha;

public interface ReparoRepository {

    void listarReparos();

    void listarRaparosAbertos();

    void encerraReparo();

    void cadastrarReparo(Falha falha);
}
