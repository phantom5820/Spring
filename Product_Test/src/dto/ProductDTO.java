package dto;

public class ProductDTO {
	private String pno;
	private String pname;
	private int price;
	private int ea;
	private String maker;
	
	
	public ProductDTO(String pno, String pname, int price, int ea, String maker) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.price = price;
		this.ea = ea;
		this.maker = maker;
	}


	public String getPno() {
		return pno;
	}


	public void setPno(String pno) {
		this.pno = pno;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getMaker() {
		return maker;
	}


	public void setMaker(String maker) {
		this.maker = maker;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getEa() {
		return ea;
	}


	public void setEa(int ea) {
		this.ea = ea;
	}


	@Override
	public String toString() {
		return "ProductDTO [pno=" + pno + ", pname=" + pname + ", maker=" + maker + ", price=" + price + ", ea=" + ea
				+ "]";
	}
	
	
}
