package AltTask9;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Operator<TCollection extends Collection<TItem>, TItem>
{
    private final TCollection collection;

    private Operator(TCollection collection)
    {
        this.collection = collection;
    }

    public static <TCollection extends Collection<TItem>, TItem> Operator<TCollection, TItem> modify(TCollection collection)
    {
        return new Operator<TCollection, TItem>(collection);
    }

    public Operator<TCollection, TItem> add(TItem item)
    {
        this.collection.add(item);
        return this;
    }

    public Operator<TCollection, TItem> add(Collection<TItem> items)
    {
        this.collection.addAll(items);
        return this;
    }

    public Operator<TCollection, TItem> remove(Predicate<? super TItem> predicate)
    {
        var listToDelete = new ArrayList<TItem>();
        for (var item : this.collection) if (predicate.test(item)) listToDelete.add(item);
        this.collection.removeAll(listToDelete);
        return this;
    }

    public Operator<TCollection, TItem> sort(Comparator<? super TItem> comparator)
    {
        var sortedCollection = new ArrayList<>(this.collection);
        sortedCollection.sort(comparator);
        this.collection.clear();
        this.collection.addAll(sortedCollection);
        return this;
    }

    public Operator<TCollection, TItem> each(Consumer<? super TItem> consumer)
    {
        this.collection.forEach(consumer);
        return this;
    }

    public <TNewCollection extends Collection<TItem>> Operator<TNewCollection, TItem> copyTo(Supplier<TNewCollection> supplier)
    {
        return new Operator<TNewCollection, TItem>(this.collection.stream().collect(Collectors.toCollection(supplier)));
    }

    public TCollection get()
    {
        return this.collection;
    }

    public <TNewCollection extends Collection<TNewItem>, TNewItem> Operator<TNewCollection, TNewItem> convertTo(Supplier<TNewCollection> supplier, Function<TItem, TNewItem> modifier)
    {
        return new Operator<>(this.collection.stream().map(modifier).collect(Collectors.toCollection(supplier)));
    }
}
