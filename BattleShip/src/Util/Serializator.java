package Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Class in charge to Serialize and Deserialize an Object 
 * @author Mauricio Rondon
 * @author Julian Pulido
 */
public class Serializator {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	/**
	 * Write a Serialized Object in a file 
	 * @param object object to serialize
	 * @param fileName file name
	 */
	public void writeObject(Object object, String fileName)
	{
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Deserialize an Object from a file
	 * @param fileName file name
	 * @return auxObj Object deserialized
	 */
	public Object readObject(String fileName)
	{
		Object auxObj = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			auxObj = ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auxObj;
	}
}
