package AltTask4.Abstractions;

import AltTask4.Interfaces.IControl;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

public abstract class Control implements IControl
{
    private int width;
    private int height;
    public Position position;

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

    public abstract int getMinWidth();

    public abstract int getMinHeight();

    @Override
    public void Render(Position position, int width, int height, OutService out)
    {
        setHeight(height);
        setWidth(width);
        this.position = position;
    }
}
