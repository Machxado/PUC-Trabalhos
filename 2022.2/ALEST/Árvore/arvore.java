public class arvore{

    private class TreeNode{
        public TreeNode father;
        public int value;
        private TreeNode[] children;
        private int nChild;

        public TreeNode (Integer element){
            this.value = element;
            father =null;
            children=new TreeNode[10];
            nChild=0;
        }
        public void addSubtree(TreeNode n){
            if(nChild==children.length)
                grow();
            children[nChild]=n;
            nChild++;
            n.father=this;
        }
        private void grow(){
            TreeNode [] aux = new TreeNode [children.length*2];
            for(int i =0; i<children.length;i++)
                aux[i]=children[i];
            children=aux;
        }
        public boolean removeSubtree(TreeNode n){
            return false;
        }
        public TreeNode getSubtree(int idx){
            if(idx>=0 && idx<nChild)
                return children[idx];
            return null;
        }
        public int getSubtreesSize(){
            return nChild;
        }

    }

    private TreeNode root;
    private int nElements;

    private TreeNode searchNode(Integer e,TreeNode ref){
        if(ref!=null){
            if(ref.value == e){
                return ref;
            }
            else{
                TreeNode aux=null;
                for(int i=0;i<ref.getSubtreesSize();i++){
                    aux = searchNode(e,ref.getSubtree(i));
                    if(aux != null){
                        return aux;
                    }
                }

                return null;    
            }
        }
        else return null;
    }
    public boolean add(Integer e, Integer father){
        if(nElements==0)    {//raíz
            root = new TreeNode(e);
            nElements++;
            return true;
        }
        else {      //não raíz
            TreeNode aux = searchNode(father,root);
            if(aux!=null){
                aux.addSubtree(new TreeNode(e));
                nElements++;
                return true;
            }
            return false;
        }
        
    }
    public Integer getRoot(){
        if(root  != null){
            return root.value;
        }
            return null;
        
    }
    public void setRoot(Integer e){
        if(e!=null){
            if(root==null){
                add(e,null);
            }
            else root.value=e;
        }
    }
    public Integer getParent(Integer e){
        TreeNode aux = searchNode(e, root);
        if(aux==null || aux == root){
            return null;
        }
        else return aux.father.value;
        
    }
    public boolean removeBranch(Integer e){
        return false;
    }
    public boolean contains(Integer e){return true;}
    public boolean isInternal(Integer e){return true;}
    public boolean isExternal(Integer e){return true;}
    public boolean isRoot(Integer e){
        if((root!=null) && (e!=null) && (root.value==e)){
            return true;
        }
        return false;
    }
    public boolean isEmpty(){
        return(nElements==0);
    }
    public int size(){
        return nElements;
    }
    public void clear(){        //duvida garbage colector
        nElements=0;
        root=null;
    }
    // public int [] positionsPre(){return }
    // public int [] positionsPos(){return }
    // public int [] positionsWidth(){return}

    private void printTree(TreeNode ref){
        if (ref != null){
            System.out.println(ref.value+"; ");
            for(int i=0;i<ref.getSubtreesSize();i++){
                printTree(ref.getSubtree(i));
            }
        }
    }

    public void printTree(){
        if(root==null){
            printTree(root);
        }
        else printTree(root);
    }
}