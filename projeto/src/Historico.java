public class Historico  //CLASSE EXCLUSIVA PARA UNDO E REDO
{
    Horista horis;
    Assalariado assal;
    int adicionado;
    int removido;
    Cartao_de_ponto card;
    int id_card;
    Resultado_venda sale;
    int id_venda;
    Taxa_de_servico taxa_horista;
    Taxa_de_servico taxa_assalariado;
    int id_taxa;
    int id_alteracao;
    int horis_ou_assal;
    int escolha_alteracao;

    String nome, nome_alterado, endereco, endereco_alterado;
    int escolha_pag, escolha_pag_alterada, id_sindicato, id_sindicato_alterado;
    boolean is_sindicate, is_sindicate_alterado;
    double taxa_sindical, taxa_sindical_alterada;
}
