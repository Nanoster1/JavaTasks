package AltTask4.Controls;

import AltTask4.Abstractions.Control;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

public class Text extends Control
{
    private final String text;

    public Text(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    @Override
    public String toString()
    {
        return getText();
    }

    @Override
    public int getMinWidth()
    {
        return text.length();
    }

    @Override
    public int getMinHeight()
    {
        return 1;
    }

    @Override
    public void Render(Position position, int width, int height, OutService out)
    {
        out.Append(text, position);
    }
}
