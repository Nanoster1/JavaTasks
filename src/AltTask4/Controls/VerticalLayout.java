package AltTask4.Controls;

import AltTask4.Abstractions.Container;
import AltTask4.Abstractions.Control;
import AltTask4.Abstractions.Layout;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

import java.util.Comparator;

public class VerticalLayout extends Layout
{
    public VerticalLayout(Container container, int spacing)
    {
        super(container, spacing);
        Spacing = 0;
    }

    @Override
    public void RenderChildren(OutService out)
    {
        var childWidth = container.children.stream().max(Comparator.comparing(Control::getMinWidth)).get().getMinWidth();
        var childHeight = container.children.stream().max(Comparator.comparing(Control::getMinHeight)).get().getMinHeight();
        for (var i = 0; i < container.children.size(); i++)
        {
            var child = container.children.get(i);
            var x = container.position.getX();
            var y = container.position.getY() + i * childHeight + Spacing;
            var positionForChildren = new Position(x, y);
            child.Render(positionForChildren, childWidth, childHeight, out);
        }
    }

    @Override
    public int GetMinWidth()
    {
        var maxChild = container.children.stream().max(Comparator.comparing(Control::getMinWidth));
        var maxWidth = 0;
        if (maxChild.isPresent()) maxWidth = maxChild.get().getMinWidth();
        return maxWidth;
    }

    @Override
    public int GetMinHeight()
    {
        var maxChild = container.children.stream().max(Comparator.comparing(Control::getMinHeight));
        var maxHeight = 0;
        if (maxChild.isPresent()) maxHeight = maxChild.get().getMinHeight();

        var result = 0;
        for (var ignored : container.children)
        {
            result += maxHeight + Spacing;
        }
        return result - Spacing;
    }
}
