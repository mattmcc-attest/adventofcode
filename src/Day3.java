import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day3 implements Day {
  @Override
  public void part2() throws IOException {
    String filePath = "day3.txt";
    String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)";

    boolean on = true;

    var sum = 0;
    var lines = Files.readAllLines(Paths.get(filePath));

    for (var line: lines) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(line);

      while (matcher.find()) {

        if (Objects.equals(matcher.group(), "do()")) {
          on = true;
        } else if (Objects.equals(matcher.group(), "don't()")) {
          on = false;
        } else {
          var x = Integer.parseInt(matcher.group(1));
          var y = Integer.parseInt(matcher.group(2));

          if (on) {
            sum = sum + (x * y);
          }
        }

      }
    }
    System.out.println(sum);
  }

  @Override
  public void part1() throws IOException {
    String filePath = "day3.txt";
    String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
    var sum = 0;
    var lines = Files.readAllLines(Paths.get(filePath));
    for(var line: lines) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(line);
      while (matcher.find()) {
        var x = Integer.parseInt(matcher.group(1));
        var y = Integer.parseInt(matcher.group(2));

        sum = sum + (x * y);
      }
    }
    System.out.println(sum);
  }
}
