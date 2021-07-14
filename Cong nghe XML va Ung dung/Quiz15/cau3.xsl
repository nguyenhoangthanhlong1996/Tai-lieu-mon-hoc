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
