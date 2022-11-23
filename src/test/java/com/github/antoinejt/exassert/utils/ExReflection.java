package com.github.antoinejt.exassert.utils;

public class ExReflection {
  private ExReflection() {
    // hides the public default ctor
  }

  public static Class<?> getPrivateStaticClass(Class<?> parent, String innerClassName) {
    for (Class<?> clazz : parent.getDeclaredClasses()) {
      if (clazz.getSimpleName().equals(innerClassName)) return clazz;
    }
    return null;
  }
}
