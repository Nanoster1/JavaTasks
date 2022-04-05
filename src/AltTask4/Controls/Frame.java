package AltTask4.Controls;

import AltTask4.Abstractions.Control;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

public class Frame extends Border
{
    private final String header;


    public Frame(String header, Control child)
    {
        super(Border.GetDoubleBorder(child));
        this.header = header;
    }

    @Override
    public void Render(Position position, int width, int height, OutService out)
    {
        super.Render(position, width, height, out);
        out.Append(String.format(" %s ", getHeader()), new Position(position.getX() + 2, position.getY()));
    }

    @Override
    public int getMinWidth()
    {
        var minWidth = super.getMinWidth();
        if (minWidth < header.length() + 5) minWidth = header.length() + 5;
        return minWidth + 2;
    }

    @Override
    public int getMinHeight()
    {
        var min = super.getMinHeight();
        return super.getMinHeight();
    }

    @Override
    public void RenderChild(OutService out)
    {
        if (getChild() == null) return;
        var childPos = new Position(position.getX() + getThickness() + 1, position.getY() + getThickness());
        getChild().Render(childPos, getWidth() - 2 * getThickness() - 2, getHeight() - 2 * getThickness(), out);
    }

    public String getHeader()
    {
        return header;
    }

}
