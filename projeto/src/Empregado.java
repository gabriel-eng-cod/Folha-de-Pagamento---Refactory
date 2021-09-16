import java.util.Scanner;
import java.util.LinkedList;

public class Empregado 
{
    Scanner input = new Scanner(System.in);

    public LinkedList<Taxa_de_servico> taxa_list = new LinkedList<>();

    public String name, metodo_pag;
    public String endereco;
    public double taxa_mensal_sindicato, salario;
    public int id, id_sindicato, escolha_pag;
    public boolean is_sindicate;

    void sindicate(int id_sindicato, double taxa_mensal_sindicato)
    {
        this.id_sindicato = id_sindicato;
        this.taxa_mensal_sindicato = taxa_mensal_sindicato;
    }

    public void alterar_nome(Empregado aux)
    {
        System.out.printf("Digite um novo nome: ");

        String new_name = input.nextLine();

        aux.name = new_name;
    }

    public void alterar_endereco(Empregado aux)
    {
        System.out.printf("Digite um novo endereço: ");

        String new_endereco = input.nextLine();

        aux.endereco = new_endereco;
    }

    public void alterar_meth_pag(Empregado aux)
    {
        int meth = input.nextInt();

        aux.escolha_pag = meth;
    }

    public void alterar_is_sind(Empregado aux)
    {
        if(aux.is_sindicate)
        {
            aux.is_sindicate = false;
        }
        else
        {
            aux.is_sindicate = true;

            System.out.printf("Qual a taxa mensal do sindicato?\n\n-> ");
                            
            double taxa_mensal_sindicato = input.nextDouble();

            aux.taxa_mensal_sindicato = taxa_mensal_sindicato;
        }
    }

    public void alterar_id_sind(Empregado aux)
    {
        System.out.printf("Digite o novo ID do funcionário no sindicato:\n\n-> ");
        int id_sind = input.nextInt();

        aux.id_sindicato = id_sind;
    }

    public void alterar_taxa(Empregado aux)
    {
        System.out.printf("Digite a nova taxa sindical: ");
        double new_taxa = input.nextDouble();

        aux.taxa_mensal_sindicato = new_taxa;
    }
}
