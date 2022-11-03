public class arvorebinaria{
    private class BinaryTreeNode{
        public int value;
        public BinaryTreeNode father;
        public BinaryTreeNode left, right;

        public BinaryTreeNode(int e){
            this.value=e;
            this.father=this.left=this.rigth=null;
        }
    }

    
    //raiz
    BinaryTreeNode root;
    //nElementos
    int nElementos;

    // -- MÉTODOS --
    public boolean isEmpty(){
        return nElementos ==0;
    }
    public int size(){
        return nElementos;
    }
    public void clear(){
        nElements=0;
        root=null;
    } 
    public int getRoot(){
        if(nElementos>0)
            return root.value;
        throw new Exeption(); 
    } 
    public void add(Integer e){
        if(nElementos){
            root=new BinaryTreeNode(e);
        }
        else{
            System.out.println("Implementar navegação")
        }
        nElementos++;

    } 
    public boolean remove(Integer e){

    } 
    private BinaryTreeNode searchNode(int e,BinaryTreeNode ref){
        if (ref == null){
            return null;
        }
        if(ref.value==e){
            return ref;
        }
        if(e<ref.value){
            return searchNode(e,ref.left);
        }
        else
            return searchNode(e,ref.right);
    }
    public int getLeft(Integer e){
        BinaryTreeNode aux = searchNode(e,this.root)
        if(aux!=null){
            return aux.left;
        }
    } 
    public int getRight(Integer e){
        BinaryTreeNode aux = searchNode(e,this.root)
        if(aux!=null){
            return aux.right;
    }
    public int getParent(Integer e){}
    public int[] positionsPre(){}
    public int[] positionsCentral(){}
    public int[] positionsPos(){}
    public int[] positionsWidth(){}
    public int level(Integer e){}
    public boolean contains(Integer e){}
    public int height(){}
    public boolean isInternal(Integer e){}
    public boolean isExternal(Integer e){}

}