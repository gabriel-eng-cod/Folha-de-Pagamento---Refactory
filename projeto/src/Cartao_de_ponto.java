public class Cartao_de_ponto 
{
    int in;
    int out;
    int horas_trabalhadas;
    String data;
    
    Cartao_de_ponto(int in, int out, String date)
    {
        this.in = in;
        this.out = out;
        this.horas_trabalhadas = out - in;
        this.data = date;
    }
    
}
