

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
 * ���д���򣬸��ƶ���ļ��У�������
 * 1���ж������·���Ƿ����ļ���
 * 2.������ļ��н��еݹ鴦��
 * 3.������ļ�ֱ�Ӹ���
 */
public class Copy {
	public static void main(String[] args) {
		// �������¼��
		Scanner sc = new Scanner(System.in);
		// �����û�����Դ�ļ�·��
		System.out.println("������Ҫ���Ƶ��ļ���ȫ·����");
		// ����һ��Դ�ļ���File ����
		File ff = new File(sc.nextLine());
		// �����û�����Ŀ��·��
		System.out.println("������Ŀ��·����");
		// ����һ��Ŀ��·����File����
		File ft = new File(sc.nextLine());

		// ���ԭ·�����ļ���
		if (ff.isDirectory()) {
			File ffj = ff;
			CopyFolder(ffj, ft);
		
		// ���ԭ·�����ļ�
		} else if (ff.isFile()) {
			File ffw = ff;
			CopyFile(ffw,ft);
		} else {
			throw new RuntimeException("�ļ�������������!");
		}

		System.out.println("�ļ����Ƴɹ���");
	}

	// ����һ�������ļ��еķ�ʽ
	public static void CopyFolder(File ffj, File ft) {
			// ��Ŀ��·���´���һ�����ļ���
			File newFile = new File(ft.getAbsolutePath() + "\\" + ffj.getName());
			boolean b = newFile.mkdirs();
			if(!b){
				System.out.println(b+"���ļ�δ������"+newFile.toString());
			}
			// ��ȡԴ�ļ��������е����ļ��м��ļ����ļ���
			File[] listFiles = ffj.listFiles(new MyFilter());
			if (listFiles != null) {
				// ����forѭ���õ�ÿһ��File����
				for (File thisFile : listFiles) {
					// �ж��Ƿ����ļ���
					if (thisFile.isDirectory()){
						String nameto = ft.getAbsolutePath() + "\\" + newFile.getName();
						String namefrom = ffj.getAbsolutePath() + "\\" + thisFile.getName();
						File ffj1 = new File(namefrom);//Ҫ�ֿ�д�������ֽڸ�ֵ���Լ�
						File ft1 = new File(nameto);//Ҫ�ֿ�д�������ֽڸ�ֵ���Լ�
						CopyFolder(ffj1, ft1);
					}else {
						// �����ֽ�������
						FileInputStream fis = null;
						BufferedInputStream bis = null;
						FileOutputStream fos = null;
						BufferedOutputStream bos = null;
						try {
							fis = new FileInputStream(thisFile.getAbsolutePath());
							bis = new BufferedInputStream(fis);
							fos = new FileOutputStream(newFile.getAbsolutePath()+"\\"+thisFile.getName());
							bos = new BufferedOutputStream(fos);
							// ����һ���ֽ�����
							byte[] bytes = new byte[1024*100];
							// ����һ����������¼ÿ�ζ�ȡ���ֽ���
							int len = -1;
							// ��whileѭ�����ж�д
							while ((len = bis.read(bytes)) != -1) {
								bos.write(bytes, 0, len);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException("���ݴ������������");
							
						} finally {
							try {
								if (fis!= null) {
									fis.close();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException("�ļ��ر�ʧ�ܣ�������");
							} finally {
								try {
									if (fos != null) {
										fos.close();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									throw new RuntimeException("�ļ��ر�ʧ�ܣ�������");
								}
							}
						}
					}
				}

			}

	}
	
	//����һ�������ļ��ķ���
	public static void CopyFile(File ffw, File ft) {
		// �����ֽ�������
					FileInputStream fis = null;
					BufferedInputStream bis = null;
					FileOutputStream fos = null;
					BufferedOutputStream bos = null;
					try {
						fis = new FileInputStream(ffw);
						bis = new BufferedInputStream(fis);
						fos = new FileOutputStream(ft.getAbsolutePath() + "\\" + ffw.getName());
						bos = new BufferedOutputStream(fos);
						// ����һ���ֽ�����
						byte[] b = new byte[1024*100];
						// ����һ����������¼ÿ�ζ�ȡ���ֽ���
						int len = -1;
						// ��whileѭ�����ж�д
						while ((len = bis.read(b)) != -1) {
							bos.write(b, 0, len);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						throw new RuntimeException("�ļ�����ʧ�ܣ�������");
					} finally {
						try {
							if (fis != null) {
								fis.close();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException("�ļ��ر�ʧ�ܣ�������");
						} finally {
							try {
								if (fos != null) {
									fos.close();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException("�ļ��ر�ʧ�ܣ�������");
							}
						}
					}
		
	}
}
