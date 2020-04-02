// T08_37_7419_Rafik_Sherif
import java.util.ArrayList;

public class T08 {
    int [][] states;
    String [] outputs;
    boolean[] accept;

    public T08(String s){
        String [] a = s.split("#");
        String [] accepts = a[1].split(",");
        String[]dfas = a[0].split(";");
        
        states = new int[dfas.length][2];
        outputs = new String[dfas.length];
        for(int i =0;i<dfas.length;i++){
            String[]triple = dfas[i].split(",");
            states[Integer.parseInt(triple[0])][0] = Integer.parseInt(triple[1]);
            states[Integer.parseInt(triple[0])][1] = Integer.parseInt(triple[2]);
            outputs[Integer.parseInt(triple[0])] = triple[3];
        }
        
        accept = new boolean[states.length];
        
        for(int i=0; i<accepts.length;i++){
            accept[Integer.parseInt(accepts[i])] = true;
        }
    }
    public T08(int[][] states, boolean[]accept, String[] outputs){
        this.states = states;
        this.accept = accept;
        this.outputs = outputs;
    }
    public String run(String runable){
        int right = 0;
        int left = 0;
        int pointer = 0;
        String ret = "";
        int temp = 0;
        boolean flag;
        int last;
        ArrayList<Integer> stack = new ArrayList<Integer>();
        while(right < runable.length()){
            flag = true;
            left = right;
            stack.add(0);
            pointer = 0;
            while(left < runable.length()){
                pointer = states[pointer][Integer.parseInt(""+runable.charAt(left))];
                stack.add(pointer);
                left++;
            }

            last = stack.get(stack.size()-1);
            while(!stack.isEmpty()){
                left--;
                temp = stack.remove(stack.size()-1);
                if(accept[temp]){
                    ret += " " + outputs[temp];
                    right = ++left;
                    flag = false;
                    stack = new ArrayList<Integer>();
                    break;
                }
            }
            if(flag){
                ret += " " + outputs[last];
                return ret;
            }
        }
        return ret;
    }
    public static void main(String[]args){
        T08 my = new T08("0,0,1,00;1,2,1,01;2,0,3,10;3,3,3,11#0,1,2");
        System.out.println(my.run("1011100"));
        T08 myfbda = new T08("0,0,0,00;1,1,1,11#1");
        System.out.println(myfbda.run("01111"));
        
    }
}
    
