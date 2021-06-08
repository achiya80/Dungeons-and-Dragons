package BusinessLayer.NumericGenerators;

public abstract class NumericGenerator {
    protected static RandomGenerator randomGenerator;

    protected static DeterministicGenerator deterministicGenerator;

    protected NumericGenerator(){

    }

    public static NumericGenerator getInstance(boolean debug){
        if(debug){
            randomGenerator = (randomGenerator == null) ? new RandomGenerator() : randomGenerator;
        }
        else{
            deterministicGenerator =  (deterministicGenerator == null) ? new DeterministicGenerator() : deterministicGenerator;
        }
        return (debug) ? randomGenerator : deterministicGenerator;
    }

    public abstract int generate(int bound);
}
