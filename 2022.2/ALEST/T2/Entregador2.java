public class Entregador2 {
    
    String nome;
    Pedido entregando;
    filaPedidos filaEntrega;
    private int pedidosEntregues = 0;

    public Entregador2(String nome,filaPedidos Entrega){
        this.filaEntrega=Entrega;
        this.nome = nome;
    }

    public void comecarEntrega(){
        if(entregando == null)
            this.entregando = filaEntrega.dequeue();
        else System.out.printf("%s está em entrega no momento\n",this.nome);
    }

    public void descCaminho()
    {
        if (entregando != null)
        {
            if(entregando.getDist()>0){
                entregando.descDis(2);
                
            }
            
            else{
            System.out.printf("Pedido %d entregue por %s!\n",this.entregando.getNpedido(),this.nome);
            this.entregando = null;
            pedidosEntregues++;
            }
        }
        else
        {
            System.out.printf("%s não está em entrega no momento\n",this.nome);
        }
    }

    public boolean Disponivel(){
        return entregando==null;
    }

    public Pedido getPedido()
    {
        return this.entregando;
    }

    public int pedidosEntregues(){
        return pedidosEntregues;
    }

    public String getNome()
    {
        return this.nome;
    }
}
