package AltTask4.Interfaces;

import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

public interface IControl
{
    void Render(Position position, int width, int height, OutService out);
}
