package AltTask6;

import java.util.ArrayList;
import java.util.List;

public class ExceptionUtil
{
    public static Throwable merge(Throwable e, StackTraceElement[] stackTrace)
    {
        var causes = GetExceptionWithAllCauses(e);
        causes.forEach(cause -> UpdateStackTrace(cause, stackTrace));
        return e;
    }

    private static List<Throwable> GetExceptionWithAllCauses(Throwable e)
    {
        var causes = new ArrayList<Throwable>();
        if (e == null) return causes;
        var cause = e;
        while (cause != null)
        {
            causes.add(cause);
            cause = cause.getCause();
        }

        return causes;
    }

    private static void UpdateStackTrace(Throwable e, StackTraceElement[] stackTrace)
    {
        if (e == null || stackTrace == null || stackTrace.length == 0) return;
        var updatedStackTrace = new ArrayList<>(List.of(e.getStackTrace()));
        updatedStackTrace.addAll(List.of(stackTrace));
        e.setStackTrace(updatedStackTrace.toArray(new StackTraceElement[0]));
    }
}
