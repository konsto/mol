package engine;

public class MultiplicationEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.multiply(right);
    }

}
