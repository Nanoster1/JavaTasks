package AltTask4.Tests;

import AltTask4.Controls.Border;
import AltTask4.Controls.Frame;
import AltTask4.Controls.Panel;
import AltTask4.Controls.Text;
import AltTask4.Enums.Orientation;
import AltTask4.Primitives.Position;
import AltTask4.Services.OutService;

public class Main
{
    //@formatter:off
    public static void main(String[] args)
    {
        /*var frame = new Frame(
                "Login1",
                new Frame(
                        "Login2",
                        new Panel(Orientation.Horizontal,
                                new Text("Shit"),
                                new Text("Yes!"),
                                new Frame(
                                        "Oh, Yea!",
                                        new Text("Frame")
                                ),
                                Border.GetClassicBorder(
                                        new Text("Border")
                                )
                        )
                )
        );*/
        var frame = new Frame(
                "Login",
                new Panel(
                        Orientation.Horizontal,
                        new Panel(
                                Orientation.Vertical,
                                Border.GetClassicBorder(new Text("User")),
                                Border.GetClassicBorder(new Text("Password")),
                                new Panel(
                                        Orientation.Horizontal,
                                        Border.GetClassicBorder(new Text("Ok")),
                                        Border.GetClassicBorder(new Text("Cancel"))
                                        )
                                ),
                        new Frame(
                                "Help",
                                new Panel(
                                        Orientation.Vertical,
                                        new Text("Please enter login"),
                                        new Text("  and password"),
                                        new Text("Then press Ok button")
                                )
                        )
                )
        );
        var out = new OutService(frame.getMinWidth(), frame.getMinHeight());

        frame.Render(new Position(0, 0), out.getWidth(), out.getHeight(), out);
        out.Render();
    }
}