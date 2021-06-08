package BusinessLayer.NumericGenerators;

public class DeterministicGenerator extends NumericGenerator{

    public DeterministicGenerator(){

    }

    public int generate(int bound){
        return bound-1;
    }

}
