package AltTask4.Extensions;

public class StringExtensions
{
    public static String Create(char character, int count)
    {
        return String.valueOf(character).repeat(Math.max(0, count));
    }
}
