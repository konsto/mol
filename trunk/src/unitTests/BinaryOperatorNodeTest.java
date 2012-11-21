package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.IExpressionNode;
import ast.LiteralNode;
import ast.TestVisitor;

public class BinaryOperatorNodeTest {

    @Test
    public void testGetting() {
        LiteralNode  leftOperand = new LiteralNode(1);
        LiteralNode rightOperand = new LiteralNode(2);
        BinaryOperatorType operator = BinaryOperatorType.ADDITION;
        BinaryOperatorNode node = new BinaryOperatorNode(leftOperand, 
                rightOperand, operator);
        assertEquals(leftOperand, node.getLeftOperand());
        assertEquals(rightOperand, node.getRightOperand());
        assertEquals(operator, node.getOperator());
    }
    
    @Test
    public void testAccept() {
        LiteralNode  leftOperand = new LiteralNode(1);
        LiteralNode rightOperand = new LiteralNode(2);
        BinaryOperatorType operator = BinaryOperatorType.ADDITION;
        BinaryOperatorNode node = new BinaryOperatorNode(leftOperand, 
                rightOperand, operator);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertTrue(visitor.message.equals("BinaryOperatorNode"));
    }

}
