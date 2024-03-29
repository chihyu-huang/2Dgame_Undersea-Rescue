package util;


//Modified from Graphics 3033J course point class  by Abey Campbell 
public class Vector3f {

	private float x = 0;
	private  float y = 0;
	private  float z = 0;
	
	public Vector3f() {
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	 
	public Vector3f(float x, float y, float z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	 //implement Vector plus a Vector  and comment what the method does  
	public Vector3f PlusVector(Vector3f Additional) {
		return new Vector3f(this.getX()+Additional.getX(),
							this.getY()+Additional.getY(),
							this.getZ()+Additional.getZ());
	} 
	
	 //implement Vector minus a Vector  and comment what the method does  
	public Vector3f MinusVector(Vector3f Minus) {
		return new Vector3f(this.getX()-Minus.getX(),
							this.getY()-Minus.getY(),
							this.getZ()-Minus.getZ());
	}
	
	//implement Vector plus a Point and comment what the method does  
	public Point3f PlusPoint(Point3f Additional)
	{ 
		return new Point3f(this.getX()+Additional.getX(),
							this.getY()+Additional.getY(),
							this.getZ()+Additional.getZ());
	} 
	//Do not implement Vector minus a Point as it is undefined 
	
	//Implement a Vector * Scalar  and comment what the method does    ( we wont create Scalar * Vector due to expediency ) 
	public Vector3f byScalar(float scale )
	{
		return new Vector3f(this.getX()*scale,
							this.getY()*scale,
							this.getZ()*scale);
	}
	
	//implement returning the negative of a Vector  and comment what the method does  
	public Vector3f  NegateVector() {
		return new Vector3f( -this.getX(), -this.getY(), -this.getZ());
	}
	
	//implement getting the length of a Vector    and comment what the method does
	public float length() {
	    return (float) Math.sqrt(getX() * getX() + getY()*getY() + getZ()*getZ());
	}
	
	//implement getting the Normal  of a Vector   and comment what the method does
	public Vector3f Normal() {
		float LengthOfTheVector=  this.length();
		return this.byScalar(1.0f / LengthOfTheVector);
	} 
	
	//implement getting the dot product of Vector.Vector and comment what the method does 

	public float dot(Vector3f v) {
		return ( this.getX()*v.getX() + this.getY()*v.getY() + this.getZ()*v.getZ());
	}
	
	//implement getting the cross product of Vector X Vector and comment what the method does  
	public Vector3f cross(Vector3f v) {
    float u0 = (this.getY()*v.getZ() - getZ()*v.getY());
    float u1 = (getZ()*v.getX() - getX()*v.getZ());
    float u2 = (getX()*v.getY() - getY()*v.getX());
    return new Vector3f(u0,u1,u2);
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
 
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */