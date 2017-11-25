package com.io7m.example;

import org.immutables.value.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.Optional;

/**
 * The type of patterns.
 *
 * @param <T> The type of associated data
 */

public interface PPatternType<T> extends PModelElementType<T>
{
  /**
   * @return The kind of pattern
   */

  Kind kind();

  @Override
  Optional<LexicalPosition<Path>> lexical();

  @Override
  T data();

  /**
   * The kind of patterns.
   */

  enum Kind
  {
    /**
     * @see PPatternConstantRealType
     */

    PATTERN_CONSTANT_REAL,

    /**
     * @see PPatternConstantStringType
     */

    PATTERN_CONSTANT_STRING,

    /**
     * @see PPatternConstantIntegerType
     */

    PATTERN_CONSTANT_INTEGER,

    /**
     * @see PPatternConstructorType
     */

    PATTERN_CONSTRUCTOR,

    /**
     * @see PPatternWildcardType
     */

    PATTERN_WILDCARD
  }

  /**
   * An integer constant pattern.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PPatternConstantIntegerType<T> extends PPatternType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.PATTERN_CONSTANT_INTEGER;
    }

    @Override
    @Value.Parameter
    @Value.Auxiliary
    Optional<LexicalPosition<Path>> lexical();

    @Override
    @Value.Parameter
    @Value.Auxiliary
    T data();

    /**
     * @return The constant value
     */

    @Value.Parameter
    BigInteger value();
  }

  /**
   * A real constant pattern.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PPatternConstantRealType<T> extends PPatternType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.PATTERN_CONSTANT_REAL;
    }

    @Override
    @Value.Parameter
    @Value.Auxiliary
    Optional<LexicalPosition<Path>> lexical();

    @Override
    @Value.Parameter
    @Value.Auxiliary
    T data();

    /**
     * @return The constant value
     */

    @Value.Parameter
    BigDecimal value();
  }

  /**
   * A string constant pattern.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PPatternConstantStringType<T> extends PPatternType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.PATTERN_CONSTANT_STRING;
    }

    @Override
    @Value.Parameter
    @Value.Auxiliary
    Optional<LexicalPosition<Path>> lexical();

    @Override
    @Value.Parameter
    @Value.Auxiliary
    T data();

    /**
     * @return The constant value
     */

    @Value.Parameter
    String value();
  }

  /**
   * A wildcard pattern.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PPatternWildcardType<T> extends PPatternType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.PATTERN_WILDCARD;
    }

    @Override
    @Value.Parameter
    @Value.Auxiliary
    Optional<LexicalPosition<Path>> lexical();

    @Override
    @Value.Parameter
    @Value.Auxiliary
    T data();
  }

  /**
   * A constructor pattern.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PPatternConstructorType<T> extends PPatternType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.PATTERN_CONSTRUCTOR;
    }

    @Override
    @Value.Parameter
    @Value.Auxiliary
    Optional<LexicalPosition<Path>> lexical();

    @Override
    @Value.Parameter
    @Value.Auxiliary
    T data();

    /**
     * @return The constructor name
     */

    @Value.Parameter
    PTermName<T> constructor();

    /**
     * @return The constructor argument
     */

    @Value.Parameter
    Optional<PTermName<T>> argument();
  }
}
