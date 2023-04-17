package star.tratto.token.restrictions.single;

import star.tratto.oracle.OracleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class contains the default SingleTokenRestrictions. This is because SingleTokenRestrictions
 * are not supposed to be created (its constructor is private), but rather to be predefined.
 */
public class SingleTokenRestrictions {

    public static final List<SingleTokenRestriction> DEFAULT_SINGLE_RESTRICTIONS = List.of(
            // Preconditions cannot make use of the ternary operator ( ? : ):
            new StandardSingleTokenRestriction(OracleType.PRE, "?", null, null, true),
            // true is not a valid Predicate in preconditions:
            new StandardSingleTokenRestriction(OracleType.PRE, "true", null, List.of(), true),
            // Preconditions cannot mention methodResultID:
            new StandardSingleTokenRestriction(OracleType.PRE, "methodResultID", null, null, true),
            // Preconditions must mention the argument of the method they refer to and/or this:
            PreYesArgumentOrThisRestriction.getInstance(),
            // Normal postconditions must make use of the ternary operator ( ? : ):
            new StandardSingleTokenRestriction(OracleType.NORMAL_POST, ";", List.of(), List.of("?"), false),
            // true is not a valid Predicate in the true property of normal postconditions:
            new StandardSingleTokenRestriction(OracleType.NORMAL_POST, "true", List.of("?"), List.of(), false),
            // Normal postconditions cannot mention methodResultID in the guard:
            new StandardSingleTokenRestriction(OracleType.NORMAL_POST, "methodResultID", null, List.of("?"), true),
            // Normal postconditions must mention methodResultID and/or this in the true property
            new StandardSingleTokenRestriction(OracleType.NORMAL_POST, ":", null, List.of("methodResultID", "this"), true),
            // Exceptional postconditions cannot make use of the ternary operator ( ? : ):
            new StandardSingleTokenRestriction(OracleType.EXCEPT_POST, "?", null, null, true),
            // Exceptional postconditions cannot mention methodResultID:
            new StandardSingleTokenRestriction(OracleType.EXCEPT_POST, "methodResultID", null, null, true),
            // "this" is forbidden if the method under test is static:
            NoThisRestriction.getInstance(),
            // "jdVar" is only allowed within the argument of a method performed on a stream (e.g., anyMatch), otherwise it is forbidden:
            NoJdVarRestriction.getInstance(),
            // "." is forbidden if last token is a method name, or if previous VarOrClassWithModifiers is not a class, or if its return type is primitive:
            NoPeriodRestriction.getInstance(),
            // "instanceof" is forbidden if previous element return type is not a class. This means that it is also forbidden after a class name:
            NoInstanceofRestriction.getInstance(),
            // If left element is not integral, forbid "~":
            NoBitwiseNegateOperatorRestriction.getInstance(),
            // If the last token is an attribute name and not a method name, the next token cannot be a "(":
            NoOpeningParenthesisRestriction.getInstance(),
            // If current method argument does not comply with type, forbid comma:
            NoCommaRestriction.getInstance(),
            // If last method argument does not comply with type, forbid closing parenthesis:
            NoClosingParenthesisRestriction.getInstance(),
            // Forbid "Arrays" if nor methodResultID or some method argument is an array:
            NoArraysRestriction.getInstance(),
            // Forbid "stream" if nor this, methodResultID or some method argument is a collection:
            NoStreamRestriction.getInstance()
    );

    /**
     * Keys: PRE, NORMAL_POST, EXCEPT_POST. Values: The tokens that could be restricted for each of the three oracle types.
     */
    public static Map<OracleType, List<String>> RESTRICTED_TOKENS = Map.of(
            OracleType.PRE, new ArrayList<>(),
            OracleType.NORMAL_POST, new ArrayList<>(),
            OracleType.EXCEPT_POST, new ArrayList<>()
    );

    static {
        for (SingleTokenRestriction restriction : DEFAULT_SINGLE_RESTRICTIONS) {
            if (restriction.getOracleType() != null) {
                RESTRICTED_TOKENS.get(restriction.getOracleType()).add(restriction.getRestrictedToken());
            } else {
                RESTRICTED_TOKENS.get(OracleType.PRE).add(restriction.getRestrictedToken());
                RESTRICTED_TOKENS.get(OracleType.NORMAL_POST).add(restriction.getRestrictedToken());
                RESTRICTED_TOKENS.get(OracleType.EXCEPT_POST).add(restriction.getRestrictedToken());
            }
        }
    }

    public static SingleTokenRestriction getSingleTokenRestriction(OracleType oracleType, String token) {
        return DEFAULT_SINGLE_RESTRICTIONS
                .stream()
                .filter(restriction -> restriction.getOracleType() == null || restriction.getOracleType() == oracleType)
                .filter(restriction -> restriction.getRestrictedToken().equals(token))
                .findFirst()
                .orElse(null);
    }

}
