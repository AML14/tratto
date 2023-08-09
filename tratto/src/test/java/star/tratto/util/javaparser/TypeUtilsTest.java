package star.tratto.util.javaparser;

import com.github.javaparser.JavaToken;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.TypeParameter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUtilsTest {
    @Test
    public void removeTypeArgumentsTest() {
        assertEquals("List", TypeUtils.removeTypeArgumentsAndSemicolon("List<? extends Integer>"));
        assertEquals("ArrayList", TypeUtils.removeTypeArgumentsAndSemicolon("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", TypeUtils.removeTypeArgumentsAndSemicolon("Oversuperstition"));
        assertEquals("Foo", TypeUtils.removeTypeArgumentsAndSemicolon("Foo<? super Collection<T>, T>"));
    }

    @Test
    public void getPackageNameFromNameSegmentsTest() {
        assertEquals("normal.package.setup.for.a", TypeUtils.getPackageNameFromClassGetName("normal.package.setup.for.a.Class$InnerClass"));
    }

    @Test
    public void classGetNameToClassGetSimpleNameTest() {
        assertEquals(List.of("int"), TypeUtils.classGetNameToClassGetSimpleName(List.of("int")));
        assertEquals(List.of("int[][]"), TypeUtils.classGetNameToClassGetSimpleName(List.of("[[I")));
        assertEquals(List.of("MyClass"), TypeUtils.classGetNameToClassGetSimpleName(List.of("MyClass")));
        assertEquals(List.of("MyClass[]"), TypeUtils.classGetNameToClassGetSimpleName(List.of("[LMyClass;")));
        assertEquals(List.of("Integer"), TypeUtils.classGetNameToClassGetSimpleName(List.of("java.lang.Integer")));
        assertEquals(List.of("Integer[]"), TypeUtils.classGetNameToClassGetSimpleName(List.of("[Ljava.lang.Integer;")));
        assertEquals(List.of("Inner"), TypeUtils.classGetNameToClassGetSimpleName(List.of("pkg.Outer$Inner")));
        assertEquals(List.of("Inner[]"), TypeUtils.classGetNameToClassGetSimpleName(List.of("[Lpkg.Outer$Inner;")));
    }

    @Test
    public void hasEllipsisTest() {
        String normalType = "int";
        String almost = "How..";
        String varArg = "int...";
        assertFalse(TypeUtils.hasEllipsis(normalType));
        assertFalse(TypeUtils.hasEllipsis(almost));
        assertTrue(TypeUtils.hasEllipsis(varArg));
    }

    @Test
    public void getRawTypeNamePrimitiveTest() {
        PrimitiveType integerType = new PrimitiveType(PrimitiveType.Primitive.INT);
        ClassOrInterfaceDeclaration jpClass = new ClassOrInterfaceDeclaration()
                .setName("Foo")
                .addModifier(Modifier.Keyword.PUBLIC);
        MethodDeclaration jpCallable = new MethodDeclaration()
                .setName("printElements")
                .addModifier(Modifier.Keyword.PUBLIC)
                .addParameter(integerType, "arg0");
        assertEquals("int", TypeUtils.getRawTypeName(jpClass, jpCallable, jpCallable.getParameter(0)));
    }

    @Test
    public void getRawTypeNameVarArgTest() {
        PrimitiveType integerType = new PrimitiveType(PrimitiveType.Primitive.INT);
        Parameter parameter = new Parameter(integerType, "arg0")
                .setVarArgs(true);
        ClassOrInterfaceDeclaration jpClass = new ClassOrInterfaceDeclaration()
                .setName("Foo")
                .addModifier(Modifier.Keyword.PUBLIC);
        MethodDeclaration jpCallable = new MethodDeclaration()
                .setName("printElements")
                .addModifier(Modifier.Keyword.PUBLIC)
                .addParameter(parameter);
        assertEquals("int[]", TypeUtils.getRawTypeName(jpClass, jpCallable, jpCallable.getParameter(0)));
    }

    @Test
    public void getRawTypeNameWithoutGenericUpperBoundTest() {
        ClassOrInterfaceDeclaration jpClass = new ClassOrInterfaceDeclaration()
                .setName("Foo")
                .addModifier(Modifier.Keyword.PUBLIC);
        MethodDeclaration jpCallable = jpClass.addMethod("printElements")
                .addModifier(Modifier.Keyword.PUBLIC)
                .addTypeParameter(new TypeParameter("T"))
                .addParameter("T", "arg0");
        assertEquals("T", TypeUtils.getRawTypeName(jpClass, jpCallable, jpCallable.getParameter(0)));
    }

    @Test
    public void getRawTypeNameWithGenericUpperBoundTest() {
        // create generic type with upper bound
        ClassOrInterfaceType upperBound = new ClassOrInterfaceType()
                .setName("Integer");
        TypeParameter genericWithUpperBound = new TypeParameter("U", new NodeList<>(upperBound));
        // instantiate new class and method
        CompilationUnit cu = new CompilationUnit();
        ClassOrInterfaceDeclaration jpClass = cu.addClass("Foo")
                .addModifier(Modifier.Keyword.PUBLIC)
                .addTypeParameter(genericWithUpperBound);
        MethodDeclaration jpCallable = jpClass.addMethod("printElements")
                .addModifier(Modifier.Keyword.PUBLIC)
                .addParameter("U", "arg0");
        assertEquals("Integer", TypeUtils.getRawTypeName(jpClass, jpCallable, jpCallable.getParameter(0)));
    }

    @Test
    public void isStandardTypeTest() {
        assertTrue(TypeUtils.isObjectOrComparable("Comparable"));
        assertFalse(TypeUtils.isObjectOrComparable("Other"));
    }

    @Test
    public void isStandardTypeArrayTest() {
        assertTrue(TypeUtils.isObjectOrComparableArray("Object[]"));
        assertFalse(TypeUtils.isObjectOrComparableArray("Object"));
        assertFalse(TypeUtils.isObjectOrComparableArray("AnyOtherObject"));
        assertFalse(TypeUtils.isObjectOrComparableArray("AnyOtherObjectArray[]"));
    }
}
