package main.renderer.synthesized;

//@COGEN<Catalog>
//@COGEN<CommandList>
//@COGEN<Mesh>
//@COGEN<Image>
//@COGEN<Pipeline>

import java.util.ArrayList;

public class TriangleMainWithSynthesis {

    //@COGEN<Catalog> ************************************************************
    Catalog resourceCatalog;

    //@COGEN<LifeCycleMethods> ************************************************************
    private void initRendering(Catalog catalog) {
        //1.
        //2. create swapchain
        //2. create
    }
    private void destroyRendering(Catalog catalog) {
        //1. Create render resources
    }
    //***********************************************************************************

    //@COGEN<RenderLoop> ***************************************************************
    private void beginFrame(Catalog catalog) {
        //1.
    }
    private void endFrame(Catalog catalog) {

    }
    //***********************************************************************************

    //@COGEN<Commands> ******************************************************************
    private CommandList createCommandList(Catalog catalog) {return new CommandList();}

    //***********************************************************************************

    TriangleMainWithSynthesis() {

        initRendering(resourceCatalog);
    }

    public void gameLoop() {

        ArrayList<Renderable> thingsToRender = updateWorld();
        renderFrame(thingsToRender);
    }

    public void renderFrame(ArrayList<Renderable> thingsToRender) {

        beginFrame(resourceCatalog);

        CommandList cmdList = createCommandList(
                resourceCatalog
        );

        cmdList.setPipeline(
                renderable.getPipeline()
        );

        for(Renderable renderable : thingsToRender) {

            cmdList.setMesh(
                    renderable.getMesh()
            );
        }

        cmdList.setPipeline(
                lightPipelineId
        );

        endFrame(resourceCatalog);
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

        //app.destroyRendering(app.renderCatalog);

    }

}