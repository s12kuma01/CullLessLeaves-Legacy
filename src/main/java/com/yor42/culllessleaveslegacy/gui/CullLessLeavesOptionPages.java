package com.yor42.culllessleaveslegacy.gui;

import com.google.common.collect.ImmutableList;
import com.yor42.culllessleaveslegacy.CullLessLeavesConfig;
import net.minecraft.client.resources.I18n;
import org.embeddedt.embeddium.impl.gui.framework.TextComponent;
import org.taumc.celeritas.api.options.OptionIdentifier;
import org.taumc.celeritas.api.options.control.ControlValueFormatter;
import org.taumc.celeritas.api.options.control.CyclingControl;
import org.taumc.celeritas.api.options.control.SliderControl;
import org.taumc.celeritas.api.options.control.TickBoxControl;
import org.taumc.celeritas.api.options.structure.OptionFlag;
import org.taumc.celeritas.api.options.structure.OptionGroup;
import org.taumc.celeritas.api.options.structure.OptionImpl;
import org.taumc.celeritas.api.options.structure.OptionPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the CullLessLeaves option page for Celeritas GUI
 */
public class CullLessLeavesOptionPages {

    public static final OptionIdentifier<Void> CULL_LESS_LEAVES_PAGE = OptionIdentifier.create("culllessleaves", "culllessleaves");

    private static final CullLessLeavesOptionsStorage storage = new CullLessLeavesOptionsStorage();

    public static OptionPage cullLessLeaves() {
        List<OptionGroup> groups = new ArrayList<>();

        // Main toggle group
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, storage)
                        .setName(TextComponent.literal(I18n.format("culllessleaves.option.enabled")))
                        .setTooltip(TextComponent.literal(I18n.format("culllessleaves.option.enabled.tooltip")))
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.enabled = value,
                                   opts -> opts.enabled)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());

        // Preset selection group
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(CullLessLeavesConfig.CullingPreset.class, storage)
                        .setName(TextComponent.literal(I18n.format("culllessleaves.option.preset")))
                        .setTooltip(TextComponent.literal(I18n.format("culllessleaves.option.preset.tooltip")))
                        .setControl(opts -> new CyclingControl<>(opts, CullLessLeavesConfig.CullingPreset.class,
                                new TextComponent[] {
                                    TextComponent.literal(I18n.format("culllessleaves.option.preset.fast")),
                                    TextComponent.literal(I18n.format("culllessleaves.option.preset.balanced")),
                                    TextComponent.literal(I18n.format("culllessleaves.option.preset.fancy")),
                                    TextComponent.literal(I18n.format("culllessleaves.option.preset.custom"))
                                }))
                        .setBinding((opts, value) -> opts.setPreset(value),
                                   opts -> opts.getPreset())
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());

        // Custom settings group (only visible when CUSTOM preset is selected)
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(int.class, storage)
                        .setName(TextComponent.literal(I18n.format("culllessleaves.option.depth")))
                        .setTooltip(TextComponent.literal(I18n.format("culllessleaves.option.depth.tooltip")))
                        .setControl(opts -> new SliderControl(opts, 0, 5, 1, ControlValueFormatter.number()))
                        .setBinding((opts, value) -> {
                            opts.customDepth = value;
                            // Auto-switch to CUSTOM preset when manually adjusted
                            if (opts.getPreset() != CullLessLeavesConfig.CullingPreset.CUSTOM) {
                                opts.setPreset(CullLessLeavesConfig.CullingPreset.CUSTOM);
                            }
                        }, opts -> opts.customDepth)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(int.class, storage)
                        .setName(TextComponent.literal(I18n.format("culllessleaves.option.randomRejection")))
                        .setTooltip(TextComponent.literal(I18n.format("culllessleaves.option.randomRejection.tooltip")))
                        .setControl(opts -> new SliderControl(opts, 0, 50, 1, ControlValueFormatter.percentage()))
                        .setBinding((opts, value) -> {
                            opts.customRandomRejection = value / 100.0;
                            // Auto-switch to CUSTOM preset when manually adjusted
                            if (opts.getPreset() != CullLessLeavesConfig.CullingPreset.CUSTOM) {
                                opts.setPreset(CullLessLeavesConfig.CullingPreset.CUSTOM);
                            }
                        }, opts -> (int)(opts.customRandomRejection * 100))
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());

        return new OptionPage(
                CULL_LESS_LEAVES_PAGE,
                TextComponent.literal(I18n.format("culllessleaves.option.page.title")),
                ImmutableList.copyOf(groups)
        );
    }
}
