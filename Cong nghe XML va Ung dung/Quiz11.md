Quiz 11
===

---

## Selecting following siblings with filtering

#### Source document

```xml
<products>
  <product id="1" name="Teekanne" price="25.00" category="1" />
  <product id="2" name="Bleistift" price="0.29" category="2" />
  <product id="3" name="Lautsprecher" price="19.00" category="2" />
  <product id="4" name="Tasse" price="1.99" category="1" />
  <product id="5" name="Apfelsaft" price="1.49" category="1" />
  <product id="6" name="CD-Rohling" price="0.89" category="2" />
  <category id="1" name="Sortiment 2005" />
  <category id="2" name="Sortiment 2006" />
</products>
```

#### Challenge

Select the following product siblings, which contain the value 1 as a category, relatively from their current node.

#### Desired selection (XML-output)

```xml
<product id="4" name="Tasse" price="1.99" category="1" />
<product id="5" name="Apfelsaft" price="1.49" category="1" />
```

> #### Answer
> `/products/product[@id=3]/following-sibling::product[@category=1]`

---

## Attribute selection with namespaces

#### Source document

```xml
<document xmlns:xlink="http://www.w3.org/1999/xlink">
  <reference xlink:href="http://www.google.de/" />
</document>
```

#### Challenge

Select the value of the attribute “href”, which is connected to the namespace “http://www.w3.org/1999/xlink”. For your XPath expression, this namespace is attached to the prefix x.

#### Desired selection (text output)

`http://www.google.de/`

> #### Answer
> `/document/reference/@x:href`

---

## Selecting all links of a document

#### Source document

```xml
<document id="1">
  <content>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
      </head>
      <body>
        <div>
          <ul>
            <li><a href="http://www.google.de/">http://www.google.de/">Google</a>
              <ul>
                  <li><a href="http://earth.google.de/">Google Earth</a></li>
                  <li><a href="http://picasa.google.de/intl/de/">Picasa</a></li>
              </ul>
            </li> 
            <li><a href="http://www.heise.de/">http://www.heise.de/">Heise</a></li> 
            <li><a href="http://www.yahoo.de/">http://www.yahoo.de/">Yahoo</a></li> 
          </ul>
        </div>
      </body>
    </html>
  </content>
  <teaser>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
      </head>
      <body>
        <div>
          <ul>
            <li><a href="http://www.google.de/">http://www.google.de/">Google</a></li>
          </ul>
        </div>
      </body>
    </html> 
  </teaser>
</document>
```

#### Challenge

