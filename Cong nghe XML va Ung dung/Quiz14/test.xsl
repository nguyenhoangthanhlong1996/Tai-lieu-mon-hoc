<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Test</h2>
                <table border="1">
                    <thead>
                        <tr bgcolor="#E0E0E0">
                            <th style="text-align:left">Title</th>
                            <th style="text-align:left">Artist</th>
                            <th style="text-align:left">Country</th>
                            <th style="text-align:left">Company</th>
                            <th style="text-align:left">Price</th>
                            <th style="text-align:left">Year</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="catalog/cd">
                            <xsl:sort select="title" data-type="text" order="ascending" />
                        </xsl:apply-templates>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="cd">
        <xsl:if test="price &lt; 8">
            <tr bgcolor="#00ff00">
                <!-- <xsl:call-template name="cdProps" /> -->
                <xsl:apply-templates select="title" />
                <xsl:apply-templates select="artist" />
                <xsl:apply-templates select="country" />
                <xsl:apply-templates select="company" />
                <xsl:apply-templates select="price" />
                <xsl:apply-templates select="year" />
            </tr>
        </xsl:if>
        
    </xsl:template>

    <!-- <Xs:template name="cdProps">
        <xsl:apply-templates select="title" />
        <xsl:apply-templates select="artist" />
        <xsl:apply-templates select="country" />
        <xsl:apply-templates select="company" />
        <xsl:apply-templates select="price" />
        <xsl:apply-templates select="year" />
    </Xs:template> -->

    <xsl:template match="cd/*">
        <td>
            <xsl:value-of select="." />
        </td>
    </xsl:template>

</xsl:stylesheet>