package star.tratto.identifiers;

import com.github.javaparser.ast.type.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JPTypeTest {
    @Test
    public void getValueTest() {
        assertEquals("ARRAY_TYPE", JPType.ARRAY_TYPE.getValue());
        assertEquals("CLASS_OR_INTERFACE_TYPE", JPType.CLASS_OR_INTERFACE_TYPE.getValue());
        assertEquals("INTERSECTION_TYPE", JPType.INTERSECTION_TYPE.getValue());
        assertEquals("PRIMITIVE_TYPE", JPType.PRIMITIVE_TYPE.getValue());
        assertEquals("REFERENCE_TYPE", JPType.REFERENCE_TYPE.getValue());
        assertEquals("TYPE_PARAMETER", JPType.TYPE_PARAMETER.getValue());
        assertEquals("UNION_TYPE", JPType.UNION_TYPE.getValue());
        assertEquals("UNKNOWN_TYPE", JPType.UNKNOWN_TYPE.getValue());
        assertEquals("VAR_TYPE", JPType.VAR_TYPE.getValue());
        assertEquals("VOID_TYPE", JPType.VOID_TYPE.getValue());
        assertEquals("WILDCARD", JPType.WILDCARD.getValue());
    }

    @Test
    public void getJPTypeDeclarationTest() {
        assertEquals("CLASS_OR_INTERFACE_TYPE", JPType.getJPTypeDeclaration(new ClassOrInterfaceType()).getValue());
        assertEquals("PRIMITIVE_TYPE", JPType.getJPTypeDeclaration(new PrimitiveType()).getValue());
        assertEquals("TYPE_PARAMETER", JPType.getJPTypeDeclaration(new TypeParameter()).getValue());
        assertEquals("UNION_TYPE", JPType.getJPTypeDeclaration(new UnionType()).getValue());
        assertEquals("UNKNOWN_TYPE", JPType.getJPTypeDeclaration(new UnknownType()).getValue());
        assertEquals("VAR_TYPE", JPType.getJPTypeDeclaration(new VarType()).getValue());
        assertEquals("VOID_TYPE", JPType.getJPTypeDeclaration(new VoidType()).getValue());
        assertEquals("WILDCARD", JPType.getJPTypeDeclaration(new WildcardType()).getValue());
    }
}
