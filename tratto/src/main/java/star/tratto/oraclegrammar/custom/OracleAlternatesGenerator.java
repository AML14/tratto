package star.tratto.oraclegrammar.custom;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import star.tratto.oraclegrammar.trattoGrammar.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * DISCLAIMER: The code quality of this class is very poor. It contains a lot of
 * repeated code, long methods, hardly readable code, etc. It is also not unit-tested.
 * For the moment, it will remain as it is, since its only purpose is to generate
 * variants of the oracles for data augmentation (i.e., not widely used functionality,
 * and dispensable). However, it is integrated into the E2E test
 * {@code star.tratto.E2ETests#datasetsE2ETest()}, which checks two things for each
 * oracle variant generated, namely: 1) it can be parsed (i.e., the result of parsing
 * and reconstructing it is the same as the original); and 2) it can be reconstructed
 * based on the token datapoints generated from it.
 * <br><br>
 * This class relies on TrattoGrammar and on the
 * {@link star.tratto.oraclegrammar.custom.Parser} to analyze and manipulate the
 * oracles in multiple ways. All public methods are transformation rules that take
 * as input a given oracle as a String and return as output a list of alternate
 * versions of the oracle (as Strings as well), which are syntactically equivalent.
 */
public class OracleAlternatesGenerator {
    private static final Parser parser = Parser.getInstance();

    /**
     * Example input: {@code A ? B : C}
     * <br>
     * Output: {@code !A ? C : B}
     * <br><br>
     * This rule is only applied if the three predicates of a precondition are
     * different from "true" (the grammar would make the resulting oracles invalid
     * otherwise).
     */
    public static List<String> getAlternateOraclePostcondition(String oracleString) {
        List<String> alternateOracles = new ArrayList<>();
        oracleString = compactExpression(oracleString);

        if (oracleString.contains("?") && oracleString.contains(":") && !oracleString.startsWith("true?") && !oracleString.endsWith(":true;")) {
            Oracle oracle = parser.getOracle(oracleString);

            // Negate first clause
            PredicateNoTrue firstPredicateNoTrue = oracle.getPredicate().getPredicateNoTrue();
            negatePredicateClause(firstPredicateNoTrue);

            // Switch second and third clauses
            Predicate secondClause = EcoreUtil.copy(oracle.getTrueProperty());
            Predicate thirdClause = EcoreUtil.copy(oracle.getFalseProperty());
            oracle.setTrueProperty(thirdClause);
            oracle.setFalseProperty(secondClause);

            alternateOracles.add(compactExpression(split(oracle)));
        }

        return alternateOracles;
    }

    private static void negatePredicateClause(PredicateNoTrue firstPredicateNoTrue) {
        Clause firstClause = firstPredicateNoTrue.getClause();
        if (firstPredicateNoTrue.getClauseContinuations() != null && !firstPredicateNoTrue.getClauseContinuations().isEmpty()) {
            firstPredicateNoTrue.getClause().setPredicateNoTrue(EcoreUtil.copy(firstPredicateNoTrue));
            firstPredicateNoTrue.getClauseContinuations().clear();
            firstPredicateNoTrue.getClause().setClauseTrue(null);
        }
        if (firstClause.getEqOperator() == null) {
            if (firstClause.getClauseTrue() != null) {
                firstClause.setPredicateNoTrue((PredicateNoTrue) EcoreUtil.copy(firstClause.eContainer()));
                firstClause.setClauseTrue(null);
            }
            if (firstClause.getOpeningParenthesis() == null) {
                firstClause.setOpeningParenthesis("(");
                firstClause.setClosingParenthesis(")");
            }
            firstClause.setEqOperator("==");
            firstClause.setFalse("false");
        } else {
            firstClause.setEqOperator(null);
            firstClause.setFalse(null);
        }
    }

