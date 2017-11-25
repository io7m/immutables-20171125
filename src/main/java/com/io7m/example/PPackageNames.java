package com.io7m.example;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The type of package names.
 */

public final class PPackageNames
{
  /**
   * A pattern describing valid names.
   */

  public static final Pattern PATTERN =
    Pattern.compile(
      "([\\p{Ll}][\\p{Ll}\\p{Digit}_]*)(\\.[\\p{Ll}][\\p{Ll}\\p{Digit}_]*)*",
      Pattern.UNICODE_CHARACTER_CLASS);

  private PPackageNames()
  {
    throw new UnreachableCodeException();
  }

  /**
   * @param name The {@code name}
   *
   * @return {@code true} iff {@code name} is valid
   */

  public static boolean isValid(
    final String name)
  {
    Objects.requireNonNull(name, "Name");
    return name.length() <= 128 && PATTERN.matcher(name).matches();
  }
}
