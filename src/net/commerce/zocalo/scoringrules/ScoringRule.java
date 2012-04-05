package net.commerce.zocalo.scoringrules;

import net.commerce.zocalo.claim.Position;
import net.commerce.zocalo.currency.Probability;
import net.commerce.zocalo.currency.Quantity;
import java.util.Map;

public abstract class ScoringRule {
    
    public abstract static Quantity incrC(Position position, Probability curP, Probability targetP, Map<Position, Quantity> stocks, int numOutcomes);
    public abstract static Quantity baseC(Position position, Probability curP, Probability targetP, Map<Position, Quantity> stocks, int numOutcomes);
    public abstract static Quantity totalC(Position position, Probability curP, Probability targetP, Map<Position, Quantity> stocks, int numOutcomes);
    
    public abstract static Probability newPFromIncrC(Position position, Quantity limit, Probability curP, Map<Position, Quantity> stocks, int numOutcomes);
    public abstract static Probability newPFromBaseC(Position position, Quantity limit, Probability curP, Map<Position, Quantity> stocks, int numOutcomes);
    public abstract static Probability newPFromTotalC(Position position, Quantity limit, Probability curP, Map<Position, Quantity> newParam, int numOutcomes);

}
