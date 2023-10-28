package edu.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw4.Animal.Sex.*;
import static edu.hw4.Animal.Type.*;

public class TasksTest {

    private static final Animal CAT_MURKA = new Animal("Murka", CAT, F, 4, 50, 6, true);
    private static final Animal CAT_TOSHIBO = new Animal("Toshibo", CAT, M, 3, 30, 4, true);
    private static final Animal DOG_JEEMBO = new Animal("Jeembo", DOG, M, 7, 110, 15, true);
    private static final Animal DOG_BOBIK = new Animal("Bobik", DOG, M, 5, 105, 20, true);
    private static final Animal DOG_SPIKE = new Animal("Spike", DOG, M, 10, 130, 17, false);
    private static final Animal BIRD_MIKE = new Animal("Mike", BIRD, M, 2, 15, 2, false);
    private static final Animal SPIDER_JOHN = new Animal("John", SPIDER, M, 1, 2, 2, true);
    private static final Animal FISH_LEILA = new Animal("Leila", FISH, F, 2, 4, 5, false);
    private static final List<Animal> ANIMALS = new ArrayList<>();

    @BeforeAll
    public static void initList() {
        Collections.addAll(
            ANIMALS,
            BIRD_MIKE,
            CAT_MURKA,
            CAT_TOSHIBO,
            DOG_JEEMBO,
            DOG_BOBIK,
            DOG_SPIKE,
            SPIDER_JOHN,
            FISH_LEILA
        );
    }

    @Test
    void shouldReturnSortedByHeightList() {
        List<Animal> sortedAnimalsList = Tasks.task1(ANIMALS);
        assertThat(sortedAnimalsList)
            .containsExactly(
                SPIDER_JOHN,
                FISH_LEILA,
                BIRD_MIKE,
                CAT_TOSHIBO,
                CAT_MURKA,
                DOG_BOBIK,
                DOG_JEEMBO,
                DOG_SPIKE
            );
    }

    @Test
    void shouldReturnFirstKAnimalsSortedByWeight() {
        int k = 3;

        var sortedAnimalsList = Tasks.task2(ANIMALS, k);

        assertThat(sortedAnimalsList)
            .containsExactly(DOG_BOBIK, DOG_SPIKE, DOG_JEEMBO);
    }

    @Test
    void shouldReturnAnimalsTypeCountInTheList() {
        var animalsCountMap = Tasks.task3(ANIMALS);

        assertThat(animalsCountMap)
            .containsExactlyInAnyOrderEntriesOf(Map.of(
                CAT, 2,
                DOG, 3,
                BIRD, 1,
                FISH, 1,
                SPIDER, 1
            ));
    }

    @Test
    void shouldReturnAnimalWithLongestName() {
        Animal.Type longestNameType = Tasks.task4(ANIMALS);

        assertThat(longestNameType)
            .isEqualTo(CAT);
    }

    @Test
    void shouldReturnMostOccurringSex() {
        Animal.Sex mostOccurringSex = Tasks.task5(ANIMALS);

        assertThat(mostOccurringSex)
            .isEqualTo(M);
    }

    @Test
    void shouldReturnHeaviestAnimalOfEachType() {
        Map<Animal.Type, Animal> HeaviestAnimalsList = Tasks.task6(ANIMALS);

        assertThat(HeaviestAnimalsList)
            .containsExactlyInAnyOrderEntriesOf(Map.of(
                DOG, DOG_BOBIK,
                CAT, CAT_MURKA,
                BIRD, BIRD_MIKE,
                FISH, FISH_LEILA,
                SPIDER, SPIDER_JOHN
            ));
    }

    @Test
    void shouldReturnTheOldestAnimalNumberK() {
        int k = 3;

        Animal theOldestAnimalNumberK = Tasks.task7(ANIMALS, k);

        assertThat(theOldestAnimalNumberK)
            .isEqualTo(DOG_BOBIK);
    }

    @Test
    void shouldReturnHeaviestAnimalWithHeightBelowK() {
        int k = 70;

        Optional<Animal> theOldestAnimalNumberK = Tasks.task8(ANIMALS, k);

        assertThat(theOldestAnimalNumberK.orElse(null))
            .isEqualTo(CAT_MURKA);
    }

    @Test
    void shouldReturnSumPawOfAllAnimals() {
        Integer pawsCount = Tasks.task9(ANIMALS);

        assertThat(pawsCount)
            .isEqualTo(30);
    }

    @Test
    void shouldReturnAnimalsWithAgeDifferentFromPawsCount() {
        List<Animal> animalsWithAgeDifferentFromPawsCount = Tasks.task10(ANIMALS);

        assertThat(animalsWithAgeDifferentFromPawsCount)
            .containsExactlyInAnyOrder(
                CAT_TOSHIBO,
                DOG_JEEMBO,
                DOG_BOBIK,
                DOG_SPIKE,
                SPIDER_JOHN,
                FISH_LEILA
            );
    }

    @Test
    void shouldReturAnimalsThatCanBiteAndWhoseHeightExceedsOneHundredCm() {
        List<Animal> animalsThatCanBiteAndWhoseHeightExceedsOneHundredCm = Tasks.task11(ANIMALS);

        assertThat(animalsThatCanBiteAndWhoseHeightExceedsOneHundredCm)
            .containsExactlyInAnyOrder(DOG_JEEMBO, DOG_BOBIK);
    }

    @Test
    void shouldReturnAnimalsCountWhoseWeightExceedsTheirHeight() {
        int animalsCountWhoseWeightExceedsTheirHeight = Tasks.task12(ANIMALS);

        assertThat(animalsCountWhoseWeightExceedsTheirHeight)
            .isEqualTo(1);
    }

    @Test
    void shouldReturnAnimalsWhoseNameExceedTwoWords() {
        List<Animal> newAnimals = new ArrayList<>(ANIMALS);
        Animal spiderSirJo = new Animal("Sir Jo", SPIDER, M, 2, 1, 2, true);
        newAnimals.add(spiderSirJo);

        List<Animal> animalsWithTwoWordName = Tasks.task13(newAnimals);

        assertThat(animalsWithTwoWordName)
            .containsExactlyInAnyOrder(spiderSirJo);
    }

    @Test
    void shouldReturnTrueIfListContainsDogWhoseHeightExceedK() {
        int k = 129;

        var isContainsDogWhoseHeightExceedK = Tasks.task14(ANIMALS, k);

        assertThat(isContainsDogWhoseHeightExceedK)
            .isTrue();
    }

    @Test
    void shouldReturnSumWeightAnimalsWhoseWeightExceedKAndBelowL() {
        int k = 4;
        int l = 10;

        Integer sumWeight = Tasks.task15(ANIMALS, k, l);

        assertThat(sumWeight)
            .isEqualTo(11);
    }

    @Test
    void shouldReturnAnimalSortedByTypeThenSexThenName() {
        List<Animal> newAnimals = new ArrayList<>(ANIMALS);
        Animal spiderSirJo = new Animal("Sir Jo", SPIDER, M, 1, 2, 2, true);
        newAnimals.add(spiderSirJo);

        List<Animal> sortedAnimals = Tasks.task16(newAnimals);

        assertThat(sortedAnimals)
            .containsExactly(
                CAT_TOSHIBO,
                CAT_MURKA,
                DOG_BOBIK,
                DOG_JEEMBO,
                DOG_SPIKE,
                BIRD_MIKE,
                FISH_LEILA,
                SPIDER_JOHN,
                spiderSirJo
            );
    }
}
