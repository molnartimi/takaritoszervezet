package takaritoszervezet;


import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Atom;
import jason.asSyntax.Term;

public class defaultInternalActionExample extends DefaultInternalAction {
	 @Override
	 public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
		 return un.unifies(args[0], new Atom("5"));
	 }
}
