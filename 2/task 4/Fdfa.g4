grammar Fdfa;
prog: D | B | C | A;
A: ('0'* '1'+) {System.out.println("01");};
B: (('0'* '1'+ '0' '0'+ '1'+)+) {System.out.println("01");};
C: ('0'* '1'+ '0') {System.out.println("10");};
D: (('0'* '1'+ '0' '0'+ '1'+ '0')+) {System.out.println("10");};
WS : [\n]+ -> skip ;