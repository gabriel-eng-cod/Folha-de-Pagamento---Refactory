import java.util.LinkedList;
import java.util.Scanner;

public class Horista extends Empregado{
    
    Scanner input = new Scanner(System.in);

    public LinkedList<Cartao_de_ponto> cartao_list = new LinkedList<>();

    public Horista(String name, String endereco, double salario_hora, int id, boolean is_sindicate, int escolha_pag, String metodo_pag)
    {
        this.name = name;
        this.endereco = endereco;
        this.salario = salario_hora;
        this.id = id;
        this.is_sindicate = is_sindicate;
        this.escolha_pag = escolha_pag;
        this.metodo_pag = metodo_pag;
    }
}
