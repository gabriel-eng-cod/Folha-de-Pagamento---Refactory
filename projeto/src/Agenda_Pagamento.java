import java.util.LinkedList;
import java.util.Scanner;

public class Agenda_Pagamento 
{
    Scanner input = new Scanner(System.in);
    LinkedList<String> formas_pagamento = new LinkedList<>();
    Strategy new_agenda;
    
    Agenda_Pagamento()
    {
        this.formas_pagamento.add("Semanal 1 Sexta"); //1
        this.formas_pagamento.add("Mensal $"); //2
        this.formas_pagamento.add("Semanal 2 Sexta"); //3
    }

    void listar()
    {
        int i = 1;
        for (String string : formas_pagamento) 
        {
            System.out.printf("%d) %s\n", i, string);
            i += 1;
        }
    }

    void criar_nova_agenda()
    {
        String nova;

        System.out.printf("O pagamento na nova agenda será realizado semanal ou mensalmente?\n\n1) Semanalmente\n2) Mensalmente\n\n");

        int escolha = input.nextInt();

        if(escolha == 1)
        {
            new_agenda = new Agenda_Semanal();
        }
        else
        {
            new_agenda = new Agenda_Mensal();
        }

        nova = new_agenda.criar_agenda();

        System.out.printf("\nA agenda '%s' foi criada\n", nova);

        formas_pagamento.add(nova);

    }
}
