package net.empire.ewmedieval.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

// Base class for block entities that need to sync their NBT data to clients.
// Call inventoryChanged() whenever the inventory or state changes.
public abstract class SyncedBlockEntity extends BlockEntity {

    public SyncedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // Marks the block entity dirty and notifies clients of the change
    public void inventoryChanged() {
        if (world != null) {
            markDirty();
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    // Sends full NBT update to clients when the chunk is loaded or block is updated
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    // Sends a packet to the client to update the block entity
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}