package br.com.restassured.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Rectangle implements Serializable {

	private static final long serialVersionUID = 1L;
	private double height;
	private double width;

	public Rectangle(double height, double width) {
		this.height = height;
		this.width = width;
	}

	public double Area() {
		return height * width;
	}

	public double Perimeter() {
		return 2 * (height + width);
	}

	public static void SerializeToFile(Object classObject, String fileName) {
		try {

			FileOutputStream fileStream = new FileOutputStream(fileName);

			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

			objectStream.writeObject(classObject);

			objectStream.close();
			fileStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object DeSerializeFromFileToObject(String fileName) {
		try {

			FileInputStream fileStream = new FileInputStream(new File(fileName));

			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			Object deserializeObject = objectStream.readObject();

			objectStream.close();

			fileStream.close();

			return deserializeObject;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Rectangle rect = new Rectangle(18, 78);
		SerializeToFile(rect, "rectSerialized");

		Rectangle deSerializedRect = (Rectangle) DeSerializeFromFileToObject("rectSerialized");
		System.out.println("Rect area is " + deSerializedRect.Area());
	}
}