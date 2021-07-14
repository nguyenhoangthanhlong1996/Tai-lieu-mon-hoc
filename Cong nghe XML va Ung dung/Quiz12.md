Quiz 12
===

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