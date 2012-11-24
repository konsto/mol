package engine;

public class DivisionEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.divide(right);

    }
}
