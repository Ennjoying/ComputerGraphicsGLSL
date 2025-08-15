package projekt;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {
	float[] values = new float[16];
	/*	x=		0 1 2  3

		y=0 	0 4 8  12
	* 	y=1 	1 5 9  13
	* 	y=2 	2 6 10 14
	* 	y=3 	3 7 11 15
	* */
	public Matrix4() {
		// TODO mit der Identitätsmatrix initialisieren
		this.values[0]=1;
		this.values[5]=1;
		this.values[10]=1;
		this.values[15]=1;
	}

	public Matrix4(Matrix4 toCopyFrom) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren
		System.arraycopy(toCopyFrom.values, 0, this.values, 0, values.length);

	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzufügen
		this.values[0]=1;
		this.values[5]=1;
		this.values[10]= (-far-near)/(far-near);
		this.values[11]=-1;
		this.values[14]= (-near * 2 * far)/(far - near);

	}

	public Matrix4 multiply(Matrix4 other) {
		// TODO hier Matrizenmultiplikation "this = other * this" einfügen
		float[] tempvalues = new float[4];
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				tempvalues[y]= other.values[y] * this.values[x*4] +
								other.values[y+4] * this.values[x*4+1] +
								other.values[y+8] * this.values[x*4+2] +
								other.values[y+12] * this.values[x*4+3];
			}
			System.arraycopy(tempvalues, 0, this.values, x*4, 4);
		}
		return this;
	}
	/*		x=	0 1 2  3

		y=0 	0 4 8  12
	* 	y=1 	1 5 9  13
	* 	y=2 	2 6 10 14
	* 	y=3 	3 7 11 15
	* */

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzufügen
		Matrix4 tempMat = new Matrix4();
		tempMat.values[12]=x;
		tempMat.values[13]=y;
		tempMat.values[14]=z;
		this.multiply(tempMat);
		return this;
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichmäßige Skalierung um Faktor "uniformFactor" zu this hinzufügen
		Matrix4 tempMat = new Matrix4();
		tempMat.values[0]=uniformFactor;
		tempMat.values[5]=uniformFactor;
		tempMat.values[10]=uniformFactor;
		this.multiply(tempMat);
		return this;
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichförmige Skalierung zu this hinzufügen
		Matrix4 tempMat = new Matrix4();
		tempMat.values[0]=sx;
		tempMat.values[5]=sy;
		tempMat.values[10]=sz;
		this.multiply(tempMat);
		return this;
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzufügen
		Matrix4 tempMat = new Matrix4();
		tempMat.values[5]=(float)Math.cos(angle);
		tempMat.values[6]=(float)Math.sin(angle);
		tempMat.values[9]=(float)Math.sin(-angle);
		tempMat.values[10]=(float)Math.cos(angle);
		this.multiply(tempMat);
		return this;
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzufügen
		Matrix4 tempMat = new Matrix4();
		tempMat.values[0]=(float)Math.cos(angle);
		tempMat.values[2]=(float)Math.sin(angle);
		tempMat.values[8]=(float)Math.sin(-angle);
		tempMat.values[10]=(float)Math.cos(angle);
		this.multiply(tempMat);
		return this;
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzufügen
		Matrix4 tempMat = new Matrix4();
		tempMat.values[0]=(float)Math.cos(angle);
		tempMat.values[1]=(float)Math.sin(angle);
		tempMat.values[4]=(float)Math.sin(-angle);
		tempMat.values[5]=(float)Math.cos(angle);
		this.multiply(tempMat);
		return this;
	}

	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gefüllt) herausgeben
		return this.values;
	}
}
