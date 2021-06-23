XML Schema Quiz
=====

---

## Câu hỏi 1

Ràng buộc `totalDigits` có bao gồm dấu chấm `.` hay không?

> Câu trả lời: Không

Ví dụ: Với định nghĩa XSD như sau

```xml
<xs:simpleType name="MyType">
    <xs:restriction base="xs:decimal">
        <xs:totalDigits value="9"/>
        <xs:fractionDigits value="3"/>
    </xs:restriction>
</xs:simpleType>
```

Thì giá trị nào là hợp lệ, giá trị nào là không?

> Câu trả lời
>
> a. `12.34` &#10003;
>
> b. `123456789.123` &#10007;
>
> c. `12345678.12` &#10007;
>
> d. `123456.12` &#10003;
>
> e. `123456.123` &#10003;
>
> f. `123456` &#10003;
>
> g. `123456.` &#10003;

---

## Câu hỏi 2

Viết ràng buộc kiểu dữ liệu nhận là tên (tiếng việt không dấu)

```xml
<xs:element name="Ten">
    <xs:simpleType>
        <xs:restriction base="xs:string">
            <xs:pattern value=".?.?."/>
        </xs:restriction>
    </xs:simpleType>
</xs:element>
```

> Câu trả lời
> ```xml
> <xs:pattern value="([a-zA-Z]+([a-zA-Z]*|\s[a-zA-Z]+))+"/>
> ```
> Ý nghĩa pattern trên: Không được có khoảng trắng ở đầu và cuối, chỉ chấp nhận 1 khoảng trắng ở giữa các từ.

## Câu hỏi 3

Viết XSD cho đoạn XML sau.


```xml
<?xml version="1.0" encoding="UTF-8"?>

<shiporder orderid="889923"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:noNamespaceSchemaLocation="shiporder.xsd">
  <orderperson>John Smith</orderperson>
  <shipto>
    <name>Ola Nordmann</name>
    <address>Langgt 23</address>
    <city>4000 Stavanger</city>
    <country>Norway</country>
  </shipto>
  <item>
    <title>Empire Burlesque</title>
    <note>Special Edition</note>
    <quantity>1</quantity>
    <price>10.90</price>
  </item>
  <item>
    <title>Hide your heart</title>
    <quantity>1</quantity>
    <price>9.90</price>
  </item>
</shiporder>
```

> Câu trả lời
>
> Nội dung tập tin `shiporder.xsd`:
> 
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
>     <xs:element name="shiporder">
>         <xs:complexType>
>             <xs:sequence>
>                 <xs:element name="orderperson" type="xs:string" />
>                 <xs:element name="shipto">
>                     <xs:complexType>
>                         <xs:sequence>
>                             <xs:element name="name" type="xs:string" />
>                             <xs:element name="address" type="xs:string" />
>                             <xs:element name="city" type="xs:string" />
>                             <xs:element name="country" type="xs:string" />
>                         </xs:sequence>
>                     </xs:complexType>
>                 </xs:element>
>                 <xs:element name="item" maxOccurs="unbounded">
>                     <xs:complexType>
>                         <xs:sequence>
>                             <xs:element name="title" type="xs:string" />
>                             <xs:element name="note" type="xs:string" minOccurs="0"/>
>                             <xs:element name="quantity" type="xs:positiveInteger" />
>                             <xs:element name="price" type="xs:decimal" />
>                         </xs:sequence>
>                     </xs:complexType>
>                 </xs:element>
>             </xs:sequence>
>             <xs:attribute name="orderid" type="xs:positiveInteger" use="required"/>
>         </xs:complexType>
>     </xs:element>
> </xs:schema>
> ```
