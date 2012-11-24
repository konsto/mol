package engine;

public class LessThanOrEqualToEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.lessThanOrEqualTo(right);
    }

}
