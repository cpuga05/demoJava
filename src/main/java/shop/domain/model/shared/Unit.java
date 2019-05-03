package shop.domain.model.shared;

import com.sun.javaws.exceptions.InvalidArgumentException;

public final class Unit {
    private int amount;

    public Unit(int amount) throws InvalidArgumentException {
        this.ensureValidAmount(amount);

        this.amount = amount;
    }

    private void ensureValidAmount(int amount) throws InvalidArgumentException {
        if (amount < 0) {
            throw new InvalidArgumentException(new String[]{"Te amount " + amount + "is not valid."});
        }
    }

    public boolean equals(Unit unit) {
        return this.amount() == unit.amount();
    }

    public boolean isSmallerThan(Unit unit) {
        return this.amount() < unit.amount();
    }

    public boolean isBiggerThan(Unit unit) {
        return this.amount() > unit.amount();
    }

    public boolean isSmallerOrEqualThan(Unit unit) {
        return this.amount() <= unit.amount();
    }

    public boolean isBiggerOrEqualThan(Unit unit) {
        return this.amount() >= unit.amount();
    }

    public Unit add(Unit unit) throws InvalidArgumentException {
        return new Unit(this.amount() + unit.amount());
    }

    public int amount() {
        return this.amount;
    }
}
