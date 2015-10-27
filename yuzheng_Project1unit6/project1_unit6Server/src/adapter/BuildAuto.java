package adapter;

import server.AutoServer;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/14/2015
 * 
 * This is a interfece, extends the ProxyAutomobile class, but it has not implment
 * anything.
 */ 

public class BuildAuto extends ProxyAutomobile 
implements CreateAuto, UpdateAuto, FixAuto, MultiEditOption, AutoServer, DataBaseAuto{
	
}
