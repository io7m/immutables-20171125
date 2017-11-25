package com.io7m.example;

import io.vavr.collection.Vector;
import org.immutables.value.Value;

import java.nio.file.Path;
import java.util.Optional;

/**
 * The type of package names.
 *
 * @param <T> The type of associated data
 */

@PImmutableStyleType
@Value.Immutable(builder = false)
public interface PPackageNameType<T> extends PModelElementType<T>
{
  @Override
  @Value.Parameter
  @Value.Auxiliary
  Optional<LexicalPosition<Path>> lexical();

  @Override
  @Value.Parameter
  @Value.Auxiliary
  T data();

  /**
   * @return The actual name value
   */

  @Value.Parameter
  String value();

  /**
   * @return The components that make up the package name in declaration order
   */

  @Value.Derived
  default Vector<String> nameComponents()
  {
    return Vector.of(this.value().split("\\."));
  }
}
