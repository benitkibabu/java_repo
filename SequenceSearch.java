import java.util.*;

public class MyArrayList <ElemType> extends ArrayList<ElemType>{

  public int unorderSearc(ElemType key){
    int k=0;
    for(k =1; k<size(); k++){
      if(((Comparable)get(k)).compareTo((Comparable)key) ==0){
        return k;
      }
    }
    return -1;
  }
  
  public int orderSearch(ElemType key){
    int k=0;
    while(((Comparable)get(key)).compareTo((Comparable)key) <0 && k < size()){
      k= k+ 1;
    }
    if(((Comparable)get(k)).compareTo((Comparable)key) ==0){
      return k;
    }
    else
      return -1;
  }
}
