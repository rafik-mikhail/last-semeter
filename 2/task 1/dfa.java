// import java.util.ArrayList;
public class dfa {
int [][] states;
boolean[] accept;
int pointer = 0;
public dfa(String s){
 String [] a = s.split("#");
 String [] accepts = a[1].split(",");
 String[]dfas = a[0].split(";");
 
 states = new int[dfas.length][2];
 for(int i =0;i<dfas.length;i++){
  String[]triple = dfas[i].split(",");
  states[Integer.parseInt(triple[0])][0] = Integer.parseInt(triple[1]);
  states[Integer.parseInt(triple[0])][1] = Integer.parseInt(triple[2]);
 }
 
 accept = new boolean[states.length];
 
 for(int i=0; i<accepts.length;i++){
  accept[Integer.parseInt(accepts[i])] = true;
 }
}
public dfa(int[][] states, boolean[]accept){
    this.states = states;
    this.accept = accept;
}
public boolean run(String runable){
 pointer = 0;
 for(int q=0;q<runable.length();q++){
  pointer = states[pointer][Integer.parseInt(""+runable.charAt(q))];
 }
 return accept[pointer];
}
public static void main(String[]args){
dfa my = new dfa("0,0,1;1,2,1;2,0,3;3,3,3#1,3");
System.out.println(my.run("01001101"));
}
}
