package projekt;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class Vao {

    int vao;
    Matrix4[] modelmatrix = new Matrix4[5];
    float[] vertices;
    float[] colors;
    float[] normals;
    float[] UVs;

    public Vao(){
        this.vao = glGenVertexArrays();
    }

   /* public void initializeVao(){
        this.vao = glGenVertexArrays();
    }*/
    public void bindVao(){
        glBindVertexArray(this.vao);
    }

    public int vboBuffer(float[] data, int size, int index){
        int vboVertices = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertices);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT,false, 0, 0);
        glEnableVertexAttribArray(index);
        return vboVertices;
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public float[] getColors() {
        return colors;
    }

    public void setColors(float[] colors) {
        this.colors = colors;
    }

    public float[] getNormals() {
        return normals;
    }

    public void setNormals(float[] normals) {
        this.normals = normals;
    }

    public float[] getUVs() {
        return UVs;
    }

    public void setUVs(float[] UVs) {
        this.UVs = UVs;
    }
}
