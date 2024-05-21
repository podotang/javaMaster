package co.yedam.vo;

import lombok.Data;

@Data
public class ProductVO {
	private String prodCode;
	private String prodName;
	private String prodDesc;
	private int price;
	private int offPrice;
	private String likeIt;
	private	String prodImage;
}
