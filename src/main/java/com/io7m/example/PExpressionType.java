/*
 * Copyright © 2017 <code@io7m.com> http://io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.example;

import io.vavr.collection.Vector;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.Optional;

/**
 * The type of expressions.
 *
 * @param <T> The type of associated data
 */

public interface PExpressionType<T> extends PModelElementType<T>
{
  /**
   * @return The kind of expression
   */

  Kind kind();

  @Override
  Optional<LexicalPosition<Path>> lexical();

  @Override
  T data();

  /**
   * The kind of expressions.
   */

  enum Kind
  {
    /**
     * @see PExprConstantRealType
     */

    EXPR_CONSTANT_REAL,

    /**
     * @see PExprConstantStringType
     */

    EXPR_CONSTANT_STRING,

    /**
     * @see PExprConstantIntegerType
     */

    EXPR_CONSTANT_INTEGER,

    /**
     * @see PExprApplicationType
     */

    EXPR_APPLICATION,

    /**
     * @see PExprBeginType
     */

    EXPR_BEGIN,

    /**
     * @see PExprLambdaType
     */

    EXPR_LAMBDA,

    /**
     * @see PExprReferenceType
     */

    EXPR_REFERENCE,

    /**
     * @see PExprRecordFieldAccessType
     */

    EXPR_RECORD_FIELD_ACCESS,

    /**
     * @see PExprLetType
     */

    EXPR_LET,

    /**
     * @see PExprMatchType
     */

    EXPR_MATCH
  }

  /**
   * An integer constant.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprConstantIntegerType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_CONSTANT_INTEGER;
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
   * A real constant.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprConstantRealType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_CONSTANT_REAL;
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
   * A string constant.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprConstantStringType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_CONSTANT_STRING;
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
   * A function application.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprApplicationType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_APPLICATION;
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
     * @return The function value
     */

    @Value.Parameter
    PExpressionType<T> function();

    /**
     * @return The function arguments
     */

    @Value.Parameter
    Vector<PExpressionType<T>> arguments();
  }

  /**
   * A 'begin' expression.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprBeginType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_BEGIN;
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
     * @return The sequence of expressions
     */

    @Value.Parameter
    Vector<PExpressionType<T>> expressions();
  }

  /**
   * A lambda expression.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprLambdaType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_LAMBDA;
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
     * @return The arguments
     */

    @Value.Parameter
    Vector<PTermName<T>> arguments();

    /**
     * @return The body expression
     */

    @Value.Parameter
    PExpressionType<T> expression();
  }

  /**
   * A reference expression.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprReferenceType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_REFERENCE;
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
     * @return The qualifying unit name, if any
     */

    @Value.Parameter
    Optional<PUnitName<T>> unit();

    /**
     * @return The term name
     */

    @Value.Parameter
    PTermName<T> name();
  }

  /**
   * A record field access expression.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprRecordFieldAccessType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_RECORD_FIELD_ACCESS;
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
     * @return The left-hand side of the expression
     */

    @Value.Parameter
    PExpressionType<T> expression();

    /**
     * @return The term name
     */

    @Value.Parameter
    PTermName<T> name();
  }

  /**
   * A let expression.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprLetType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_LET;
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
     * @return The local declarations
     */

    @Value.Parameter
    Vector<PTermDeclarationType<T>> declarations();

    /**
     * @return The body of the let expression
     */

    @Value.Parameter
    PExpressionType<T> expression();
  }

  /**
   * A match expression.
   *
   * @param <T> The type of associated data
   */

  @PImmutableStyleType
  @Value.Immutable
  interface PExprMatchType<T> extends PExpressionType<T>
  {
    @Override
    default Kind kind()
    {
      return Kind.EXPR_MATCH;
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
     * @return The target of the match expression
     */

    @Value.Parameter
    PExpressionType<T> target();
  }
}
