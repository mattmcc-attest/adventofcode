import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Day2 implements Day {

  @Override
  public void part2() throws IOException {
    String filePath = "day2.txt";
    var answer = Files.lines(Paths.get(filePath)).map(report -> {

      var levels = new ArrayList<>(Arrays.stream(report.split("\\s+")).map(Integer::parseInt).toList());

      // is it already safe
      if (isSafe(levels)) {
        return 1;
      }

      // could it be safe with dampener
      var anyPermutationTrue = false;
      for (int i = 0; i < levels.size(); i++) {
        var newList = new ArrayList<>(levels);
        newList.remove(i);
        if (isSafe(newList)) {
          anyPermutationTrue = true;
        }
      }

      if (anyPermutationTrue) {
        return 1;
      }

      return 0;
    }).mapToInt(Integer::intValue).sum();
    System.out.println(answer);
  }

  private static boolean isSafe(List<Integer> levels) {
    return (isAscending(levels) || isDescending(levels)) && isSmallDiff(levels);
  }

  static boolean isAscending(List<Integer> list) {
    return list.stream().sorted().toList().equals(list);
  }

  static boolean isDescending(List<Integer> list) {
    return list.stream().sorted(Comparator.reverseOrder()).toList().equals(list);
  }

  static boolean isSmallDiff(List<Integer> list) {
    return IntStream.range(1, list.size())
        .mapToObj(i -> {
          Integer current = list.get(i);
          Integer previous = list.get(i - 1);
          var difference = Math.abs(current - previous);
          return difference > 3 || difference < 1;
        }).noneMatch(b -> b);
  }


  @Override
  public void part1() throws IOException {
    String filePath = "day2.txt";
    var answer = Files.lines(Paths.get(filePath)).map(report -> {
      var levels = Arrays.stream(report.split("\\s+")).map(Integer::parseInt).toList();
      if (isSafe(levels)) {
        return 1;
      }
      return 0;
    }).mapToInt(Integer::intValue).sum();
    System.out.println(answer);
  }
}
