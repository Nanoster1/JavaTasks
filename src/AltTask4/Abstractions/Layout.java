package AltTask4.Abstractions;

import AltTask4.Services.OutService;

public abstract class Layout
{
    public int Spacing;
    protected Container container;

    public Layout(Container container, int spacing)
    {
        this.container = container;
        Spacing = spacing;
    }

    public abstract int GetMinWidth();

    public abstract int GetMinHeight();

    public abstract void RenderChildren(OutService out);
}
