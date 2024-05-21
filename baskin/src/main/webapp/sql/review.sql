CREATE TABLE product_view (
	view_no	number	primary key,    -- 번호
	user_id	varchar2(20)	NOT NULL,   --회원아이디
	order_no	varchar2(30)	NOT NULL,   --주문 번호
	product_no	varchar2(20)	NOT NULL,  --제품 번호
	product_img	varchar2(60)	,   --제품 사진
	product_name	varchar2(50)	NOT NULL,   --제품 이름
	product_size	varchar2(20)	NOT NULL,   --제품 사이즈
	product_color	varchar2(30)	NOT NULL,   --제품 색상
	view_like_cnt	number	,   --좋아요
	view_score	number	default 5,  --조회수(클릭수)
	content	varchar2(60)	NOT NULL,    --내용
    view_date date default sysdate    --작성시간
);

CREATE SEQUENCE view_no_seq;

drop SEQUENCE view_no_seq;

drop table product_view;


-- 더미 데이터 삽입
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user01', 'order001', 'prod001', 'img001.png', 'Product 1', 'M', 'Red', 10, 5, 'This is a review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user02', 'order002', 'prod002', 'img002.png', 'Product 2', 'L', 'Blue', 20, 4, 'This is another review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user03', 'order003', 'prod003', 'img003.png', 'Product 3', 'S', 'Green', 5, 3, 'This is a sample review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user04', 'order004', 'prod004', 'img004.png', 'Product 4', 'XL', 'Yellow', 15, 5, 'This is an example review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user05', 'order005', 'prod005', 'img005.png', 'Product 5', 'M', 'Black', 25, 5, 'This is another sample review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user06', 'order006', 'prod006', 'img006.png', 'Product 6', 'L', 'White', 8, 4, 'This is a test review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user07', 'order007', 'prod007', 'img007.png', 'Product 7', 'S', 'Pink', 18, 3, 'This is a nice review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user08', 'order008', 'prod008', 'img008.png', 'Product 8', 'XL', 'Purple', 22, 5, 'This is a detailed review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user09', 'order009', 'prod009', 'img009.png', 'Product 9', 'M', 'Orange', 30, 4, 'This is a thorough review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user10', 'order010', 'prod010', 'img010.png', 'Product 10', 'L', 'Gray', 12, 5, 'This is an insightful review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user11', 'order011', 'prod011', 'img011.png', 'Product 11', 'S', 'Red', 7, 4, 'This is an interesting review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user12', 'order012', 'prod012', 'img012.png', 'Product 12', 'M', 'Blue', 13, 3, 'This is a fascinating review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user13', 'order013', 'prod013', 'img013.png', 'Product 13', 'L', 'Green', 9, 5, 'This is a compelling review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user14', 'order014', 'prod014', 'img014.png', 'Product 14', 'XL', 'Yellow', 17, 4, 'This is a detailed review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user15', 'order015', 'prod015', 'img015.png', 'Product 15', 'M', 'Black', 14, 3, 'This is a comprehensive review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user16', 'order016', 'prod016', 'img016.png', 'Product 16', 'S', 'White', 11, 5, 'This is an in-depth review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user17', 'order017', 'prod017', 'img017.png', 'Product 17', 'L', 'Pink', 19, 4, 'This is a thoughtful review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user18', 'order018', 'prod018', 'img018.png', 'Product 18', 'XL', 'Purple', 16, 3, 'This is a well-written review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user19', 'order019', 'prod019', 'img019.png', 'Product 19', 'M', 'Orange', 20, 5, 'This is a well-detailed review.');
INSERT INTO product_view (view_no, user_id, order_no, product_no, product_img, product_name, product_size, product_color, view_like_cnt, view_score, content) VALUES (view_no_seq.nextval, 'user20', 'order020', 'prod020', 'img020.png', 'Product 20', 'L', 'Gray', 21, 4, 'hello~~~~~~~~~~~');

select * from product_view;




