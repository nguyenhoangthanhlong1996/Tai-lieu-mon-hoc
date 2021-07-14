<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="countries">
        <xsl:apply-templates select="country[@population &gt;= 9000000 and @population &lt;= 10000000]"/>
    </xsl:template>

    <xsl:template match="countries/country">
        <xsl:copy-of select="."/>
    </xsl:template>
</xsl:stylesheet>
