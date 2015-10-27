package util;

import java.io.*;
import java.util.Properties;

import exception.AutoException;
import exception.ExceptionEnumerator;
import exception.Fix1to100;
import model.Automobile;

/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/1/2015
 * 
 * This class is a File IO class. it can read a txt file, and then put all
 * value into Automotive property.In this class file, 
 * we add a function to read data from properties file
 */
public class FileIO {
	
	/**
	 * read in Automobile from Properties object
	 * @param p
	 * @return
	 */
	public Automobile buildAutoObjectProperty(Properties p) {
		Automobile auto = null;
		auto = new Automobile(p.getProperty("CarMake"),
				p.getProperty("CarModel"), Float.parseFloat(p
						.getProperty("BasePrice")));

		String option = "Option";
		String optionValue = "OptionValue";
		String optionPrice = "OptionPrice";

		for (int i = 1; p.getProperty(option + Integer.toString(i)) != null; i++) {
			auto.setOpset(p.getProperty(option + Integer.toString(i)));

			for (char optionValueNum = 'a'; p.getProperty(optionValue
					+ Integer.toString(i) + optionValueNum) != null; optionValueNum++) {

				auto.setOption(
						p.getProperty(option + Integer.toString(i)),
						p.getProperty(optionValue + Integer.toString(i) + optionValueNum),
						Float.parseFloat(p.getProperty(optionPrice + Integer.toString(i)
								+ optionValueNum)));
			}

		}

		return auto;
	}
	
	/**
	 * Read in Automobile from Properties file
	 * @param fileName
	 * @return
	 */
	public Automobile buildAutoObjectProperty(String fileName) {
		Automobile auto = null;
		Properties p= new Properties();
		
		try {
			FileInputStream in = new FileInputStream(fileName);
			p.load(in);
			
			auto = new Automobile(p.getProperty("CarMake"),
					p.getProperty("CarModel"), Float.parseFloat(p
							.getProperty("BasePrice")));

			String option = "Option";
			String optionValue = "OptionValue";
			String optionPrice = "OptionPrice";

			for (int i = 1; p.getProperty(option + Integer.toString(i)) != null; i++) {
				auto.setOpset(p.getProperty(option + Integer.toString(i)));

				for (char optionValueNum = 'a'; p.getProperty(optionValue
						+ Integer.toString(i) + optionValueNum) != null; optionValueNum++) {

					auto.setOption(
							p.getProperty(option + Integer.toString(i)),
							p.getProperty(optionValue + Integer.toString(i) + optionValueNum),
							Float.parseFloat(p.getProperty(optionPrice + Integer.toString(i)
									+ optionValueNum)));
				}

			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return auto;
	}

	public Automobile buildAutoObject(String filename, Automobile a1)
			throws AutoException {
		a1 = null;
		boolean firstline = true;
		int count = 0;
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					if (firstline) {
						String[] basic = line.split(", ");

						// handle the exception: no base price
						if (basic[1].length() == 0) {
							try {
								throw new AutoException("No base price",
										ExceptionEnumerator.noBasePrice);
							} catch (AutoException e) {
								// TODO Auto-generated catch block
								Fix1to100 fix = new Fix1to100();
								basic[1] = fix.fixFileNoBasePrice();
								System.out.println("The problem is fixed");
								System.out.println("----------------------");
							}
						}
						a1 = new Automobile(basic[0], basic[1],
								Float.parseFloat(basic[2]));
						firstline = false;
					} else {
						// configure the option set
						String[] opsetinfo = line.split("#");
						if (opsetinfo[0].length() == 0) {
							try {
								throw new AutoException(
										"Missing option set name",
										ExceptionEnumerator.noOptionSetNmae);
							} catch (AutoException e) {
								e.printmas();
								Fix1to100 fix = new Fix1to100();
								opsetinfo[0] = fix.fixNoOptionSetName();
								System.out.println("The problem is fixed");
								System.out.println("----------------------");
							}
						}
						a1.setOpset(opsetinfo[0]);
						// configure the option
						for (int i = 0; i < Integer.parseInt(opsetinfo[1]); i++) {
							line = buff.readLine();
							String[] optioninfo = line.split("#");
							if (optioninfo.length != 2
									&& !optioninfo[0].matches("[0-9]+")) {
								try {
									throw new AutoException(
											"Miss option price",
											ExceptionEnumerator.noOptionPrice);
								} catch (AutoException e) {
									e.printmas();
									Fix1to100 fix = new Fix1to100();
									String newprice = fix.fixNoOptionPrice();
									System.out.println("The problem is fixed");
									System.out
											.println("----------------------");
									a1.setOption(opsetinfo[0], optioninfo[0],
											Float.parseFloat(newprice));
								}
							} else if (optioninfo[0].length() == 0) {
								try {
									throw new AutoException("Miss option name",
											ExceptionEnumerator.noOptionName);
								} catch (AutoException e) {
									e.printmas();
									Fix1to100 fix = new Fix1to100();
									String newname = fix.fixNoOptionName();
									System.out.println("The problem is fixed");
									System.out
											.println("----------------------");
									a1.setOption(opsetinfo[0], newname,
											Float.parseFloat(optioninfo[1]));
								}

							} else {
								a1.setOption(opsetinfo[0], optioninfo[0],
										Float.parseFloat(optioninfo[1]));
							}
						}
						count++;
					}
				}
			}
			buff.close();
		} catch (FileNotFoundException e) {
			throw new AutoException("File doesn't found",
					ExceptionEnumerator.fileNotFound);
		} catch (IOException e) {
			System.out.println("Error ­­ " + e.toString());
		}
		return a1;
	}

	// serialize the object
	public void serializeAuto(Automobile a1) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("auto.ser"));
			out.writeObject(a1);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// deserialize the object
	public Automobile DeserializeAuto(String filename) {
		Automobile a1 = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"auto.ser"));
			a1 = (Automobile) in.readObject();
			in.close();
		} catch (Exception e) {
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return a1;
	}

}
