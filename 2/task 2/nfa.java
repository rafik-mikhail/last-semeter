import java.util.ArrayList;

public class nfa{
    dfa myDfa;
    ArrayList<String []> theStates = new ArrayList<String[]>();
    String [][] theNfaRepresentedAsA2dArray;
    
    public static boolean contained(String big, String sub){
        boolean flag = true;
        boolean helperFlag = false;
        String[] bigA = big.split(",");
        String[] subA = sub.split(",");
        for(int i=0;i<subA.length;i++){
            helperFlag = false;
            for(int j=0;j<bigA.length;j++){
                if(subA[i].equals(bigA[j])){
                    helperFlag = true;
                    break;
                }
            }
            if(!helperFlag){
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    public static String sAdd(String s1, String s2){
        if(s1 == null || s1.equals("")){
            return s2;
        }
        else{
            if(!(s2 == null || s2.equals("")))
                return s1+","+s2;
            else
                return s1;
        }
    }
    
    public String getNfaStates(String [] zeros, String [] ones, String [] es){
        String states = "";
        for(int i=0;i<zeros.length;i++){
            String [] temp = zeros[i].split(",");
            if(temp.length != 1){
                if(!contained(states, temp[0])){
                    states = sAdd(states, temp[0]);
                }
                if(!contained(states, temp[1])){
                    states  = sAdd(states, temp[1]);
                }
            }
        }
        for(int i=0;i<ones.length;i++){
            String [] temp = ones[i].split(",");
            if(temp.length != 1){
                if(!contained(states, temp[0])){
                    states = sAdd(states, temp[0]);
                }
                if(!contained(states, temp[1])){
                    states = sAdd(states, temp[1]);
                }
            }
        }
        for(int i=0;i<es.length;i++){
            String [] temp = es[i].split(",");
            if(temp.length != 1){
                if(!contained(states, temp[0])){
                    states = sAdd(states, temp[0]);
                }
                if(!contained(states, temp[1])){
                    states = sAdd(states, temp[1]);
                }
            }
        }
        return states;
    }

    public static String getFirst(String cell){
        String ret = "";
        for(int i=0;i<cell.length();i++){
            if(cell.charAt(i) != ','){
                ret+=cell.charAt(i);
            }
            else{
                break;
            }
        }
        return ret;
    }

    public static String getSecond(String cell){
        String ret = "";
        boolean x = true;
        for(int i=0;i<cell.length();i++){
            if(x){
                if(cell.charAt(i) == ','){
                    x=false;
                }
            }
            else{
                ret+=cell.charAt(i);
            }
        }
        return ret;
    }

    public void populate2dArray(String [] zeros, String [] ones, String [] es){
        for(int i=0;i<theNfaRepresentedAsA2dArray.length;i++){
            for(int j=0;j<theNfaRepresentedAsA2dArray[i].length;j++){
                theNfaRepresentedAsA2dArray[i][j] = "";
            }
        }
        if(!zeros[0].equals(""))
        for(int i =0 ;i<zeros.length;i++){
            theNfaRepresentedAsA2dArray[Integer.parseInt(getFirst(zeros[i]))][0] = sAdd(theNfaRepresentedAsA2dArray[Integer.parseInt(getFirst(zeros[i]))][0], getSecond(zeros[i]));
        }
        if(!ones[0].equals(""))
        for(int i =0 ;i<ones.length;i++){
            theNfaRepresentedAsA2dArray[Integer.parseInt(getFirst(ones[i]))][1] = sAdd(theNfaRepresentedAsA2dArray[Integer.parseInt(getFirst(ones[i]))][1], getSecond(ones[i]));
        }
        if(!es[0].equals(""))
        for(int i =0 ;i<es.length;i++){
            theNfaRepresentedAsA2dArray[Integer.parseInt(getFirst(es[i]))][2] = sAdd(theNfaRepresentedAsA2dArray[Integer.parseInt(getFirst(es[i]))][2], getSecond(es[i]));
        }

    } 

    public String statePosition(String state){
        for(int i=0;i<theStates.size();i++){
            if((contained(theStates.get(i)[0], state)) && (contained(state, theStates.get(i)[0]))){
                return ""+i;
            }
        }
        return null; 
    }

    public String getDirectEClosure(String s){
        return theNfaRepresentedAsA2dArray[Integer.parseInt(s)][2];
    } 

    public String [] newState(String state){
        String [] ret = {state, "", ""};
        return ret;
    }

    public nfa(String s){
        String [] initialTa2seema = s.split("#");
        String [] accs = initialTa2seema[initialTa2seema.length - 1].split(",");
        String [] zeros = initialTa2seema[0].split(";");
        String [] ones = initialTa2seema[1].split(";");
        String [] es = initialTa2seema[2].split(";");
        String states = getNfaStates(zeros, ones, es);
        theNfaRepresentedAsA2dArray = new String[states.split(",").length][3];
        populate2dArray(zeros, ones, es);
        ArrayList<String> queue = new ArrayList<String>();
        String initialState =  sAdd("0", theNfaRepresentedAsA2dArray[0][2]);
        String [] oneTimeThing = initialState.split(",");
        for(int i=0;i<oneTimeThing.length;i++){
            oneTimeThing = initialState.split(",");
            if(!contained(initialState, getDirectEClosure(oneTimeThing[i])))
                initialState = sAdd(initialState, getDirectEClosure(oneTimeThing[i]));
        }
        queue.add(initialState);
        theStates.add(newState(initialState));
        // int debug = 0;
        while(!queue.isEmpty()){
            // System.out.println(debug++);
            String currentState = queue.remove(0);
            String zeroState = "";
            String oneState = "";
            String[] currentStateArray = currentState.split(",");
            for(int i=0;i<currentStateArray.length;i++){
                if(!contained(zeroState, theNfaRepresentedAsA2dArray[Integer.parseInt(currentStateArray[i])][0]))
                    zeroState = sAdd(zeroState, theNfaRepresentedAsA2dArray[Integer.parseInt(currentStateArray[i])][0]);
                if(!contained(oneState, theNfaRepresentedAsA2dArray[Integer.parseInt(currentStateArray[i])][1]))
                    oneState = sAdd(oneState, theNfaRepresentedAsA2dArray[Integer.parseInt(currentStateArray[i])][1]);
            }
                
                // ZERO CLOSURE
            
            if(!zeroState.equals("")){
                oneTimeThing = zeroState.split(",");
                for(int j=0;j<oneTimeThing.length;j++){
                    if(!contained(zeroState, getDirectEClosure(oneTimeThing[j])))
                        zeroState = sAdd(zeroState, getDirectEClosure(oneTimeThing[j]));
                }
                if(statePosition(zeroState) == null){
                    queue.add(zeroState);
                    theStates.add(newState(zeroState));
                }
            }
            

                // ONE CLOSURE
            
            if(!oneState.equals("")){
                oneTimeThing = oneState.split(",");
                for(int j=0;j<oneTimeThing.length;j++){
                    if(!contained(oneState, getDirectEClosure(oneTimeThing[j])))
                        oneState = sAdd(oneState, getDirectEClosure(oneTimeThing[j]));
                }
                if(statePosition(oneState) == null){
                    queue.add(oneState);
                    theStates.add(newState(oneState));
                }
            }
            
            theStates.get(Integer.parseInt(statePosition(currentState)))[1] = zeroState;
            theStates.get(Integer.parseInt(statePosition(currentState)))[2] = oneState;

        }
        
        // System.out.println('\n');
        // System.out.println("check dfa state diagram with complex state names");
        // for(int finall=0;finall<theStates.size();finall++){
        //     System.out.println(theStates.get(finall)[0]+":  "+theStates.get(finall)[1]+":  "+theStates.get(finall)[2]);
        // }
        int[][] dfaStates = new int[theStates.size()+1][2];
        for(int hopefullyMyLastLoopCounter=0;hopefullyMyLastLoopCounter<theStates.size();hopefullyMyLastLoopCounter++){
            if(statePosition(theStates.get(hopefullyMyLastLoopCounter)[1]) != null)
                dfaStates[hopefullyMyLastLoopCounter][0] = Integer.parseInt(statePosition(theStates.get(hopefullyMyLastLoopCounter)[1]));
            else
                dfaStates[hopefullyMyLastLoopCounter][0] = theStates.size();
            
            if(statePosition(theStates.get(hopefullyMyLastLoopCounter)[2]) != null)            
                dfaStates[hopefullyMyLastLoopCounter][1] = Integer.parseInt(statePosition(theStates.get(hopefullyMyLastLoopCounter)[2]));
            else    
                dfaStates[hopefullyMyLastLoopCounter][1] = theStates.size();
        }
        dfaStates[theStates.size()][0] = theStates.size();
        dfaStates[theStates.size()][1] = theStates.size();
        
        // System.out.println('\n');
        // System.out.println("DFA state check");
        // for(int finall=0;finall<theStates.size()+1;finall++){
        //     System.out.println(dfaStates[finall][0]+":  "+dfaStates[finall][1]);
        // }
        boolean[] dfaAccept = new boolean[theStates.size()+1];
        for(int oopsMoreCounterVariables=0;oopsMoreCounterVariables<accs.length;oopsMoreCounterVariables++){
            for(int theLastCounter=0;theLastCounter<theStates.size();theLastCounter++){
                if(contained(theStates.get(theLastCounter)[0], accs[oopsMoreCounterVariables])){
                    dfaAccept[theLastCounter] = true;
                }
            }
        }
        // System.out.println("DFA check accept states");
        // for(int extraAddedCounter=0;extraAddedCounter<dfaAccept.length;extraAddedCounter++){
        //     System.out.println(dfaAccept[extraAddedCounter]);
        // }
        // System.out.println("\n\n\n\n");

        // create DFA
        myDfa = new dfa(dfaStates, dfaAccept);
    }
    
    public boolean run(String s){
        return myDfa.run(s);
    }

    public static void main(String[]args){
        // nfa myNfa = new nfa("0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3");
        // System.out.println(myNfa.run(""));     //f
        // System.out.println(myNfa.run("0"));     //f
        // System.out.println(myNfa.run("01"));    //f
        // System.out.println(myNfa.run("010"));   //f
        // System.out.println(myNfa.run("011"));   //t
        // System.out.println(myNfa.run("01010")); //t
        // System.out.println(myNfa.run("010100"));//t

        // nfa nfa2 = new nfa("0,0;0,1#1,0;0,1##0");
        // System.out.println(nfa2.run(""));   //t
        // System.out.println(nfa2.run("0"));  //t
        // System.out.println(nfa2.run("01")); //t
        // System.out.println(nfa2.run("00")); //t
        // System.out.println(nfa2.run("1"));  //f
        // System.out.println(nfa2.run("11")); //t
        // System.out.println(nfa2.run("10")); //f
        // System.out.println(nfa2.run("101"));    //f
        // System.out.println(nfa2.run("10111"));  //f
        // System.out.println(nfa2.run("1000100010010"));  //f

        // nfa nfa3 = new nfa("0,2;1,0;2,1#2,2;2,1#0,1#1");
        // System.out.println(nfa3.run(""));   //t
        // System.out.println(nfa3.run("0"));  //t
        // System.out.println(nfa3.run("000"));   //t
        // System.out.println(nfa3.run("001"));    //t
        // System.out.println(nfa3.run("0011"));   //t
        // System.out.println(nfa3.run("00110"));  //t
        // System.out.println();
        // System.out.println(nfa3.run("001101")); //f
        // System.out.println(nfa3.run("0101"));   //f
        // System.out.println(nfa3.run("010111")); //f
        // System.out.println(nfa3.run("010101")); //f
        // System.out.println(nfa3.run("1"));  //f
        // System.out.println(nfa3.run("10")); //f

        nfa nfa1 = new nfa("0,0;0,1;1,3;#1,2;2,4;4,4#0,1;3,4#3,4");
        nfa nfa2 = new nfa("0,1;1,3;4,5;5,5#1,2;2,4;4,4#0,1;3,4#5");
        
        System.out.println(nfa1.run("0010"));
        System.out.println(nfa1.run("0111"));
        System.out.println(nfa1.run("1010"));
        System.out.println(nfa1.run("1111"));
        System.out.println(nfa1.run("0110"));

        System.out.println();

        System.out.println(nfa2.run("1011"));
        System.out.println(nfa2.run("01100"));
        System.out.println(nfa2.run("000111"));
        System.out.println(nfa2.run("010"));
        System.out.println(nfa2.run("1111"));

    }
}