Collect all links in the document (XHTML elements of type a, which are attached to the namespace http://www.w3.org/1999/xhtml) below the elements document/content.

For the XPath expression, the prefix x is available for the namespace http://www.w3.org/1999/xhtml.

#### Desired selection (XML-output)

```xml
<a href="http://www.google.de/" xmlns="http://www.w3.org/1999/xhtml">http://www.google.de/">Google</a>
<a href="http://earth.google.de/" xmlns="http://www.w3.org/1999/xhtml">Google Earth</a>
<a href="http://picasa.google.de/intl/de/" xmlns="http://www.w3.org/1999/xhtml">Picasa</a>
<a href="http://www.heise.de/" xmlns="http://www.w3.org/1999/xhtml">http://www.heise.de/">Heise</a>
<a href="http://www.yahoo.de/" xmlns="http://www.w3.org/1999/xhtml">http://www.yahoo.de/">Yahoo</a>
```

> #### Answer
> `/document/content//x:a`

---

## Selecting all links to Google within a document

#### Source document

```xml
<document id="1">
  <content>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
      </head>
      <body>
        <div>
          <ul>
            <li><a href="http://www.google.de/">http://www.google.de/Google</a>
              <ul>
                <li><a href="http://earth.google.de/">Google Earth</a></li>
                <li><a href="http://picasa.google.de/intl/de/">Picasa</a></li>
              </ul>
            </li> 
            <li><a href="http://www.heise.de/">Heise</a></li> 
            <li><a href="http://www.yahoo.de/">Yahoo</a></li> 
          </ul>
        </div>
      </body>
    </html>
  </content>
</document>
```

#### Challenge

Collect all links in the document (XHTML elements of type a, which are attached to the namespace http://www.w3.org/1999/xhtml) which refer to a Google service. The existence of the text “google” within the href-attribute decides whether a Google service is concerned. For the XPath expression, the prefix x is available for the namespace “http://www.w3.org/1999/xhtml”.

#### Desired selection (XML-output)

```xml
<a href="http://www.google.de/" xmlns="http://www.w3.org/1999/xhtml">http://www.google.de/Google</a>
<a href="http://earth.google.de/" xmlns="http://www.w3.org/1999/xhtml">Google Earth</a>
<a href="http://picasa.google.de/intl/de/" xmlns="http://www.w3.org/1999/xhtml">Picasa</a>
```

> #### Answer
> `/document/content//x:a[contains(@href, 'google')]`

---

## Selecting elements by name

#### Source document

```xml
<document>
  <item_0001>Erstes Element</item_0001>
  <item_0002>Zweites Element</item_0002>
  <item_0003>Drittes Element</item_0003>
  <item_0004>Viertes Element</item_0004>
  <other_0001>Erstes Element</other_0001>
  <other_0002>Zweites Element</other_0002>
</document>
```

#### Challenge

Select all elements with the name beginning with »item«.

#### Desired selection (XML-output)

```xml
<item_0001>Erstes Element</item_0001>
<item_0002>Zweites Element</item_0002>
<item_0003>Drittes Element</item_0003>
<item_0004>Viertes Element</item_0004>
```

> #### Answer
> `/document/*[starts-with(name(), 'item')]`

---

## Selecting elements by name with namespaces

#### Source document

```xml
<document xmlns="http://example.org">
  <item_0001>Erstes Element</item_0001>
  <item_0002>Zweites Element</item_0002>
  <item_0003>Drittes Element</item_0003>
  <item_0004 xmlns="http://example.net/">Viertes Element</item_0004>
  <other_0001>Erstes Element</other_0001>
  <other_0002>Zweites Element</other_0002>
</document>
```

#### Challenge

Select all elements having a name beginning with “item” and a namespace attached to “http://example.org”. For the expression, the prefix t is attached to the namespace “http://example.org”.

#### Desired selection (XML-output)

```xml
<item_0001 xmlns="http://example.org">Erstes Element</item_0001>
<item_0002 xmlns="http://example.org">Zweites Element</item_0002>
<item_0003 xmlns="http://example.org">Drittes Element</item_0003>
```

> #### Answer
> `/t:document/t:*[starts-with(name(), 'item')]`

---

## Counting elements selected by certain criteria

#### Source document

```xml
<collection>
  <artist>
    <name>Robbie Williams</name>
    <cds>
      <cd>Rudebox</cd>
      <cd>Swing when you're winning</cd>
    </cds>
  </artist>
  <band>
    <name>Juli</name>
    <cds>
      <cd>Ein neuer Tag</cd>
    </cds>
  </band>
  <band>
    <name>Silbermond</name>
    <cds>
      <cd>Verschwende deine Zeit</cd>
    </cds>
  </band>
  <artist>
    <name>Michael Jackson</name>
    <cds>
      <cd>Bad</cd>
      <cd>Thriller</cd>
    </cds>
  </artist>
</collection>
```

#### Challenge

Output in the sentence below how many albums in the collection are by solo artists (artist) and how many are by bands (band).

#### Desired selection (text output)

`In der Sammlung gibt es 4 Alben von Solokünstlern und 2 Alben von Bands.`

> #### Answer
> `concat('In der Sammlung gibt es ', count(/collection/artist/cds/cd), ' Alben von Solokünstlern und ', count(/collection/band/cds/cd), ' Alben von Bands.')`

---

## Content of a parent element

#### Source document

```xml
<collection>
  <artist>
    <name>Robbie Williams</name>
    <cds>
      <cd>Rudebox</cd>
      <cd>Swing when you're winning</cd>
    </cds>
  </artist>
  <band>
    <name>Juli</name>
    <cds>
      <cd>Ein neuer Tag</cd>
    </cds>
  </band>
  <band>
    <name>Silbermond</name>
    <cds>
      <cd>Verschwende deine Zeit</cd>
    </cds>
  </band>
  <artist>
    <name>Michael Jackson</name>
    <cds>
      <cd>Bad</cd>
      <cd>Thriller</cd>
    </cds>
  </artist>
</collection>
```

#### Challenge

Select the name of the artist that goes with the CD.

#### Desired selection (text output)

`Robbie Williams`

> #### Answer
> `/collection/artist//cd[text() = 'Rudebox']/parent::cds/preceding-sibling::name`

---

## Finding surrounding element

#### Source document

```xml
<document id="1">
  <content>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
      </head>
      <body>
        <div id="outer">
          <div id="inner">
            <ul>
              <li><a href="http://www.google.de/">Google</a>
                <ul>
                  <li><a href="http://earth.google.de/">Google Earth</a></li>
                  <li><a href="http://picasa.google.de/intl/de/">Picasa</a></li>
                </ul>
              </li> 
              <li><a href="http://www.heise.de/">Heise</a></li> 
              <li><a href="http://www.yahoo.de/">Yahoo</a></li> 
            </ul>
          </div>
        </div>
      </body>
    </html>
  </content>
</document>
```

#### Challenge

Resolve the id-attribute of the div-element nearest to you. The expression selected by you should work at any point within the nested list.

#### Desired selection (text output)

`inner`

> #### Answer
> `//x:a[@href='http://earth.google.de/']/parent::x:li/ancestor::x:div[1]/@id`

---

## First and last element

#### Source document

```xml
<collection>
  <artist>
    <name>Robbie Williams</name>
    <cds>
      <cd>Rudebox</cd>
      <cd>Swing when you're winning</cd>
    </cds>
  </artist>
  <band>
    <name>Juli</name>
    <cds>
      <cd>Ein neuer Tag</cd>
    </cds>
  </band>
  <band>
    <name>Silbermond</name>
    <cds>
      <cd>Verschwende deine Zeit</cd>
    </cds>
  </band>
  <artist>
    <name>Michael Jackson</name>
    <cds>
      <cd>Bad</cd>
      <cd>Thriller</cd>
    </cds>
  </artist>
</collection>
```

#### Challenge

Output the name of the first-listed CD by the last artist in the list.

#### Desired selection (text output)

`Bad`

> #### Answer
> `/collection/artist[last()]/cds/cd[1]/text()`

---

## Changing character string

#### Source document

```xml
<hooey>HTTP is a markup language.</hooey>
```

#### Challenge

Correct the false statement by means of XPath as specified below.

#### Desired selection (text output)

`HTML is a markup language.`

> #### Answer
> `concat('HTML', substring(/hooey/text(), 5))`

---

## Counting nodes with condition

#### Source document

```xml
<jobs>
  <job priority="critical" name="Müll rausbringen" />
  <job priority="low" name="Möbel säubern" />
  <job priority="low" name="Teppich reinigen" />
  <job priority="medium" name="Fenster putzen" />
  <job priority="high" name="Pflanzen gießen" />
</jobs>
```

#### Challenge

Output the number of jobs with the priority “low”.

#### Desired selection (text output)

`2`

> #### Answer
> `count(/jobs/job[@priority='low'])`

---

## Comparisons in conditions

#### Source document

```xml
<jobs>
  <job name="cut Screens" availableBudget="8">
    <work usedBudget="3">Conception</work>
    <work usedBudget="3">Implementation</work>
    <work usedBudget="2.5">Implementation</work>
  </job>
  <job name="build HTML-demonstration" availableBudget="6">
    <work usedBudget="1.5">Conception</work>
    <work usedBudget="2.5">Implementation</work>
    <work usedBudget="2">Implementation</work>
  </job>
  <job name="CMS implementation" availableBudget="12">
    <work usedBudget="1">Data model</work>
    <work usedBudget="4">Editor-Customizing</work>
    <work usedBudget="4">Transformations</work>
    <work usedBudget="2">QS</work>
  </job>
</jobs>
```

#### Challenge

Select all job elements with an exceeded budget (attribute “availableBudget”). Count the total number of used units (attribute “usedBudget”) of all work-elements of a job for this purpose.

#### Desired selection (XML-output)

```xml
<job name="cut Screens" availableBudget="8">
<work usedBudget="3">Conception</work>
<work usedBudget="3">Implementation</work>
<work usedBudget="2.5">Implementation</work>
</job>
```

> #### Answer
> `/jobs/job[@availableBudget < sum(work/@usedBudget)]`

---

## Selecting all links which open a new window

#### Source document

```xml
<document id="1">
  <content>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
      </head>
      <body>
        <div>
          <ul>
            <li><a href="http://www.google.de/" target="_new">http://www.google.de/Google</a>
              <ul>
                <li><a href="http://earth.google.de/">Google Earth</a></li>
                <li><a href="http://picasa.google.de/intl/de/" target="_new">Picasa</a></li>
              </ul>
            </li> 
            <li><a href="http://www.heise.de/" target="_new">Heise</a></li> 
            <li><a href="http://www.yahoo.de/">Yahoo</a></li> 
          </ul>
        </div>
      </body>
    </html>
  </content>
</document>
```

#### Challenge

Collect all links in the document (XHTML elements of type a, which are attached to the namespace “http://www.w3.org/1999/xhtml”) which are opened in a new window (attribute "target=_new").

#### Desired selection (XML-output)

```xml
<a href="http://www.google.de/" target="_new" xmlns="http://www.w3.org/1999/xhtml">http://www.google.de/Google</a>
<a href="http://picasa.google.de/intl/de/" target="_new" xmlns="http://www.w3.org/1999/xhtml">Picasa</a>
<a href="http://www.heise.de/" target="_new" xmlns="http://www.w3.org/1999/xhtml">Heise</a>
```

> #### Answer
> `/document//x:a[@target='_new']`

---

## Calculating position

#### Source document

```xml
<artist name="Robbie Williams">
  <cd name="Rudebox"> 
    <title>Rudebox</title>
    <title>Viva Life On Mars</title>
    <title>Lovelight (Lewis Taylor Cover)</title>
    <title>King Of The Bongo (Manu Chao Cover)</title>
    <title>Swing when you're winning</title>
    <title>Good Doctor</title>
  </cd> 
</artist>
```

#### Challenge

Calculate the title number using its position.

#### Desired selection (text output)

`5`

> #### Answer
> `count(//title[text() = "Swing when you're winning"]/preceding-sibling::title) + 1`

---

## Changing small to capital letters

#### Source document

```xml
<text>this is a test</text>
```

#### Challenge

Change all small letters into capital letters.

Note that the method »upper-case()« is a function of XPath 2.0. onion.net currently only supports functions from XPath 1.0.

#### Desired selection (text output)

`THIS IS A TEST`

> #### Answer
> `translate(/text/text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')`

---

## Selecting querystring

#### Source document

```xml
<link>http://www.google.de/search?hl=de&amp;q=getit&amp;meta=</link>
```

#### Challenge

Select the text behind the question mark

#### Desired selection (text output)

`hl=de&q=getit&meta=`

> #### Answer
> `substring-after(/link/text(), '?')`

---

## Elements with mixed text

#### Source document

```xml
<items>
  <item width="25px" />
  <item width="50px" />
  <item width="65px" />
  <item width="100px" />
  <item width="15pt" />
  <item width="40%" />
  <item width="25px" />
</items>
```

#### Challenge

Output all item-elements that are specified in “px” and do not exceed the value of 70.

Note that the method »ends-with()« is a function of XPath 2.0. onion.net currently only supports functions from XPath 1.0.

#### Desired selection (XML-output)

```xml
<item width="25px" />
<item width="50px" />
<item width="65px" />
<item width="25px" />
```

> #### Answer
> `/items/item[contains(@width, 'px') and number(substring-before(@width, 'px')) <= 70]`

---

## Filtering by key and numerical value

#### Source document

```xml
<items>
  <item width="25" unit="px" />
  <item width="50" unit="px" />
  <item width="65" unit="px" />
  <item width="100" unit="px" />
  <item width="15" unit="pt" />
  <item width="40" unit="%" />
  <item width="25" unit="px" />
</items>
```

#### Challenge

Output all item elements which are specified in “px” and do not exceed the value of 70

#### Desired selection (XML-output)

```xml
<item width="25" unit="px" />
<item width="50" unit="px" />
<item width="65" unit="px" />
<item width="25" unit="px" />
```

> #### Answer
> `/items/item[@unit='px' and @width<=70]`

---

## Totalling by key and numerical value

#### Source document

```xml
<items>
  <item width="25" unit="px" />
  <item width="50" unit="px" />
  <item width="65" unit="px" />
  <item width="100" unit="px" />
  <item width="15" unit="pt" />
  <item width="40" unit="%" />
  <item width="25" unit="px" />
</items>
```

#### Challenge

Add up all width-properties of the item-elements that are specified in “px” and do not exceed the value of 70

#### Desired selection (text output)

`165`

> #### Answer
> `sum(/items/item[@unit='px' and @width<=70]/@width)`

---

## Selecting subelements by number

#### Source document

```xml
<collection>
  <artist>
    <name>Robbie Williams</name>
    <cds>
      <cd>Rudebox</cd>
      <cd>Viva Life On Mars</cd>
      <cd>Lovelight (Lewis Taylor Cover)</cd>
      <cd>King Of The Bongo (Manu Chao Cover)</cd>
      <cd>Swing when you're winning</cd>
      <cd>Good Doctor</cd>
    </cds>
  </artist>
  <band>
    <name>Juli</name>
    <cds>
      <cd>Ein neuer Tag</cd>
    </cds>
  </band>
  <band>
    <name>Silbermond</name>
    <cds>
      <cd>Verschwende deine Zeit</cd>
    </cds>
  </band>
  <artist>
    <name>Michael Jackson</name>
    <cds>
      <cd>Bad</cd> 
    </cds>
  </artist>
</collection>
```

#### Challenge

Select all band or artist elements containing only one CD.

#### Desired selection (XML-output)

```xml
<band>
<name>Juli</name>
<cds>
<cd>Ein neuer Tag</cd>
</cds>
</band>
<band>
<name>Silbermond</name>
<cds>
<cd>Verschwende deine Zeit</cd>
</cds>
</band>
<artist>
<name>Michael Jackson</name>
<cds>
<cd>Bad</cd>
</cds>
</artist>
```

> #### Answer
> `/collection/*[count(cds/cd)=1]`

---

## Selecting elements by namespace

#### Source document

```xml
<items>
  <item_a xmlns="http://www.getit.de/2006/" value="A" />
  <item_b xmlns="http://www.getit.de/2005/" value="B" />
  <item_c xmlns="http://www.getit.de/2004/" value="C" />
  <item_d xmlns="http://www.getit.de/2003/" value="D" />
  <item_e xmlns="http://www.getit.de/2002/" value="E" />
  <item_f xmlns="http://www.getit.de/2001/" value="F" />
  <item_g xmlns="http://www.getit.de/2000/" value="G" />
  <item_h xmlns="http://www.isa-team.de/2002/" value="H" />
  <item_i xmlns="http://www.isa-team.de/2001/" value="I" />
  <item_j xmlns="http://www.isa-team.de/2000/" value="J" />
</items>
```

#### Challenge

Select all elements attached to a namespace below “http://www.getit.de/”.

#### Desired selection (XML-output)

```xml
<item_a value="A" xmlns="http://www.getit.de/2006/" />
<item_b value="B" xmlns="http://www.getit.de/2005/" />
<item_c value="C" xmlns="http://www.getit.de/2004/" />
<item_d value="D" xmlns="http://www.getit.de/2003/" />
<item_e value="E" xmlns="http://www.getit.de/2002/" />
<item_f value="F" xmlns="http://www.getit.de/2001/" />
<item_g value="G" xmlns="http://www.getit.de/2000/" />
```

> #### Answer
> `/items/*[starts-with(namespace-uri(), 'http://www.getit.de/')]`

---

## Change XML document into CSV output

#### Source document

```xml
<row>
  <cell name="id">1</cell>
  <cell name="firstName">Hans</cell>
  <cell name="lastName">Peter</cell>
  <cell name="age">44</cell>
</row>
```

#### Challenge

Change the XML representation into a comma-separated variant.

#### Desired selection (text output)

`1;"Hans";"Peter";44`

> #### Answer
> `concat(/row/cell[1], ';"', /row/cell[2], '";"', /row/cell[3], '";', /row/cell[4])`