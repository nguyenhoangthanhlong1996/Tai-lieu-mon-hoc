Quiz 14
===
---

Cho nội dung xml CD Catalog

```xml
<catalog>
    <cd>
        <title>Empire Burlesque</title>
        <artist>Bob Dylan</artist>
        <country>USA</country>
        <company>Columbia</company>
        <price>10.90</price>
        <year>1985</year>
    </cd>
    <cd>
        <title>Hide your heart</title>
        <artist>Bonnie Tyler</artist>
        <country>UK</country>
        <company>CBS Records</company>
        <price>9.90</price>
        <year>1988</year>
    </cd>
    <cd>
        <title>Greatest Hits</title>
        <artist>Dolly Parton</artist>
        <country>USA</country>
        <company>RCA</company>
        <price>9.90</price>
        <year>1982</year>
    </cd>
    <cd>
        <title>Still got the blues</title>
        <artist>Gary Moore</artist>
        <country>UK</country>
        <company>Virgin records</company>
        <price>10.20</price>
        <year>1990</year>
    </cd>
    <cd>
        <title>Eros</title>
        <artist>Eros Ramazzotti</artist>
        <country>EU</country>
        <company>BMG</company>
        <price>9.90</price>
        <year>1997</year>
    </cd>
    <cd>
        <title>One night only</title>
        <artist>Bee Gees</artist>
        <country>UK</country>
        <company>Polydor</company>
        <price>10.90</price>
        <year>1998</year>
    </cd>
    <cd>
        <title>Sylvias Mother</title>
        <artist>Dr.Hook</artist>
        <country>UK</country>
        <company>CBS</company>
        <price>8.10</price>
        <year>1973</year>
    </cd>
    <cd>
        <title>Maggie May</title>
        <artist>Rod Stewart</artist>
        <country>UK</country>
        <company>Pickwick</company>
        <price>8.50</price>
        <year>1990</year>
    </cd>
    <cd>
        <title>Romanza</title>
        <artist>Andrea Bocelli</artist>
        <country>EU</country>
        <company>Polydor</company>
        <price>10.80</price>
        <year>1996</year>
    </cd>
    <cd>
        <title>When a man loves a woman</title>
        <artist>Percy Sledge</artist>
        <country>USA</country>
        <company>Atlantic</company>
        <price>8.70</price>
        <year>1987</year>
    </cd>
    <cd>
        <title>Black angel</title>
        <artist>Savage Rose</artist>
        <country>EU</country>
        <company>Mega</company>
        <price>10.90</price>
        <year>1995</year>
    </cd>
    <cd>
        <title>1999 Grammy Nominees</title>
        <artist>Many</artist>
        <country>USA</country>
        <company>Grammy</company>
        <price>10.20</price>
        <year>1999</year>
    </cd>
    <cd>
        <title>For the good times</title>
        <artist>Kenny Rogers</artist>
        <country>UK</country>
        <company>Mucik Master</company>
        <price>8.70</price>
        <year>1995</year>
    </cd>
    <cd>
        <title>Big Willie style</title>
        <artist>Will Smith</artist>
        <country>USA</country>
        <company>Columbia</company>
        <price>9.90</price>
        <year>1997</year>
    </cd>
    <cd>
        <title>Tupelo Honey</title>
        <artist>Van Morrison</artist>
        <country>UK</country>
        <company>Polydor</company>
        <price>8.20</price>
        <year>1971</year>
    </cd>
    <cd>
        <title>Soulsville</title>
        <artist>Jorn Hoel</artist>
        <country>Norway</country>
        <company>WEA</company>
        <price>7.90</price>
        <year>1996</year>
    </cd>
    <cd>
        <title>The very best of</title>
        <artist>Cat Stevens</artist>
        <country>UK</country>
        <company>Island</company>
        <price>8.90</price>
        <year>1990</year>
    </cd>
    <cd>
        <title>Stop</title>
        <artist>Sam Brown</artist>
        <country>UK</country>
        <company>A and M</company>
        <price>8.90</price>
        <year>1988</year>
    </cd>
    <cd>
        <title>Bridge of Spies</title>
        <artist>T`Pau</artist>
        <country>UK</country>
        <company>Siren</company>
        <price>7.90</price>
        <year>1987</year>
    </cd>
    <cd>
        <title>Private Dancer</title>
        <artist>Tina Turner</artist>
        <country>UK</country>
        <company>Capitol</company>
        <price>8.90</price>
        <year>1983</year>
    </cd>
    <cd>
        <title>Midt om natten</title>
        <artist>Kim Larsen</artist>
        <country>EU</country>
        <company>Medley</company>
        <price>7.80</price>
        <year>1983</year>
    </cd>
    <cd>
        <title>Pavarotti Gala Concert</title>
        <artist>Luciano Pavarotti</artist>
        <country>UK</country>
        <company>DECCA</company>
        <price>9.90</price>
        <year>1991</year>
    </cd>
    <cd>
        <title>The dock of the bay</title>
        <artist>Otis Redding</artist>
        <country>USA</country>
        <company>Stax Records</company>
        <price>7.90</price>
        <year>1968</year>
    </cd>
    <cd>
        <title>Picture book</title>
        <artist>Simply Red</artist>
        <country>EU</country>
        <company>Elektra</company>
        <price>7.20</price>
        <year>1985</year>
    </cd>
    <cd>
        <title>Red</title>
        <artist>The Communards</artist>
        <country>UK</country>
        <company>London</company>
        <price>7.80</price>
        <year>1987</year>
    </cd>
    <cd>
        <title>Unchain my heart</title>
        <artist>Joe Cocker</artist>
        <country>USA</country>
        <company>EMI</company>
        <price>8.20</price>
        <year>1987</year>
    </cd>
