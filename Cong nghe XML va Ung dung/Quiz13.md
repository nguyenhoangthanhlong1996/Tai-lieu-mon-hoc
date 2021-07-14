Quiz 13
===

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