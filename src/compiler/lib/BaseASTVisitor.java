package compiler.lib;

import compiler.AST.*;
import compiler.exc.*;

import static compiler.lib.FOOLlib.*;

// Classe base dedicata all'esplorazione e alla stampa dell'albero da cui ereditano i visitor specializzati.
// Implementa i metodi generali, comuni a tutti i visitor, ad esempio la stampa del nodo visitato.
// Più i metodi di visita che saranno svrascritti dagli altri visitor più specializzati.
// I tipi generici, e è l'eccezione ed S è il tipo restituito dalla visita del nodo, esso è dettato dalla classe che estende BaseASTVisitor
public class BaseASTVisitor<S,E extends Exception> {

	private boolean incomplExc; // Se incontra un ecezione lo dice o propaga i null
	protected boolean print;    // enables printing
	protected String indent;
	
	protected BaseASTVisitor() {}
	protected BaseASTVisitor(boolean ie) { incomplExc = ie; } 
	protected BaseASTVisitor(boolean ie, boolean p) { incomplExc = ie; print = p; } 

	protected void printNode(Node n) {
		System.out.println(indent+extractNodeName(n.getClass().getName())); // Stampa il nome della produzione (toglie dal nome della classe il "Node"), correttamente indentato
	}

	protected void printNode(Node n, String s) { // Nome produzione più dati aggiuntivi (es id, STEntry...)
		System.out.println(indent+extractNodeName(n.getClass().getName())+": "+s);
	}

	public S visit(Visitable v) throws E {
		return visit(v, "");                //performs unmarked visit
	}

	// DIsegna le cose indentate
	// Il mark sono ulteriori caratteri da aggiungere alla stampa, es -> nel tipo di una funzione (int, int -> int)(parametro, parametro -> tipo ritorno)
	public S visit(Visitable v, String mark) throws E {
		if (v==null)                                      
			if (incomplExc) throw new IncomplException(); 
			else                                         
				return null; 
		if (print) {
			String temp = indent;
			indent = (indent == null) ? "" : indent + "  ";
			indent+=mark; //inserts mark
			try {
				S result = visitByAcc(v);
				return result;
			} finally { indent = temp; }
		} else 
			return visitByAcc(v);
	}

	// Sempre per stampare, ma non si può usare direttamente ma solo attraverso visit, dalle classi esterne
	// Si passa il nodo generico su cui si chiama accept, al fine di evitare di capire il tipo a runtime
	S visitByAcc(Visitable v) throws E {
		return v.accept(this);
	}

	// Metodi più specifici per ciascun nodo, sovrascritti dai visitor specializzati
	public S visitNode(ProgLetInNode n) throws E {throw new UnimplException();}
	public S visitNode(ProgNode n) throws E {throw new UnimplException();}
	public S visitNode(FunNode n) throws E {throw new UnimplException();}
	public S visitNode(ParNode n) throws E {throw new UnimplException();}
	public S visitNode(VarNode n) throws E {throw new UnimplException();}
	public S visitNode(PrintNode n) throws E {throw new UnimplException();}
	public S visitNode(IfNode n) throws E {throw new UnimplException();}
	public S visitNode(EqualNode n) throws E {throw new UnimplException();}
	public S visitNode(TimesNode n) throws E {throw new UnimplException();}
	public S visitNode(PlusNode n) throws E {throw new UnimplException();}
	public S visitNode(CallNode n) throws E {throw new UnimplException();}
	public S visitNode(IdNode n) throws E {throw new UnimplException();}
	public S visitNode(BoolNode n) throws E {throw new UnimplException();}
	public S visitNode(IntNode n) throws E {throw new UnimplException();}	

	public S visitNode(ArrowTypeNode n) throws E {throw new UnimplException();}
	public S visitNode(BoolTypeNode n) throws E {throw new UnimplException();}
	public S visitNode(IntTypeNode n) throws E {throw new UnimplException();}
}
