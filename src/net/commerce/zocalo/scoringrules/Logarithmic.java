package net.commerce.zocalo.scoringrules;

import java.util.Map;

import net.commerce.zocalo.claim.Position;
import net.commerce.zocalo.currency.Probability;
import net.commerce.zocalo.currency.Quantity;

public class Logarithmic extends ScoringRule {

    public static Quantity incrC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks, int numOutcomes) {
        return getBeta().times(targetP.div(curP).absLog());
    }

    public static Quantity baseC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks, int numOutcomes) {
        Probability currentInverted = curP.inverted();
        Probability targetInverted = targetP.inverted();
        if (targetInverted.isZero() || currentInverted.isZero()) {
            throw new ArithmeticException("probabilities can't be zero or one.");
        }
        return getBeta().times(targetInverted.div(currentInverted).absLog());
    }

    public static Quantity totalC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks, int numOutcomes) {
        Probability curPNot = curP.inverted();
        Probability targetPNot = targetP.inverted();
        return getBeta().times(targetP.times(curPNot).div(curP.times(targetPNot)).absLog());    }

    public static Probability newPFromIncrC(Position position, Quantity limit,
            Probability curP, Map<Position, Quantity> stocks, int numOutcomes) {
        return new Probability(curP.times(limit.div(getBeta()).exp()));    }

    public static Probability newPFromBaseC(Position position, Quantity limit,
            Probability curP, Map<Position, Quantity> stocks, int numOutcomes) {
        Quantity baseC = limit.div(getBeta());
        Probability curPNot = curP.inverted();
        return new Probability(curPNot.div(baseC.exp())).inverted();    }

    public static Probability newPFromTotalC(Position position, Quantity limit,
            Probability curP, Map<Position, Quantity> newParam, int numOutcomes) {
        Quantity expTotalC = limit.div(getBeta()).exp();
        Probability curPNot = curP.inverted();
        return new Probability(curP.div(curP.plus(curPNot.times(expTotalC))));
    }


}
