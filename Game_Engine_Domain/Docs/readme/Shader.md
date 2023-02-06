
* Goals
    * Generate code to support usage of shaders under a number of graphics apis. 
    * Need to facilitate binding of resources to shaders
    * Generate SHADER boilerplate such as the following?
        -----------------------------------------------------------
        layout(binding = 0) uniform UniformBufferObject {
            mat4 model;
            mat4 view;
            mat4 proj;
        } ubo;

        layout(location = 0) in vec2 inPosition;
        layout(location = 1) in vec3 inColor;

        layout(location = 0) out vec3 fragColor;
        -----------------------------------------------------------
    * Generate actual uniform buffer code!