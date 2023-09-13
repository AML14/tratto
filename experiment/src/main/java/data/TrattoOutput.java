package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class represents a restricted version of an TrattoOutput produced by Tratto.
 * It only captures the information needed to generate a corresponding OracleOutput.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TrattoOutput(
        String oracle,
        // the type of oracle (e.g. pre-condition, post-condition, exceptional condition)
        OracleType oracleType,
        // the package name of the class under analysis
        String packageName,
        // the name of the class under analysis
        String className,
        // the source code of the method under analysis
        String methodSourceCode
) { }
