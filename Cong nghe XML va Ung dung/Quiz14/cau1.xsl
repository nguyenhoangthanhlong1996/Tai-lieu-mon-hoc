<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Câu 1</h2>
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
                        <xsl:sort select="price" order="descending" data-type="number" />
                        <xsl:if test="price > 10">
                            <tr>
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
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>