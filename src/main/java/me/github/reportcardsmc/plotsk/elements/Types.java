package me.github.reportcardsmc.plotsk.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.function.Parameter;
import ch.njol.skript.lang.function.SimpleJavaFunction;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.DefaultClasses;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;

import static ch.njol.skript.lang.function.Functions.registerFunction;

public class Types {

    static {

        Classes.registerClass(new ClassInfo<>(Plot.class, "plot")
                .defaultExpression(new EventValueExpression<>(Plot.class))
                .user("plot")
                .name("Plot")
                .description("Represents a PlotSquared plot")
                .since("1.3.0")
                .parser(new Parser<Plot>() {

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

        registerFunction(new SimpleJavaFunction<>("plot", new Parameter[]{

                new Parameter<>("x", DefaultClasses.NUMBER, true, null),
                new Parameter<>("y", DefaultClasses.NUMBER, true, null)

        }, Classes.getExactClassInfo(Plot.class), true) {

            @Override
            public Plot[] executeSimple(Object[][] params) {

                if (params.length < 2) {
                    return null;
                }

                return new Plot[]{PlotSquaredUtil.getPlot(params[0][0] + ";" + params[1][0])};

            }
        });
    }
}