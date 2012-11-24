package engine;

public class EqualToEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.equalTo(right);
    }

}
