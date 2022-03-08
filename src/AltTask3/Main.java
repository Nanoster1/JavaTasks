package AltTask3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        //Main array
        var arr = new char[][]
                {
                        {'#', 's', '#', '#', '#'},
                        {'#', '.', '.', '.', '#'},
                        {'.', '.', '#', '.', '#'},
                        {'.', '#', '.', '.', '#'},
                        {'#', '#', '#', '.', 'f'}
                };

        //Set configuration
        Predicate<Character> isStart = character -> character == 's';
        Predicate<Character> isEmpty = character -> character != '#';
        Predicate<Character> isFinish = character -> character == 'f';

        //Find start (For example s)
        var start = FindStart(arr, isStart);

        //Find the minimum path
        var result = FindWay(arr, start, isEmpty, isFinish);

        //Draw path on the array
        for (var i = 1; i < result.size() - 1; i++)
        {
            arr[result.get(i).getY()][result.get(i).getX()] = '*';
        }

        //Output the array to the console
        for (var element : arr)
        {
            for (var subElement : element)
            {
                System.out.print(subElement);
            }
            System.out.print('\n');
        }
    }

    public static Position FindStart(char[][] arr, Predicate<Character> isStart) throws Exception
    {
        for (var i = 0; i < arr.length; i++)
        {
            for (var j = 0; j < arr[i].length; j++)
            {
                if (isStart.test(arr[i][j])) return new Position(j, i);
            }
        }
        throw new Exception("Start not found");
    }

    public static ArrayList<Position> FindWay(char[][] arr, Position startPos, Predicate<Character> isEmpty, Predicate<Character> isFinish)
    {
        var paths = new ArrayList<ArrayList<Position>>();

        FindAllWays(arr, new Position(startPos.getX(), startPos.getY()), isEmpty, isFinish, new ArrayList<>(), paths);

        return paths.stream().min(Comparator.comparing(ArrayList::size)).get();
    }

    public static void FindAllWays(
            char[][] arr, Position startPos, Predicate<Character> isEmpty, Predicate<Character> isFinish,
            ArrayList<Position> path, ArrayList<ArrayList<Position>> paths)
    {
        if (path.contains(startPos)) return;
        if (startPos.getY() < 0 || startPos.getY() >= arr.length || startPos.getX() < 0 || startPos.getX() >= arr[startPos.getY()].length)
            return;

        path.add(new Position(startPos.getX(), startPos.getY()));

        if (isFinish.test(arr[startPos.getY()][startPos.getX()])) paths.add(path);
        if (!isEmpty.test(arr[startPos.getY()][startPos.getX()])) return;

        FindAllWays(arr, new Position(startPos.getX() - 1, startPos.getY()), isEmpty, isFinish, (ArrayList<Position>) path.clone(), paths);
        FindAllWays(arr, new Position(startPos.getX() + 1, startPos.getY()), isEmpty, isFinish, (ArrayList<Position>) path.clone(), paths);
        FindAllWays(arr, new Position(startPos.getX(), startPos.getY() - 1), isEmpty, isFinish, (ArrayList<Position>) path.clone(), paths);
        FindAllWays(arr, new Position(startPos.getX(), startPos.getY() + 1), isEmpty, isFinish, (ArrayList<Position>) path.clone(), paths);
    }
}
