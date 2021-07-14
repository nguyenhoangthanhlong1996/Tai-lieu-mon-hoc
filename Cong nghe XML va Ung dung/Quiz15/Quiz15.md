Quiz 15
===
---

## Câu 1

```xml
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="countries">
        <xsl:apply-templates select="country[@population &gt;= 9000000 and @population &lt;= 10000000]"/>
    </xsl:template>

    <xsl:template match="countries/country">
        <xsl:copy-of select="."/>
    </xsl:template>
</xsl:stylesheet>
```

---

## Câu 2

```xml
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="countries">
        <html>
            <table border="1">
                <xsl:apply-templates select="country[count(language) > 3]">
                    <xsl:sort select="count(language)" data-type="number" order="descending" />
                </xsl:apply-templates>
            </table>
        </html>
    </xsl:template>

    <xsl:template match="countries/country">
        <tr>
            <td>
                <b>
                    <xsl:value-of select="@name"/>
                </b>
            </td>
            <td><xsl:value-of select="@population"/></td>
            <td><xsl:value-of select="@area"/></td>
            <td><xsl:value-of select="count(language)"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
```

---

## Câu 3

```xml
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="countries">
        <countries>
            <xsl:apply-templates select="country"/>
        </countries>
    </xsl:template>

    <xsl:template match="countries/country">
        <country>
            <xsl:attribute name="languages">
                <xsl:value-of select="count(language)"/>
            </xsl:attribute>
            <xsl:attribute name="cities">
                <xsl:value-of select="count(city)"/>
            </xsl:attribute>
            <name>
                <xsl:value-of select="@name"/>
            </name>
            <population>
                <xsl:value-of select="@population"/>
            </population>
        </country>
    </xsl:template>
</xsl:stylesheet>
```

---

## Câu 4

```xml
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="countries">
        <xsl:apply-templates select="country[contains(@name, 'stan')]"/>
    </xsl:template>

    <xsl:template match="countries/country">
        <stan>
            <xsl:value-of select="@name"/>
        </stan>
    </xsl:template>
</xsl:stylesheet>
```

---

## Câu 5

```xml
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="countries">
        <countries>
            <xsl:apply-templates select="country[@area &lt;= 40000 and count(city) &gt; 0]"/>    
        </countries>
    </xsl:template>

    <xsl:template match="countries/country">
        <xsl:copy-of select="."/>
    </xsl:template>
</xsl:stylesheet>
```