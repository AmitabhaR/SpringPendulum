import java.util.*;
import java.io.*;
import java.math.*;

public class MainClass 
{
	static double fSpringConst = 50.0f; // In N/m.
	static final double fGravConst = 9.8f; // In m/s^2.
	static double fBobMass = 0.01f; // In kg.
	static final int nNumOfSteps = 1000;
	static final double fTimeStep = 0.001; // In s.
	
	private static void executeSimulation(FileWriter pWriter,
										  CSimulationStateDescriptor pInitState) throws IOException
	{
		Vector<CSimulationStateDescriptor> pSimStates = new Vector<CSimulationStateDescriptor>(nNumOfSteps);
		CSimulationStateDescriptor pNextState = new CSimulationStateDescriptor(0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
								   pPresentState;
		
		// Print the initial state...
		//pInitState.printStateObject(pWriter);
		pPresentState = pInitState;
		
		for(int nCounter = 0; nCounter <= nNumOfSteps;nCounter++)
		{
			pNextState.nStepNo = pPresentState.nStepNo + 1;
			pPresentState.fSpringAcc = pPresentState.fSpringLen * pPresentState.fOmega * pPresentState.fOmega +
								   fGravConst * Math.cos(pPresentState.fTheta) - 
								   fSpringConst * pPresentState.fSpringLen / fBobMass;
			pPresentState.fAlpha = -(fGravConst * Math.sin(pPresentState.fTheta) + 2 * pPresentState.fSpringVel * pPresentState.fOmega) / pPresentState.fSpringLen;
			pNextState.fOmega = pPresentState.fOmega + pPresentState.fAlpha * fTimeStep;
			pNextState.fTheta = pPresentState.fTheta + pPresentState.fOmega * fTimeStep;
			pNextState.fSpringVel = pPresentState.fSpringVel + pPresentState.fSpringAcc * fTimeStep;
			pNextState.fSpringLen = pPresentState.fSpringLen + pPresentState.fSpringVel * fTimeStep;
			
			pPresentState.printStateObject(pWriter);
			pPresentState.copyStateVariables(pNextState);
		}
	}
	
	
	public static void main(String args[]) throws IOException
	{
		for(int nCounter = 1;nCounter <= 10;nCounter++)
		{
			File pOutFile = new File("SimOut_" + Integer.toString(nCounter) + ".txt"); // Our output file instance...
			FileWriter pWriter = new FileWriter(pOutFile); // Out data writer instance...
			
			pOutFile.createNewFile(); // Create a new file...
	
			fSpringConst = 10 * nCounter;
			//fBobMass = 0.01f * nCounter;
			
			// Invoke the execution of simulation...
			executeSimulation(pWriter, new CSimulationStateDescriptor(0, 0.0f, 0.1f, 0.0f, fBobMass * fGravConst / fSpringConst, 0.0f, 0.0f));
			
			pWriter.flush(); // Flush all the contents to disk...
			pWriter.close(); // Close the writer...
		}
	}
	
	
}
