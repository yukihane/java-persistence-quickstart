one-to-many 関連がある2種類のエンティティがあるとき、
manyの方は全て欲しいわけではなく、特定の条件に一致したものだけを取得したい。
ただし特定の条件に一致したものがない場合もone側のエンティティは取得したい。

```sql
select p from Paremt p
left join fetch p.children c with c.name = 'Alice'
```

のようなことがやりたいが、 join fetch と with は同時に利用できない。

仕方がないのでjoin fetchは諦め別々に取得する。

```sql
select p, c from Parent p
left join p.children c with c.name = 'Alice'
```

結果は `Tuple` 型になるので、それぞれ取得する。

- ref: [1.6.2. Queries with multiple projected items](https://docs.jboss.org/hibernate/orm/6.6/querylanguage/html_single/Hibernate_Query_Language.html#query-result-types-multiple)
