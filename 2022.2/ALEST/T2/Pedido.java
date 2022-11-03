public class Pedido
{   
    private int produtos;
    private int dist;
    private int Npedido;

    public Pedido(int id,int qtd, int distancia)
    {
        this.Npedido=id;
        produtos = qtd;
        dist = distancia;

    }


    public int getProdutos()
    {
        return produtos;
    }
    
    public double getDist()
    {
        return dist;
    }

    public void descProdutos(int valor){
        this.produtos -= valor;
    }
    public void descDis(double valor){
        this.dist-=valor;
    }
    public int getNpedido(){
        return Npedido;
    }
}