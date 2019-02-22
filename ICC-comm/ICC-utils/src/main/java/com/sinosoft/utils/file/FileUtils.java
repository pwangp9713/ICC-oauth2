package com.sinosoft.utils.file;

import com.sinosoft.utils.Base64Util;
import com.sinosoft.utils.date.DateUtils;
import com.sinosoft.utils.path.Environment;
import com.sinosoft.utils.string.StringUtils;
import com.sinosoft.utils.string.UUIDUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;





public class FileUtils {

	

	/**
	 * 把文件内容读出一个字串返回
	 * 
	 * @param filePath
	 *            文件路径
	 * @param charaSet
	 *            文件编码
	 * @return
	 * @author zouren
	 */
	public static String readFile(String filePath, String charaSet) {
		File file = new File(filePath);
		StringBuffer str = new StringBuffer();
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				read = new InputStreamReader(new FileInputStream(file),
						charaSet);// 考虑到编码格式
				bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					str.append(lineTxt.trim());
				}
				bufferedReader.close();
				read.close();
			} else {
				file = null;
			}
		} catch (Exception e) {
			return null;
		} finally {
			if (file != null)
				if (read != null) {
					try {
						if (bufferedReader != null) {
							bufferedReader.close();
						}
						read.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		return str.toString();
	}

	

	/**
	 * 将文字写入文件
	 * 
	 * @param str
	 * @param xmlfile
	 * @return
	 */
	public static boolean fileWriteStr(String str, File xmlfile) {
		FileWriter fileWriter = null;
		try {
			if (!xmlfile.exists()) {
				xmlfile.createNewFile();
			}
			fileWriter = new FileWriter(xmlfile);
			fileWriter.write(str);
			fileWriter.flush();
		} catch (IOException e) {
			return false;
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	

	/**
	 * 追加文字
	 * 
	 * @param file
	 * @param content
	 */
	public static void appendTxt(File file, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(file, true);
			writer.write(content);
			writer.write("\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将文件base64码保存为文件
	 * 
	 * @param savePath
	 *            保存图片文件夹路径
	 * @param busys
	 *            业务受理渠道 P10
	 * @param fileName
	 *            文件名称 例 test.jpg
	 * @param base64Code
	 *            文件编码 base64
	 * @return 文件保存路径
	 */
	public static String saveFile(String savePath, String busys,
			String fileName, String base64Code) {

		String saveDate =  DateUtils.getCurrentDateString("yyyyMMddHHmmss");
		FileOutputStream os = null;
		
		if (!fileName.contains(".")) {
			fileName = fileName + ".jpg";
		}

		if (StringUtils.isBlank(savePath)) {// 数据库路径不存在
			savePath = Environment.getServerRealPath() + "resources/upload/";
		}

		String path = savePath + busys + "/" + DateUtils.getCurrentDateString("yyyyMMdd") + "/";// 生成文件路径

		String filePath = path + saveDate + UUIDUtil.getUUID().substring(0, 4)
				+ "_" + fileName;// 生成文件路径
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();// 生成文件夹
		file = new File(filePath);// 生成新文件

		try {
			if (!file.exists())
				file.createNewFile();
			byte[] bytes = Base64Util.decode(base64Code);// 将base64码转为字节
			os = new FileOutputStream(file);
			os.write(bytes);
			os.flush();
		} catch (Exception e) {
			// 处理异常
			if (file.exists())
				file.delete();// 删除文件
			filePath = "";// 路径置为空

			e.printStackTrace();
		} finally {

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return filePath;
	}

	

	/**
	 * 复制文件
	 * 
	 * @param sourcesPath 源文件
	 * @param destPath 新文件
	 * @return true 成功 false 失败
	 */
	public static boolean fileChannelCopy(String sourcesPath, String destPath) {
		boolean flag = false;
		File sources = new File(sourcesPath);
		File dest = new File(destPath);
		if(sources.exists()){
		
			if(dest.exists()){//如果目标文件已存在则删除
				dest.delete();
			}
			try {
				FileInputStream inputStream = new FileInputStream(sources);
				FileOutputStream outputStream = new FileOutputStream(dest);
				FileChannel fileChannepn = inputStream.getChannel();// 得到对应的文件通道
				FileChannel fileChannelout = outputStream.getChannel();// 得到对应的文件通道
				fileChannepn.transferTo(0, fileChannepn.size(), fileChannelout);// 连接两个通道，并且从in通道读取，然后写入out通道
	
				inputStream.close();
				fileChannepn.close();
				outputStream.close();
				fileChannelout.close();
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	public static void main(String[] args) throws IOException {
//		List<String> lines = new ArrayList<String>();
		// lines.add("1");
		// lines.add("123123");
		// lines.add("44444");
		// lines.add("54555");
		// lines.add("66666");
		// FileUtils.writeLines(new File("e:/url/ttt.txt"), lines);
		
	}
}
