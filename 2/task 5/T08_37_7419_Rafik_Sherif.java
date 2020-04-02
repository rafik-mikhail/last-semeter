// T08_37_7419_Rafik_Sherif
import java.util.ArrayList;
// Lre
public class T08_37_7419_Rafik_Sherif{
    public static boolean capital(String s){
        if(s.charAt(0)<= 'Z' && s.charAt(0)>='A')   return true; else return false;
    }
    public static String LRE(String inputString){
        String[] fullRules = inputString.split(";");        // for full rules (each cell is for one full rule: S,Sct,T)
        // for(int q=0;q<fullRules.length;q++) System.out.println(fullRules[q]);       // display rules
        ArrayList<String> leftRecursive;        // for rules that are left recursive
        ArrayList<String> nonLeftRecursive;     // for rules that are not left recursive
        String[] individualRules;               // for the break down of single rules (each cell is one of the rules between | |)
        ArrayList<String> newRules = new ArrayList<String>();
        ArrayList<String> newRulesDash = new ArrayList<String>();
        for(int i=0;i<fullRules.length;i++){
            // System.out.println("Checking rule number: "+(i));
            // System.out.println("    "+fullRules[i]);
            leftRecursive = new ArrayList<String>();
            nonLeftRecursive = new ArrayList<String>();
            individualRules = fullRules[i].split(",");
            String theNewRule = "";             // the rule after left most variable substitution
            if(i==0){
                theNewRule = fullRules[0];      // for first rule the rule stays the same
                // System.out.println("i equals zero!");
                // System.out.println("the new rule = "+theNewRule);
            }
            for(int k=0;k<i;k++){       // Substitution of left most variables AKA the inner loop
                // System.out.println("i not equal zero!");
                // System.out.println("checking for substituting with rule " + k);
                theNewRule = "" + fullRules[i].charAt(0);
                for(int j=1;j<individualRules.length;j++){
                    // System.out.println("checking rule part "+j+":     " + individualRules[j]);
                    if(individualRules[j].charAt(0) == fullRules[k].charAt(0)){
                        // System.out.println("to be substituted with rule number  " + k);
                        String[] rulesToSubstitute = newRules.get(k).split(","); // newRules not yet populated
                        String[] rulesSubstituted = new String[rulesToSubstitute.length-1];
                        for(int q=1;q<rulesToSubstitute.length;q++){
                            // System.out.println("substituting with rule  " + rulesToSubstitute[q]);
                            rulesSubstituted[q-1] = rulesToSubstitute[q] + individualRules[j].substring(1);
                            // System.out.println("substitution yielded " + rulesSubstituted[q-1]);
                        }
                        for(int q=0;q<rulesSubstituted.length;q++){
                            theNewRule += "," + rulesSubstituted[q];
                        }
                    }
                    else{
                        theNewRule += "," + individualRules[j];
                    }
                }
                individualRules = theNewRule.split(",");
            }
            // System.out.println("full rule after substitution is: " + theNewRule);
            // left recursive elemination
            for(int r=1;r<individualRules.length;r++){
                if(individualRules[r].charAt(0) == individualRules[0].charAt(0)){
                    leftRecursive.add(individualRules[r]);
                    // System.out.println("A left recursive rule for rule "+individualRules[0]+" is: "+individualRules[r]);
                }
                else{
                    nonLeftRecursive.add(individualRules[r]);
                    // System.out.println("A non left recursive rule for rule "+individualRules[0]+" is: "+individualRules[r]);
                }
            }
            if(leftRecursive.size() == 0){
                // System.out.println("no left recursive rules for rule "+i);
                newRules.add(theNewRule);
            }
            else{
                String newRule = "" + theNewRule.charAt(0);
                String newRuleSymbol = "" + theNewRule.charAt(0) + '\'';
                String newRuleDash = "" + theNewRule.charAt(0) + '\'';
                // System.out.println("new rule with left variable: "+newRule);
                // System.out.println("new rule dash with left variable: "+newRuleSymbol);
                for(int z=0;z<nonLeftRecursive.size();z++){
                    newRule += ',' + nonLeftRecursive.get(z) + newRuleSymbol;
                }
                for(int z=0;z<leftRecursive.size();z++){
                    newRuleDash += ',' + leftRecursive.get(z).substring(1) + newRuleSymbol;
                }
                newRules.add(newRule);
                newRulesDash.add(newRuleDash);
                // System.out.println("the new rule is: "+newRule);
                // System.out.println("the new rule dash is: "+newRuleDash);
            }
        }
        String outputString = newRules.get(0);
        // System.out.println(outputString);
        if(!newRulesDash.isEmpty() && (outputString.charAt(0) == newRulesDash.get(0).charAt(0)))
            outputString += ";" + newRulesDash.remove(0);
        // System.out.println(outputString);
        for(int done=1;done<newRules.size();done++){
            if(!newRulesDash.isEmpty() && (newRules.get(done).charAt(0) == newRulesDash.get(0).charAt(0))){
                // System.out.println();
                outputString += ";" + newRules.get(done) + ";" + newRulesDash.remove(0);
            }
            else{
                outputString += ";" + newRules.get(done);
            }
        }
        // System.out.println(newRulesDash.isEmpty());
        return outputString;
    }
    public static void main(String[]args){
        System.out.println(LRE("S,ScT,T;T,aSb,iaLb,i;L,SdL,S"));
        String input = "S,ScT,T;T,aSb,iaLb,i;L,SdL,S";
        String output = LRE(input);
        System.out.println(output);
        System.out.println();
        System.out.println(LRE("S,Sa,b"));
        System.out.println(LRE("S,Sa,cd"));
        System.out.println(LRE("S,SuS,SS,S*,(S),a"));
        System.out.println(LRE("E,EuT,T;T,TF,F;F,F*,P;P,a,b"));
        System.out.println(LRE("A,0,T1;T,1,A0"));
        System.out.println(LRE("S,(L),a;L,L<S,S"));
        System.out.println(LRE("S,SS+,SS*,a"));

    }
}