package main.renderer.synthesized;


import javafx.scene.effect.Light;
import main.renderer.synthesized.buffers.LightInfoBuffer;
import main.renderer.synthesized.buffers.TransformBuffer;
import org.lwjgl.CLongBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkCalibratedTimestampInfoEXT;

import java.util.ArrayList;

public class TriangleMainWithSynthesis {

    LightInfoBuffer lightInfo;

    Handle lightPipeline = new Handle();
    Handle transformBuffer = new Handle();
    Handle lightInfoBuffer = new Handle();
    Handle meshPipeline = new Handle();

    //@COGEN<Catalog> ************************************************************
    Catalog catalog;

    //@COGEN<Helpers>
    private Handle createLightPipeline(Catalog catalog) {
        return new Handle();
    }
    private Handle createMeshPipeline(Catalog catalog) {
        return new Handle();
    }
    private Handle createIndexBuffer(Catalog catalog, ArrayList<Integer> data) {
        return new Handle();
    }
    private Handle createVertexBuffer(Catalog catalog, ArrayList<Float> data) {
        return new Handle();
    }
    private Handle createTransformBuffer(Catalog catalog) {
        return new Handle();
    }
    private Handle createLightInfoBuffer(Catalog catalog) {
        return new Handle();
    }
    private Handle createDiffuseTexture(Catalog catalog, int height, int width) {
        return new Handle();
    }



    //@COGEN<RuntimeMethods> ************************************************************
    private void initRendering(Catalog catalog) {
        //1. create physical device
        //2. attach vulkan to display surface
        //3. create logical device
        //4. create command pool
        //5. create descriptors
        //6. create descriptor set pool
        //7. create descriptor sets
        //8. create synchronization objects
        //9. create framebuffer and gbuffer images
        //10. create swapchain

    }
    private void destroyRendering(Catalog catalog) {
        //1. destroy previously created resources
    }
    //***********************************************************************************

    //@COGEN<RenderLoop> ***************************************************************
    private void beginFrame(Catalog catalog) {
        //1. recreate swapchain if required
        //2. wait for previous frame to finish drawing
    }
    private void endFrame(Catalog catalog) {
        //1. signal that render is finished
        //2. submit command buffers
        //3. present by publishing on preset queue
    }
    //***********************************************************************************

    //@COGEN<Commands> ******************************************************************
    private Handle createCommandList(Catalog catalog) {return new Handle();}
    private void submitCommandList(Catalog catalog, Handle cmdList) {}
    private void enqueueSetPipeline(Catalog catalog, Handle cmdListId, Handle pipelineId) {}
    private void enqueueSetBuffer(Catalog catalog, Handle cmdListId, Handle bufferId) {}
    private void enqueueDraw(Catalog catalog, Handle cmdListId) {}
    private boolean enqueueUpdateTransformBuffer(Catalog catalog, Handle cmdList, Handle buffId, TransformBuffer buff) {
        return true;
    }
    private boolean enqueueUpdateLightInfoBuffer(Catalog catalog, Handle cmdList, Handle buffId, LightInfoBuffer buff) {
        return true;
    }
    private boolean enqueueUpdateIndexBuffer(Catalog catalog, Handle cmdList, Handle buffId, ArrayList<Integer> indexData) {
        return true;
    }
    private boolean enqueueUpdateVertexBuffer(Catalog catalog, Handle cmdList, Handle buffId, ArrayList<Float> floatData) {
        return true;
    }

    private boolean enqueueSetTexture(Catalog catalog, Handle cmdList, Handle texture) {
        return true;
    }



    //***********************************************************************************

    TriangleMainWithSynthesis() {

        lightInfo = new LightInfoBuffer();
        initRendering(catalog);

        meshPipeline = createMeshPipeline(catalog);
        lightPipeline = createLightPipeline(catalog);
        transformBuffer = createTransformBuffer(catalog);
        lightInfoBuffer = createLightInfoBuffer(catalog);

    }

    public void gameLoop() {

        ArrayList<Renderable> thingsToRender = updateWorld();
        renderFrame(thingsToRender);
    }

    public void renderFrame(ArrayList<Renderable> thingsToRender) {

        beginFrame(catalog);

        Handle cmdListId = createCommandList(catalog);

        enqueueSetPipeline(catalog, cmdListId, meshPipeline);

        for(Renderable renderable : thingsToRender) {

            Mesh mesh = renderable.getMesh();
            Handle indexBuffer = createIndexBuffer(catalog, mesh.getIndexBufferData());
            Handle vertexBuffer = createVertexBuffer(catalog, mesh.getVertexBufferData());

            enqueueUpdateIndexBuffer(
                    catalog,
                    cmdListId,
                    indexBuffer,
                    renderable.getMesh().getIndexBufferData()
            );
            enqueueUpdateVertexBuffer(
                    catalog,
                    cmdListId,
                    vertexBuffer,
                    renderable.getMesh().getVertexBufferData()
            );

            enqueueUpdateTransformBuffer(
                    catalog,
                    cmdListId,
                    transformBuffer,
                    renderable.getTransformBuffer()
            );

            enqueueSetTexture(catalog, cmdListId, mesh.getTexture());
            enqueueSetBuffer(catalog, cmdListId, vertexBuffer);
            enqueueSetBuffer(catalog, cmdListId, indexBuffer);
            enqueueSetBuffer(catalog, cmdListId, transformBuffer);

            enqueueDraw(catalog, cmdListId);
        }

        enqueueUpdateLightInfoBuffer(
                catalog,
                cmdListId,
                lightInfoBuffer,
                lightInfo

        );

        enqueueSetBuffer(catalog, cmdListId, lightInfoBuffer);
        enqueueSetPipeline(catalog, cmdListId, lightPipeline);
        enqueueDraw(catalog, cmdListId);

        submitCommandList(catalog, cmdListId);

        endFrame(catalog);
    }

    public ArrayList<Renderable> updateWorld() {return new ArrayList<>();}

    public static void main(String[] args) {

        TriangleMainWithSynthesis app = new TriangleMainWithSynthesis();
        boolean allDone = false;

        while(true) {
            app.gameLoop();
            if(allDone) {
                break;
            }
        }

        app.destroyRendering(app.catalog);

    }

}