package util;

//Modified from Graphics 3033J course point class  by Abey Campbell
public class Point3f {

	private float x;
	private float y;
	private float z;
	
	private int boundary = 1100;
	
	
	// default constructor
	public Point3f() { 
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	
	//initializing constructor
	public Point3f(float x, float y, float z) { 
		this.setX(x);
		this.setY(y);
		this.setZ(z); 
	}
	
	private void setBoundary(int boundary) {
		this.boundary = boundary;
	}

	// sometimes for different algorithms we will need to address the point using positions 0 1 2 
	public float getPosition(int position) {
		switch(position) {
		case 0: return getX();
		case 1: return getY();
		case 2: return getZ(); 
		default: return Float.NaN;  
		} 
	}
	
	public String toString() {
		return ("(" + getX() +"," + getY() +"," + getZ() +")");
    }



	 //implement Point plus a Vector and comment what the method does 
	public Point3f PlusVector(Vector3f Additional) {
		return new Point3f(this.getX() + Additional.getX(),
							this.getY() + Additional.getY(),
							this.getZ() + Additional.getZ());
	} 
	
	 //implement Point minus a Vector and comment what the method does 
	public Point3f MinusVector(Vector3f Minus) { 
		return new Point3f(this.getX() - Minus.getX(),
							this.getY() - Minus.getY(),
							this.getZ() - Minus.getZ());
	}
	
	
	/// implement Point - Point  and comment what the method does  
	public Vector3f MinusPoint(Point3f Minus) { 
		return new Vector3f(this.getX()-Minus.getX(),
							this.getY()-Minus.getY(),
							this.getZ()-Minus.getZ());
	}
	
	 //Use for direct application of a Vector 
	public void ApplyVector(Vector3f vector) { 
		 setX(CheckBoundary(this.getX()+vector.getX()));
		 setY(CheckBoundary(this.getY()-vector.getY()));
		 setZ(CheckBoundary(this.getZ()-vector.getZ())); 
	}

	private float CheckBoundary(float f) {
		if (f < 0) f = 0.0f;
		if (f > boundary)f = (float) boundary;
		return f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	
	  
	  
	 // Remember point + point  is not defined so we not write a method for it.  
}

/*................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
....................................=?7777+.....................................
.............................,8MMMMMMMMMMMMMMMMM7...............................
...........................$MMMMMMMMMMMMMMMMMMMMMM7.............................
........................IMMMMMMMMMMMMMMMMMMMMMMMMMMMM?..........................
......................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN........................
.....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$......................
...................ZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8....................
.................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM...................
................IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$.................
...............=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ................
..............:MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
..............7MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:...............
..............DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ...............
..............MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8...............
..............MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN...............
..............NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO...............
..............$MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMI...............
..............+MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=...............
...............8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN,................
................=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
.................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ..................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD...................
...................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
....................8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM......................
.....................,8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM,.......................
........................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMN?.........................
..........................NMMMMMMMMMMMMMMMMMMMMMMMMMI...........................
.............................$MMMMMMMMMMMMMMMMMMM?..............................
.................................,I$NMMMMMN$?...................................
................................................................................
................................................................................
................................................................................
.......................................................................*/
