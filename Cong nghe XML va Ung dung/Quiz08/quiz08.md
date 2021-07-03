Quiz 08 - XML DOM
===
---

## Yêu cầu

Hãy viết một chương trình (có thể hiện thực bằng bất kỳ ngôn ngữ lập trình theo sở thích)

* Parse dữ liệu XML thành cây DOM
* Truy vấn theo tag
* Truy vấn theo id
* Vẽ cây

---

## Bài làm

1. Ngôn ngữ sử dụng

    * HTML
    * CSS
    * JavaScript

1. Tập tin `Quiz08.rar` bao gồm:
```
+-- Quiz08
|___+-- example_data_xml    thư mục chưa các tập tin xml data mẫu
|___+-- screenshot          thư mục chứa ảnh chụp màn hình
|___+-- source_code         thư mục chứa mã nguồn
|___+-- quiz08.md           tập tin mô tả nội dung bài làm
```

1. Hướng dẫn sử dụng

    * Bước 1: Mở tập tin `source_code/dom.html` bắng trình duyệt web

    ![Bước 1](/screenshot/Capture1.PNG "Bước 1")

    * Bước 2: Nhấn vào nút `Choose File` cửa sổ chọn file sẽ hiện ra, chọn bất kỳ một tập tin xml trong thư mục `example_data_xml`

    ![Bước 2](/screenshot/Capture2.PNG "Bước 2")

    * Bước 3: Nhấn vào nút `Draw DOM Tree` chương trình sẽ vẽ ra cây DOM như hình dưới

    ![Bước 3](/screenshot/Capture3.PNG "Bước 3")

    * Bước 4: Chọn loại truy vấn theo `Tag name` hoặc `id`, nhập giá trị muốn truy vấn sau đó nhấn nút `Search`. Hình dưới thể hiện kết quả của truy vấn in ra tất cả các DOM của các element có tag name là title

    ![Bước 4](/screenshot/Capture4.PNG "Bước 4")
