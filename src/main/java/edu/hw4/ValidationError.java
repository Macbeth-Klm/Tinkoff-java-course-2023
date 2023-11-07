package edu.hw4;

public final class ValidationError {

    private final String errorMessage;
    private final String animalAttribute;

    private ValidationError(String errorMessage, String animalAttribute) {
        this.errorMessage = errorMessage;
        this.animalAttribute = animalAttribute;
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidName(Animal animal) {
        String name = "name";
        if (animal.name().length() > 50) {
            return new ValidationError("The name is too long!", name);
        } else if (animal.name().split(" ").length > 5) {
            return new ValidationError("The name contains an unacceptable number of words!", name);
        } else {
            return null;
        }
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidAge(Animal animal) {
        String age = "age";
        var isInvalidAge = false;
        if (animal.age() < 0) {
            return new ValidationError("Age value is negative!", age);
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
        return (isInvalidAge) ? new ValidationError("Age value is too high!", age) : null;
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidHeight(Animal animal) {
        String height = "height";
        var isInvalidHeight = false;
        if (animal.height() < 0) {
            return new ValidationError("Height value is negative!", height);
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
        return (isInvalidHeight) ? new ValidationError("Height value is too high!", height) : null;
    }

    @SuppressWarnings("MagicNumber")
    public static ValidationError invalidWeight(Animal animal) {
        String weight = "weight";
        var isInvalidWeight = false;
        if (animal.weight() < 0) {
            return new ValidationError("Weight value is negative!", weight);
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
        return (isInvalidWeight) ? new ValidationError("Weight value is too high!", weight) : null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getAnimalAttribute() {
        return animalAttribute;
    }
}
