package engine;

public class NotEqualToEvaluator implements IBinaryEvaluator {

    @Override
    public IObject evaluate(IObject left, IObject right) throws Exception {
        return left.notEqualTo(right);
    }

}
