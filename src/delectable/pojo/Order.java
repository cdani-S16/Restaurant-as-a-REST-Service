package delectable.pojo;

public class Order {
	//id
	//cid
	//menu aray of ids
	int ID;
	
    public boolean matchesId(int lid) {
        return(lid == this.ID);
    }
	

}
