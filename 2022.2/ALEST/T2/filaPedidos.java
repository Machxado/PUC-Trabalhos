public class filaPedidos 
{
    private class Nodo{
        private Pedido pedido;
        private Nodo prox;
        
        public Nodo(Pedido pedido){
            this.pedido=pedido;
            this.prox=null;
        }
        public Pedido getPedido(){
            return this.pedido;
        }
        public Nodo getProx(){
            return this.prox;
        }
        public void setProx(Nodo ref){
            this.prox=ref;
        }
    }

    Nodo inicio, fim;
    int nElementos;

    public filaPedidos(){
        nElementos=0;
        inicio=null;
        fim=null;        
    }

    public void enqueue(Pedido pedidoFila){
        Pedido pedido = pedidoFila;
        Nodo novoObjeto = new Nodo(pedido);

        if(nElementos==0)
            inicio=novoObjeto;
            
        else{fim.setProx(novoObjeto);}
        
        fim=novoObjeto;
        nElementos++;  
                     
    }
    public Pedido dequeue(){
        if(nElementos>0){
            Pedido atendido = inicio.getPedido();
            Nodo objetoADeletar=inicio;
            inicio=inicio.getProx();
            nElementos--;
            objetoADeletar.setProx(null);
            return atendido;
        } else
            return null;
        
    }
    public Pedido head() throws Exception{
        if(nElementos>0)
            return inicio.getPedido();
        else
            throw new Exception("A fila está vazia");        
    }

    public boolean isEmpty(){
        return (nElementos==0);

    }
    public int size(){
        return nElementos;        
    }
    public void clear(){
        nElementos=0;
        fim=inicio=null;        
    }
   
    public boolean remove(int valor){
        if(contains(valor)){
            Nodo amyrKlink=inicio;
            if(inicio.getPedido().getNpedido()==valor){
                inicio=amyrKlink.getProx();
                amyrKlink.setProx(null);
            }
            else{
                for(int i =0;i<nElementos;i++){
                    if(amyrKlink.getProx().getPedido().getNpedido()==valor){
                Nodo nodoAEliminar = amyrKlink.getProx();
                amyrKlink.setProx(nodoAEliminar.getProx());
                nodoAEliminar.setProx(null);}
                    }
            }

            nElementos--;
            // posso remover
            return true;
        }
        else
            return false;
        }

        public boolean contains (int valor)
        {
             Nodo amyrKlink = inicio;
             for(int i=0; i<nElementos; i++)
             {
               if(amyrKlink.getPedido().getNpedido()==valor)
                      return true;
               else 
                  amyrKlink=amyrKlink.getProx();
                 }
             return false;
        }
        
}

