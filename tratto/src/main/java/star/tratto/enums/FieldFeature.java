package data.collection.enums;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public enum FieldFeature {
    STATIC("static"),
    PUBLIC("public"),
    PRIVATE("private"),
    FINAL("final"),
    PROTECTED("protected");

    private final String value;

    private FieldFeature(String value) {
        this.value = value;
    }

    public String getValue() { return this.value; }

    public static List<Predicate<FieldDeclaration>> generateFieldDeclarationPredicates(
            List<Pair<FieldFeature, Boolean>> features
    ) {
        // Generate predicates to find the methods that satisfy the given conditions
        List<Predicate<FieldDeclaration>> predicates = new ArrayList<>();
        // Iterate over the list of features to generate the corresponding predicates
        for(Pair<FieldFeature, Boolean> feature: features) {
            FieldFeature fieldFeature = feature.getValue0();
            Boolean is = feature.getValue1();
            if (fieldFeature.equals(FieldFeature.PUBLIC)) {
                if (is) {
                    // Add the predicates to ensure the method is public
                    predicates.add(field -> field.isPublic());
                } else {
                    // Add the predicates to ensure the method is not public
                    predicates.add(field -> !field.isPublic());
                }
            }
            if (fieldFeature.equals(FieldFeature.PRIVATE)) {
                if (is) {
                    // Add the predicates to ensure the method is private
                    predicates.add(field -> field.isPrivate());
                } else {
                    // Add the predicates to ensure the method is not private
                    predicates.add(field -> !field.isPrivate());
                }
            }
            if (fieldFeature.equals(FieldFeature.PROTECTED)) {
                if (is) {
                    // Add the predicates to ensure the method is private
                    predicates.add(field -> field.isProtected());
                } else {
                    // Add the predicates to ensure the method is not private
                    predicates.add(field -> !field.isProtected());
                }
            }
            if (fieldFeature.equals(FieldFeature.STATIC)) {
                if (is) {
                    // Add the predicates to ensure the method is static
                    predicates.add(field -> field.isStatic());
                } else {
                    // Add the predicates to ensure the method is not static
                    predicates.add(field -> !field.isStatic());
                }
            }
        }
        return predicates;
    }

    public static List<Predicate<ResolvedFieldDeclaration>> generateResolvedFieldDeclarationPredicates(
            List<Pair<FieldFeature, Boolean>> features
    ) {
        // Generate predicates to find the methods that satisfy the given conditions
        List<Predicate<ResolvedFieldDeclaration>> predicates = new ArrayList<>();
        // Iterate over the list of features to generate the corresponding predicates
        for(Pair<FieldFeature, Boolean> feature: features) {
            FieldFeature fieldFeature = feature.getValue0();
            Boolean is = feature.getValue1();
            if (fieldFeature.equals(FieldFeature.PUBLIC)) {
                if (is) {
                    // Add the predicates to ensure the method is public
                    predicates.add(field -> field.accessSpecifier().equals(AccessSpecifier.PUBLIC));
                } else {
                    // Add the predicates to ensure the method is not public
                    predicates.add(field -> !field.accessSpecifier().equals(AccessSpecifier.PUBLIC));
                }
            }
            if (fieldFeature.equals(FieldFeature.PRIVATE)) {
                if (is) {
                    // Add the predicates to ensure the method is private
                    predicates.add(field -> field.accessSpecifier().equals(AccessSpecifier.PRIVATE));
                } else {
                    // Add the predicates to ensure the method is not private
                    predicates.add(field -> !field.accessSpecifier().equals(AccessSpecifier.PRIVATE));
                }
            }
            if (fieldFeature.equals(FieldFeature.PROTECTED)) {
                if (is) {
                    // Add the predicates to ensure the method is private
                    predicates.add(field -> field.accessSpecifier().equals(AccessSpecifier.PROTECTED));
                } else {
                    // Add the predicates to ensure the method is not private
                    predicates.add(field -> !field.accessSpecifier().equals(AccessSpecifier.PROTECTED));
                }
            }
            if (fieldFeature.equals(FieldFeature.STATIC)) {
                if (is) {
                    // Add the predicates to ensure the method is static
                    predicates.add(field -> field.isStatic());
                } else {
                    // Add the predicates to ensure the method is not static
                    predicates.add(field -> !field.isStatic());
                }
            }
        }
        return predicates;
    }
}
