public class Separador2 {

    filaPedidos filaSeparar;
    Pedido separando;
    filaPedidos filaEntrega;
    Pedido pronto;
    private String nome;
    private int pedidosSeparados=0;
    int nElementos=0;

    public Separador2(String nome,filaPedidos filaSepara, filaPedidos filaEntrega){
        this.filaSeparar = filaSepara;
        this.filaEntrega = filaEntrega;
        this.nome=nome;
    }

    public void separarPedido(){
        if(nElementos==0){
            this.separando=filaSeparar.dequeue();
            nElementos++;
        }
        else{System.out.printf("%s está indisponível no momento\n",this.nome);}
    }

    public void itemSeparado(){
        if(this.separando.getProdutos()>0)
            separando.descProdutos(2);

        else if(this.separando.getProdutos()<=0){
        this.pronto=this.separando;
        this.separando=null;
        this.pedidoPronto();
        }
        
        else System.out.printf("%s não está separando no momento\n",this.nome);

        
    }

    public void pedidoPronto(){
        if(this.pronto !=null){
            filaEntrega.enqueue(this.pronto);
            System.out.printf("Pedido [%d] feito por %s está pronto para entrega!\n",this.pronto.getNpedido(),this.nome);
            this.pronto=null;
            nElementos--;
            pedidosSeparados++;
        }
        else
            System.out.printf("%s ainda está Separando\n",this.nome);
    }

    public boolean Disponível(){
       return (nElementos==0); 
    }

    public String getNome(){
        return this.nome;
    }
    public Pedido getPedido()
    {
        return this.separando;
    }

    public void remove()
    {
        nElementos--;
    }
    
    public int getSeparados()
    {
        return pedidosSeparados;
    }
}
