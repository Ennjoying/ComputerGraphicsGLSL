package projekt;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Projekt extends AbstractOpenGLBase {

	Vao octa;
	Vao block;
	Vao pillar;


	//int texWeightId;
	//int colorWeightId;
	private ShaderProgram shaderProgram;
	private ShaderProgram shaderProgramTex;
	int location;
	int locationTex;
	int locationCam;
	int locationCamTex;
	Matrix4 camMat;
	Texture dirtTex;
	Texture augenkrebsTex;
	//set all the handwritten data
	public void createAndSetData(){
		float[] vbo1Vertices = new float[]{
				-0.5f,0f,-0.5f,	//hinten Links
				-0.5f,0f,0.5f,	//vorne links
				0f,1f,0f,		//spitze

				-0.5f,0f,0.5f,	//vorne links
				0.5f,0f,0.5f,	//vorne rechts
				0f,1f,0f,		//spitze

				0.5f,0f,0.5f,	//vorne rechts
				0.5f,0f,-0.5f,	//hinten rechts
				0f,1f,0f,		//spitze

				0.5f,0f,-0.5f,	//hinten rechts
				-0.5f,0f,-0.5f,	//hinten Links
				0f,1f,0f,		//spitze

				-0.5f,0f,0.5f,	//vorne links
				-0.5f,0f,-0.5f,	//hinten Links
				0f,-1f,0f,		//spitze-

				0.5f,0f,0.5f,	//vorne rechts
				-0.5f,0f,0.5f,	//vorne links
				0f,-1f,0f,		//spitze-

				0.5f,0f,-0.5f,	//hinten rechts
				0.5f,0f,0.5f,	//vorne rechts
				0f,-1f,0f,		//spitze-

				-0.5f,0f,-0.5f,	//hinten Links
				0.5f,0f,-0.5f,	//hinten rechts
				0f,-1f,0f,		//spitze-
		};
		float[] vbo1Colors = new float[]{
				0.0f,1.0f,0.0f,
				1.0f,0.0f,0.0f,
				1.0f,1.0f,1.0f,
				1.0f,0.0f,0.0f,
				0.0f,0.0f,1.0f,
				1.0f,1.0f,1.0f,
				0.0f,0.0f,1.0f,
				1.0f,1.0f,0.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,0.0f,
				0.0f,1.0f,0.0f,
				1.0f,1.0f,1.0f,
				1.0f,0.0f,0.0f,
				0.0f,1.0f,0.0f,
				0f,0f,0f,
				0.0f,0.0f,1.0f,
				1.0f,0.0f,0.0f,
				0f,0f,0f,
				1.0f,1.0f,0.0f,
				0.0f,0.0f,1.0f,
				0f,0f,0f,
				0.0f,1.0f,0.0f,
				1.0f,1.0f,0.0f,
				0f,0f,0f,
		};
		float[] vbo1Normals = new float[]{
				-0.5f,0f,-0.5f,	//hinten Links
				-0.5f,0f,0.5f,	//vorne links
				0f,1f,0f,		//spitze

				-0.5f,0f,0.5f,	//vorne links
				0.5f,0f,0.5f,	//vorne rechts
				0f,1f,0f,		//spitze

				0.5f,0f,0.5f,	//vorne rechts
				0.5f,0f,-0.5f,	//hinten rechts
				0f,1f,0f,		//spitze

				0.5f,0f,-0.5f,	//hinten rechts
				-0.5f,0f,-0.5f,	//hinten Links
				0f,1f,0f,		//spitze

				-0.5f,0f,0.5f,	//vorne links
				-0.5f,0f,-0.5f,	//hinten Links
				0f,-1f,0f,		//spitze-

				0.5f,0f,0.5f,	//vorne rechts
				-0.5f,0f,0.5f,	//vorne links
				0f,-1f,0f,		//spitze-

				0.5f,0f,-0.5f,	//hinten rechts
				0.5f,0f,0.5f,	//vorne rechts
				0f,-1f,0f,		//spitze-

				-0.5f,0f,-0.5f,	//hinten Links
				0.5f,0f,-0.5f,	//hinten rechts
				0f,-1f,0f,		//spitze-
		};
		float[] vbo2Vertices = new float[]{
				//rechte seite
				0.5f,0.5f,0.5f, //rechts oben vorne
				0.5f,-0.5f,0.5f, //rechts unten vorne
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,-0.5f,0.5f, //rechts unten vorne
				0.5f,-0.5f,-0.5f, //rechts unten hinten

				//hinter seite
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,-0.5f,-0.5f, //rechts unten hinten
				-0.5f,0.5f,-0.5f, //links oben hinten
				-0.5f,0.5f,-0.5f, //links oben hinten
				0.5f,-0.5f,-0.5f, //rechts unten hinten
				-0.5f,-0.5f,-0.5f, //links unten hinten
				//linke seite
				-0.5f,0.5f,-0.5f, //links oben hinten
				-0.5f,-0.5f,-0.5f, //links unten hinten
				-0.5f,0.5f,0.5f, //links oben vorne
				-0.5f,0.5f,0.5f, //links oben vorne
				-0.5f,-0.5f,-0.5f, //links unten hinten
				-0.5f,-0.5f,0.5f, //links unten vorne
				//vorder seite
				-0.5f,0.5f,0.5f, //links oben vorne
				-0.5f,-0.5f,0.5f, //links unten vorne
				0.5f,0.5f,0.5f, //rechts oben vorne
				0.5f,0.5f,0.5f, //rechts oben vorne
				-0.5f,-0.5f,0.5f, //links unten vorne
				0.5f,-0.5f,0.5f, //rechts unten vorne
				//obere seite
				-0.5f,0.5f,-0.5f, //links oben hinten
				-0.5f,0.5f,0.5f, //links oben vorne
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,0.5f,-0.5f, //rechts oben hinten
				-0.5f,0.5f,0.5f, //links oben vorne
				0.5f,0.5f,0.5f, //rechts oben vorne
				//untere seite
				0.5f,-0.5f,0.5f, //rechts unten vorne
				-0.5f,-0.5f,0.5f, //links unten vorne
				-0.5f,-0.5f,-0.5f, //links unten hinten
				0.5f,-0.5f,0.5f, //rechts unten vorne
				-0.5f,-0.5f,-0.5f, //links unten hinten
				0.5f,-0.5f,-0.5f, //rechts unten hinten
		};
		float[] vbo2Colors = new float[]{
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,

				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,

				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,

				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,

				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,

				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,

				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
				1.0f,1.0f,1.0f,
		};
		float[] vbo2Normals = new float[]{
				//rechte seite
				0.5f,0.5f,0.5f, //rechts oben vorne
				0.5f,-0.5f,0.5f, //rechts unten vorne
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,-0.5f,0.5f, //rechts unten vorne
				0.5f,-0.5f,-0.5f, //rechts unten hinten

				//hinter seite
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,-0.5f,-0.5f, //rechts unten hinten
				-0.5f,0.5f,-0.5f, //links oben hinten
				-0.5f,0.5f,-0.5f, //links oben hinten
				0.5f,-0.5f,-0.5f, //rechts unten hinten
				-0.5f,-0.5f,-0.5f, //links unten hinten
				//linke seite
				-0.5f,0.5f,-0.5f, //links oben hinten
				-0.5f,-0.5f,-0.5f, //links unten hinten
				-0.5f,0.5f,0.5f, //links oben vorne
				-0.5f,0.5f,0.5f, //links oben vorne
				-0.5f,-0.5f,-0.5f, //links unten hinten
				-0.5f,-0.5f,0.5f, //links unten vorne
				//vorder seite
				-0.5f,0.5f,0.5f, //links oben vorne
				-0.5f,-0.5f,0.5f, //links unten vorne
				0.5f,0.5f,0.5f, //rechts oben vorne
				0.5f,0.5f,0.5f, //rechts oben vorne
				-0.5f,-0.5f,0.5f, //links unten vorne
				0.5f,-0.5f,0.5f, //rechts unten vorne
				//obere seite
				-0.5f,0.5f,-0.5f, //links oben hinten
				-0.5f,0.5f,0.5f, //links oben vorne
				0.5f,0.5f,-0.5f, //rechts oben hinten
				0.5f,0.5f,-0.5f, //rechts oben hinten
				-0.5f,0.5f,0.5f, //links oben vorne
				0.5f,0.5f,0.5f, //rechts oben vorne
				//untere seite
				0.5f,-0.5f,0.5f, //rechts unten vorne
				-0.5f,-0.5f,0.5f, //links unten vorne
				-0.5f,-0.5f,-0.5f, //links unten hinten
				0.5f,-0.5f,0.5f, //rechts unten vorne
				-0.5f,-0.5f,-0.5f, //links unten hinten
				0.5f,-0.5f,-0.5f, //rechts unten hinten
		};
		float[] vbo2UVs = new float[]{
				//rechte seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//hinter seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//linke seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//vorder seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//obere seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//untere seite
				1f,0f,	1f,1f, 0f,1f,
				1f,0f,	0f,1f, 1f,1f,
				/*//rechte seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//hinter seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//linke seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//vorder seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//obere seite
				0f,0f,	0f,1f, 1f,0f,
				1f,0f,	0f,1f, 1f,1f,
				//untere seite
				1f,0f,	1f,1f, 0f,1f,
				1f,0f,	0f,1f, 1f,1f,*/

		};
		octa.setVertices(vbo1Vertices);
		octa.setColors(vbo1Colors);
		octa.setNormals(vbo1Normals);
		block.setVertices(vbo2Vertices);
		block.setColors(vbo2Colors);
		block.setNormals(vbo2Normals);
		block.setUVs(vbo2UVs);

	}

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);

	}

	public BufferedReader readFile(String path) throws FileNotFoundException {
		BufferedReader buffReader = new BufferedReader(new FileReader(new File(path)));
		return buffReader;
	}
	public void readobjFile(String path, Vao mesh) throws IOException {
		//obj format:
		// V = vertices
		// vn= vertice normal
		// f = face 1. zahl jedes blocks ist indice der vertices des dreiecks, 2. zahl sind indices für UVs des dreiecks, 3. der normal
		// vt= UVs

		BufferedReader objFile = readFile(path);
		//load file

		String zeile;
		ArrayList<Float> verts = new ArrayList<>();
		ArrayList<Float> normals = new ArrayList<>();
		ArrayList<Float> UVs= new ArrayList<>();
		ArrayList<Integer> vertsIndices = new ArrayList<>();
		ArrayList<Integer> normalsIndices = new ArrayList<>();
		ArrayList<Integer> UVsIndices = new ArrayList<>();

		//iterate over file until done
		while((zeile = objFile.readLine()) != null) {

			if (zeile.startsWith("v ")) {
				verts.add(Float.parseFloat(zeile.split(" ")[1] + "f"));
				verts.add(Float.parseFloat(zeile.split(" ")[2] + "f"));
				verts.add(Float.parseFloat(zeile.split(" ")[3] + "f"));
			}
			if (zeile.startsWith("vn ")) {
				normals.add(Float.parseFloat(zeile.split(" ")[1] + "f"));
				normals.add(Float.parseFloat(zeile.split(" ")[2] + "f"));
				normals.add(Float.parseFloat(zeile.split(" ")[3] + "f"));
			}
			if (zeile.startsWith("vt ")) {
				UVs.add(Float.parseFloat(zeile.split(" ")[1] + "f"));
				UVs.add(Float.parseFloat(zeile.split(" ")[2] + "f"));
			}
			if (zeile.startsWith("f ")) {
				zeile= zeile.substring(2).replace(" ","/");
				vertsIndices.add(Integer.parseInt(zeile.split("/")[0]));
				vertsIndices.add(Integer.parseInt(zeile.split("/")[3]));
				vertsIndices.add(Integer.parseInt(zeile.split("/")[6]));

				UVsIndices.add(Integer.parseInt(zeile.split("/")[1]));
				UVsIndices.add(Integer.parseInt(zeile.split("/")[4]));
				UVsIndices.add(Integer.parseInt(zeile.split("/")[7]));

				normalsIndices.add(Integer.parseInt(zeile.split("/")[2]));
				normalsIndices.add(Integer.parseInt(zeile.split("/")[5]));
				normalsIndices.add(Integer.parseInt(zeile.split("/")[8]));

			}
		}
		float[] sortedVerts = new float[vertsIndices.size()*3];
		float[] sortedNormals = new float[normalsIndices.size()*3];
		float[] sortedUVs = new float[UVsIndices.size()*2];


		//TODO: sort the arrays, 1 index zeigt auf 3 oder 2 werte
		int index;
		int writeIndex=0;
		for (int i = 0; i < vertsIndices.size(); i++) {
			index= vertsIndices.get(i)-1;
			sortedVerts[writeIndex]=verts.get(index*3);
			sortedVerts[writeIndex+1]=verts.get(index*3+1);
			sortedVerts[writeIndex+2]=verts.get(index*3+2);
			writeIndex+=3;
		}
		writeIndex=0;
		for (int i = 0; i < normalsIndices.size(); i++) {
			index= normalsIndices.get(i)-1;
			sortedNormals[writeIndex]=normals.get(index*3);
			sortedNormals[writeIndex+1]=normals.get(index*3+1);
			sortedNormals[writeIndex+2]=normals.get(index*3+2);
			writeIndex+=3;
		}
		writeIndex=0;
		for (int i = 0; i < UVsIndices.size(); i++) {
			index= UVsIndices.get(i)-1;
			sortedUVs[writeIndex]=UVs.get(index*2);
			sortedUVs[writeIndex+1]=UVs.get(index*2+1);
			writeIndex+=2;
		}


		mesh.setVertices(sortedVerts);
		mesh.setNormals(sortedNormals);
		mesh.setUVs(sortedUVs);

	}


	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		shaderProgramTex = new ShaderProgram("projektTex");

		// projection matrix
		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		// diese zeilen returnen die IDs der uniform variablen
		//texWeightId = glGetUniformLocation(shaderProgram.getId(),"texWeight");
		//colorWeightId = glGetUniformLocation(shaderProgram.getId(),"colorWeight");

		// 1. create projectionmatrix
		// 2. get id von uniform variable projectionmatrix
		// 3. übertragung der matrix an den shader mit der vorher durch getuniformlocation bekommenen id
		Matrix4 projectionMatrix = new Matrix4(-1,1);

		glUseProgram(shaderProgram.getId());
		location = glGetUniformLocation(shaderProgram.getId(),"modelmatrix");
		int projectionMatrixID = glGetUniformLocation(shaderProgram.getId(),"projectionmatrix");
		glUniformMatrix4fv(projectionMatrixID, false, projectionMatrix.getValuesAsArray());
		locationCam = glGetUniformLocation(shaderProgram.getId(), "viewmatrix");


		glUseProgram(shaderProgramTex.getId());
		locationTex = glGetUniformLocation(shaderProgramTex.getId(),"modelmatrix");
		int projectionMatrixIDTex = glGetUniformLocation(shaderProgramTex.getId(),"projectionmatrix");
		glUniformMatrix4fv(projectionMatrixIDTex, false, projectionMatrix.getValuesAsArray());
		locationCamTex = glGetUniformLocation(shaderProgramTex.getId(), "viewmatrix");

		octa = new Vao();
		block = new Vao();
		createAndSetData();
		pillar = new Vao();
		try{
			readobjFile("src/res/säule.obj", pillar );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		octa.bindVao();
		octa.vboBuffer(octa.vertices,3,0);
		octa.vboBuffer(octa.colors,3,1);
		octa.vboBuffer(octa.normals,3,2);

		block.bindVao();
		block.vboBuffer(block.vertices,3,0);
		block.vboBuffer(block.colors,3,1);
		block.vboBuffer(block.normals,3,2);
		block.vboBuffer(block.UVs,2,3);

		pillar.bindVao();
		pillar.vboBuffer(pillar.vertices,3,0);
		pillar.colors = new float[pillar.vertices.length];
		//Arrays.fill(pillar.colors, (float)Math.random());
		for (int i = 0; i < pillar.colors.length; i++) {
			pillar.getColors()[i]=(float)Math.random();
		}
		pillar.vboBuffer(pillar.colors,3,1);
		pillar.vboBuffer(pillar.normals,3,2);
		pillar.vboBuffer(pillar.UVs,3,3);


		// Herr Lenz hat gesagt das passt für folgende bedingung:
		// Besonderheiten von Mipmapping erkennbar
		//Over-/Undersampling erkennbar
		//Bilineare Filter vs. ungefiltert

		dirtTex = new Texture("dirt.png", 3); //"grass_block_side.png" , "grass_block_top.png"
		glBindTexture(GL_TEXTURE_2D, dirtTex.getId());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		augenkrebsTex = new Texture("feinesGitter.png", 5);
		glBindTexture(GL_TEXTURE_2D, augenkrebsTex.getId());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR); //last param es wird linear interpoliert auf der Textur und zwischen den mipmap stufen nearest neighbor verwendet


		// settet ein paar einstellungen für die tetxure
		//glTexParameterIi(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		//glTexParameterIi(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren

	}

	float rotaV = 1f;
	float transV = 0;
	float scaleV = 0.5f;
	float deltaT = 0.05f;
	float deltaS = 0.005f;
	@Override
	public void update() {
		// wird 30x pro sekunde aufgerufen
		// Transformation durchführen (Matrix anpassen)
		rotaV +=0.01f;
		transV += deltaT;
		scaleV += deltaS;
		if(transV > 4 || transV < -4) deltaT *=-1;
		if(scaleV > 1.5 || scaleV < 0.5) deltaS *=-1;

		octa.modelmatrix[0] = new Matrix4().rotateY(rotaV /2).translate(0,0,-7);
		pillar.modelmatrix[0] = new Matrix4().rotateY(rotaV*5).rotateX(-rotaV).rotateZ((float)Math.toRadians(45)+rotaV /2).translate(0,0,-7);
		pillar.modelmatrix[1] = new Matrix4().rotateY(rotaV*5).scale(scaleV).rotateX(-rotaV).rotateZ((float)Math.toRadians(135)+rotaV /2).translate(0,0,-7);
		pillar.modelmatrix[2] = new Matrix4().rotateY(rotaV*5).rotateX(rotaV).rotateZ((float)Math.toRadians(225)+rotaV /2).translate(0,0,-7);
		pillar.modelmatrix[3] = new Matrix4().rotateY(rotaV*5).scale(scaleV).rotateX(rotaV).rotateZ((float)Math.toRadians(-45)+rotaV /2).translate(0,0,-7);
		block.modelmatrix[0] = new Matrix4().rotateX(rotaV).translate(3f, transV /3,0f).multiply(octa.modelmatrix[0]);
		block.modelmatrix[1] = new Matrix4().rotateX(rotaV).translate(3f,transV /3,0f).rotateY((float)Math.toRadians(90)).multiply(octa.modelmatrix[0]);
		block.modelmatrix[2] = new Matrix4().rotateX(rotaV).translate(-3f,transV /3,0f).multiply(octa.modelmatrix[0]);
		block.modelmatrix[3] = new Matrix4().rotateX(rotaV).translate(-3f,transV /3,0f).rotateY((float)Math.toRadians(90)).multiply(octa.modelmatrix[0]);
		camMat=new Matrix4().translate(0,0,0);

		//backface culling fix
		octa.modelmatrix[0].values[15]=-1;
		pillar.modelmatrix[0].values[15]=-1;
		pillar.modelmatrix[1].values[15]=-1;
		pillar.modelmatrix[2].values[15]=-1;
		pillar.modelmatrix[3].values[15]=-1;
		block.modelmatrix[0].values[15]=-1;
		block.modelmatrix[1].values[15]=-1;
		block.modelmatrix[2].values[15]=-1;
		block.modelmatrix[3].values[15]=-1;
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//model matrix
		// Matrix an Shader übertragen
		// VAOs zeichnen

		glUseProgram(shaderProgram.getId());
		glUniformMatrix4fv(locationCam, false, camMat.getValuesAsArray());

		octa.bindVao();
		glUniformMatrix4fv(location, false, octa.modelmatrix[0].getValuesAsArray()); // hiermit befüllt man die variable modelmatrix 1. parameter id der variable, 3. parameter sind die werte
		glDrawArrays(GL_TRIANGLES, 0, octa.vertices.length/3); //anzahl vertices


		pillar.bindVao();
		glUniformMatrix4fv(location, false, pillar.modelmatrix[0].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, pillar.vertices.length/3);

		glUniformMatrix4fv(location, false, pillar.modelmatrix[1].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, pillar.vertices.length/3);

		glUniformMatrix4fv(location, false, pillar.modelmatrix[2].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, pillar.vertices.length/3);

		glUniformMatrix4fv(location, false, pillar.modelmatrix[3].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, pillar.vertices.length/3);


		glUseProgram(shaderProgramTex.getId());
		glUniformMatrix4fv(locationCamTex, false, camMat.getValuesAsArray());

		block.bindVao();
		glBindTexture(GL_TEXTURE_2D, dirtTex.getId());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glUniformMatrix4fv(locationTex, false, block.modelmatrix[0].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, block.vertices.length/3); //anzahl vertices

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST_MIPMAP_NEAREST);
		glUniformMatrix4fv(locationTex, false, block.modelmatrix[1].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, block.vertices.length/3); //anzahl vertices


		glBindTexture(GL_TEXTURE_2D, augenkrebsTex.getId());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glUniformMatrix4fv(locationTex, false, block.modelmatrix[2].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, block.vertices.length/3); //anzahl vertices

		glBindTexture(GL_TEXTURE_2D, augenkrebsTex.getId());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
		glUniformMatrix4fv(locationTex, false, block.modelmatrix[3].getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, block.vertices.length/3); //anzahl vertices



	}

	@Override
	public void destroy() {
	}
}
