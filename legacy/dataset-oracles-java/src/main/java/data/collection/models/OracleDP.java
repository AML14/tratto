package data.collection.models;

import data.collection.enums.OracleDPAttrKey;
import data.collection.enums.OracleType;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import data.collection.enums.JavadocValueType;

import java.util.HashMap;
import java.util.List;

/**
 * The record models an oracle-datapoint.
 * @param id The unique identifier of an oracle data-point.
 * @param oracle The string representation of an oracle.
 * @param oracleType The type of the oracle, corresponding to a JDoctor pre-condition, post-condition, or exceptional
 *                   condition.
 * @param projectName The name of the Java project where the oracle is defined.
 * @param packageName The package name of the class of the method to which the oracle of the JDoctor condition refers.
 * @param className The class name of the method to which the oracle of the JDoctor condition refers.
 * @param javadocTag The Javadoc tag of the JDoctor condition.
 * @param methodJavadoc The Javadoc comment of the method to which the Javadoc condition refers.
 * @param methodSourceCode The source code of the method to which the Javadoc condition refers.
 * @param classJavadoc The Javadoc comment of the class where the method to which the Javadoc condition refers is defined.
 * @param classSourceCode The source code of the class where the method to which the Javadoc condition refers is defined.
 * @param tokensGeneralGrammar The general grammar tokens (the same for each oracle data-point).
 * @param tokensGeneralValuesGlobalDictionary The general values global dictionary tokens (the same for each oracle data-point).
 * @param tokensProjectClasses A list of pairs of strings {@link Pair<String,String>}, each one representing the class name
 *                             and the package name of a class of the Java project to which the JDoctor condition refers,
 *                             and from which the oracle data-point is built.
 * @param tokensProjectClassesNonPrivateStaticNonVoidMethods A list of quartets of strings {@link Quartet<String,String,String,String>},
 *                                                           representing the methods declared within each class of the
 *                                                           Java project to which the JDoctor condition refers, and from
 *                                                           which the oracle data-point is built.
 * @param tokensProjectClassesNonPrivateStaticAttributes A list of quartets of strings {@link Quartet<String,String,String,String>},
 *                                                       representing the attributes declared within each class of the Java
 *                                                       project to which the JDoctor condition refers, and from which
 *                                                       the oracle data-point is built.
 * @param tokensMethodJavadocValues The integer, real, and string values defined within the Javadoc comment of
 *                                  the method/constructor to which the JDoctor condition refers, in the form of a list
 *                                  of pairs of strings {@link Pair<String,String>}, where the first element of each pair
 *                                  is the numeric or string value, while the second element is the type of the value
 *                                  {@link JavadocValueType} (integer, real, or string).
 * @param tokensMethodArguments the corresponding value contains the list of arguments of the method/constructor to which
 *                              a JDoctor condition refers, in the form of a list of triplets of strings
 *                              {@link Triplet<String,String,String>}. Each triplet contains the argument name, package name,
 *                              and type name of each argument.
 * @param tokensMethodVariablesNonPrivateNonStaticNonVoidMethods A list of quartets of strings {@link Quartet<String,String,String,String>},
 *                                                               representing (i) the non-private, non-static, non-void
 *                                                               methods declared within the class and superclasses where
 *                                                               the method/constructor to which the JDoctor condition refers
 *                                                               is defined, (ii) the non-private, non-static, non-void
 *                                                               methods declared within the class and superclasses of the
 *                                                               parameter types of the method/constructor to which a JDoctor
 *                                                               condition refers is defined, and (iii) the non-private,
 *                                                               non-static, non-void methods declared within the class
 *                                                               and superclasses of the return type of the method/constructor
 *                                                               to which a JDoctor condition refers is defined.
 * @param tokensMethodVariablesNonPrivateNonStaticAttributes A list of quartets of strings {@link Quartet<String,String,String,String>},
 *                                                           representing (i) the non-private, non-static attributes declared
 *                                                           within the class and superclasses where the method/constructor
 *                                                           to which a JDoctor condition refers is defined, (ii) the non-private,
 *                                                           non-static attributes declared within the class and superclasses
 *                                                           of the parameter types of the method/constructor to which a
 *                                                           JDoctor condition refers is defined, and (iii) the non-private,
 *                                                           non-static attributes declared within the class and superclasses
 *                                                           of the return type of the method/constructor to which a JDoctor
 *                                                           condition refers is defined (no return type if the method
 *                                                           considered is a constructor).
 * @param tokensOracleVariablesNonPrivateNonStaticNonVoidMethods A list of quartets of strings {@link Quartet<String,String,String,String>},
 *                                                               representing the non-private, non-static, non-void methods
 *                                                               declared within the classes that represent the return types
 *                                                               of each subexpression of the oracle defined within the
 *                                                               JDoctor condition.
 * @param tokensOracleVariablesNonPrivateNonStaticAttributes A list of quartets of strings {@link Quartet<String,String,String,String>},
 *                                                           representing the non-private, non-static attributes declared
 *                                                           within the classes that represent the return types of each
 *                                                           subexpression of the oracle defined within the JDoctor condition.
 */
public record OracleDP(
    Integer id,
    String oracle,
    OracleType oracleType,
    String projectName,
    String packageName,
    String className,
    String javadocTag,
    String methodJavadoc,
    String methodSourceCode,
    String classJavadoc,
    String classSourceCode,
    List<String> tokensGeneralGrammar,
    List<Pair<String, String>> tokensGeneralValuesGlobalDictionary, // <token, type>
    List<Pair<String, String>> tokensProjectClasses, // <token, package>
    List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticNonVoidMethods, // <token, package, class, signature>
    List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticAttributes, // <token, package, class, declaration>
    List<Pair<String, String>> tokensMethodJavadocValues, // <token, type>
    List<Triplet<String, String, String>> tokensMethodArguments, // <token, package, class>
    List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods, // <token, package, class, signature>
    List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticAttributes, // <token, package, class, declaration>
    List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods, // <token, package, class, signature>
    List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticAttributes // <token, package, class, declaration>
) {
    public static OracleDP generateOracleDPFromHashMap(
            HashMap<OracleDPAttrKey,OracleDPAttrValue> oraclesDPMap
    ) {
        return new OracleDP(
                (Integer) oraclesDPMap.get(OracleDPAttrKey.ID).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.ORACLE).value(),
                (OracleType) oraclesDPMap.get(OracleDPAttrKey.ORACLE_TYPE).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.PROJECT_NAME).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.PACKAGE_NAME).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.CLASS_NAME).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.JAVADOC_TAG).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.METHOD_JAVADOC).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.METHOD_SOURCE_CODE).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.CLASS_JAVADOC).value(),
                (String) oraclesDPMap.get(OracleDPAttrKey.CLASS_SOURCE_CODE).value(),
                (List<String>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_GENERAL_GRAMMAR).value(),
                (List<Pair<String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_GENERAL_VALUES_GLOBAL_DICTIONARY).value(),
                (List<Pair<String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_PROJECT_CLASSES).value(),
                (List<Quartet<String, String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_NON_VOID_METHODS).value(),
                (List<Quartet<String, String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_ATTRIBUTES).value(),
                (List<Pair<String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_METHOD_JAVADOC_VALUES).value(),
                (List<Triplet<String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_METHOD_ARGUMENTS).value(),
                (List<Quartet<String, String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS).value(),
                (List<Quartet<String, String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES).value(),
                (List<Quartet<String, String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS).value(),
                (List<Quartet<String, String, String, String>>) oraclesDPMap.get(OracleDPAttrKey.TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES).value()
        );
    }
}

