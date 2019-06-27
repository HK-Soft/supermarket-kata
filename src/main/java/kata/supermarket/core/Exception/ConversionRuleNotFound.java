package kata.supermarket.core.Exception;

import kata.supermarket.core.Unit;

public class ConversionRuleNotFound extends Exception {

    public ConversionRuleNotFound(Unit from, Unit to) {
        super("Can't convert from " + from.getDescription() + " to " + to.getDescription());
    }
}
