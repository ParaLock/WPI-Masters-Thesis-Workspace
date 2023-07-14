package main.renderer.synthesized;

import main.renderer.synthesized.buffers.TransformBuffer;

public class Renderable {
    public Mesh getMesh() {
        return new Mesh();
    }

    public TransformBuffer getTransformBuffer() {
        return new TransformBuffer();
    }
}
