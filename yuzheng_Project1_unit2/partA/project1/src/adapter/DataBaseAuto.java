package adapter;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 10/16/2015
 * This is a interface, extends the ProxyAutomobile class, but it has not implment
 * anything.
 *
 */

public interface DataBaseAuto {
	public void creatDBAuto(String filename);

	public void updateDBAutoBasicPrice(String autoName, float price);

	public void deleteDBAuto(String autoname);
}
