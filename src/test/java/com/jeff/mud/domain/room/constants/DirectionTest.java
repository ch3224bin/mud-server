package com.jeff.mud.domain.room.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DirectionTest {

  @Test
  public void testStringValues() {
    Direction[] values = Direction.values();
    List<String> synonyms = new ArrayList<>();

    for (Direction direction : values) {
      synonyms.addAll(direction.getSynonyms());
    }

    Assertions.assertArrayEquals(synonyms.toArray(new String[] {}), Direction.stringValues());
  }
}