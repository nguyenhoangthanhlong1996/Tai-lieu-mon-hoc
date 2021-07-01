XML Schema Quiz
=====

## Câu hỏi 1

Ràng buộc `totalDigits` có bao gồm dấu chấm `.` hay không?

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

a. `12.34`
b. `123456789.123` hợp lệ
c. `12345678.12`
d. `123456.12`
e. `123456.123`
f. `123456`
g. `123456.`

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
([a-zA-Z\s])+
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

## bài làm
<?xml version="1.0" encoding="utf-8"?>
<xs:schema targetNamespace="http://tempuri.org/XMLSchema.xsd"
    elementFormDefault="qualified"
    xmlns="http://tempuri.org/XMLSchema.xsd"
    xmlns:mstns="http://tempuri.org/XMLSchema.xsd"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
>
	<xs:complexType name="shipto">
		<xs:sequence>
			<xs:element name="name" type="xs:string"></xs:element>
			<xs:element name="address" type="xs:string"></xs:element>
			<xs:element name="city" type="xs:string"></xs:element>
			<xs:element name="country" type="xs:string"></xs:element>
		</xs:sequence>
	</xs:complexType>
	<xs:complexType name="item" >
		<xs:sequence>
			<xs:element name="title" type="xs:string"></xs:element>
			<xs:element name="note" type="xs:string"></xs:element>
			<xs:element name="quantity" type="xs:string"></xs:element>
			<xs:element name="price" type="xs:double"></xs:element>
		</xs:sequence>
	</xs:complexType>
	<xs:complexType name="shiporder" id="889923" >
		<xs:sequence>
			<xs:element name="orderperson" type="xs:string"></xs:element>
			<xs:element name="shipto" type="shipto"></xs:element>
			<xs:element name="item" type="item"></xs:element>
		</xs:sequence>
	</xs:complexType>
</xs:schema>