    /**
     * Example input: {@code A && B}
     * <br>
     * Output 1: {@code (A) && B}
     * <br>
     * Output 2: {@code A && (B)}
     */
    public static List<String> getAlternateOraclesClausesWithParentheses(String oracleString) {
        List<String> alternateOracles = new ArrayList<>();
        Oracle oracle = parser.getOracle(oracleString);
        List<Clause> clauses = IteratorExtensions.toList(oracle.eAllContents()).stream().filter(eo -> eo instanceof Clause).map(eo -> (Clause) eo).collect(Collectors.toList());
        for (int i = 0; i < clauses.size(); i++) {
            Clause clause = clauses.get(i);
            if (clause.getOpeningParenthesis() == null) {
                Oracle oracleCopy = EcoreUtil.copy(oracle);
                List<Clause> clausesCopy = IteratorExtensions.toList(oracleCopy.eAllContents()).stream().filter(eo -> eo instanceof Clause).map(eo -> (Clause) eo).collect(Collectors.toList());
                Clause clauseCopy = clausesCopy.get(i);

                PredicateNoTrue predicateNoTrueCopyCopy = clauseCopy.eContainer() instanceof PredicateNoTrue ? (PredicateNoTrue) EcoreUtil.copy(clauseCopy.eContainer()) : null;
                if (predicateNoTrueCopyCopy != null) {
                    clauseCopy.setPredicateNoTrue(predicateNoTrueCopyCopy);
                    clauseCopy.setClauseTrue(null);
                    if (predicateNoTrueCopyCopy.getClauseContinuations() != null) {
                        predicateNoTrueCopyCopy.getClauseContinuations().clear();
                    }
                } else { // Parent is ClauseContinuation, not PredicateNoTrue
                    predicateNoTrueCopyCopy = (PredicateNoTrue) EcoreUtil.copy(clauseCopy.eContainer().eContainer());
                    Clause clauseCopyCopy = EcoreUtil.copy(clauseCopy);
                    clauseCopy.setPredicateNoTrue(predicateNoTrueCopyCopy);
                    clauseCopy.setClauseTrue(null);
                    predicateNoTrueCopyCopy.setClause(clauseCopyCopy);
                    predicateNoTrueCopyCopy.getClauseContinuations().clear();
                }
                clauseCopy.setOpeningParenthesis("(");
                clauseCopy.setClosingParenthesis(")");

                alternateOracles.add(compactExpression(split(oracleCopy)));
            }
        }

        return alternateOracles;
    }

