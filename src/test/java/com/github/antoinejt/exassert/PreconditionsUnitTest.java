package com.github.antoinejt.exassert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.github.antoinejt.exassert._helper.CamelCaseDisplayNameGenerator;
import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.NumberSignException;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(CamelCaseDisplayNameGenerator.class)
class PreconditionsUnitTest {

  @Nested
  class GivenStrictlyPositiveNumber {

    private int positiveInt;
    private float positiveFloat;
    private double positiveDouble;

    @BeforeEach
    void setup() {
      this.positiveInt = PreconditionsUnitTest.this.generatePositiveNumber();
      this.positiveFloat = PreconditionsUnitTest.this.generatePositiveNumber();
      this.positiveDouble = PreconditionsUnitTest.this.generatePositiveNumber();
    }

    @Nested
    class WhenCallingRequiresUnsigned {

      private Throwable thrownInt;
      private Throwable thrownFloat;
      private Throwable thrownDouble;

      @BeforeEach
      void setup() {
        this.thrownInt =
            catchThrowable(
                () -> Preconditions.requiresUnsigned(GivenStrictlyPositiveNumber.this.positiveInt));
        this.thrownFloat =
            catchThrowable(
                () ->
                    Preconditions.requiresUnsigned(GivenStrictlyPositiveNumber.this.positiveFloat));
        this.thrownDouble =
            catchThrowable(
                () ->
                    Preconditions.requiresUnsigned(
                        GivenStrictlyPositiveNumber.this.positiveDouble));
      }

      @Test
      void thenItShouldNotThrowForIntParam() {
        assertThat(this.thrownInt).isNull();
      }

      @Test
      void thenItShouldNotThrowForFloatParam() {
        assertThat(this.thrownFloat).isNull();
      }

      @Test
      void thenItShouldNotThrowForDouble() {
        assertThat(this.thrownDouble).isNull();
      }
    }

    @Nested
    class WhenCallingRequiresStrictlyPositive {

      private Throwable thrownInt;
      private Throwable thrownFloat;
      private Throwable thrownDouble;

      @BeforeEach
      void setup() {
        this.thrownInt =
            catchThrowable(
                () ->
                    Preconditions.requiresStrictlyPositive(
                        GivenStrictlyPositiveNumber.this.positiveInt));
        this.thrownFloat =
            catchThrowable(
                () ->
                    Preconditions.requiresStrictlyPositive(
                        GivenStrictlyPositiveNumber.this.positiveFloat));
        this.thrownDouble =
            catchThrowable(
                () ->
                    Preconditions.requiresStrictlyPositive(
                        GivenStrictlyPositiveNumber.this.positiveDouble));
      }

      @Test
      void thenItShouldNotThrowForIntParam() {
        assertThat(this.thrownInt).isNull();
      }

      @Test
      void thenItShouldNotThrowForFloatParam() {
        assertThat(this.thrownFloat).isNull();
      }

      @Test
      void thenItShouldNotThrowForDoubleParam() {
        assertThat(this.thrownDouble).isNull();
      }
    }
  }

  @Nested
  class GivenNegativeNumber {

    private int negativeInt;
    private float negativeFloat;
    private double negativeDouble;

    @BeforeEach
    void setup() {
      this.negativeInt = PreconditionsUnitTest.this.generateNegativeNumber();
      this.negativeFloat = PreconditionsUnitTest.this.generateNegativeNumber();
      this.negativeDouble = PreconditionsUnitTest.this.generateNegativeNumber();
    }

    @Nested
    class WhenCallingRequiresUnsigned {

      private Throwable thrownInt;
      private Throwable thrownFloat;
      private Throwable thrownDouble;

      @BeforeEach
      void setup() {
        this.thrownInt =
            catchThrowable(
                () -> Preconditions.requiresUnsigned(GivenNegativeNumber.this.negativeInt));
        this.thrownFloat =
            catchThrowable(
                () -> Preconditions.requiresUnsigned(GivenNegativeNumber.this.negativeFloat));
        this.thrownDouble =
            catchThrowable(
                () -> Preconditions.requiresUnsigned(GivenNegativeNumber.this.negativeDouble));
      }

      @Test
      void thenItShouldThrowNumberSignExceptionForIntParam() {
        assertThat(this.thrownInt).isInstanceOf(NumberSignException.class);
      }

