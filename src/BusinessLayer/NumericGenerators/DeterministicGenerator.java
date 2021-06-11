package BusinessLayer.NumericGenerators;

public class DeterministicGenerator extends NumericGenerator{

    @Override
    public int generate(int bound){
        return bound-1;
    }

}