    /**
     * Example input: {@code A != null}
     * <br>
     * Output: {@code (A == null) == false}
     */
    public static List<String> getAlternateOraclesSwitchIneqOp(String oracleString) {
        List<String> alternateOracles = new ArrayList<>();
        Oracle oracle = parser.getOracle(oracleString);

        List<ClauseWithVars> clausesWithIneqOp = IteratorExtensions.toList(oracle.eAllContents())
                .stream()
                .filter(eo -> eo instanceof ClauseWithVars)
                .map(eo -> (ClauseWithVars) eo)
                .filter(cwv -> cwv.getIneqOperator() != null)
                .collect(Collectors.toList());
        for (int i = 0; i < clausesWithIneqOp.size(); i++) {
            Oracle oracleCopy = EcoreUtil.copy(oracle);
            List<ClauseWithVars> clausesWithIneqOpCopy = IteratorExtensions.toList(oracleCopy.eAllContents())
                    .stream()
                    .filter(eo -> eo instanceof ClauseWithVars)
                    .map(eo -> (ClauseWithVars) eo)
                    .filter(cwv -> cwv.getIneqOperator() != null)
                    .collect(Collectors.toList());
            ClauseWithVars clauseWithIneqOpCopy = clausesWithIneqOpCopy.get(i);

            CanEvaluateToPrimitive originalLeftElement = EcoreUtil.copy(clauseWithIneqOpCopy.getCanEvaluateToPrimitive());
            EObject originalRightElement = null;
            clauseWithIneqOpCopy.setIneqOperator(null);
            clauseWithIneqOpCopy.setSecondEqOperator("==");
            if (clauseWithIneqOpCopy.getSecondEqIneqComparisonElement() != null) {
                originalRightElement = EcoreUtil.copy(clauseWithIneqOpCopy.getSecondEqIneqComparisonElement());
                clauseWithIneqOpCopy.setFirstEqIneqComparisonElement(EcoreUtil.copy(clauseWithIneqOpCopy.getSecondEqIneqComparisonElement()));
                clauseWithIneqOpCopy.setSecondEqIneqComparisonElement(null);
            } else if (clauseWithIneqOpCopy.getSecondOtherComparisonElement() != null) {
                originalRightElement = EcoreUtil.copy(clauseWithIneqOpCopy.getSecondOtherComparisonElement());
                clauseWithIneqOpCopy.setFirstOtherComparisonElement(EcoreUtil.copy(clauseWithIneqOpCopy.getSecondOtherComparisonElement()));
                clauseWithIneqOpCopy.setSecondOtherComparisonElement(null);
            }

            EObject parent3rdLevelCopy = clauseWithIneqOpCopy.eContainer().eContainer().eContainer();
            PredicateNoTrue parentPredicateNoTrueCopy = parent3rdLevelCopy instanceof PredicateNoTrue ? (PredicateNoTrue) parent3rdLevelCopy : null;
            if (parentPredicateNoTrueCopy == null) {
                parentPredicateNoTrueCopy = (PredicateNoTrue) parent3rdLevelCopy.eContainer();
            }

            if (parent3rdLevelCopy instanceof ClauseContinuation) {
                Clause clauseToModify = ((ClauseContinuation) parent3rdLevelCopy).getClause();
                PredicateNoTrue clauseToModifyAsPredicate = EcoreUtil.copy(parentPredicateNoTrueCopy);
                clauseToModifyAsPredicate.setClause(EcoreUtil.copy(clauseToModify));
                clauseToModifyAsPredicate.getClauseContinuations().clear();
                clauseToModifyAsPredicate.getClause().setPredicateNoTrue(EcoreUtil.copy(clauseToModifyAsPredicate));
                clauseToModifyAsPredicate.getClause().setClauseTrue(null);
                clauseToModifyAsPredicate.getClause().setOpeningParenthesis("(");
                clauseToModifyAsPredicate.getClause().setClosingParenthesis(")");
                clauseToModifyAsPredicate.getClause().setEqOperator("==");
                clauseToModifyAsPredicate.getClause().setFalse("false");
                clauseToModify.setPredicateNoTrue(clauseToModifyAsPredicate);
                clauseToModify.setClauseTrue(null);
            } else if (parentPredicateNoTrueCopy.getClauseContinuations() != null && !parentPredicateNoTrueCopy.getClauseContinuations().isEmpty()) {
                Clause parentClauseCopy = parent3rdLevelCopy instanceof ClauseContinuation ? ((ClauseContinuation)parent3rdLevelCopy).getClause() : parentPredicateNoTrueCopy.getClause();

                if (parentClauseCopy.getEqOperator() == null) {
                    if (parentClauseCopy.getClauseTrue() != null) {
                        PredicateNoTrue parentPredicateNoTrueCopyCopy = (PredicateNoTrue) (parentClauseCopy.eContainer() instanceof PredicateNoTrue ? parentClauseCopy.eContainer() : parentClauseCopy.eContainer().eContainer());
                        parentClauseCopy.setPredicateNoTrue(EcoreUtil.copy(parentPredicateNoTrueCopyCopy));
                        parentClauseCopy.setClauseTrue(null);
                    }
                    if (parentClauseCopy.getOpeningParenthesis() == null) {
                        parentClauseCopy.setOpeningParenthesis("(");
                        parentClauseCopy.setClosingParenthesis(")");
                    }
                    parentClauseCopy.setEqOperator("==");
                    parentClauseCopy.setFalse("false");
                } else {
                    parentClauseCopy.setEqOperator(null);
                    parentClauseCopy.setFalse(null);
                }
                parentClauseCopy.getPredicateNoTrue().getClauseContinuations().clear();
                try {
                    ClauseWithVars clauseToBeFixed = ((ClauseContinuation) parent3rdLevelCopy).getClause().getPredicateNoTrue().getClause().getClauseTrue().getClauseWithVars();
                    if (!"==".equals(clauseToBeFixed.getSecondEqOperator())) {
                        clauseToBeFixed.setIneqOperator(null);
                        clauseToBeFixed.setNonEqIneqOperator(null);
                        clauseToBeFixed.setSecondEqIneqComparisonElement(null);
                        clauseToBeFixed.setSecondOtherComparisonElement(null);
                        clauseToBeFixed.setThirdOtherComparisonElement(null);
                        clauseToBeFixed.setSecondEqOperator("==");
                        clauseToBeFixed.setCanEvaluateToPrimitive(originalLeftElement);
                        if (originalRightElement instanceof EqIneqComparisonElement) {
                            clauseToBeFixed.setFirstEqIneqComparisonElement((EqIneqComparisonElement) originalRightElement);
                        } else if (originalRightElement instanceof OtherComparisonElement) {
                            clauseToBeFixed.setFirstOtherComparisonElement((OtherComparisonElement) originalRightElement);
                        }
                    }
                } catch (Exception ignored) {}
            } else {
                negatePredicateClause(parentPredicateNoTrueCopy);
            }

            alternateOracles.add(compactExpression(split(oracleCopy)));
        }

        return alternateOracles;
    }

