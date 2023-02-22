package util;

public class UnitTests {
	
	//Unit test to see if missed our target by more than a full frame very basic and
	// don't use JUNIT just my own version to show you an example
	public static void CheckFrameRate(long TargetTime,long DeliveredTime,int TargetFPS)
	{
		int TimeBetweenFrames = 1000 / TargetFPS;
		if( (TargetTime - DeliveredTime) > TimeBetweenFrames)
		{
		   System.out.println("FPS failure by 10 m");
			 System.out.println("Frame was late by  "+ (TargetTime - DeliveredTime) + " ms");
			//Write out to log file 
		}
	}

}
