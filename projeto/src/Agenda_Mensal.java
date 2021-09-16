import java.util.Scanner;

public class Agenda_Mensal implements Strategy{
    Scanner input = new Scanner(System.in);

    @Override

    public String criar_agenda()
    {
        String nova = "Mensal";

            System.out.printf("\nEm que dia do mês será realizado o pagamento?\n\n");

            int data = input.nextInt();

            while(data < 1 || data > 30)
            {
                System.out.println("\nRespota inválida! Tente novamente!\n");
                System.out.printf("Em que dia do mês será realizado o pagamento?\n\n");
                data = input.nextInt();
            }

            String str = Integer.toString(data);

            nova += " ";
            nova += str;
        
            return nova;
    }
}
