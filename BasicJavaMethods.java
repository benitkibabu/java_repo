//A custom arraylist class which include some usefull method names and so fort.

import java.util.*;

public class MyArrayList <ElemType> extends ArrayList<ElemType>{

  //Un Ordered Sequencial Search
  public int unorderSearch(ElemType key){
    int k=0;
    for(k =1; k<size(); k++){
      if(((Comparable)get(k)).compareTo((Comparable)key) ==0){
        return k;
      }
    }
    return -1;
  }
  
  //Ordered Sequencial Search
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
  
  //fibonacci
  public int fibonacci(int n){
    int fib = 0;
    if((n==1) || (n==2)){
      fib = 1;
    }
    else{
      fib = fibonacci(n-1) + fibonacci(n-2);
    }
    return fib;
  }
}
