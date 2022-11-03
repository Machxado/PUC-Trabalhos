import java.util.Scanner;
public class Swap {
    public static void main(String[] args) {

        ListaContinua();

    }

    public static void ListaContinua(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o tamanho do vetor");
        int tam = teclado.nextInt();

        int vetor[] = new int [tam];

        for(int i=0;i<vetor.length;i++)
        {
            vetor[i] = i;
        }

        System.out.println("Digite as posições que você deseja trocar: ");
        int pos1=teclado.nextInt();
        int pos2=teclado.nextInt();

        if((pos1 > 0 && pos1<vetor.length) && (pos2 > 0 && pos2 < vetor.length))
        {
            int aux = vetor[pos1];
            vetor[pos1] = vetor[pos2];
            vetor[pos2] = aux;
            System.out.println("Troca Realizada");
        }
        else{System.out.print("Valor(es) inválido(s)!");}

        for(int i =0; i<vetor.length;i++)
        {
            System.out.println(vetor[i]);
        }
    }
}


