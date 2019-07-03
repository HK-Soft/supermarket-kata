package kata.supermarket.Utils;

import kata.supermarket.core.Exception.ConversionRuleNotFound;
import kata.supermarket.domain.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitUtils {

    private static List<ConversionRule> CONVERSION_RULES = new ArrayList<>();

    static {
        Unit UNIT_OUNCE = new Unit();
        UNIT_OUNCE.setDescription("ounce");
        Unit UNIT_POUND = new Unit();
        UNIT_POUND.setDescription("pound");
        ConversionRule POUND_TO_OUNCE_RULE = new ConversionRule(UNIT_POUND, UNIT_OUNCE, 16);
        CONVERSION_RULES.add(POUND_TO_OUNCE_RULE);
    }

    public static double convert(double value, Unit from, Unit to) throws ConversionRuleNotFound {
        if (from.equals(to)) return value;
        for (ConversionRule rule : CONVERSION_RULES) {
            if (rule.getFrom().equals(from) && rule.getTo().equals(to)) {
                return value * rule.getRate();
            } else if (rule.getFrom().equals(to) && rule.getTo().equals(from)) {
                return value / rule.getRate();
            }
        }
        throw new ConversionRuleNotFound(from, to);
    }
}

class ConversionRule {

    private Unit from;
    private Unit to;
    private double rate;

    public ConversionRule(Unit from, Unit to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public Unit getFrom() {
        return from;
    }

    public void setFrom(Unit from) {
        this.from = from;
    }

    public Unit getTo() {
        return to;
    }

    public void setTo(Unit to) {
        this.to = to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


}
