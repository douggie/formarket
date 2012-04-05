package net.commerce.zocalo.scoringrules;

import java.util.HashMap;
import java.util.Map;

import net.commerce.zocalo.claim.Position;
import net.commerce.zocalo.currency.Probability;
import net.commerce.zocalo.currency.Quantity;

public class Spherical extends ScoringRule {
    private final static double ERROR = 0.0005;
    
    public static Quantity incrC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks, int numOutcomes) {
        return totalC(position, curP, targetP, stocks, numOutcomes).minus(baseC(position, curP, targetP, stocks, numOutcomes));
    }
    
    public static initBeta()

    public static Quantity baseC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks, int numOutcomes) {
        Quantity currentCost = getCostValue(position, curP, beta, numOutcomes, stocks);
        Quantity targetCost = getCostValue(position, targetP, beta, numOutcomes, stocks);
        return targetCost.minus(currentCost);
    }

    public static Quantity totalC(Position position, Probability curP,
            Probability targetP, Map<Position, Quantity> stocks, int numOutcomes) {
        Quantity currentStock = getStockFromProbability(position, curP, beta, numOutcomes, stocks);
        Quantity targetStock = getStockFromProbability(position, targetP, beta, numOutcomes, stocks);
        return targetStock.minus(currentStock);
    }

    public static Probability newPFromIncrC(Position position, Quantity limitQuant,
            Probability curP, Map<Position, Quantity> stocks, int numOutcomes) {
        Map<Position, Quantity> newq = new HashMap<Position, Quantity>(stocks);
        Map<Position, Quantity> newqa = new HashMap<Position, Quantity>(stocks);
        double limit = limitQuant.asValue().doubleValue();
        
        while(true){       
            double newqStock = newq.get(position).asValue().doubleValue();
            double newqaStock = newqa.get(position).asValue().doubleValue();
            double qStock = stocks.get(position).asValue().doubleValue();
            if( Math.abs(newqStock - qStock - C(newq, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit) < ERROR)
                break;
            else if ( Math.abs(newqaStock - qStock - C(newqa, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit) < ERROR){
                newq = newqa;
                break;
            }
            else if((newqStock - qStock - C(newq, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit)*limit > 0)
                break;
            else if((newqaStock - qStock - C(newqa, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit)*limit > 0){
                newq = newqa;
                break;
            }
            
            newq.put(position, newq.get(position).times(Quantity.TWO));
            newqa.put(position, newqa.get(position).div(Quantity.TWO));
        }
        
        newqa = new HashMap<Position, Quantity>(stocks);        
        Map<Position, Quantity> temp = new HashMap<Position, Quantity>(stocks);
        
        while(true){
            double newqStock = newq.get(position).asValue().doubleValue();
            double tempStock = temp.get(position).asValue().doubleValue();
            double qStock = stocks.get(position).asValue().doubleValue();
            if(Math.abs(newqStock - qStock - C(newq, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit) < ERROR)
                break;
            temp.put(position, newq.get(position).plus(newqa.get(position)).div(2));
            if( (newqStock - qStock - C(newq, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit)*(tempStock - qStock - C(temp, beta, numOutcomes) + C(stocks, beta, numOutcomes) - limit) > 0)
                newq.put(position, temp.get(position));
            else
                newqa.put(position, temp.get(position));
        }
        
        return findNewP(position, newq, beta, numOutcomes);
    }

    private static Probability findNewP(Position position, Map<Position, Quantity> newq, double beta, int numOutcomes) {
        double sumq1 = getSumStocks(newq,1);
        double sumq2 = getSumStocks(newq,2);
        double n = numOutcomes;
        double b = beta;
        double qi = newq.get(position).asValue().doubleValue();
        
        return new Probability((1/n + (sumq1 - n*qi)/(n*Math.sqrt(sumq1*sumq1 + b*b*n*n - n*sumq2))));
    }

    public static Probability newPFromBaseC(Position position, Quantity limitQuant,
            Probability curP, Map<Position, Quantity> stocks, int numOutcomes) {
        Map<Position, Quantity> newq = new HashMap<Position, Quantity>(stocks);
        Map<Position, Quantity> newqa = new HashMap<Position, Quantity>(stocks);
        double limit = limitQuant.asValue().doubleValue();
        
        while(true){       
            if( Math.abs(C(newq, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit) < ERROR)
                break;
            else if ( Math.abs(C(newqa, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit) < ERROR){
                newq = newqa;
                break;
            }
            else if((C(newq, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit)*limit > 0)
                break;
            else if((C(newqa, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit)*limit > 0){
                newq = newqa;
                break;
            }
            
            newq.put(position, newq.get(position).times(Quantity.TWO));
            newqa.put(position, newqa.get(position).div(Quantity.TWO));
        }
        
        newqa = new HashMap<Position, Quantity>(stocks);        
        Map<Position, Quantity> temp = new HashMap<Position, Quantity>(stocks);
        
        while(true){
            if(Math.abs(C(newq, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit) < ERROR)
                break;
            temp.put(position, newq.get(position).plus(newqa.get(position)).div(2));
            if( (C(newq, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit)*(C(temp, beta, numOutcomes) - C(stocks, beta, numOutcomes) - limit) > 0)
                newq.put(position, temp.get(position));
            else
                newqa.put(position, temp.get(position));
        }
        
        return findNewP(position, newq, beta, numOutcomes);
    }

    public static Probability newPFromTotalC(Position position, Quantity limit,
            Probability curP, Map<Position, Quantity> stocks, int numOutcomes) {
        Map<Position, Quantity> newq = new HashMap<Position, Quantity>(stocks);
        
        newq.put(position, stocks.get(position).plus(limit));
        
        return findNewP(position, newq, beta, numOutcomes);
    }


    public static double getSumStocks(int power, Map<Position, Quantity> stocks) {
        return getSumStocks(stocks, power);
    }
    
    public static double getSumStocks(Map<Position, Quantity> stockMap, int power) {
        double sum = 0;
        for(Quantity q : stockMap.values()) {
            double term = Math.pow(q.asValue().doubleValue(), power);
            sum += term;
        }
        return sum;
    }
    
    public static double getConstantSumStocks(Position position, int power, Map<Position, Quantity> stocks) {
        double sum = getSumStocks(power, stocks);
        double value = Math.pow(stocks.get(position).asValue().doubleValue(), power);
        return sum - value;
    }
    
    public static Quantity getCostValue(Position position, Probability p, double beta, int numOutcomes, Map<Position, Quantity> stocks) {
        Quantity sum = new Quantity(beta);
        double q = getStockFromProbability(position, p, beta, numOutcomes, null).asValue().doubleValue();
        double const1 = getConstantSumStocks(position, 1, stocks);
        double prob = p.asValue().doubleValue();
        double temp = ((q + const1) * prob - q) / (numOutcomes * prob - 1);
        return sum.plus(new Quantity(temp));
    }
    
    public static Quantity getStockFromProbability(Position position, Probability p, double beta, int numOutcomes, Map<Position, Quantity> stocks) {
        double temp = Math.pow(numOutcomes * p.asValue().doubleValue() - 1, 2);
        double const1 = getConstantSumStocks(position, 1, stocks);
        double const2 = getConstantSumStocks(position, 2, stocks);
        double A = (numOutcomes - 1) * (temp + numOutcomes - 1);
        double B = const1 * (numOutcomes - 1 + temp);
        double const1sq = const1 * const1;
        double C = const1sq - temp * (const1sq + beta*beta*numOutcomes*numOutcomes - numOutcomes * const2);
        double solution = (B + Math.sqrt(B*B - A*C)) / A;
        return new Quantity(solution);
    }
    
    private static double C(Map<Position, Quantity> q, double beta, int numOutcomes){
        double sumq1 = getSumStocks(q,1);
        double sumq2 = getSumStocks(q,2);
        double n = numOutcomes;
        double b = beta;
        
        return (sumq1/n + b + Math.sqrt(sumq1*sumq1 + b*b*n*n - n*sumq2)/n);
    }
    
}
