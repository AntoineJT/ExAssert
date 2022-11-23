package com.github.antoinejt.exassert._helper;

import java.lang.reflect.Method;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGenerator;

public class CamelCaseDisplayNameGenerator implements DisplayNameGenerator {

  @Override
  public String generateDisplayNameForClass(final Class<?> testClass) {
    return this.camelCase(testClass.getSimpleName());
  }

  @Override
  public String generateDisplayNameForNestedClass(final Class<?> nestedClass) {
    return this.camelCase(nestedClass.getSimpleName());
  }

  @Override
  public String generateDisplayNameForMethod(final Class<?> testClass, final Method testMethod) {
    return this.camelCase(testMethod.getName());
  }

  private String camelCase(final String camelCaseName) {
    final String result =
        camelCaseName
            .chars()
            .mapToObj((c) -> (char) c)
            .map((c) -> Character.isUpperCase(c) ? " " + c : String.valueOf(c))
            .collect(Collectors.joining(""));

    return this.capitalize(result);
  }

  private String capitalize(final String value) {
    return Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }
}
