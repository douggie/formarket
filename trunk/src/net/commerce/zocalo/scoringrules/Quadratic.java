package net.commerce.zocalo.scoringrules;

import java.util.Map;

import net.commerce.zocalo.claim.Position;
import net.commerce.zocalo.currency.Probability;
import net.commerce.zocalo.currency.Quantity;

public class Quadratic extends ScoringRule {

    public Quadratic(Quantity endowment, Quantity maxPrice, int num) {
        this.numOutcomes = num;
        initBeta(endowment, maxPrice);
    }
    
    protected void initBeta(Quantity endowment, Quantity maxPrice) {
        this.beta = endowment.times(new Quantity(numOutcomes)).div(new Quantity(numOutcomes-1)).div(maxPrice).asValue().doubleValue();
    }

    public Quantity incrC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks) {
        Quantity p=curP.asQuantity();
        Quantity pp=targetP.asQuantity();
        return (pp.minus(p)).times(pp.plus(p).minus(new Quantity(2.0))).times(getBeta().times(outcomeCount())).div(outcomeCount().minus(new Quantity(1))).abs();
    }

    public Quantity baseC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks) {
        Probability currentInverted = curP.inverted();
        Probability targetInverted = targetP.inverted();
        if (targetInverted.isZero() || currentInverted.isZero()) {
            throw new ArithmeticException("probabilities can't be zero or one.");
        }
        return getBeta().times(outcomeCount()).div(outcomeCount().minus(new Quantity(1))).times(targetP.times(targetP).minus(curP.times(curP))).abs();
    }

    private Quantity outcomeCount() {
        // TODO Auto-generated method stub
        return new Quantity(numOutcomes);
    }

    public Quantity totalC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks) {
        Quantity p=curP.asQuantity();
        Quantity pp=targetP.asQuantity();
        return getBeta().times(outcomeCount().times(new Quantity(2)).div(outcomeCount().minus(new Quantity(1)))).times(pp.minus(p)).abs();
    }

    public Probability newPFromIncrC(Position position, Quantity limit,
            Probability curP,  Map<Position, Quantity> stocks) {
        return new Probability((outcomeCount().minus(new Quantity(1)).times(limit).div(getBeta().times(outcomeCount().times(new Quantity(2.0))))));
    }

    public Probability newPFromBaseC(Position position, Quantity limit,
            Probability curP,  Map<Position, Quantity> stocks) {
        Quantity baseC = limit.div(getBeta());
        Quantity frac=(outcomeCount().minus(Quantity.ONE)).div(outcomeCount());
        
        return new Probability((baseC.times(frac).plus(curP.times(curP))).sqrt());
    }

    public Probability newPFromTotalC(Position position, Quantity limit,
            Probability curP,  Map<Position, Quantity> stocks) {
        Quantity q=limit.times(outcomeCount().minus(new Quantity(1))).div(getBeta().times(new Quantity(2)).times(outcomeCount()));
        return new Probability(curP.minus(q));
    }


}
