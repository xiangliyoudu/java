package com.xlyd.mybatis.pagehelper.entity;

public class Book {
	private int bookId;
	private String bookName;
	private double bookPrice;
	private int bookSum;
	private String bookPic;

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName
				+ ", bookPrice=" + bookPrice + ", bookSum=" + bookSum
				+ ", bookPic=" + bookPic + "]";
	}

	public Book() {
		super();
	}

	public Book(int bookId, String bookName, double bookPrice, int bookSum,
			String bookPic) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookSum = bookSum;
		this.bookPic = bookPic;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookSum() {
		return bookSum;
	}

	public void setBookSum(int bookSum) {
		this.bookSum = bookSum;
	}

	public String getBookPic() {
		return bookPic;
	}

	public void setBookPic(String bookPic) {
		this.bookPic = bookPic;
	}

}
