LAB 3 - XSD
=====
---

## Câu 1

So sánh DTD và XSD

> Câu trả lời:
> 1. Giống nhau:
>
> Đều dùng để xác định cấu trúc, các thuộc tính của các thẻ trong tài liệu XML, một ứng dụng có thể dựa vào DTD, XSD để xác minh rằng dữ liệu XML là hợp lệ
>
> 2. Khác nhau
>
>|DTD|XSD|
>|:---|:---|
>|DTD (Document Type Definition)|XSD (XML Schema Definition)
>|DTD sử dụng cú pháp SGML để trình bày|XSD sử dụng cú pháp XML để trình bày|
>|DTD khó mở rộng|XSD dể mở rộng hơn|
>|DTD không hỗ trợ namespace thay vào đó nó có bộ từ khóa riêng để xác định lược đồ. Ví dụ: ! DOCTYPE, !ELEMENT, !ENTITY và e.t.c.|XSD hỗ trợ namespace và sử dụng tập hợp các namspace và phần tử của riêng nó để xác định lược đồ.
>|DTD không hỗ trợ kiểu dữ liệu, nó chỉ có kiểu #PCDATA làm kiểu dữ liệu cho các phần tử được sử dụng cho chuỗi|XSD có hỗ trợ nhiều kiểu dữ liệu cho element và attributes như String, Date, Numeric, Misc, Reference
>|DTD không thể miêu tả các ràng buộc về kiểu dữ liệu một cách cụ thể|XSD cho phép miêu tả các ràng buộc cụ thể trên dữ liệu
>|DTD phù hợp hơn với tài liệu XML kích thước nhỏ và ít yêu cầu về mặt ràng buộc dữ liệu|XSD có thể được dùng để miêu tả cho tài liệu XML lớn, yêu cầu nhiều ràng buộc cụ thể trên dữ liệu|
>|DTD không cung cấp nhiều khả năng để ta có thể kiểm soát cấu trúc XML của mình một cách tốt nhất|XSD cung cấp khả năng kiểm soát cấu trúc XML mạnh hơn

---

## Câu 2

Cho file XML sau bank.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<bank>
  <accounts>
    <savings_accounts>
      <savings_account id="a1" interest="0.03">
        <balance>2500</balance>
      </savings_account>
      <savings_account id="a2" interest="0.03">
        <balance>15075</balance>
      </savings_account>
    </savings_accounts>
    <checking_accounts>
      <checking_account id="a3">
        <balance>4025</balance>
      </checking_account>
      <checking_account id="a4">
        <balance>-125</balance>
      </checking_account>
      <checking_account id="a5">
        <balance>325</balance>
      </checking_account>  
    </checking_accounts>
  </accounts>
  <customers>
    <customer id="c1">
      <name>Ben Richerdson</name>
      <address>Park Drive 2</address>
    </customer>
    <customer id="c2">
      <name>Marc Wretcher</name>
      <address>Mill Drive 75</address>
    </customer>
    <customer id="c3">
      <name>Angel Steady</name>
      <address>Lake Sight 15</address>
    </customer>
  </customers>
  <customer_accounts>
    <customer_account c_id="c1" ac_id="a2"/>
    <customer_account c_id="c1" ac_id="a3"/>
    <customer_account c_id="c2" ac_id="a4"/>
    <customer_account c_id="c3" ac_id="a1"/>
    <customer_account c_id="c3" ac_id="a5"/>
  </customer_accounts>
