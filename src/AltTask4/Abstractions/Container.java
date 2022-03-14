package AltTask4.Abstractions;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Container extends Control
{
    public final ArrayList<Control> children = new ArrayList<>();

    public Container(Control... controls)
    {
        children.addAll(Arrays.asList(controls));
    }
}
