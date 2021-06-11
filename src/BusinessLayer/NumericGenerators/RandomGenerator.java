package BusinessLayer.NumericGenerators;

import java.util.Random;

public class RandomGenerator extends NumericGenerator {

    private Random r;

    public RandomGenerator(){
        r = new Random();
    }

    @Override
    public int generate(int bound) {
        return r.nextInt(bound);
    }
}
