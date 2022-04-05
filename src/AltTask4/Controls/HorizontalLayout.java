package AltTask4.Controls;

import AltTask4.Abstractions.Container;
import AltTask4.Abstractions.Control;
import AltTask4.Abstractions.Layout;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

import java.util.Comparator;

public class HorizontalLayout extends Layout
{

    public HorizontalLayout(Container container, int spacing)
    {
        super(container, spacing);
    }

    @Override
    public int GetMinWidth()
    {
        var result = 0;
        for (var child : container.children)
        {
            result += child.getMinWidth() + Spacing;
        }
        return result;
    }

    @Override
    public int GetMinHeight()
    {
        var maxChild = container.children.stream().max(Comparator.comparing(Control::getMinHeight));
        var maxHeight = 0;
        if (maxChild.isPresent()) maxHeight = maxChild.get().getMinHeight();
        return maxHeight;
    }

    @Override
    public void RenderChildren(OutService out)
    {
        var maxChild = container.children.stream().max(Comparator.comparing(Control::getMinWidth));
        var childHeight = 0;
        maxChild = container.children.stream().max(Comparator.comparing(Control::getMinHeight));
        if (maxChild.isPresent()) childHeight = maxChild.get().getMinHeight();

        var currentSize = 0;
        for (var i = 0; i < container.children.size(); i++)
        {
            var child = container.children.get(i);
            var x = container.position.getX() + currentSize;
            currentSize += child.getMinWidth() + Spacing;
            var y = container.position.getY();
            var positionForChildren = new Position(x, y);
            child.Render(positionForChildren, child.getMinWidth(), childHeight, out);
        }
    }
}
