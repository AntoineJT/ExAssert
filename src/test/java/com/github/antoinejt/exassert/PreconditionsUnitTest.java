package com.github.antoinejt.exassert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PreconditionsUnitTest {

  // TODO test for positive/negative double/float

  @Nested
  class GivenStrictlyPositiveInteger {

    private int number;

    @BeforeEach
    void setup() {
      this.number = Faker.instance().number().positive();
    }

    @Nested
    class WhenCallingRequiresUnsigned {

      private Throwable thrown;

      @BeforeEach
      void setup() {
        this.thrown =
            catchThrowable(
                () -> Preconditions.requiresUnsigned(GivenStrictlyPositiveInteger.this.number));
      }

      @Test
      void thenItShouldNotThrowAssertionFailedException() {
        assertThat(this.thrown).isNull();
      }
    }

    @Nested
    class WhenCallingRequiresStrictlyPositive {

      private Throwable thrown;

      @BeforeEach
      void setup() {
        this.thrown =
            catchThrowable(
                () ->
                    Preconditions.requiresStrictlyPositive(
                        GivenStrictlyPositiveInteger.this.number));
      }

      @Test
      void thenItShouldNotThrowAssertionFailedException() {
        assertThat(this.thrown).isNull();
      }
    }
  }

  @Nested
  class GivenNegativeInteger {

    private int number;

    @BeforeEach
    void setup() {
      this.number = Faker.instance().number().negative();
    }

    @Nested
    class WhenCallingRequiresUnsigned {

      private Throwable thrown;

      @BeforeEach
      void setup() {
        this.thrown =
            catchThrowable(() -> Preconditions.requiresUnsigned(GivenNegativeInteger.this.number));
      }

      @Test
      void thenItShouldThrowAssertionFailedException() {
        assertThat(this.thrown).isInstanceOf(AssertionFailedException.class);
      }
    }

    @Nested
    class WhenCallingRequiresStrictlyPositive {

      private Throwable thrown;

      @BeforeEach
      void setup() {
        this.thrown =
            catchThrowable(
                () -> Preconditions.requiresStrictlyPositive(GivenNegativeInteger.this.number));
      }

      @Test
      void thenItShouldThrowAssertionFailedException() {
        assertThat(this.thrown).isInstanceOf(AssertionFailedException.class);
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
}
