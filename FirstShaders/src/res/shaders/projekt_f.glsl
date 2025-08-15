#version 330
out vec3 colorf;
in vec3 vertex;
in vec3 color;
in vec3 normal;
in vec2 UVs;
uniform sampler2D sampler;
//uniform float texWeight;
//uniform float colorWeight;
vec3 normalizedNormal= normalize(normal);

vec4 position = vec4(vertex, 1);
float aLight=0.1;
float dLightIntensity=1;
vec3 lightsrc=vec3(0,0,10);
vec3 L =normalize(lightsrc-vertex);
vec3 V = normalize(-vertex);
vec3 R = reflect(L , normalizedNormal);

void main(){

    vec4 texel = texture(sampler, UVs);
    float light = aLight + dLightIntensity*max(dot(L ,normalizedNormal),0) + pow( max(dot( R , V ),0),20) ;
    //colorf=color*texel.rgb*light;
    colorf=color*light;
}