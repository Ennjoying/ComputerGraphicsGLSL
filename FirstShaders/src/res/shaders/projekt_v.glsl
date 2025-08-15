#version 330
layout(location=0) in vec3 VboVertex;
layout(location=1) in vec3 VboColor;
layout(location=2) in vec3 VboNormal;
layout(location=3) in vec2 VboUV;

out vec3 vertex;
out vec3 color;
out vec3 normal;
out vec2 UVs;

vec4 position = vec4(VboVertex, 1);

uniform mat4 modelmatrix;
uniform mat4 projectionmatrix;
uniform mat4 viewmatrix;

void main() {
    normal =normalize(inverse(transpose(mat3(modelmatrix)))*VboNormal);
    gl_Position= viewmatrix*projectionmatrix*modelmatrix*position;
    vertex = mat3(modelmatrix)*VboVertex;
    color = VboColor;
    UVs = VboUV;
}