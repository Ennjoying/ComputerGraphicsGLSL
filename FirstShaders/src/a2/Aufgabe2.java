package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// folgende Zeile läd automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		float[] dreiecke={
				-1f,0f,	1.0f,1.0f,1.0f,
				0f,1f,	1.0f,1.0f,1.0f,
				1f,0f,	1.0f,1.0f,1.0f,
				/*-0.5f,-0.5f,1.0f,0.0f,0.0f,
				0.0f,0.5f,0.0f,1.0f,0.0f,
				0.5f,-0.5f,0.0f,0.0f,1.0f,
				0.2f,0.4f,1.0f,0.0f,0.0f,
				-0.3f,-0.7f,0.0f,1.0f,0.0f,
				-0.9f,0.5f,0.0f,0.0f,1.0f*/
		};
		int vao1 = glGenVertexArrays();
		glBindVertexArray(vao1);
		int vbo1 = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo1);
		glBufferData(GL_ARRAY_BUFFER, dreiecke, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT,false, 20, 0); //stride beschreibt die länge einer zeile
		glVertexAttribPointer(1, 3, GL_FLOAT,false, 20, 8);//pointer beschreibt der byte andem man anfängt zu lesen,
																							// in dem fall ignorier die ersten 8 bit (die 2 float coords haben jeweils 4)
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
	}

	@Override
	public void update() {

	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren
		glDrawArrays(GL_TRIANGLES, 0, 3);

	}

	@Override
	public void destroy() {
	}
}
