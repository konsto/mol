package engine;

public class NegationEvaluator implements IUnaryEvaluator {

    @Override
    public IObject evaluate(IObject operand) throws Exception {
        return operand.negate();
    }

}
