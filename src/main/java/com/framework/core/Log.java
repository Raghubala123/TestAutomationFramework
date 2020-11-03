package com.framework.core;
import org.apache.log4j.Logger;

/**
 * This class is used to initialize the log files for debugging the automation scripts.
*/

public class Log {

	static Logger log=null;
	
	/**
	 * Creates an instance of the log file.
	 *  @version 1.0
	 *  @return Logger
	 *  */
	public static Logger getLogInstance()
	{	
		try {
			if(log==null)
			{
				log= Logger.getLogger(Log.class.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return log;	
	}


}
