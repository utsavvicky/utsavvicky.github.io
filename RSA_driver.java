/**
Driver function for RSA encryption and decryption
@author Utsav Poddar
	2014196
*/
//import BigInteger package to handle the numbers which cannot be handled by conventional data types like int and float
import java.math.BigInteger;
//import SecureRandom package for securely generating random numbers(used with ProbablePrime to generate primes also)
//SecureRandom increases the security of code by using special algorithms for generating random numbers. The instances of java.util.Random are not cryptographically secure.
import java.security.SecureRandom;
import java.util.*;
class main{
	public void systemGeneratedValues(RSA key){
		//method for generating a random input
		//random message generation
		BigInteger message = new BigInteger(4095, new SecureRandom());
		System.out.println("\n\nOriginal Message is: " + message);
		//encrpt the message
		System.out.println("\n\nEncrypting ----------------------------------------------");
		//start timer for judging performance
		final long startTimeEncrypt = System.currentTimeMillis();
		BigInteger encryptedmessage = key.encrypt(message);
		//take end time
		final long endTimeEncrypt = System.currentTimeMillis();
		System.out.println("\n\nEncryption complete, the string is: "+ encryptedmessage);
		System.out.println("\nPress enter key to decrpyt the message.");
        Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();
		//Decrypt the message
		System.out.println("\n\nDecrypting ----------------------------------------------");
		final long startTimeDecrypt = System.currentTimeMillis();
		BigInteger decryptedmessage = key.decrypt(encryptedmessage);

		final long endTimeDecrypt = System.currentTimeMillis();
		System.out.println("\n\nDecryption complete, the message is: "+ decryptedmessage);
		System.out.println("\nThe time took for encryption is: "+ (endTimeEncrypt-startTimeEncrypt)+" miliseconds");
		System.out.println("\nThe time took for decryption is: "+ (endTimeDecrypt-startTimeDecrypt)+" miliseconds");
		System.out.println("\nThe time took for process is: "+ (endTimeEncrypt-startTimeEncrypt+endTimeDecrypt-startTimeDecrypt)+" miliseconds");
	}
	public void userDecidedValues(RSA key){
	    //method for accepting a user input
	    System.out.println("Enter the message you want to encrypt.");
	    Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        byte [] bytes = str.getBytes();
        BigInteger message = new BigInteger(bytes);
		System.out.println("\n\nOriginal Message in Big integer representation is: " + message);
		//encrpt the message
		System.out.println("\n\nEncrypting ----------------------------------------------");
		//start timer for judging performance
		final long startTimeEncrypt = System.currentTimeMillis();
		BigInteger encryptedmessage = key.encrypt(message);
		//take end time
		final long endTimeEncrypt = System.currentTimeMillis();
		System.out.println("\n\nEncryption complete, the string is: "+ encryptedmessage);
		System.out.println("\nPress enter key to decrpyt the message.");
		String temp = scan.nextLine();
		//Decrypt the message
		System.out.println("\n\nDecrypting ----------------------------------------------");
		final long startTimeDecrypt = System.currentTimeMillis();
		BigInteger decryptedmessage = key.decrypt(encryptedmessage);
		final long endTimeDecrypt = System.currentTimeMillis();
		System.out.println("\n\nDecryption complete, the message is: "+ new String(decryptedmessage.toByteArray()));
		System.out.println("\nThe time took for encryption is: "+ (endTimeEncrypt-startTimeEncrypt)+" miliseconds");
		System.out.println("\nThe time took for decryption is: "+ (endTimeDecrypt-startTimeDecrypt)+" miliseconds");
		System.out.println("\nThe time took for process is: "+ (endTimeEncrypt-startTimeEncrypt+endTimeDecrypt-startTimeDecrypt)+" miliseconds");
	}

	public static void main(String [] args){
		//instanciate the keys(prime numbers, modulus, phi value, encryption public key and decryption private key)
		System.out.println("Kindly wait, instanciating variables and starting services.");
		final long startTimeInit = System.currentTimeMillis();
		RSA key = new RSA();
		final long endTimeInit = System.currentTimeMillis();
		System.out.println("Variables set, the values are as follows.");
		System.out.println(key);
		System.out.println("Do you want to encode a message given by you or you want the program to do it for you?\n Enter Y for system generated value and anything for user input.");
		Scanner sc=new Scanner(System.in);
		String c = sc.nextLine();
		main m = new main();
		if(c.equals("Y")){
            m.systemGeneratedValues(key);
		}
		else{
            m.userDecidedValues(key);
		}
		System.out.println("\nThe time took for initialization of variables is: "+ (endTimeInit-startTimeInit)+" miliseconds");
	}
}
