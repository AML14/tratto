/*
 * generated by Xtext 2.29.0
 */
package star.tratto.oraclegrammar;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class TrattoGrammarStandaloneSetup extends TrattoGrammarStandaloneSetupGenerated {

	public static void doSetup() {
		new TrattoGrammarStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
