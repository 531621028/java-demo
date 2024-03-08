package generic;

/**
 * @author kang
 * @date 2023/9/7
 */
public interface Operation {

    double apply(double x, double y);


    enum BasicOperation implements Operation {
        PLUS("+") {
            @Override
            public double apply(double x, double y) {
                return x + y;
            }
        };

        BasicOperation(String symbol) {
            this.symbol = symbol;
        }

        private final String symbol;
    }


}
