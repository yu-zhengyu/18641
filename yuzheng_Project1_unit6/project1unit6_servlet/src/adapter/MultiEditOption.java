package adapter;
/**
 * @version  1.0
 * @Date 9/20/2015
 * @author zhengyu
 * 
 * this is interface, which is use to do the multithread operation
 *
 */

public interface MultiEditOption {
	public void updateEditOptionName(String model, String optionset, String option,
			String optionNewName);
	public void updateEditOptionPrice(String mode, String optionset, String option,
			float price);
}
