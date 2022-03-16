# 4. Collections
## Materials
- [Collections](https://docs.oracle.com/javase/tutorial/collections/index.html)
- [Lecture 9](https://drive.google.com/file/d/13TibWYVYc8CaFVcLUMklHLrqkmS5Q6h\_/view?usp=sharing)
- [XML](https://en.wikipedia.org/wiki/XML)
- [XML processing](https://docs.oracle.com/javase/tutorial/jaxp/)

## VideoLectures
[Collections playlist](https://youtube.com/playlist?list=PL96uE92X-ozdXTE7PoMfFQJ2CBz-hv9YK)
- [Collections, Lists, Sets, Unmodifiable Lists and Sets](https://youtu.be/boKGhpMjqUw)
- [Lists, Sets, Maps, Unmodifiable Maps](https://youtu.be/48s7xoswhkE)
- [Generics (part 1)](https://youtu.be/vT2I3LcLWJM)
- [Generics (part 2)](https://youtu.be/9mwtYNzWUV0)
- [Functional Interfaces, Introduction to lambdas](https://youtu.be/Fp2R6uY02o8)
- [Lambdas](https://youtu.be/RQ30tUIppQU)
- [Stream API](https://youtu.be/4coew\_omxRo)
- [PrimitiveStream, Optional](https://youtu.be/KU6RbxTzTj0)
## Task #4
Starting extend our store. Please append ability user to interact with our store, while sending commands through read stream.
Add support of such commands: 
- `sort` - products from store according config. In resources folder create xml config file like
```
xml
<sort>
    <name>asc</name>
    <price>asc</price>
    <rate>desc</rate>
</sort>
```
Config file can contains from 1 to N fields. Sort should be done using `Comparator`. Sort and print should not modify
 default store product lists and their order.
- `top` - print top 5 products of whole store sorted via price desc
- `quit` - exit app

