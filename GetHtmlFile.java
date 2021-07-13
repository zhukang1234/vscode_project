import java.io.*;//File FileOutputStream BufferedOutputStream InputStream IOException
import java.net.*;//URI URL URISyntaxException
public class GetHtmlFile {
	public static void main(String[] s) throws IOException, URISyntaxException {
		URL url= new URL("http://www.swu.edu.cn");
		InputStream is = url.openStream();
		
		File file = new File(new URI("file:////Users/zhukang/Downloads/swu.txt"));
		// 或File file = new File("file:///E:/javaProg/string/swu.txt");
		FileOutputStream fos = new FileOutputStream(file); 
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int ch;
		while((ch=is.read())!=-1)
		{
			bos.write(ch);
			//bos.flush();
			System.out.print((char)ch);
		}
		bos.close();
	}
}

