import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Day1 implements Day {

  @Override
  public void part1() throws IOException {
    String filePath = "day1.txt";
    var leftQueue = new PriorityQueue<Integer>();
    var rightQueue = new PriorityQueue<Integer>();
    Files.lines(Paths.get(filePath)).forEach(l -> {
      var nums = l.split("\\s+");
      leftQueue.add(Integer.parseInt(nums[0]));
      rightQueue.add(Integer.parseInt(nums[1]));
    });
    var totalDistance = 0;
    while(!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
      totalDistance += Math.abs(leftQueue.poll() - rightQueue.poll());
    }
    System.out.println(totalDistance);
  }

  @Override
  public void part2() throws IOException {
    String filePath = "day1.txt";
    var rightKeysCount = new HashMap<Integer, Integer>();
    var leftList = Files.lines(Paths.get(filePath)).map(l -> {
      var nums = l.split("\\s+");
      var left = Integer.parseInt(nums[0]);
      var right = Integer.parseInt(nums[1]);
      rightKeysCount.merge(right, 1, Integer::sum);
      return left;
    }).toList();
    var answer = leftList.stream()
        .map(l -> l * rightKeysCount.getOrDefault(l, 0)).mapToInt(Integer::intValue).sum();
    System.out.println(answer);
  }
}
