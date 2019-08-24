package com.xlyd.mybatis.pagehelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlyd.mybatis.pagehelper.dao.BookDao;
import com.xlyd.mybatis.pagehelper.dao.PhotoDao;
import com.xlyd.mybatis.pagehelper.entity.Book;
import com.xlyd.mybatis.pagehelper.entity.Photo;

public class TestPerson {
	private Logger logger = Logger.getLogger(TestPerson.class);

	SqlSession sqlSession = null;

	@Test
	public void test2() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		int size = dao.queryAll().size();
		logger.info(size);

		sqlSession.close();
	}

	@Test
	public void test3() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		PageHelper.startPage(1, 5);
		List<Book> bs = dao.queryAll();
		PageInfo<Book> pi = new PageInfo<Book>(bs);

		bs = pi.getList();
		int pageNum = pi.getPageNum();
		long totalNum = pi.getTotal();

		System.out.println(pageNum);
		System.out.println(totalNum);

		sqlSession.close();
	}

	@Test
	public void test4() {
		sqlSession = SqlSessionUtil.getSqlSession();
		BookDao dao = sqlSession.getMapper(BookDao.class);
		PageHelper.startPage(1, 5);
		List<Book> bs = dao.queryAll();
		PageInfo<Book> pi = new PageInfo<Book>(bs);
		int pageNum = pi.getPageNum();
		System.out.println(pageNum);
		int size = pi.getSize();
		System.out.println(size);
		int pages = pi.getPages();
		System.out.println(pages);
		long total = pi.getTotal();
		System.out.println(total);
		boolean isHasPrePage = pi.isHasPreviousPage();
		System.out.println(isHasPrePage);
		int prePage = pi.getPrePage();
		System.out.println(prePage);
		boolean isHaxNextPage = pi.isHasNextPage();
		System.out.println(isHaxNextPage);
		int nextPage = pi.getNextPage();
		System.out.println(nextPage);
		bs = pi.getList();
		System.out.println(pi);
	}

	@Test
	public void testPicAll() {
		sqlSession = SqlSessionUtil.getSqlSession();
		PhotoDao pDao = sqlSession.getMapper(PhotoDao.class);
		List<Photo> phs = pDao.queryAll();
		System.out.println(phs.get(0).getPic().length);
	}

	@Test
	public void testPicAdd() {
		sqlSession = SqlSessionUtil.getSqlSession();
		PhotoDao pDao = sqlSession.getMapper(PhotoDao.class);

		// String picLoc = "E:/DSC_1759.JPG";
		String picLoc = "E:/kidingme.png";
		Photo ph = new Photo();
		ph.setName("恭子1");
		ph.setPic(getPicByte(picLoc));
		int i = pDao.insert(ph);
		System.out.println(i);
	}

	@Test
	public void testGetPicByte() {
		String picLoc = "E:/kidingme.png";
		byte[] ps = getPicByte(picLoc);
		System.out.println(ps.length);
	}

	@Test
	public void testPicSelect() throws IOException{
		sqlSession = SqlSessionUtil.getSqlSession();
		PhotoDao pDao = sqlSession.getMapper(PhotoDao.class);
		Photo ph = new Photo();
		ph = pDao.selectById(1);
		System.out.println(ph.getPic().length);
		
		File newFile = new File("E:/newkidingme.png");
		FileOutputStream fos = new FileOutputStream(newFile);
		
		byte[] buf = new byte[1000];
		ByteArrayInputStream bis = new ByteArrayInputStream(buf);
		int n =0;
		while((n = bis.read(ph.getPic())) != -1) {
			fos.write(n);
		}
		fos.flush();
		fos.close();
	}

	public static byte[] getPicByte(String fileLoc) {
		byte[] buffer = null;

		try {
			File file = new File(fileLoc);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	// 读取表中图片获取输出流
	public static void readBin2Image(InputStream in, String targetPath) {
		File file = new File(targetPath);
		String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
		if (!file.exists()) {
			new File(path).mkdir();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}