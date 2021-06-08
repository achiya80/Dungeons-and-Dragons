package BusinessLayer.NumericGenerators;

import java.util.Random;

public class RandomGenerator extends NumericGenerator {

    private Random r = new Random();

    public RandomGenerator(){

    }


    @Override
    public int generate(int bound) {
        return r.nextInt(bound);
    }
}