    /**
     * Example input: {@code A == null}
     * <br>
     * Output: {@code (A != null) == false}
     */
    public static List<String> getAlternateOraclesSwitchEqOp(String oracleString) {
        List<String> alternateOracles = new ArrayList<>();
        Oracle oracle = parser.getOracle(oracleString);

        List<ClauseWithVars> clausesWithEqOp = IteratorExtensions.toList(oracle.eAllContents())
                .stream()
                .filter(eo -> eo instanceof ClauseWithVars)
                .map(eo -> (ClauseWithVars) eo)
                .filter(cwv -> cwv.getSecondEqOperator() != null)
                .collect(Collectors.toList());
        for (int i = 0; i < clausesWithEqOp.size(); i++) {
            Oracle oracleCopy = EcoreUtil.copy(oracle);
            List<ClauseWithVars> clausesWithEqOpCopy = IteratorExtensions.toList(oracleCopy.eAllContents())
                    .stream()
                    .filter(eo -> eo instanceof ClauseWithVars)
                    .map(eo -> (ClauseWithVars) eo)
                    .filter(cwv -> cwv.getSecondEqOperator() != null)
                    .collect(Collectors.toList());
            ClauseWithVars clauseWithEqOpCopy = clausesWithEqOpCopy.get(i);
            if (clauseWithEqOpCopy.getThirdThis() != null) {
                continue;
            }

            CanEvaluateToPrimitive originalLeftElement = EcoreUtil.copy(clauseWithEqOpCopy.getCanEvaluateToPrimitive());
            EObject originalRightElement = null;
            clauseWithEqOpCopy.setSecondEqOperator(null);
            clauseWithEqOpCopy.setIneqOperator("!=");
            if (clauseWithEqOpCopy.getFirstEqIneqComparisonElement() != null) {
                originalRightElement = EcoreUtil.copy(clauseWithEqOpCopy.getFirstEqIneqComparisonElement());
                clauseWithEqOpCopy.setSecondEqIneqComparisonElement((EcoreUtil.copy(clauseWithEqOpCopy.getFirstEqIneqComparisonElement())));
                clauseWithEqOpCopy.setFirstEqIneqComparisonElement(null);
            } else if (clauseWithEqOpCopy.getFirstOtherComparisonElement() != null) {
                originalRightElement = EcoreUtil.copy(clauseWithEqOpCopy.getFirstOtherComparisonElement());
                clauseWithEqOpCopy.setSecondOtherComparisonElement(EcoreUtil.copy(clauseWithEqOpCopy.getFirstOtherComparisonElement()));
                clauseWithEqOpCopy.setFirstOtherComparisonElement(null);
            }

            EObject parent3rdLevelCopy = clauseWithEqOpCopy.eContainer().eContainer().eContainer();
            PredicateNoTrue parentPredicateNoTrueCopy = parent3rdLevelCopy instanceof PredicateNoTrue ? (PredicateNoTrue) parent3rdLevelCopy : null;
            if (parentPredicateNoTrueCopy == null) {
                parentPredicateNoTrueCopy = (PredicateNoTrue) parent3rdLevelCopy.eContainer();
            }

            if (parentPredicateNoTrueCopy.getClauseContinuations() != null && !parentPredicateNoTrueCopy.getClauseContinuations().isEmpty()) {
                Clause parentClauseCopy = parent3rdLevelCopy instanceof ClauseContinuation ? ((ClauseContinuation)parent3rdLevelCopy).getClause() : parentPredicateNoTrueCopy.getClause();

                if (parentClauseCopy.getEqOperator() == null) {
                    if (parentClauseCopy.getClauseTrue() != null) {
                        PredicateNoTrue parentPredicateNoTrueCopyCopy = (PredicateNoTrue) (parentClauseCopy.eContainer() instanceof PredicateNoTrue ? parentClauseCopy.eContainer() : parentClauseCopy.eContainer().eContainer());
                        parentClauseCopy.setPredicateNoTrue(EcoreUtil.copy(parentPredicateNoTrueCopyCopy));
                        parentClauseCopy.setClauseTrue(null);
                    }
                    if (parentClauseCopy.getOpeningParenthesis() == null) {
                        parentClauseCopy.setOpeningParenthesis("(");
                        parentClauseCopy.setClosingParenthesis(")");
                    }
                    parentClauseCopy.setEqOperator("==");
                    parentClauseCopy.setFalse("false");
                } else {
                    parentClauseCopy.setEqOperator(null);
                    parentClauseCopy.setFalse(null);
                }
                parentClauseCopy.getPredicateNoTrue().getClauseContinuations().clear();
                try {
                    ClauseWithVars clauseToBeFixed = ((ClauseContinuation) parent3rdLevelCopy).getClause().getPredicateNoTrue().getClause().getClauseTrue().getClauseWithVars();
                    if (!"!=".equals(clauseToBeFixed.getIneqOperator())) {
                        clauseToBeFixed.setSecondEqOperator(null);
                        clauseToBeFixed.setNonEqIneqOperator(null);
                        clauseToBeFixed.setFirstEqIneqComparisonElement(null);
                        clauseToBeFixed.setFirstOtherComparisonElement(null);
                        clauseToBeFixed.setThirdOtherComparisonElement(null);
                        clauseToBeFixed.setIneqOperator("!=");
                        clauseToBeFixed.setCanEvaluateToPrimitive(originalLeftElement);
                        if (originalRightElement instanceof EqIneqComparisonElement) {
                            clauseToBeFixed.setSecondEqIneqComparisonElement((EqIneqComparisonElement) originalRightElement);
                        } else if (originalRightElement instanceof OtherComparisonElement) {
                            clauseToBeFixed.setSecondOtherComparisonElement((OtherComparisonElement) originalRightElement);
                        }
                    }
                } catch (Exception ignored) {}
            } else {
                negatePredicateClause(parentPredicateNoTrueCopy);
            }

            alternateOracles.add(compactExpression(split(oracleCopy)));
        }

        return alternateOracles;
    }

