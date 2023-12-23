package edu.hw10.task1;

import edu.hw10.task1.Classes.MyClass;
import edu.hw10.task1.Classes.MyRecord;
import edu.hw10.task1.Classes.MyRecordWithAnnotations;
import edu.hw8.task3.PasswordMiner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {
    private final RandomObjectGenerator rog = new RandomObjectGenerator();

    @Test
    void shouldReturnCorrectInstanceWithConstructor() {
        var myClass = rog.nextObject(MyClass.class);

        if (myClass instanceof MyClass instance) {
            assertTrue(instance.getName() == null && instance.getAge() == 0);
        }
    }

    @Test
    void shouldReturnCorrectInstanceWithFabricMethodWithoutParams() {
        var myClass = rog.nextObject(MyClass.class, "createWithoutParams");

        if (myClass instanceof MyClass instance) {
            assertTrue(instance.getName().equals("Ilia") && instance.getAge() == 21);
        }
    }

    @Test
    void shouldReturnCorrectInstanceWithFabricMethodWithParams() {
        var myClass = rog.nextObject(MyClass.class, "createWithParams");

        if (myClass instanceof MyClass instance) {
            assertTrue(
                instance.getName().isEmpty()
                    && 0 <= instance.getAge()
                    && instance.getAge() < 100
            );
        }
    }

    @Test
    void shouldReturnCorrectRecordInstance() {
        var myRecord = rog.nextObject(MyRecord.class);
        if (myRecord instanceof MyRecord instance) {

            assertTrue(instance.name() == null && instance.age() == 0);
        }
    }

    @Test
    void shouldReturnCorrectRecordInstanceWithAnnotations() {
        var myRecord = rog.nextObject(MyRecordWithAnnotations.class);

        if (myRecord instanceof MyRecordWithAnnotations instance) {
            assertTrue(
                instance.name().isEmpty()
                    && 0 <= instance.age()
                    && instance.age() < 100
                    && instance.weight() >= 80
            );
        }
    }

    @Test
    void shouldThrowExceptionBecauseMethodOrCtorContainsParametersThatCannotBeGenerated() {
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
            var passwordMinerInstance = rog.nextObject(PasswordMiner.class);
        });

        Assertions.assertEquals(
            "java.lang.RuntimeException: Method/C-tor contains parameters that cannot be generated!",
            ex.getMessage()
        );
    }
}
