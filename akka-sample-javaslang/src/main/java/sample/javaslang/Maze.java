package sample.javaslang;

import javaslang.collection.List;
import javaslang.control.Option;
import javaslang.match.annotation.Patterns;

import java.util.Arrays;
import java.util.Collections;

@Patterns
public class Maze {

  private final Character finish = '⚑';
  private final List<String> layout;

  public Maze(List<String> layout) {
    this.layout = layout;
  }

  public boolean isLegal(Coords c) {
    return Character.isWhitespace(layout.get(c.y).charAt(c.x)) || isFinish(c);
  }

  public boolean isFinish(Coords c) {
    return layout.get(c.y).charAt(c.x) == finish;
  }

  public Option<Coords> legalFrom(Coords coords) {
    return Translation.randomOrder()
      .map(coords::translate)
      .filter(this::isLegal)
      .toOption();
  }

  enum Translation {
    TOP, RIGHT, DOWN, LEFT;

    public static List<Translation> randomOrder() {
      java.util.List<Translation> translations = Arrays.asList(values());
      Collections.shuffle(translations);
      return List.ofAll(translations);
    }
  }
}
