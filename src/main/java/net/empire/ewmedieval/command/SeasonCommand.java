package net.empire.ewmedieval.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.empire.ewmedieval.season.SeasonManager;
import net.empire.ewmedieval.season.SeasonPeriod;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class SeasonCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("season")
            .then(CommandManager.literal("get")
                .executes(SeasonCommand::executeGet))
            .then(CommandManager.literal("set")
                .requires(src -> src.hasPermissionLevel(2))
                .then(CommandManager.argument("period", StringArgumentType.word())
                    .suggests((ctx, builder) -> {
                        for (SeasonPeriod p : SeasonPeriod.values()) {
                            builder.suggest(p.name().toLowerCase());
                        }
                        return builder.buildFuture();
                    })
                    .executes(SeasonCommand::executeSet)))
        );
    }

    private static int executeGet(CommandContext<ServerCommandSource> ctx) {
        ServerWorld world = ctx.getSource().getWorld();
        SeasonPeriod period = SeasonManager.getCurrentPeriod(world);
        long totalDays    = world.getTimeOfDay() / 24000L;
        long year         = totalDays / SeasonPeriod.TOTAL_YEAR_DAYS + 1;
        int  dayInYear    = (int)(totalDays % SeasonPeriod.TOTAL_YEAR_DAYS) + 1;
        int  dayInPeriod  = (int)((totalDays % SeasonPeriod.TOTAL_YEAR_DAYS) % SeasonPeriod.DAYS_PER_PERIOD) + 1;

        ctx.getSource().sendFeedback(() -> Text.literal(
            "Season: " + period.displayName +
            " | Day " + dayInPeriod + "/" + SeasonPeriod.DAYS_PER_PERIOD +
            " | Year " + year + ", day " + dayInYear + "/96"
        ).formatted(Formatting.GOLD), false);
        return 1;
    }

    private static int executeSet(CommandContext<ServerCommandSource> ctx) {
        String input = StringArgumentType.getString(ctx, "period");
        SeasonPeriod target = null;
        for (SeasonPeriod p : SeasonPeriod.values()) {
            if (p.name().equalsIgnoreCase(input)) { target = p; break; }
        }
        if (target == null) {
            ctx.getSource().sendError(Text.literal("Unknown period: " + input +
                ". Use e.g. early_spring, mid_summer, late_winter…"));
            return 0;
        }

        ServerWorld world = ctx.getSource().getWorld();
        long totalDays  = world.getTimeOfDay() / 24000L;
        long currentYear = totalDays / SeasonPeriod.TOTAL_YEAR_DAYS;
        long newTime = (currentYear * SeasonPeriod.TOTAL_YEAR_DAYS
                        + (long) target.ordinal() * SeasonPeriod.DAYS_PER_PERIOD) * 24000L;
        world.setTimeOfDay(newTime);

        final SeasonPeriod t = target;
        ctx.getSource().sendFeedback(
            () -> Text.literal("Season set to: " + t.displayName).formatted(Formatting.GREEN),
            true);
        return 1;
    }
}
