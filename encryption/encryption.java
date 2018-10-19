package encryption;

public class encryption 
{
	public static int SEOP = 311;
	public static int MOON = 20;
	
	public static String encryption(String encry)
	{
		String key = "";		
		
		key = key + Integer.parseInt(encry)*SEOP*MOON;
		
		return key;
	}
	
	public static long decryption(String key)
	{
		long result = 0;
		
		result = Integer.parseInt(key) / SEOP / MOON;
		
		return result;
	}
}