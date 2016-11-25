

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
 * 请编写程序，复制多层文件夹，并测试
 * 1、判断输入的路径是否是文件夹
 * 2.如果是文件夹进行递归处理
 * 3.如果是文件直接复制
 */
public class Copy {
	public static void main(String[] args) {
		// 定义键盘录入
		Scanner sc = new Scanner(System.in);
		// 提醒用户输入源文件路径
		System.out.println("请输入要复制的文件的全路径：");
		// 创建一个源文件的File 对象
		File ff = new File(sc.nextLine());
		// 提醒用户输入目的路径
		System.out.println("请输入目的路径：");
		// 创建一个目的路径的File对象
		File ft = new File(sc.nextLine());

		// 如果原路径是文件夹
		if (ff.isDirectory()) {
			File ffj = ff;
			CopyFolder(ffj, ft);
		
		// 如果原路径是文件
		} else if (ff.isFile()) {
			File ffw = ff;
			CopyFile(ffw,ft);
		} else {
			throw new RuntimeException("文件不存在请重试!");
		}

		System.out.println("文件复制成功！");
	}

	// 定义一个复制文件夹的方式
	public static void CopyFolder(File ffj, File ft) {
			// 在目的路径下创建一个该文件夹
			File newFile = new File(ft.getAbsolutePath() + "\\" + ffj.getName());
			boolean b = newFile.mkdirs();
			if(!b){
				System.out.println(b+"该文件未创建："+newFile.toString());
			}
			// 获取源文件夹下所有的子文件夹及文件的文件名
			File[] listFiles = ffj.listFiles(new MyFilter());
			if (listFiles != null) {
				// 调用for循环得到每一个File对象
				for (File thisFile : listFiles) {
					// 判断是否是文件夹
					if (thisFile.isDirectory()){
						String nameto = ft.getAbsolutePath() + "\\" + newFile.getName();
						String namefrom = ffj.getAbsolutePath() + "\\" + thisFile.getName();
						File ffj1 = new File(namefrom);//要分开写，不能字节赋值给自己
						File ft1 = new File(nameto);//要分开写，不能字节赋值给自己
						CopyFolder(ffj1, ft1);
					}else {
						// 定义字节流对象
						FileInputStream fis = null;
						BufferedInputStream bis = null;
						FileOutputStream fos = null;
						BufferedOutputStream bos = null;
						try {
							fis = new FileInputStream(thisFile.getAbsolutePath());
							bis = new BufferedInputStream(fis);
							fos = new FileOutputStream(newFile.getAbsolutePath()+"\\"+thisFile.getName());
							bos = new BufferedOutputStream(fos);
							// 创建一个字节数组
							byte[] bytes = new byte[1024*100];
							// 创建一个变量，记录每次读取的字节数
							int len = -1;
							// 用while循环进行读写
							while ((len = bis.read(bytes)) != -1) {
								bos.write(bytes, 0, len);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException("数据传输错误，请重试");
							
						} finally {
							try {
								if (fis!= null) {
									fis.close();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException("文件关闭失败，请重试");
							} finally {
								try {
									if (fos != null) {
										fos.close();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									throw new RuntimeException("文件关闭失败，请重试");
								}
							}
						}
					}
				}

			}

	}
	
	//定义一个复制文件的方法
	public static void CopyFile(File ffw, File ft) {
		// 定义字节流对象
					FileInputStream fis = null;
					BufferedInputStream bis = null;
					FileOutputStream fos = null;
					BufferedOutputStream bos = null;
					try {
						fis = new FileInputStream(ffw);
						bis = new BufferedInputStream(fis);
						fos = new FileOutputStream(ft.getAbsolutePath() + "\\" + ffw.getName());
						bos = new BufferedOutputStream(fos);
						// 创建一个字节数组
						byte[] b = new byte[1024*100];
						// 创建一个变量，记录每次读取的字节数
						int len = -1;
						// 用while循环进行读写
						while ((len = bis.read(b)) != -1) {
							bos.write(b, 0, len);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						throw new RuntimeException("文件传输失败，请重试");
					} finally {
						try {
							if (fis != null) {
								fis.close();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException("文件关闭失败，请重试");
						} finally {
							try {
								if (fos != null) {
									fos.close();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException("文件关闭失败，请重试");
							}
						}
					}
		
	}
}
