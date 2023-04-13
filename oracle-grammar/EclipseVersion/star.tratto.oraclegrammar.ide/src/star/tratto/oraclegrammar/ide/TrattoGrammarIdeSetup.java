/*
 * generated by Xtext 2.29.0
 */
package star.tratto.oraclegrammar.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import star.tratto.oraclegrammar.TrattoGrammarRuntimeModule;
import star.tratto.oraclegrammar.TrattoGrammarStandaloneSetup;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class TrattoGrammarIdeSetup extends TrattoGrammarStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new TrattoGrammarRuntimeModule(), new TrattoGrammarIdeModule()));
	}
	
}
