/**
Main code for RSA encyption and decryption
@author Utsav Poddar
	2014196
*/
//import BigInteger package to handle the numbers which cannot be handled by conventional data types like int and float
import java.math.BigInteger;
//import SecureRandom package for securely generating random numbers(used with ProbablePrime to generate primes also)
//SecureRandom increases the security of code by using special algorithms for generating random numbers. The instances of java.util.Random are not cryptographically secure. Use of Miller-Rabin test to check prime number.
import java.security.SecureRandom;
//the access specifiers of variables inside RSA class made private make sure that security of code is not compromised by attack on the server itself.
class RSA{
	private final static BigInteger one = new BigInteger("1");
	private BigInteger privatekey;
	private BigInteger publickey;
	private BigInteger n;
	RSA(){
		//step 1: Generate two random primes of the order 10^100 (2048 bits mean 617 decimal digits, hence secure. This is also the current industry standard)
		BigInteger p = BigInteger.probablePrime(2048, new SecureRandom());
		BigInteger q = BigInteger.probablePrime(2048, new SecureRandom());
		//step 2: Compute n=p*q. n is used as modulus for both public and private key. Its length is the key length.
		this.n = p.multiply(q);
		//step 3: Compute phi(n), i.e. the Euler Totient Function for n which is coprime to n. phi = (p-1)*(q-1)
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		//step 4: Choose public key "e" such that 1<e<phi(n) and GCD(e,phi(n)) = 1, i.e. they are coprimes. Short bit length and small hamming weight results in more efficiency, most commonly 2^16 - 1 = 65537
		this.publickey = BigInteger.valueOf(65537);
		//check co-prime feature of e and phi
		int flag = 1;
        BigInteger temp = phi.gcd(this.publickey);
        if(temp.equals(one))
            flag = 0;
		while(flag == 1){
		    //repeat steps 1 to 4
            p = BigInteger.probablePrime(2048, new SecureRandom());
            q = BigInteger.probablePrime(2048, new SecureRandom());
            this.n = p.multiply(q);
            phi = p.subtract(one).multiply(q.subtract(one));
            this.publickey = BigInteger.valueOf(65537);
            temp = phi.gcd(this.publickey);
            if(temp.equals(one))
                flag = 0;
		}
		//step 5: Determine d as d = inverse(e) mod(phi(n)), i.e. modular multiplicative inverse of e(mod phi(n)) Based on Euler's theorem
		this.privatekey = publickey.modInverse(phi);
	}
	//method for encrypting the message. Encrypted message is: c = m^publickey modulo n
	//made public so any client can access this function
	public BigInteger encrypt(BigInteger message){
		return message.modPow(publickey, n);
	}
	//method for decrypting the message. Decrypted message is: m = c^privatekey modulo n
	//the safety of this is ensured as its access type is default and hence it cannot be accessed by any client placed in another package.
	BigInteger decrypt(BigInteger crypted){
		return crypted.modPow(privatekey, n);
	}
	//method to print the keys, note that it overrides the toString method of object class
	public String toString(){
		String s = "";
		s+="Public key is : "+ this.publickey + "\n";
		s+="Private key is : "+ this.privatekey + "\n";
		s+="N is : "+ this.n + "\n";
		return s;
	}
}
