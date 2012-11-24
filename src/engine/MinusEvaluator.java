package engine;

public class MinusEvaluator implements IUnaryEvaluator {

    @Override
    public IObject evaluate(IObject operand) throws Exception {
        return operand.minus();
    }

}
