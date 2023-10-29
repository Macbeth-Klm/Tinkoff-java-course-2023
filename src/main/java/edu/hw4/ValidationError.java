package edu.hw4;

public final class ValidationError {

    private final String error;

    private ValidationError(String error) {
        this.error = error;
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidName(Animal animal) {
        if (animal.name().length() > 50) {
            return new ValidationError("The name is too long!");
        } else if (animal.name().split(" ").length > 5) {
            return new ValidationError("The name contains an unacceptable number of words!");
        } else {
            return null;
        }
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidAge(Animal animal) {
        var isInvalidAge = false;
        if (animal.age() < 0) {
            return new ValidationError("Age value is negative!");
        }
        switch (animal.type()) {
            case CAT, DOG, BIRD:
                if (animal.age() > 50) {
                    isInvalidAge = true;
                }
                break;
            case FISH:
                if (animal.age() > 150) {
                    isInvalidAge = true;
                }
                break;
            default:
                if (animal.age() > 10) {
                    isInvalidAge = true;
                }
        }
        return (isInvalidAge) ? new ValidationError("Age value is too high!") : null;
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidHeight(Animal animal) {
        var isInvalidHeight = false;
        if (animal.height() < 0) {
            return new ValidationError("Height value is negative!");
        }
        switch (animal.type()) {
            case CAT, FISH:
                if (animal.height() > 50) {
                    isInvalidHeight = true;
                }
                break;
            case DOG:
                if (animal.height() > 150) {
                    isInvalidHeight = true;
                }
                break;
            case BIRD:
                if (animal.height() > 300) {
                    isInvalidHeight = true;
                }
                break;
            default:
                if (animal.height() > 30) {
                    isInvalidHeight = true;
                }
        }
        return (isInvalidHeight) ? new ValidationError("Height value is too high!") : null;
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidWeight(Animal animal) {
        var isInvalidWeight = false;
        if (animal.weight() < 0) {
            return new ValidationError("Weight value is negative!");
        }
        switch (animal.type()) {
            case CAT:
                if (animal.weight() > 30) {
                    isInvalidWeight = true;
                }
                break;
            case DOG, BIRD:
                if (animal.height() > 150) {
                    isInvalidWeight = true;
                }
                break;
            case FISH:
                if (animal.weight() > 100) {
                    isInvalidWeight = true;
                }
                break;
            default:
                if (animal.height() > 5) {
                    isInvalidWeight = true;
                }
        }
        return (isInvalidWeight) ? new ValidationError("Weight value is too high!") : null;
    }

    public String getError() {
        return error;
    }
}
