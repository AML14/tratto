package star.tratto.enums;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.resolution.MethodUsage;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public enum MethodFeature {
    STATIC("static"),
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    FINAL("final"),
    VOID("void");

    private final String value;

    private MethodFeature(String value) {
        this.value = value;
    }

    public String getValue() { return this.value; }

    public static List<Predicate<MethodDeclaration>> generateMethodDeclarationPredicates(
            List<Pair<MethodFeature, Boolean>> features
    ) {
        // Generate predicates to find the methods that satisfy the given conditions
        List<Predicate<MethodDeclaration>> predicates = new ArrayList<>();
        // Iterate over the list of features to generate the corresponding predicates
        for(Pair<MethodFeature, Boolean> feature: features) {
            MethodFeature methodFeature = feature.getValue0();
            Boolean is = feature.getValue1();
            if (methodFeature.equals(MethodFeature.PUBLIC)) {
                if (is) {
                    // Add the predicates to ensure the method is public
                    predicates.add(method -> method.isPublic());
                } else {
                    // Add the predicates to ensure the method is not public
                    predicates.add(method -> !method.isPublic());
                }
            }
            if (methodFeature.equals(MethodFeature.PRIVATE)) {
                if (is) {
                    // Add the predicates to ensure the method is private
                    predicates.add(method -> method.isPrivate());
                } else {
                    // Add the predicates to ensure the method is not private
                    predicates.add(method -> !method.isPrivate());
                }
            }
            if (methodFeature.equals(MethodFeature.PROTECTED)) {
                if (is) {
                    // Add the predicates to ensure the method is protected
                    predicates.add(method -> method.isProtected());
                } else {
                    // Add the predicates to ensure the method is not protected
                    predicates.add(method -> !method.isProtected());
                }
            }
            if (methodFeature.equals(MethodFeature.STATIC)) {
                if (is) {
                    // Add the predicates to ensure the method is static
                    predicates.add(method -> method.isStatic());
                } else {
                    // Add the predicates to ensure the method is not static
                    predicates.add(method -> !method.isStatic());
                }
            }
            if (methodFeature.equals(MethodFeature.VOID)) {
                if (is) {
                    // Add the predicates to ensure the method is void
                    predicates.add(method -> method.getType().isVoidType());
                } else {
                    // Add the predicates to ensure the method is not void
                    predicates.add(method -> !method.getType().isVoidType());
                }
            }
        }
        return predicates;
    }

    public static List<Predicate<MethodUsage>> generateMethodUsagePredicates(
            List<Pair<MethodFeature, Boolean>> features
    ) {
        // Generate predicates to find the methods that satisfy the given conditions
        List<Predicate<MethodUsage>> predicates = new ArrayList<>();
        // Iterate over the list of features to generate the corresponding predicates
        for(Pair<MethodFeature, Boolean> feature: features) {
            MethodFeature methodFeature = feature.getValue0();
            Boolean is = feature.getValue1();
            if (methodFeature.equals(MethodFeature.PUBLIC)) {
                if (is) {
                    // Add the predicates to ensure the method is public
                    predicates.add(method -> method.getDeclaration().accessSpecifier().equals(AccessSpecifier.PUBLIC));
                } else {
                    // Add the predicates to ensure the method is not public
                    predicates.add(method -> !method.getDeclaration().accessSpecifier().equals(AccessSpecifier.PUBLIC));
                }
            }
            if (methodFeature.equals(MethodFeature.PRIVATE)) {
                if (is) {
                    // Add the predicates to ensure the method is private
                    predicates.add(method -> method.getDeclaration().accessSpecifier().equals(AccessSpecifier.PRIVATE));
                } else {
                    // Add the predicates to ensure the method is not private
                    predicates.add(method -> !method.getDeclaration().accessSpecifier().equals(AccessSpecifier.PRIVATE));
                }
            }
            if (methodFeature.equals(MethodFeature.PROTECTED)) {
                if (is) {
                    // Add the predicates to ensure the method is protected
                    predicates.add(method -> method.getDeclaration().accessSpecifier().equals(AccessSpecifier.PROTECTED));
                } else {
                    // Add the predicates to ensure the method is not protected
                    predicates.add(method -> !method.getDeclaration().accessSpecifier().equals(AccessSpecifier.PROTECTED));
                }
            }
            if (methodFeature.equals(MethodFeature.STATIC)) {
                if (is) {
                    // Add the predicates to ensure the method is static
                    predicates.add(method -> method.getDeclaration().isStatic());
                } else {
                    // Add the predicates to ensure the method is not static
                    predicates.add(method -> !method.getDeclaration().isStatic());
                }
            }
            if (methodFeature.equals(MethodFeature.VOID)) {
                if (is) {
                    // Add the predicates to ensure the method is void
                    predicates.add(method -> method.getDeclaration().getReturnType().isVoid());
                } else {
                    // Add the predicates to ensure the method is not void
                    predicates.add(method -> !method.getDeclaration().getReturnType().isVoid());
                }
            }
        }
        return predicates;
    }
}
