package engine;

public class SubtractionEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.subtract(right);
    }

}
