package me.github.reportcardsmc.plotsk.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.plotsquared.core.plot.Plot;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(Plot.class, "plot")
                .defaultExpression(new EventValueExpression<>(Plot.class))
                .user("plot")
                .name("Plot")
                .description("Represents a PlotSquared plot")
                .since("INSERT VERSION")
                .parser(new Parser<>() {

                    @Override
                    public Plot parse(final String id, final ParseContext context) {
                        return null;
                    }

                    @Override
                    public String toString(final Plot plot, final int arg1) {
                        return plot.getId().toString();
                    }

                    @Override
                    public String toVariableNameString(final Plot plot) {
                        return "plot-" + plot.getId();
                    }

                })
        );
    }

}