</catalog>
```

---

## Câu 1
Viết XSLT cho nội dung XML trên với yêu cầu in ra
- Chỉ liệt kê các CD có giá > 10
- Xếp theo trật tự giá giảm dần.

> Câu trả lời:
> ```xml
> <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
>     <xsl:template match="/">
>         <html>
>             <body>
>                 <h2>Câu 1</h2>
>                 <table border="1">
>                     <tr bgcolor="#E0E0E0">
>                         <th style="text-align:left">Title</th>
>                         <th style="text-align:left">Artist</th>
>                         <th style="text-align:left">Country</th>
>                         <th style="text-align:left">Company</th>
>                         <th style="text-align:left">Price</th>
>                         <th style="text-align:left">Year</th>
>                     </tr>
>                     <xsl:for-each select="catalog/cd">
>                         <xsl:sort select="price" order="descending" data-type="number" />
>                         <xsl:if test="price > 10">
>                             <tr>
>                                 <td>
>                                     <xsl:value-of select="title" />
>                                 </td>
>                                 <td>
>                                     <xsl:value-of select="artist" />
>                                 </td>
>                                 <td>
>                                     <xsl:value-of select="country" />
>                                 </td>
>                                 <td>
>                                     <xsl:value-of select="company" />
>                                 </td>
>                                 <td>
>                                     <xsl:value-of select="price" />
>                                 </td>
>                                 <td>
>                                     <xsl:value-of select="year" />
>                                 </td>
>                             </tr>
>                         </xsl:if>
>                     </xsl:for-each>
>                 </table>
>             </body>
>         </html>
>     </xsl:template>
> </xsl:stylesheet>
> ```

---

## Câu 2
Viết XSLT cho nội dung XML trên với yêu cầu in ra
- Sắp xếp theo giá tăng dần
- Nếu giá rẻ `(price <8)` thì in ra với nền xanh lá cây
- Nếu giá trung bình `(8 ≤ price < 10)` thì in ra với nền vàng
- Nếu giá mắc `(price ≥ 10)` thì in ra với nền đỏ

> Câu trả lời:
> ```xml
> <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
>     <xsl:template match="/">
>         <html>
>             <body>
>                 <h2>Câu 2</h2>
>                 <table border="1">
>                     <tr bgcolor="#E0E0E0">
>                         <th style="text-align:left">Title</th>
>                         <th style="text-align:left">Artist</th>
>                         <th style="text-align:left">Country</th>
>                         <th style="text-align:left">Company</th>
>                         <th style="text-align:left">Price</th>
>                         <th style="text-align:left">Year</th>
>                     </tr>
> 
>                     <xsl:for-each select="catalog/cd">
>                         <xsl:sort select="price" order="ascending" data-type="number" />
>                         <xsl:choose>
>                             <xsl:when test="price &lt; 8">
>                                 <tr bgcolor="#0f0">
>                                     <td>
>                                         <xsl:value-of select="title" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="artist" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="country" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="company" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="price" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="year" />
>                                     </td>
>                                 </tr>
>                             </xsl:when>
>                             <xsl:when test="price &gt;= 8 and price &lt;= 10 ">
>                                 <tr bgcolor="#ff0">
>                                     <td>
>                                         <xsl:value-of select="title" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="artist" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="country" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="company" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="price" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="year" />
>                                     </td>
>                                 </tr>
>                             </xsl:when>
>                             <xsl:otherwise>
>                                 <tr bgcolor="#f00">
>                                     <td>
>                                         <xsl:value-of select="title" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="artist" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="country" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="company" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="price" />
>                                     </td>
>                                     <td>
>                                         <xsl:value-of select="year" />
>                                     </td>
>                                 </tr>
>                             </xsl:otherwise>
>                         </xsl:choose>
>                     </xsl:for-each>
>                 </table>
>             </body>
>         </html>
>     </xsl:template>
> </xsl:stylesheet>
> ```

---

## Câu 3
Viết XSLT cho nội dung XML trên với yêu cầu in ra
- Sắp xếp theo giá tăng dần
- Nếu cùng giá thì sắp xếp theo tên Nghệ sĩ (artist)
- Nếu cùng giá, cùng nghệ sĩ thì xếp theo trật tự tên CD

> Câu trả lời:
> ```xml
> <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
>     <xsl:template match="/">
>         <html>
>             <body>
>                 <h2>Câu 3</h2>
>                 <table border="1">
>                     <tr bgcolor="#E0E0E0">
>                         <th style="text-align:left">Title</th>
>                         <th style="text-align:left">Artist</th>
>                         <th style="text-align:left">Country</th>
>                         <th style="text-align:left">Company</th>
>                         <th style="text-align:left">Price</th>
>                         <th style="text-align:left">Year</th>
>                     </tr>
> 
>                     <xsl:for-each select="catalog/cd">
>                         <xsl:sort select="price" order="ascending" data-type="number" />
>                         <xsl:sort select="artist" order="ascending" data-type="text" />
>                         <xsl:sort select="title" order="ascending" data-type="text" />
>                         <tr>
>                             <td>
>                                 <xsl:value-of select="title" />
>                             </td>
>                             <td>
>                                 <xsl:value-of select="artist" />
>                             </td>
>                             <td>
>                                 <xsl:value-of select="country" />
>                             </td>
>                             <td>
>                                 <xsl:value-of select="company" />
>                             </td>
>                             <td>
>                                 <xsl:value-of select="price" />
>                             </td>
>                             <td>
>                                 <xsl:value-of select="year" />
>                             </td>
>                         </tr>
>                     </xsl:for-each>
>                 </table>
>             </body>
>         </html>
>     </xsl:template>
> </xsl:stylesheet>
> ```