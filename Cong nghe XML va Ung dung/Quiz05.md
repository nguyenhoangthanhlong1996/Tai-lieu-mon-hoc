CNXML - Quiz 05
=====

## Câu 1

Làm thế nào để miêu tả múi giờ kiểu `xs:duration`?

> <ans>Câu trả lời:</ans>
>
> Kiểu `xs:duration` được sử dụng để chỉ định khoảng thời gian, không thể miêu tả múi giờ ở kiểu này

## Câu 2


Cho định nghĩa tag 

```xml
<xs:element name="period" type="xs:duration"/>
```


Cho biết thời gian được miêu tả của các tag sau với định nghĩa trên. 

* `<period>P2M</period>`
* `<period>PT2M</period>`
* `<period>P2MT2M</period>`

> <ans>Câu trả lời:</ans>
> |Tag|Miêu tả khoảng thời gian|
> |:---|:---|
> |`<period>P2M</period>`|2 tháng|
> |`<period>PT2M</period>`|2 phút|
> |`<period>P2MT2M</period>`|2 tháng, 2 phút|

## Câu 3

Cho biết miền giá trị của các kiểu dữ liệu sau

* `byte`
* `unsignedByte` 
* `short`
* `unsignedShort` 
* `int`
* `unsignedInt` 
* `long`
* `unsignedLong` 
* `negativeInteger` 
* `nonNegativeInteger`  
* `nonPositiveInteger` 
* `positiveInteger` 

> <ans>Câu trả lời:</ans>
> |Kiểu|Mô tả|Từ|Đến|
> |:---|:---|---:|---:|
> |`byte`|Số nguyên 8-bit có dấu|-2<sup>7</sup>|2<sup>7</sup>-1|
> |`unsignedByte`|Số nguyên 8-bit không dấu|0|2<sup>8</sup>-1|
> |`short`|Số nguyên 16-bit có dấu|-2<sup>15</sup>|2<sup>15</sup>-1|
> |`unsignedShort`|Số nguyên 16-bit không dấu|0|2<sup>16</sup>-1|
> |`int`|Số nguyên 32-bit có dấu|-2<sup>31</sup>|2<sup>31</sup>-1|
> |`unsignedInt`|Số nguyên 32-bit không dấu|0|2<sup>32</sup>-1|
> |`long`|Số nguyên 64-bit có dấu|-2<sup>63</sup>|2<sup>63</sup>-1|
> |`unsignedLong`|Số nguyên 64-bit không dấu|0|2<sup>64</sup>-1|
> |`negativeInteger`|Số nguyên âm|-&#x221e;|-1|
> |`nonNegativeInteger`|Số nguyên không âm|0|+&#x221e;|
> |`positiveInteger`|Số nguyên dương|1|+&#x221e;|
> |`nonPositiveInteger`|Số nguyên không dương|-&#x221e;|0|


## Câu 4

Liệt kê các ràng buộc mà kiểu `xs:boolean` không dùng được trong slide.

> <ans>Câu trả lời:</ans>
> |Loại ràng buộc|Dùng được|
> |:---|:---:|
> |enumeration|&#10060;|
> |fractionDigits|&#10060;|
> |length|&#10060;|
> |maxExclusive|&#10060;|
> |maxInclusive|&#10060;|
> |maxLength|&#10060;|
> |minExclusive|&#10060;|
> |minInclusive|&#10060;|
> |minLength|&#10060;|
> |pattern|&#10060;|
> |totalDigits|&#10060;|
> |whiteSpace|&#10004;|