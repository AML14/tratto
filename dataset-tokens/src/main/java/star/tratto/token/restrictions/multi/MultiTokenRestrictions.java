package star.tratto.token.restrictions.multi;

import java.util.List;

/**
 * This class contains the default MultiTokenRestrictions. This is because MultiTokenRestrictions
 * are not supposed to be created (its constructor is private), but rather to be predefined.
 */
public class MultiTokenRestrictions {

    /**
     * ORDER IS IMPORTANT! This is because some MultiTokenRestrictions are more restrictive than others,
     * so evaluating them in a certain order makes the approach more efficient. Also because the check for
     * the last token being a method name MUST be the first one (otherwise an exception may be thrown).
     */
    public static final List<MultiTokenRestriction> DEFAULT_MULTI_RESTRICTIONS = List.of(
            // If the last token is a method name, the next token MUST be a "(" (i.e., forbids any other token):
            LastMethodNameRestriction.getInstance(),
            // Forbid all tokens except ")" if previous tokens are method name and "(" and method has no arguments:
            MethodWithoutArgumentsRestriction.getInstance(),
            // If last token is class name, and second to last token is not "instanceof", the next token MUST be a "." (i.e., forbids any other token):
            LastClassWithoutInstanceofRestriction.getInstance(),
            // NonEqIneqOperator tokens are forbidden if previous element's return type is not number-like (int, long, float, double, and corresponding wrappers):
            NoNonEqIneqOperatorRestriction.getInstance(),
            // In a comparison, if previous element does not evaluate to number-like, forbid ArithmeticalOperators:
            NoArithmeticalOperatorRestriction.getInstance(),
            // In a comparison, if previous element does not evaluate to integral, forbid BitwiseOperators:
            NoBitwiseOperatorRestriction.getInstance(),
            // Forbid elements that end an expression if it is not complete:
            ForbidEndExpressionRestriction.getInstance()
    );
}
