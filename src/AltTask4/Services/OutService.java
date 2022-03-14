package AltTask4.Services;

import AltTask4.Primitives.Position;

public class OutService
{
    private char[][] chars;
    private int width;
    private int height;

    public OutService(int width, int height)
    {
        setWidth(width);
        setHeight(height);
        chars = new char[height][width];
    }

    public void Render()
    {
        var outStr = new StringBuilder();
        for (var row : chars)
        {
            for (var character : row)
            {
                outStr.append(character);
            }
            outStr.append('\n');
        }
        System.out.println(outStr);
    }

    public void Append(char[][] arr, Position position)
    {
        var pos = new Position(position.getX(), position.getY());
        for (var row : arr)
        {
            for (var character : row)
            {
                try
                {
                    chars[pos.getY()][pos.getX()] = character;
                    pos.setX(pos.getX() + 1);
                }
                catch (Exception e)
                {
                    break;
                }
            }
            pos.setX(position.getX());
            pos.setY(pos.getY() + 1);
        }
    }

    public void Append(String str, Position position)
    {
        Append(new char[][]{str.toCharArray()}, position);
    }

    public void Append(String[] arr, Position position)
    {
        var pos = new Position(position.getX(), position.getY());
        for (var row : arr)
        {
            for (var character : row.toCharArray())
            {
                try
                {
                    chars[pos.getY()][pos.getX()] = character;
                    pos.setX(pos.getX() + 1);
                }
                catch (Exception e)
                {
                    break;
                }
            }
            pos.setX(position.getX());
            pos.setY(pos.getY() + 1);
        }
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
