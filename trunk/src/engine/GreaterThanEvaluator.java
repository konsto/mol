package engine;

public class GreaterThanEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.greaterThan(right);
    }

}
