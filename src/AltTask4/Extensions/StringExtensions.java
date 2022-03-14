package AltTask4.Extensions;

public class StringExtensions
{
    public static String Create(char character, int count)
    {
        var string = new StringBuilder();
        for (var i = 0; i < count; i++)
        {
            string.append(character);
        }
        return string.toString();
    }
}