    /**
     * Example input: {@code A == true || B}
     * <br>
     * Output: {@code A || B}
     */
    public static List<String> getAlternateOraclesRemoveEqualsTrue(String oracleString) {
        List<String> alternateOracles = new ArrayList<>();
        String toRemove = "==true";
        String originalOracle = oracleString;
        while (oracleString.contains(toRemove)) {
            int occurrenceIndex = originalOracle.indexOf("==true");
            String alternateOracle = originalOracle.substring(0, occurrenceIndex) + originalOracle.substring(occurrenceIndex + 6);
            alternateOracles.add(alternateOracle);
            oracleString = oracleString.replaceFirst(toRemove, "");
        }
        return alternateOracles;
    }

    /**
     * Example input: {@code A <= 1}
     * <br>
     * Output: {@code (A > 1) == false}
     */
    public static List<String> getAlternateOraclesSwitchNonEqIneqOp(String oracleString) {
        List<String> alternateOracles = new ArrayList<>();
        Oracle oracle = parser.getOracle(oracleString);

        List<ClauseWithVars> clausesWithNonEqIneqOp = IteratorExtensions.toList(oracle.eAllContents())
                .stream()
                .filter(eo -> eo instanceof ClauseWithVars)
                .map(eo -> (ClauseWithVars) eo)
                .filter(cwv -> cwv.getNonEqIneqOperator() != null)
                .collect(Collectors.toList());
        for (int i = 0; i < clausesWithNonEqIneqOp.size(); i++) {
            Oracle oracleCopy = EcoreUtil.copy(oracle);
            List<ClauseWithVars> clausesWithNonEqIneqOpCopy = IteratorExtensions.toList(oracleCopy.eAllContents())
                    .stream()
                    .filter(eo -> eo instanceof ClauseWithVars)
                    .map(eo -> (ClauseWithVars) eo)
                    .filter(cwv -> cwv.getNonEqIneqOperator() != null)
                    .collect(Collectors.toList());
            ClauseWithVars clauseWithNonEqIneqOpCopy = clausesWithNonEqIneqOpCopy.get(i);

            CanEvaluateToPrimitive originalLeftElement = EcoreUtil.copy(clauseWithNonEqIneqOpCopy.getCanEvaluateToPrimitive());
            OtherComparisonElement originalRightElement = EcoreUtil.copy(clauseWithNonEqIneqOpCopy.getThirdOtherComparisonElement());
            String expectedNewOp = switch (clauseWithNonEqIneqOpCopy.getNonEqIneqOperator()) {
                case ">" -> "<=";
                case ">=" -> "<";
                case "<" -> ">=";
                case "<=" -> ">";
                default -> null;
            };
            clauseWithNonEqIneqOpCopy.setNonEqIneqOperator(expectedNewOp);

            EObject parent3rdLevelCopy = clauseWithNonEqIneqOpCopy.eContainer().eContainer().eContainer();
            PredicateNoTrue parentPredicateNoTrueCopy = parent3rdLevelCopy instanceof PredicateNoTrue ? (PredicateNoTrue) parent3rdLevelCopy : null;
            if (parentPredicateNoTrueCopy == null) {
                parentPredicateNoTrueCopy = (PredicateNoTrue) parent3rdLevelCopy.eContainer();
            }

            if (parentPredicateNoTrueCopy.getClauseContinuations() != null && !parentPredicateNoTrueCopy.getClauseContinuations().isEmpty()) {
                Clause parentClauseCopy = parent3rdLevelCopy instanceof ClauseContinuation ? ((ClauseContinuation)parent3rdLevelCopy).getClause() : parentPredicateNoTrueCopy.getClause();

                if (parentClauseCopy.getEqOperator() == null) {
                    if (parentClauseCopy.getClauseTrue() != null) {
                        PredicateNoTrue parentPredicateNoTrueCopyCopy = (PredicateNoTrue) (parentClauseCopy.eContainer() instanceof PredicateNoTrue ? parentClauseCopy.eContainer() : parentClauseCopy.eContainer().eContainer());
                        parentClauseCopy.setPredicateNoTrue(EcoreUtil.copy(parentPredicateNoTrueCopyCopy));
                        parentClauseCopy.setClauseTrue(null);
                    }
                    if (parentClauseCopy.getOpeningParenthesis() == null) {
                        parentClauseCopy.setOpeningParenthesis("(");
                        parentClauseCopy.setClosingParenthesis(")");
                    }
                    parentClauseCopy.setEqOperator("==");
                    parentClauseCopy.setFalse("false");
                } else {
                    parentClauseCopy.setEqOperator(null);
                    parentClauseCopy.setFalse(null);
                }
                parentClauseCopy.getPredicateNoTrue().getClauseContinuations().clear();
                try {
                    ClauseWithVars clauseToBeFixed = ((ClauseContinuation) parent3rdLevelCopy).getClause().getPredicateNoTrue().getClause().getClauseTrue().getClauseWithVars();
                    String modifiedOp = clauseToBeFixed.getNonEqIneqOperator();
                    if (!expectedNewOp.equals(modifiedOp)) {
                        clauseToBeFixed.setSecondEqOperator(null);
                        clauseToBeFixed.setIneqOperator(null);
                        clauseToBeFixed.setFirstEqIneqComparisonElement(null);
                        clauseToBeFixed.setFirstOtherComparisonElement(null);
                        clauseToBeFixed.setSecondEqIneqComparisonElement(null);
                        clauseToBeFixed.setSecondOtherComparisonElement(null);
                        clauseToBeFixed.setCanEvaluateToPrimitive(originalLeftElement);
                        clauseToBeFixed.setThirdOtherComparisonElement(originalRightElement);
                        clauseToBeFixed.setNonEqIneqOperator(expectedNewOp);
                    }
                } catch (Exception ignored) {}
            } else {
                negatePredicateClause(parentPredicateNoTrueCopy);
            }

            alternateOracles.add(compactExpression(split(oracleCopy)));
        }

        return alternateOracles;
    }
}
