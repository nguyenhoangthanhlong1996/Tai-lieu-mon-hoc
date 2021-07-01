Quiz 07
===
---

## Exercise 1

Nội dung tập tin `Quiz07_E1.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<members xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="Quiz07_E1.xsd">
    <member>
        <name>Chris Smith</name>
        <yearsOfMembership>4</yearsOfMembership>
    </member>
    <member>
        <name>Judi Zimmerman</name>
        <yearsOfMembership>5</yearsOfMembership>
    </member>
    <member>
        <name>Michael Calder</name>
        <yearsOfMembership>3</yearsOfMembership>
    </member>
</members>
```

Nội dung tập tin `Quiz07_E1.xsd`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="members">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="member" minOccurs="0" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="name">
                                <xs:simpleType>
                                    <xs:restriction base="xs:string">
                                        <xs:pattern value="([a-zA-Z]+([a-zA-Z]*|\s[a-zA-Z]+))+"/>
                                    </xs:restriction>
                                </xs:simpleType>
                            </xs:element>
                            <xs:element name="yearsOfMembership" type="xs:nonNegativeInteger"></xs:element>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```

---

## Exercise 2

Nội dung tập tin `Quiz07_E2.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<books xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="Quiz07_E2.xsd">
    <book>
        <title>A semantic web primer. MIT press</title>
        <year>2004</year>
        <publisher>MIT press</publisher>
        <authors>
            <author>Antoniou</author>
            <author>G.</author>
            <author>&amp; Van Harmelen</author>
            <author>F</author>
        </authors>
    </book>
    <book>
        <title>The Semantic Web: a guide to the future of XML, Web services, and knowledge management. John Wiley &amp; Sons</title>
        <year>2003</year>
        <publisher>John Wiley &amp; Sons</publisher>
        <authors>
            <author>Daconta</author>
            <author>M. C.</author>
            <author>Obrst</author>
            <author>L. J.</author>
            <author>&amp; Smith</author>
            <author>K. T</author>
        </authors>
    </book>
</books>
```

Nội dung tập tin `Quiz07_E2.xsd`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="books">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="book" minOccurs="0" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="title" type="xs:string" />
                            <xs:element name="year" type="xs:gYear" />
                            <xs:element name="publisher" type="xs:string" />
                            <xs:element name="authors">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="author" type="xs:string" maxOccurs="unbounded"/>
                                    </xs:sequence>
                                </xs:complexType>
                            </xs:element>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```
---

## Exercise 3

Nội dung tập tin `shiporder.xsd`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="shiporder">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="orderperson" type="xs:string" />
                <xs:element name="shipto">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="name" type="xs:string" />
                            <xs:element name="address" type="xs:string" />
                            <xs:element name="city" type="xs:string" />
                            <xs:element name="country" type="xs:string" />
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
                <xs:element name="item" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="title" type="xs:string" />
                            <xs:element name="note" type="xs:string" minOccurs="0"/>
                            <xs:element name="quantity" type="xs:positiveInteger" />
                            <xs:element name="price" type="xs:decimal" />
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
            <xs:attribute name="orderid" type="xs:positiveInteger" use="required"/>
        </xs:complexType>
    </xs:element>
</xs:schema>
```
---

## Exercise 4

Nội dung tập tin `Quiz07_E4.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<library xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="Quiz07_E4.xsd">
    <books>
        <book>
            <bookID>123-4567-890</bookID>
            <title>XML</title>
            <pubDate>2001-10-20</pubDate>
            <replacementValue>15.95</replacementValue>
            <maxDaysOut>14</maxDaysOut>
        </book>
        <book>
            <bookID>123-4567-891</bookID>
            <title>Artificial Intelligence</title>
            <pubDate>2000-05-30</pubDate>
            <replacementValue>30.25</replacementValue>
            <maxDaysOut>14</maxDaysOut>
        </book>
        <book>
            <bookID>123-4567-892</bookID>
            <title>Semantic Web</title>
            <pubDate>2004-03-10</pubDate>
            <replacementValue>17.99</replacementValue>
            <maxDaysOut>14</maxDaysOut>
        </book>
        <book>
            <bookID>123-4567-893</bookID>
            <title>Agent</title>
            <pubDate>2005-06-10</pubDate>
            <replacementValue>11.95</replacementValue>
            <maxDaysOut>14</maxDaysOut>
        </book>
    </books>
    <transactions>
        <transaction id="1">
            <lender>
                <name>Michael Calder</name>
                <address>41 McMahons Rd, Frankston, WI</address>
                <phone>9903 4563</phone>
            </lender>
            <borrower>
                <name>Craig Gavin</name>
                <address>56 Overport Rd, Karingal, WI</address>
                <phone>9903 3455</phone>
            </borrower>
            <checkout>2007-10-15</checkout>
            <note>lender wants these books back in two weeks!</note>
            <bookIds>
                <bookId>123-4567-890</bookId>
                <bookId>123-4567-892</bookId>
            </bookIds>
        </transaction>
    </transactions>
</library>
```

Nội dung tập tin `Quiz07_E4.xsd`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <!-- bookIdType  -->
    <xs:simpleType name="bookIdType">
        <xs:restriction base="xs:string">
            <xs:pattern value="[0-9]+[0-9-]*" />
        </xs:restriction>
    </xs:simpleType>
    <!-- personInfoType  -->
    <xs:complexType name="personInfoType">
        <xs:sequence>
            <xs:element name="name" type="xs:string"></xs:element>
            <xs:element name="address" type="xs:string"></xs:element>
            <xs:element name="phone" type="xs:string"></xs:element>
        </xs:sequence>
    </xs:complexType>
    <!-- Structure of XML  -->
    <xs:element name="library">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="books">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="book" minOccurs="0" maxOccurs="unbounded">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="bookID" type="bookIdType" />
                                        <xs:element name="title" type="xs:string" />
                                        <xs:element name="pubDate" type="xs:date" />
                                        <xs:element name="replacementValue" type="xs:decimal" />
                                        <xs:element name="maxDaysOut" type="xs:positiveInteger" />
                                    </xs:sequence>
                                </xs:complexType>
                            </xs:element>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
                <xs:element name="transactions">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="transaction" minOccurs="0" maxOccurs="unbounded">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="lender" type="personInfoType"></xs:element>
                                        <xs:element name="borrower" type="personInfoType"></xs:element>
                                        <xs:element name="checkout" type="xs:date"></xs:element>
                                        <xs:element name="note" type="xs:string"></xs:element>
                                        <xs:element name="bookIds">
                                            <xs:complexType>
                                                <xs:sequence>
                                                    <xs:element name="bookId" type="bookIdType" maxOccurs="unbounded" />
                                                </xs:sequence>
                                            </xs:complexType>
                                        </xs:element>
                                    </xs:sequence>
                                    <xs:attribute name="id" type="xs:positiveInteger"></xs:attribute>
                                </xs:complexType>
                            </xs:element>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
        <!-- ID Unique, Ref ID  -->
        <xs:key name="bookIdUnique">
            <xs:selector xpath="books/book/bookID" />
            <xs:field xpath="." />
        </xs:key>
        <xs:keyref name="refBookID" refer="bookIdUnique">
            <xs:selector xpath="transactions/transaction/bookIds/bookId" />
            <xs:field xpath="." />
        </xs:keyref>
    </xs:element>
</xs:schema>
```