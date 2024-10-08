package me.github.reportcardsmc.plotsk.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.classes.YggdrasilSerializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import org.jetbrains.annotations.Nullable;

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

//                    @Override
//                    public boolean canParse() {
//
//                    }

                    @Override
                    public String toString(final Plot plot, final int arg1) {
                        return plot.getId().toString();
                    }

                    @Override
                    public String toVariableNameString(final Plot plot) {
                        return "plot-" + plot.getId();
                    }

                })
                .serializer(new Serializer<Plot>() {
                    @Override
                    public Fields serialize(Plot n) {
                        throw new IllegalStateException(); // serialised natively by Yggdrasil
                    }

                    @Override
                    public boolean canBeInstantiated() {
                        return true;
                    }

                    @Override
                    public void deserialize(Plot o, Fields f) {
                        assert false;
                    }

                    @Override
                    @Nullable
                    public Plot deserialize(String s) {
                        try {
                            if (PlotSquaredUtil.getPlot(s) != null)
                                return PlotSquaredUtil.getPlot(s);
                            return null;
                        } catch (NullPointerException err) {
                            throw err;
                        }
                    }

                    @Override
                    public boolean mustSyncDeserialization() {
                        return false;
                    }
                })
        );
    }

}
