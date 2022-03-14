package AltTask4.Abstractions;

import AltTask4.Services.OutService;

public abstract class Block extends Control
{
    private Control child;

    public Block(Control child)
    {
        setChild(child);
    }

    public abstract void RenderChild(OutService out);

    @Override
    public int getMinWidth()
    {
        if (getChild() == null) return 0;
        return getChild().getMinWidth();
    }

    @Override
    public int getMinHeight()
    {
        if (getChild() == null) return 0;
        return getChild().getMinHeight();
    }

    public Control getChild()
    {
        return child;
    }

    public void setChild(Control child)
    {
        this.child = child;
    }
}
