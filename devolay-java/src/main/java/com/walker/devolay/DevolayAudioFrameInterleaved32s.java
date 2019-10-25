package com.walker.devolay;

import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;

public class DevolayAudioFrameInterleaved32s implements AutoCloseable {

    static class State implements Runnable {
        private long structPointer;

        State(long pointer) {
            this.structPointer = pointer;
        }

        public void run() {
            destroyAudioFrameInterleaved32s(structPointer);
        }
    }


    private final DevolayAudioFrame.State state;
    private final Cleaner.Cleanable cleanable;
    final long structPointer;

    public DevolayAudioFrameInterleaved32s() {
        this.structPointer = createNewAudioFrameInterleaved32sDefaultSettings();

        this.state = new DevolayAudioFrame.State(structPointer);
        this.cleanable = Devolay.cleaner.register(this, state);
    }

    public void setSampleRate(int sampleRate) {
        setSampleRate(structPointer, sampleRate);
    }
    public int getSampleRate() {
        return getSampleRate(structPointer);
    }

    public void setChannels(int channels) {
        setNoChannels(structPointer, channels);
    }
    public int getChannels() {
        return getNoChannels(structPointer);
    }

    public void setSamples(int samples) {
        setNoSamples(structPointer, samples);
    }
    public int getSamples() {
        return getNoSamples(structPointer);
    }

    public void setTimecode(long timecode) {
        setTimecode(structPointer, timecode);
    }
    public long getTimecode() {
        return getTimecode(structPointer);
    }

    public void setReferenceLevel(int referenceLevel) {
        setReferenceLevel(structPointer, referenceLevel);
    }
    public long getReferenceLevel() {
        return getReferenceLevel(structPointer);
    }

    public void setData(ByteBuffer data) {
        setData(structPointer, data);
    }
    public ByteBuffer getData() {
        return getData(structPointer);
    }

    @Override
    public void close() {
        cleanable.clean();
    }

    // Native Methods

    private static native long createNewAudioFrameInterleaved32sDefaultSettings();
    private static native void destroyAudioFrameInterleaved32s(long structPointer);

    private static native void setSampleRate(long structPointer, int sampleRate);
    private static native int getSampleRate(long structPointer);
    private static native void setNoChannels(long structPointer, int channels);
    private static native int getNoChannels(long structPointer);
    private static native void setNoSamples(long structPointer, int samples);
    private static native int getNoSamples(long structPointer);
    private static native void setTimecode(long structPointer, long timecode);
    private static native long getTimecode(long structPointer);
    private static native void setReferenceLevel(long structPointer, int referenceLevel);
    private static native int getReferenceLevel(long structPointer);
    private static native void setData(long structPointer, ByteBuffer data);
    private static native ByteBuffer getData(long structPointer);
}