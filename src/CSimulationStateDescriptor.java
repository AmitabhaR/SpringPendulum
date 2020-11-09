import java.io.*;

public class CSimulationStateDescriptor 
{
	public int nStepNo;   // Step number of this state object. 
	public double fTheta, // Angular displacement.
				  fOmega, // Angular velocity.
				  fAlpha; // Angular acceleration.
	public double fSpringLen, // Length of the spring.
				  fSpringVel, // Velocity of spring.
				  fSpringAcc; // Acceleration of spring.
	
	public CSimulationStateDescriptor(int nStepNo,
									  double fTheta,
									  double fOmega,
									  double fAlpha,
									  double fSpringLen,
									  double fSpringVel,
									  double fSpringAcc)
	{
		// Initialize our object using passed parameters.
		this.nStepNo = nStepNo;
		this.fTheta = fTheta;
		this.fOmega = fOmega;
		this.fAlpha = fAlpha;
		this.fSpringLen = fSpringLen;
		this.fSpringVel = fSpringVel;
		this.fSpringAcc = fSpringAcc;
	}
	
	// Prints the state variables to output file...
	public void printStateObject(FileWriter pWriter) throws IOException
	{ 
		pWriter.write(Double.toString(fTheta) + " " + Double.toString(fOmega) +  " " + Double.toString(fAlpha)
					  + " " + Double.toString(fSpringLen) + " " + Double.toString(fSpringVel) + " " + Double.toString(fSpringAcc));
		pWriter.write("\n"); // Go to next line...
	}
	
	// Copy the state variables from another state object...
	public void copyStateVariables(CSimulationStateDescriptor pState)
	{
		this.nStepNo = pState.nStepNo;
		this.fTheta = pState.fTheta;
		this.fOmega = pState.fOmega;
		this.fAlpha = pState.fAlpha;
		this.fSpringLen = pState.fSpringLen;
		this.fSpringVel = pState.fSpringVel;
		this.fSpringAcc = pState.fSpringAcc;
	}
}