</bank>
```

- Hãy viết DTD và XSD với những ràng buộc sau
    - Có 2 loại tài khoản: "checking" và "saving".
    - Trong danh sách "accounts" thì account id là duy nhất (unique).
    - Trong danh sách "customers" thì customer id là duy nhất (unique).
    - "c_id" tham khảo đến customer id và "ac_id" tham khảo đến account id.
    - Số dư của tài khoản phải lớn hơn -5000.
    - Sử dụng thừa kế cho hai loại tài khoản: tài khoản checking và savings được thừa kế từ tài khoản common

> Câu trả lời
>
> 1. Nội dung tập tin `bank.dtd`
>```xml
><!ELEMENT bank (accounts, customers, customer_accounts)>
><!ELEMENT accounts (savings_accounts, checking_accounts)>
><!ELEMENT savings_accounts (savings_account*)>
><!ELEMENT savings_account (balance)>
><!ELEMENT checking_accounts (checking_account*)>
><!ELEMENT checking_account (balance)>
><!ELEMENT balance (#PCDATA)>
><!ELEMENT name (#PCDATA)>
><!ELEMENT address (#PCDATA)>
><!ELEMENT customer_accounts (customer_account*)>
><!ELEMENT customer_account EMPTY>
><!ELEMENT customers (customer*)>
><!ELEMENT customer (name, address)>
>
><!ATTLIST savings_account id ID #REQUIRED>
><!ATTLIST savings_account interest CDATA #REQUIRED>
><!ATTLIST checking_account id ID #REQUIRED>
><!ATTLIST customer id ID #REQUIRED>
><!ATTLIST customer_account c_id IDREF #REQUIRED>
><!ATTLIST customer_account ac_id IDREF #REQUIRED>
>```
>
> 2. Nội dung tập tin `bank.xsd`
>```xml
><?xml version="1.0" encoding="UTF-8"?>
><xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
>    <!--  _______________________________Simple Type_____________________________________ -->
>    <!-- idType -->
>    <xs:simpleType name="idType">
>        <xs:restriction base="xs:string">
>            <xs:pattern value="[a-zA-Z0-9]+" />
>        </xs:restriction>
>    </xs:simpleType>
>    <!-- balanceType -->
>    <xs:simpleType name="balanceType">
>        <xs:restriction base="xs:integer">
>            <xs:minExclusive value="-5000" />
>        </xs:restriction>
>    </xs:simpleType>
>    <!-- interestType -->
>    <xs:simpleType name="interestType">
>        <xs:restriction base="xs:decimal">
>            <xs:totalDigits value="4" />
>            <xs:fractionDigits value="2" />
>        </xs:restriction>
>    </xs:simpleType>
>    <!-- nameType -->
>    <xs:simpleType name="nameType">
>        <xs:restriction base="xs:string">
>            <xs:pattern value="[a-zA-Z][a-zA-Z\s]+" />
>        </xs:restriction>
>    </xs:simpleType>
>    <!-- addressType -->
>    <xs:simpleType name="addressType">
>        <xs:restriction base="xs:string">
>            <xs:pattern value="[a-zA-Z0-9][a-zA-Z0-9,\s]+" />
>        </xs:restriction>
>    </xs:simpleType>
>    <!--  _______________________________Complex Type_____________________________________ -->
>    <!-- commonAccountType -->
>    <xs:complexType name="commonAccountType">
>        <xs:all>
>            <xs:element name="balance" type="balanceType" />
>        </xs:all>
>        <xs:attribute name="id" type="idType" use="required" />
>    </xs:complexType>
>    <!-- savingAccountType base on commonAccountType -->
>    <xs:complexType name="savingAccountType">
>        <xs:complexContent>
>            <xs:extension base="commonAccountType">
>                <xs:attribute name="interest" type="interestType" use="required" />
>            </xs:extension>
>        </xs:complexContent>
>    </xs:complexType>
>    <!-- checkingAccountType base on commonAccountType -->
>    <xs:complexType name="checkingAccountType">
>        <xs:complexContent>
>            <xs:extension base="commonAccountType" />
>        </xs:complexContent>
>    </xs:complexType>
>    <!-- customerType -->
>    <xs:complexType name="customerType">
>        <xs:all>
>            <xs:element name="name" type="nameType" />
>            <xs:element name="address" type="addressType" />
>        </xs:all>
>        <xs:attribute name="id" type="idType" use="required" />
>    </xs:complexType>
>    <!-- customerAccountType -->
>    <xs:complexType name="customerAccountType">
>        <xs:attribute name="c_id" type="idType" use="required" />
>        <xs:attribute name="ac_id" type="idType" use="required" />
>    </xs:complexType>
>    <!--  _______________________________Define structure of XML Document_____________________________________ -->
>    <xs:element name="bank">
>        <xs:complexType>
>            <xs:sequence>
>                <xs:element name="accounts">
>                    <xs:complexType>
>                        <xs:sequence>
>                            <xs:element name="savings_accounts">
>                                <xs:complexType>
>                                    <xs:sequence>
>                                        <xs:element name="savings_account" type="savingAccountType" minOccurs="0" maxOccurs="unbounded" />
>                                    </xs:sequence>
>                                </xs:complexType>
>                            </xs:element>
>                            <xs:element name="checking_accounts">
>                                <xs:complexType>
>                                    <xs:sequence>
>                                        <xs:element name="checking_account" type="checkingAccountType" minOccurs="0" maxOccurs="unbounded" />
>                                    </xs:sequence>
>                                </xs:complexType>
>                            </xs:element>
>                        </xs:sequence>
>                    </xs:complexType>
>                </xs:element>
>                <xs:element name="customers">
>                    <xs:complexType>
>                        <xs:sequence>
>                            <xs:element name="customer" type="customerType" minOccurs="0" maxOccurs="unbounded" />
>                        </xs:sequence>
>                    </xs:complexType>
>                </xs:element>
>                <xs:element name="customer_accounts">
>                    <xs:complexType>
>                        <xs:sequence>
>                            <xs:element name="customer_account" type="customerAccountType" minOccurs="0" maxOccurs="unbounded" />
>                        </xs:sequence>
>                    </xs:complexType>
>                </xs:element>
>            </xs:sequence>
>        </xs:complexType>
>        <!--  _____________ ID and Reference ID _____________ -->
>        <!-- Unique ID of Account -->
>        <xs:key name="accountId">
>            <xs:selector xpath="./accounts/savings_accounts/savings_account|./accounts/checking_accounts/checking_account" />
>            <xs:field xpath="@id" />
>        </xs:key>
>        <!-- Unique ID of Customer -->
>        <xs:key name="customerId">
>            <xs:selector xpath="./customers/customer" />
>            <xs:field xpath="@id" />
>        </xs:key>
>        <!-- Reference to ID of Account -->
>        <xs:keyref name="accountIdRef" refer="accountId">
>            <xs:selector xpath="./customer_accounts/customer_account" />
>            <xs:field xpath="@ac_id" />
>        </xs:keyref>
>        <!-- Reference to ID of Customer -->
>        <xs:keyref name="customerIdRef" refer="customerId">
>            <xs:selector xpath="./customer_accounts/customer_account" />
>            <xs:field xpath="@c_id" />
>        </xs:keyref>
>    </xs:element>
></xs:schema>
>```