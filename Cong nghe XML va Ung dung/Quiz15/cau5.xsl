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
