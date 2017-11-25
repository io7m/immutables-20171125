package com.io7m.example;

import java.nio.file.Path;
import java.util.Optional;

/**
 * The type of model elements
 *
 * @param <T> The type of associated data
 */

public interface PModelElementType<T>
{
  /**
   * @return The lexical information for the element
   */

  Optional<LexicalPosition<Path>> lexical();

  /**
   * @return The associated data
   */

  T data();
}
