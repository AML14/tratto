package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class MethodWithoutArgumentsRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static MethodWithoutArgumentsRestriction getMethodWithoutArgumentsRestriction() {
        return MethodWithoutArgumentsRestriction.getInstance();
    }
}
