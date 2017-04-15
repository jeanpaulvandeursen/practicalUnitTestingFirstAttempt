package org.vandeursen.practicalunittesting;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jp on 26/02/2017.
 */
class MoneyTest {

    // Single static test
    @Test
    public void constructorShouldSetAmountAndCurrency() {

        Money money = new Money(10, "USD");
        assertEquals (10, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    public void constructorShouldThrowIAEForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(-5,"USD");
        });
    }

    // Multiple dynamic tests
    // The long way round:
    @TestFactory
    public Collection<DynamicTest> multipleAmountTests() {
        List<Integer> amounts = new ArrayList<>(Arrays.asList(15,20,25,30));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < amounts.size(); i++) {
            int amount = amounts.get(i);
            Money money = new Money(amount, "USD");
            Executable exec = () -> assertEquals(amount, money.getAmount());
            String TestName = "Test money amount " + amount;
            DynamicTest dTest = DynamicTest.dynamicTest(TestName,exec);
            dynamicTests.add(dTest);
        }
        return dynamicTests;
    }


    // Multiple dynamic tests
    // The shortcut or Java 8 method:
    @TestFactory
    public Stream<DynamicTest> multipleAmountTestsStream() {
        List<Integer> amounts = new ArrayList<>(Arrays.asList(15,20,25,30));

        return amounts.stream().map(amount -> DynamicTest.dynamicTest("Test money amount " +
                amount, () -> {
            Money money = new Money(amount, "USD");
            assertEquals((int)amount,money.getAmount());
        }));
    }

}