package engine;

public class GreaterThanOrEqualToEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.greaterThanOrEqualTo(right);
    }

}
