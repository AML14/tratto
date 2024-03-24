package data;

/**
 * This enum defines all possible operations of the main Tog class.
 */
public enum OperationType {
    /** Removes oracles from a test suite. */
    REMOVE_ORACLES,
    /** Adds oracles to a test prefix. */
    INSERT_ORACLES,
    /** Preprocesses an input file to be passed to a TOG. */
    GENERATE_TOG_INPUT,
    /** Post-processes the output of a TOG into an {@link OracleOutput}. */
    GENERATE_ORACLE_OUTPUT,
    /** Converts Defects4J run_bug_detection output into a {@link Defects4JOutput}. */
    GENERATE_DEFECTS4J_OUTPUT,
    COUNT_TEST_METHODS
}
