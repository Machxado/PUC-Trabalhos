
import java.util.Random;
import java.util.Scanner;
public class Mercado {
    public static void main(String []args){
            /*declarando as filas */
        filaPedidos pedidosSeparar = new filaPedidos();
        filaPedidos pedidosEntrega = new filaPedidos();
    
            /*declarando os separadores */
        Separador2 s1 = new Separador2("Sérgio",pedidosSeparar,pedidosEntrega);
        Separador2 s2 = new Separador2("Jão",pedidosSeparar,pedidosEntrega);
        Separador2 s3  = new Separador2("EdsonBronzeado",pedidosSeparar,pedidosEntrega);
        Separador2 [] separadores = {s1,s2,s3}; 
        /*declarando os entregadores */
        Entregador2 e1 = new Entregador2("Jean", pedidosEntrega);
        Entregador2 e2 = new Entregador2("Pedro", pedidosEntrega);
        Entregador2 e3 = new Entregador2("Paulo", pedidosEntrega);
        Entregador2 [] entregadores = {e1,e2,e3}; 
        /*declarando o restante */
        Scanner usr = new Scanner(System.in);
            int nRodadas;
            Random aleatorio = new Random();
            int randvar1;
            int randvar2;
            int randvar3;
            int pedidosTotais=0;
            int pedidosCancelado = 0;
            int TentativapedidosCancelado = 0;
            int pedidosEntreguesTotal = 0;
            int rodadaSemPedido = 0;
            int rodadaComPedido =0;

            System.out.println("Digite o numero de rodadas: ");
            nRodadas =usr.nextInt();

            for(int rodada=0;rodada<nRodadas*3;rodada+=3){
                System.out.println("=======INICIO DA RODADA=======");
                randvar1=aleatorio.nextInt(nRodadas)+1;
                randvar2=aleatorio.nextInt(8)+1;
                randvar3=aleatorio.nextInt(9)+4;

                if(randvar1>(nRodadas*0.3)){
                    rodadaComPedido++;
                    Pedido pedido = new Pedido(rodada,randvar2,randvar3+2);
                    System.out.printf("Pedido %d adicionado à fila\n",pedido.getNpedido());
                    Pedido pedido2 = new Pedido(rodada+1,randvar2+3,randvar3+1);
                    System.out.printf("Pedido %d adicionado à fila\n",pedido2.getNpedido());
                    Pedido pedido3 = new Pedido(rodada+2,randvar2+5,randvar3-1);
                    System.out.printf("Pedido %d adicionado à fila\n",pedido3.getNpedido());

                    pedidosSeparar.enqueue(pedido);
                    pedidosSeparar.enqueue(pedido2);
                    pedidosSeparar.enqueue(pedido3);
                    pedidosTotais+=3;}
                else
                rodadaSemPedido++;


                
                for(int i=0;i<separadores.length;i++){
                    if(pedidosSeparar.isEmpty()){}
                    else{
                        if(separadores[i].Disponível()){
                            separadores[i].separarPedido();
                            System.out.printf("Pedido %d está sendo separado por %s\n",separadores[i].separando.getNpedido(),separadores[i].getNome());
                        }
                    }
                }
                

                for(int i=0;i<separadores.length;i++){
                    if(separadores[i].Disponível()){}
                    else {
                        separadores[i].itemSeparado();
                        
                    }
                }


                for(int i=0;i<entregadores.length;i++){
                    if(pedidosEntrega.isEmpty()){}
                    else{
                        if(entregadores[i].Disponivel()){
                            entregadores[i].comecarEntrega();
                            System.out.printf("Pedido %d saiu para entrega!\n",entregadores[i].entregando.getNpedido());

                        }
                    }
                }
                

                for(int i=0;i<entregadores.length;i++){
                    if(entregadores[i].Disponivel()){}
                    else entregadores[i].descCaminho();
                }

                

                if(randvar1<(nRodadas*0.2)){
                int rodadaAle = aleatorio.nextInt(nRodadas);
                for(int i=0;i<separadores.length;i++){
                    if(separadores[i].Disponível()==false)
                        if(separadores[i].separando.getNpedido()==rodadaAle)
                        {
                            separadores[i].remove();
                            System.out.printf("Pedido do %s foi cancelado\n",separadores[i].getNome());
                            pedidosCancelado++;
                        }
                        else if(pedidosSeparar.contains(rodadaAle)==true)
                        {
                            pedidosSeparar.remove(rodadaAle);
                            System.out.printf("Pedido %d removido da fila de pedidos\n",rodadaAle);
                            pedidosCancelado++;
                        }
                        else if(pedidosEntrega.contains(rodadaAle)==true)
                        {
                            pedidosEntrega.remove(rodadaAle);
                            System.out.printf("Pedido %d removido da fila de entrega\n",rodadaAle);
                            pedidosCancelado++;
                        }

                        else
                            TentativapedidosCancelado++;
                    
            }
   }
            System.out.println("=======FIM DA RODADA=======");
   }
   for(int i=0; i<separadores.length;i++){
    for(int j=i+1;j<separadores.length;j++)
    {
        if (separadores[i].getSeparados()>separadores[j].getSeparados())
        {
            Separador2 aux = separadores[j];
            separadores[j] = separadores[i];
            separadores[i] = aux;
        }
    }
   }
   for(int i=0; i<entregadores.length;i++){
    for(int j=i+1;j<entregadores.length;j++)
    {
        if (entregadores[i].pedidosEntregues()>entregadores[j].pedidosEntregues())
        {
            Entregador2 aux = entregadores[j];
            entregadores[j] = entregadores[i];
            entregadores[i] = aux;
        }
    }
   }

   pedidosEntreguesTotal = e1.pedidosEntregues() + e2.pedidosEntregues() + e3.pedidosEntregues();
   System.out.println("*******RELATORIO FINAL*******");
   System.out.println("Rodadas COM Pedidos: "+rodadaComPedido);
   System.out.println("Rodadas SEM Pedidos:  "+rodadaSemPedido);
   System.out.println("Pedidos Totais: "+pedidosTotais);
   System.out.println("Pedidos Entregues: "+pedidosEntreguesTotal);
   System.out.println("Tentativas de Cancelamento: "+TentativapedidosCancelado);
   System.out.println("Pedidos Cancelados: "+pedidosCancelado);
   System.out.println("Melhor Separador: "+separadores[2].getNome());
   System.out.println("Melhor Entregador: "+entregadores[2].getNome());   
    }
}



