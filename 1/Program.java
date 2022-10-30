import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private record Tuple(Integer previous, Integer current) {}
    private record Tuple3(Integer a, Integer b, Integer c) {}

    private static List<Integer> readFileAndParse(String filePath) throws Exception {
        return Files.readAllLines(Path.of(filePath))
                    .stream()
                    .map(s -> Integer.parseInt(s))
                    .toList();
    }

    private static Integer countMeasurementIncreases(List<Integer> input) {
        return zipItself(input)
               .stream()
               .filter(a -> a.previous < a.current)
               .toArray()
               .length;
    }

    private static List<Tuple> zipItself(List<Integer> input) {
        var ret = new ArrayList<Tuple>();
        for (int i = 1; i < input.size(); i++) {
            ret.add(new Tuple(input.get(i-1), input.get(i)));
        }
        return ret;
    }

    private static List<Tuple3> zipItself3(List<Integer> input) {
        var ret = new ArrayList<Tuple3>();
        for (int i = 2; i < input.size(); i++) {
            ret.add(new Tuple3(input.get(i-2), input.get(i-1), input.get(i)));
        }
        return ret;
    }

    private static void Part1(String filePath) throws Exception {
        var input = readFileAndParse(filePath);
        
        var result = countMeasurementIncreases(input);

        System.out.println("Part1 result: " + result);
    }

    private static void Part2(String filePath) throws Exception {
        var input = readFileAndParse(filePath);

        // convert to sliding window
        var slidingWindow = zipItself3(input)
            .stream()
            .map(x -> x.a + x.b + x.c)
            .toList();
        
        var result = countMeasurementIncreases(slidingWindow);

        System.out.println("Part2 result: " + result);
    }

    public static void main(String[] args) throws Exception {
        Part1(args[0]);
        Part2(args[0]);
    }
}