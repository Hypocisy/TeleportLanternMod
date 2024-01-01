package com.kumoe.LeatherHat.gui;

import com.kumoe.LeatherHat.LeatherMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TeleportSettingScreen extends AbstractContainerScreen<TeleportBlockContainerMenu> {
    private EditBox targetInput;
    private Button button;
    private ResourceLocation guiSetting = new ResourceLocation(LeatherMod.MODID, "textures/gui/bg.png");

    public TeleportSettingScreen(TeleportBlockContainerMenu pMenu, Inventory playerInventory, Component title) {
        super(pMenu, playerInventory, title);
        this.init();
    }

    @Override
    protected void init() {
        this.targetInput = new EditBox(this.font, 200, 20, this.width / 2 - 100, this.height / 2 - 50, Component.translatable("gui.leather_mod.default_position"));
        this.addWidget(targetInput);
        this.button = new Button(this.width / 2 - 40, 96, 80, 20, Component.literal("Save"), (button) -> {
        });

        this.addWidget(button);
        super.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.clearColor(1f, 1f, 1f, 1f);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindForSetup(guiSetting);
        }
        int textureWidth = 256;
        int textureHeight = 256;
        blit(pPoseStack, this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        this.font.draw(pPoseStack, "hello", (float) this.width / 2 - 10, 30, 0xeb0505);

        this.targetInput.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float v, int i, int i1) {
        blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (targetInput.isFocused()) {
            targetInput.charTyped(pCodePoint, pModifiers);
            LeatherMod.channel.sendToServer(targetInput.getValue());
        }
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (targetInput.isFocused()) {
            targetInput.mouseClicked(pMouseX, pMouseY, pButton);
            LeatherMod.channel.sendToServer(targetInput.getValue());
            return true;
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    protected void containerTick() {
        this.targetInput.tick();
        super.containerTick();
    }
}
