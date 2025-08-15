#version 330
layout(location=0) in vec2 vbo1;
layout(location=1) in vec3 vbocolor;
out vec3 color;

vec2 rotate(vec2 vector, float winkel){
	mat2 rotatematrix=mat2(cos(winkel), -sin(winkel), sin(winkel), cos(winkel));
	return vector * rotatematrix;
}

void main() {
	color = vbocolor;
	gl_Position = vec4(rotate(vbo1, 2), 0.0, 1.0);
}