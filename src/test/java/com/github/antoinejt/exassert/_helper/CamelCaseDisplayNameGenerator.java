package com.github.antoinejt.exassert._helper;

import java.lang.reflect.Method;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayNameGenerator;

public class CamelCaseDisplayNameGenerator implements DisplayNameGenerator {

  @Override
  public String generateDisplayNameForClass(final Class<?> testClass) {
    return this.toCamelCase(testClass.getSimpleName());
  }

  @Override
  public String generateDisplayNameForNestedClass(final Class<?> nestedClass) {
    return this.toCamelCase(nestedClass.getSimpleName());
  }

  @Override
  public String generateDisplayNameForMethod(final Class<?> testClass, final Method testMethod) {
    return this.toCamelCase(testMethod.getName());
  }

  private String toCamelCase(@NonNull final String camelCaseName) {
    if (camelCaseName.isEmpty()) {
      return "";
    }

    final StringBuilder stringBuilder = new StringBuilder();

    final char upperFirstChar = Character.toUpperCase(camelCaseName.charAt(0));
    stringBuilder.append(upperFirstChar);

    for (int i = 1; i < camelCaseName.length(); ++i) {
      final char c = camelCaseName.charAt(i);
      if (Character.isUpperCase(c)) {
        stringBuilder.append(' ');
      }
      stringBuilder.append(c);
    }

    return stringBuilder.toString();
  }
}
