Quiz 10
===

---

## Attribut selection

#### Source document

```xml
<document name="getit Übungsaufgaben" />
```

#### Challenge

Output the content of the attribute “name”.

#### Desired selection (text output)

`getit Übungsaufgaben`

> #### Answer
> `/document/@name `

---

## Conditional element selection

#### Source document

```xml
<document xmlns:xlink="http://www.w3.org/1999/xlink">
  <linkList name="A">
    <document xlink:href="15024" />
    <document xlink:href="15028" />
  </linkList>
  <linkList name="B">
    <document xlink:href="15030" />
    <document xlink:href="15032" />
  </linkList>
</document>
```

#### Challenge

Select all document elements below the node “linkList” with the name A.

#### Desired selection (XML-output)

```xml
<document xlink:href="15024" xmlns:xlink="http://www.w3.org/1999/xlink" />
<document xlink:href="15028" xmlns:xlink="http://www.w3.org/1999/xlink" />
```

> #### Answer
> `/document/linkList[@name = 'A']/document`

---

## Merging character strings

#### Source document

```xml
<person>
  <lastName>Peter</lastName>
  <firstName>Hans</firstName>
</person>
```

#### Challenge

Create an output consisting of the elements “lastName”, the character string ", " and firstName.

#### Desired selection (text output)

`Peter, Hans`

> #### Answer
> `concat(person/lastName, ', ', person/firstName)`

---

## Filtering by attribute value

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

Select all jobs with a priority corresponding to the value “critical” or “high”.

#### Desired selection (XML-output)

```xml
<job priority="critical" name="Müll rausbringen" />
<job priority="high" name="Pflanzen gießen" />
```

> #### Answer
> `/jobs/job[@priority = 'critical' or @priority = 'high']`

---

## Filtering by numeric values

#### Source document

```xml
<persons>
  <person firstName="Hans" lastName="Mustermann" age="28" />
  <person firstName="Herbert" lastName="Möllemann" age="33" />
  <person firstName="Peter" lastName="Meier" age="37" />
  <person firstName="Ulrike" lastName="Albrecht" age="45" />
</persons>
```

#### Challenge

Select all persons aged less than 35 years.

#### Desired selection (XML-output)

```xml
<person firstName="Hans" lastName="Mustermann" age="28" />
<person firstName="Herbert" lastName="Möllemann" age="33" />
```

> #### Answer
> `/persons/person[@age < 35]`

---

## Limiting number

#### Source document

```xml
<persons>
  <person firstName="Hans" lastName="Mustermann" age="28" />
  <person firstName="Herbert" lastName="Möllemann" age="33" />
  <person firstName="Peter" lastName="Meier" age="37" />
  <person firstName="Ulrike" lastName="Albrecht" age="45" />
</persons>
```

#### Challenge

Select the first three person elements.

#### Desired selection (XML-output)

```xml
<person firstName="Hans" lastName="Mustermann" age="28" />
<person firstName="Herbert" lastName="Möllemann" age="33" />
<person firstName="Peter" lastName="Meier" age="37" />
```

> #### Answer
> `/persons/person[position() <= 3]`

---

## Filtering by character conditions

#### Source document

```xml
<persons>
  <person firstName="Hans" lastName="Mustermann" age="28" />
  <person firstName="Herbert" lastName="Möllemann" age="33" />
  <person firstName="Peter" lastName="Meier" age="37" />
  <person firstName="Ulrike" lastName="Albrecht" age="45" />
</persons>
```

#### Challenge

Select all persons whose first name begins with the letter H.

#### Desired selection (XML-output)

```xml
<person firstName="Hans" lastName="Mustermann" age="28" />
<person firstName="Herbert" lastName="Möllemann" age="33" />
```

> #### Answer
> `/persons/person[starts-with(@firstName, 'H')]`

---

## Filtering by text length

#### Source document

```xml
<persons>
  <person firstName="Hans" lastName="Mustermann" age="28" />
  <person firstName="Herbert" lastName="Möllemann" age="33" />
  <person firstName="Peter" lastName="Meier" age="37" />
  <person firstName="Ulrike" lastName="Albrecht" age="45" />
  <person firstName="Uwe" lastName="Peters" age="34" />
</persons>
```

#### Challenge

Select all person elements with an attribute “firstName” a maximum of 5 characters long.

#### Desired selection (XML-output)

```xml
<person firstName="Hans" lastName="Mustermann" age="28" />
<person firstName="Peter" lastName="Meier" age="37" />
<person firstName="Uwe" lastName="Peters" age="34" />
```

> #### Answer
> `/persons/person[string-length(@firstName) <= 5]`

---

## Rounding up and down and adding

#### Source document

```xml
<numbers>
  <number>33</number>
  <number>34.4</number>
  <number>33.8</number>
  <number>33.43</number>
  <number>34.46</number>
  <number>35</number>
  <number>33.49</number>
  <number>33.00</number>
</numbers>
```

#### Challenge

Determine the sum of all numbers, which round off to 34.

#### Desired selection (text output)

`102,66`

> #### Answer
> `sum(/numbers/number[round(.) = 34])`

---

## Selecting the following siblings

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

Select the following product-siblings relatively from their current node.

#### Desired selection (XML-output)

```xml
<product id="4" name="Tasse" price="1.99" category="1" />
<product id="5" name="Apfelsaft" price="1.49" category="1" />
<product id="6" name="CD-Rohling" price="0.89" category="2" />
```

> #### Answer
> `/products/product[@id=3]/following-sibling::product`

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