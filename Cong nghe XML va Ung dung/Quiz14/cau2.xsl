<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Câu 2</h2>
                <table border="1">
                    <tr bgcolor="#E0E0E0">
                        <th style="text-align:left">Title</th>
                        <th style="text-align:left">Artist</th>
                        <th style="text-align:left">Country</th>
                        <th style="text-align:left">Company</th>
                        <th style="text-align:left">Price</th>
                        <th style="text-align:left">Year</th>
                    </tr>

                    <xsl:for-each select="catalog/cd">
                        <xsl:sort select="price" order="ascending" data-type="number" />
                        <xsl:choose>
                            <xsl:when test="price &lt; 8">
                                <tr bgcolor="#0f0">
                                    <td>
                                        <xsl:value-of select="title" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="artist" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="country" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="company" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="price" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="year" />
                                    </td>
                                </tr>
                            </xsl:when>
                            <xsl:when test="price &gt;= 8 and price &lt;= 10 ">
                                <tr bgcolor="#ff0">
                                    <td>
                                        <xsl:value-of select="title" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="artist" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="country" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="company" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="price" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="year" />
                                    </td>
                                </tr>
                            </xsl:when>
                            <xsl:otherwise>
                                <tr bgcolor="#f00">
                                    <td>
                                        <xsl:value-of select="title" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="artist" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="country" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="company" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="price" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="year" />
                                    </td>
                                </tr>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>