package util;

import java.io.*;

import exception.AutoException;
import exception.ExceptionEnumerator;
import exception.Fix1to100;
import model.Automobile;
/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/14/2015
 * 
 * This class is a File IO class. it can read a txt file, and then put all value into
 * Automotive property
 */ 
public class FileIO {
	public Automobile buildAutoObject(String filename, Automobile a1) throws AutoException {
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
						a1 = new Automobile(Integer.parseInt(basic[2]),
								basic[0], Float.parseFloat(basic[1]));
						firstline = false;
					} else {
						// configure the option set
						String[] opsetinfo = line.split("#");
						if(opsetinfo[0].length() == 0) {
							try {
								throw new AutoException("Missing option set name",
											ExceptionEnumerator.noOptionSetNmae);
							} catch (AutoException e) {
								e.printmas();
								Fix1to100 fix = new Fix1to100();
								opsetinfo[0] = fix.fixNoOptionSetName();
								System.out.println("The problem is fixed");
								System.out.println("----------------------");
							}
						}
						a1.setOpset(count, opsetinfo[0],
								Integer.parseInt(opsetinfo[1]));
						// configure the option
						for (int i = 0; i < Integer.parseInt(opsetinfo[1]); i++) {
							line = buff.readLine();
							String[] optioninfo = line.split("#");
							if (optioninfo.length != 2 && 
									!optioninfo[0].matches("[0-9]+")) {
								try {
									throw new AutoException("Miss option price", 
											ExceptionEnumerator.noOptionPrice);
								} catch (AutoException e) {
									e.printmas();
									Fix1to100 fix = new Fix1to100();
									String newprice = fix.fixNoOptionPrice();
									System.out.println("The problem is fixed");
									System.out.println("----------------------");
									a1.setOption(count, i, optioninfo[0],
											Float.parseFloat(newprice));
								}
							}
							else if(optioninfo[0].length() == 0) {
								try {
									throw new AutoException("Miss option name", 
											ExceptionEnumerator.noOptionName);
								} catch (AutoException e) {
									e.printmas();
									Fix1to100 fix = new Fix1to100();
									String newname = fix.fixNoOptionName();
									System.out.println("The problem is fixed");
									System.out.println("----------------------");
									a1.setOption(count, i, newname,
											Float.parseFloat(optioninfo[1]));
								} 
								
							}
							else {
								a1.setOption(count, i, optioninfo[0],
										Float.parseFloat(optioninfo[1]));
							}
						}
						count++;
					}
				}
			}
			buff.close();
		}
		catch (FileNotFoundException e) {
			throw new AutoException("File doesn't found", 
					ExceptionEnumerator.fileNotFound);
		}
		catch (IOException e) {
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
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("auto.ser"));
			a1 = (Automobile) in.readObject();
			in.close();
		} catch (Exception e) {
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return a1;
	}

}
