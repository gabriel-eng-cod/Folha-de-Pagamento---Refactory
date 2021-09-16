import java.util.Scanner;

public class Agenda_Semanal implements Strategy{
    
    Scanner input = new Scanner(System.in);

    @Override

    public String criar_agenda()
    {
        String nova = "Semanal";

            System.out.printf("\nA cada quantas semanas será realizado o pagamento?\n\n");

            int semanas = input.nextInt();

            while(semanas < 1 || semanas > 5)
            {
                System.out.println("Respota inválida! Tente novamente!\n");
                System.out.printf("A cada quantas semanas será realizado o pagamento?\n\n");
                semanas = input.nextInt();
            }

            String str1 = Integer.toString(semanas);

            nova += " ";
            nova += str1;

            System.out.printf("\nEm qual dia da semana será realizado o pagamento?\n\n");
            System.out.println("1) Segunda-feira");
            System.out.println("2) Terça-feira");
            System.out.println("3) Quarta-feira");
            System.out.println("4) Quinta-feira");
            System.out.println("5) Sexta-feira\n");

            int dia = input.nextInt();

            while(dia < 1 || dia > 5)
            {
                System.out.println("\nRespota inválida! Tente novamente!\n");
                System.out.printf("Em qual dia da semana será realizado o pagamento?\n\n");
                System.out.println("1) Segunda-feira");
                System.out.println("2) Terça-feira");
                System.out.println("3) Quarta-feira");
                System.out.println("4) Quinta-feira");
                System.out.println("5) Sexta-feira\n");
                dia = input.nextInt();
            }

            if(dia == 1)
            {
                nova += " Segunda";
            }
            else if(dia == 2)
            {
                nova += " Terça";
            }
            else if(dia == 3)
            {
                nova += " Quarta";
            }
            else if(dia == 4)
            {
                nova += " Quinta";
            }
            else if(dia == 5)
            {
                nova += " Sexta";
            }

        return nova; 
    }
}
