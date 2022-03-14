package AltTask4.Controls;

import AltTask4.Abstractions.Block;
import AltTask4.Abstractions.Control;
import AltTask4.Extensions.StringExtensions;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

import java.util.ArrayList;

public class Border extends Block
{
    private char leftTop;
    private char rightTop;
    private char leftBot;
    private char rightBot;
    private char horizontalLine;
    private char verticalLine;
    private int thickness;

    public Border(char leftTop, char rightTop, char leftBot, char rightBot, char horizontalLine, char verticalLine, int thickness, Control child)
    {
        super(child);
        setHorizontalLine(horizontalLine);
        setVerticalLine(verticalLine);
        setLeftTop(leftTop);
        setRightTop(rightTop);
        setLeftBot(leftBot);
        setRightBot(rightBot);
        this.thickness = thickness;
    }

    public Border(Border border)
    {
        super(border.getChild());
        setHorizontalLine(border.horizontalLine);
        setVerticalLine(border.verticalLine);
        setLeftTop(border.leftTop);
        setRightTop(border.rightTop);
        setLeftBot(border.leftBot);
        setRightBot(border.rightBot);
        this.thickness = border.thickness;
    }

    public static Border GetClassicBorder(Control child)
    {
        return new Border('┌', '┐', '└', '┘', '─', '│', 1, child);
    }

    public static Border GetDoubleBorder(Control child)
    {
        return new Border('╔', '╗', '╚', '╝', '═', '║', 1, child);
    }

    @Override
    public void Render(Position position, int width, int height, OutService out)
    {
        super.Render(position, width, height, out);

        var list = new ArrayList<String>();
        var str = new StringBuilder();

        str.append(StringExtensions.Create(leftTop, thickness));
        str.append(StringExtensions.Create(horizontalLine, width - 2));
        str.append(StringExtensions.Create(rightTop, thickness));
        list.add(str.toString());
        str.delete(0, str.length());

        for (var i = 0; i < height - 2; i++)
        {
            list.add(verticalLine + StringExtensions.Create(' ', width - 2) + verticalLine);
        }

        str.append(StringExtensions.Create(leftBot, thickness));
        str.append(StringExtensions.Create(horizontalLine, width - 2));
        str.append(StringExtensions.Create(rightBot, thickness));
        list.add(str.toString());

        out.Append(list.toArray(new String[list.size()]), position);

        RenderChild(out);
    }

    public char getHorizontalLine()
    {
        return horizontalLine;
    }

    public void setRightBot(char rightBot)
    {
        this.rightBot = rightBot;
    }

    public void setLeftBot(char leftBot)
    {
        this.leftBot = leftBot;
    }

    public void setRightTop(char rightTop)
    {
        this.rightTop = rightTop;
    }

    public void setLeftTop(char leftTop)
    {
        this.leftTop = leftTop;
    }

    public void setVerticalLine(char verticalLine)
    {
        this.verticalLine = verticalLine;
    }

    public void setHorizontalLine(char horizontalLine)
    {
        this.horizontalLine = horizontalLine;
    }

    public int getThickness()
    {
        return thickness;
    }

    @Override
    public void RenderChild(OutService out)
    {
        var childPos = new Position(position.getX() + thickness, position.getY() + thickness);
        if (getChild() != null)
            getChild().Render(childPos, getWidth() - 2 * thickness, getHeight() - 2 * thickness, out);
    }

    @Override
    public int getMinWidth()
    {
        return super.getMinWidth() + 2 * thickness;
    }

    @Override
    public int getMinHeight()
    {
        return super.getMinHeight() + 2 * thickness;
    }
}
