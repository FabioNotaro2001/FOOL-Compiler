package svm;
public class ExecuteVM {

    // Dimensione massima del programma, cioè della pila.
    public static final int CODESIZE = 10000;

    // Grandezza della memoria
    public static final int MEMSIZE = 10000;
    
    private int[] code; // Rimane inalterato durante l'esecuzione il suo contenuto è quello messo dal parser
    private int[] memory = new int[MEMSIZE]; // Viene utilizzata durante l'esecuzione
    
    private int ip = 0; // Istruction pointer, punto della prossima istruzione da eseguire
    private int sp = MEMSIZE; // stack pointer che punta al fondo della memoria(cima stack) che va verso indirizzi più bassi
    
    private int hp = 0;  // heap pointer per classi, noi non utiliziamo
    private int fp = MEMSIZE; // Punta agli argomenti della funzione e alle variabili locali, e dopo di essi c'è il vecchio fp (ra)
    private int ra; // Quando viene chaimata una funzione esso punta all'indirizzo di ritorno della funzione chiamante (posto dove è chiama la nuova funzione)
    private int tm;
    
    public ExecuteVM(int[] code) {
      this.code = code;
    }
    
    public void cpu() {
        // Per tutte le istruzioni
      while ( true ) {
        int bytecode = code[ip++]; // fetch dell'istruzione
        int v1,v2; // Argomenti che possono essere al massimo 2
        int address;
        switch ( bytecode ) {
          case SVMParser.PUSH:
            push( code[ip++] ); // Inseriamo dentro a memory l'argomento della push che si trova nella cella successiva
            break;
          case SVMParser.POP:
            pop();
            break;
          case SVMParser.ADD :
            v1=pop();
            v2=pop();
            push(v2 + v1);
            break;
          case SVMParser.MULT :
            v1=pop();
            v2=pop();
            push(v2 * v1);
            break;
          case SVMParser.DIV :
            v1=pop();
            v2=pop();
            push(v2 / v1);
            break;
          case SVMParser.SUB :
            v1=pop();
            v2=pop();
            push(v2 - v1);
            break;
          case SVMParser.STOREW : // Salva nell'indirizzo preso dalla prima pop di memory un valore preso dalla seconda pop
            address = pop();
            memory[address] = pop();    
            break;
          case SVMParser.LOADW : // Prende l'indirizzo con la pop e mette in cima alla pila il valore che si trova a quell'indirizzo
            push(memory[pop()]);
            break;
          case SVMParser.BRANCH : // jump incondizionale, prende l'indirizzo specificato come argomento e salta a quell'indirizzo modificano l'istruction pointer
            address = code[ip];
            ip = address;
            break;
          case SVMParser.BRANCHEQ : // Come sopra ma salta solo se i 2 elementi in cima alla pila sono uguali
            address = code[ip++];
            v1=pop();
            v2=pop();
            if (v2 == v1) ip = address;
            break;
          case SVMParser.BRANCHLESSEQ : // ... solo se il secondo è <= del primo
            address = code[ip++];
            v1=pop();
            v2=pop();
            if (v2 <= v1) ip = address;
            break;
          case SVMParser.JS : // jump to subroutine, nel return address mette l'indirizzo corrente dove dovrà tornare alla fine della subroutine e poi salta all'indirizzo che ha preso dallo stack
            address = pop();
            ra = ip;
            ip = address;
            break;
         case SVMParser.STORERA : //
            ra=pop();
            break;
         case SVMParser.LOADRA : //
            push(ra);
            break;
         case SVMParser.STORETM : 
            tm=pop();
            break;
         case SVMParser.LOADTM : 
            push(tm);
            break;
         case SVMParser.LOADFP : //
            push(fp);
            break;
         case SVMParser.STOREFP : //
            fp=pop();
            break;
         case SVMParser.COPYFP : //
            fp=sp;
            break;
         case SVMParser.STOREHP : //
            hp=pop();
            break;
         case SVMParser.LOADHP : //
            push(hp);
            break;
         case SVMParser.PRINT : // Stampa il valore presente nella memoria(top dello stack) all'indirizzo dello stack pointer, se c'è qualcosa.
            System.out.println((sp<MEMSIZE)?memory[sp]:"Empty stack!");
            break;
         case SVMParser.HALT : // termina il programma
            return;
        }
      }
    } 
    
    private int pop() {
      return memory[sp++];
    } // Toglie qualcosa dallo stack
    
    private void push(int v) {
      memory[--sp] = v;
    } // Mette qualcosa nello stack
    
}