package hash;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import util.CryptoTools;

public class RSA_Hash {
	public static void main(String[] args) throws Exception{
		BigInteger one = new BigInteger("1");
		BigInteger nAlice = new BigInteger("171024704183616109700818066925197841516671277");
		BigInteger eAlice = new BigInteger("1571");
		BigInteger pBob = new BigInteger("98763457697834568934613");
		BigInteger qBob = new BigInteger("8495789457893457345793");
		BigInteger eBob = new BigInteger("87697");
		BigInteger mBob = new BigInteger("418726553997094258577980055061305150940547956");
		BigInteger sBob = new BigInteger("749142649641548101520133634736865752883277237");
		BigInteger nBob = pBob.multiply(qBob);
		BigInteger phi = (pBob.subtract(one)).multiply(qBob.subtract(one));
		BigInteger dBob = eBob.modInverse(phi);
		
		BigInteger pt = mBob.modPow(dBob, nBob);
		byte[] ptArray = pt.toByteArray();
		for (int x = 0; x < ptArray.length; x++) {
			System.out.print(Character.toString((char)ptArray[x]));
		}
		BigInteger alicePt = sBob.modPow(dBob, nBob);
		BigInteger pt2 = alicePt.modPow(eAlice, nAlice);
		byte[] pt2Array = pt2.toByteArray();
		for (int x = 0; x < pt2Array.length; x++) {
			System.out.print(Character.toString((char)pt2Array[x]));
		}
		System.out.print("\n" + CryptoTools.bytesToHex(pt2Array) + "\n");
		if(Arrays.equals(ptArray, pt2Array)) {
			System.out.println("Same");//message verified
		}
		else {
			System.out.println("not same");
		}
		
	}

}
