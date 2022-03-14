package AltTask4.Controls;

import AltTask4.Abstractions.Container;
import AltTask4.Abstractions.Control;
import AltTask4.Abstractions.Layout;
import AltTask4.Enums.Orientation;
import AltTask4.Services.OutService;
import AltTask4.Primitives.Position;

public class Panel extends Container
{
    private Layout layout = new HorizontalLayout(this, 1);
    private Orientation orientation = Orientation.Horizontal;

    public Panel(Orientation orientation, Control... controls)
    {
        super(controls);
        setOrientation(orientation);
    }

    @Override
    public void Render(Position position, int width, int height, OutService out)
    {
        super.Render(position, width, height, out);
        layout.RenderChildren(out);
    }

    public void setLayout(Layout ILayout)
    {
        this.layout = ILayout;
    }

    @Override
    public int getMinWidth()
    {
        return layout.GetMinWidth();
    }

    @Override
    public int getMinHeight()
    {
        return layout.GetMinHeight();
    }

    public Orientation getOrientation()
    {
        return orientation;
    }

    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
        if (orientation == Orientation.Horizontal) setLayout(new HorizontalLayout(this, 1));
        else setLayout(new VerticalLayout(this, 1));
    }
}
