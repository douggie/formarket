package net.commerce.zocalo.scoringrules;

import net.commerce.zocalo.claim.Position;
import net.commerce.zocalo.currency.Probability;
import net.commerce.zocalo.currency.Quantity;
import java.util.Map;

public abstract class ScoringRule {
    
    protected double beta;
    protected int numOutcomes;
    
    protected abstract void initBeta(Quantity endowment, Quantity maxPrice);
    protected Quantity getBeta(){
        return new Quantity(beta);
    }
    
    public abstract Quantity incrC(Position position, Probability curP, Probability targetP, Map<Position, Quantity> stocks);
    public abstract Quantity baseC(Position position, Probability curP, Probability targetP, Map<Position, Quantity> stocks);
    public abstract Quantity totalC(Position position, Probability curP, Probability targetP, Map<Position, Quantity> stocks);
    
    public abstract Probability newPFromIncrC(Position position, Quantity limit, Probability curP, Map<Position, Quantity> stocks);
    public abstract Probability newPFromBaseC(Position position, Quantity limit, Probability curP, Map<Position, Quantity> stocks);
    public abstract Probability newPFromTotalC(Position position, Quantity limit, Probability curP, Map<Position, Quantity> newParam);

}
