import java.util.Scanner;
import java.util.Random;
public class Trab3FundProgVM
{
    /**
     * 
     * @param args
     */
    public static void main(String[]args)
    {
        //variaveis gerais
        Scanner teclado = new Scanner(System.in);
        Random gerador = new Random();
        int opcaoM1 = 0;
        int opcaoM2 = 0;
        int opcaoM3 = 0;
        int opcaoMList = 0;
        int opcaoMOrdem = 0;
        int opcaoMPicos = 0;
        int[] vetor;

        //começo (menu 1)
        do{
            opcaoM1 = MenuPrincipal();
                  
    
            if(opcaoM1 == 1)
            {
               vetor = gerarVet();
               
               //menu 2
               do
               {
                       opcaoM2 = MenuPreencherVetor();
                       if(opcaoM2 == 1)
                       {
                            vetor = PreencherVet(vetor);
                            
                            do 
                            {
                                opcaoM3 = MenuVetor();
                                
                                if(opcaoM3 == 1)
                                {
                                    opcaoMList = MenuListagem();
                                    
                                    switch(opcaoMList)
                                    {
                                        case 1:
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                                System.out.println("A posição "+i+" do vetor é: "+vetor[i]);
                                            }
                                            break;
                                        
                                        case 2:
                                            int soma = 0;
                                            int media;
                                            for (int i = 0;i<vetor.length;i++)
                                            {
                                                soma = soma + vetor[i];
                                            }
                                            media = soma/vetor.length;
                                            for(int i =0;i<vetor.length;i++)
                                            {
                                                if(vetor[i] > media)
                                                {
                                                    System.out.println("O valor na posição "+i+" esta acima da média ("+media+") e seu valor é: "+vetor[i]);
                                                }
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Digite o valor que você deseja como referência: ");
                                            int valorRef = teclado.nextInt();
                                            for(int i =0;i<vetor.length;i++)
                                            {
                                                if(vetor[i] > valorRef)
                                                {
                                                    System.out.println("O valor na posição "+i+" esta acima da referência ("+valorRef+") e seu valor é: "+vetor[i]);
                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println("Opção inválida!");
                                            break;
                                    }
                                    
                                    opcaoM3 = 0;
                                }
                                else if(opcaoM3 == 2)
                                {
                                    opcaoMOrdem = MenuOrdem();
                                    
                                    switch(opcaoMOrdem)
                                    {
                                        case 1:
                                            int troca;
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                                for(int c = 0;c<vetor.length;c++)
                                                {
                                                    if(vetor[i] < vetor[c])
                                                    {
                                                        troca = vetor[i];
                                                        vetor[i] = vetor[c];
                                                        vetor[c] = troca;
                                                    }
                                                }
                                            }
                                            
                                            System.out.println("O vetor em ordem crescente é:");
                                            
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                                System.out.println("Posição "+i+" = "+vetor[i]);
                                            }
                                            break;
                                        case 2:
                                             int troca2;
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                                for(int c = 0;c<vetor.length;c++)
                                                {
                                                    if(vetor[i] > vetor[c])
                                                    {
                                                        troca2 = vetor[i];
                                                        vetor[i] = vetor[c];
                                                        vetor[c] = troca2;
                                                    }
                                                }
                                            }
                                            
                                            System.out.println("O vetor em ordem decrescente é:");
                                            
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                                System.out.println("Posição "+i+" = "+vetor[i]);
                                            }
                                            break;
                                        default:
                                            System.out.println("Opção inválida!");
                                            break;
                                    
                                    }
                                    opcaoM3 = 0;
                                }
                                else if(opcaoM3 == 3)
                                {
                                    opcaoMPicos = MenuPicos();
                                    
                                    switch(opcaoMPicos)
                                    {
                                        case 1:
                                            int valorMax = 0;
                                            int pos = 0;
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                               if(vetor[i] > valorMax)
                                               {
                                                   valorMax = vetor[i];
                                                   pos = i;
                                               }
                                            }
                                            
                                            System.out.println("O mmaior valor é: "+valorMax+" e sua posição é: "+pos);
                                            
                                            break;
                                        case 2:
                                            int valorMin = 0;
                                            int pos2 = 0;
                                            for(int i = 0;i<vetor.length;i++)
                                            {
                                                if(vetor[i] < valorMin)
                                               {
                                                   valorMax = vetor[i];
                                                   pos2 = i;
                                               }
                                            }
                                            
                                            System.out.println("O menor valor é: "+valorMin+" e sua posição é: "+pos2);
                                            
                                            break;
                                        default:
                                            System.out.println("Opção Inválida!");
                                            break;
                                    }
                                    
                                    opcaoM3 = 0;
                                }
                                else if(opcaoM3 == 4)
                                {
                                    opcaoM2 = 0;
                                }
                                else
                                {
                                    System.out.println("Opção Inválida!");
                                    opcaoM3 = 0;
                                }
                                                    
                        }while(opcaoM3 == 0);
                   }
                   
                   else if(opcaoM2 == 2){opcaoM1 = 0;}
                   
                   else
                   {
                        System.out.println("Opção Inválida!");
                        opcaoM1 = 0;
                   }
                   
                }while(opcaoM2 == 0);
                
            }
            else if(opcaoM1==9)
            {
                System.exit(0);
            }
    
            else
            {  
                System.out.println("Opção Inválida!");
                opcaoM1 = 0;
            
            }
        }while(opcaoM1 == 0);  
                
    }


    /**Menu1
     * @param
     * @return
     */
    public static int MenuPrincipal()
    {
        //scanner
        Scanner teclado = new Scanner (System.in);
        
        //tela
        System.out.println("====================");
        System.out.println("*  MENU PRINCIPAL  *");
        System.out.println("====================");
        System.out.println("Digite 1 para criar o vetor");
        System.out.println("Digite 9 para sair");
        int usrSlct = teclado.nextInt();

        //retorno
        if(usrSlct == 1)
        {
            return 1;
        }
        else if(usrSlct == 9)
        {
            return 9;
        }
        else   
        {
            return 0;
        }

    }

    /**Menu2
     * @param 
     * @return
     */
    public static int MenuPreencherVetor()
    {
        //scanner e random
        Scanner teclado = new Scanner (System.in);
        Random gerador = new Random();
        
        //tela
        System.out.println("====================");
        System.out.println("* PREENCHER  VETOR *");
        System.out.println("====================");
        System.out.println("Digite 1 para selecionar o valor máximo dos elementos que serão preenchidos: ");
        System.out.println("Digite 2 para voltar");
        int usrSlct = teclado.nextInt();

        //retorno
        if(usrSlct == 1)
        {
            return 1;
        }
        else if(usrSlct == 2)
        { 
            return 2 ;
        }
        else
        {
            System.out.println("opção inválida"); 
            return 3 ;
        }

    }
    
    /**Gerador de Vetor
     * 
     */
    public static int[] gerarVet()
    {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o tamanho do vetor: ");
        
        int tamVet = teclado.nextInt();
        
        int [] vet = new int [tamVet];
        
        return vet; 
    }
    
    /**Preenchedor do Vetor
     * 
     */
    public static int[] PreencherVet(int [] vet)
    {
        Scanner teclado = new Scanner (System.in);
        Random gerador = new Random();
        
        System.out.print("Digite o número máximo do vetor: ");
        int max = teclado.nextInt();
            
            for(int i = 0;i < vet.length;i++)
            {
                vet[i] = gerador.nextInt(max);
            }
        return vet;
    }
    
    /**Menu Do Vetor
     * 
     */
    public static int MenuVetor()
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("============================");
        System.out.println("*  MENU PRINCIPAL DO VETOR *");
        System.out.println("*   Selecione uma opção:   *");
        System.out.println("* 1- Listagem              *");
        System.out.println("* 2- Ordenar Vetor         *");
        System.out.println("* 3- Picos do Vetor        *");
        System.out.println("* 4- Voltar                *");
        System.out.println("============================");
        int usrSlct = teclado.nextInt();
        
        return usrSlct;
    }
    
    /**Menu Listagem
     * 
     */
    public static int MenuListagem()
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("============================");
        System.out.println("*    Menu De Listagem      *");
        System.out.println("* 1- Todos                 *");
        System.out.println("* 2- Acima da Média        *");
        System.out.println("* 3- Acima valor específico*");
        System.out.println("============================");
        System.out.println("Selecione uma opção: ");
        
        int usrSlct = teclado.nextInt();
        
        return usrSlct;    
    }
    
    /**MenuOrdem 
     * 
     */
    public static int MenuOrdem()
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("============================");
        System.out.println("*      Menu De Ordem       *");
        System.out.println("* 1- Ordem crescente       *");
        System.out.println("* 2- Ordem decrescente     *");
        System.out.println("============================");
        System.out.println("Selecione uma opção: ");
        int usrSlct = teclado.nextInt();
        
        return usrSlct;
    }
    
    /**MenuPicos
     * 
     */
    public static int MenuPicos()
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("============================");
        System.out.println("*       Menu Picos         *");
        System.out.println("* 1- Valor máximo          *");
        System.out.println("* 2- Valor mínimo          *");
        System.out.println("============================");
        System.out.println("Selecione uma opção: ");
        int usrSlct = teclado.nextInt();
        
        return usrSlct;
    }
}