package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.IExpressionNode;
import ast.LiteralNode;
import ast.TestVisitor;
import ast.UnaryOperatorNode;
import ast.UnaryOperatorType;

public class UnaryOperatorNodeTest {

    @Test
    public void testGetting() {
        IExpressionNode operand = new LiteralNode(1);
        UnaryOperatorType operator = UnaryOperatorType.LOGICAL_NEGATION;
        UnaryOperatorNode node = new UnaryOperatorNode(operand, operator);
        assertEquals(operand, node.getOperand());
        assertEquals(operator, node.getOperator());
    }
    
    @Test
    public void testAccept() {
        IExpressionNode operand = new LiteralNode(1);
        UnaryOperatorType operator = UnaryOperatorType.LOGICAL_NEGATION;
        UnaryOperatorNode node = new UnaryOperatorNode(operand, operator);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertTrue(visitor.message.equals("UnaryOperatorNode"));
    }

}
