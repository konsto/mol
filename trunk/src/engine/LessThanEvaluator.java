package engine;

public class LessThanEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.lessThan(right);
    }

}