      @Test
      void thenItShouldThrowNumberSignExceptionForFloatParam() {
        assertThat(this.thrownFloat).isInstanceOf(NumberSignException.class);
      }

      @Test
      void thenItShouldThrowNumberSignExceptionForDoubleParam() {
        assertThat(this.thrownDouble).isInstanceOf(NumberSignException.class);
      }
    }

    @Nested
    class WhenCallingRequiresStrictlyPositive {

      private Throwable thrownInt;
      private Throwable thrownFloat;
      private Throwable thrownDouble;

      @BeforeEach
      void setup() {
        this.thrownInt =
            catchThrowable(
                () -> Preconditions.requiresStrictlyPositive(GivenNegativeNumber.this.negativeInt));
        this.thrownFloat =
            catchThrowable(
                () ->
                    Preconditions.requiresStrictlyPositive(GivenNegativeNumber.this.negativeFloat));
        this.thrownDouble =
            catchThrowable(
                () ->
                    Preconditions.requiresStrictlyPositive(
                        GivenNegativeNumber.this.negativeDouble));
      }

      @Test
      void thenItShouldThrowAssertionFailedExceptionForIntParam() {
        assertThat(this.thrownInt).isInstanceOf(AssertionFailedException.class);
      }

      @Test
      void thenItShouldThrowAssertionFailedExceptionForFloatParam() {
        assertThat(this.thrownFloat).isInstanceOf(AssertionFailedException.class);
      }

      @Test
      void thenItShouldThrowAssertionFailedExceptionForDoubleParam() {
        assertThat(this.thrownDouble).isInstanceOf(AssertionFailedException.class);
      }
    }
  }

  @Nested
  class GivenZero {

    private final int zeroInt = 0;
    private final float zeroFloat = 0.0f;
    private final double zeroDouble = 0.0d;

    @Nested
    class WhenCallingRequiresUnsigned {

      private Throwable thrownForInt;
      private Throwable thrownForFloat;
      private Throwable thrownForDouble;

      @BeforeEach
      void setup() {
        this.thrownForInt =
            catchThrowable(() -> Preconditions.requiresUnsigned(GivenZero.this.zeroInt));
        this.thrownForFloat =
            catchThrowable(() -> Preconditions.requiresUnsigned(GivenZero.this.zeroFloat));
        this.thrownForDouble =
            catchThrowable(() -> Preconditions.requiresUnsigned(GivenZero.this.zeroDouble));
      }

      @Test
      void thenItShouldNotThrowForIntParam() {
        assertThat(this.thrownForInt).isNull();
      }

      @Test
      void thenItShouldNotThrowForFloatParam() {
        assertThat(this.thrownForFloat).isNull();
      }

      @Test
      void thenItShouldNotThrowForDoubleParam() {
        assertThat(this.thrownForDouble).isNull();
      }
    }

    @Nested
    class WhenCallingRequiresStrictlyPositive {

      private Throwable thrownForInt;
      private Throwable thrownForFloat;
      private Throwable thrownForDouble;

      @BeforeEach
      void setup() {
        this.thrownForInt =
            catchThrowable(() -> Preconditions.requiresStrictlyPositive(GivenZero.this.zeroInt));
        this.thrownForFloat =
            catchThrowable(() -> Preconditions.requiresStrictlyPositive(GivenZero.this.zeroFloat));
        this.thrownForDouble =
            catchThrowable(() -> Preconditions.requiresStrictlyPositive(GivenZero.this.zeroDouble));
      }

      @Test
      void thenItShouldThrowAssertionFailedExceptionForIntParam() {
        assertThat(this.thrownForInt).isInstanceOf(AssertionFailedException.class);
      }

      @Test
      void thenItShouldThrowAssertionFailedExceptionForFloatParam() {
        assertThat(this.thrownForFloat).isInstanceOf(AssertionFailedException.class);
      }

      @Test
      void thenItShouldThrowAssertionFailedExceptionForDoubleParam() {
        assertThat(this.thrownForDouble).isInstanceOf(AssertionFailedException.class);
      }
    }
  }

  private int generatePositiveNumber() {
    return Faker.instance().number().positive();
  }

  private int generateNegativeNumber() {
    return Faker.instance().number().negative();
  }
}
